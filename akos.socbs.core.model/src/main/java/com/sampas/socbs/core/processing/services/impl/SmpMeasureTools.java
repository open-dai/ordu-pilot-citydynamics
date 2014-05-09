package com.sampas.socbs.core.processing.services.impl;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.operation.TransformException;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.crs.wkts.Wkt2000273;
import com.sampas.socbs.core.crs.wkts.Wkt2000303;
import com.sampas.socbs.core.crs.wkts.Wkt2000333;
import com.sampas.socbs.core.crs.wkts.Wkt2000363;
import com.sampas.socbs.core.crs.wkts.Wkt2000393;
import com.sampas.socbs.core.crs.wkts.Wkt2000423;
import com.sampas.socbs.core.crs.wkts.Wkt2000453;
import com.sampas.socbs.core.crs.wkts.Wkt2001273;
import com.sampas.socbs.core.crs.wkts.Wkt2001303;
import com.sampas.socbs.core.crs.wkts.Wkt2001333;
import com.sampas.socbs.core.crs.wkts.Wkt2001363;
import com.sampas.socbs.core.crs.wkts.Wkt2001393;
import com.sampas.socbs.core.crs.wkts.Wkt2001423;
import com.sampas.socbs.core.crs.wkts.Wkt2001453;
import com.sampas.socbs.core.crs.wkts.Wkt900913;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.processing.services.IMeasureTools;
import com.vividsolutions.jts.geom.Coordinate;


public class SmpMeasureTools implements IMeasureTools {
	
	public SmpMeasureTools() {
		
	}
	
