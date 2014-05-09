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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.geotools.data.DataSourceException;
import org.geotools.data.DataUtilities;
import org.geotools.data.DefaultQuery;
import org.geotools.data.Query;
import org.geotools.data.jdbc.JDBCDataStore;
import org.geotools.data.jdbc.JDBCFeatureStore;
import org.geotools.data.jdbc.JDBCUtils;
import org.geotools.data.jdbc.SQLBuilder;
import org.geotools.data.jdbc.fidmapper.FIDMapper;
import org.geotools.factory.FactoryConfigurationError;
import org.geotools.feature.AttributeType;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.FeatureType;
import org.geotools.feature.GeometryAttributeType;
import org.geotools.feature.IllegalAttributeException;
import org.geotools.feature.SchemaException;
import org.geotools.filter.FidFilter;
import org.geotools.filter.FilterFactory;
import org.geotools.filter.FilterFactoryFinder;
import org.geotools.filter.SQLEncoderException;
import org.geotools.filter.SQLUnpacker;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.filter.Filter;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;

/**
 * Implementation of a Postgis specific FeatureStore.
 * <p>
 * This mostly just rips off code from PostgisDataSource
 * It could definitely use some nice code reuse with PostgisDataStore, as they
 * have a number of similar if not identical methods right now.
 * <p>
 * Approaching deadlines, however, mean that
 * we're sticking with the code that works, instead of getting all kinds of
 * nice reuse.  This'll hopefully change.  This bypasses the writers used in
 * JDBCFeatureStore, as I'm just not yet confident in them.  We  also should
 * do some solid tests to see which is actually faster.
 *
 * @author Chris Holmes, TOPP
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/plugin/postgis/src/main/java/org/geotools/data/postgis/PostgisFeatureStore.java $
 * @version $Id: PostgisFeatureStore.java 30323 2008-05-19 11:03:07Z groldan $
 *
 * @task HACK: too little code reuse with PostgisDataStore.
 * @task TODO: make individual operations truly atomic.  If the transaction is
 *       an auto-commit one, then it should make a a new jdbc transaction that
 *       rollsback if there are errors while performing its action.
 * @task TODO: don't use encoder at all, only access it through
 *       PostgisSQLBuilder
 */
@SuppressWarnings({"unused", "unchecked", "deprecation"})
public class PostgisFeatureStore extends JDBCFeatureStore {
    /** The logger for the postgis module. */
    private static final Logger LOGGER = org.geotools.util.logging.Logging.getLogger("org.geotools.data.postgis");

    /** Well Known Text writer (from JTS). */
    protected static WKTWriter geometryWriter = new WKTWriter();

    /** Factory for producing geometries (from JTS). */
    protected static GeometryFactory geometryFactory = new GeometryFactory();

    /** Well Known Text reader (from JTS). */
    protected static WKTReader geometryReader = new WKTReader(geometryFactory);

    /** Error message prefix for sql connection errors */
    protected static final String CONN_ERROR = "Some sort of database connection error: ";

    /** To create the sql where statement */
    protected PostgisSQLBuilder sqlBuilder;
    protected SQLEncoderPostgis encoder;
    protected String tableName;

    /** the name of the column to use for the featureId */
    protected FIDMapper fidMapper;

