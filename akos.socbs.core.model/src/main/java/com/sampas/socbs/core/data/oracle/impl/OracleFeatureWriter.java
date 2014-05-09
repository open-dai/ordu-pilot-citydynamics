/*
 *    GeoTools - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002-2006, Geotools Project Managment Committee (PMC)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package com.sampas.socbs.core.data.oracle.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OracleConnection;
import oracle.sql.STRUCT;

import org.geotools.data.DataSourceException;
import org.geotools.data.DataUtilities;
import org.geotools.data.FeatureReader;
import org.geotools.data.jdbc.JDBCTextFeatureWriter;
import org.geotools.data.jdbc.MutableFIDFeature;
import org.geotools.data.jdbc.QueryData;
import org.geotools.data.jdbc.datasource.DataSourceFinder;
import org.geotools.data.jdbc.datasource.UnWrapper;
import org.geotools.feature.AttributeType;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureType;
import org.geotools.feature.type.GeometricAttributeType;

import com.vividsolutions.jts.geom.Geometry;


/**
 * Subclasses JDBCTextFeatureWriter to issue Oracle transactions  directly as
 * sql text statements.  The super class takes care of all the nasty details,
 * this just returns the encoded geometry. To get some speed increases Jody
 * maintains that this class should not be used, that the updatable result
 * sets of JDBCFeatureWriter will work better.  But I couldn't get those to
 * work at all, whereas this works great for me.  We could also consider
 * putting the option for this or jdbc in the factory for OracleDataStore.
 * Should also consider using prepared statements for inserts, as they should
 * work faster - this should probably be done in the superclass.
 *
 * @author Chris Holmes, TOPP
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/unsupported/oracle-spatial/src/main/java/org/geotools/data/oracle/OracleFeatureWriter.java $
 * @version $Id: OracleFeatureWriter.java 27862 2007-11-12 19:51:19Z desruisseaux $
 */
@SuppressWarnings("deprecation")
public class OracleFeatureWriter extends JDBCTextFeatureWriter {
	private static final Logger LOGGER = org.geotools.util.logging.Logging.getLogger("org.geotools.data.oracle");
	
	GeometryConverter converter;
	
    public OracleFeatureWriter(FeatureReader fReader, QueryData queryData )
        throws IOException {
        super(fReader, queryData);
        Connection conn = queryData.getConnection();
        if(!(conn instanceof OracleConnection)) {
            UnWrapper uw = DataSourceFinder.getUnWrapper(conn);
            if(uw != null)
                conn = uw.unwrap(conn);
        }
        OracleConnection oracleConnection = (OracleConnection) conn;
        this.converter = new GeometryConverter(oracleConnection);
    }

    protected String getGeometryInsertText(Geometry geom, int srid)
        throws IOException {
    	return "?"; // Please use a prepaired statement to insert your geometry
    	
        //String geomText = SQLEncoderOracle.toSDOGeom(geom, srid);
        //return geomText;
        }

    /**
     * Override that uses sql statements to perform the operation.
     *
     * @see org.geotools.data.jdbc.JDBCFeatureWriter#doUpdate(org.geotools.feature.Feature,
     *      org.geotools.feature.Feature)
     */
    protected void doUpdate(Feature live, Feature current)
        throws IOException, SQLException {
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("updating postgis feature " + current);
        }

        PreparedStatement statement = null;
        Connection conn = null;

