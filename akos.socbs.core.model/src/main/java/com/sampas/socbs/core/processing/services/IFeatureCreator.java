package com.sampas.socbs.core.processing.services;

import java.util.List;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IPoint;

public interface IFeatureCreator {

	public IFeature pointFeatureCreator(String featureType,String featureName,String description, IPoint smpPoint);
	
	public IFeature lineFeatureCreator(String featureType,String featureName,String description, List<ICoordinate> coordinates, ICoordinateSystem coordinateSystem);
	
	public IFeature polygonFeatureCreator(String featureType,String featureName,String description, List<ICoordinate> coordinates, ICoordinateSystem coordinateSystem);
		
	public IFeature cloneFeatureCreator(IFeature baseFeature);
	
}
