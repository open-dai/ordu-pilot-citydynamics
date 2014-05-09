package com.sampas.socbs.core.geometry.impl;

import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.ICoordinateFilter;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateFilter;

public class SmpCoordinateFilter implements ICoordinateFilter {

	private CoordinateFilter geoToolsCoordinateFilter = null;

	public SmpCoordinateFilter() {

	}

	public SmpCoordinateFilter(CoordinateFilter geoToolsCoordinateFilter) {

		this.geoToolsCoordinateFilter = geoToolsCoordinateFilter;
	}

	public ICoordinateFilter getGeotoolsCoordinateFilter() {
		
		return (ICoordinateFilter) (this.geoToolsCoordinateFilter);
	}
	
	public void filter(ICoordinate smpCoordinate) {
		
		this.geoToolsCoordinateFilter.filter((Coordinate) smpCoordinate);
	}


	
}
