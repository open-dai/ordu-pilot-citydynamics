package com.sampas.socbs.poi.model;

import com.sampas.gis.ortak.basic.model.geometry.SmpCoordinate;


public class JointFeature {

	private String typeName;

	private Long id;

	private SmpCoordinate centerCoordinate;


	public JointFeature() {
		
	}
	
	public JointFeature(Long id, String typeName, SmpCoordinate coordinate) {
		
		this.id = id;
		
		this.typeName = typeName;
		
		this.centerCoordinate = coordinate;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SmpCoordinate getCenterCoordinate() {
		return centerCoordinate;
	}

	public void setCenterCoordinate(SmpCoordinate centerCoordinate) {
		this.centerCoordinate = centerCoordinate;
	}

}