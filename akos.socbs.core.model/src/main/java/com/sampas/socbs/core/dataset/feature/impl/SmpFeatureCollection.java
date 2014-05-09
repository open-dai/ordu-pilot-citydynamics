package com.sampas.socbs.core.dataset.feature.impl;

import java.util.ArrayList;
import java.util.List;

import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureCollections;
import org.geotools.feature.FeatureIterator;
import org.opengis.filter.Filter;

import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.filter.impl.SmpGFilter;


public class SmpFeatureCollection implements IFeatureCollection{
	

	//Last change start, date 16-12-2010
//	private FeatureCollection geoToolsFeatureCollection = null;
	private FeatureCollection geoToolsFeatureCollection = FeatureCollections.newCollection(); 
	//Last change end	
	private List<IFeature> smpFeatures = new ArrayList<IFeature>();
	
	public SmpFeatureCollection() {
		
	}
	
	public SmpFeatureCollection(FeatureCollection geoToolsFeatureCollection) {
		
		try {
			
			this.geoToolsFeatureCollection = geoToolsFeatureCollection;
			
			FeatureIterator featureIterator = geoToolsFeatureCollection.features();
			
			while (featureIterator.hasNext()) {
			
				SmpFeature smpFeature = new SmpFeature(featureIterator.next());
			
				this.smpFeatures.add(smpFeature);
					
			}
		} catch (Exception ex) {
			
			System.out.println("Error on binding features. Check layer data geometry types");
		}
	}
	
	public FeatureCollection getGeoToolsFeatureCollection() {
		
		return (this.geoToolsFeatureCollection);
	}
	
	public IFeature getFeatureAt(int index) {
		
		if(this.smpFeatures!=null){
			
			if(this.smpFeatures.size()>index){
				
				return (this.smpFeatures.get(index));
			}
		}
		return null;	
	}
	
	@SuppressWarnings("unchecked")
	public void addFeature(IFeature smpfeature) {
		
		this.smpFeatures.add((SmpFeature)smpfeature);
		//Last change start, date 16-12-2010
		if(((SmpFeature)smpfeature).getGeoToolFeature() != null) {
			
			this.geoToolsFeatureCollection.add(((SmpFeature)smpfeature).getGeoToolFeature());
		}
		//Last change end
	}
	
	public void removeFeature(int index) {
		
		this.smpFeatures.remove(index);
	}
	
	public int getFeaturesCount() {
		
		return (this.smpFeatures.size());
	}
	
	public SmpFeatureType getSchema() {
		
		SmpFeatureType smpFeatureType = new SmpFeatureType(this.geoToolsFeatureCollection.getSchema());
		
		return (smpFeatureType);
	}
	
	public SmpFeatureCollection subCollection(SmpGFilter smpGFilter) {
		
		Filter filter = smpGFilter.getGeoToolsFilter();
		
		SmpFeatureCollection smpFeatureCollection = new SmpFeatureCollection(this.geoToolsFeatureCollection.subCollection(filter));
		
		return (smpFeatureCollection);
	}
	
	public IFeatureCollection cloneFeatures() {
		
		IFeatureCollection resultFeatures = new SmpFeatureCollection();
		
		try {
			
			for (IFeature tempFeature : this.smpFeatures) {
				
				resultFeatures.addFeature(tempFeature.cloneFeature());
			}
			return resultFeatures;
		} catch (Exception ex) {
			
			System.out.println("Error on cloning features ! ERROR: " + ex.getMessage());
		}
		return null;
	}
	
}
