package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.filter.BBoxExpression;
import org.geotools.filter.IllegalFilterException;

import com.sampas.socbs.core.geometry.impl.SmpEnvelope;

public class SmpBBoxExpression extends SmpLiteralExpression {
	
	private BBoxExpression geoToolsBBoxExpression = null;

	public SmpBBoxExpression() {

	}

	public SmpBBoxExpression(BBoxExpression geoToolsBBoxExpression) {

		this.geoToolsBBoxExpression = geoToolsBBoxExpression;
	}

	public BBoxExpression getGeotoolsBBoxExpression() {
		
		return (this.geoToolsBBoxExpression);
	}
	
	
	public void setBounds(SmpEnvelope smpEnvelope) throws IllegalFilterException {
		
		this.geoToolsBBoxExpression.setBounds(smpEnvelope.getGeoToolsEnvelope());
	}
	
	
	
}
