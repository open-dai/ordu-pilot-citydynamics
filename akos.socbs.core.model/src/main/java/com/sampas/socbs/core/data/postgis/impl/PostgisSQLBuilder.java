/*
 *    GeoTools - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002-2006, GeoTools Project Managment Committee (PMC)
 * 
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package com.sampas.socbs.core.data.postgis.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.geotools.data.jdbc.DefaultSQLBuilder;
import org.geotools.data.jdbc.JDBCDataStoreConfig;
import org.geotools.data.jdbc.fidmapper.FIDMapper;
import org.geotools.factory.Hints;
import org.geotools.feature.AttributeType;
import org.geotools.feature.FeatureType;
import org.geotools.feature.GeometryAttributeType;
import org.geotools.filter.Filter;
import org.geotools.filter.SQLEncoder;
import org.geotools.filter.SQLEncoderException;
import org.opengis.filter.sort.SortBy;
import org.opengis.filter.sort.SortOrder;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * Builds sql for postgis.
 *
 * @author Chris Holmes
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/plugin/postgis/src/main/java/org/geotools/data/postgis/PostgisSQLBuilder.java $
 */
@SuppressWarnings({"unused", "unchecked", "deprecation"})
public class PostgisSQLBuilder extends DefaultSQLBuilder {
	
    /** If true, WKB format is used instead of WKT */
    protected boolean WKBEnabled = false;
    
    /** If true, ByteA function is used to transfer WKB data*/
    protected boolean byteaEnabled = false;

    /** If true, tables are qualified with a schema **/
    protected boolean schemaEnabled = true;
    
    /** The DataStore we are building SQL for **/
    protected JDBCDataStoreConfig config;
    
    /**
     * Strategy used to encode column information into SQL.
     * <p>
     * Different stratagies are defined according to:
     * <ul>
     * <li>how they handle CoordinateReferenceSystem information with 3D data
     * <li>if the geometry information is requested using WKB
     * <li>if "Byte A" needs to be enabled
     * </ul>
     */
    private class ColumnStratagy {    	
    	/**
    	 * The base Column Stratagy does nothing special.
    	 * 
    	 * @param sql
    	 * @param attribute
    	 */
    	public StringBuffer sqlColumn(StringBuffer sql, AttributeType attribute){
    		sql.append( "\"" );
    		sql.append( attribute.getLocalName() );
    		sql.append( "\"" );
    		return sql;
    	}
    }

    /**
     * Construct with a provided SRID; mostly used for testing.
     */
    public PostgisSQLBuilder(int srid, JDBCDataStoreConfig config) {
        this((SQLEncoder) new SQLEncoderPostgis(srid),config);
    }

    /**
     * Constructor with encoder.  Use PostgisSQLBuilder(encoder, config, ft) if possible.
     * 
     * @param encoder
     */
    public PostgisSQLBuilder(SQLEncoder encoder, JDBCDataStoreConfig config) {
        super(encoder);
        this.config = config;
    }
    
    public PostgisSQLBuilder(SQLEncoder encoder, JDBCDataStoreConfig config, FeatureType ft) {
    	super(encoder);
        this.config = config;
        this.ft = ft;
        encoder.setFeatureType( ft );
    }
    
        
    /**
     * Overrides to support offset and maxFeatures
     */
    //@Override
    public String buildSQLQuery(String typeName,
            FIDMapper mapper,
            AttributeType[] attrTypes,
            org.opengis.filter.Filter filter,
            SortBy[] sortBy,
            Integer offset,
            Integer limit) throws SQLEncoderException {
        
        if(offset != null){
            //we need to add the PK as sorting order regardless of the client asking for a specific order or not
            //so we can ensure a consistent order if the client asks for ordering over an attribute other than the PK
           List/*<SortBy>*/ sortAtts = new ArrayList/*<SortBy>*/();
            if(sortBy != null){
                sortAtts.addAll(Arrays.asList(sortBy));
            }
            if(!(sortAtts.contains(SortBy.NATURAL_ORDER) || sortAtts.contains(SortBy.REVERSE_ORDER))){
                //no natural order contained in the required list, append PK ordering...
                sortAtts.add(SortBy.NATURAL_ORDER);
            }
            sortBy = (SortBy[]) sortAtts.toArray(new SortBy[sortAtts.size()]);
        }
        
        final String selectStatement = super.buildSQLQuery(typeName, mapper, attrTypes, filter, sortBy, offset, limit);
        StringBuffer sb = new StringBuffer(selectStatement);
        
        if(offset != null){
            sb.append(" OFFSET ").append(offset);
        }
        
        if(limit != null){
            sb.append(" LIMIT ").append(limit);
        }
        
        return sb.toString();
    }
        
