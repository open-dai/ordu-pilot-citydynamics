package com.sampas.socbs.core.symbology;


/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:40
 */
public interface IChartSymbol extends ISymbol {

	/**
	 * 
	 * @param symbol
	 */
	public void addSymbol(ISymbol symbol);

	public void clearSymbols();


	public boolean getIsDisplayed3D();

	public double getMaxValue();

	/**
	 * 
	 * @param index
	 */
	public ISymbol getSymbol(int index);

	public int getSymbolCount();

	public double getThickness();

	public int getTilt();

	/**
	 * 
	 * @param index
	 */
	public double getValue(int index);

	/**
	 * 
	 * @param index
	 */
	public void removeSymbol(int index);
	/**
	 * 
	 * @param is3D
	 */
	public void setIsDisplayed3D(boolean is3D);

	/**
	 * 
	 * @param value
	 */
	public void setMaxValue(double value);

	/**
	 * 
	 * @param thickness
	 */
	public void setThickness(double thickness);

	/**
	 * 
	 * @param tilt
	 */
	public void setTilt(int tilt);

	/**
	 * 
	 * @param index
	 * @param value
	 */
	public void setValue(int index, double value);	

}