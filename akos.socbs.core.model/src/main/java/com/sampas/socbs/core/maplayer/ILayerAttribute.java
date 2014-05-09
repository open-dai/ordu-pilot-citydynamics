package com.sampas.socbs.core.maplayer;

public interface ILayerAttribute {
	
	public long getAttributeId();
	
	public void setAttributeId(long attributeId);
	
	public String getAttributeName();
	
	public void setAttributeName(String attributeName);
	
	public String getAttributeColumnName();
	
	public void setAttributeColumnName(String attributeColumnName);
	
	public void setAttributeVisible(boolean isAttributeVisible);
	
	public boolean isAttributeVisible();
	
	public boolean isEmpty();

}
