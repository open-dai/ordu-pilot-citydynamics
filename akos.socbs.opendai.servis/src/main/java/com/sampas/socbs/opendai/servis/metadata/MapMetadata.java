package com.sampas.socbs.opendai.servis.metadata;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.sampas.socbs.opendai.servis.tools.SmpMetaDataHandler;


public class MapMetadata {

	//AppNameForLAyerMetadata
	private String appNameForMetadata = "";
	//Max zoom level
	private int maxZoomLevel = -1;
	//List of layers for show on map
	private List<Integer> mapLayerNoList;
	//Reference Layer for application
	private int layerReferance = -1;
	//Layer Address Place
	private int layerAddress = -1;
	//Layer District Place
	private int layerDistrict = -1;
	//Layer Street Place
	private int layerStreet = -1;
	//Layer Build Island Place
	private int layerBuildIsland = -1;
	//Layer Building Place
	private int layerBuilding = -1;
	//Layer Important Place Place
	private int layerImportantPlace = -1;
	//Layer Raster Place
	private int layerRaster = -1;
	//Layer Adres feature
	private int layerAddressFeature = -1;
	//Layer Important place feature
	private int layerImportantPlaceFeature = -1;
	//Layer Building feature
	private int layerBuildingFeature = -1;

	//Layer Parcel feature
	private int layerParcelFeature = -1;	
	
	
	public MapMetadata() {
		
		try {
			
			SmpMetaDataHandler smpMetaDataHandler = new SmpMetaDataHandler("layerMetadata");
			
			Properties properties = new Properties();
			
			InputStream streamOrder = smpMetaDataHandler.getResource();
			
			if (streamOrder != null) {

				properties.load(streamOrder);
				
				setAppNameForMetadata(properties.getProperty("appNameForMetadata").trim());
				
				setMaxZoomLevel(Integer.parseInt(properties.getProperty("maxZoomLevel").trim()));
				
				String tempMapLayersNoString = properties.getProperty("mapImageLayers").trim();
				
				String[] tempMapLayerNoStringList = tempMapLayersNoString.split(",");
				
				this.mapLayerNoList = new ArrayList<Integer>();
				
				for (String tempMapLayerNoString : tempMapLayerNoStringList) {
					
					this.mapLayerNoList.add(Integer.decode(tempMapLayerNoString));
				}
				setLayerReferance(Integer.decode(properties.getProperty("layerReference").trim()));
				
				setLayerAddress(Integer.decode(properties.getProperty("layerAddress").trim()));
				
				setLayerDistrict(Integer.decode(properties.getProperty("layerDistrict").trim()));
				
				setLayerBuilding(Integer.decode(properties.getProperty("layerBuilding").trim()));
				
				setLayerStreet(Integer.decode(properties.getProperty("layerStreet").trim()));
				
				setLayerBuildIsland(Integer.decode(properties.getProperty("layerBuildIsland").trim()));
				
				setLayerImportantPlace(Integer.decode(properties.getProperty("layerImportantPlace").trim()));
				
				setLayerRaster(Integer.decode(properties.getProperty("layerRaster").trim()));
				
				setLayerAddressFeature(Integer.decode(properties.getProperty("layerAddressFeature").trim()));
				
				setLayerImportantPlaceFeature(Integer.decode(properties.getProperty("layerImportantPlaceFeature").trim()));
				
				setLayerBuildingFeature(Integer.decode(properties.getProperty("layerBuildingFeature").trim()));

				setLayerParcelFeature(Integer.decode(properties.getProperty("layerParcelFeature").trim()));	
				
			} else {
				
				System.out.println("Error on finding metadata.properties file!");
			}
		} catch (Exception ex) {
			
			System.out.println("Error on reading metadata ! ERROR : " + ex);
		}
	}

	public String getAppNameForMetadata() {
		return appNameForMetadata;
	}

	public void setAppNameForMetadata(String appNameForMetadata) {
		this.appNameForMetadata = appNameForMetadata;
	}

	public int getMaxZoomLevel() {
		return maxZoomLevel;
	}

	public void setMaxZoomLevel(int maxZoomLevel) {
		this.maxZoomLevel = maxZoomLevel;
	}

	public List<Integer> getMapLayerNoList() {
		return mapLayerNoList;
	}

	public void setMapLayerNoList(List<Integer> mapLayerNoList) {
		this.mapLayerNoList = mapLayerNoList;
	}

	public int getLayerDistrict() {
		return layerDistrict;
	}

	public void setLayerDistrict(int layerDistrict) {
		this.layerDistrict = layerDistrict;
	}

	public int getLayerStreet() {
		return layerStreet;
	}

	public void setLayerStreet(int layerStreet) {
		this.layerStreet = layerStreet;
	}

	public int getLayerBuildIsland() {
		return layerBuildIsland;
	}

	public void setLayerBuildIsland(int layerBuildIsland) {
		this.layerBuildIsland = layerBuildIsland;
	}

	public int getLayerBuilding() {
		return layerBuilding;
	}

	public void setLayerBuilding(int layerBuilding) {
		this.layerBuilding = layerBuilding;
	}

	public int getLayerImportantPlace() {
		return layerImportantPlace;
	}

	public void setLayerImportantPlace(int layerImportantPlace) {
		this.layerImportantPlace = layerImportantPlace;
	}

	public int getLayerRaster() {
		return layerRaster;
	}

	public void setLayerRaster(int layerRaster) {
		this.layerRaster = layerRaster;
	}

	public int getLayerReferance() {
		return layerReferance;
	}

	public void setLayerReferance(int layerReferance) {
		this.layerReferance = layerReferance;
	}

	public int getLayerAddress() {
		return layerAddress;
	}

	public void setLayerAddress(int layerAddress) {
		this.layerAddress = layerAddress;
	}

	public int getLayerAddressFeature() {
		return layerAddressFeature;
	}

	public void setLayerAddressFeature(int layerAddressFeature) {
		this.layerAddressFeature = layerAddressFeature;
	}

	public int getLayerImportantPlaceFeature() {
		return layerImportantPlaceFeature;
	}

	public void setLayerImportantPlaceFeature(int layerImportantPlaceFeature) {
		this.layerImportantPlaceFeature = layerImportantPlaceFeature;
	}

	public int getLayerBuildingFeature() {
		return layerBuildingFeature;
	}

	public void setLayerBuildingFeature(int layerBuildingFeature) {
		this.layerBuildingFeature = layerBuildingFeature;
	}

	public int getLayerParcelFeature() {
		return layerParcelFeature;
	}

	public void setLayerParcelFeature(int layerParcelFeature) {
		this.layerParcelFeature = layerParcelFeature;
	}
	
}
