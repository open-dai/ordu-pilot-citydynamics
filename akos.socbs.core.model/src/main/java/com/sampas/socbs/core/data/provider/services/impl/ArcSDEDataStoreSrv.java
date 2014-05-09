package com.sampas.socbs.core.data.provider.services.impl;

import java.util.Properties;
import org.geotools.data.DataStore;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.arcsde.impl.ArcSDEConnectionConfig;
import com.sampas.socbs.core.data.arcsde.impl.ArcSDEConnectionPool;
import com.sampas.socbs.core.data.arcsde.impl.ArcSDEConnectionPoolFactory;
import com.sampas.socbs.core.data.arcsde.impl.ArcSDEDataStore;
import com.sampas.socbs.core.data.provider.services.IArcSDEDataStoreSrv;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;


public class ArcSDEDataStoreSrv implements IArcSDEDataStoreSrv {

	private DataStore dataStore = null;
	
	private ArcSDEDataStore arcSDEDataStore = null;
	
	private static final String ARCSDE_DEFAULT_MAX_ACTIVE = "3";
	
	private static final String ARCSDE_DEFAULT_MIN_IDLE = "1";
	
	private static final String ARCSDE_DEFAULT_TIMEOUT = "5000";
	

	public ArcSDEDataStoreSrv(String dbtype, String server, String port, String instance, String user, String pass) {

		try {
			
			Properties connectionProps = new Properties();
			
			connectionProps.setProperty("dbtype", dbtype);
			
			connectionProps.setProperty("server", server);
			
			connectionProps.setProperty("port", port);
			
			connectionProps.setProperty("instance", instance);
			
			connectionProps.setProperty("user", user);
			
			connectionProps.setProperty("pass", pass);
			
			connectionProps.setProperty("pool.minConnections", ARCSDE_DEFAULT_MIN_IDLE);
			
			connectionProps.setProperty("pool.maxConnections", ARCSDE_DEFAULT_MAX_ACTIVE);
			
			connectionProps.setProperty("pool.timeOut", ARCSDE_DEFAULT_TIMEOUT);
			
			ArcSDEConnectionPoolFactory poolfactory = ArcSDEConnectionPoolFactory.getInstance();
			
	        ArcSDEConnectionConfig config = new ArcSDEConnectionConfig(connectionProps);
	        
	        ArcSDEConnectionPool connectionPool = poolfactory.createPool(config);
			
	        dataStore = new ArcSDEDataStore(connectionPool);
	        
	        arcSDEDataStore = (ArcSDEDataStore) dataStore;
	        
		} catch (Exception ex) {

			System.out.println("Error on creating ArcSDE Datastore ! ERROR: " + ex.getMessage());
		}
	}
	
	public IFeatureDataStore getArcSDEDataStore() {
						
		return (this.arcSDEDataStore);
	}

	public IFeatureCollection getFeaturesFromArcSDEDSByLayerName(String layerName) {
		// TODO Auto-generated method stub
		return null;
	}

}
