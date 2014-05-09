package com.sampas.socbs.core.dataset.filter.spatial;

import org.opengis.filter.spatial.Beyond;

public class SmpOgisBeyond extends SmpOgisDistanceBufferOperator {

	private Beyond openGisBeyond = null;

	public SmpOgisBeyond() {

	}

	public SmpOgisBeyond(Beyond openGisBeyond) {

		this.openGisBeyond = openGisBeyond;
	}

	public Beyond getOpenGisBeyond() {
		
		return (this.openGisBeyond);
	}
	
}
