package com.sampas.ortak.spatial.servis.tools.impl;

import com.sampas.ortak.spatial.servis.tools.IAppFeatureLayer;
import com.sampas.socbs.core.maplayer.IFeatureLayer;


public class AppFeatureLayer implements IAppFeatureLayer {

	private String layerSystemName = "";
	
	private String layerRelatedBaseObjectName = "";
	
	private IFeatureLayer featureLayer = null;
	

	public AppFeatureLayer(String layerSystemName, String layerRelatedBaseObjectName, IFeatureLayer featureLayer) {
		
		this.layerSystemName = layerSystemName;
		
		this.layerRelatedBaseObjectName = layerRelatedBaseObjectName;
		
		this.featureLayer = featureLayer;
	}
	
	public String getLayerSystemName() {
		return layerSystemName;
	}

	public IFeatureLayer getFeatureLayer() {
		return featureLayer;
	}

	public String getLayerRelatedBaseObjectName() {
		return layerRelatedBaseObjectName;
	}

}
