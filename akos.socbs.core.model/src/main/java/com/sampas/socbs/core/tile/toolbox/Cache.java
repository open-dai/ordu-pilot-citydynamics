package com.sampas.socbs.core.tile.toolbox;

import java.util.Properties;

import org.springframework.context.ApplicationContextAware;

import com.sampas.socbs.core.tile.impl.TileLayer;

public interface Cache extends ApplicationContextAware {
	public void setDefaultKeyBeanId(String defaultKeyBeanId);
	
    public void init(Properties props) throws CacheException;

    public void destroy();

    public void setUp(String cachePrefix) throws CacheException;

    public String getDefaultKeyBeanId();
    
    public String getDefaultPrefix(String param) throws CacheException;

    /**
     * 
     * Note that maxAge is only honored if the backend supports it. See static
     * value defined on GeoWebCache
     * 
     * @param key
     *            the cache key
     * @param ttl
     *            the maximum age of the object, in milliseconds
     * @return
     * @throws CacheException
     */
    public boolean get(CacheKey keyProto, Tile tile, long ttl) 
    throws CacheException, GeoWebCacheException;

    /**
     * 
     * Note that ttl is only honored if the backend supports it. See static
     * value defined on GeoWebCache
     * 
     * @param key
     * @param obj
     * @param ttl
     *            the maximum amount of time to cache the object, in
     *            milliseconds
     * @throws CacheException
     */
    public void set(CacheKey keyProto, Tile tile, long ttl) 
    throws CacheException, GeoWebCacheException;

    public int truncate(TileLayer tl, SRS srs, int zoomStart, int zoomStop, 
            int[][] bounds, MimeType[] mimeTypes) throws CacheException;
    
    public boolean remove(CacheKey keyProto, Tile tile) throws CacheException;

    public void removeAll() throws CacheException;

}
