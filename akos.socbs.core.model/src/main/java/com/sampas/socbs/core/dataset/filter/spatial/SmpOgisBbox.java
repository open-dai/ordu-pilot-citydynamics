package com.sampas.socbs.core.dataset.filter.spatial;

import org.opengis.filter.spatial.BBOX;

public class SmpOgisBbox extends SmpOgisSpatialOperator {

	private BBOX openGisBbox = null;

	public SmpOgisBbox() {

	}

	public SmpOgisBbox(BBOX openGisBbox) {

		this.openGisBbox = openGisBbox;
	}

	public BBOX getOpenGisBbox() {
		
		return (this.openGisBbox);
	}
	
	public String getPropertyName() {
		
		return (this.openGisBbox.getPropertyName());
	}

	public String getSRS() {
		
		return (this.openGisBbox.getPropertyName());
	}

	public double getMinX() {
		
		return (this.openGisBbox.getMinX());
	}

	public double getMinY() {
		
		return (this.openGisBbox.getMinY());
	}

	public double getMaxX() {
		
		return (this.openGisBbox.getMaxX());
	}

	public double getMaxY() {
		
		return (this.openGisBbox.getMaxY());
	}
}
