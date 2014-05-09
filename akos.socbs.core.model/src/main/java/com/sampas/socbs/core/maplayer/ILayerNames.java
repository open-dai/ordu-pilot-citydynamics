package com.sampas.socbs.core.maplayer;

import java.util.List;

public interface ILayerNames {

	public List<String> getLayerNames();

	public void setLayerNames(List<String> layerNames);
	
	public void setLayerNames(String[] layerNames);
	
	public void addLayerName(String layerName);
	
	public String getLayerName(int layerNameIndex);
	
	public void removeLayerName(int layerNameIndex);
	
}
