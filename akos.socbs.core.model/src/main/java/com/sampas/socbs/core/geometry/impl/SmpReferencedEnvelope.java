package com.sampas.socbs.core.geometry.impl;

import org.geotools.geometry.jts.ReferencedEnvelope;

import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IReferencedEnvelope;

public class SmpReferencedEnvelope implements IReferencedEnvelope {

	private ReferencedEnvelope geoToolsReferencedEnvelope = null;
	
	public SmpReferencedEnvelope(ReferencedEnvelope geoToolsReferencedEnvelope) {
		
		this.geoToolsReferencedEnvelope = geoToolsReferencedEnvelope;
	}
	
	public ReferencedEnvelope getGeoToolsReferencedEnvelope() {
		
		return (this.geoToolsReferencedEnvelope);
	}
	
	public IEnvelope getEnvelope() {
		
		IEnvelope envelope = new SmpEnvelope(this.geoToolsReferencedEnvelope.getMinX(), this.geoToolsReferencedEnvelope.getMinY(), this.geoToolsReferencedEnvelope.getMaxX(), this.geoToolsReferencedEnvelope.getMaxY());
		
		return (envelope);
		
	}
		
}
