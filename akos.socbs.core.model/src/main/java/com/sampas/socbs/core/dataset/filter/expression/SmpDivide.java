package com.sampas.socbs.core.dataset.filter.expression;

import org.opengis.filter.expression.Divide;

public class SmpDivide extends SmpBinaryExpression {

	private Divide geoToolsDivide = null;

	public SmpDivide() {

	}

	public SmpDivide(Divide geoToolsDivide) {

		this.geoToolsDivide = geoToolsDivide;
	}

	public Divide getGeotoolsDivide() {
		
		return (this.geoToolsDivide);
	}
	
}
