package com.sampas.socbs.core.dataset.filter.expression;

import org.opengis.filter.expression.Subtract;

public class SmpSubtract extends SmpBinaryExpression{

	private Subtract geoToolsSubtract = null;

	public SmpSubtract() {

	}

	public SmpSubtract(Subtract geoToolsSubtract) {

		this.geoToolsSubtract = geoToolsSubtract;
	}

	public Subtract getGeotoolsSubtract() {
		
		return (this.geoToolsSubtract);
	}
	
}
