package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.PropertyIsLessThan;

public class SmpPropertyIsLessThan {

	private PropertyIsLessThan openGisPropertyIsLessThan = null;

	public SmpPropertyIsLessThan() {

	}

	public SmpPropertyIsLessThan(PropertyIsLessThan openGisPropertyIsLessThan) {

		this.openGisPropertyIsLessThan = openGisPropertyIsLessThan;
	}

	public PropertyIsLessThan getOpenGisPropertyIsLessThan() {

		return (this.openGisPropertyIsLessThan);
	}
	
}
