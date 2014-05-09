package com.sampas.socbs.core.dataset.feature.impl;

import org.geotools.feature.GeometryAttributeType;
import org.opengis.filter.Filter;

import com.sampas.socbs.core.dataset.filter.impl.SmpFilter;

public class SmpGeometryAttributeType extends SmpAttributeType {

	private GeometryAttributeType geoToolsGeometryAttributeType = null;

	public SmpGeometryAttributeType() {

	}

	public SmpGeometryAttributeType(GeometryAttributeType geoToolsGeometryAttributeType) {

		this.geoToolsGeometryAttributeType = geoToolsGeometryAttributeType;
	}

	public GeometryAttributeType getGeotoolsGeometryAttributeType() {
		
		return (this.geoToolsGeometryAttributeType);
	}
	
    
	@SuppressWarnings("unchecked")
	public Class getType() {
    	
    	return (this.geoToolsGeometryAttributeType.getType());
    }

    
	public Filter getRestriction() {
	
	SmpFilter smpFilter = new SmpFilter(this.geoToolsGeometryAttributeType.getRestriction());
	
	return ((Filter)smpFilter);
	}
	
//	public SmpFilter getRestriction() {
//    	
//    	SmpFilter smpFilter = new SmpFilter(this.geoToolsGeometryAttributeType.getRestriction());
//    	
//    	return (smpFilter);
//    }
    
    
//Ignored by Sampas GIS developer group
//    public CoordinateReferenceSystem getCoordinateSystem() {
//    	
//    	
//    }
//
//    public GeometryFactory getGeometryFactory() {
//    	
//    	
//    }

    @SuppressWarnings("deprecation")
	public boolean isGeometry() {
    	
    	return (this.geoToolsGeometryAttributeType.isGeometry());
    }
	
}
