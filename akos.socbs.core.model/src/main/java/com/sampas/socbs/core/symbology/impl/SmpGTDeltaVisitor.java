package com.sampas.socbs.core.symbology.impl;

import org.geotools.event.GTDelta;
import org.geotools.event.GTDeltaVisitor;

public class SmpGTDeltaVisitor {

	private GTDeltaVisitor geoToolsGTDeltaVisitor = null;

	public SmpGTDeltaVisitor() {

	}

	public SmpGTDeltaVisitor(GTDeltaVisitor geoToolsGTDeltaVisitor) {

		this.geoToolsGTDeltaVisitor = geoToolsGTDeltaVisitor;
	}

	public GTDeltaVisitor getGeotoolsGTDeltaVisitor() {
		
		return (this.geoToolsGTDeltaVisitor);
	}
	
	public boolean visit(SmpGTDelta smpDelta) {
		
		GTDelta dTDelta = smpDelta.getGeotoolsGTDelta();
		
		return (this.geoToolsGTDeltaVisitor.visit(dTDelta));
	}
	
}
