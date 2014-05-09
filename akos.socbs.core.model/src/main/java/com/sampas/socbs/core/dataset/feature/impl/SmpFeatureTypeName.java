package com.sampas.socbs.core.dataset.feature.impl;

import com.sampas.socbs.core.dataset.feature.IFeatureTypeName;

public class SmpFeatureTypeName implements IFeatureTypeName {

	private String featureTypeName;
	
	public SmpFeatureTypeName(String newFeatureTypeName) {
		
		this.featureTypeName = newFeatureTypeName;
	}
	
	public String getFeatureTypeName() {
		return featureTypeName;
	}

	public void setFeatureTypeName(String featureTypeName) {
		this.featureTypeName = featureTypeName;
	}
	
}
