package com.sampas.socbs.core.maplayer;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.sampas.socbs.core.data.ITileDataStore;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.tile.impl.Grid;
import com.sampas.socbs.core.tile.toolbox.BBOX;
import com.sampas.socbs.core.tile.toolbox.BadTileException;
import com.sampas.socbs.core.tile.toolbox.Cache;
import com.sampas.socbs.core.tile.toolbox.CacheFactory;
import com.sampas.socbs.core.tile.toolbox.CacheKey;
import com.sampas.socbs.core.tile.toolbox.GeoWebCacheException;
import com.sampas.socbs.core.tile.toolbox.MimeType;
import com.sampas.socbs.core.tile.toolbox.SRS;
import com.sampas.socbs.core.tile.toolbox.Tile;

public interface ITileLayer extends ILayer{

	//public  List<ITileLayer> getLayerDefinitions();
	
	public void setTileDataProvider(ITileDataStore tileDataProvider);
	
    public void setName(String name);
    
    public String getName();
    
    public void addGrid(SRS srs,Grid grid);
    
    public Hashtable<SRS,Grid> getGrids();
    
    public void addFormat(String format);
    
    public abstract Boolean isInitialized();
    
    public boolean supportsSRS(SRS srs) throws GeoWebCacheException;
    
    public boolean supportsFormat(String strFormat) throws GeoWebCacheException;
    
    public String supportsBbox(SRS srs, BBOX reqBounds) throws GeoWebCacheException;
    
    public abstract Tile getTile(Tile tile) throws GeoWebCacheException, IOException;
    
    public abstract Tile getNoncachedTile(Tile tile, boolean requestTiled) throws GeoWebCacheException;
    
    public abstract void seedTile(Tile tile, boolean tryCache) throws GeoWebCacheException, IOException;
    
    public abstract Tile doNonMetatilingRequest(Tile tile) throws GeoWebCacheException;
    
    public Grid getGrid(SRS srs);
    
    public double[] getResolutions(SRS srs) throws GeoWebCacheException;
    
    public abstract String getStyles();
    
    public abstract int[] getMetaTilingFactors();
    
    public abstract Boolean isCacheBypassAllowed();
    
    public abstract void isCacheBypassAllowed(boolean allowed);
    
    public abstract Integer getBackendTimeout();
    
    public abstract void setBackendTimeout(int seconds);
    
    public abstract int[][] getCoveredGridLevels(SRS srs, BBOX bounds) throws GeoWebCacheException;
    
    public abstract List<MimeType> getMimeTypes();
    
    public abstract MimeType getDefaultMimeType();
    
    public abstract void destroy();
    
    public abstract int[] getGridLocForBounds(SRS srs, BBOX bounds) throws BadTileException, GeoWebCacheException;
    
    public abstract BBOX getBboxForGridLoc(SRS srs, int[] gridLoc) throws GeoWebCacheException;
    
    public abstract BBOX getBboxForGridLoc(SRS srs, int[] gridLoc, int zoomLevel) throws GeoWebCacheException;
    
    public int getZoomStart(SRS srs);
    
    public int getZoomStop(SRS srs);
    
    public abstract int[][] getZoomInGridLoc(SRS srs, int[] gridLoc) throws GeoWebCacheException;
    
    public abstract int[] getZoomedOutGridLoc(SRS srs) throws GeoWebCacheException;
    
    public abstract boolean tryCacheFetch(Tile tile);
    
    public abstract String getCachePrefix();
    
    public abstract CacheKey getCacheKey();
    
    public abstract Cache getCache();
    
    public abstract void acquireLayerLock();
    
    public abstract void releaseLayerLock();
    
    public abstract void putTile(Tile tile) throws GeoWebCacheException;
    
    public abstract void setExpirationHeader(HttpServletResponse response);
    
    public abstract void setCacheFactory(CacheFactory cacheFactory);
    
    public byte[] getTileAtPosition(int tileNoX,int tileNoY, IDimension tileDimension);
}