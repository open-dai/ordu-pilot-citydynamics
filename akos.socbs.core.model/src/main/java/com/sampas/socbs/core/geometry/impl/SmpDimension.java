package com.sampas.socbs.core.geometry.impl;


import com.sampas.socbs.core.geometry.IDimension;

public class SmpDimension implements IDimension{

	private double width 	= 0;
	private double height 	= 0;
	
	public SmpDimension() {
		
	}
	
	public SmpDimension(double width, double height) {
		
		this.width = width;
		this.height = height;
	}
	
	public double getHeight() {

		return this.height;
	}

	
	public double getWidth() {

		return this.width;
	}
	
	
	public void setHeight(double height) {
		
		this.height =height;
	}

	
	public void setWidth(double width) {

		this.width = width;
	}

}
