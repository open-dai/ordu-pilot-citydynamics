package com.sampas.socbs.core.dataset.feature.impl;


public class FeatureCloner{

	public FeatureCloner() {

	}

	public SmpFeature CloneFeature(SmpFeature smpFeature) {
		
		SmpFeature smpCloneFeature = null;
		
		try {
			
			smpCloneFeature = new SmpFeature(smpFeature.getGeoToolFeature());			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		

//		for (int i = 0; i < smpFeature.getNumberOfAttributes(); i++) {
//
//			try {
//				
//				smpCloneFeature.getGeoToolFeature().setAttribute(i, smpFeature.getGeoToolFeature().getAttribute(i));
//				
//			} catch (Exception ex) {
//				
//			}
//		}
//
//		
//		try {
//			
//			smpCloneFeature.setDefaultGeometry(smpFeature.getDefaultGeometry());			
//			
//		} catch (Exception ex) {
//			
//		}
		
		return (smpCloneFeature);

	}

}
