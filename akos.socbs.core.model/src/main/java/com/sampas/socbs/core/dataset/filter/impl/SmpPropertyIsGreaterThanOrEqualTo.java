package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;

public class SmpPropertyIsGreaterThanOrEqualTo {

	private PropertyIsGreaterThanOrEqualTo openGisPropertyIsGreaterThanOrEqualTo = null;

	public SmpPropertyIsGreaterThanOrEqualTo() {

	}

	public SmpPropertyIsGreaterThanOrEqualTo(PropertyIsGreaterThanOrEqualTo openGisPropertyIsGreaterThanOrEqualTo) {

		this.openGisPropertyIsGreaterThanOrEqualTo = openGisPropertyIsGreaterThanOrEqualTo;
	}

	public PropertyIsGreaterThanOrEqualTo getOpenGisPropertyIsGreaterThanOrEqualTo() {

		return (this.openGisPropertyIsGreaterThanOrEqualTo);
	}
	
}
