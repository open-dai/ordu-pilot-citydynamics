package com.sampas.socbs.core.dataset.feature;



public interface IFeatureCollection {
	
	public IFeature getFeatureAt(int index);
	
	public int getFeaturesCount();
	
	public IFeatureType getSchema();
	
	public void removeFeature(int index);
	
	public void addFeature(IFeature smpfeature);
	
	public IFeatureCollection cloneFeatures();
	
}
