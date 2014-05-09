package com.sampas.socbs.core.geometry;

import com.sampas.socbs.core.geometry.impl.SmpCoordinate;


public interface ICoordinate {

	public void setCoordinate(SmpCoordinate smpCoordinate);
	public double getX();
	public double getY();
	public double getZ();

}
