package com.sampas.socbs.core.dataset.filter.spatial;

import org.opengis.filter.spatial.Crosses;

public class SmpOgisCrosses extends SmpOgisBinarySpatialOperator {

	private Crosses openGisCrosses = null;

	public SmpOgisCrosses() {

	}

	public SmpOgisCrosses(Crosses openGisCrosses) {

		this.openGisCrosses = openGisCrosses;
	}

	public Crosses getOpenGisCrosses() {
		
		return (this.openGisCrosses);
	}
	
}
