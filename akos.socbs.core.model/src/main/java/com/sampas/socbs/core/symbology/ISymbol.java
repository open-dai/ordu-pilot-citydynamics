package com.sampas.socbs.core.symbology;

import com.sampas.socbs.core.coordinatesystem.ITransformation;
import com.sampas.socbs.core.display.IDevice;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.tools.IColor;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:45
 */
public interface ISymbol {

	/**
	 * 
	 * @param device
	 * @param transformation
	 * @param geometry
	 */
	public IEnvelope getBoundary(IDevice device, ITransformation transformation, IGeometry geometry);

	//public Style setupStyle(int type, Color color);
	
	public void removeDevice();

	/**
	 * 
	 * @param device
	 * @param transformation
	 */
	public void setDevice(IDevice device, ITransformation transformation);
	
	public IDevice getDevice();
	
	public boolean isUseDash();
	
	public float[] getDashline();
	
	public float getWidth();
	
	public IColor getColor();

	public void draw(double x, double y);

	public void draw(int x0, int y0, int x1, int y1);

	public void draw(int[] xpoints, int[] ypoints, int npoints);
	
}