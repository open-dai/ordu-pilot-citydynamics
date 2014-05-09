package com.sampas.socbs.core.gisdatabase.tools.servis.impl;


import com.sampas.socbs.core.dataset.feature.IAttributeType;
import com.sampas.socbs.core.gisdatabase.tools.servis.IAttributeItem;
import com.sampas.socbs.core.gisdatabase.tools.servis.IClassType;

public class SmpAttributeItem implements IAttributeItem {

	private IAttributeType attributeTypes;
	
	private IClassType classType;

	public IAttributeType getAttributeTypes() {
		return attributeTypes;
	}

	public void setAttributeTypes(IAttributeType attributeTypes) {
		this.attributeTypes = attributeTypes;
	}

	public IClassType getClassType() {
		return classType;
	}

	public void setClassType(IClassType classType) {
		this.classType = classType;
	}
	
}
