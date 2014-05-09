package com.sampas.socbs.core.maplayer;

import java.util.Map;


public interface ILayersNameAndPD_CRS {

	public Map<String,String> getLayersNameAndPD_CRS();

	public void addLayersNameAndPD_CRS(String layerName, String CRS);
	
	public String getLayersNameAndPD_CRS(String layerNameKey);
	
	public void removeLayersNameAndPD_CRS(String layerNameKey);
	
}