package com.sampas.socbs.core.geometry.impl;

import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.IGeometryFilter;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFilter;

public class SmpGeometryFilter implements IGeometryFilter{

	private GeometryFilter geoToolsGeometryFilter = null;

	public SmpGeometryFilter() {

	}

	public SmpGeometryFilter(GeometryFilter geoToolsGeometryFilter) {

		this.geoToolsGeometryFilter = geoToolsGeometryFilter;
	}

	public IGeometryFilter getGeotoolsGeometryFilter() {
		
		return (IGeometryFilter) (this.geoToolsGeometryFilter);
	}
	
	public void filter(IGeometry smpGeometry) {
		
		Geometry geometry = (Geometry) smpGeometry;
		
		this.geoToolsGeometryFilter.filter(geometry);		
	}

	
}
