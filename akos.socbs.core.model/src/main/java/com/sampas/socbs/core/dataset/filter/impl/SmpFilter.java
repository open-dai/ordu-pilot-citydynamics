package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.feature.Feature;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterVisitor;

import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;

public class SmpFilter implements IFilter {
	
	private Filter openGisFilter = null;

	public SmpFilter() {

	}

	public SmpFilter(Filter geoToolsFilter) {

		this.openGisFilter = geoToolsFilter;
	}

	public Filter getGeoToolsFilter() {
		
		return (this.openGisFilter);
	}
	
    public boolean evaluate(SmpFeature smpFeature) {
    	
    	 Feature feature = smpFeature.getGeoToolFeature();
    	 
    	 return (this.openGisFilter.evaluate(feature));
    	
    }

    public Object accept(SmpOgisFilterVisitor smpOgisFilterVisitor, Object extraData) {
    	
    	FilterVisitor filterVisitor = smpOgisFilterVisitor.getGeoToolsFilterVisitor();
    	
    	return (this.openGisFilter.accept(filterVisitor, extraData));
    }
    
}
