package com.sampas.socbs.core.processing.services.impl;

import java.util.List;

import org.geotools.feature.AttributeType;
import org.geotools.feature.AttributeTypeFactory;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureType;
import org.geotools.feature.FeatureTypeFactory;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpPoint;
import com.sampas.socbs.core.processing.services.IFeatureCreator;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTReader;


@SuppressWarnings("deprecation")
public class SmpFeatureCreator implements IFeatureCreator {
	
	public IFeature pointFeatureCreator(String featureType, String featureName, String description, IPoint smpPoint) {
		
		if (featureType == null || featureType ==""){
			featureType = "DefaultType";
		}
		if (featureName == null || featureName ==""){
			featureName = "DefaultName";
		}
		try {			
			AttributeType geom = AttributeTypeFactory.newAttributeType("GEOMETRY", Point.class);
			AttributeType name = AttributeTypeFactory.newAttributeType("Name", java.lang.String.class);
			AttributeType desc = AttributeTypeFactory.newAttributeType("Description", java.lang.String.class);
			FeatureType newFeatureType = FeatureTypeFactory.newFeatureType(new AttributeType[] {geom, name, desc}, featureType);
			WKTReader wktReader = new WKTReader();
			Point geometry = (Point) wktReader.read("POINT (" + smpPoint.getX() + " " + smpPoint.getY() + ")");
			Feature newFeature = newFeatureType.create(new Object[] {geometry, featureName, description});
			newFeature.getDefaultGeometry().setSRID(((SmpPoint)smpPoint).getGeoToolsPoint().getSRID());
			
			return (new SmpFeature(newFeature));
			
		} catch (Exception ex) {
			System.out.println("Error on creating Point feature");
			return null;
		}
	}
	
	public IFeature lineFeatureCreator(String featureType,String featureName,String description, List<ICoordinate> coordinates, ICoordinateSystem coordinateSystem) {
		
		if (featureType == null || featureType ==""){
			
			featureType = "DefaultType";
		}
		if (featureName == null || featureName ==""){
			
			featureName = "DefaultName";
		}
		if (coordinates.size() > 1) {
			
			try {			
				
				AttributeType geom = AttributeTypeFactory.newAttributeType("GEOMETRY", LineString.class);
				
				AttributeType name = AttributeTypeFactory.newAttributeType("Name", java.lang.String.class);
				
				AttributeType desc = AttributeTypeFactory.newAttributeType("Description", java.lang.String.class);
				
				FeatureType newFeatureType = FeatureTypeFactory.newFeatureType(new AttributeType[] {geom, name, desc}, featureType);
				
				String coordinatesStr = "";
				
				for (int i = 0; i < coordinates.size(); i++) {
					
					coordinatesStr = coordinatesStr + coordinates.get(i).getX() + " " + coordinates.get(i).getY() + ","; 
				}
				coordinatesStr = coordinatesStr.substring(0, coordinatesStr.length() - 1);
				
				WKTReader wktReader = new WKTReader();
				
				LineString geometry = (LineString) wktReader.read("LINESTRING(" + coordinatesStr + ")");
				
				Feature newFeature = newFeatureType.create(new Object[] {geometry, featureName, description});
					
				if(coordinateSystem != null) {
				
					newFeature.getDefaultGeometry().setSRID(coordinateSystem.getEPSGCodeNo());
				}
				return (new SmpFeature(newFeature));
				
			} catch (Exception ex) {
				
				System.out.println("Error on creating Line feature");
				
				return null;
			}
		} else {
			
			System.out.println("Not enough coordinates for create line");
			
			return null;
		}
	}
	
	public IFeature polygonFeatureCreator(String featureType,String featureName,String description, List<ICoordinate> coordinates, ICoordinateSystem coordinateSystem) {
		
		if (featureType == null || featureType ==""){
			featureType = "DefaultType";
		}
		if (featureName == null || featureName ==""){
			featureName = "DefaultName";
		}
		if (coordinates.size() > 2) {
			
			try {			
				
				AttributeType geom = AttributeTypeFactory.newAttributeType("GEOMETRY", Polygon.class);
				AttributeType name = AttributeTypeFactory.newAttributeType("Name", java.lang.String.class);
				AttributeType desc = AttributeTypeFactory.newAttributeType("Description", java.lang.String.class);
				FeatureType newFeatureType = FeatureTypeFactory.newFeatureType(new AttributeType[] {geom, name, desc}, featureType);
				
				String coordinatesStr = "";
				
				for (int i = 0; i < coordinates.size(); i++) {
					
					coordinatesStr = coordinatesStr + coordinates.get(i).getX() + " " + coordinates.get(i).getY() + ","; 
				}
				coordinatesStr = coordinatesStr + coordinates.get(0).getX() + " " + coordinates.get(0).getY();
				
				WKTReader wktReader = new WKTReader();
				
				Polygon geometry = (Polygon) wktReader.read("POLYGON((" + coordinatesStr + "))");
				
				Feature newFeature = newFeatureType.create(new Object[] {geometry, featureName, description});
					
				if(coordinateSystem != null) {
				
					newFeature.getDefaultGeometry().setSRID(coordinateSystem.getEPSGCodeNo());
				}
				
				return (new SmpFeature(newFeature));
				
			} catch (Exception ex) {
				System.out.println("Error on creating Polygon feature");
				return null;
			}
		} else {
			System.out.println("Not enough coordinates for create polygon");
			return null;
		}
	}
	
	public IFeature cloneFeatureCreator(IFeature baseFeature) {
		
		try {
			
			return(new SmpFeature(((SmpFeature)baseFeature).getGeoToolFeature().getFeatureType().duplicate(((SmpFeature)baseFeature).getGeoToolFeature())));
		} catch (Exception ex) {

			System.out.println("Error on cloning feature !");
		}		
		return null;
	}
	
}
