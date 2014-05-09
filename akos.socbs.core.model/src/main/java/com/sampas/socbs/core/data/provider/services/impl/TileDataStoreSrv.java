package com.sampas.socbs.core.data.provider.services.impl;

import java.net.URL;

import com.sampas.socbs.core.tile.impl.TileDataStore;
import com.sampas.socbs.core.tile.impl.TileDataStore.TileImageReturnType;
import com.sampas.socbs.core.tile.impl.TileDataStore.TileServerVersion;

public class TileDataStoreSrv {
	
	private TileDataStore tileDataStore = null;
	
	public TileDataStoreSrv(URL tileServerURL, TileServerVersion tileServerVersion, TileImageReturnType tileImageReturnType, int metaTiling, boolean allowCacheByPass) {
		
		try {

			tileDataStore = new TileDataStore(tileServerURL, tileServerVersion, tileImageReturnType, metaTiling, allowCacheByPass);
			
		} catch (Exception ex) {
			
			System.out.println("Error on creating Tile Data Store ! ERROR : " + ex);
		}
	}
	
	public TileDataStore getTileDataStore() {
		
		return tileDataStore;
	}
	
}
