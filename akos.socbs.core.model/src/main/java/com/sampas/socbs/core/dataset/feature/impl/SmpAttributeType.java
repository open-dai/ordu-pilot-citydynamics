package com.sampas.socbs.core.dataset.feature.impl;

import org.geotools.feature.AttributeType;
import org.geotools.feature.AttributeTypeFactory;
import org.geotools.feature.FeatureType;
import org.geotools.feature.IllegalAttributeException;
import org.opengis.filter.Filter;

import com.sampas.socbs.core.dataset.feature.IAttributeType;
import com.sampas.socbs.core.dataset.feature.IFeatureType;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.dataset.filter.impl.SmpFilter;

public class SmpAttributeType implements IAttributeType, AttributeType {

	private AttributeType geoToolsAttributeType = null;
	
	public SmpAttributeType() {
		
	}
	
	public SmpAttributeType(String attributeName, Class<?> clazz) {
		
		this.geoToolsAttributeType = AttributeTypeFactory.newAttributeType(attributeName, clazz);
	}
	
	public SmpAttributeType(String attributeName, IFeatureType type) {
		
		FeatureType gtType = ((SmpFeatureType)(type)).getGeoToolsFeatureType();
			
		this.geoToolsAttributeType = AttributeTypeFactory.newAttributeType(attributeName, gtType);
	}
	
	public SmpAttributeType(String attributeName, Class<?> clazz, boolean isNillable) {
		
		this.geoToolsAttributeType = AttributeTypeFactory.newAttributeType(attributeName, clazz, isNillable);
	}
	
	public SmpAttributeType(String attributeName, IFeatureType type, boolean isNillable) {
		
		FeatureType gtType = ((SmpFeatureType)(type)).getGeoToolsFeatureType();
			
		this.geoToolsAttributeType = AttributeTypeFactory.newAttributeType(attributeName, gtType, isNillable);
	}
	
	public SmpAttributeType(String attributeName, Class<?> clazz, boolean isNillable, int fieldLenght) {
		
		this.geoToolsAttributeType = AttributeTypeFactory.newAttributeType(attributeName, clazz, isNillable, fieldLenght);
	}
	
	public SmpAttributeType(String attributeName, Class<?> clazz, boolean isNillable, int fieldLenght, Object defaultValue) {
		
		this.geoToolsAttributeType = AttributeTypeFactory.newAttributeType(attributeName, clazz, isNillable, fieldLenght, defaultValue);
	}
	
	public SmpAttributeType(String attributeName, Class<?> clazz, boolean isNillable, IFilter filter, Object defaultValue, Object metaData) {
		
		Filter gtFilter = ((SmpFilter)(filter)).getGeoToolsFilter();
		
		this.geoToolsAttributeType = AttributeTypeFactory.newAttributeType(attributeName, clazz, isNillable, gtFilter, defaultValue, metaData);
	}
	
	public SmpAttributeType(String attributeName, Class<?> clazz, boolean isNillable, int fieldLenght, Object defaultValue, Object metaData) {
		
		this.geoToolsAttributeType = AttributeTypeFactory.newAttributeType(attributeName, clazz, isNillable, fieldLenght, defaultValue, metaData);
	}
	
	public SmpAttributeType(String attributeName, Class<?> clazz, boolean isNillable, IFilter filter, Object defaultValue, Object metaData, int min, int max) {
		
		Filter gtFilter = ((SmpFilter)(filter)).getGeoToolsFilter();
		
		this.geoToolsAttributeType = AttributeTypeFactory.newAttributeType(attributeName, clazz, isNillable, gtFilter, defaultValue, metaData, min, max);
	}

	
	public SmpAttributeType(AttributeType geoToolsAttributeType) {
		
		this.geoToolsAttributeType = geoToolsAttributeType;
	}
	
	public AttributeType getGeoToolsAttributeType() {
		
		return (this.geoToolsAttributeType);
	}
	
	
    public static int UNBOUNDED = Integer.MAX_VALUE;

    @SuppressWarnings("deprecation")
	public String getName() {
    	
    	return (this.geoToolsAttributeType.getName());
    }

    public String getLocalName() {
    	
    	return (this.geoToolsAttributeType.getLocalName());
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
	public Class getType() {
    	
    	return (this.geoToolsAttributeType.getType());
    }

    @SuppressWarnings("unchecked")
	public Class getBinding() {
    	
    	return (this.geoToolsAttributeType.getBinding());
    }

    public Filter getRestriction() {
    	
    	IFilter smpFilter = new SmpFilter(this.geoToolsAttributeType.getRestriction());
    	
    	return ((Filter)smpFilter);
    }

    public boolean isNillable() {
    	
    	return (this.geoToolsAttributeType.isNillable());
    }

    public int getMinOccurs() {
    
    	return (this.geoToolsAttributeType.getMinOccurs());
    }

    public int getMaxOccurs() {
    	
    	return (this.geoToolsAttributeType.getMaxOccurs());
    }

    public Object parse(Object value) throws IllegalArgumentException {
    	
    	return (this.geoToolsAttributeType.parse(value));
    }

    public void validate(Object obj) throws IllegalArgumentException {
    	
    	this.geoToolsAttributeType.validate(obj);
    }

    public Object duplicate(Object src) throws IllegalAttributeException {
    	
    	return (this.geoToolsAttributeType.duplicate(src));
    }

    public Object createDefaultValue() {
    	
    	return (this.geoToolsAttributeType.createDefaultValue());
    }

}