    public PostgisFeatureStore(PostgisDataStore postgisDataStore, final FeatureType featureType)
        throws IOException {
        super(postgisDataStore, featureType);
        tableName = featureType.getTypeName();
        fidMapper = postgisDataStore.getFIDMapper(tableName);
        sqlBuilder = (PostgisSQLBuilder) postgisDataStore.getSqlBuilder(tableName);

        AttributeType geomType = featureType.getDefaultGeometry();
        encoder = new SQLEncoderPostgis();
        encoder.setFeatureType( featureType );
        encoder.setFIDMapper(postgisDataStore.getFIDMapper(featureType.getTypeName()));

        if (geomType != null) {
            //HACK: encoder should be set for each geometry.
            int srid = getSRID(geomType.getName());
            encoder.setDefaultGeometry(geomType.getName());
            encoder.setSRID(srid);
            encoder.setFIDMapper(fidMapper);
        }
        /**
         * Override to indicate we support offset, and natural and reverse order sorting
         */
        queryCapabilities = new JDBCQueryCapabilities(featureType){
            //@Override
            public boolean isOffsetSupported(){
                return true;
            }
            
            //@Override
            protected boolean supportsNaturalOrderSorting() {
                return true;
            }

            //@Override
            protected boolean supportsReverseOrderSorting() {
                return true;
            }
        };
    }

//    /**
//     * Returns a feature collection, based on the passed filter.  The schema of
//     * the features passed in must match the schema of the datasource.
//     *
//     * @param reader Add features to the PostGIS database.
//     *
//     * @return A set of featureIds of the features added.
//     *
//     * @throws IOException if anything went wrong.
//     * @throws DataSourceException DOCUMENT ME!
//     *
//     * @task REVISIT: Check to make sure features passed in match schema.
//     * @task TODO: get working with the primary key fid column.  This will
//     *       currently just insert nulls for the fids if oid is not being used
//     *       as the column.  We probably need a sequence to generate the fids.
//     *       Or if the fid is supposed to be part of the insert (which doesn't
//     *       make sense if we return fids), then we should check for
//     *       uniqueness.
//     * @task REVISIT: not sure about previousAutoCommit stuff.  We want to make
//     *       sure that each of these actions is atomic if we're not working
//     *       against a Transaction.
//     */
//    public Set addFeatures(FeatureReader reader) throws IOException {
//        boolean fail = false;
//        Set curFids = null;
//        Set newFids = null;
//
//        //Feature[] featureArr = collection.getFeatures();
//        Connection conn = null;
//        Statement statement = null;
//
//        if (reader.hasNext()) {
//            try {
//                conn = getConnection();
//
//                curFids = getFidSet(conn);
//                LOGGER.fine("fids before add: " + curFids);
//                statement = conn.createStatement();
//
//                while (reader.hasNext()) {
//                    String sql = makeInsertSql(tableName, reader.next());
//                    LOGGER.finer("this sql statement = " + sql);
//                    statement.executeUpdate(sql);
//                }
//
//                newFids = getFidSet(conn);
//                LOGGER.fine("fids after add: " + newFids);
//                newFids.removeAll(curFids);
//                LOGGER.fine("to return " + newFids);
//            } catch (SQLException sqle) {
//                fail = true;
//                close(conn, getTransaction(), sqle);
//
//                String message = CONN_ERROR + sqle.getMessage();
//                LOGGER.warning(message);
//                throw new DataSourceException(message, sqle);
//            } catch (IllegalAttributeException iae) {
//                throw new DataSourceException("attribute problem", iae);
//            } finally {
//                reader.close();
//                close(statement);
//                close(conn, getTransaction(), null);
//
//                //finalizeTransactionMethod(previousAutoCommit, fail);
//            }
//        }
//
//        //Set retFids = new HashSet(newFids.size());
//        //for (Iterator i = newFids.iterator(); i.hasNext;){
//        return newFids;
//    }

//    /**
//     * Gets the set of fids for all features in this datasource .  Used by
//     * insert to  figure out which features it added.  There should be a more
//     * efficient way of doing this, I'm just not sure what.
//     *
//     * @param conn The connection to get the fid set with.
//     *
//     * @return a set of strings of the featureIds
//     *
//     * @throws IOException if there were problems connecting to the db backend.
//     * @throws DataSourceException DOCUMENT ME!
//     */
//    private Set getFidSet(Connection conn) throws IOException {
//        Set fids = new HashSet();
//        Statement statement = null;
//
//        try {
//            LOGGER.finer("entering fid set");
//
//            //conn = getConnection();
//            statement = conn.createStatement();
//
//            DefaultQuery query = new DefaultQuery();
//            query.setPropertyNames(new String[0]);
//
//            SQLUnpacker unpacker = new SQLUnpacker(encoder.getCapabilities());
//
//            //REVISIT: redo unpacker-this has to be called first, or it breaks.
//            unpacker.unPackAND(null);
//
//            String sql = makeSql(unpacker, (Query) query);
//            ResultSet result = statement.executeQuery(sql);
//
//            while (result.next()) {
//                //REVISIT: this formatting could be done after the remove,
//                //would speed things up, but also would make that code ugly.
//                fids.add(createFid(result.getString(1)));
//            }
//        } catch (SQLException sqle) {
//            String message = CONN_ERROR + sqle.getMessage();
//            LOGGER.warning(message);
//            close(conn, getTransaction(), sqle);
//            throw new DataSourceException(message, sqle);
//        } finally {
//            close(statement);
//            close(conn, getTransaction(), null);
//        }
//
//        LOGGER.finest("returning fids " + fids);
//
//        return fids;
//    }

//    /**
//     * Creates a sql insert statement.  Uses each feature's schema, which makes
//     * it possible to insert out of order, as well as inserting less than all
//     * features.
//     *
//     * @param tableName the name of the feature table being inserted into.
//     * @param feature the feature to add.
//     *
//     * @return an insert sql statement.
//     *
//     * @throws IOException DOCUMENT ME!
//     */
//    private String makeInsertSql(String tableName, Feature feature) throws IOException {
//        String attrValue;
//        StringBuffer sql = new StringBuffer();
//
//        sql.append("INSERT INTO \"");
//        sql.append(tableName);
//        sql.append("\"(");
//
//        FeatureType featureSchema = feature.getFeatureType();
//        AttributeType[] types = featureSchema.getAttributeTypes();
//
//        for (int i = 0; i < types.length; i++) {
//            sql.append("\"");
//            sql.append(types[i].getName());
//            sql.append("\"");
//            sql.append((i < (types.length - 1)) ? ", " : ") ");
//        }
//
//        sql.append("VALUES (");
//
//        Object[] attributes = feature.getAttributes(null);
//
//        for (int j = 0; j < attributes.length; j++) {
//            if (attributes[j] == null) {
//                sql.append("null");
//            } else if (types[j].isGeometry()) {
//                int srid = getSRID(types[j].getName());
//                String geoText = geometryWriter.write((Geometry) attributes[j]);
//                sql.append("GeometryFromText('");
//                sql.append(geoText);
//                sql.append("', ");
//                sql.append(srid);
//                sql.append(")");
//            } else {
//                attrValue = addQuotes(attributes[j]);
//                sql.append(attrValue);
//            }
//
//            if (j < (attributes.length - 1)) {
//                sql.append(", ");
//            }
//
//        }
//
//        sql.append(");");
//
//        LOGGER.fine("insert statement is " + sql);
//        return sql.toString();
//    }

