package com.sampas.socbs.core.dataset.filter.spatial;

import org.opengis.filter.spatial.Contains;

public class SmpOgisContains extends SmpOgisBinarySpatialOperator {

	private Contains openGisContains = null;

	public SmpOgisContains() {

	}

	public SmpOgisContains(Contains openGisContains) {

		this.openGisContains = openGisContains;
	}

	public Contains getOpenGisContains() {
		
		return (this.openGisContains);
	}
	
}
