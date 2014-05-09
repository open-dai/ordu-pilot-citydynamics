package com.sampas.socbs.core.data.wms.impl;

import java.util.Arrays;

public class GetMapResult {
	
	private byte[] image = null;
	private String exception = "";
	
	public void setImage(byte[] image) {
		this.image = image;
	}

	public byte[] getImage() {
		return image;
	}
	
	public void setException(String exception) {
		this.exception = exception;
	}

	public String getException() {
		return exception;
	}

	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((exception == null) ? 0 : exception.hashCode());
		result = prime * result + Arrays.hashCode(image);
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetMapResult other = (GetMapResult) obj;
		if (exception == null) {
			if (other.exception != null)
				return false;
		} else if (!exception.equals(other.exception))
			return false;
		if (!Arrays.equals(image, other.image))
			return false;
		return true;
	}	
}