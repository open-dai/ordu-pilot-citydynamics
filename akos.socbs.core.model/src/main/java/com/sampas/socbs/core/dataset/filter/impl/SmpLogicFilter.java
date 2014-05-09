package com.sampas.socbs.core.dataset.filter.impl;

import java.util.Iterator;

import org.geotools.feature.Feature;
import org.geotools.filter.IllegalFilterException;
import org.geotools.filter.LogicFilter;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;

@SuppressWarnings("deprecation")
public class SmpLogicFilter extends SmpGFilter {
	
	private LogicFilter geoToolslogicFilter = null;

	public SmpLogicFilter() {
		
	}
	
	public SmpLogicFilter(LogicFilter geoToolslogicFilter) {
		
		this.geoToolslogicFilter = geoToolslogicFilter;
		
	}

	public LogicFilter getGeoToolsLagicFilter() {
		
		return (this.geoToolslogicFilter);
	}

    
	public boolean contains(SmpFeature smpFeature) {
    	
    	Feature feature = smpFeature.getGeoToolFeature();
    	
    	return (this.geoToolslogicFilter.contains(feature));
    }

	@SuppressWarnings("unchecked")
	public Iterator getFilterIterator() {
    	
    	return (this.geoToolslogicFilter.getFilterIterator());
    }

	public void addFilter(SmpFilter smpFilter) throws IllegalFilterException {
    	
    	org.opengis.filter.Filter filter = smpFilter.getGeoToolsFilter();
    	
    	this.geoToolslogicFilter.addFilter(filter);
    }
	
}