    /**
     * Overrides to support NATURAL_ORDER and REVERSE_ORDER
     */
    //@Override
    protected void addOrderByPK(StringBuffer sql, FIDMapper mapper, SortOrder sortOrder)
            throws SQLEncoderException {
        if (mapper == null || mapper.getColumnCount() == 0) {
            throw new SQLEncoderException(
                    "NATURAL_ORDER and REVERSE_ORDER is not supported without a primary key");
            }
     
            final String order = SortOrder.ASCENDING == sortOrder ? "ASC" : "DESC";
        String colName;
        final int columnCount = mapper.getColumnCount();
        for (int idx = 0; idx < columnCount; idx++) {
            colName = mapper.getColumnName(idx);
            sql.append(colName).append(" ").append(order);
            if (idx < columnCount - 1) {
                sql.append(", ");
            }
        }
    }
    

    /**
     * Produces the select information required.
     * 
     * <p>
     * The featureType, if known, is always requested.
     * </p>
     * 
     * <p>
     * sql: <code>featureID (,attributeColumn)</code>
     * </p>
     * 
     * <p>
     * We may need to provide AttributeReaders with a hook so they can request
     * a wrapper function.
     * </p>
     *
     * @param sql
     * @param mapper
     * @param attributes
     */
    public void sqlColumns(StringBuffer sql, FIDMapper mapper,
        AttributeType[] attributes) {
    	
        for (int i = 0; i < mapper.getColumnCount(); i++) {
            sql.append("\""+mapper.getColumnName(i)+"\"");
            //DJB: add quotes in.  NOTE: if FID mapper isnt oid (ie. PK - Primary Key),
            // you could be requesting PK columns multiple times             
            if ((attributes.length > 0) || (i < (mapper.getColumnCount() - 1))) {
                sql.append(", ");
            }
        }

        for (int i = 0; i < attributes.length; i++) {
        	AttributeType attribute = attributes[i];

        	if (attribute instanceof GeometryAttributeType) {   
        		GeometryAttributeType geometryAttribute = (GeometryAttributeType) attribute;
        		CoordinateReferenceSystem crs = geometryAttribute.getCoordinateSystem();
    			final int D = crs == null ? 2 : crs.getCoordinateSystem().getDimension();
    			
                if (WKBEnabled) {
                    if(byteaEnabled) {
                    	columnGeometryByteaWKB( sql, geometryAttribute, D );
                    } else {
                    	columnGeometryWKB( sql, geometryAttribute, D );
                    }
                } else {
                	columnGeometry( sql, geometryAttribute, D );
                }
            } else {
            	columnAttribute(sql, attribute);
            }

            if (i < (attributes.length - 1)) {
                sql.append(", ");
            }
        }
    }
    /** Used when WKB "ByteA" is enabled */
    private void columnGeometryByteaWKB(StringBuffer sql,
			GeometryAttributeType geometryAttribute, final int D) {
    	
    	sql.append("encode(");
		if( D == 3 ){
			sql.append("asEWKB(");
		}
		else {
			sql.append("asBinary(");
		}
		columnGeometry(sql, geometryAttribute.getLocalName(), D );
		sql.append(",'XDR'),'base64')");
	}
    /** Used when plain WKB is enabled */   
    private void columnGeometryWKB(StringBuffer sql,
			GeometryAttributeType geometryAttribute, final int D) {
    	
    	if( D == 3 ){
			sql.append("asEWKB(");
		}
		else {
			sql.append("asBinary(");
		}
		columnGeometry(sql, geometryAttribute.getLocalName(), D );
		sql.append(",'XDR')");
	}
    /** Used to request a text format. */    
    private void columnGeometry(StringBuffer sql,
			GeometryAttributeType geometryAttribute, final int D) {
    	if( D == 3 && !isForce2D() ){
			sql.append("asEWKT(");
		}
		else {
			sql.append("asText(");
		}
		columnGeometry(sql, geometryAttribute.getLocalName(), D );
		sql.append(")");
	}
    /**
     * Used to wrap the correct function (force_3d or force3d) around
     * the request for geometry data.
     * <p>
     * This method prevents the request of extra ordinates that will
     * not be used.
     * 
     * @see isForce2D
     * @see Hints.FEATURE_2D
     *  
     * @param sql
     * @param geomName
     * @param D
     */
    private void columnGeometry(StringBuffer sql,String geomName, final int D) {
    	if (D == 2 || isForce2D() ){ 
			sql.append("force_2d(\"" + geomName + "\")");
		}
    	else if( D == 3 ){
			sql.append("force_3d(\"" + geomName + "\")");
		}
		else {
			// D = 4?
			// force 2D is the default behaviour until you report
			// this as a bug and are willing to test with real data!
			sql.append("force_2d(\"" + geomName + "\")" );
		}
    }
    
