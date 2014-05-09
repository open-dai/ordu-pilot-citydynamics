package com.sampas.socbs.geolsa.model;

public class MdLayerAttributeVisibleName {

	private long oid;
	private String attributeName;
	private boolean attributeVisibility;
	private String attributeVisibleName;
	private String specificFunction;
	private boolean select;

	public MdLayerAttributeVisibleName(){

	}

	public void finalize() throws Throwable {

	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public boolean getAttributeVisibility() {
		return attributeVisibility;
	}

	public void setAttributeVisibility(boolean attributeVisibility) {
		this.attributeVisibility = attributeVisibility;
	}

	public String getAttributeVisibleName() {
		return attributeVisibleName;
	}

	public void setAttributeVisibleName(String attributeVisibleName) {
		this.attributeVisibleName = attributeVisibleName;
	}

	public String getSpecificFunction() {
		return specificFunction;
	}

	public void setSpecificFunction(String specificFunction) {
		this.specificFunction = specificFunction;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

}