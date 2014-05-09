package com.sampas.socbs.core.data;

import java.awt.image.BufferedImage;
import java.util.List;

import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.ITileLayer;
import com.sampas.socbs.core.symbology.IStyle;
import com.sampas.socbs.core.tile.impl.TileDataStore.TileImageReturnType;
import com.sampas.socbs.core.tile.toolbox.BBOX;
import com.sampas.socbs.core.tile.toolbox.SRS;

public interface ITileDataStore {

	public  List<ITileLayer> getLayerDefinitions();
	
	public  List<ITileLayer> getLayerDefinitions(ILayerNames layerNames);
	
	public BufferedImage getTileWithTileLocation(List<ITileLayer> tileLayers, SRS layerSRS, List<IStyle> styles, int tileNoX, int tileNoY, int layerZoomLevel, IDimension tileDimension, TileImageReturnType returnType);
		
	public int[] getTileBoundsFromBBox(ITileLayer tileLayer, SRS layerSRS, BBOX boundingBox, int zoomLevel);
	
}
