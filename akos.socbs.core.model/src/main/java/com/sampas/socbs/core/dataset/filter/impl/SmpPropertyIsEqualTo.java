package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.PropertyIsEqualTo;

public class SmpPropertyIsEqualTo {

	private PropertyIsEqualTo openGisPropertyIsEqualTo = null;

	public SmpPropertyIsEqualTo() {

	}

	public SmpPropertyIsEqualTo(PropertyIsEqualTo openGisPropertyIsEqualTo) {

		this.openGisPropertyIsEqualTo = openGisPropertyIsEqualTo;
	}

	public PropertyIsEqualTo getOpenGisPropertyIsEqualTo() {

		return (this.openGisPropertyIsEqualTo);
	}
	
}
