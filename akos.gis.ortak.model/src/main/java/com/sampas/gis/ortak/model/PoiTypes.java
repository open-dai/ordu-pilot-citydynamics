package com.sampas.gis.ortak.model;

import java.util.ArrayList;
import java.util.List;

public class PoiTypes {

	private List<String> poiTypes;

	public List<String> getPoiTypes() {
		return poiTypes;
	}

	public void setPoiTypes(List<String> poiTypes) {
		this.poiTypes = poiTypes;
	}
	
	public void addPoiTypes(String newPoiType) {
		
		if (this.poiTypes == null) {
			
			this.poiTypes = new ArrayList<String>();
		}
		
		this.poiTypes.add(newPoiType);
	}
	
}