    protected int getSRID(String geomName) throws IOException {
        return getPostgisDataStore().getSRID(tableName, geomName);
    }

    /**
     * Adds quotes to an object for storage in postgis.  The object should be a
     * string or a number.  To perform an insert strings need quotes around
     * them, and numbers work fine with quotes, so this method can be called
     * on unknown objects.
     *
     * @param value The object to add quotes to.
     *
     * @return a string representation of the object with quotes.
     */
    private String addQuotes(Object value) {
        String retString;

        if (value != null) {
            retString = "'" + value.toString() + "'";
        } else {
            retString = "null";
        }

        return retString;
    }

    /**
     * Removes the features specified by the passed filter from the PostGIS
     * database.
     *
     * @param filter An OpenGIS filter; specifies which features to remove.
     *
     * @throws IOException If anything goes wrong or if deleting is not
     *         supported.
     * @throws DataSourceException DOCUMENT ME!
     */
    public void removeFeatures(Filter filter) throws IOException {
        String sql = "";
        String whereStmt = null;

        // check locks!
        // (won't do anything if we use our own
        // database locking)
        assertFilter(filter);

        //boolean previousAutoCommit = getAutoCommit();
        //setAutoCommit(false);
        Filter encodableFilter = sqlBuilder.getPreQueryFilter(filter);
        Filter unEncodableFilter = sqlBuilder.getPostQueryFilter(filter);

//        SQLUnpacker unpacker = new SQLUnpacker(encoder.getCapabilities());
//        unpacker.unPackOR(filter);

        PreparedStatement statement = null;
        Connection conn = null;

        try {
            conn = getConnection();

            if (encodableFilter == null && unEncodableFilter != null) {
                encodableFilter = getEncodableFilter(unEncodableFilter);
            }

            if (encodableFilter != null) {
                whereStmt = encoder.encode(encodableFilter);
                sql = "DELETE from " + sqlBuilder.encodeTableName(tableName) + whereStmt + ";";

                //do actual delete
                LOGGER.fine("sql statment is " + sql);
                DefaultQuery query=new DefaultQuery(getSchema().getTypeName(), filter);
                Envelope bounds=bounds(query);
                statement = conn.prepareStatement(sql);
                statement.executeUpdate();
                
                if( bounds!=null && !bounds.isNull())
	    			getJDBCDataStore().listenerManager.fireFeaturesRemoved(getSchema().getTypeName(),
	    					getTransaction(), bounds, false);

            }

            close(statement);
        } catch (SQLException sqle) {
            close(conn, getTransaction(), sqle);

            String message = CONN_ERROR + sqle.getMessage();
            LOGGER.warning(message);
            throw new DataSourceException(message, sqle);
        } catch (SQLEncoderException ence) {
            String message = "error encoding sql from filter " + ence.getMessage();
            LOGGER.warning(message);

            throw new DataSourceException(message, ence);
        } catch (IllegalAttributeException iae) {
            throw new DataSourceException("attribute problem", iae);
        } finally {
            close(statement);
            close(conn, getTransaction(), null);
        }
    }

