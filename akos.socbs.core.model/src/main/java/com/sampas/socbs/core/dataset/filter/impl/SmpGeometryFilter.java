package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.feature.Feature;
import org.geotools.filter.Expression;
import org.geotools.filter.GeometryFilter;
import org.geotools.filter.IllegalFilterException;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;

@SuppressWarnings("deprecation")
public class SmpGeometryFilter extends SmpGFilter {
	
	private GeometryFilter geoToolsGeometryFilter = null;

	public SmpGeometryFilter() {

	}

	public SmpGeometryFilter(GeometryFilter geoToolsGeometryFilter) {

		this.geoToolsGeometryFilter = geoToolsGeometryFilter;
	}

	public GeometryFilter getGeotoolsGeometryFilter() {

		return (this.geoToolsGeometryFilter);
	}

	public void addRightGeometry(SmpExpression rightGeometry)	throws IllegalFilterException {
		
		Expression expression = rightGeometry.getGeoToolsExpression();
		
		this.geoToolsGeometryFilter.addRightGeometry(expression);
	}

	public void addLeftGeometry(SmpExpression leftGeometry) throws IllegalFilterException {
		
		Expression expression = leftGeometry.getGeoToolsExpression();
		
		this.geoToolsGeometryFilter.addRightGeometry(expression);
	}

	
	public boolean contains(SmpFeature smpFeature) {
		
		Feature feature = smpFeature.getGeoToolFeature();
		
		return (this.geoToolsGeometryFilter.contains(feature));
	}

	public SmpExpression getRightGeometry() {
		
		SmpExpression smpExpression = new SmpExpression(this.geoToolsGeometryFilter.getRightGeometry());
		
		return (smpExpression);
	}

	public SmpExpression getLeftGeometry() {
		
		SmpExpression smpExpression = new SmpExpression(this.geoToolsGeometryFilter.getLeftGeometry());
		
		return (smpExpression);
	}
	
}
