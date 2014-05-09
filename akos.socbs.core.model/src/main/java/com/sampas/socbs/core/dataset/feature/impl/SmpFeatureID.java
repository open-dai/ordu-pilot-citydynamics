package com.sampas.socbs.core.dataset.feature.impl;

import com.sampas.socbs.core.dataset.feature.IFeatureID;

public class SmpFeatureID implements IFeatureID{
	
	String featureId = "";

	public SmpFeatureID() {
		
	}
	
	public SmpFeatureID(String featureId) {
		
		this.featureId = featureId;
	}
	
	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}
	
	public Long getFeatureIdasLong() {

		return (parseFID(this.featureId));
	}
	
	private Long parseFID(String featureID) {
		
		featureID = featureID.trim();
		
		int pointLoc = featureID.indexOf(".");
		
		String idPart = featureID.substring(pointLoc);
		
		try {
			
			Long id = Long.parseLong(idPart);
			
			return id;
			
		} catch (Exception ex) {

			System.out.println("Error on converting id string to long check an numeric column is primary key ! ERROR " + ex);
			return null;
		}
	}

}
