package com.sampas.socbs.core.geometry;

import com.sampas.socbs.core.geometry.impl.SmpCoordinate;

public interface ICoordinateSequence {
	
	  public int getDimension();

	  public ICoordinate getCoordinate(int i);

	  public ICoordinate getCoordinateCopy(int i);

	  public void getCoordinate(int index, SmpCoordinate smpCoordinate); 
	  
	  public double getX(int index);

	  public double getY(int index);

	  public int size();
	  
	  public ICoordinate[] toCoordinateArray(); 
	  
	  public IEnvelope expandEnvelope(IEnvelope smpEnvelope);
	  
}
