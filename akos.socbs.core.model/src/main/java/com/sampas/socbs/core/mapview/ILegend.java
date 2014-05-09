package com.sampas.socbs.core.mapview;

/**
 * Use this interface to modify a legend and access its subparts.
 * 
 * A legend is mostly a collection of legend items, one for each layer of the
 * map. See the ILegendItem documentation for information about these. ILegend
 * provides access to and manages the legend?s items with the Item and ItemCount
 * properties as well as the AddItem, InsertItem, RemoveItem and ClearItems
 * methods.
 * 
 * The format of the legend is managed by the LegendFormat object. The Format
 * properties gives access to this object through the ILegendFormat interface.
 * ILegend also manages a few of the legend properties such as the Title . When
 * changing the properties of an existing legend, you must call Refresh to have
 * the changes reflected in the layout.
 * 
 * Finally the AutoAdd, AutoReorder and AutoVisibility properties are used to
 * synchronize the aspect and ordering of the legend with the TOC. The default
 * value for this property is True.
 * 
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface ILegend extends IMapItem {

	/**
	 * 
	 * @param legendItem
	 */
	public void addLegendItem(ILegendItem legendItem);

	/**
	 * 
	 * @param index
	 */
	public ILegendItem getLegendItem(int index);

	public int getLegendItemCount();
	
	public void clearLegendItemList();

	public String getLegendTitle();

	/**
	 * 
	 * @param index
	 * @param legendItem
	 */
	public void insertLegendItem(int index, ILegendItem legendItem);

	/**
	 * 
	 * @param index
	 */
	public void removeLegendItem(int index);

	/**
	 * 
	 * @param title
	 */
	public void setLegendTitle(String title);

}