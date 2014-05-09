package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.feature.Feature;
import org.geotools.filter.AttributeExpression;
import org.geotools.filter.IllegalFilterException;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;

@SuppressWarnings("deprecation")
public class SmpAttributeExpression extends SmpExpression{

	private AttributeExpression geoToolsAttributeExpression = null;

	public SmpAttributeExpression() {

	}

	public SmpAttributeExpression(AttributeExpression geoToolsAttributeExpression) {

		this.geoToolsAttributeExpression = geoToolsAttributeExpression;
	}

	public AttributeExpression getGeoToolsAttributeExpression() {
		
		return (this.geoToolsAttributeExpression);
	}
	
	public void setAttributePath(String attributePath) throws IllegalFilterException {
    	
    	this.geoToolsAttributeExpression.setAttributePath(attributePath);
    }

    
	public Object getValue(SmpFeature smpFeature) {
    	
    	Feature feature = smpFeature.getGeoToolFeature();
    	
    	return (this.geoToolsAttributeExpression.getValue(feature));
    }

	public String getAttributePath() {
    	
    	return (this.geoToolsAttributeExpression.getAttributePath());
    }
	
}
