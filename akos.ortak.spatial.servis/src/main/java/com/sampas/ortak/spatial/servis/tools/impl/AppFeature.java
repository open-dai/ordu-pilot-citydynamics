package com.sampas.ortak.spatial.servis.tools.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import com.sampas.ortak.spatial.servis.tools.IAppFeature;
import com.sampas.ortak.spatial.servis.tools.IAppFeatureLayer;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureID;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureID;


public class AppFeature implements IAppFeature {

	private Object akosObject = null;
	
	private IFeature feature = null;

	private static String ID_GLOBAL_METHOD_NAME = "getId";
	
	@SuppressWarnings("static-access")
	public AppFeature(Object akosObject, IAppFeatureLayer appFeatureLayer) {
		
		this.akosObject = akosObject;
		
		try {
			
			Long objectID = -1L;
			
			List<IFeatureID> featureIDs = new ArrayList<IFeatureID>();
			
			Method methods[] = this.akosObject.getClass().getDeclaredMethods();
			
			for (int i = 0; i < methods.length; i++) {
				
				if (methods[i].getName().equals(this.ID_GLOBAL_METHOD_NAME)){
					
					objectID = (Long)methods[i].invoke(this.akosObject,  new Object[]{});
					break;
				}
			}
			
			if (objectID != null) {
			
				featureIDs.add(new SmpFeatureID(objectID.toString()));
				
				
				IFeatureCollection features = appFeatureLayer.getFeatureLayer().getFeaturesbyFIDs(featureIDs);
				
				if (features.getFeaturesCount() == 1) {
					
					this.feature = features.getFeatureAt(0);
				} else if (features.getFeaturesCount() > 1) {
					
					System.out.println("Multible record detected by id : " + objectID + " on layer :" + appFeatureLayer.getLayerSystemName());
				} else {
					
					System.out.println("No feature found by id : " + objectID);
				}
			} else {
				
				System.out.println("Error on Object, has no ID column, please check !");
			}
		} catch (Exception ex) {
			
			System.out.println("Error on finding reletad feature to object ! ERROR : " + ex);
		}
	}
	
	public IFeature getFeature() {
		return this.feature;
	}

	public Object getAkosObject() {
		return akosObject;
	}
	
}