    /**
     * Modifies the passed attribute types with the passed objects in all
     * features that correspond to the passed OGS filter.
     *
     * @param type The attributes to modify.
     * @param value The values to put in the attribute types.
     * @param filter An OGC filter to note which attributes to modify.
     *
     * @throws IOException If modificaton is not supported, if the attribute
     *         and object arrays are not eqaul length, or if the object types
     *         do not match the attribute types.
     * @throws DataSourceException DOCUMENT ME!
     *
     * @task REVISIT: validate values with types.  Database does this a bit
     *       now, but should be more fully implemented.
     * @task REVISIT: do some nice prepared statement stuff like oracle.
     */
    public void modifyFeatures(AttributeType[] type, Object[] value, Filter filter)
        throws IOException {
        // check locks!
        // (won't do anything if we use our own
        // database locking)
        LOGGER.finer("asserting filter " + filter);
        assertFilter(filter);

        //boolean previousAutoCommit = getAutoCommit();
        //setAutoCommit(false);
        boolean fail = false;
        Connection conn = null;
        PreparedStatement statement = null;
        String fid = null;

        //check schema with filter???
//        SQLUnpacker unpacker = new SQLUnpacker(encoder.getCapabilities());
//        unpacker.unPackOR(filter);

        String whereStmt = null;
        Filter encodableFilter = sqlBuilder.getPreQueryFilter(filter);
        Filter unEncodableFilter = sqlBuilder.getPostQueryFilter(filter);

        try {
            conn = getConnection();

            if (encodableFilter == null && unEncodableFilter != null) {
                FidFilter fidFilter = getEncodableFilter(unEncodableFilter);
                encodableFilter = fidFilter;
            }

            if (encodableFilter != null) {
                whereStmt = encoder.encode(encodableFilter);
                final String sql = makeModifySqlStatement(type, value, whereStmt);
                statement = conn.prepareStatement(sql);
                LOGGER.finer("encoded modify is " + sql);
                setModifyPreparedStatementValues(statement, type, value);
                
                DefaultQuery query=new DefaultQuery(getSchema().getTypeName(), filter);
                Envelope bounds=bounds(query);
                statement.executeUpdate();
                if (bounds!=null && !bounds.isNull()) {
                    Envelope afterBounds = bounds(query);
                    if(afterBounds != null)
                        bounds.expandToInclude(afterBounds);
                } else {
                    bounds=bounds(query);
                }
                if (bounds!=null && !bounds.isNull())
	    			getJDBCDataStore().listenerManager.fireFeaturesChanged(getSchema().getTypeName(),
	    					getTransaction(), bounds, false);
            }

        } catch (SQLException sqle) {
            fail = true;
            close(conn, getTransaction(), sqle);

            String message = CONN_ERROR + sqle.getMessage();
            LOGGER.warning(message);
            throw new DataSourceException(message, sqle);
        } catch (SQLEncoderException ence) {
            fail = true;

            String message = "error encoding sql from filter " + ence.getMessage();
            LOGGER.warning(message);
            throw new DataSourceException(message, ence);
        } catch (IllegalAttributeException iae) {
            throw new DataSourceException("attribute problem", iae);
        } finally {
            close(statement);
            close(conn, getTransaction(), null);
        }
    }

	private FidFilter getEncodableFilter(Filter unEncodableFilter)
        throws IOException, FactoryConfigurationError, IllegalAttributeException {
        // this is very similar to getFidSet - the reason is so that we
        // don't spend time constructing geometries when we don't need
        // to, but we probably could get some better code reuse.
        DefaultQuery query = new DefaultQuery();
        query.setPropertyNames(new String[0]);
        query.setFilter(unEncodableFilter);

        FeatureCollection features = getFeatures(unEncodableFilter);

        FilterFactory ff = FilterFactoryFinder.createFilterFactory();
        FidFilter fidFilter = ff.createFidFilter();
        FeatureIterator it = features.features();
        try {
            while( it.hasNext() ) {
                Feature feature = it.next();
                fidFilter.addFid(feature.getID());
            }
        } finally {
          features.close( it );  
        }
        return fidFilter;
    }

    /**
     * strips the tableName from the fid for those in the format
     * featureName.3534 should maybe just strip out all alpha-numeric
     * characters.
     *
     * @param feature The feature for which the fid number should be stripped.
     *
     * @return The fid without the leading tablename.
     */
    private String formatFid(Feature feature) {
        String fid = feature.getID();

        if (fid.startsWith(tableName)) {
            //take out the tableName and the .
            fid = fid.substring(tableName.length() + 1);
        }

        return addQuotes(fid);
    }

