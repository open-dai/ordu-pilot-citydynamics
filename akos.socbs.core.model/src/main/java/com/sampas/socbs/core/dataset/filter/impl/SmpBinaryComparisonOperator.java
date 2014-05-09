package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.BinaryComparisonOperator;

import com.sampas.socbs.core.dataset.filter.expression.SmpOgisExpression;

public class SmpBinaryComparisonOperator extends SmpGFilter {

	private BinaryComparisonOperator geoToolsBinaryComparisonOperator = null;

	public SmpBinaryComparisonOperator() {

	}

	public SmpBinaryComparisonOperator(BinaryComparisonOperator geoToolsBinaryComparisonOperator) {

		this.geoToolsBinaryComparisonOperator = geoToolsBinaryComparisonOperator;
	}

	public BinaryComparisonOperator getGeotoolsBinaryComparisonOperator() {
		
		return (this.geoToolsBinaryComparisonOperator);
	}
	
    public SmpOgisExpression getExpression1() {
    	
    	SmpOgisExpression smpOgisExpression = new SmpOgisExpression(this.geoToolsBinaryComparisonOperator.getExpression1());
    	
    	return (smpOgisExpression);    	
    }

    public SmpOgisExpression getExpression2() {
    	
    	SmpOgisExpression smpOgisExpression = new SmpOgisExpression(this.geoToolsBinaryComparisonOperator.getExpression2());
    	
    	return (smpOgisExpression);    	
    }
	
}
