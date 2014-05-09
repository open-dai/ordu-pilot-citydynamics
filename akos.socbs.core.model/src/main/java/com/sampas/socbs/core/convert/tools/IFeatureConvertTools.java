package com.sampas.socbs.core.convert.tools;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;


public interface IFeatureConvertTools {

	public IFeatureCollection getPointFeaturesFromFeatureCollection(IFeatureCollection featureCollection, ICoordinateSystem coordinateSystem, String pointTypeName) throws Exception;
	
}
