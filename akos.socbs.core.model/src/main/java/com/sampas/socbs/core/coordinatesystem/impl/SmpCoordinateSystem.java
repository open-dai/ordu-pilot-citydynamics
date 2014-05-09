package com.sampas.socbs.core.coordinatesystem.impl;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;

public class SmpCoordinateSystem implements ICoordinateSystem {

	private String coordinateSystem = "";
	
	public SmpCoordinateSystem(String epsgCode) {
		
		this.coordinateSystem = epsgCode;
	}
	
	public String getEPSGCode() {
		
		return (this.coordinateSystem);
	}
	
	public int getEPSGCodeNo() {
		
		int dPointPlace = this.coordinateSystem.indexOf(":");
		String intStr = this.coordinateSystem.substring(dPointPlace + 1);
		int EPSG = 0;
		try
		{
			EPSG = Integer.parseInt(intStr);
		}
		catch (Exception ex)
		{ 
			return EPSG;
		}
		
		return (EPSG);
	}

}
