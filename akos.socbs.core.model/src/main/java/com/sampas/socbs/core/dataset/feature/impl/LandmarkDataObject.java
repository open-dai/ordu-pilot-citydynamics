package com.sampas.socbs.core.dataset.feature.impl;

import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IPoint;

public class LandmarkDataObject {

	private String id;
	private String description = "";
	private String name = "";
	private IEnvelope visibleBound;
	private String epsgCode = "";
	private IPoint point;

	public LandmarkDataObject(String landmarkId, IEnvelope visibleBound,
			IPoint point ) {

		this.id = landmarkId;
		this.visibleBound = visibleBound;
		this.point = point;
		
	}
	
	public LandmarkDataObject(String landmarkId,String description, IEnvelope visibleBound,
			IPoint point) {

		this.id = landmarkId;
		this.visibleBound = visibleBound;
		this.point = point;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IEnvelope getVisibleBound() {
		return visibleBound;
	}

	public void setVisibleBound(IEnvelope visibleBound) {
		this.visibleBound = visibleBound;
	}

	public void ac_changeLandmarkerInfo() {

	}

	public void setEpsgCode(String epsgCode) {
		this.epsgCode = epsgCode;
	}

	public String getEpsgCode() {
		return epsgCode;
	}

	public void setPoint(IPoint point) {
		this.point = point;
	}

	public IPoint getPoint() {
		return point;
	}

}
