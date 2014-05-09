package com.sampas.socbs.core.geometry.impl;

import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IGeometry;
import com.vividsolutions.jts.geom.CoordinateFilter;
import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryComponentFilter;
import com.vividsolutions.jts.geom.GeometryFilter;

public class SmpGeometry implements IGeometry{
	
	private Geometry geoToolsGeometry = null;

	public SmpGeometry() {
		
	}
	
	public SmpGeometry(Geometry geoToolsGeometry) {
		
		this.geoToolsGeometry = geoToolsGeometry;		
	}
	
	public Geometry getGeoToolGeometry() {
		
		return (this.geoToolsGeometry);		
	}	

	public String getGeometryType() {
		
		return (this.geoToolsGeometry.getGeometryType());
	}

	public ICoordinate getCoordinate() {

		ICoordinate smpCoordinate = new SmpCoordinate(this.geoToolsGeometry.getCoordinate());
		return (smpCoordinate);
	}

	public ICoordinate[] getCoordinates() {
		
		ICoordinate[] smpCoordinates = new SmpCoordinate[this.geoToolsGeometry.getCoordinates().length];
		
		for (int i = 0; i < smpCoordinates.length; i++) {
			smpCoordinates[i] =  new SmpCoordinate(this.geoToolsGeometry.getCoordinates()[i]);
		}
		
		return smpCoordinates;
	}

	public int getNumPoints() {
		
		return (this.geoToolsGeometry.getNumPoints());
	}
	
	public int getNumGeometries(){
		
		return (this.geoToolsGeometry.getNumGeometries());
	}

	public boolean isEmpty() {
		
		return (this.geoToolsGeometry.isEmpty());
	}

	public int getDimension() {
		
		return (this.geoToolsGeometry.getDimension());
	}

	public IGeometry getBoundary() {
		
		SmpGeometry smpGeometry = new SmpGeometry(this.geoToolsGeometry.getBoundary()); 
		
		return smpGeometry;
	}

	public int getBoundaryDimension() {
		
		return (this.geoToolsGeometry.getBoundaryDimension());
	}

	public boolean equalsExact(SmpGeometry otherSmpGeometry, double tolerance) {
		
		Geometry otherGeometry = (Geometry) otherSmpGeometry.getGeoToolGeometry();
		
		return (this.geoToolsGeometry.equalsExact(otherGeometry));
	}

	public void apply(SmpCoordinateFilter smpCoordinateFilter) {
		
		CoordinateFilter coordinateFilter = 
			(CoordinateFilter) smpCoordinateFilter.getGeotoolsCoordinateFilter();
		
		this.geoToolsGeometry.apply(coordinateFilter);
	}

	public void apply(SmpCoordinateSequenceFilter smpCoordinateSequenceFilter) {
		
		CoordinateSequenceFilter coordinateSequenceFilter = smpCoordinateSequenceFilter.getGeotoolsCoordinateSequenceFilter();
		
		this.geoToolsGeometry.apply(coordinateSequenceFilter);
		
	}

	public void apply(SmpGeometryFilter smpGeometryFilter) {		
		
		GeometryFilter geometryFilter = (GeometryFilter) smpGeometryFilter.getGeotoolsGeometryFilter();
				
		this.geoToolsGeometry.apply(geometryFilter);
		
	}

	public void apply(SmpGeometryComponentFilter smpGeometryComponentFilter) {
		
		GeometryComponentFilter geometryComponentFilter = 
			(GeometryComponentFilter) smpGeometryComponentFilter.getGeotoolsGeometryComponentFilter();
		
		this.geoToolsGeometry.apply(geometryComponentFilter);
		
	}

	public void normalize() {
	
		this.geoToolsGeometry.normalize();
	}
	
// Must check
	@SuppressWarnings("unused")
	private SmpEnvelope computeEnvelopeInternal() {
				
		SmpEnvelope smpEnvelope = new SmpEnvelope();
		
		return (smpEnvelope);
	}
		
	protected int compareToSameClass(Object o) {
		
		return (this.geoToolsGeometry.compareTo(o));
	}


//	protected int compareToSameClass(Object o, SmpCoordinateSequenceComparator smpCoordinateSequenceComparator) {
//		
//		CoordinateSequenceComparator comparator = new CoordinateSequenceComparator();
//		
//		comparator= (CoordinateSequenceComparator) smpCoordinateSequenceComparator;
//		
//		return (this.geoToolsGeometry.compareTo(o, smpCoordinateSequenceComparator));
//		
//	}
	
}