	private final void columnAttribute(StringBuffer sql, AttributeType attribute){
        sql.append( "\"" );
        sql.append( attribute.getLocalName() );
        sql.append( "\"" );
    }
    
    /**
     * Constructs FROM clause for featureType
     * 
     * <p>
     * sql: <code>FROM typeName</code>
     * </p>
     *
     * @param sql
     * @param typeName
     */
    public void sqlFrom(StringBuffer sql, String typeName) {
        sql.append(" FROM ");
        sql.append(encodeTableName(typeName));
    }

    /**
     * Constructs WHERE clause, if needed, for FILTER.
     * 
     * <p>
     * sql: <code>WHERE filter encoding</code>
     * </p>
     *
     * @param sql DOCUMENT ME!
     * @param preFilter DOCUMENT ME!
     *
     * @throws SQLEncoderException DOCUMENT ME!
     */
    public void sqlWhere(StringBuffer sql, Filter preFilter)
        throws SQLEncoderException {
        if ((preFilter != null) || (preFilter == org.geotools.filter.Filter.NONE)) {
            String where = encoder.encode(preFilter);
            sql.append(" ");
            sql.append(where);
        }
    }

    /**
     * Returns true if the WKB format is used to transfer geometries, false
     * otherwise
     *
     */
    public boolean isWKBEnabled() {
        return WKBEnabled;
    }

    /**
     * If turned on, WKB will be used to transfer geometry data instead of  WKT
     *
     * @param enabled
     */
    public void setWKBEnabled(boolean enabled) {
        WKBEnabled = enabled;
    }
    
    /**
     * Enables the use of the bytea function to transfer faster WKB geometries
     */
    public boolean isByteaEnabled() {
        return byteaEnabled;
    }
    /**
     * Enables/disables the use of the bytea function
     * @param byteaEnable
     */
    public void setByteaEnabled(boolean byteaEnable) {
        byteaEnabled = byteaEnable;
    }
    /**
     * Enables/disables schema name qualification.
     */
    public void setSchemaEnabled(boolean schemaEnabled) {
		this.schemaEnabled = schemaEnabled;
	}
    /**
     * @return true if table names are prefixed with the containing schema.
     */
    public boolean isSchemaEnabled() {
		return schemaEnabled;
	}
    
    /**
     * Encode the table name (if schemaEnabled is true the schema name
     * will be prepended).
     * 
     * @param tableName
     * @return "tableName" or "schema"."tableName"
     */
    public String encodeTableName(String tableName) {
    	return schemaEnabled ? 
			"\"" + config.getDatabaseSchemaName() + "\".\"" + tableName + "\"" : 
			"\"" + tableName + "\""; 
    }
    /**
     * Surround columnName with quotes.
     * @param columnName
     * @return "columnName"
     */
    public String encodeColumnName(String columnName) {
    	return "\"" + columnName + "\""; 
    }

}
