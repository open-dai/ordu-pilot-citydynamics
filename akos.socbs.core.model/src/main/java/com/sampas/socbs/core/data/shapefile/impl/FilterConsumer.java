/*
 *    GeoTools - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2003-2006, GeoTools Project Managment Committee (PMC)
 *    (C) 2002, Centre for Computational Geography
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

import java.util.Iterator;
import java.util.logging.Logger;
import org.geotools.filter.AbstractFilter;
import org.geotools.filter.AttributeExpression;
import org.geotools.filter.BetweenFilter;
import org.geotools.filter.CompareFilter;
import org.geotools.filter.DefaultExpression;
import org.geotools.filter.Expression;
import org.geotools.filter.FidFilter;
import org.geotools.filter.Filter;
import org.geotools.filter.FilterVisitor;
import org.geotools.filter.Filters;
import org.geotools.filter.FunctionExpression;
import org.geotools.filter.GeometryFilter;
import org.geotools.filter.LikeFilter;
import org.geotools.filter.LiteralExpression;
import org.geotools.filter.LogicFilter;
import org.geotools.filter.MathExpression;
import org.geotools.filter.NullFilter;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;

/**
 * DOCUMENT ME!
 *
 * @author Tommaso Nolli
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/plugin/shapefile/src/main/java/org/geotools/index/rtree/FilterConsumer.java $
 */
@SuppressWarnings({"unchecked", "deprecation"})
public class FilterConsumer implements FilterVisitor {
    private static Logger LOGGER = org.geotools.util.logging.Logging.getLogger("org.geotools.index.rtree");
    private Envelope bounds = null;

    public Envelope getBounds() {
        return this.bounds;
    }

    /**
     * @see org.geotools.filter.FilterVisitor#visit(org.geotools.filter.Filter)
     */
    public void visit(Filter filter) {
        if (filter == Filter.NONE) {
            this.bounds = new Envelope(Double.NEGATIVE_INFINITY,
                    Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY,
                    Double.POSITIVE_INFINITY);
        } else if (filter == Filter.ALL) {
            this.bounds = null;
        } else {
            LOGGER.warning("Unknown filter type:" + filter.toString());
        }
    }

    /**
     * @see org.geotools.filter.FilterVisitor#visit(org.geotools.filter.BetweenFilter)
     */
    public void visit(BetweenFilter filter) {
        LOGGER.finest("BetweenFilter ignored!");
    }

    /**
     * @see org.geotools.filter.FilterVisitor#visit(org.geotools.filter.CompareFilter)
     */
    public void visit(CompareFilter filter) {
        LOGGER.finest("CompareFilter ignored!");
    }

    /**
     * @see org.geotools.filter.FilterVisitor#visit(org.geotools.filter.GeometryFilter)
     */
    public void visit(GeometryFilter filter) {
        if (filter.getFilterType() == Filter.GEOMETRY_BBOX || 
		filter.getFilterType() == Filter.GEOMETRY_EQUALS || 
		filter.getFilterType() == Filter.GEOMETRY_INTERSECTS || 
		filter.getFilterType() == Filter.GEOMETRY_TOUCHES || 
		filter.getFilterType() == Filter.GEOMETRY_CROSSES || 
		filter.getFilterType() == Filter.GEOMETRY_WITHIN || 
		filter.getFilterType() == Filter.GEOMETRY_CONTAINS || 
		filter.getFilterType() == Filter.GEOMETRY_OVERLAPS || 
		filter.getFilterType() == Filter.GEOMETRY_DWITHIN ) {
            DefaultExpression left = (DefaultExpression) filter.getLeftGeometry();
            DefaultExpression right = (DefaultExpression) filter
                .getRightGeometry();

            if (left != null) {
                left.accept(this);
            }

            if (right != null) {
                right.accept(this);
            }
        }
    }

    /**
     * @see org.geotools.filter.FilterVisitor#visit(org.geotools.filter.LikeFilter)
     */
    public void visit(LikeFilter filter) {
        LOGGER.finest("LikeFilter ignored!");
    }

    /**
     * @see org.geotools.filter.FilterVisitor#visit(org.geotools.filter.LogicFilter)
     */
    public void visit(LogicFilter filter) {
        switch (filter.getFilterType()) {
        case AbstractFilter.LOGIC_NOT:
            LOGGER.finest("[NOT] LogicFilter ignored!");

            break;

        case AbstractFilter.LOGIC_OR:
            LOGGER.finest("[OR] LogicFilter ignored!");

            break;

        case AbstractFilter.LOGIC_AND:

            Iterator list = filter.getFilterIterator();

            while (list.hasNext()) {
                Filters.accept((org.opengis.filter.Filter)list.next(),this);
            }

            break;

        default:
            LOGGER.finest("LogicFilter ignored!");
        }
    }

    /**
     * @see org.geotools.filter.FilterVisitor#visit(org.geotools.filter.NullFilter)
     */
    public void visit(NullFilter filter) {
        LOGGER.finest("NullFilter ignored!");
    }

    /**
     * @see org.geotools.filter.FilterVisitor#visit(org.geotools.filter.FidFilter)
     */
    public void visit(FidFilter filter) {
        LOGGER.finest("FidFilter ignored!");
    }

    /**
     * @see org.geotools.filter.FilterVisitor#visit(org.geotools.filter.AttributeExpression)
     */
    public void visit(AttributeExpression expression) {
        LOGGER.finest("AttributeExpression ignored!");
    }

    /**
     * @see org.geotools.filter.FilterVisitor#visit(org.geotools.filter.Expression)
     */
    public void visit(Expression expression) {
        LOGGER.finest("Expression ignored!");
    }

    /**
     * @see org.geotools.filter.FilterVisitor#visit(org.geotools.filter.LiteralExpression)
     */
    public void visit(LiteralExpression expression) {
        if (expression.getType() == DefaultExpression.LITERAL_GEOMETRY) {
            Geometry bbox = (Geometry) expression.getLiteral();

            if (this.bounds == null) {
                this.bounds = bbox.getEnvelopeInternal();
            } else {
                this.bounds.expandToInclude(bbox.getEnvelopeInternal());
            }
        } else {
            LOGGER.warning("LiteralExpression ignored!");
        }
    }

    /**
     * @see org.geotools.filter.FilterVisitor#visit(org.geotools.filter.MathExpression)
     */
    public void visit(MathExpression expression) {
        LOGGER.finest("MathExpression ignored!");
    }

    /**
     * @see org.geotools.filter.FilterVisitor#visit(org.geotools.filter.FunctionExpression)
     */
    public void visit(FunctionExpression expression) {
        LOGGER.finest("FunctionExpression ignored!");
    }
}
