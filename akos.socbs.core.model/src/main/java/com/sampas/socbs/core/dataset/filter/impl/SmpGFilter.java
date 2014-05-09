package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.feature.Feature;
import org.geotools.filter.Filter;
import org.geotools.filter.FilterVisitor;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;

@SuppressWarnings("deprecation")
public class SmpGFilter extends SmpFilterType {

	private Filter geoToolsFilter = null;
	
    public SmpGFilter() {	
		
	}
    
	public SmpGFilter(Filter geoToolsFilter) {
		
		this.geoToolsFilter = geoToolsFilter;
	}
	
	public Filter getGeoToolsFilter() {
		
		return (this.geoToolsFilter);
	}
		
    static final org.opengis.filter.Filter ALL = org.opengis.filter.Filter.EXCLUDE;
    
    static final org.opengis.filter.Filter NONE = org.opengis.filter.Filter.INCLUDE;

	boolean evaluate(SmpFeature smpFeature) {
    	
    	Feature feature = smpFeature.getGeoToolFeature();
    	
    	return (this.geoToolsFilter.evaluate(feature));
    }

	public boolean contains(SmpFeature smpFeature) {
    	
    	Feature feature = smpFeature.getGeoToolFeature();
    	
    	return (this.geoToolsFilter.contains(feature));
    }

	public SmpGFilter and(SmpFilter smpFilter) {
    	
    	org.opengis.filter.Filter ogisFilter = smpFilter.getGeoToolsFilter();
    	
    	Filter filter = this.geoToolsFilter.and(ogisFilter);
    		
    	SmpGFilter smpGFilter = new SmpGFilter(filter);
    	
    	return (smpGFilter);    	
    }

	public SmpGFilter or(SmpFilter smpFilter) {
    	
    	org.opengis.filter.Filter ogisFilter = smpFilter.getGeoToolsFilter();
    	
    	Filter filter = this.geoToolsFilter.or(ogisFilter);
    		
    	SmpGFilter smpGFilter = new SmpGFilter(filter);
    	
    	return (smpGFilter);    
    }

	public SmpGFilter not() {
    	
    	SmpGFilter smpGFilter = new SmpGFilter(this.geoToolsFilter.not());
    	
    	return (smpGFilter);
    }

	public short getFilterType() { 
    	
    	return (this.geoToolsFilter.getFilterType());
    }

	public void accept(SmpFilterVisitor smpFilterVisitor) {
    	
    	FilterVisitor filterVisitor = smpFilterVisitor.getGeotoolsFilterVisitor();
    	
    	this.geoToolsFilter.accept(filterVisitor);
    }
    
}
