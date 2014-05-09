package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.Or;

public class SmpOr extends SmpBinaryLogicOperator{

	private Or geoToolsOr = null;

	public SmpOr() {

	}

	public SmpOr(Or geoToolsOr) {

		this.geoToolsOr = geoToolsOr;
	}

	public Or getGeotoolsOr() {
		
		return (this.geoToolsOr);
	}
	
}