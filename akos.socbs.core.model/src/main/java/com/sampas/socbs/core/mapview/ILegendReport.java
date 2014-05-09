package com.sampas.socbs.core.mapview;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:43
 */
public interface ILegendReport {

	/**
	 * 
	 * @param index
	 */
	public ILegendEntityGroup getLegendGroup();

	public int getLegendGroupCount();

	public ILegendItem getLegendItem();

}