package com.sampas.core.dataset.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpCoordinate;
import com.sampas.socbs.core.geometry.impl.SmpPoint;
import com.sampas.socbs.core.processing.services.impl.SmpFeatureCreator;


public class FeatureCreationJTests extends TestCase {

	public void testPointCreation() {
		
		ICoordinateSystem coordinateSystem = new SmpCoordinateSystem("EPSG:2320");
		
		IPoint smpPoint =new SmpPoint(10,20,coordinateSystem);
		
		assertNotSame(null, smpPoint);
		
	}
	
	public void testPolygonCreation() {
		
		List<ICoordinate> coordinates = new ArrayList<ICoordinate>();
		
		coordinates.add(new SmpCoordinate(3,5));
		coordinates.add(new SmpCoordinate(5,10));
		coordinates.add(new SmpCoordinate(9,4));
		coordinates.add(new SmpCoordinate(7,1));
		
		ICoordinateSystem coordinateSystem = new SmpCoordinateSystem("EPSG:2320");
		
		SmpFeatureCreator featureCreater = new SmpFeatureCreator();
		
		IFeature newFeature = featureCreater.polygonFeatureCreator("unknownType", "testFeature", "description", coordinates, coordinateSystem);
		
		////////////////////////////////////////////////////////////////////////////////////////////
		//Check results
		System.out.println("======================================================================");
		System.out.println("Test Adi : Feature Creation JUnit Test");
		for (int i = 0; i < newFeature.getDefaultGeometry().getCoordinates().length; i++) {
			
			System.out.print("Oluşan Poligonun noktaları   X: " + newFeature.getDefaultGeometry().getCoordinates()[i].getX() + " Y: ");
			System.out.println(newFeature.getDefaultGeometry().getCoordinates()[i].getY());
		}
		System.out.println("**********************************************************************" + "\n");
		////////////////////////////////////////////////////////////////////////////////////////////
		
		assertNotSame(null, newFeature);
		
	}
	
	public void testCloneFeature() {
		
		List<ICoordinate> coordinates = new ArrayList<ICoordinate>();
		coordinates.add(new SmpCoordinate(3,5));
		coordinates.add(new SmpCoordinate(5,10));
		coordinates.add(new SmpCoordinate(9,4));
		coordinates.add(new SmpCoordinate(7,1));
		
		ICoordinateSystem coordinateSystem = new SmpCoordinateSystem("EPSG:2320");
		
		SmpFeatureCreator featureCreater = new SmpFeatureCreator();
		
		IFeature baseFeature = featureCreater.polygonFeatureCreator("unknownType", "testFeature", "description", coordinates, coordinateSystem);
		
		IFeature cloneFeature = baseFeature.cloneFeature();
		
		try {
			
			baseFeature.setAttribute(1, "newValue");
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		assertNotSame(baseFeature.getAttribute(1), cloneFeature.getAttribute(1));
	}
	
}
