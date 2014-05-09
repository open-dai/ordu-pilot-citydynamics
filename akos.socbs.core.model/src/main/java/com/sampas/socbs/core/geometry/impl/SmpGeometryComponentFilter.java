package com.sampas.socbs.core.geometry.impl;

import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IGeometryComponentFilter;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryComponentFilter;

public class SmpGeometryComponentFilter implements IGeometryComponentFilter {

	private GeometryComponentFilter geoToolsGeometryComponentFilter = null;

	public SmpGeometryComponentFilter() {

	}

	public SmpGeometryComponentFilter(GeometryComponentFilter geoToolsGeometryComponentFilter) {

		this.geoToolsGeometryComponentFilter = geoToolsGeometryComponentFilter;
	}

	public IGeometryComponentFilter getGeotoolsGeometryComponentFilter() {
		
		return (IGeometryComponentFilter) (this.geoToolsGeometryComponentFilter);
	}
	
	//must fix
	public void filter(ICoordinate smpGeometry) {
		
		Geometry geom = null;
		
		this.geoToolsGeometryComponentFilter.filter(geom);
	}
}
