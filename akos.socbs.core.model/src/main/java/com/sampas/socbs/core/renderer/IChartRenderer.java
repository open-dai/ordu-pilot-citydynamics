package com.sampas.socbs.core.renderer;

import com.sampas.socbs.core.symbology.IChartSymbol;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:40
 */
public interface IChartRenderer {

	public IChartSymbol getChartSymbol();

	/**
	 * 
	 * @param index
	 */
	public double getFieldTotal(int index);

	public String getLabel();

	/**
	 * 
	 * @param symbol
	 */
	public void setChartSymbol(IChartSymbol symbol);

	/**
	 * 
	 * @param index
	 * @param total
	 */
	public void setFieldTotal(int index, double total);

	/**
	 * 
	 * @param label
	 */
	public void setLabel(String label);

}