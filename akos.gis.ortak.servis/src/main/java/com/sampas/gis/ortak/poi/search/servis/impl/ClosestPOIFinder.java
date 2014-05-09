package com.sampas.gis.ortak.poi.search.servis.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sampas.gis.ortak.poi.search.servis.IClosestPOIFinder;
import com.sampas.socbs.base.spatial.query.services.IEqualsQuery;
import com.sampas.socbs.base.spatial.query.services.impl.SmpEqualsQuery;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.dataset.feature.IFilterBuilder;
import com.sampas.socbs.core.dataset.feature.impl.SmpFilterBuilder;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpCoordinate;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayerAttribute;
import com.sampas.socbs.core.processing.services.IMeasureTools;
import com.sampas.socbs.core.processing.services.impl.SmpMeasureTools;


public class ClosestPOIFinder implements IClosestPOIFinder {
  	
  	public IFeature findClosestPOI(IFeatureLayer searchLayer, IPoint referancePoint, HashMap<ILayerAttribute, Object> attributes, List<ILayerAttribute> attributeList) {
  		
  		IEqualsQuery equalsQuery = new SmpEqualsQuery();
  		
		if (attributeList.size() != 0) {
  		
			List<IFilter> filters = new ArrayList<IFilter>();
			
			for (int i = 0; i < attributeList.size(); i++) {
			
				filters.add(equalsQuery.equalsFilter((ILayerAttribute)attributeList.get(i),	attributes.get(attributeList.get(i))));
				
				equalsQuery = new SmpEqualsQuery();
			}
			
			IFilterBuilder filterBuilder = new SmpFilterBuilder();
			
			IFilter totalFilter = filterBuilder.addAndFilterList(filters);
			
			IFeatureCollection features = searchLayer.getFeaturesByBRectAndFilter(null, totalFilter);
			
			if (features.getFeaturesCount() != 0) {
				
				IMeasureTools measure = new SmpMeasureTools();
				
				double minDistance = Double.MAX_VALUE;
				double tempDistance = Double.MAX_VALUE;
				int closestFeature = -1;
				
				ICoordinate coordinate = new SmpCoordinate(referancePoint.getX(), referancePoint.getY());
				
				for (int i = 0; i < features.getFeaturesCount(); i++) {
					
					try {

//						if (!searchLayer.getCoordinateSystem().equals(wgs84EpsgCode)) {
//							
//							ICoordinateSystemTransformers coordinateTransformers = new CoordinateSystemTransformers();
//							
//							IFeature transformedFeature = coordinateTransformers.FeatureCoordinateTransformer(features.getFeatureAt(i), searchLayer.getCoordinateSystem(), new SmpCoordinateSystem(wgs84EpsgCode));
//							
//							tempDistance = measure.getDistance(coordinate, transformedFeature.getDefaultGeometry().getCoordinate(), searchLayer.getCoordinateSystem());
//							
//						} else {
//						
//							tempDistance = measure.getDistance(coordinate, features.getFeatureAt(i).getDefaultGeometry().getCoordinate(), searchLayer.getCoordinateSystem());
//						}
						
						tempDistance = measure.getDistance(coordinate, features.getFeatureAt(i).getDefaultGeometry().getCoordinate(), searchLayer.getCoordinateSystem());
						
						if(minDistance > tempDistance) {
							minDistance = tempDistance;
							closestFeature = i;					
						}
					} catch (Exception ex) {

						System.out.println("Error on finding distance from given coordinates ERROR: " + ex);
					}
				}
				IFeature closestPOI = features.getFeatureAt(closestFeature);
				
				return (closestPOI);
			} else {
				
				System.out.println("There is no suitasble POI in the datastore");
			}
		}
		return null;
  	}
  	
	public IFeature findClosestPOI(IFeatureLayer searchLayer, IPoint referancePoint, List<AttributeListItem> attributeList) {
  		
  		IEqualsQuery equalsQuery = new SmpEqualsQuery();
  		
		if (attributeList.size() != 0) {
  		
			List<IFilter> filters = new ArrayList<IFilter>();
			
			for (int i = 0; i < attributeList.size(); i++) {
			
				filters.add(equalsQuery.equalsFilter((ILayerAttribute)attributeList.get(i).getLayerAttribute(),	attributeList.get(i).getAttributeValue() ) );
				
				equalsQuery = new SmpEqualsQuery();
			}
			IFilterBuilder filterBuilder = new SmpFilterBuilder();
			
			IFilter totalFilter = filterBuilder.addAndFilterList(filters);
			
			IFeatureCollection features = searchLayer.getFeaturesByBRectAndFilter(null, totalFilter);
			
			if (features.getFeaturesCount() != 0) {
				
				IMeasureTools measure = new SmpMeasureTools();
				
				double minDistance = Double.MAX_VALUE;
				double tempDistance = Double.MAX_VALUE;
				int closestFeature = -1;
				
				ICoordinate coordinate = new SmpCoordinate(referancePoint.getX(), referancePoint.getY());
				
				for (int i = 0; i < features.getFeaturesCount(); i++) {
					
					try {
						
//						if (!searchLayer.getCoordinateSystem().equals(wgs84EpsgCode)) {
//							
//							ICoordinateSystemTransformers coordinateTransformers = new CoordinateSystemTransformers();
//							
//							IFeature transformedFeature = coordinateTransformers.FeatureCoordinateTransformer(features.getFeatureAt(i), searchLayer.getCoordinateSystem(), new SmpCoordinateSystem(wgs84EpsgCode));
//							
//							tempDistance = measure.getDistance(coordinate, transformedFeature.getDefaultGeometry().getCoordinate(), searchLayer.getCoordinateSystem());
//							
//						} else {
//						
//							tempDistance = measure.getDistance(coordinate, features.getFeatureAt(i).getDefaultGeometry().getCoordinate(), searchLayer.getCoordinateSystem());
//						}
						
						tempDistance = measure.getDistance(coordinate, features.getFeatureAt(i).getDefaultGeometry().getCoordinate(), searchLayer.getCoordinateSystem());
						
						if(minDistance > tempDistance) {
							
							minDistance = tempDistance;
							closestFeature = i;					
						}
					} catch (Exception ex) {

						System.out.println("Error on finding distance from given coordinates ERROR: " + ex);
					}
				}
				try{
					
					IFeature closestPOI = features.getFeatureAt(closestFeature);
					
					return (closestPOI);
				} catch (Exception ex) {
					
					ex.printStackTrace();
				}
			} else {
				
				System.out.println("There is no suitasble POI in the datastore");
			}
		}
		return null;
  	}
	
}
