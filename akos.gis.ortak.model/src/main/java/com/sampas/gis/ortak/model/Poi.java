package com.sampas.gis.ortak.model;



public class Poi {

	private int poiID;
	
	private String poiName;
	
	private Long imptPlaceID;
	
	private Double[] coordinate;
	
	
	public int getPoiID() {
		return poiID;
	}
	
	public void setPoiID(int poiID) {
		this.poiID = poiID;
	}
	
	public String getPoiName() {
		return poiName;
	}
	
	public void setPoiName(String poiName) {
		this.poiName = poiName;
	}
	
	public Double[] getCoordinate() {
		return coordinate;
	}
	
	public void setCoordinate(Double[] coordinate) {
		this.coordinate = coordinate;
	}

	public void setImptPlaceID(Long imptPlaceID) {
		this.imptPlaceID = imptPlaceID;
	}

	public Long getImptPlaceID() {
		return imptPlaceID;
	}

}
