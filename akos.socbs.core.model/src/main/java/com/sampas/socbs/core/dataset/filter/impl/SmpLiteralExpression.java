package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.feature.Feature;
import org.geotools.filter.IllegalFilterException;
import org.geotools.filter.LiteralExpression;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;

@SuppressWarnings("deprecation")
public class SmpLiteralExpression extends SmpExpression {

	
	private LiteralExpression geoToolsLiteralExpression = null;

	public SmpLiteralExpression() {

	}

	public SmpLiteralExpression(LiteralExpression geoToolsLiteralExpression) {

		this.geoToolsLiteralExpression = geoToolsLiteralExpression;
	}

	public LiteralExpression getGeoToolsLiteralExpression() {
		
		return (this.geoToolsLiteralExpression);
	}
	
	public void setLiteral(Object literal) throws IllegalFilterException {
		
		this.geoToolsLiteralExpression.setLiteral(literal);
	}

	
	public Object getValue(SmpFeature smpFeature) {
		
		Feature feature = smpFeature.getGeoToolFeature();
		
		return (this.geoToolsLiteralExpression.getValue(feature));
	}

	
	public short getType() {
		
		return (this.geoToolsLiteralExpression.getType());
	}

	public Object getLiteral() {
		
		return (this.geoToolsLiteralExpression.getLiteral());
	}
	
}
