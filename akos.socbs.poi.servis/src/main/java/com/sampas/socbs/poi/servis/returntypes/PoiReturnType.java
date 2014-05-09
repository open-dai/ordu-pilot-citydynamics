package com.sampas.socbs.poi.servis.returntypes;

import java.util.List;
import com.sampas.gis.ortak.model.Poi;


public class PoiReturnType {

	private int errorCode = 0;
	
	private List<Poi> returnPoiList;
	
	private String errorDescription;

	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public List<Poi> getReturnPoiList() {
		return returnPoiList;
	}

	public void setReturnPoiList(List<Poi> returnPoiList) {
		this.returnPoiList = returnPoiList;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
}
