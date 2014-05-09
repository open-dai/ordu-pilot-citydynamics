package com.sampas.socbs.core.data.providers.impl;

import java.util.List;

import com.sampas.socbs.core.data.IWMSDataStore;
import com.sampas.socbs.core.data.providers.IWMSLayerProvider;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.IWMSLayer;

public class SmpWMSLayerProviderSrv implements IWMSLayerProvider {

	public List<IWMSLayer> getLayers(IWMSDataStore dataProvider) {

		List<IWMSLayer> wmsLayers = dataProvider.getLayerDefinitions();
		
		for (int i = 0; i < wmsLayers.size(); i++) {
			
			wmsLayers.get(i).setWMSDataProvider(dataProvider);
		}
		
		return(wmsLayers);
	}
	
	public List<IWMSLayer> getLayers(IWMSDataStore dataProvider, ILayerNames layerNames) {

		List<IWMSLayer> wmsLayers = dataProvider.getLayerDefinitions(layerNames);
		
		for (int i = 0; i < wmsLayers.size(); i++) {
			
			wmsLayers.get(i).setWMSDataProvider(dataProvider);
		}
		
		return(wmsLayers);
	}

}
