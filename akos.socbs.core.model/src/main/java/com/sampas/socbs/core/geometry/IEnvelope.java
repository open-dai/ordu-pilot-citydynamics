package com.sampas.socbs.core.geometry;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:41
 */
public interface IEnvelope {
	
	public double getMinX();
	
	public double getMinY();
	
	public double getMaxX();
	
	public double getMaxY();
	
	public double getWidth();
	
	public double getHeight();	
	
	public IEnvelope cloneEnvelope();
	
}