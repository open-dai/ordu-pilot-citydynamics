package com.sampas.gis.ortak.poi.search.servis.impl;

import com.sampas.socbs.core.maplayer.ILayerAttribute;

public class AttributeListItem {

	private ILayerAttribute layerAttribute;
	private Object attributeValue;

	public AttributeListItem(ILayerAttribute layerAttribute,Object attributeValue) {

		setAttributeValue(attributeValue);
		setLayerAttribute(layerAttribute);
	}
	
	public ILayerAttribute getLayerAttribute() {

		return this.layerAttribute;
	}

	public Object getAttributeValue() {

		return this.attributeValue;
	}

	public void setLayerAttribute(ILayerAttribute layerAttribute) {
		this.layerAttribute = layerAttribute;
	}

	public void setAttributeValue(Object attributeValue) {
		this.attributeValue = attributeValue;
	}	
}
