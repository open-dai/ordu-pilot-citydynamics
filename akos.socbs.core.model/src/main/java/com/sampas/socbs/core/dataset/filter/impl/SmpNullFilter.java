package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.feature.Feature;
import org.geotools.filter.Expression;
import org.geotools.filter.IllegalFilterException;
import org.geotools.filter.NullFilter;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;

@SuppressWarnings("deprecation")
public class SmpNullFilter extends SmpGFilter{
	
	private NullFilter geoToolsNullFilter = null;

	public SmpNullFilter() {

	}

	public SmpNullFilter(NullFilter geoToolsNullFilter) {

		this.geoToolsNullFilter = geoToolsNullFilter;
	}

	public NullFilter getGeoToolsNullFilter() {

		return (this.geoToolsNullFilter);
	}

	public void nullCheckValue(SmpExpression nullCheck) throws IllegalFilterException {
		
		Expression expression = nullCheck.getGeoToolsExpression();
		
		this.geoToolsNullFilter.nullCheckValue(expression);
	}

	public SmpExpression getNullCheckValue() {
		
		SmpExpression smpExpression = new SmpExpression(this.geoToolsNullFilter.getNullCheckValue());
		
		return (smpExpression);
	}

	
	public boolean contains(SmpFeature smpFeature) {
		
		Feature feature = smpFeature.getGeoToolFeature();
		
		return (this.geoToolsNullFilter.contains(feature));
	}

}
