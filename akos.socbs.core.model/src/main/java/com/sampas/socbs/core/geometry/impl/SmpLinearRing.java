package com.sampas.socbs.core.geometry.impl;

import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.ILinearRing;
import com.vividsolutions.jts.geom.LinearRing;

public class SmpLinearRing implements ILinearRing {

	private LinearRing linearRing = null;
	
	public SmpLinearRing(SmpCoordinateSequence points, SmpGeometryFactory smpGeometryFactory) {
	
	this.linearRing = new LinearRing( points.getGeotoolsCoordinateSequence(), smpGeometryFactory.getGeoToolsGeometryFactory());	
		
	}
	
	public LinearRing getGeoToolsLinearRing() {
		
		return (this.linearRing);
	}
	
	public double getArea(){
		
		return this.linearRing.getArea();
	}
	
	public ICoordinate getCoordinate(){
		
		ICoordinate coordinate = new SmpCoordinate(this.linearRing.getCoordinate());
		
		return (coordinate);
	}
	
	public 	ICoordinate[] getCoordinates(){
		
		ICoordinate[] coordinates = new SmpCoordinate[this.linearRing.getCoordinates().length];
		
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = new SmpCoordinate(this.linearRing.getCoordinateN(i)); 
		}
		
		return (coordinates);
		
	}
	
	public IGeometry getEnvelope(){
		
		return (IGeometry) this.linearRing.getEnvelope();
	}
	
	public double getLength(){
		
		return this.linearRing.getLength();
	}

	public void setSRID(int SRID){
		
		this.linearRing.setSRID(SRID);
	}
	
	public int getSRID(){
		
		return (this.linearRing.getSRID());
	}

}
