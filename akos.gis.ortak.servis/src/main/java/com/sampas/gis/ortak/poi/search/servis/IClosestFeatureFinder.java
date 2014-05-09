package com.sampas.gis.ortak.poi.search.servis;

import java.util.List;
import com.sampas.gis.ortak.poi.search.servis.impl.AttributeListItem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.maplayer.IFeatureLayer;


public interface IClosestFeatureFinder {

	public IFeature findClosestFeature(IFeatureLayer searchLayer, IPoint referancePoint, double offset, List<AttributeListItem> attributeList);
	
	public IFeature findClosestFeature(IFeatureLayer searchLayer, IPoint referancePoint, double offset);
	
}
