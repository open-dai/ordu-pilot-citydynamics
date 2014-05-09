package com.sampas.socbs.core.mobil;

public class BinaResp {

	private Long BinaId;
	private String BinaLabel;
	private double lat;
	private double lon;
	private double dist;
	public Long getBinaId() {
		return BinaId;
	}
	public void setBinaId(Long binaId) {
		BinaId = binaId;
	}
	public String getBinaLabel() {
		return BinaLabel;
	}
	public void setBinaLabel(String binaLabel) {
		BinaLabel = binaLabel;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getDist() {
		return dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
	}
	
	
}
