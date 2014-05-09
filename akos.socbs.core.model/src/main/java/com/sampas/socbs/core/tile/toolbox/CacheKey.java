package com.sampas.socbs.core.tile.toolbox;

/**
 * Interface to define cache keys.
 * 
 * @author Arne Kepp, The Open Planning Project, (C) 2007
 */
public interface CacheKey {
	public void init();

	public Object createKey(Tile tile);
}
