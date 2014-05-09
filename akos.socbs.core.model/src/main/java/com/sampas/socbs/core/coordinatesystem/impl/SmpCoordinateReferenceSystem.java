package com.sampas.socbs.core.coordinatesystem.impl;

import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class SmpCoordinateReferenceSystem {

	private CoordinateReferenceSystem geoCoordinateReferenceSystem = null;

	public SmpCoordinateReferenceSystem() {
		
	}
	
	public SmpCoordinateReferenceSystem(CoordinateReferenceSystem geoCoordinateReferenceSystem) {
		
		this.geoCoordinateReferenceSystem = geoCoordinateReferenceSystem;
	}
	
	public CoordinateReferenceSystem getGeoCoordinateReferenceSystem() {
		return this.geoCoordinateReferenceSystem;
	}

	public void setGeoCoordinateReferenceSystem(CoordinateReferenceSystem geoCoordinateReferenceSystem) {
		this.geoCoordinateReferenceSystem = geoCoordinateReferenceSystem;
	}	
	
}
