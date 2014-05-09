package com.sampas.socbs.core.data.provider.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.geotools.data.DataStore;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.postgis.impl.PostgisDataStore;
import com.sampas.socbs.core.data.postgis.impl.PostgisDataStoreFactory;
import com.sampas.socbs.core.data.provider.services.IPostGisDataStoreSrv;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;


@SuppressWarnings("unchecked")
public class PostGisDataStoreSrv implements IPostGisDataStoreSrv {

	private DataStore dataStore = null;
	
	private PostgisDataStore postGisDataStore = null;
	

	public PostGisDataStoreSrv(String host, int port, String database, String schema, String user, String passwd, boolean wkbEnabled, boolean looseBbox) {

	    Map params = new HashMap();
        params.put(PostgisDataStoreFactory.DBTYPE.key, "postgis");
        
        params.put(PostgisDataStoreFactory.HOST.key, host);
        
        params.put(PostgisDataStoreFactory.PORT.key, port);
        
        params.put(PostgisDataStoreFactory.SCHEMA.key, schema);
        
        params.put(PostgisDataStoreFactory.DATABASE.key, database);
        
        params.put(PostgisDataStoreFactory.USER.key, user);
        
        params.put(PostgisDataStoreFactory.PASSWD.key, passwd);        
        
        if (wkbEnabled) {
        	
            params.put(PostgisDataStoreFactory.WKBENABLED.key, wkbEnabled);
        }
        if (looseBbox) {
        	
            params.put(PostgisDataStoreFactory.LOOSEBBOX.key, looseBbox);
        }
        try {
        	
        	dataStore = new PostgisDataStoreFactory().createDataStore(params);
		} catch (IOException e) {
			
			e.printStackTrace();
			
			System.out.println("Error on Connecting PostGreSqlDB");
		}
		this.postGisDataStore = (PostgisDataStore) dataStore;
	}
	
	public IFeatureDataStore getPostGisDataStore() {
						
		return (this.postGisDataStore);
	}

	public IFeatureCollection getFeaturesFromPostGisDSByLayerName(String layerName) {
		// TODO Auto-generated method stub
		return null;
	}

}
