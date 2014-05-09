package com.sampas.socbs.core.convert.tools.impl;

import com.sampas.socbs.core.convert.tools.IFeatureConvertTools;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureCollection;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.impl.SmpPoint;
import com.sampas.socbs.core.processing.services.IFeatureCreator;
import com.sampas.socbs.core.processing.services.impl.SmpFeatureCreator;


public class FeatureConvertTools implements IFeatureConvertTools {

	public IFeatureCollection getPointFeaturesFromFeatureCollection(IFeatureCollection featureCollection, ICoordinateSystem coordinateSystem, String pointTypeName) throws Exception {
		
		if (featureCollection == null) {
			
			throw new Exception("Feature collection can't be null !!!");
		}
		if (featureCollection.getFeaturesCount() <= 0) {
			
			throw new Exception("Feature collection can't be empty !!!");
		}
		IFeatureCollection resultFeatures = new SmpFeatureCollection();
		
		IFeatureCreator featureCreator = new SmpFeatureCreator();
		
		for (int featureCntr = 0; featureCntr < featureCollection.getFeaturesCount(); featureCntr++) {
			
			for (ICoordinate tempCoordinate : featureCollection.getFeatureAt(featureCntr).getDefaultGeometry().getCoordinates()) {
				
				IFeature tmpFeature = featureCreator.pointFeatureCreator(pointTypeName, "", "", new SmpPoint(tempCoordinate.getX(), tempCoordinate.getY(), coordinateSystem));
				
				resultFeatures.addFeature(tmpFeature);
			}
		}
		return resultFeatures;
	}
}
