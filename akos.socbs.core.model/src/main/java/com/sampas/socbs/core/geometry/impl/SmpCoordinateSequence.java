package com.sampas.socbs.core.geometry.impl;

import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.ICoordinateSequence;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.vividsolutions.jts.geom.CoordinateSequence;

public class SmpCoordinateSequence implements ICoordinateSequence {
	
	private CoordinateSequence geoToolsCoordinateSequence = null;
	
	int X = 0;
	int Y = 1;
	int Z = 2;
	int M = 3;

	public SmpCoordinateSequence() {

	}

	public SmpCoordinateSequence(CoordinateSequence geoToolsCoordinateSequence) {

		this.geoToolsCoordinateSequence = geoToolsCoordinateSequence;
	}

	public CoordinateSequence getGeotoolsCoordinateSequence() {
		
		return (this.geoToolsCoordinateSequence);
	}

	  public int getDimension() {
		  
		  return (this.geoToolsCoordinateSequence.getDimension());
	  }
	  
	  public ICoordinate getCoordinate(int i) {
		  
		  ICoordinate smpCoordinate = new SmpCoordinate(this.geoToolsCoordinateSequence.getCoordinate(i));
		 
		  return (smpCoordinate);
	  }

	  public ICoordinate getCoordinateCopy(int i) {
		  
		  ICoordinate smpCoordinate = new SmpCoordinate(this.geoToolsCoordinateSequence.getCoordinate(i));
		 
		  return (smpCoordinate);
	  }
	  


	  public void getCoordinate(int index, SmpCoordinate smpCoordinate) {
  
		  this.geoToolsCoordinateSequence.getCoordinate(index, smpCoordinate.getGeoToolsCoordinate());
	  }

	  public double getX(int index) {
		  
		  return (this.geoToolsCoordinateSequence.getX(index));
	  }

	  public double getY(int index) {
		  
		  return (this.geoToolsCoordinateSequence.getY(index));
	  }

	  public double getOrdinate(int index, int ordinateIndex) {
		  
		  return (this.getOrdinate(index, ordinateIndex));
	  }

	  public int size() {
		  
		  return (this.size());
	  }

	  public void setOrdinate(int index, int ordinateIndex, double value) {
		  
		  this.geoToolsCoordinateSequence.setOrdinate(index, ordinateIndex, value);
	  }

	  public ICoordinate[] toCoordinateArray() {
		  
		  ICoordinate[] coordinates = new ICoordinate[this.geoToolsCoordinateSequence.size()];
		  
		  for (int i = 0; i < coordinates.length; i++) {
			
			  coordinates[i] = (ICoordinate) this.geoToolsCoordinateSequence.getCoordinate(i);
		  }
		  
		  //SmpCoordinate[] smpCoordinates = (SmpCoordinate[])this.geoToolsCoordinateSequence.toCoordinateArray();
		  
		  return (coordinates);
	  }

	  public IEnvelope expandEnvelope(IEnvelope smpEnvelope) {
		  
		  return (this.expandEnvelope(smpEnvelope));		  
	  }

	  
	public Object clone() {
		  
		  return (this.geoToolsCoordinateSequence.clone());
	  }

	
}
