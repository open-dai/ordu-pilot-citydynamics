package com.sampas.socbs.core.geometry.impl;

import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.CoordinateSequenceFilter;

public class SmpCoordinateSequenceFilter {

	private CoordinateSequenceFilter geoToolsCoordinateSequenceFilter = null;

	public SmpCoordinateSequenceFilter() {

	}

	public SmpCoordinateSequenceFilter(
			CoordinateSequenceFilter geoToolsCoordinateSequenceFilter) {

		this.geoToolsCoordinateSequenceFilter = geoToolsCoordinateSequenceFilter;
	}

	public CoordinateSequenceFilter getGeotoolsCoordinateSequenceFilter() {

		return (this.geoToolsCoordinateSequenceFilter);
	}

	public void filter(SmpCoordinateSequence smpCoordinateSequence, int i) {
		
		CoordinateSequence coordinateSequence = (CoordinateSequence) smpCoordinateSequence.getGeotoolsCoordinateSequence();
		
		this.geoToolsCoordinateSequenceFilter.filter(coordinateSequence, i);
	}

	public boolean isDone() {
		
		return (this.geoToolsCoordinateSequenceFilter.isDone());
	}

	public boolean isGeometryChanged() {
		
		return (this.geoToolsCoordinateSequenceFilter.isGeometryChanged());
	}

}
