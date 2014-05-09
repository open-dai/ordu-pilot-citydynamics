package com.sampas.socbs.core.tiler.tool.impl;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.ITileDataStore;
import com.sampas.socbs.core.data.provider.services.impl.OracleDataStoreSrv;
import com.sampas.socbs.core.data.provider.services.impl.PostGisDataStoreSrv;
import com.sampas.socbs.core.data.provider.services.impl.TileDataStoreSrv;
import com.sampas.socbs.core.data.provider.services.impl.WFSDataStoreSrv;
import com.sampas.socbs.core.data.wfs.impl.WFSDataStore.WfsGMLVersion;
import com.sampas.socbs.core.data.wfs.impl.WFSDataStore.WfsVersion;
import com.sampas.socbs.core.tile.impl.TileDataStore.TileImageReturnType;
import com.sampas.socbs.core.tile.impl.TileDataStore.TileServerVersion;
import com.sampas.socbs.core.tile.toolbox.SRS;
import com.sampas.socbs.core.tile.toolbox.Tile;

public class TilingRequirements {

	private String appName = "";

	private String dsType = "";
	
	private String dsNameSpace = "";
	
	private String dsHost = "";

	private int dsPort = 0;

	private String dsInstance = "";

	private String dsUser = "";

	private String dsPass = "";

	private String dsSchema = "";

	private int[] layerList = null;

	private int minPyramidLevel = 0;
	
	private int maxPyramidLevel = 20;

	private String tileLayerName = "";
	
	private String tileLayerGeomColumnName = "";

	private String GWCCacheDirectory = "";
	
	private String tileServerAddress = "";
	
	private int tileDimention = 0;

	private TileImageReturnType tileImgFileType = TileImageReturnType.jpeg;

	private TileServerVersion tileServerVersion = TileServerVersion.ver111;

	private int metaTilingRange = 0;

	private boolean enableCatching = true;
	
	private SRS defaultSRS = null;
	
	private URL relatedWFSAddress = null;

	private WfsVersion relatedWFSVersion = WfsVersion.ver100;
	
	private WfsGMLVersion relatedWFSGMLVersion = WfsGMLVersion.gml2;
	
	private OracleDataStoreSrv oracleDataStoreSrv = null;
	
	private PostGisDataStoreSrv postgisDataStoreSrv = null;
	
	private WFSDataStoreSrv wfsDataStoreSrv = null;
	
	private String impPlcDSLayerName = "";

	private ITileDataStore tileDataStore = null;
	
	
	
