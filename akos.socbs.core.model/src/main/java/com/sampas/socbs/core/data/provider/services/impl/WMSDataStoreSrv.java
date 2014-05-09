package com.sampas.socbs.core.data.provider.services.impl;

import java.net.URL;

import com.sampas.socbs.core.data.wms.impl.WMSDataStore;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsGMLVersion;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsVersion;

public class WMSDataStoreSrv {

	private WMSDataStore wmsDataStore = null;
	private URL wmsUrl;

	public WMSDataStoreSrv(URL wmsUrl, WmsVersion wmsVersion, WmsGMLVersion wmsGMLVersion) {
		
		try {
			this.wmsUrl = wmsUrl;
			wmsDataStore = new WMSDataStore(wmsUrl, wmsVersion, wmsGMLVersion);
			
		} catch (Exception ex) {

			System.out.println("Error on creating WMS Data Store ! ERROR : " + ex);
		}
	}
	
	public WMSDataStore getWmsDataStore() {
		return wmsDataStore;
	}

	public void setWmsUrl(URL wmsUrl) {
		this.wmsUrl = wmsUrl;
	}

	public URL getWmsUrl() {
		return wmsUrl;
	}
	
	
	
}
