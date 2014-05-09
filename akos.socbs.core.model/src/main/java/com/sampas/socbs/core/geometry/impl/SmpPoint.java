package com.sampas.socbs.core.geometry.impl;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.geometry.IPoint;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;


public class SmpPoint implements IPoint {
	
	private Point geoToolsPoint;
//	private double x;
//	private double y;
//	private double z;
	private ICoordinateSystem coordinateSystem;
	
	@SuppressWarnings("deprecation")
	public SmpPoint(double xCoordinate, double yCoordinate, ICoordinateSystem coordinateSystem){
		
		this.coordinateSystem = coordinateSystem;
		
		Coordinate coordinate = new Coordinate(xCoordinate, yCoordinate);
		
		PrecisionModel precisionModel = new PrecisionModel();
		
		geoToolsPoint = new Point(coordinate, precisionModel, coordinateSystem.getEPSGCodeNo());

	}
	
	public SmpPoint(Point geoToolsPoint) {
				
		this.geoToolsPoint = geoToolsPoint;
		
	}
	
	public SmpPoint(Object pointObject) throws Exception {
		
		try {
			
			this.geoToolsPoint = (Point)pointObject;
		} catch (Exception ex) {

			throw new Exception("Error on converting object to point !!!");
		}
	}
	
	public SmpPoint(CoordinateSequence smpCoordinateSequence, GeometryFactory smpFactory) {
			
		geoToolsPoint = new Point(smpCoordinateSequence,smpFactory);
		
	}

	public Point getGeoToolsPoint() {
		
		return (this.geoToolsPoint);
	}
	
	
	public double getX() {

		return this.geoToolsPoint.getX();
	}

	
	public double getY() {
		
		return this.geoToolsPoint.getY();
	}
	
	
	public double getZ() {

		return 0;
		
	}

	public ICoordinateSystem getCoordinateSystem() {

		return this.coordinateSystem;
	}

}


//package com.sampas.ogcows.geometry;
//
//import com.vividsolutions.jts.geom.Point;
//
//public class SmpPoint extends Point{
//
//	private static final long serialVersionUID = 4902022702746614570L;
//	
//	public SmpPoint(SmpCoordinateSequence smpCoordinateSequence, SmpGeometryFactory smpFactory){
//				
//		super(smpCoordinateSequence.getGeotoolsCoordinateSequence(), smpFactory);
//	}
//	
//}
