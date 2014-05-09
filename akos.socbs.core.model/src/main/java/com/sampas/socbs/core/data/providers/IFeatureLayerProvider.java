package com.sampas.socbs.core.data.providers;

import java.util.List;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.ILayersNameAndPD_CRS;

public interface IFeatureLayerProvider {
	
	public ILayerNames getLayerNames(IFeatureDataStore dataStore);
	
	public List<IFeatureLayer> getLayers(IFeatureDataStore dataStore);
	
	public List<IFeatureLayer> getLayers(IFeatureDataStore dataStore, ILayerNames layerNames);
	
	public List<IFeatureLayer> getLayers(IFeatureDataStore dataStore, ILayersNameAndPD_CRS layersNameAndPD_CRS);

}
