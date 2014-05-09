package com.sampas.socbs.core.data.provider.services;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;


public interface IDB2DataStoreSrv {

	public IFeatureDataStore getDB2DataStore();

	public IFeatureCollection getFeaturesFromDB2DSByLayerName(String layerName);
	
}
