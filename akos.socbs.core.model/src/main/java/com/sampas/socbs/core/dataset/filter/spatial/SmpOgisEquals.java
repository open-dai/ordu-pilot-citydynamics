package com.sampas.socbs.core.dataset.filter.spatial;

import org.opengis.filter.spatial.Equals;

public class SmpOgisEquals extends SmpOgisBinarySpatialOperator {

	private Equals openGisEquals = null;

	public SmpOgisEquals() {

	}

	public SmpOgisEquals(Equals openGisEquals) {

		this.openGisEquals = openGisEquals;
	}

	public Equals getOpenGisEquals() {
		
		return (this.openGisEquals);
	}
	
}
