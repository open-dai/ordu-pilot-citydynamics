package com.sampas.socbs.core.dataset.filter.expression;

import org.opengis.filter.expression.BinaryExpression;

import com.sampas.socbs.core.dataset.filter.impl.SmpExpression;

public class SmpBinaryExpression extends SmpExpression {

	private BinaryExpression geoToolsBinaryExpression = null;

	public SmpBinaryExpression() {

	}

	public SmpBinaryExpression(BinaryExpression geoToolsBinaryExpression) {

		this.geoToolsBinaryExpression = geoToolsBinaryExpression;
	}

	public BinaryExpression getGeotoolsBinaryExpression() {
		
		return (this.geoToolsBinaryExpression);
	}
	
	public SmpOgisExpression getExpression1() {

		SmpOgisExpression smpOgisExpression = new SmpOgisExpression(
				this.geoToolsBinaryExpression.getExpression1());

		return (smpOgisExpression);
	}

	public SmpOgisExpression getExpression2() {

		SmpOgisExpression smpOgisExpression = new SmpOgisExpression(
				this.geoToolsBinaryExpression.getExpression2());

		return (smpOgisExpression);
	}
	
}
