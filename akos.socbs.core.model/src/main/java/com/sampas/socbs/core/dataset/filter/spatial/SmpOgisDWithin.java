package com.sampas.socbs.core.dataset.filter.spatial;

import org.opengis.filter.spatial.DWithin;

public class SmpOgisDWithin extends SmpOgisDistanceBufferOperator {

	private DWithin openGisDWithin = null;

	public SmpOgisDWithin() {

	}

	public SmpOgisDWithin(DWithin openGisDWithin) {

		this.openGisDWithin = openGisDWithin;
	}

	public DWithin getOpenGisDWithin() {
		
		return (this.openGisDWithin);
	}	
	
}
