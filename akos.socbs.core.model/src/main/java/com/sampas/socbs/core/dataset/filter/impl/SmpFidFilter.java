package com.sampas.socbs.core.dataset.filter.impl;

import java.util.Collection;

import org.geotools.feature.Feature;
import org.geotools.filter.FidFilter;
import org.geotools.filter.Filter;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;

@SuppressWarnings("deprecation")
public class SmpFidFilter extends SmpGFilter {


	private FidFilter geoToolsFidFilter = null;
	
	public SmpFidFilter() {
				
	}
	
	public SmpFidFilter(FidFilter geoToolsFidFilter) {
		
		this.geoToolsFidFilter = geoToolsFidFilter;
	}
	
	public FidFilter getGeoToolsFidFilter() {
		
		return (this.geoToolsFidFilter);
	}
	
	
	public Filter getGeoToolsFilter() {
		
		return (this.geoToolsFidFilter);
	}
	
	
	public boolean contains(SmpFeature smpFeature) {
		
		Feature feature = smpFeature.getGeoToolFeature();
		
		return (this.geoToolsFidFilter.contains(feature));
	}

	public void addFid(String fid) {
				
		this.geoToolsFidFilter.addFid(fid);
	}

	public String[] getFids() {
		
		return (this.geoToolsFidFilter.getFids());
	}

	@SuppressWarnings("unchecked")
	public void addAllFids(Collection fidsToAdd) {
		
		this.geoToolsFidFilter.addAllFids(fidsToAdd);
	}

	@SuppressWarnings("unchecked")
	public void removeAllFids(Collection fidsToRemove) {
		
		this.geoToolsFidFilter.removeAllFids(fidsToRemove);
	}

	public void removeFid(String fid) {
		
		this.geoToolsFidFilter.removeFid(fid);
	}
	
}