    /**
     * Modifies the passed attribute types with the passed objects in all
     * features that correspond to the passed OGS filter.  A convenience
     * method for single attribute modifications.
     *
     * @param type The attributes to modify.
     * @param value The values to put in the attribute types.
     * @param filter An OGC filter to note which attributes to modify.
     *
     * @throws IOException If modificaton is not supported, if the object type
     *         do not match the attribute type.
     */
    public void modifyFeatures(AttributeType type, Object value, Filter filter)
        throws IOException {
        AttributeType[] singleType = { type };
        Object[] singleVal = { value };
        modifyFeatures(singleType, singleVal, filter);
    }

    /**
     * Builds an UPDATE sql statement suitable to be used to create a
     * PreparedStatement.
     * <p>
     * The form of the returned statement is like
     * <code>UPDATE tableName SET att = ?, att2 = ?</code>. Note the use of
     * the
     * <code>?<code> value placeholder. The number of <code>?<code> value place holders
     * in the returned statement will be equal to the number of <code>types</code> and <code>values</code>
     * passed as arguments.
     * </p>
     * 
     * @param types
     *            the attributes to be changed.
     * @param values
     *            the values to change them to, forming a one to one mapping with <code>types</code>.
     * @param whereStmt
     *            the feature to update.
     * 
     * @return an update sql statement to be used in a PreparedStatement.
     * 
     * @throws IOException
     *             if the lengths of types and values don't match.
     * @see #setModifyPreparedStatementValues
     */
    private String makeModifySqlStatement(AttributeType[] types, Object[] values, String whereStmt)
            throws IOException {
        final int arrLength = types.length;
        if (arrLength != values.length) {
            throw new IllegalArgumentException("length of value array is not " + "same length as type array");
        }
        StringBuffer sqlStatement = new StringBuffer("UPDATE ");
        sqlStatement.append(sqlBuilder.encodeTableName(tableName) + " SET ");

        for (int i = 0; i < arrLength; i++) {
            AttributeType curType = types[i];
            sqlStatement.append(sqlBuilder.encodeColumnName(curType.getLocalName()));
            sqlStatement.append(" = ");

            if (curType instanceof GeometryAttributeType) {
                //create a placeholder where to set the geometry as WKT
                int srid = getSRID(curType.getLocalName());
                sqlStatement.append("GeometryFromText( ?, ").append(srid).append(")");
            } else {
                sqlStatement.append("?");
            }

            sqlStatement.append((i < (arrLength - 1)) ? ", " : " ");
        }

        sqlStatement.append(whereStmt);
        sqlStatement.append(";");

        return sqlStatement.toString();        
    }

    /**
     * Sets the values corresponding to the PreparedStatement placeholders.
     * <p>
     * The number of <code>"?"</code> placeholders in the statement, the number of attributes
     * in <code>types</code> and the numbers of <code>values</code> shall be the same.
     * </p>
     * <p>
     * This method is intended to be used after creating a prepared statement through
     * {@link #makeModifySqlStatement} in order to set the statement values.
     * </p>
     * 
     * @param types
     *            the attribute to be changed.
     * @param values
     *            the value to change it to.
     * @param whereStmt
     *            the feature to update.
     * 
     * @return an update sql statement.
     * 
     * @throws IOException
     *             if the lengths of types and values don't match.
     * @throws SQLException 
     */
    private void setModifyPreparedStatementValues(PreparedStatement statement,
            AttributeType[] types, Object[] values) throws IOException, SQLException {
        final int arrLength = types.length;
        if (arrLength != values.length) {
            throw new IllegalArgumentException("length of value array is not "
                    + "same length as type array");
        }

        final PostgisDataStore dataStore = getPostgisDataStore();
        for (int i = 0; i < arrLength; i++) {
            Object newValue = values[i];
            AttributeType curType = types[i];
            if (curType instanceof GeometryAttributeType) {
                newValue = geometryWriter.write((Geometry) newValue);
                // prepStatement indexing starts at 1...
                statement.setObject(1 + i, newValue);
            }else{
                //Get the jdbc column type
                final Class target = curType.getBinding();
                final int jdbcType = dataStore.getJdbcType(target).intValue();
                // prepStatement indexing starts at 1...
                statement.setObject(1 + i, newValue, jdbcType);
            }
        }
    }

