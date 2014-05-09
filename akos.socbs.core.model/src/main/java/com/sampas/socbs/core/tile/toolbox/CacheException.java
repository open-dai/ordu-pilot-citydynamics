package com.sampas.socbs.core.tile.toolbox;

public class CacheException extends GeoWebCacheException {
    /**
     * 
     */
    private static final long serialVersionUID = 6668244317627578130L;

    public CacheException(String msg) {
        super(msg);
    }

    public CacheException(Throwable thrw) {
        super(thrw);
    }
}
