package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.feature.Feature;
import org.geotools.filter.Expression;
import org.geotools.filter.FilterVisitor;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;
import com.sampas.socbs.core.dataset.filter.expression.SmpExpressionType;

@SuppressWarnings("deprecation")
public class SmpExpression extends SmpExpressionType {

	private Expression geoToolsExpression = null;

	public SmpExpression() {

	}
	
	public SmpExpression(Expression geoToolsExpression) {

		this.geoToolsExpression = geoToolsExpression;
	}

	public Expression getGeoToolsExpression() {

		return (this.geoToolsExpression);
	}
	
	public short getType() {

		return (this.geoToolsExpression.getType());
	}

	public Object evaluate(SmpFeature smpFeature) {

		Feature feature = smpFeature.getGeoToolFeature();
		
		return (this.geoToolsExpression.evaluate(feature));
	}

	public Object getValue(SmpFeature smpFeature) {

		Feature feature = smpFeature.getGeoToolFeature();
		
		return (this.geoToolsExpression.getValue(feature));
	}
	
	public void accept(SmpFilterVisitor smpFilterVisitor) {

		FilterVisitor featureVisitor = smpFilterVisitor.getGeotoolsFilterVisitor();
		
	    this.geoToolsExpression.accept(featureVisitor);
	}

}
