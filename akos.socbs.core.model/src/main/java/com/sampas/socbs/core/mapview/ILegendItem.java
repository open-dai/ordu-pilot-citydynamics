package com.sampas.socbs.core.mapview;

import com.sampas.socbs.core.maplayer.ILayer;
import com.sampas.socbs.core.symbology.ISymbol;
import com.sampas.socbs.core.symbology.ITextSymbol;

/**
 * The Legend can be seen as a collection of map layers each layer being
 * represented by a LegendItem. The ILegendItem interface controls all the
 * properties of a legend item:
 * 
 * CanDisplay will be true if the type of the LegendItem is compatible with the
 * rendering of the layer. NestedLegendItem for instance, cannot be used by all
 * renderers.
 * 
 * The Layer property returns the layer this LegendItem is associated with
 * 
 * Columns is the number of columns it should span. Height and Width control the
 * size of the legend item.
 * 
 * ILegendItem also gives access to the graphic elements used to draw the legend
 * item - Graphics property - to the symbols specific to this legend item -
 * HeadingSymbol, LayerNameSymbol - and to the legend items LegendClassFormat
 * object.
 * 
 * NewColumn controls whether the LegendItem should be displayed starting a new
 * column. KeepTogether indicates if it can be splitted over different columns.
 * A number of properties control and whether the label, description, heading,
 * and layer name should be displayed.
 * 
 * Horizontal and Vertical legend items use the esriLegendItemArrangement
 * enumeration which can be set with IHorizontalLegendItem::Arrangement and
 * IVerticalLegendItem::Arrangement to specify the position of the label, patch,
 * and description. The default is esriPatchLabelDescription, which translates
 * to the patch on the far left, label to the right of the patch, then the
 * description, if available, on the far right. There are currently four types
 * of legend items: HorizontalLegendItem, VerticalLegendItem,
 * HorizontalBarLegendItem, and NestedLegendItem with corresponding interfaces.
 * 
 * @version 1.0
 * @created 07-Kas-2008 13:35:43
 */
public interface ILegendItem {

	public ISymbol getHeadingSymbol();

	public ILayer getLayer();

	public ITextSymbol getLayerNameSymbol();

	/**
	 * 
	 * @param layer
	 */
	public boolean IsCanDisplay(ILayer layer);

	public boolean IsShowDescriptions();

	public boolean IsShowHeading();

	public boolean IsShowLabels();

	public boolean IsShowLayerName();

	/**
	 * 
	 * @param symbol
	 */
	public void setHeadingSymbol(ISymbol symbol);

	/**
	 * 
	 * @param layer
	 */
	public void setLayer(ILayer layer);

	/**
	 * 
	 * @param textSymbol
	 */
	public void setLayerNameSymbol(ITextSymbol textSymbol);

	/**
	 * 
	 * @param show
	 */
	public void setShowDescriptions(boolean show);

	/**
	 * 
	 * @param show
	 */
	public void setShowHeading(boolean show);

	/**
	 * 
	 * @param show
	 */
	public void setShowLabels(boolean show);

	/**
	 * 
	 * @param show
	 */
	public void setShowLayerName(boolean show);

	public void setIconWidth(int iconWidth);

	public int getIconWidth();

	public void setIconHeight(int iconHeight);

	public int getIconHeight();
}