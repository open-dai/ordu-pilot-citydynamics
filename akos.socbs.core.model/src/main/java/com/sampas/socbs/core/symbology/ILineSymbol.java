package com.sampas.socbs.core.symbology;

import com.sampas.socbs.core.tools.IColor;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:43
 */
public interface ILineSymbol extends ISymbol {
	
	/*
	 * Line symbol color.
	 */
	public void setColor(IColor pColor);
	/*
	 * Line symbol color.
	 */
	public IColor getColor();
	/*
	 * Line symbol width.
	 */
	public void setWidth(float width);
	/*
	 * Line symbol width.
	 */
	public float getWidth();
	
	public void setPointSymbol(IPointSymbol pointSymbol);
	
	public IPointSymbol getPointSymbol();
	
	public void setDashline(float[] dashline);
	
	public float[] getDashline();
	
	public boolean isUseDash();


}