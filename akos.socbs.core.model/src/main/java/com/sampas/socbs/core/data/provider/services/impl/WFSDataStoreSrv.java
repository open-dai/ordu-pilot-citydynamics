package com.sampas.socbs.core.data.provider.services.impl;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.wfs.impl.WFSDataStore;
import com.sampas.socbs.core.data.wfs.impl.WFSDataStoreFactory;
import com.sampas.socbs.core.data.wfs.impl.WFSDataStore.WfsGMLVersion;
import com.sampas.socbs.core.data.wfs.impl.WFSDataStore.WfsVersion;

public class WFSDataStoreSrv {

	@SuppressWarnings("unchecked")
	private Map connectionParameters = new HashMap();

	private WFSDataStoreFactory wfsDataStoreFactory = new WFSDataStoreFactory();

	private WFSDataStore wfsDataStore = null;
	
	private URL wfsUrl = null;
	
	private String wfsVersion = null;
	
	@SuppressWarnings("unused")
	private WfsGMLVersion wfsGMLVersion = null;
	
	public WFSDataStoreSrv(URL wfsUrl, WfsGMLVersion wfsGMLVersion, WfsVersion wfsVersion) {
	
		this.wfsUrl = wfsUrl;
		this.wfsGMLVersion = wfsGMLVersion;
		
		if (wfsVersion.toString().compareTo("ver100") == 0) {
			this.wfsVersion = "1.0.0";
		} else if (wfsVersion.toString().compareTo("ver110") == 0){
			this.wfsVersion = "1.1.0";
		}

		try {

			if (SetConnectionParameters()) {
				
				this.wfsDataStore = (WFSDataStore) wfsDataStoreFactory.createNewDataStore(connectionParameters);
			} else {

			}
		} catch (Exception e) {
			
			//TODO Loging mechanizm must activate
			System.out.println("Error on creating data store !");
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean SetConnectionParameters() {
		try {

			// String getCapabilities = wfsUrl + "?REQUEST=GetCapabilities";
			// connectionParameters.put("WFSDataStoreFactory:GET_CAPABILITIES_URL",
			// getCapabilities);
			//connectionParameters.put("VERSION", this.wfsVersion);
			
			connectionParameters.put("VERSION", this.wfsVersion);
			connectionParameters.put(WFSDataStoreFactory.URL.key, wfsUrl);
			connectionParameters.put(WFSDataStoreFactory.TIMEOUT.key, new Integer(10000));
			connectionParameters.put(WFSDataStoreFactory.USERNAME.key, "wfsadmin");
			connectionParameters.put(WFSDataStoreFactory.PASSWORD.key, "sampaswfsadmin321");
			return true;

		} catch (Exception e) {
			
			return false;
		}
	}
	
	public IFeatureDataStore getWfsDataStore() {
						
		return (this.wfsDataStore);
	}
	
	
	
}
