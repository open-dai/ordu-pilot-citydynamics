package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.feature.Feature;
import org.geotools.filter.EnvironmentVariable;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;

public class SmpEnvironmentVariable extends SmpExpression{

	private EnvironmentVariable geoToolsEnvironmentVariable = null;

	public SmpEnvironmentVariable() {

	}

	public SmpEnvironmentVariable(EnvironmentVariable geoToolsEnvironmentVariable) {

		this.geoToolsEnvironmentVariable = geoToolsEnvironmentVariable;
	}

	public EnvironmentVariable getGeotoolsEnvironmentVariable() {
		
		return (this.geoToolsEnvironmentVariable);
	}
	
	
	@SuppressWarnings("deprecation")
	public Object getValue(SmpFeature smpFeature) {
		
		Feature feature = smpFeature.getGeoToolFeature();
		
		return (this.geoToolsEnvironmentVariable.getValue(feature));		
	}
	
}
