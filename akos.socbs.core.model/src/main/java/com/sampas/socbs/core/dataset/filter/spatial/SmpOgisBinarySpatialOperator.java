package com.sampas.socbs.core.dataset.filter.spatial;

import org.opengis.filter.spatial.BinarySpatialOperator;

import com.sampas.socbs.core.dataset.filter.expression.SmpOgisExpression;

public class SmpOgisBinarySpatialOperator extends SmpOgisSpatialOperator {

	private BinarySpatialOperator openGisBinarySpatialOperator = null;

	public SmpOgisBinarySpatialOperator() {

	}

	public SmpOgisBinarySpatialOperator(
			BinarySpatialOperator openGisBinarySpatialOperator) {

		this.openGisBinarySpatialOperator = openGisBinarySpatialOperator;
	}

	public BinarySpatialOperator getOpenGisBinarySpatialOperator() {

		return (this.openGisBinarySpatialOperator);
	}

	public SmpOgisExpression getExpression1() {

		SmpOgisExpression smpOgisExpression = new SmpOgisExpression(
				this.openGisBinarySpatialOperator.getExpression1());

		return (smpOgisExpression);
	}

	public SmpOgisExpression getExpression2() {

		SmpOgisExpression smpOgisExpression = new SmpOgisExpression(
				this.openGisBinarySpatialOperator.getExpression2());

		return (smpOgisExpression);
	}
}