	public TilingRequirements() {
		
		try {
		
			defaultSRS = SRS.getSRS("EPSG:4326");
			
			MetaDataFileHandler metaDataFileHandler = new MetaDataFileHandler("TilingAppMetadata");
			
			Properties properties = new Properties();
			
			InputStream streamOrder = metaDataFileHandler.getResource();
			
			if (streamOrder != null) {
	
				properties.load(streamOrder);
				
				this.appName =  properties.getProperty("appName").trim();
				
				this.dsType = properties.getProperty("dsType").trim();
				
				this.dsNameSpace = properties.getProperty("dsNameSpace").trim();
	
				this.dsHost = properties.getProperty("dsHost").trim();
	
				this.dsPort = Integer.parseInt(properties.getProperty("dsPort").trim());
				
				this.dsPort = Integer.parseInt(properties.getProperty("dsPort").trim());
	
				this.dsInstance = properties.getProperty("dsInstance").trim();
	
				this.dsUser = properties.getProperty("dsUser").trim();
	
				this.dsPass = properties.getProperty("dsPass").trim();
	
				this.dsSchema = properties.getProperty("dsSchema").trim();
				
				this.layerList = getLayerList(properties.getProperty("layerList").trim());

				this.minPyramidLevel = Integer.parseInt(properties.getProperty("minPyramidLevel").trim());
				
				this.maxPyramidLevel = Integer.parseInt(properties.getProperty("maxPyramidLevel").trim());

				this.tileLayerName = properties.getProperty("tileLayerName").trim();
				
				this.tileLayerGeomColumnName = properties.getProperty("tileLayerGeomColumnName").trim();

				this.GWCCacheDirectory = properties.getProperty("GWCCacheDirectory").trim();
				
				this.tileServerAddress = properties.getProperty("tileServerAddress").trim();
				
				this.defaultSRS = SRS.getSRS(properties.getProperty("defaultSRS").trim());
				
				this.tileDimention = Integer.parseInt(properties.getProperty("tileDimention").trim());

				if (properties.getProperty("tileImgFileType").trim().equals("jpeg")) {
					this.tileImgFileType = TileImageReturnType.jpeg;
				} else if (properties.getProperty("tileImgFileType").trim().equals("png")) {
					this.tileImgFileType = TileImageReturnType.png;
				} else if (properties.getProperty("tileImgFileType").trim().equals("gif")) {
					this.tileImgFileType = TileImageReturnType.gif;
				}
				
				if (properties.getProperty("tileServerVersion").trim().equals("1.0.0")) {
					this.tileServerVersion = TileServerVersion.ver100;
				} else if (properties.getProperty("tileServerVersion").trim().equals("1.1.0")) {
					this.tileServerVersion = TileServerVersion.ver110;
				} else if (properties.getProperty("tileServerVersion").trim().equals("1.1.1")) {
					this.tileServerVersion = TileServerVersion.ver111;
				} else if (properties.getProperty("tileServerVersion").trim().equals("1.3.0")) {
					this.tileServerVersion = TileServerVersion.ver130;
				} 

				this.metaTilingRange = Integer.parseInt(properties.getProperty("metaTilingRange").trim());

				this.enableCatching = Boolean.parseBoolean(properties.getProperty("tileDimention").trim());
				
				if (properties.getProperty("relatedWFSGMLVersion").trim().equals("gml2")) {
					this.relatedWFSGMLVersion = WfsGMLVersion.gml2;
				} else if (properties.getProperty("relatedWFSGMLVersion").trim().equals("gml3")) {
					this.relatedWFSGMLVersion = WfsGMLVersion.gml3;
				} 

				if (properties.getProperty("relatedWFSVersion").trim().equals("1.0.0")) {
					this.relatedWFSVersion = WfsVersion.ver100;
				} else if (properties.getProperty("relatedWFSVersion").trim().equals("1.1.0")) {
					this.relatedWFSVersion = WfsVersion.ver110;
				}
				
				this.relatedWFSAddress = new URL(properties.getProperty("relatedWFSAddress").trim());
				
			}
		} catch (Exception ex) {
			
			System.out.println("Error on reading tiling metadata ! ERROR : " + ex);
		}
	}

	private int[] getLayerList(String property) {

		int[] layers = getNumaricsFromString(property);
		
		return layers;
	}
	
	public int[] getNumaricsFromString(String property) {
		
		try {
		
			String[] strParts = property.trim().split(",");
			
			int[] resultSet = new int[strParts.length];
			
			for (int i = 0; i < resultSet.length; i++) {
				
				resultSet[i] = Integer.parseInt(strParts[i]);
			}
		
			return resultSet;
		} catch (Exception ex) {

			System.out.println("Error on reading tile layers metadata ! ERROR : " + ex);
			return null;
		}
		
	}