    /**
     * Performs the setFeautres operation by removing all and then adding the
     * full collection.  This is not efficient, the add, modify and  remove
     * operations should be used instead, this is just to follow the
     * interface.
     *
     * @return DOCUMENT ME!
     *
     * @task REVISIT: to abstract class, same as oracle.
     */

    /*public void setFeatures(FeatureCollection features)
       throws IOException {
       boolean originalAutoCommit = getAutoCommit();
       setAutoCommit(false);
       removeFeatures(null);
       addFeatures(features);
       //commit();
       //setAutoCommit(originalAutoCommit);
       }*/
    protected PostgisDataStore getPostgisDataStore() {
        return (PostgisDataStore) super.getJDBCDataStore();
    }

    /**
     * Creates a SQL statement for the PostGIS database.
     * 
     * @deprecated please use makeSql(query) 
     * @param unpacker the object to get the encodable filter.
     * @param query the getFeature query - for the tableName, properties and
     *        maxFeatures.
     * @throws IOException if there are problems encoding the sql.
     */
    public String makeSql(SQLUnpacker unpacker, Query query) throws IOException {
        //one to one relationship for now, so typeName is not used.
        //String tableName = query.getTypeName();

        boolean useLimit = (unpacker.getUnSupported() == null);
        Filter filter = unpacker.getSupported();
        LOGGER.fine("Filter in making sql is " + filter);

        AttributeType[] attributeTypes = getAttTypes(query);
        int numAttributes = attributeTypes.length;

        StringBuffer sqlStatement = new StringBuffer("SELECT ");
        if (!fidMapper.returnFIDColumnsAsAttributes()) {
            for (int i = 0; i < fidMapper.getColumnCount(); i++) {
                sqlStatement.append(fidMapper.getColumnName(i));
                if (numAttributes > 0 || i < (fidMapper.getColumnCount() - 1))
                    sqlStatement.append(", ");
            }
        }

        LOGGER.finer("making sql for " + numAttributes + " attributes");

        for (int i = 0; i < numAttributes; i++) {
            String curAttName = attributeTypes[i].getName();

            if (Geometry.class.isAssignableFrom(attributeTypes[i].getType())) {
                sqlStatement.append(", AsText(force_2d(\"" + curAttName + "\"))");
            } else {
                sqlStatement.append(", \"" + curAttName + "\"");
            }
        }

        String where = "";

        if (filter != null) {
            try {
                where = encoder.encode(filter);
            } catch (SQLEncoderException sqle) {
                String message = "Encoder error" + sqle.getMessage();
                LOGGER.warning(message);
                throw new DataSourceException(message, sqle);
            }
        }

        //int limit = HARD_MAX_FEATURES;
        String limit = "";

        if (useLimit) {
            limit = " LIMIT " + query.getMaxFeatures();
        }

        sqlStatement.append(" FROM \"" + tableName + "\" " + where + limit + ";").toString();
        LOGGER.fine("sql statement is " + sqlStatement);

        return sqlStatement.toString();
    }
    
    /**
     * Creates a SQL statement for the PostGIS database.
     *
     * @return Full SQL statement.
     * @throws IOException if there are problems encoding the sql.
     * 
     * @task REVISIT: faithfully use sqlBuilder
     */
    public String makeSql(Query query) throws IOException {
        //one to one relationship for now, so typeName is not used.
        //String tableName = query.getTypeName();

        Filter encodableFilter = sqlBuilder.getPreQueryFilter(query.getFilter());
        Filter unEncodableFilter = sqlBuilder.getPostQueryFilter(query.getFilter());
        boolean useLimit = (unEncodableFilter == null || unEncodableFilter.equals(Filter.INCLUDE));

        LOGGER.fine("Filter in making sql is " + encodableFilter);

        AttributeType[] attributeTypes = getAttTypes(query);
        int numAttributes = attributeTypes.length;

        StringBuffer sqlStatement = new StringBuffer("SELECT ");
        if (!fidMapper.returnFIDColumnsAsAttributes()) {
            for (int i = 0; i < fidMapper.getColumnCount(); i++) {
                sqlStatement.append(fidMapper.getColumnName(i));
                if (numAttributes > 0 || i < (fidMapper.getColumnCount() - 1))
                    sqlStatement.append(", ");
            }
        }

        LOGGER.finer("making sql for " + numAttributes + " attributes");

        for (int i = 0; i < numAttributes; i++) {
            String curAttName = attributeTypes[i].getName();

            if (Geometry.class.isAssignableFrom(attributeTypes[i].getType())) {
                sqlStatement.append(", AsText(force_2d(\"" + curAttName + "\"))");
            } else {
                sqlStatement.append(", \"" + curAttName + "\"");
            }
        }

        String where = "";

        if (encodableFilter != null) {
            try {
                where = encoder.encode(encodableFilter);
            } catch (SQLEncoderException sqle) {
                String message = "Encoder error" + sqle.getMessage();
                LOGGER.warning(message);
                throw new DataSourceException(message, sqle);
            }
        }

        //int limit = HARD_MAX_FEATURES;
        String limit = "";

        if (useLimit) {
            limit = " LIMIT " + query.getMaxFeatures();
        }

        sqlBuilder.sqlFrom(sqlStatement, tableName);
        sqlStatement.append(where + limit + ";");
        LOGGER.fine("sql statement is " + sqlStatement);

        return sqlStatement.toString();
    }

//    /**
//     * Prepends the tablename (featureType) on to featureIds that start with
//     * digits.
//     *
//     * @param featureId A featureId string to be prepended with tablename if
//     *        needed.
//     *
//     * @return the prepended feautre Id.
//     */
//    protected String createFid(String featureId) {
//        String newFid;
//
//        if (Character.isDigit(featureId.charAt(0))) {
//            //so prepend the table name.
//            newFid = tableName + "." + featureId;
//        } else {
//            newFid = featureId;
//        }
//
//        return newFid;
//    }

