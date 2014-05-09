package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.PropertyIsNull;

import com.sampas.socbs.core.dataset.filter.expression.SmpOgisExpression;

public class SmpPropertyIsNull extends SmpGFilter {

	private PropertyIsNull geoToolsPropertyIsNull = null;

	public SmpPropertyIsNull() {

	}

	public SmpPropertyIsNull(PropertyIsNull geoToolsPropertyIsNull) {

		this.geoToolsPropertyIsNull = geoToolsPropertyIsNull;
	}

	public PropertyIsNull getGeotoolsPropertyIsNull() {
		
		return (this.geoToolsPropertyIsNull);
	}
	
	public SmpOgisExpression getExpression() {
		
		SmpOgisExpression smpOgisExpression = new SmpOgisExpression(this.geoToolsPropertyIsNull.getExpression());
		
		return (smpOgisExpression);
	}
	
}
