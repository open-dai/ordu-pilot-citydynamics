package com.sampas.socbs.core.dataset.filter.expression;

import org.opengis.filter.expression.Multiply;

public class SmpMultiply extends SmpBinaryExpression {

	private Multiply geoToolsMultiply = null;

	public SmpMultiply() {

	}

	public SmpMultiply(Multiply geoToolsMultiply) {

		this.geoToolsMultiply = geoToolsMultiply;
	}

	public Multiply getGeotoolsMultiply() {
		
		return (this.geoToolsMultiply);
	}
	
}

