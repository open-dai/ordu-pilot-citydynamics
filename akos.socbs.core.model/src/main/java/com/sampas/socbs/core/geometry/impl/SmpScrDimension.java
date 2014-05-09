package com.sampas.socbs.core.geometry.impl;

public class SmpScrDimension {

	private int width = 0;
	private int height = 0;
	
	
	public SmpScrDimension() {
		
	}
	
	public SmpScrDimension(int width, int heigth) {
		
		this.width = width;
		this.height = heigth;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeigth(int height) {
		this.height = height;
	}
	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}
	
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmpScrDimension other = (SmpScrDimension) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}
	
	
	
}
