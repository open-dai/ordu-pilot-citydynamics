package com.sampas.socbs.core.data.providers;

import java.util.List;

import com.sampas.socbs.core.data.IWMSDataStore;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.IWMSLayer;

public interface IWMSLayerProvider {
	
	public List<IWMSLayer> getLayers(IWMSDataStore dataStore);
	
	public List<IWMSLayer> getLayers(IWMSDataStore dataProvider, ILayerNames layerNames);

}
