package com.sampas.socbs.core.dataset.feature.impl;

import com.sampas.socbs.core.dataset.table.IColumn;


@SuppressWarnings("unused")
public class SmpColumn implements IColumn {
	
	private String name;
	private String type; 
	private String geometryType;
	
	public SmpColumn(String name,String type, String geometryType){
	
		this.name = name;
		this.type = type;
		this.geometryType	= geometryType;
		
	}
	public String getGeometryType() {
		// TODO Auto-generated method stub
		return this.geometryType;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return this.geometryType;
	}

	public Object get_DefaultValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEditable() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isSuitableValue() {
		// TODO Auto-generated method stub
		return false;
	}

}