	public double getDistance(ICoordinate coordinateOne, ICoordinate coordinateTwo, ICoordinateSystem coordinateSystem) {
		
		double distance = -1;
		
		Coordinate geoToolsCoordinateOne = new Coordinate(coordinateOne.getX(), coordinateOne.getY());
		
		Coordinate geoToolscoordinateTwo = new Coordinate(coordinateTwo.getX(), coordinateTwo.getY());
		
		try {
			
			if (coordinateSystem.getEPSGCode().equals(Wkt900913.EPSG900913)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt900913.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000273.EPSG2000273)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000273.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000303.EPSG2000303)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000303.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000333.EPSG2000333)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000333.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000363.EPSG2000363)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000363.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000393.EPSG2000393)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000393.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000423.EPSG2000423)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000423.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000453.EPSG2000453)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000453.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001273.EPSG2001273)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001273.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001303.EPSG2001303)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001303.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001333.EPSG2001333)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001333.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001363.EPSG2001363)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001363.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001393.EPSG2001393)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001393.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001423.EPSG2001423)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001423.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001453.EPSG2001453)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001453.wkt));
			} else {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.decode(coordinateSystem.getEPSGCode())) * 1.2;
			}
			//System.out.println(distance);
		} catch (NoSuchAuthorityCodeException ex) {
			
			ex.printStackTrace();
		} catch (TransformException ex) {
			
			ex.printStackTrace();
		} catch (FactoryException ex) {
			
			ex.printStackTrace();
		}
		//Correction for make same calculated and measured lenght same 
		return (distance);
	}
	
	public double getDistance(IPoint coordinateOne, IPoint coordinateTwo, ICoordinateSystem coordinateSystem) {
		
		double distance = -1;
		
		Coordinate geoToolsCoordinateOne = new Coordinate(coordinateOne.getX(), coordinateOne.getY());
		
		Coordinate geoToolscoordinateTwo = new Coordinate(coordinateTwo.getX(), coordinateTwo.getY());
		
		try {
			
			if (coordinateSystem.getEPSGCode().equals(Wkt900913.EPSG900913)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt900913.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000273.EPSG2000273)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000273.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000303.EPSG2000303)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000303.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000333.EPSG2000333)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000333.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000363.EPSG2000363)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000363.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000393.EPSG2000393)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000393.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000423.EPSG2000423)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000423.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000453.EPSG2000453)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000453.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001273.EPSG2001273)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001273.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001303.EPSG2001303)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001303.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001333.EPSG2001333)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001333.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001363.EPSG2001363)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001363.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001393.EPSG2001393)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001393.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001423.EPSG2001423)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001423.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001453.EPSG2001453)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001453.wkt));
			} else {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.decode(coordinateSystem.getEPSGCode())) * 1.2;
			}
			//System.out.println(distance);
		} catch (NoSuchAuthorityCodeException ex) {
			
			ex.printStackTrace();
		} catch (TransformException ex) {
			
			ex.printStackTrace();
		} catch (FactoryException ex) {
			
			ex.printStackTrace();
		}
		return (distance);
	}

	public double getDistance(IGeometry geometryOne, IGeometry geometryTwo, ICoordinateSystem coordinateSystem) {
		
		double distance = -1;
		
		Coordinate geoToolsCoordinateOne = new Coordinate(geometryOne.getCoordinate().getX(), geometryOne.getCoordinate().getY());
		
		Coordinate geoToolscoordinateTwo = new Coordinate(geometryTwo.getCoordinate().getX(), geometryTwo.getCoordinate().getY());
		
		try {
			
			if (coordinateSystem.getEPSGCode().equals(Wkt900913.EPSG900913)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt900913.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000273.EPSG2000273)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000273.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000303.EPSG2000303)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000303.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000333.EPSG2000333)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000333.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000363.EPSG2000363)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000363.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000393.EPSG2000393)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000393.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000423.EPSG2000423)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000423.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000453.EPSG2000453)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000453.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001273.EPSG2001273)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001273.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001303.EPSG2001303)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001303.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001333.EPSG2001333)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001333.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001363.EPSG2001363)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001363.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001393.EPSG2001393)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001393.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001423.EPSG2001423)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001423.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001453.EPSG2001453)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001453.wkt));
			} else {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.decode(coordinateSystem.getEPSGCode())) * 1.2;
			}
			//System.out.println(distance);
		} catch (NoSuchAuthorityCodeException ex) {
			
			ex.printStackTrace();
		} catch (TransformException ex) {
			
			ex.printStackTrace();
		} catch (FactoryException ex) {
			
			ex.printStackTrace();
		}
		return (distance);
	}

	
	public double getDistance1(Coordinate geoToolsCoordinateOne, Coordinate geoToolscoordinateTwo, ICoordinateSystem coordinateSystem) {
		
		double distance = -1;
		
		//Coordinate geoToolsCoordinateOne = new Coordinate(coordinateOne.getX(), coordinateOne.getY());
		
		//Coordinate geoToolscoordinateTwo = new Coordinate(coordinateTwo.getX(), coordinateTwo.getY());
		
		try {
			
			if (coordinateSystem.getEPSGCode().equals(Wkt900913.EPSG900913)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt900913.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000273.EPSG2000273)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000273.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000303.EPSG2000303)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000303.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000333.EPSG2000333)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000333.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000363.EPSG2000363)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000363.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000393.EPSG2000393)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000393.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000423.EPSG2000423)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000423.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2000453.EPSG2000453)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2000453.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001273.EPSG2001273)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001273.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001303.EPSG2001303)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001303.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001333.EPSG2001333)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001333.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001363.EPSG2001363)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001363.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001393.EPSG2001393)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001393.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001423.EPSG2001423)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001423.wkt));
			} else if (coordinateSystem.getEPSGCode().equals(Wkt2001453.EPSG2001453)) {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.parseWKT(Wkt2001453.wkt));
			} else {
				
				distance = JTS.orthodromicDistance(geoToolsCoordinateOne, geoToolscoordinateTwo, CRS.decode(coordinateSystem.getEPSGCode())) * 1.2;
			}
			//System.out.println(distance);
		} catch (NoSuchAuthorityCodeException ex) {
			
			ex.printStackTrace();
		} catch (TransformException ex) {
			
			ex.printStackTrace();
		} catch (FactoryException ex) {
			
			ex.printStackTrace();
		}
		//Correction for make same calculated and measured lenght same 
		return (distance);
	}
	
	
	
}
