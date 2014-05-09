package com.sampas.socbs.core.dataset.filter.impl;

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

@SuppressWarnings("deprecation")
public class SmpFilterVisitor {
	
	private FilterVisitor geoToolsFilterVisitor = null;

	public SmpFilterVisitor() {

	}

	public SmpFilterVisitor(FilterVisitor geoToolsFilterVisitor) {

		this.geoToolsFilterVisitor = geoToolsFilterVisitor;
	}

	public FilterVisitor getGeotoolsFilterVisitor() {
		
		return (this.geoToolsFilterVisitor);
	}
	
	public void visit(SmpGFilter smpfilter) {
		
		Filter filter = smpfilter.getGeoToolsFilter();
		
		this.geoToolsFilterVisitor.visit(filter);
	}

	public void visit(SmpBetweenFilter smpBetweenFilter) {
		
		BetweenFilter betweenFilter = smpBetweenFilter.getGeotoolsBetweenFilter();
		
		this.geoToolsFilterVisitor.visit(betweenFilter);
	}

	public void visit(SmpCompareFilter smpCompareFilter) {
		
		CompareFilter compareFilter = smpCompareFilter.getGeotoolsCompareFilter();
		
		this.geoToolsFilterVisitor.visit(compareFilter);
	}

	public void visit(SmpGeometryFilter smpGeometryFilter) {
		
		GeometryFilter geometryFilter = smpGeometryFilter.getGeotoolsGeometryFilter();
		
		this.geoToolsFilterVisitor.visit(geometryFilter);
	}

	public void visit(SmpLikeFilter smpLikeFilter) {
		
		LikeFilter likeFilter = smpLikeFilter.getGeotoolsLikeFilter();
		
		this.geoToolsFilterVisitor.visit(likeFilter);
	}

	public void visit(SmpLogicFilter smpLogicFilter) {
		
		LogicFilter logicFilter = smpLogicFilter.getGeoToolsLagicFilter();
		
		this.geoToolsFilterVisitor.visit(logicFilter);
	}

	public void visit(SmpNullFilter smpNullFilter) {
		
		NullFilter nullFilter = smpNullFilter.getGeoToolsNullFilter();
		
		this.geoToolsFilterVisitor.visit(nullFilter);
	}

	public void visit(SmpFidFilter smpFidFilter) {
		
		FidFilter fidFilter = smpFidFilter.getGeoToolsFidFilter();
		
		this.geoToolsFilterVisitor.visit(fidFilter);
	}

	public void visit(SmpAttributeExpression smpAttributeExpression) {
		
		AttributeExpression attributeExpression = smpAttributeExpression.getGeoToolsAttributeExpression();
		
		this.geoToolsFilterVisitor.visit(attributeExpression);
	}

	public void visit(SmpExpression smpExpression) {
		
		Expression expression = smpExpression.getGeoToolsExpression();
		
		this.geoToolsFilterVisitor.visit(expression);
	}

	public void visit(SmpLiteralExpression smpLiteralExpression) {
		
		LiteralExpression literalExpression = smpLiteralExpression.getGeoToolsLiteralExpression();
		
		this.geoToolsFilterVisitor.visit(literalExpression);
	}

	public void visit(SmpMathExpression smpMathExpression) {
		
		MathExpression mathExpression = smpMathExpression.getGeoToolsMathExpression();
		
		this.geoToolsFilterVisitor.visit(mathExpression);
	}

	public void visit(SmpFunctionExpression smpFunctionExpression) {
		
		FunctionExpression functionExpression = smpFunctionExpression.getGeoToolsFunctionExpression();
		
		this.geoToolsFilterVisitor.visit(functionExpression);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

