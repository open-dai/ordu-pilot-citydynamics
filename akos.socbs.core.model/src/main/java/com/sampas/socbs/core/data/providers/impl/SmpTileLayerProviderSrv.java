package com.sampas.socbs.core.data.providers.impl;

import java.util.List;

import com.sampas.socbs.core.data.ITileDataStore;
import com.sampas.socbs.core.data.providers.ITileLayerProvider;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.ITileLayer;

public class SmpTileLayerProviderSrv implements ITileLayerProvider {

	public List<ITileLayer> getLayers(ITileDataStore tileDataStore) {
		
		List<ITileLayer> tileLayers = tileDataStore.getLayerDefinitions();
		
		for (int i = 0; i < tileLayers.size(); i++) {
			
			tileLayers.get(i).setTileDataProvider(tileDataStore);
		}
		
		return(tileLayers);
	}

	public List<ITileLayer> getLayers(ITileDataStore tileDataStore, ILayerNames layerNames) {
		
		List<ITileLayer> tileLayers = tileDataStore.getLayerDefinitions(layerNames);
		
		for (int i = 0; i < tileLayers.size(); i++) {
			
			tileLayers.get(i).setTileDataProvider(tileDataStore);
		}
		
		return(tileLayers);
	}

}
