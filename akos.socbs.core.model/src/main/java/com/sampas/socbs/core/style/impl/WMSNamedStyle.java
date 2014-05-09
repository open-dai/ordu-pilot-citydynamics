package com.sampas.socbs.core.style.impl;

import com.sampas.socbs.core.style.IWMSNamedStyle;

public class WMSNamedStyle implements IWMSNamedStyle {

	private String name = null;
	
	private int id = -1;
	
	public WMSNamedStyle(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
