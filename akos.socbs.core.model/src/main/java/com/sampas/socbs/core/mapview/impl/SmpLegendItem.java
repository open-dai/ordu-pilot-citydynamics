package com.sampas.socbs.core.mapview.impl;

import com.sampas.socbs.core.maplayer.ILayer;
import com.sampas.socbs.core.mapview.ILegendItem;
import com.sampas.socbs.core.symbology.ISymbol;
import com.sampas.socbs.core.symbology.ITextSymbol;

public class SmpLegendItem implements ILegendItem {

	private boolean showLayerName;
	private boolean showLabels;
	private boolean showHeading;
	private boolean showDescriptions;
	private ITextSymbol layerNameSymbol;
	private ILayer layer;
	private ISymbol headingSymbol;
	private int iconWidth;
	private int iconHeight;

	public boolean IsCanDisplay(ILayer layer) {

		return false;
	}

	public boolean IsShowDescriptions() {

		return this.showDescriptions;
	}

	public boolean IsShowHeading() {

		return this.showHeading;
	}

	public boolean IsShowLabels() {

		return this.showLabels;
	}

	public boolean IsShowLayerName() {

		return this.showLayerName;
	}

	public ISymbol getHeadingSymbol() {
		
		return this.headingSymbol;
	}

	public ILayer getLayer() {

		return this.layer;
	}

	public ITextSymbol getLayerNameSymbol() {

		return this.layerNameSymbol;
	}

	public void setHeadingSymbol(ISymbol symbol) {

		this.headingSymbol = symbol;
	}

	public void setLayer(ILayer layer) {

		this.layer = layer;
	}

	public void setLayerNameSymbol(ITextSymbol textSymbol) {

		this.layerNameSymbol = textSymbol; 
	}

	public void setShowDescriptions(boolean show) {

		this.showDescriptions = show;
	}

	public void setShowHeading(boolean show) {

		this.showHeading = show;
	}

	public void setShowLabels(boolean show) {
		
		this.showLabels = show;

	}

	public void setShowLayerName(boolean show) {

		this.showLayerName = show;

	}
	
	public void setIconWidth(int iconWidth) {
		this.iconWidth = iconWidth;
	}

	public int getIconWidth() {
		return iconWidth;
	}

	public void setIconHeight(int iconHeight) {
		this.iconHeight = iconHeight;
	}

	public int getIconHeight() {
		return iconHeight;
	}

}
