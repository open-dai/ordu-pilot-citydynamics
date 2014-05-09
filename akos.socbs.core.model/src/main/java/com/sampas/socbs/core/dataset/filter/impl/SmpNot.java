package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.Filter;
import org.opengis.filter.Not;

public class SmpNot extends SmpBinaryLogicOperator{

	private Not geoToolsNot = null;

	public SmpNot() {

	}

	public SmpNot(Not geoToolsNot) {

		this.geoToolsNot = geoToolsNot;
	}

	public Not getGeotoolsNot() {
		
		return (this.geoToolsNot);
	}
	
	public SmpFilter getFilter() {
		
		Filter geoToolsFilter = null;
		
		SmpFilter smpFilter = new SmpFilter(geoToolsFilter);
		
		return (smpFilter);
	}
	
}