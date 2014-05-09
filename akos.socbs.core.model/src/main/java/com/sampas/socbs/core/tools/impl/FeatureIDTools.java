package com.sampas.socbs.core.tools.impl;

import com.sampas.socbs.core.dataset.feature.IFeatureID;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureID;
import com.sampas.socbs.core.tools.IFeatureIDTools;


public class FeatureIDTools implements IFeatureIDTools {

	public Long getFeatureIDFromFID(IFeatureID featureID) {

		return (parseFID(featureID.getFeatureId()));
	}

	public Long getFeatureIDFromStr(String featureID) {
		
		return (parseFID(featureID));
	}
	
	public IFeatureID createFeatureIDWithFTypeAndID(String featureType, Long id) {
		
		return (createFID(featureType, id));
	}
	
	public IFeatureID createFeatureIDWithLayerNameAndID(String layerName, Long id) {
		
		String featureType = layerName.split(":")[1];
		
		return (createFID(featureType, id));
	}


	private IFeatureID createFID(String featureType, Long id) {
		
		return (new SmpFeatureID(featureType + "." + id));
	}

	private Long parseFID(String featureID) {
		
		featureID = featureID.trim();
		
		int pointLoc = featureID.indexOf(".");
		
		String idPart = featureID.substring(pointLoc + 1);
		
		try {
			
			Long id = Long.parseLong(idPart);
			
			return id;
			
		} catch (Exception ex) {

			System.out.println("Error on converting id string to long check an numeric column is primary key ! ERROR " + ex);
			return null;
		}
	}
	
	public String getFeatureTypeFromLayerName(String layerName) {
		
		layerName = layerName.trim();
		
		int doublePointLoc = layerName.indexOf(":");
		
		return layerName.substring(doublePointLoc +1);
	}
	
}
