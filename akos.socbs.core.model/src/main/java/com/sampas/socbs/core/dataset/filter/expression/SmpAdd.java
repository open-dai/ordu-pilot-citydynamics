package com.sampas.socbs.core.dataset.filter.expression;

import org.opengis.filter.expression.Add;

public class SmpAdd extends SmpBinaryExpression {

	private Add geoToolsAdd = null;

	public SmpAdd() {

	}

	public SmpAdd(Add geoToolsAdd) {

		this.geoToolsAdd = geoToolsAdd;
	}

	public Add getGeotoolsAdd() {
		
		return (this.geoToolsAdd);
	}
	
}
