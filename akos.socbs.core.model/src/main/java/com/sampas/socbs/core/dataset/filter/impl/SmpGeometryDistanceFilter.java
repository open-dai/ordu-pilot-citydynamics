package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.feature.Feature;
import org.geotools.filter.GeometryDistanceFilter;
import org.geotools.filter.IllegalFilterException;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;
import com.sampas.socbs.core.dataset.filter.spatial.SmpOgisDistanceBufferOperator;

@SuppressWarnings("deprecation")
public class SmpGeometryDistanceFilter extends SmpOgisDistanceBufferOperator {

	private GeometryDistanceFilter geoToolsGeometryDistanceFilter = null;

	public SmpGeometryDistanceFilter() {

	}

	public SmpGeometryDistanceFilter(GeometryDistanceFilter geoToolsGeometryDistanceFilter) {

		this.geoToolsGeometryDistanceFilter = geoToolsGeometryDistanceFilter;
	}

	public GeometryDistanceFilter getGeotoolsGeometryDistanceFilter() {
		
		return (this.geoToolsGeometryDistanceFilter);
	}
	
    
	public boolean equals(Object obj) {
    	
    	return (this.geoToolsGeometryDistanceFilter.equals(obj));
    }

	public void setDistance(double distance) throws IllegalFilterException {
    	
    	this.geoToolsGeometryDistanceFilter.setDistance(distance);
    }

    
	public boolean contains(SmpFeature smpFeature) {
    	
    	Feature feature = smpFeature.getGeoToolFeature();
    	
    	return (this.geoToolsGeometryDistanceFilter.contains(feature));
    }

    
	public double getDistance() {
    	
    	return (this.geoToolsGeometryDistanceFilter.getDistance());
    }
	
}
