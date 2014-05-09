package com.sampas.socbs.core.tools;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IPoint;


public interface IFeatureGeometryTools {

	public IPoint getFirstPointOfFeature(IFeature feature, ICoordinateSystem coordinateSystem);
	
	public ICoordinate getFirstCoordinteOfFeature(IFeature feature);
	
	public IPoint getLastPointOfFeature(IFeature feature, ICoordinateSystem coordinateSystem);
	
	public ICoordinate getLastCoordinteOfFeature(IFeature feature);
}