    /**
     * Gets the attribute types from the query.  If all are requested then
     * returns all attribute types of this query.  If only certain
     * propertyNames are requested then this returns the correct attribute
     * types, throwing an exception is they can not be found.
     *
     * @param query contains the propertyNames.
     *
     * @return the array of attribute types to be returned by getFeature.
     *
     * @throws IOException if query contains a propertyName that is not a part
     *         of this type's schema.
     */
    private AttributeType[] getAttTypes(Query query) throws IOException {
        AttributeType[] schemaTypes = getSchema().getAttributeTypes();

        if (query.retrieveAllProperties()) {
            return schemaTypes;
        } else {
            List attNames = Arrays.asList(query.getPropertyNames());
            AttributeType[] retAttTypes = new AttributeType[attNames.size()];
            int retPos = 0;

            for (int i = 0, n = schemaTypes.length; i < n; i++) {
                String schemaTypeName = schemaTypes[i].getName();

                if (attNames.contains(schemaTypeName)) {
                    retAttTypes[retPos++] = schemaTypes[i];
                }
            }

            //TODO: better error reporting, and completely test this method.
            if (attNames.size() != retPos) {
                String msg =
                    "attempted to request a property, "
                        + attNames.get(0)
                        + " that is not part of the schema ";
                throw new IOException(msg);
            }

            return retAttTypes;
        }
    }

    public Envelope getBounds() throws IOException {
        return getBounds(Query.ALL);
    }

    /**
     * Retrieve Bounds of Query results.
     * 
     * <p>
     * Currently returns null, consider getFeatures( query ).getBounds()
     * instead.
     * </p>
     * 
     * <p>
     * Subclasses may override this method to perform the appropriate
     * optimization for this result.
     * </p>
     *
     * @param query Query we are requesting the bounds of
     *
     * @return null representing the lack of an optimization
     *
     * @throws IOException DOCUMENT ME!
     */
    public Envelope getBounds(Query query) throws IOException {
        return bounds(query);
    }

