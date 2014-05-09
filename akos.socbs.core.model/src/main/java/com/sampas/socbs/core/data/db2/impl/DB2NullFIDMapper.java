/*
 *    GeoTools - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) Copyright IBM Corporation, 2005. All rights reserved.
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

import org.geotools.data.jdbc.fidmapper.NullFIDMapper;
import java.util.logging.Logger;

/**
 * Overrides NullFIDMapper methods for DB2-specific handling.
 *
 * @author David Adler - IBM Corporation
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/unsupported/db2/src/main/java/org/geotools/data/db2/DB2NullFIDMapper.java $
 */
@SuppressWarnings({"unused", "serial" })
public class DB2NullFIDMapper extends NullFIDMapper {
	
    private static final Logger LOGGER = org.geotools.util.logging.Logging.getLogger("org.geotools.data.db2");
    
    private int currentFID = 1;

    /**
     * Default constructor.
     */
    public DB2NullFIDMapper() {
        super();
    }
    /** 
     * Constructor to set schema and table name for Null mapper.
     * 
     * @param tableSchemaName
     * @param tableName
     */
    public DB2NullFIDMapper(String tableSchemaName, String tableName) {
    	super(tableSchemaName, tableName);
    }
    /**
     * @see org.geotools.data.jdbc.fidmapper.FIDMapper#getID(java.lang.Object[])
     */
    public String getID(Object[] attributes) {
        currentFID++;

        return String.valueOf(currentFID);
    }
}
