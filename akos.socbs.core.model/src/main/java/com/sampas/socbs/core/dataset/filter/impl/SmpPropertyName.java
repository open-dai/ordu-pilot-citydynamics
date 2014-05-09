package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.expression.PropertyName;


public class SmpPropertyName  extends SmpExpression{

	private PropertyName geoToolsPropertyName = null;

	public SmpPropertyName() {

	}

	public SmpPropertyName(PropertyName geoToolsPropertyName) {

		this.geoToolsPropertyName = geoToolsPropertyName;
	}

	public PropertyName getGeotoolsPropertyName() {
		
		return (this.geoToolsPropertyName);
	}
	
	public String getPropertyName() {
		
		return (this.geoToolsPropertyName.getPropertyName());		
	}
	
}
