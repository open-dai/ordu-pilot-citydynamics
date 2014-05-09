package com.sampas.socbs.core.mapview;

import com.sampas.socbs.core.symbology.ISymbol;

/**
 * There are typically many LegendEntity objects in a LegendGroup. Each legend
 * entity contains a symbol and descriptive text strings form of a Label and
 * Description. The Label appears in the table of contents, and the label and
 * description can appear in the legend.
 * 
 * If a legend group is editable, then the symbol and label for the label
 * classes contained by the legend group can be edited in the table of contents.
 * Changes to symbols symbol will change the renderer's symbology and the map
 * will refresh to reflect this change. Changes to labels will change the
 * content of any legends that contain the layer.
 * 
 * The typically route for modifying renderer appeance through code is to go to
 * the renderer objects themselves. For example, to change the simple renderer
 * symbol, label and description fields, use the methods and properties of
 * ISimpleRenderer in preference to the legend entity object.
 * 
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface ILegendEntity {

	public String getDescription();

	public String getLabel();

	public ISymbol getSymbol();

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description);

	/**
	 * 
	 * @param label
	 */
	public void setLabel(String label);

	/**
	 * 
	 * @param symbol
	 */
	public void setSymbol(ISymbol symbol);

}