package com.sampas.socbs.core.data.provider.services;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;


public interface IArcSDEDataStoreSrv {

	public IFeatureDataStore getArcSDEDataStore();

	public IFeatureCollection getFeaturesFromArcSDEDSByLayerName(String layerName);
	
}
