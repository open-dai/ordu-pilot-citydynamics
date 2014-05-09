package com.sampas.socbs.core.gisdatabase.tools.servis;

import com.sampas.socbs.core.dataset.feature.IAttributeType;

public interface IAttributeItem {

	public IAttributeType getAttributeTypes();
	
	public void setAttributeTypes(IAttributeType attributeTypes);

	public IClassType getClassType();

	public void setClassType(IClassType classType);
	
}
