package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.PropertyIsLessThanOrEqualTo;

public class SmpPropertyIsLessThanOrEqualTo {

	private PropertyIsLessThanOrEqualTo openGisPropertyIsLessThanOrEqualTo = null;

	public SmpPropertyIsLessThanOrEqualTo() {

	}

	public SmpPropertyIsLessThanOrEqualTo(PropertyIsLessThanOrEqualTo openGisPropertyIsLessThanOrEqualTo) {

		this.openGisPropertyIsLessThanOrEqualTo = openGisPropertyIsLessThanOrEqualTo;
	}

	public PropertyIsLessThanOrEqualTo getOpenGisPropertyIsLessThanOrEqualTo() {

		return (this.openGisPropertyIsLessThanOrEqualTo);
	}
	
}
