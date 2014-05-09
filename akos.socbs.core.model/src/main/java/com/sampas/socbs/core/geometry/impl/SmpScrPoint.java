package com.sampas.socbs.core.geometry.impl;

public class SmpScrPoint {

	private int x = 0;
	private int y = 0;
	private int z = 0;
	
	public SmpScrPoint() {
		
	}
	
	public SmpScrPoint(int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	
	public SmpScrPoint(int x, int y, int z) {
		
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getScrX() {
	
		return x;
	}
	
	public int getScrY() {
		
		return y;
	}
	
	public int getScrZ() {
		
		return z;
	}
	
	public void setScrX(int x) {
		
		this.x = x;
	}
	
	public void setScrY(int y) {
		
		this.y = y;
	}
	
	public void setScrZ(int z) {
		
		this.z = z;
	}

	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmpScrPoint other = (SmpScrPoint) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}
	
	
}
