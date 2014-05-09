package com.sampas.socbs.core.geometry;

public interface ILinearRing {

	public double getArea();
	
	public ICoordinate getCoordinate();
	
	public 	ICoordinate[] getCoordinates();
	
	public IGeometry getEnvelope();
	
	public double getLength();

	public void setSRID(int SRID);
	
	public int getSRID();
}
