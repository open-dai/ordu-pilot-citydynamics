package com.sampas.socbs.core.mapview;

import com.sampas.socbs.core.maplayer.ILayer;
import com.sampas.socbs.core.symbology.IFillSymbol;
import com.sampas.socbs.core.symbology.ITextSymbol;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:44
 */
public interface IMapOverview extends IMapItem {

	public IFillSymbol getAOIFillSymbol();

	/**
	 * 
	 * @param gridLayerField
	 */
	public void getOverlayGridCell(int gridLayerField);

	public ITextSymbol getOverlayGridLabelSymbol();

	public ILayer getOverlayGridLayer();

	/**
	 * 
	 * @param aoiFillSymbol
	 */
	public void setAOIFillSymbol(IFillSymbol aoiFillSymbol);

	/**
	 * 
	 * @param overlayGridLabelSymbol
	 */
	public void setOverlayGridLabelSymbol(ITextSymbol overlayGridLabelSymbol);

	/**
	 * 
	 * @param overlayGridLayer
	 */
	public void setOverlayGridLayer(ILayer overlayGridLayer);

	/**
	 * 
	 * @param width
	 * @param height
	 */
	public void updateDisplay(int width, int height);

}