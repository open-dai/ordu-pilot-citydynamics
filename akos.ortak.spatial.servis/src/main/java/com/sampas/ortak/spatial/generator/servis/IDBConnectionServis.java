package com.sampas.ortak.spatial.generator.servis;

import com.sampas.ortak.spatial.generator.model.DBConnection;
import com.sampas.socbs.core.data.IFeatureDataStore;


public interface IDBConnectionServis {

	public DBConnection getDbConnection();
	
	public IFeatureDataStore getFeatureDataStore();
}
