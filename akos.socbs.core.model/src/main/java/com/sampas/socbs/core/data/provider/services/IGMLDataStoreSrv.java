package com.sampas.socbs.core.data.provider.services;

import java.io.File;
import java.io.InputStream;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;

public interface IGMLDataStoreSrv {

	public IFeatureDataStore getFileGMLDataStore();
	
	public IFeatureCollection getFeaturesFromGmlFile(File gmlFile);
	
	public IFeatureCollection getFeaturesFromGmlStream(InputStream gmlStream);
	
}
