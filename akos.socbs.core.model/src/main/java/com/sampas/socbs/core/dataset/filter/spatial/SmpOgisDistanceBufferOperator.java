package com.sampas.socbs.core.dataset.filter.spatial;

import org.opengis.filter.spatial.DistanceBufferOperator;

public class SmpOgisDistanceBufferOperator extends SmpOgisBinarySpatialOperator {

	private DistanceBufferOperator openGisDistanceBufferOperator = null;

	public SmpOgisDistanceBufferOperator() {

	}

	public SmpOgisDistanceBufferOperator(DistanceBufferOperator openGisDistanceBufferOperator) {

		this.openGisDistanceBufferOperator = openGisDistanceBufferOperator;
	}

	public DistanceBufferOperator getOpenGisDistanceBufferOperator() {
		
		return (this.openGisDistanceBufferOperator);
	}
	
	public double getDistance() {
		
		return (this.openGisDistanceBufferOperator.getDistance());		
	}

    public String getDistanceUnits() {
    	
    	return (this.openGisDistanceBufferOperator.getDistanceUnits());
    }
}
