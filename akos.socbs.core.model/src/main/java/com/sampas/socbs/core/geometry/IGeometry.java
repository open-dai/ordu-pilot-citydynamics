package com.sampas.socbs.core.geometry;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface IGeometry {
	
	public String getGeometryType(); 
	
	public ICoordinate getCoordinate();
	
	public ICoordinate[] getCoordinates(); 
	
	public int getNumPoints();
	
	public int getDimension(); 
	
	public IGeometry getBoundary();
	
	public int getBoundaryDimension();
	
	public void normalize();
	
	public int getNumGeometries();

}