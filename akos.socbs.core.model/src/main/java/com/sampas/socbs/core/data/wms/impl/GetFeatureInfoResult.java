package com.sampas.socbs.core.data.wms.impl;

public class GetFeatureInfoResult {
	
	private String features = null;
	private String exception = "";
	
	public void setFeatures(String features) {
		this.features = features;
	}

	public String getFeatures() {
		return features;
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
		result = prime * result
				+ ((features == null) ? 0 : features.hashCode());
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetFeatureInfoResult other = (GetFeatureInfoResult) obj;
		if (exception == null) {
			if (other.exception != null)
				return false;
		} else if (!exception.equals(other.exception))
			return false;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		return true;
	}	
}
