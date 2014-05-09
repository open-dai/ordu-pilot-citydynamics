package com.sampas.socbs.core.data.providers;

import java.util.List;

import com.sampas.socbs.core.data.ITileDataStore;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.ITileLayer;

public interface ITileLayerProvider {
	
	public List<ITileLayer> getLayers(ITileDataStore tileDataStore);
	
	public List<ITileLayer> getLayers(ITileDataStore tileDataStore, ILayerNames layerNames);

}
