package com.sampas.socbs.core.tools;

import com.sampas.socbs.core.dataset.feature.IFeatureID;


public interface IFeatureIDTools {

	public abstract Long getFeatureIDFromFID(IFeatureID featureId);
	
	public abstract Long getFeatureIDFromStr(String featureId);
	
	public abstract IFeatureID createFeatureIDWithFTypeAndID(String featureType, Long id);
	
	public abstract IFeatureID createFeatureIDWithLayerNameAndID(String layerName, Long id);
	
	public abstract String getFeatureTypeFromLayerName(String layerName);
}
