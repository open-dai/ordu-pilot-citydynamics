package com.sampas.socbs.core.processing.services;


import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.IPoint;
import com.vividsolutions.jts.geom.Coordinate;

public interface IMeasureTools {

	public double getDistance(ICoordinate coordinateOne, ICoordinate coordinateTwo, ICoordinateSystem coordinateSystem);
	
	public double getDistance(IPoint coordinateOne, IPoint coordinateTwo, ICoordinateSystem coordinateSystem);
	
	public double getDistance(IGeometry geometryOne, IGeometry geometryTwo, ICoordinateSystem coordinateSystem);

	public double getDistance1(Coordinate coordinateMinL, Coordinate coordinateMinR, ICoordinateSystem coordinateSystem) ;
	
}