	public IFeatureDataStore getDBDataStore() {
		
		try {

			IFeatureDataStore datastore = null;
			
			if (dsType.equals("oracle")) {
				
				if (this.oracleDataStoreSrv == null) {
					
			        String namespace = this.dsNameSpace;	        
			        String host = this.dsHost;
			        int port = this.dsPort;
			        String instance = this.dsInstance;
			        String user = this.dsUser;
			        String passwd = this.dsPass;  
			        String schema = this.dsSchema;
			        int maxConn = 20;
			        int minConn = 4;
			        Boolean validateConn = false;
					
			        this.oracleDataStoreSrv = new OracleDataStoreSrv(namespace, host, port, instance, user, passwd, schema, maxConn, minConn, validateConn);
		        
				}
				
				datastore = oracleDataStoreSrv.getOracleDataStore();
				
			} else if (dsType.equals("postgis")) {
				
				if (this.postgisDataStoreSrv == null) {
					
			        String host = this.dsHost;
			        int port = this.dsPort;
			        String instance = this.dsInstance;
			        String user = this.dsUser;
			        String passwd = this.dsPass;  
			        String schema = this.dsSchema;
			        boolean wkbEnabled = true;
			        boolean looseBBox = true;
					
			        this.postgisDataStoreSrv = new PostGisDataStoreSrv(host, port, instance, schema, user, passwd, wkbEnabled, looseBBox);
		        
				}
				
				datastore = postgisDataStoreSrv.getPostGisDataStore();
				
			} else {
				
				System.out.println("Database Type not match one of defined, check metadata !");
			}
			
	        return datastore;
        
		} catch (Exception ex) {

			System.out.println("Error on creating Oracle data store ! ERROR: " + ex);
			
			return null;
		}
	}
	
	public IFeatureDataStore getWFSDataStore() {
		
		try {
			
			if (this.wfsDataStoreSrv == null) {
				
		        this.wfsDataStoreSrv = new WFSDataStoreSrv(this.relatedWFSAddress, this.relatedWFSGMLVersion, this.relatedWFSVersion);
	        
			}
			
			IFeatureDataStore wfsDatastore = wfsDataStoreSrv.getWfsDataStore();
			
	        return wfsDatastore;
        
		} catch (Exception ex) {
	
			System.out.println("Error on creating WFS data store ! ERROR: " + ex);
			
			return null;
		}
	}

	public ITileDataStore getTileDataStore() {
		
		try {

			TileDataStoreSrv tileDataStoreSrv = new TileDataStoreSrv(new URL(this.tileServerAddress), tileServerVersion, TileImageReturnType.png, 0, true);

			ITileDataStore tileDataStore = tileDataStoreSrv.getTileDataStore();
			
			this.tileDataStore  = tileDataStore;
			
			return tileDataStore; 
		}		
		catch (Exception ex) {
			
			System.out.println("Error on creating Tile data store ! ERROR: " + ex);
		
			return null;
		}
	}
	
	public Tile getTileWithLocation() {
		
		if (this.tileDataStore == null) {
			this.tileDataStore = getTileDataStore();
		}
		
		//TODO OOO00OOO00
		return null;
	}
		
		
	//TODO Getters and Setters Location Separator
	
	public String getTileLayerGeomColumnName() {
		return tileLayerGeomColumnName;
	}

	public void setTileLayerGeomColumnName(String tileLayerGeomColumnName) {
		this.tileLayerGeomColumnName = tileLayerGeomColumnName;
	}

	public String getTileServerAddress() {
		return tileServerAddress;
	}

	public void setTileServerAddress(String tileServerAddress) {
		this.tileServerAddress = tileServerAddress;
	}

	public int getTileDimention() {
		return tileDimention;
	}

	public void setTileDimention(int tileDimention) {
		this.tileDimention = tileDimention;
	}

	public int getMetaTilingRange() {
		return metaTilingRange;
	}

	public void setMetaTilingRange(int metaTilingRange) {
		this.metaTilingRange = metaTilingRange;
	}

	public boolean isEnableCatching() {
		return enableCatching;
	}

	public void setEnableCatching(boolean enableCatching) {
		this.enableCatching = enableCatching;
	}
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int[] getLayerList() {
		return layerList;
	}

	public void setLayerList(int[] layerList) {
		this.layerList = layerList;
	}

	public int getMaxPyramidLevel() {
		return maxPyramidLevel;
	}