        try {
            conn = queryData.getConnection();

            String sql = makeUpdateSql(live, current);
            statement = conn.prepareStatement(sql);

            FeatureType schema = current.getFeatureType();
            int position = 1;

            for (int i = 0; i < current.getNumberOfAttributes(); i++) {
                AttributeType type = schema.getAttributeType(i);

                if (type instanceof GeometricAttributeType && !DataUtilities.attributesEqual(current.getAttribute(i), live.getAttribute(i))) {
                    Geometry geometry = (Geometry) current.getAttribute(i);

                    LOGGER.fine("ORACLE SPATIAL: geometry to be written:"
                        + geometry);
                    
                    int srid = queryData.getFeatureTypeInfo().getSRID(type.getName());
                    geometry.setSRID(srid);
                    
                    STRUCT struct = converter.toSDO(geometry);
                    statement.setObject(position, struct);
                    LOGGER.fine(
                        "ORACLE SPATIAL: set geometry parameter at position:"
                        + position);
                    position++;

                    break;
                }
            }

            // System.out.println(sql);
            LOGGER.fine(sql);
            statement.execute();
        } catch (SQLException sqle) {
            String msg = "SQL Exception writing geometry column"
                + sqle.getLocalizedMessage();
            LOGGER.log(Level.SEVERE, msg, sqle);
            queryData.close(sqle);
            throw new DataSourceException(msg, sqle);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    String msg = "Error closing JDBC Statement";
                    LOGGER.log(Level.WARNING, msg, e);
                }
            }
        }
    }

    
    /**
     * Override that uses sql prepaired statements to perform the operation.
     *
     * @see org.geotools.data.jdbc.JDBCFeatureWriter#doInsert(org.geotools.data.jdbc.MutableFIDFeature)
     */
    protected void doInsert(MutableFIDFeature current) throws IOException, SQLException {
        
    	LOGGER.fine("inserting into oracle feature " + current);
        
        
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            conn = queryData.getConnection();
            String sql = makeInsertSql(current);

//  //Added by Sampas GIS Developer
//  ///////////////////////////////////////////////////////////////////
            sql = sqlDateFixer(sql);
//  ///////////////////////////////////////////////////////////////////

            statement = conn.prepareStatement( sql );
            
            int position = 1;
            FeatureType schema = current.getFeatureType();
            for( int i=0; i<current.getNumberOfAttributes();i++){
            	AttributeType type = schema.getAttributeType( i );
            	if( type instanceof GeometricAttributeType ){
            		Geometry geometry = (Geometry) current.getAttribute( i );
                    
                    // set the proper SRID, otherwise insertion will fail due to issues
                    // with the spatial index
                    int srid = queryData.getFeatureTypeInfo().getSRID(type.getName());
                    geometry.setSRID(srid);
                    
            		STRUCT struct = converter.toSDO( geometry );
            		statement.setObject( position, struct );
            		position++;
            	}
            }
            LOGGER.fine(sql);
            statement.execute();

            // should the ID be generated during an insert, we need to read it back
            // and set it into the feature
          if (((mapper.getColumnCount() > 0) && mapper.hasAutoIncrementColumns())) {
//          if (((mapper.getColumnCount() > 0))) {
                current.setID(mapper.createID(conn, current, statement));
            }
        } catch (SQLException sqle) {
            String msg = "SQL Exception writing geometry column" + sqle.getLocalizedMessage();
            LOGGER.log(Level.SEVERE, msg, sqle);
            queryData.close(sqle);
            throw new DataSourceException(msg, sqle);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    String msg = "Error closing JDBC Statement";
                    LOGGER.log(Level.WARNING, msg, e);
                }
            }
        }
    }
    
    
    
//  //Added by Sampas GIS Developer
//  ///////////////////////////////////////////////////////////////////
    private String sqlDateFixer(String sql) {
    	
    	int dateColCount = 0;
    	
        for (int i = 0; i < current.getNumberOfAttributes(); i++) {
        	
        	if (current.getAttribute(i) != null && current.getAttribute(i).getClass().equals(Date.class)) {
        		
        		try {
	        		String searchAttribute = current.getFeatureType().getAttributeType(i).getLocalName();
	        		
	        		int cutPart1 = sql.indexOf(searchAttribute);
	        		
	        		String remainPart1 = sql.substring(0, cutPart1);
	        		
	        		int whichValue = 0;
	        		boolean isString = false;
	        		
	        		for (int j = 0; j < remainPart1.length(); j++) {
						
	        			if (remainPart1.charAt(j) == '\'') {
	        				if (isString == true) isString = false;
	        				else
	        				if (isString == false) isString = true;	        				
	        			}
	        			
	        			if (isString == false &&remainPart1.charAt(j) == ',') {
	        				
	        				whichValue++;
	        			}
					}
	        		
	        		whichValue += dateColCount;
	        		
	        		String valuePart1 = sql.substring(sql.indexOf("VALUES"));
	        		
	        		int cntr = 0;
	        		
	        		String valueStr = "";
	        		
	        		isString = false;
	        		for (int j = 0; j < valuePart1.length(); j++) {
	        			
	        			if (valuePart1.charAt(j) == '\'') {
	        				if (isString == true) isString = false;
	        				else
	        				if (isString == false) isString = true;	        				
	        			}
	        			
	        			if (isString == false && valuePart1.charAt(j) == ',') {
	        				
	        				cntr++;
	        			}
	        			
	       				if (cntr == whichValue) {
	    					
	    					valueStr = valueStr + valuePart1.charAt(j);
	    					
	    				}
					}
	        		
	        		String dateStr = valueStr.replace(",", "").replace("'", "").replace(")", "");
	        		
	        		int firstSeperator = dateStr.indexOf("-");
	        		int lastSeperator = dateStr.lastIndexOf("-");
	        		
	        		if (firstSeperator != -1 && lastSeperator != -1) {
	        			
		        		String yearStr = dateStr.substring(0, firstSeperator);
		        		String monthStr = dateStr.substring(firstSeperator + 1, lastSeperator);
		        		String dayStr = dateStr.substring(lastSeperator + 1);
		        		
		        		String newDateStr = "TO_DATE('" + dayStr + "-" + monthStr + "-" + yearStr + "','dd-mm-yyyy')";
		        		
		        		int sqlDateSeperator = sql.indexOf(dateStr);
		        		
		        		String sqlPart1Str = sql.substring(0, sqlDateSeperator - 1);
		        		
		        		String sqlPart2Str = sql.substring(sqlDateSeperator + dateStr.length() + 1);
		        		
		        		sql = sqlPart1Str + newDateStr + sqlPart2Str;
		        		
		        		dateColCount++;
	        		}
        		
        		} catch (Exception ex) {
        			
        			System.out.println("Error on changing sql for Oracle DATE time ! ERROR : " + ex);
        		}
			}
		}
        		
    	return (sql);
    }
    
//  ///////////////////////////////////////////////////////////////////
    
}
