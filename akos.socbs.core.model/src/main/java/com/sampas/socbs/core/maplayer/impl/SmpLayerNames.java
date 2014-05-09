package com.sampas.socbs.core.maplayer.impl;

import java.util.ArrayList;
import java.util.List;

import com.sampas.socbs.core.maplayer.ILayerNames;

public class SmpLayerNames implements ILayerNames {

	private List<String> layerNames = new ArrayList<String>();

	public SmpLayerNames() {
		
	}
	
	public List<String> getLayerNames() {
		return layerNames;
	}

	public void setLayerNames(List<String> layerNames) {
		this.layerNames = layerNames;
	}
	
	public void setLayerNames(String[] layerNames) {
		
		for (int i = 0; i < layerNames.length; i++) {
			
			this.layerNames.add(layerNames[i]);
		}
		
	}
	
	public void addLayerName(String layerName) {
		this.layerNames.add(layerName);
	}
	
	public String getLayerName(int layerNameIndex) {
		return (this.layerNames.get(layerNameIndex));
	}
	
	public void removeLayerName(int layerNameIndex) {
		this.layerNames.remove(layerNameIndex);
	}
	
}
