package com.sampas.socbs.core.geometry.impl;

import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.IGeometryFactory;
import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

public class SmpGeometryFactory implements IGeometryFactory{

	private GeometryFactory geoToolsGeometryFactory= null;

	public SmpGeometryFactory() {

	}

	public SmpGeometryFactory(PrecisionModel precisionModel, int SRID,
			CoordinateSequenceFactory coordinateSequenceFactory) {

		this.geoToolsGeometryFactory = new GeometryFactory(precisionModel, SRID, coordinateSequenceFactory);
		//super(precisionModel, SRID, coordinateSequenceFactory);
	}
	
	public GeometryFactory getGeoToolsGeometryFactory(){
		
		return this.geoToolsGeometryFactory;
	}

	
	public void createGeometry(IGeometry g) {
		// TODO Auto-generated method stub
		
	}

	
	public int getSRID() {
		// TODO Auto-generated method stub
		return 0;
	}
}
