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

import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SubSelect;
import com.esri.sde.sdk.client.SeConnection;

/**
 * Qualifies a column reference in a subselect clause.
 * 
 * @author Gabriel Roldan, Axios Engineering
 * @version $Id: SubSelectQualifier.java 29625 2008-03-14 13:55:02Z groldan $
 * 
 * @source $URL:
 *         http://svn.geotools.org/geotools/trunk/gt/modules/plugin/arcsde/datastore/src/main/java/org/geotools/arcsde/data/view/SubSelectQualifier.java $
 * @since 2.3.x
 */
class SubSelectQualifier {
    /**
     * DOCUMENT ME!
     * 
     * @param conn
     *            DOCUMENT ME!
     * @param subSelect
     *            DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public static SubSelect qualify(SeConnection conn, SubSelect subSelect) {
        String alias = subSelect.getAlias();
        SelectBody select = subSelect.getSelectBody();

        SelectQualifier visitor = new SelectQualifier(conn);
        select.accept(visitor);

        PlainSelect qualifiedSelect = visitor.getQualifiedQuery();

        SubSelect qualifiedSubSelect = new SubSelect();
        qualifiedSubSelect.setAlias(alias);
        qualifiedSubSelect.setSelectBody(qualifiedSelect);

        return qualifiedSubSelect;
    }
}
