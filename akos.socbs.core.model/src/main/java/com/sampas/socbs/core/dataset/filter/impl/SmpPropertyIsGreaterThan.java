package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.PropertyIsGreaterThan;

public class SmpPropertyIsGreaterThan {

	private PropertyIsGreaterThan openGisPropertyIsGreaterThan = null;

	public SmpPropertyIsGreaterThan() {

	}

	public SmpPropertyIsGreaterThan(PropertyIsGreaterThan openGisPropertyIsGreaterThan) {

		this.openGisPropertyIsGreaterThan = openGisPropertyIsGreaterThan;
	}

	public PropertyIsGreaterThan getOpenGisPropertyIsEqualTo() {

		return (this.openGisPropertyIsGreaterThan);
	}
	
}
