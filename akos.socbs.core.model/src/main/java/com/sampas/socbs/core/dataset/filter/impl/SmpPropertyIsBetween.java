package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.PropertyIsBetween;

import com.sampas.socbs.core.dataset.filter.expression.SmpOgisExpression;

public class SmpPropertyIsBetween extends SmpGFilter{

	private PropertyIsBetween geoToolsPropertyIsBetween = null;

	public SmpPropertyIsBetween() {

	}

	public SmpPropertyIsBetween(PropertyIsBetween geoToolsPropertyIsBetween) {

		this.geoToolsPropertyIsBetween = geoToolsPropertyIsBetween;
	}

	public PropertyIsBetween getGeotoolsPropertyIsBetween() {

		return (this.geoToolsPropertyIsBetween);
	}

	public SmpOgisExpression getExpression() {
		
		SmpOgisExpression smpOgisExpression = new SmpOgisExpression(this.geoToolsPropertyIsBetween.getExpression());

		return (smpOgisExpression);
	}

	public SmpOgisExpression getLowerBoundary() {
		
		SmpOgisExpression smpOgisExpression = new SmpOgisExpression(this.geoToolsPropertyIsBetween.getLowerBoundary());

		return (smpOgisExpression);
	}

	public SmpOgisExpression getUpperBoundary() {
		
		SmpOgisExpression smpOgisExpression = new SmpOgisExpression(this.geoToolsPropertyIsBetween.getUpperBoundary());

		return (smpOgisExpression);
	}

}
