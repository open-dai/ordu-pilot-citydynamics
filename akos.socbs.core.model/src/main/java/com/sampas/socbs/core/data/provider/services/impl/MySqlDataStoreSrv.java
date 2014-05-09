package com.sampas.socbs.core.data.provider.services.impl;

import org.geotools.data.DataStore;
import org.geotools.data.jdbc.datasource.ManageableDataSource;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.mysql.impl.MySQLDataStore;
import com.sampas.socbs.core.data.mysql.impl.MySQLDataStoreFactory;
import com.sampas.socbs.core.data.provider.services.IMySqlDataStoreSrv;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;


public class MySqlDataStoreSrv implements IMySqlDataStoreSrv {

	private DataStore dataStore = null;
	
	private MySQLDataStore mySqlDataStore = null;
	
	private static final int MYSQL_DEFAULT_MAX_ACTIVE = 20;
	
	private static final int MYSQL_DEFAULT_MIN_IDLE = 2;
	
	private static final boolean MYSQL_DEFAULT_VALIDATION = false;
	

	public MySqlDataStoreSrv(String nameSpace, String host, int port, String database, String user, String pass) {
		
	    MySQLDataStore dStore;
	    
	    ManageableDataSource connPool;
	    
	    try {
			
	        connPool = MySQLDataStoreFactory.getDefaultDataSource(host, user, pass, port, database, MYSQL_DEFAULT_MAX_ACTIVE, MYSQL_DEFAULT_MIN_IDLE, MYSQL_DEFAULT_VALIDATION);
	        
	        dStore = new MySQLDataStore(connPool, nameSpace);
	        
	        dStore.setWKBEnabled(true);
	        
	        this.dataStore = (MySQLDataStore)dStore;
	        
	        this.mySqlDataStore = (MySQLDataStore) this.dataStore;
		} catch (Exception ex) {
			
			System.out.println("Error on creating MYSQL dataStore ! ERROR: " + ex.getMessage());
		}
	}

	public IFeatureDataStore getMySqlDataStore() {

		return this.mySqlDataStore; 
	}
	
	public IFeatureCollection getFeaturesFromMySqlDSByLayerName(String layerName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
