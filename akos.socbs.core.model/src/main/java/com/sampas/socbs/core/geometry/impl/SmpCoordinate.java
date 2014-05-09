package com.sampas.socbs.core.geometry.impl;

import com.sampas.socbs.core.geometry.ICoordinate;
import com.vividsolutions.jts.geom.Coordinate;

public class SmpCoordinate implements ICoordinate {

	private Coordinate geoToolsCoordinate = null;
	

	public SmpCoordinate(Coordinate coordinate) {
		
		this.geoToolsCoordinate = coordinate;
		
	  }
	
	public Coordinate getGeoToolsCoordinate() {
		
		return (this.geoToolsCoordinate);
	}
	  
	public SmpCoordinate(double x, double y) {		  
		this.geoToolsCoordinate = new Coordinate(x,y);		  
		  }
	  
	public SmpCoordinate(double x, double y, double z) {		  
		this.geoToolsCoordinate = new Coordinate(x,y,z);	
			 
		  }  
	  
	public void setCoordinate(SmpCoordinate coordinate) {
		
			 this.geoToolsCoordinate.setCoordinate(coordinate.getGeoToolsCoordinate());
		  }

	
	public double getX() {
		
		return  (this.geoToolsCoordinate.x);
		
	}

	
	public double getY() {

		return (this.geoToolsCoordinate.y);
		
	}

	
	public double getZ() {

		return (this.geoToolsCoordinate.z);
		
	}


}
