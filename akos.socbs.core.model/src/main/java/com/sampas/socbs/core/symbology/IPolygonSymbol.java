package com.sampas.socbs.core.symbology;


import com.sampas.socbs.core.tools.IColor;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:44
 */
public interface IPolygonSymbol extends ISymbol {
	
	/*
	 * Line symbol of fill outline.
	 */
	public void setOutline(ILineSymbol pLineSymbol);
	/*
	 * Line symbol of fill outline.
	 */
	public ILineSymbol getOutline();
	/*
	 * 	Fill color.
	 */
	public IColor getColor();
	/*
	 * 	Fill color.
	 */
	public void setColor(IColor color);

	
}