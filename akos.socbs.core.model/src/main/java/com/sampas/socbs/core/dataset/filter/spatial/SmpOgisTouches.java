package com.sampas.socbs.core.dataset.filter.spatial;

import org.opengis.filter.spatial.Touches;

public class SmpOgisTouches  extends SmpOgisBinarySpatialOperator{

	private Touches openGisTouches = null;

	public SmpOgisTouches() {

	}

	public SmpOgisTouches(Touches openGisTouches) {

		this.openGisTouches = openGisTouches;
	}

	public Touches getOpenGisTouches() {
		
		return (this.openGisTouches);
	}
	
}
