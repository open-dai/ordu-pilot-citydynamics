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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
import net.sf.jsqlparser.statement.select.SubSelect;
import com.esri.sde.sdk.client.SeConnection;

/**
 * DOCUMENT ME!
 * 
 * @author Gabriel Roldan, Axios Engineering
 * @version $Id: ItemsListQualifier.java 29625 2008-03-14 13:55:02Z groldan $
 * @source $URL:
 *         http://svn.geotools.org/geotools/trunk/gt/modules/plugin/arcsde/datastore/src/main/java/org/geotools/arcsde/data/view/ItemsListQualifier.java $
 * @since 2.3.x
 */
@SuppressWarnings("unchecked")
class ItemsListQualifier implements ItemsListVisitor {
    /** DOCUMENT ME! */
    ItemsList _qualifiedList;

    /** DOCUMENT ME! */
    private SeConnection conn;

    private Map tableAliases;

    /**
     * Creates a new ItemsListQualifier object.
     * 
     * @param conn
     *            DOCUMENT ME!
     */
    public ItemsListQualifier(SeConnection conn, Map tableAliases) {
        this.conn = conn;
        this.tableAliases = tableAliases;
    }

    /**
     * DOCUMENT ME!
     * 
     * @param conn
     *            DOCUMENT ME!
     * @param items
     *            DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public static ItemsList qualify(SeConnection conn, Map tableAliases, ItemsList items) {
        if (items == null) {
            return null;
        }

        ItemsListQualifier q = new ItemsListQualifier(conn, tableAliases);
        items.accept(q);

        return q._qualifiedList;
    }

    /**
     * DOCUMENT ME!
     * 
     * @param subSelect
     *            DOCUMENT ME!
     */
    public void visit(SubSelect subSelect) {
        SubSelect qualified = SubSelectQualifier.qualify(conn, subSelect);
        this._qualifiedList = qualified;
    }

    /**
     * DOCUMENT ME!
     * 
     * @param expressionList
     *            DOCUMENT ME!
     */
    public void visit(ExpressionList expressionList) {
        List expressions = expressionList.getExpressions();
        List qualifiedList = new ArrayList(expressions.size());

        for (Iterator it = expressions.iterator(); it.hasNext();) {
            Expression exp = (Expression) it.next();
            Expression qExp = ExpressionQualifier.qualify(conn, tableAliases, exp);

            qualifiedList.add(qExp);
        }

        ExpressionList qExpList = new ExpressionList();
        qExpList.setExpressions(qualifiedList);
        this._qualifiedList = qExpList;
    }
}
