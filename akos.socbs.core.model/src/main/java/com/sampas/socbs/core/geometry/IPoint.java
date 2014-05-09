package com.sampas.socbs.core.geometry;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;

public interface IPoint {
	
	public double getX();
	
	public double getY();
	
	public double getZ();
	
	public ICoordinateSystem getCoordinateSystem();
	
}
