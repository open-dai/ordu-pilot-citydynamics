package com.sampas.gis.ortak.model;

import java.io.Serializable;
import com.sampas.akos.common.model.BaseObject;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpPoint;


public abstract class BaseSpatialObject extends BaseObject implements Serializable {

	private static final long serialVersionUID = 7344130155970466277L;

	private IFeature feature;
	
	private ICoordinateSystem coordinateSystem;
	
	private IPoint startPoint;
	
	private IPoint endPoint;

	
	public IFeature getFeature() {
		return feature;
	}

	public void setFeature(IFeature feature) {
		this.feature = feature;
	}

	public IPoint getStartPoint() {
		
		if (getFeature() != null && !getFeature().isGeometryEmpty() && getCoordinateSystem() != null) {
			
			setStartPoint(new SmpPoint(getFeature().getDefaultGeometry().getCoordinates()[0].getX(),getFeature().getDefaultGeometry().getCoordinates()[0].getY(), getCoordinateSystem()));
		}
		return startPoint;
	}

	public void setStartPoint(IPoint startPoint) {
		this.startPoint = startPoint;
	}

	public IPoint getEndPoint() {
		
		if (getFeature() != null && !getFeature().isGeometryEmpty() && getCoordinateSystem() != null) {
			
			setEndPoint(new SmpPoint(getFeature().getDefaultGeometry().getCoordinates()[getFeature().getDefaultGeometry().getNumPoints() - 1].getX(),getFeature().getDefaultGeometry().getCoordinates()[getFeature().getDefaultGeometry().getNumPoints() - 1].getY(), getCoordinateSystem()));
		}
		return endPoint;
	}

	public void setEndPoint(IPoint endPoint) {
		this.endPoint = endPoint;
	}

	public ICoordinateSystem getCoordinateSystem() {
		return coordinateSystem;
	}
	
	public void setCoordinateSystem(ICoordinateSystem coordinateSystem) {
		this.coordinateSystem = coordinateSystem;
	}
	
}