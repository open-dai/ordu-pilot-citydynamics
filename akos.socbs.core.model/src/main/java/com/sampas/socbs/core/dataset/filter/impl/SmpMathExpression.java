package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.feature.Feature;
import org.geotools.filter.Expression;
import org.geotools.filter.IllegalFilterException;
import org.geotools.filter.MathExpression;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;

@SuppressWarnings("deprecation")
public class SmpMathExpression extends SmpExpression {
	
	private MathExpression geoToolsMathExpression = null;

	public SmpMathExpression() {

	}

	public SmpMathExpression(MathExpression geoToolsMathExpression) {

		this.geoToolsMathExpression = geoToolsMathExpression;
	}

	public MathExpression getGeoToolsMathExpression() {
		
		return (this.geoToolsMathExpression);
	}
	
    
	public Object getValue(SmpFeature smpFeature) {
    	
    	Feature feature = smpFeature.getGeoToolFeature();
    	
    	return (this.geoToolsMathExpression.getValue(feature));
    }

	public void addRightValue(SmpExpression rightValue) throws IllegalFilterException {
    	
    	Expression expression = rightValue.getGeoToolsExpression();
    	
    	this.geoToolsMathExpression.addRightValue(expression);
    }

    
	public short getType() {
    	
    	return (this.geoToolsMathExpression.getType());
    }

	public SmpExpression getLeftValue() {
    	
    	SmpExpression smpExpression = new SmpExpression(this.geoToolsMathExpression.getLeftValue());
    	
    	return (smpExpression);
    }
    
	public SmpExpression getRightValue() {
    	
    	SmpExpression smpExpression = new SmpExpression(this.geoToolsMathExpression.getRightValue());
    	
    	return (smpExpression);
    }

	public void addLeftValue(SmpExpression leftValue) throws IllegalFilterException {
    	
    	Expression expression = leftValue.getGeoToolsExpression();
    	
    	this.geoToolsMathExpression.addLeftValue(expression);
    }
	
}
