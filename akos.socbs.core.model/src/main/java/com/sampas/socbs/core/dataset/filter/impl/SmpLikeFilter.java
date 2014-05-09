package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.feature.Feature;
import org.geotools.filter.Expression;
import org.geotools.filter.IllegalFilterException;
import org.geotools.filter.LikeFilter;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;

@SuppressWarnings("deprecation")
public class SmpLikeFilter extends SmpGFilter {

	private LikeFilter geoToolsLikeFilter = null;

	public SmpLikeFilter() {

	}

	public SmpLikeFilter(LikeFilter geoToolsLikeFilter) {

		this.geoToolsLikeFilter = geoToolsLikeFilter;
	}

	public LikeFilter getGeotoolsLikeFilter() {
		
		return (this.geoToolsLikeFilter);
	}
	
	public void setPattern(String pattern, String wildcardMulti, String wildcardSingle, String escape) {
		
		this.geoToolsLikeFilter.setPattern(pattern, wildcardMulti, wildcardSingle, escape);
	}

	public String getWildcardMulti() {
		
		return (this.geoToolsLikeFilter.getWildcardMulti());
	}

	public String getEscape() {
		
		return (this.geoToolsLikeFilter.getEscape());
	}

	public void setPattern(SmpExpression smpExpression, String wildcardMulti, String wildcardSingle, String escape) {
		
		Expression pattern = smpExpression.getGeoToolsExpression();
		
		this.geoToolsLikeFilter.setPattern(pattern, wildcardMulti, wildcardSingle, escape);
	}

	public String getPattern() {
		
		return (this.geoToolsLikeFilter.getPattern());
	}

	public void setValue(SmpExpression smpAttribute) throws IllegalFilterException {
		
		Expression attribute = smpAttribute.getGeoToolsExpression();
		
		this.geoToolsLikeFilter.setValue(attribute);
	}

	public SmpExpression getValue() {
		
		SmpExpression smpExpression = new SmpExpression(this.geoToolsLikeFilter.getValue());
		
		return (smpExpression);
	}

	public String getWildcardSingle() {
		
		return (this.getWildcardSingle());
	}

	
	public boolean contains(SmpFeature smpFeature) {
		
		Feature feature = smpFeature.getGeoToolFeature();
		
		return (this.geoToolsLikeFilter.contains(feature));
	}
}
