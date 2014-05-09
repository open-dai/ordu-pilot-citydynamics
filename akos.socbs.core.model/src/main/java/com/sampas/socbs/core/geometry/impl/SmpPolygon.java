package com.sampas.socbs.core.geometry.impl;

import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.IGeometryFactory;
import com.sampas.socbs.core.geometry.ILinearRing;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.IPolygon;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;


public class SmpPolygon  implements IPolygon {

	private Polygon geoToolsPolygon = null;
	
	public SmpPolygon(){
		
	}
	
	public SmpPolygon(ILinearRing shell, ILinearRing[] holes, IGeometryFactory smpGeometryFactory) {
		
		this.geoToolsPolygon = new Polygon((LinearRing) shell,(LinearRing[]) holes,(GeometryFactory) smpGeometryFactory);
		
	}

	public Polygon getGeoToolsPolygon() {
		
		return (this.geoToolsPolygon);
	}
	
	
	public boolean IsEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public int getGeometryN() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public double getArea() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public IGeometry getBoundary() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getBoundaryDimension() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public IPoint getCentroid() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ICoordinate getCoordinate() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ICoordinate[] getCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public IEnvelope getEnvelope() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getGeometryType() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getNumGeometries() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getSRID() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void setSRID(int srid) {
		// TODO Auto-generated method stub
		
	}

	
	public int getDimension() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getNumPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void normalize() {
		// TODO Auto-generated method stub
		
	}
}