	public void setMaxPyramidLevel(int maxPyramidLevel) {
		this.maxPyramidLevel = maxPyramidLevel;
	}

	public String getTileLayerName() {
		return tileLayerName;
	}

	public void setTileLayerName(String tileLayerName) {
		this.tileLayerName = tileLayerName;
	}

	public String getGWCCacheDirectory() {
		return GWCCacheDirectory;
	}

	public void setGWCCacheDirectory(String cacheDirectory) {
		GWCCacheDirectory = cacheDirectory;
	}

	public TileImageReturnType getTileImgFileType() {
		return tileImgFileType;
	}

	public void setTileImgFileType(TileImageReturnType tileImgFileType) {
		this.tileImgFileType = tileImgFileType;
	}

	public TileServerVersion getTileServerVersion() {
		return tileServerVersion;
	}

	public void setTileServerVersion(TileServerVersion tileServerVersion) {
		this.tileServerVersion = tileServerVersion;
	}

	public SRS getDefaultSRS() {
		return defaultSRS;
	}

	public void setDefaultSRS(SRS defaultSRS) {
		this.defaultSRS = defaultSRS;
	}

	public WFSDataStoreSrv getWfsDataStoreSrv() {
		return wfsDataStoreSrv;
	}

	public void setWfsDataStoreSrv(
			WFSDataStoreSrv wfsDataStoreSrv) {
		this.wfsDataStoreSrv = wfsDataStoreSrv;
	}

	public String getDsType() {
		return dsType;
	}

	public void setDsType(String dsType) {
		this.dsType = dsType;
	}
	
	public String getDsNameSpace() {
		return dsNameSpace;
	}

	public void setDsNameSpace(String dsNameSpace) {
		this.dsNameSpace = dsNameSpace;
	}

	public String getDsHost() {
		return dsHost;
	}

	public void setDsHost(String dsHost) {
		this.dsHost = dsHost;
	}

	public int getDsPort() {
		return dsPort;
	}

	public void setDsPort(int dsPort) {
		this.dsPort = dsPort;
	}

	public String getDsInstance() {
		return dsInstance;
	}

	public void setDsInstance(String dsInstance) {
		this.dsInstance = dsInstance;
	}

	public String getDsUser() {
		return dsUser;
	}

	public void setDsUser(String dsUser) {
		this.dsUser = dsUser;
	}

	public String getDsPass() {
		return dsPass;
	}

	public void setDsPass(String dsPass) {
		this.dsPass = dsPass;
	}

	public String getDsSchema() {
		return dsSchema;
	}

	public void setDsSchema(String dsSchema) {
		this.dsSchema = dsSchema;
	}

	public int getMinPyramidLevel() {
		return minPyramidLevel;
	}

	public void setMinPyramidLevel(int minPyramidLevel) {
		this.minPyramidLevel = minPyramidLevel;
	}

	public URL getRelatedWFSAddress() {
		return relatedWFSAddress;
	}

	public void setRelatedWFSAddress(URL relatedWFSAddress) {
		this.relatedWFSAddress = relatedWFSAddress;
	}

	public WfsVersion getRelatedWFSVersion() {
		return relatedWFSVersion;
	}

	public void setRelatedWFSVersion(WfsVersion relatedWFSVersion) {
		this.relatedWFSVersion = relatedWFSVersion;
	}

	public WfsGMLVersion getRelatedWFSGMLVersion() {
		return relatedWFSGMLVersion;
	}

	public void setRelatedWFSGMLVersion(WfsGMLVersion relatedWFSGMLVersion) {
		this.relatedWFSGMLVersion = relatedWFSGMLVersion;
	}

	public String getImpPlcDSLayerName() {
		return impPlcDSLayerName;
	}

	public void setImpPlcDSLayerName(String impPlcDSLayerName) {
		this.impPlcDSLayerName = impPlcDSLayerName;
	}

}
