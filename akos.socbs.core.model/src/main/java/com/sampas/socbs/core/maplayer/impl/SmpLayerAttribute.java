package com.sampas.socbs.core.maplayer.impl;

import com.sampas.socbs.core.maplayer.ILayerAttribute;


public class SmpLayerAttribute implements ILayerAttribute {

	private long attributeId;
	/*Attribute Visible Name*/
	private String attributeName;
	/*Attribute Real(DB) Name*/
	private String attributeColumnName;
	private boolean attributeVisible = false;

	
	public SmpLayerAttribute() {
		
	}
	
	public SmpLayerAttribute(long attributeId,
			String attributeName,
			String attributeColumnName){
		
		this.attributeId 			= 	attributeId;
		this.attributeName			=	attributeName;
		this.attributeColumnName	=	attributeColumnName;
	}
	
	public long getAttributeId() {
		
		return (attributeId);

	}
	public void setAttributeId(long attributeId) {
		
		this.attributeId = attributeId;
	}
	public String getAttributeName() {
		
		return (attributeName);
		
	}
	public void setAttributeName(String attributeName) {
		
		this.attributeName = attributeName;
		
	}
	public String getAttributeColumnName() {
		
		return (attributeColumnName);
		
	}
	public void setAttributeColumnName(String attributeColumnName) {
		
		this.attributeColumnName = attributeColumnName;
		
	}

	public boolean isAttributeVisible() {
		
		return this.attributeVisible;
	}

	public void setAttributeVisible(boolean isAttributeVisible) {
		
		this.attributeVisible = isAttributeVisible;
		
	}

	public boolean isEmpty() {
		if(attributeColumnName == null || attributeColumnName.equals("")){
			return true;
		}
		return false;
	}
			
}
