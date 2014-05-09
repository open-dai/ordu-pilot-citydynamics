package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.PropertyIsLike;

import com.sampas.socbs.core.dataset.filter.expression.SmpOgisExpression;

public class SmpPropertyIsLike  extends SmpGFilter{
	
	private PropertyIsLike geoToolsPropertyIsLike = null;

	public SmpPropertyIsLike() {

	}

	public SmpPropertyIsLike(PropertyIsLike geoToolsPropertyIsLike) {

		this.geoToolsPropertyIsLike = geoToolsPropertyIsLike;
	}

	public PropertyIsLike getGeotoolsPropertyIsLike() {
		
		return (this.geoToolsPropertyIsLike);
	}

	public SmpOgisExpression getExpression() {		
		
		SmpOgisExpression smpOgisExpression = new SmpOgisExpression(this.geoToolsPropertyIsLike.getExpression());
		
		return (smpOgisExpression);		
	}

    public String getLiteral() {
    	
    	return (this.geoToolsPropertyIsLike.getLiteral());
    }

    public String getWildCard() {
    	
    	return (this.geoToolsPropertyIsLike.getWildCard());
    }

    public String getSingleChar() {

    	return (this.geoToolsPropertyIsLike.getSingleChar());
    }

    public String getEscape() {
    	
    	return (this.geoToolsPropertyIsLike.getEscape());
    }
}
