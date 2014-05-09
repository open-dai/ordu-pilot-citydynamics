package com.sampas.socbs.core.dataset.filter.spatial;

import org.opengis.filter.spatial.Intersects;

public class SmpOgisIntersects extends SmpOgisBinarySpatialOperator {

	private Intersects openGisIntersects = null;

	public SmpOgisIntersects() {

	}

	public SmpOgisIntersects(Intersects openGisIntersects) {

		this.openGisIntersects = openGisIntersects;
	}

	public Intersects getOpenGisIntersects() {
		
		return (this.openGisIntersects);
	}
	
}
