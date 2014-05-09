package com.sampas.socbs.core.processing.services.tests;

import junit.framework.TestCase;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.impl.SmpCoordinate;
import com.sampas.socbs.core.processing.services.IMeasureTools;
import com.sampas.socbs.core.processing.services.impl.SmpMeasureTools;

public class MeasureToolsJTests extends TestCase{
	
	public void testMeasureLenght() {
	
		ICoordinate coordinateOne = new  SmpCoordinate(28.958135884222617, 41.04257947213676);
	
		ICoordinate coordinateTwo = new  SmpCoordinate(28.958247173544997, 41.04259459884077);
	
		ICoordinateSystem coordinateSystem = new SmpCoordinateSystem("EPSG:4326"); 
		
		IMeasureTools measureTools = new SmpMeasureTools();
	
		double distanceAsMeter = -1;
		
		distanceAsMeter = measureTools.getDistance(coordinateOne, coordinateTwo, coordinateSystem);
	
		//Check results
		System.out.println("======================================================================");
		System.out.println("Test Adi : Measure Tools JUnit Tests - Lenght Test");
		System.out.println("Değerlendirilen Nokta 1 : 28.958135884222617, 41.04257947213676");
		System.out.println("Değerlendirilen Nokta 2 : 28.958247173544997, 41.04259459884077");
		System.out.println("CadCorp SDK sında Hesaplanan Uzaklık Metre Olarak : 12.4225814593581");
		System.out.println("Hesaplanan Uzaklık Metre Olarak : " + distanceAsMeter + " metre");
		System.out.println("**********************************************************************" + "\n");
		////////////////////////////////////////////////////////////////////////////////////////////
	
		assertNotSame(-1, distanceAsMeter);
	
	}
	
}
