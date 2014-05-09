package com.sampas.ortak.spatial.servis.tools;

import com.sampas.socbs.core.maplayer.IFeatureLayer;

public interface IAppFeatureLayer {

	public String getLayerSystemName();
	
	public String getLayerRelatedBaseObjectName();

	public IFeatureLayer getFeatureLayer();
	
}
