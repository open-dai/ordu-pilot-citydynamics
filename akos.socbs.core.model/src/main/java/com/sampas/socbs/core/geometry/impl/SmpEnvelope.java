package com.sampas.socbs.core.geometry.impl;

import com.sampas.socbs.core.geometry.IEnvelope;
import com.vividsolutions.jts.geom.Envelope;

public class SmpEnvelope implements IEnvelope {

	private Envelope geoToolsEnvelope = null;
	
	public SmpEnvelope(double minX, double minY, double maxX, double maxY) {
		
		this.geoToolsEnvelope = new Envelope(minX, maxX, minY, maxY);
	}

	public SmpEnvelope() {
		
	}
	
	public Envelope getGeoToolsEnvelope() {
		
		return (this.geoToolsEnvelope);
	}

	
	public double getMaxX() {

		return (this.geoToolsEnvelope.getMaxX());
	}

	
	public double getMaxY() {

		return (this.geoToolsEnvelope.getMaxY());
	}

	
	public double getMinX() {

		return (this.geoToolsEnvelope.getMinX());
	}

	
	public double getMinY() {


		return (this.geoToolsEnvelope.getMinY());
	}

	
	public double getHeight() {
		
		return (Math.abs(this.geoToolsEnvelope.getMaxY() - this.geoToolsEnvelope.getMinY()));
	}

	
	public double getWidth() {

		return (this.geoToolsEnvelope.getMaxX() - this.geoToolsEnvelope.getMinX());
	}

	public IEnvelope cloneEnvelope() {

		return (new SmpEnvelope(this.getMinX(),this.getMinY(),this.getMaxX(),this.getMaxY()));
	}
}
