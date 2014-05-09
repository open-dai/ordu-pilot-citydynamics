package com.sampas.socbs.core.data.impl;

import java.awt.image.BufferedImage;
import java.util.List;

import com.sampas.socbs.core.data.ITileDataStore;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.maplayer.ITileLayer;
import com.sampas.socbs.core.symbology.IStyle;
import com.sampas.socbs.core.tile.impl.TileDataStore.TileImageReturnType;
import com.sampas.socbs.core.tile.toolbox.SRS;

public abstract class ATileDataProvider implements ITileDataStore {
	
	public abstract BufferedImage getTileWithTileLocation(List<ITileLayer> tileLayers, SRS layerSRS, List<IStyle> style,  int tileNoX, int tileNoY, int layerZoomLevel, IDimension tileDimension, TileImageReturnType returnType);

}
