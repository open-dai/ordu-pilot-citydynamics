package com.sampas.socbs.core.dataset.filter.spatial;

import org.opengis.filter.spatial.Within;

public class SmpOgisWithin extends SmpOgisBinarySpatialOperator {

	private Within openGisWithin = null;

	public SmpOgisWithin() {

	}

	public SmpOgisWithin(Within openGisWithin) {

		this.openGisWithin = openGisWithin;
	}

	public Within getOpenGisWithin() {
		
		return (this.openGisWithin);
	}
		
}
