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
package com.sampas.socbs.core.data.shapefile.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import org.geotools.filter.AttributeExpression;
import org.geotools.filter.BetweenFilter;
import org.geotools.filter.CompareFilter;
import org.geotools.filter.Expression;
import org.geotools.filter.FidFilter;
import org.geotools.filter.Filter;
import org.geotools.filter.FilterVisitor;
import org.geotools.filter.FunctionExpression;
import org.geotools.filter.GeometryFilter;
import org.geotools.filter.LikeFilter;
import org.geotools.filter.LiteralExpression;
import org.geotools.filter.LogicFilter;
import org.geotools.filter.MathExpression;
import org.geotools.filter.NullFilter;

/**
 * Visits a Filter and obtains the fids from it.
 * @author Jesse
 */
@SuppressWarnings({"unchecked", "deprecation"})
class FidFilterParserVisitor implements FilterVisitor{

    Collection fids=new HashSet();
    
    public void visit(Filter filter) {
    }

    public void visit(BetweenFilter filter) {
    }

    public void visit(CompareFilter filter) {
    }

    public void visit(GeometryFilter filter) {
    }

    public void visit(LikeFilter filter) {
    }

    public void visit(LogicFilter filter) {
    }

    public void visit(NullFilter filter) {
    }

    public void visit(FidFilter filter) {
        fids.addAll(Arrays.asList(filter.getFids()));
    }

    public void visit(AttributeExpression expression) {
    }

    public void visit(Expression expression) {
    }

    public void visit(LiteralExpression expression) {
    }

    public void visit(MathExpression expression) {
    }

    public void visit(FunctionExpression expression) {
    }

}
