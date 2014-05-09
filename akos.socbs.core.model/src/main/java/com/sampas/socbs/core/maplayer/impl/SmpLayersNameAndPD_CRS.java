package com.sampas.socbs.core.maplayer.impl;

import java.util.HashMap;
import java.util.Map;
import com.sampas.socbs.core.maplayer.ILayersNameAndPD_CRS;


public class SmpLayersNameAndPD_CRS implements ILayersNameAndPD_CRS {

	private Map<String, String> layersNameAndPD_CRS = new HashMap<String, String>();
	
	
	public SmpLayersNameAndPD_CRS(Map<String, String> layersNameAndPD_CRS) {
		
		this.layersNameAndPD_CRS = layersNameAndPD_CRS;
	}
	
	public void addLayersNameAndPD_CRS(String layerName, String CRS) {

		this.layersNameAndPD_CRS.put(layerName, CRS);
	}

	public Map<String, String> getLayersNameAndPD_CRS() {

		return this.layersNameAndPD_CRS;
	}

	public String getLayersNameAndPD_CRS(String layerNameKey) {

		return this.layersNameAndPD_CRS.get(layerNameKey);
	}

	public void removeLayersNameAndPD_CRS(String layerNameKey) {

		this.layersNameAndPD_CRS.remove(layerNameKey);
	}

}
