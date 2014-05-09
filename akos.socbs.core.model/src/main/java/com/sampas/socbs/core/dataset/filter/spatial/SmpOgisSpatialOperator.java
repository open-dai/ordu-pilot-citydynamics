package com.sampas.socbs.core.dataset.filter.spatial;

import org.opengis.filter.spatial.SpatialOperator;

import com.sampas.socbs.core.dataset.filter.impl.SmpGFilter;

public class SmpOgisSpatialOperator extends SmpGFilter {
	
	private SpatialOperator openGisSpatialOperator = null;

	public SmpOgisSpatialOperator() {

	}

	public SmpOgisSpatialOperator(SpatialOperator openGisSpatialOperator) {

		this.openGisSpatialOperator = openGisSpatialOperator;
	}

	public SpatialOperator getOpenGisSpatialOperator() {
		
		return (this.openGisSpatialOperator);
	}

}
