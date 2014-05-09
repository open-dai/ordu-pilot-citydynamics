package com.sampas.socbs.core.geometry;

public interface IPolygon extends IGeometry {
	
	public IEnvelope getEnvelope();

	public boolean IsEmpty();
	
	public double getArea();
	
	public IPoint getCentroid();
	
	public int getGeometryN();
	
	public int getNumGeometries();
	
	public int getSRID();
	
	public void setSRID(int srid);
	
}
