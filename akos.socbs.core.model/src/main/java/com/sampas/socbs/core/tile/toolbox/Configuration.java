package com.sampas.socbs.core.tile.toolbox;

import java.util.List;

import com.sampas.socbs.core.tile.impl.TileLayer;

public interface Configuration {
	public List<TileLayer> getTileLayers(boolean reload) throws GeoWebCacheException;
	public String getIdentifier() throws GeoWebCacheException;
}
