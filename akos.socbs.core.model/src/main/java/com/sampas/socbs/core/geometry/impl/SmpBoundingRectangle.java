package com.sampas.socbs.core.geometry.impl;

import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.Envelope2D;

import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IEnvelope;

public class SmpBoundingRectangle implements IEnvelope{
	
	private Envelope2D envelope2D = null;

	public SmpBoundingRectangle(double xMin, double yMin, double xMax, double yMax) {
		
		this.envelope2D = new Envelope2D(new DirectPosition2D(xMin, yMin), new DirectPosition2D(xMax, yMax));
	}
	
	public SmpBoundingRectangle(ICoordinate coordinateMin, ICoordinate coordinateMax){
		
		this.envelope2D = new Envelope2D(
						new DirectPosition2D(coordinateMin.getX(),coordinateMin.getY()),
						new DirectPosition2D(coordinateMax.getX(),coordinateMax.getY()) );
	}
	
	public double getMaxX() {
		
		return (this.envelope2D.getMaxX());
	}

	
	public double getMaxY() {
		
		return (this.envelope2D.getMaxY());
	}

	
	public double getMinX() {

		return (this.envelope2D.getMinX());
	}

	
	public double getMinY() {

		return (this.envelope2D.getMinY());
	}
	
	
	public double getHeight() {
		
		return (this.envelope2D.getHeight());
	}

	
	public double getWidth() {

		return (this.envelope2D.getWidth());
	}

	public IEnvelope cloneEnvelope() {

		return new SmpBoundingRectangle(this.getMinX(),this.getMinY(),this.getMaxX(),this.getMaxY());
	}


}

