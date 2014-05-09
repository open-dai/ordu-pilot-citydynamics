package com.sampas.socbs.core.dataset.filter.impl;

import java.util.List;

import org.opengis.filter.BinaryLogicOperator;

public class SmpBinaryLogicOperator extends SmpGFilter{

	private BinaryLogicOperator geoToolsBinaryLogicOperator = null;

	public SmpBinaryLogicOperator() {

	}

	public SmpBinaryLogicOperator(BinaryLogicOperator geoToolsBinaryLogicOperator) {

		this.geoToolsBinaryLogicOperator = geoToolsBinaryLogicOperator;
	}

	public BinaryLogicOperator getGeotoolsBinaryLogicOperator() {
		
		return (this.geoToolsBinaryLogicOperator);
	}
	
	@SuppressWarnings("unchecked")
	public List getChildren() {
		
		return (this.geoToolsBinaryLogicOperator.getChildren());
	}
	
}
