/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002, Geotools Project Managment Committee (PMC)
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
package com.sampas.socbs.core.data.arcsde.impl;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.FromItemVisitor;
import net.sf.jsqlparser.statement.select.SubSelect;
import com.esri.sde.sdk.client.SeConnection;

/**
 * Fully qualifies a table names.
 * 
 * <p>
 * {@link net.sf.jsqlparser.schema.Table} has provitions only to store schema
 * and table names, in the traditional sense. ArcSDE uses fully qualified names
 * formed by "databaseName"."userName"."tableName". Though "databaseName" is
 * optional in some ArcSDE systems (sql server, for example), it is required in
 * Oracle. Schema and table stands for user and table in sde land. So this
 * visitor will create new Tables where schema if formed by SDE's
 * "databaseName"."userName"
 * </p>
 * 
 * @author Gabriel Roldan, Axios Engineering
 * @version $Id: FromItemQualifier.java 29625 2008-03-14 13:55:02Z groldan $
 * 
 * @source $URL:
 *         http://svn.geotools.org/geotools/trunk/gt/modules/plugin/arcsde/datastore/src/main/java/org/geotools/arcsde/data/view/FromItemQualifier.java $
 * @since 2.3.x
 */
class FromItemQualifier implements FromItemVisitor {
    /** DOCUMENT ME! */
    private SeConnection conn;

    /** DOCUMENT ME! */
    private FromItem qualifiedFromItem;

    /**
     * Creates a new FromItemQualifier object.
     * 
     * @param conn
     *            DOCUMENT ME!
     * 
     * @throws IllegalStateException
     *             DOCUMENT ME!
     */
    private FromItemQualifier(SeConnection conn) throws IllegalStateException {
        this.conn = conn;
    }

    /**
     * DOCUMENT ME!
     * 
     * @param conn
     *            DOCUMENT ME!
     * @param fromItem
     *            DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public static FromItem qualify(SeConnection conn, FromItem fromItem) {
        if (fromItem == null) {
            return null;
        }

        FromItemQualifier qualifier = new FromItemQualifier(conn);
        fromItem.accept(qualifier);

        return qualifier.qualifiedFromItem;
    }

    /**
     * DOCUMENT ME!
     * 
     * @param tableName
     *            DOCUMENT ME!
     */
    public void visit(Table tableName) {
        qualifiedFromItem = TableQualifier.qualify(conn, tableName);
    }

    /**
     * DOCUMENT ME!
     * 
     * @param subSelect
     *            DOCUMENT ME!
     */
    public void visit(SubSelect subSelect) {
        this.qualifiedFromItem = SubSelectQualifier.qualify(conn, subSelect);
    }
}
