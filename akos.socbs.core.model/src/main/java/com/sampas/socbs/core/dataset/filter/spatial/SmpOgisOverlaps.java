package com.sampas.socbs.core.dataset.filter.spatial;

import org.opengis.filter.spatial.Overlaps;

public class SmpOgisOverlaps extends SmpOgisBinarySpatialOperator {

	private Overlaps openGisOverlaps = null;

	public SmpOgisOverlaps() {

	}

	public SmpOgisOverlaps(Overlaps openGisOverlaps) {

		this.openGisOverlaps = openGisOverlaps;
	}

	public Overlaps getOpenGisOverlaps() {
		
		return (this.openGisOverlaps);
	}
	
}
