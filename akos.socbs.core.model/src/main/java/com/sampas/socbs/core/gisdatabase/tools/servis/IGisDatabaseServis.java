package com.sampas.socbs.core.gisdatabase.tools.servis;

import java.util.List;

import com.sampas.socbs.core.dataset.feature.IAttributeType;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureTypeName;


public interface IGisDatabaseServis {

	public boolean createFeatureType(IFeatureTypeName newFeatureTypeName, List<IAttributeType> attributeTypes);
	
	public IFeatureCollection readFeatures(IFeatureTypeName newFeatureTypeName);
		
	public boolean writeFeatures(IFeatureCollection features);
	
	public boolean writeFeature(IFeature feature);

	public boolean updateFeature(IFeature feature);
	
	public boolean updateFeatures(IFeatureCollection features);
	
	public boolean deleteFeature(IFeature feature);
	
	public boolean deleteFeatures(IFeatureCollection features);
	
}
