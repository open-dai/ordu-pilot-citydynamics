package com.sampas.socbs.core.tools.impl;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpPoint;
import com.sampas.socbs.core.tools.IFeatureGeometryTools;


public class FeatureGeometryTools implements IFeatureGeometryTools {

	@Override
	public ICoordinate getFirstCoordinteOfFeature(IFeature feature) {

		if (feature == null || feature.isGeometryEmpty() || feature.getDefaultGeometry().getNumPoints() == 0) {
			
			return null;
		} else {
			
			return feature.getDefaultGeometry().getCoordinates()[0];
		}
	}

	@Override
	public IPoint getFirstPointOfFeature(IFeature feature, ICoordinateSystem coordinateSystem) {

		if (feature == null || feature.isGeometryEmpty() || feature.getDefaultGeometry().getNumPoints() == 0) {
			
			return null;
		} else {
			
			return new SmpPoint(feature.getDefaultGeometry().getCoordinates()[0].getX(), feature.getDefaultGeometry().getCoordinates()[0].getY(), coordinateSystem);
		}
	}

	@Override
	public ICoordinate getLastCoordinteOfFeature(IFeature feature) {
		
		if (feature == null || feature.isGeometryEmpty() || feature.getDefaultGeometry().getNumPoints() == 0) {
			
			return null;
		} else {
			
			return feature.getDefaultGeometry().getCoordinates()[feature.getDefaultGeometry().getNumPoints()];
		}
	}

	@Override
	public IPoint getLastPointOfFeature(IFeature feature, ICoordinateSystem coordinateSystem) {
		
		if (feature == null || feature.isGeometryEmpty() || feature.getDefaultGeometry().getNumPoints() == 0) {
			
			return null;
		} else {
			
			return new SmpPoint(feature.getDefaultGeometry().getCoordinates()[feature.getDefaultGeometry().getNumPoints() - 1].getX(), feature.getDefaultGeometry().getCoordinates()[feature.getDefaultGeometry().getNumPoints() - 1].getY(), coordinateSystem);
		}
	}

}