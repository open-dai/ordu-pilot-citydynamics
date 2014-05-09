package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.feature.Feature;
import org.geotools.filter.BetweenFilter;
import org.geotools.filter.Expression;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;
import com.sampas.socbs.core.dataset.filter.expression.SmpOgisExpression;

@SuppressWarnings("deprecation")
public class SmpBetweenFilter extends SmpCompareFilter {

	private BetweenFilter geoToolsBetweenFilter = null;

	public SmpBetweenFilter() {

	}

	public SmpBetweenFilter(BetweenFilter geoToolsBetweenFilter) {

		this.geoToolsBetweenFilter = geoToolsBetweenFilter;
	}

	public BetweenFilter getGeotoolsBetweenFilter() {

		return (this.geoToolsBetweenFilter);
	}

	
	public boolean contains(SmpFeature smpFeature) {
		
		Feature feature = smpFeature.getGeoToolFeature();
		
		return (this.geoToolsBetweenFilter.contains(feature));
	}

	public SmpOgisExpression getExpression1() {
		
		SmpOgisExpression smpOgisExpression = new SmpOgisExpression(this.geoToolsBetweenFilter.getExpression1());
		
		return (smpOgisExpression);
	}

	public SmpOgisExpression getExpression2() {
		
		SmpOgisExpression smpOgisExpression = new SmpOgisExpression(this.geoToolsBetweenFilter.getExpression2());
		
		return (smpOgisExpression);
	}

	public void setExpression1(SmpOgisExpression smpOgisExpression) {
		
		org.opengis.filter.expression.Expression expression = smpOgisExpression.getGeotoolsOgisExpression();
		
		this.geoToolsBetweenFilter.setExpression1(expression);
	}

	public void setExpression2(SmpOgisExpression smpOgisExpression) {		
		
		org.opengis.filter.expression.Expression expression = smpOgisExpression.getGeotoolsOgisExpression();
		
		this.geoToolsBetweenFilter.setExpression2(expression);
	}

	public SmpExpression getMiddleValue() {
		
		SmpExpression smpExpression = new SmpExpression(this.geoToolsBetweenFilter.getMiddleValue());
		
		return (smpExpression);
	}

	public void addMiddleValue(SmpExpression middleValue) {
		
		Expression expression = middleValue.getGeoToolsExpression();
		
		this.geoToolsBetweenFilter.addMiddleValue(expression);
	}

}
