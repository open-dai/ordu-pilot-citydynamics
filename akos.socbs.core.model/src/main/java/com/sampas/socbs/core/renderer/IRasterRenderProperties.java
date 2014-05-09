package com.sampas.socbs.core.renderer;

import com.sampas.socbs.core.tools.IColor;


/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:44
 */
public interface IRasterRenderProperties {

	public int getBrightnessValue();

	public int getContrastValue();

	public int getTransparencyValue();

	public IColor getVoidDataColor();

	/**
	 * 
	 * @param value
	 */
	public void setBrightnessValue(int value);

	/**
	 * 
	 * @param value
	 */
	public void setContrastValue(int value);

	/**
	 * 
	 * @param value
	 */
	public void setTransparencyValue(int value);

	/**
	 * 
	 * @param color
	 */
	public void setVoidDataColor(IColor color);

}