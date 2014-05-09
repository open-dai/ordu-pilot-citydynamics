package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.feature.Feature;
import org.geotools.filter.CompareFilter;
import org.geotools.filter.Expression;
import org.geotools.filter.IllegalFilterException;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;

@SuppressWarnings("deprecation")
public class SmpCompareFilter extends SmpGFilter {
	 
	private CompareFilter geoToolsCompareFilter = null;

	public SmpCompareFilter() {

	}
	 
	public SmpCompareFilter(CompareFilter geoToolsCompareFilter) {

		this.geoToolsCompareFilter = geoToolsCompareFilter;
	}
	 
	public CompareFilter getGeotoolsCompareFilter() {
		
		return (this.geoToolsCompareFilter);
	}
     
	public void addLeftValue(SmpExpression smpLeftValue) throws IllegalFilterException {
    	
    	Expression leftValue = smpLeftValue.getGeoToolsExpression();
    	
    	this.geoToolsCompareFilter.addLeftValue(leftValue);
    }
     
	public void addRightValue(SmpExpression smpRightValue) throws IllegalFilterException {
    	
    	Expression rightValue = smpRightValue.getGeoToolsExpression(); 
    	
    	this.geoToolsCompareFilter.addRightValue(rightValue);
    }    
     
	public SmpExpression getLeftValue() {
    	
    	SmpExpression smpExpression = new SmpExpression(this.geoToolsCompareFilter.getLeftValue());
    	
    	return (smpExpression);
    }
     
	public SmpExpression getRightValue() {
    	
    	SmpExpression smpExpression = new SmpExpression(this.geoToolsCompareFilter.getRightValue());
    	
    	return (smpExpression);
    }

    	 
	public boolean contains(SmpFeature smpFeature) {
    	
    	Feature feature = smpFeature.getGeoToolFeature();
    	
    	return (this.geoToolsCompareFilter.contains(feature));
    }
	
}
