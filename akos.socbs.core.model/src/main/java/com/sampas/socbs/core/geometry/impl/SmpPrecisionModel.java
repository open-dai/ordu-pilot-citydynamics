package com.sampas.socbs.core.geometry.impl;

import com.sampas.socbs.core.geometry.IPrecisionModel;
import com.vividsolutions.jts.geom.PrecisionModel;

public class SmpPrecisionModel implements IPrecisionModel {

	@SuppressWarnings("unused")
	private PrecisionModel geoToolsPrecisionModel = null;
	
	public SmpPrecisionModel(PrecisionModel geoToolsPrecisionModel) {
		
		this.geoToolsPrecisionModel = geoToolsPrecisionModel;
	}
	
	public PrecisionModel getGeoToolsPrecisionModel() {
		
		return (this.getGeoToolsPrecisionModel());
	}
	
	
}
