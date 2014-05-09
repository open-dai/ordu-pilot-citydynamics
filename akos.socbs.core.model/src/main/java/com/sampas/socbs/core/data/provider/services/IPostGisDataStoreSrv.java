package com.sampas.socbs.core.data.provider.services;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;


public interface IPostGisDataStoreSrv {

	public IFeatureDataStore getPostGisDataStore();

	public IFeatureCollection getFeaturesFromPostGisDSByLayerName(String layerName);
	
}
