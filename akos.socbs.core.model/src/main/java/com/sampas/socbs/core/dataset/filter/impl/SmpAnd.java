package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.And;

public class SmpAnd extends SmpBinaryLogicOperator{

	private And openGisAnd = null;

	public SmpAnd() {

	}

	public SmpAnd(And openGisAnd) {

		this.openGisAnd = openGisAnd;
	}

	public And getOpenGisAnd() {
		
		return (this.openGisAnd);
	}
	
}
