package com.sampas.gis.ortak.poi.search.servis;

import java.util.HashMap;
import java.util.List;


import com.sampas.gis.ortak.poi.search.servis.impl.AttributeListItem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayerAttribute;

public interface IClosestPOIFinder {

	public IFeature findClosestPOI(IFeatureLayer searchLayer, IPoint referancePoint, HashMap<ILayerAttribute, Object> attributes, List<ILayerAttribute> attributeList);
	
	public IFeature findClosestPOI(IFeatureLayer searchLayer, IPoint referancePoint, List<AttributeListItem> attributeList);
	  	
}
