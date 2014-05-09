package com.sampas.socbs.core.mapview;

import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.symbology.ISymbol;
import com.sampas.socbs.core.tools.IColor;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:44
 */
public interface IMapNorthArrow extends IMapItem {

	public double getAngle();

	/**
	 * 
	 * @param angle
	 */
	public void getAngle(double angle);

	public IColor getColor();

	public IPoint getLocation();

	public ISymbol getNorthSymbol();

	public double getSize();

	/**
	 * 
	 * @param color
	 */
	public void setColor(IColor color);

	/**
	 * 
	 * @param point
	 */
	public void setLocation(IPoint point);

	/**
	 * 
	 * @param northSymbol
	 */
	public void setNorthSymbol(ISymbol northSymbol);

	/**
	 * 
	 * @param size
	 */
	public void setSize(double size);

}