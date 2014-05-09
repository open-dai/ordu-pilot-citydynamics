package com.sampas.socbs.core.data.provider.services.impl;

import org.geotools.data.DataStore;
import org.geotools.data.jdbc.JDBCDataStoreConfig;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.db2.impl.DB2DataStore;
import com.sampas.socbs.core.data.provider.services.IDB2DataStoreSrv;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;


public class DB2DataStoreSrv implements IDB2DataStoreSrv {

	private DataStore dataStore = null;
	
	private DB2DataStore db2DataStore = null;
	
	
	public DB2DataStoreSrv(String tabSchema, String db2DBURL, long cacheTimeOut) {
		
        JDBCDataStoreConfig config = new JDBCDataStoreConfig(tabSchema, tabSchema, cacheTimeOut);

        try {
			this.dataStore = new DB2DataStore(null, config, db2DBURL);
			
			this.db2DataStore = (DB2DataStore)dataStore;
			
		} catch (Exception ex) {

			System.out.println("Error on creatind DB2 Datastore ! ERROR: " + ex.getMessage());
		}
	}
	
	public IFeatureDataStore getDB2DataStore() {
		
		return (this.db2DataStore);
	}

	public IFeatureCollection getFeaturesFromDB2DSByLayerName(String layerName) {
		// TODO Auto-generated method stub
		return null;
	}

}
