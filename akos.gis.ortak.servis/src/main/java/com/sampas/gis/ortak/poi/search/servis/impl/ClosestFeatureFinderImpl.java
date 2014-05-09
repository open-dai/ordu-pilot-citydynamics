package com.sampas.gis.ortak.poi.search.servis.impl;

import java.util.ArrayList;
import java.util.List;
import com.sampas.gis.ortak.poi.search.servis.IClosestFeatureFinder;
import com.sampas.socbs.base.spatial.query.services.IEqualsQuery;
import com.sampas.socbs.base.spatial.query.services.impl.SmpEqualsQuery;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.CoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.dataset.feature.IFilterBuilder;
import com.sampas.socbs.core.dataset.feature.impl.SmpFilterBuilder;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpCoordinate;
import com.sampas.socbs.core.geometry.impl.SmpEnvelope;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayerAttribute;
import com.sampas.socbs.core.processing.services.IMeasureTools;
import com.sampas.socbs.core.processing.services.impl.SmpMeasureTools;


public class ClosestFeatureFinderImpl implements IClosestFeatureFinder {

	ICoordinateSystemTransformers coordinateSystemTransformer = new CoordinateSystemTransformers();
	
	ICoordinateSystem wgs84CRS = new SmpCoordinateSystem("EPSG:4326");
	
	
	public IFeature findClosestFeature(IFeatureLayer searchLayer, IPoint referancePoint, double offset, List<AttributeListItem> attributeList) {
	
 		IEnvelope searchEnvelope = new SmpEnvelope(referancePoint.getX() - offset, referancePoint.getY() - offset, referancePoint.getX() + offset, referancePoint.getY() + offset);
		
		if (!searchLayer.getCoordinateSystem().getEPSGCode().equals(referancePoint.getCoordinateSystem().getEPSGCode())) {
			
			try {
				
				searchEnvelope = coordinateSystemTransformer.SmpBoundingRectangleCoordinateTransformer(searchEnvelope, referancePoint.getCoordinateSystem(), searchLayer.getCoordinateSystem());
			} catch (Exception ex) {
				
				System.err.println("Error on transforming envelope !!!");
				
				ex.printStackTrace();
			}
		} else {
			
			//No need to change envelope
		}
  		IEqualsQuery equalsQuery = new SmpEqualsQuery();
  		
  		IFeatureCollection candidatefeatures;
  		
		if (attributeList != null && attributeList.size() != 0) {
  		
			List<IFilter> filters = new ArrayList<IFilter>();
			
			for (int i = 0; i < attributeList.size(); i++) {
			
				filters.add(equalsQuery.equalsFilter((ILayerAttribute)attributeList.get(i).getLayerAttribute(),	attributeList.get(i).getAttributeValue() ) );
				
				equalsQuery = new SmpEqualsQuery();
			}
			IFilterBuilder filterBuilder = new SmpFilterBuilder();
			
			IFilter totalFilter = filterBuilder.addAndFilterList(filters);
		
			candidatefeatures = searchLayer.getFeaturesByBRectAndFilter(searchEnvelope, totalFilter);
		} else {

			candidatefeatures = searchLayer.getFeaturesByBRectAndFilter(searchEnvelope, null);
		}
		if (candidatefeatures != null && candidatefeatures.getFeaturesCount() != 0) {
			
			IMeasureTools measure = new SmpMeasureTools();
			
			double minDistance = Double.MAX_VALUE;
			
			double tempDistance = Double.MAX_VALUE;
			
			int closestFeature = -1;
			
			IPoint searchPoint = null;
			
			if (!searchLayer.getCoordinateSystem().getEPSGCode().equals(referancePoint.getCoordinateSystem().getEPSGCode())) {
				
				try {
					
					searchPoint = coordinateSystemTransformer.SmpGeometryCoordinateTransformer(referancePoint, referancePoint.getCoordinateSystem(), searchLayer.getCoordinateSystem());
				} catch (Exception ex) {
					
					System.err.println("Error on transforming point !!!");
					
					ex.printStackTrace();
				}
			} else {
				
				searchPoint = referancePoint;
			}
			ICoordinate coordinate = new SmpCoordinate(searchPoint.getX(), searchPoint.getY());
			
			for (int i = 0; i < candidatefeatures.getFeaturesCount(); i++) {
				
				try {
					
					tempDistance = measure.getDistance(coordinate, candidatefeatures.getFeatureAt(i).getDefaultGeometry().getCoordinate(), searchLayer.getCoordinateSystem());
					
					if(minDistance > tempDistance) {
						
						minDistance = tempDistance;
						
						closestFeature = i;					
					}
				} catch (Exception ex) {

					System.out.println("Error on finding distance from given coordinates ERROR: " + ex.getMessage());
				}
			}
			try{
				
				return candidatefeatures.getFeatureAt(closestFeature);
			} catch (Exception ex) {
				
				ex.printStackTrace();
			}
		} else {
			
			System.out.println("There is no suitasble Feature in the datastore");
		}
		return null;
  	}

	public IFeature findClosestFeature(IFeatureLayer searchLayer, IPoint referancePoint, double offset) {
		
		return findClosestFeature(searchLayer, referancePoint, offset, null);
	}

}
