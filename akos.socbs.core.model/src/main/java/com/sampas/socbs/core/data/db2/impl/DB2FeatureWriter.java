/*
 *    GeoTools - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) Copyright IBM Corporation, 2005-2006. All rights reserved.
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
 *
 */
package com.sampas.socbs.core.data.db2.impl;

import com.vividsolutions.jts.geom.Geometry;

import org.geotools.data.FeatureReader;
import org.geotools.data.jdbc.FeatureTypeInfo;
import org.geotools.data.jdbc.JDBCTextFeatureWriter;
import org.geotools.data.jdbc.QueryData;
import org.geotools.feature.AttributeType;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureType;

import java.io.IOException;

import java.util.logging.Logger;


/**
 * DOCUMENT ME!
 *
 * @author David Adler - IBM Corporation
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/unsupported/db2/src/main/java/org/geotools/data/db2/DB2FeatureWriter.java $
 */
@SuppressWarnings("unused")
public class DB2FeatureWriter extends JDBCTextFeatureWriter {
	
	private DB2SQLBuilder sqlBuilder;

	private static final Logger LOGGER = org.geotools.util.logging.Logging.getLogger("org.geotools.data.db2");

	/**
	 * DOCUMENT ME!
	 * 
	 * @param reader
	 * @param queryData
	 * @param sqlBuilder
	 *            DOCUMENT ME!
	 * 
	 * @throws IOException
	 */
	public DB2FeatureWriter(FeatureReader reader, QueryData queryData,
			DB2SQLBuilder sqlBuilder) throws IOException {
		super(reader, queryData);
		this.sqlBuilder = sqlBuilder;
	}


	/**
	 * Generates the SQL delete statement
	 * 
	 * @param feature
	 * 
	 * @return DB2 DELETE statement
	 * 
	 * @throws IOException
	 * @throws UnsupportedOperationException
	 */
	protected String makeDeleteSql(Feature feature) throws IOException {
		String deleteSQL  = this.sqlBuilder.makeDeleteSql(feature);
		return (deleteSQL);
	}

	/**
     * Generates the SQL UPDATE statement.  
     *
     * @param feature the feature to insert.
     *
     * @return DB2 INSERT statement
     *
     * @throws IOException
     */
    protected String makeInsertSql(Feature feature) throws IOException {
        FeatureTypeInfo ftInfo = queryData.getFeatureTypeInfo();
        FeatureType featureType = ftInfo.getSchema();
        AttributeType[] attributes = featureType.getAttributeTypes();
		String insertSQL  = this.sqlBuilder.makeInsertSql(attributes, feature);
        return (insertSQL);
    }
	/**
	 * Generates the SQL UPDATE statement
	 * 
	 * @param feature
	 * 
	 * @return DB2 UPDATE statement
	 * 
	 * @throws IOException
	 * @throws UnsupportedOperationException
	 */
	protected String makeUpdateSql(Feature live, Feature current)
			throws IOException {
		FeatureTypeInfo ftInfo = queryData.getFeatureTypeInfo();
		FeatureType featureType = ftInfo.getSchema();
		AttributeType[] attributes = featureType.getAttributeTypes();
		
		String updateSQL  = this.sqlBuilder.makeUpdateSql(attributes, live, current);

		return (updateSQL);
	}


	/* (non-Javadoc)
	 * This isn't actually used but must be provided because it is defined as abstract in the
	 * parent hierarchy.
	 * @see org.geotools.data.jdbc.JDBCTextFeatureWriter#getGeometryInsertText(com.vividsolutions.jts.geom.Geometry, int)
	 */
	protected String getGeometryInsertText(Geometry geom, int srid) throws IOException {
		
		return null;
	}

}