    // TODO: change this so that it queries for just the bbox instead of the entire sub-query schema columns!
    //        (this is harder than you might think because of filter requirements!)
    //
    protected ReferencedEnvelope bounds(Query query) throws IOException {
        Filter filter = query.getFilter();

        if (filter == Filter.EXCLUDE) {
            return new ReferencedEnvelope(new Envelope(), query.getCoordinateSystem());
        }

        FeatureType schema = getSchema();
        JDBCDataStore jdbc = (JDBCDataStore)getJDBCDataStore();
        SQLBuilder sqlBuilder = jdbc.getSqlBuilder(schema.getTypeName());

        Filter postQueryFilter = sqlBuilder.getPostQueryFilter(query.getFilter());
        if (postQueryFilter != null && !postQueryFilter.equals(Filter.INCLUDE)) {
            // this would require postprocessing the filter
            // so we cannot optimize
            return null;
        }

        Connection conn = null;

        try {
            conn = getConnection();

            Envelope retEnv = new Envelope();
            Filter preFilter = sqlBuilder.getPreQueryFilter(query.getFilter());
            AttributeType[] attributeTypes = schema.getAttributeTypes();
            FeatureType schemaNew = schema;
            	//DJB: this should ensure that schema has a geometry in it or the bounds query has no chance of working
			if(!query.retrieveAllProperties()) {
				try {
                    schemaNew = DataUtilities.createSubType(schema, query.getPropertyNames());
                    if (schemaNew.getDefaultGeometry() == null)  // does the sub-schema have a geometry in it?
                    {
                    	//uh-oh better get one!
                    	if (schema.getDefaultGeometry() != null)  // does the entire schema have a geometry in it? 
                    	{
                    		//buff-up the sub-schema so it has the default geometry in it.
	                    	ArrayList al = new ArrayList (Arrays.asList(query.getPropertyNames()));
	                    	al.add(schema.getDefaultGeometry().getName());
	                    	schemaNew = DataUtilities.createSubType(schema, (String[]) al.toArray(new String[1]) );       
                    	}
                    }
                } catch (SchemaException e1) {
                    throw new DataSourceException("Could not create subtype", e1);
                }
			}
			 // at this point, the query should have a geometry in it. 
			 // BUT, if there's no geometry in the table, then the query will not (obviously) have a geometry in it.
			 
			 attributeTypes = schemaNew.getAttributeTypes();
				 
            for (int j = 0, n = schemaNew.getAttributeCount(); j < n; j++) {
                if (Geometry.class.isAssignableFrom(attributeTypes[j].getType())) // same as .isgeometry() - see new featuretype javadoc
                {
                    String attName = attributeTypes[j].getName();
                    Envelope curEnv = getEnvelope(conn, attName, sqlBuilder, filter);

                    if (curEnv == null) {
                        return null;
                    }

                    retEnv.expandToInclude(curEnv);
                }
            }

            LOGGER.finer("returning bounds " + retEnv);
            
            // handle reprojection and crs forcing
            CoordinateReferenceSystem base = null;
            if(query.getCoordinateSystem() != null)
                base = query.getCoordinateSystem();
            else if(schemaNew.getDefaultGeometry() != null)
                base = schemaNew.getDefaultGeometry().getCoordinateSystem();
            CoordinateReferenceSystem dest = query.getCoordinateSystemReproject();
            
            ReferencedEnvelope result = new ReferencedEnvelope(retEnv, base);
            if(base != null && dest != null)
                result = result.transform(dest, true);
            return result;
        } catch (SQLException sqlException) {
            JDBCUtils.close(conn, transaction, sqlException);
            conn = null;
            throw new DataSourceException("Could not count " + query.getHandle(), sqlException);
        } catch (SQLEncoderException e) {
            // could not encode count
            // but at least we did not break the connection
            return null;
        } catch (ParseException parseE) {
            String message = "Could not read geometry: " + parseE.getMessage();

            return null;
        } catch (FactoryException e) {
            throw new DataSourceException("Could not reproject", e);
        }  catch (TransformException e) {
            throw new DataSourceException("Could not reproject", e);
        } finally {
            JDBCUtils.close(conn, transaction, null);
        }
    }

    //REVISIT: do we want maxFeatures here too?  If we don't have maxFeatures then the answer
    //is still always going to be right (and guaranteed to be right, as opposed to two selects
    // that could be slightly different).  And the performance hit shouldn't be all that much.
    protected Envelope getEnvelope(
        Connection conn,
        String geomName,
        SQLBuilder sqlBuilder,
        Filter filter)
        throws SQLException, SQLEncoderException, IOException, ParseException {
        String typeName = getSchema().getTypeName();
        
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT AsText(force_2d(Envelope(" );
        
        //check if we can apply the estimated_extent optimization
        boolean useEstimatedExtent = ( filter == null || filter == Filter.INCLUDE ) 
        	&& ((PostgisDataStore)getDataStore()).isEstimatedExtent();
        
        if ( useEstimatedExtent ) {
        	sql.append("estimated_extent(");	
        	sql.append("'" + typeName + "','" + geomName + "'))));");
        }
        else {
        	sql.append("Extent(\"" + geomName + "\")))) " );
        	sqlBuilder.sqlFrom(sql, typeName);
            sqlBuilder.sqlWhere(sql, filter);
        }
        
      
        LOGGER.fine("SQL: " + sql);

        PreparedStatement statement = conn.prepareStatement(sql.toString());
        ResultSet results = statement.executeQuery();
        results.next();

        String wkt = results.getString(1);
        Envelope retEnv = null;

        if (wkt == null) {
            return null;
        } else {
            retEnv = geometryReader.read(wkt).getEnvelopeInternal();
        }

        results.close();
        statement.close();

        return retEnv;
    }	
    
    
}
