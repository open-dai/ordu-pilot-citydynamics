package com.sampas.socbs.core.dataset.feature.impl;

import java.util.List;

import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureBuffer;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.maplayer.ILayerAttribute;

public class SmpFeatureCursor implements IFeatureCursor {
	
	IFeatureCollection featureCollection;
	List<ILayerAttribute> smpLayerAttribute;
	int cursor = 0;

	public SmpFeatureCursor(IFeatureCollection featureCollection,List<ILayerAttribute> smpLayerAttribute ){
		
		this.featureCollection = featureCollection;
		this.smpLayerAttribute = smpLayerAttribute;
	}

	public void deleteFeature() {
						
			this.featureCollection.removeFeature(cursor);
	
	}
	
	public IFeature getFeature() {

		return this.featureCollection.getFeatureAt(cursor);
	}
	
	public int getFeatureSize(){
		
		return featureCollection.getFeaturesCount();
	}

	public int findField(String name) {
			
		// TODO : cannot access that property  
		return 0;
	}

	public List<ILayerAttribute> getFields() {
			
		return this.smpLayerAttribute;
	}

	public Object insertFeature(IFeatureBuffer buffer) {
		// TODO connect db by using featurebuffer parameters and insert new feature
		return null;
	}

	public IFeature nextFeature() {

		return this.featureCollection.getFeatureAt(cursor++);
	}

	public void updateFeature(IFeature object) {
		// TODO by using  cursor set new feature instead of old one
	//	this.featureCollection.getFeatureAt(cursor).
	}

}
