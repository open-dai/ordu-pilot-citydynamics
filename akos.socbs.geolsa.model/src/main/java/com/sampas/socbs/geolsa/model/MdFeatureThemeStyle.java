package com.sampas.socbs.geolsa.model;

public class MdFeatureThemeStyle {

	private long oid;
	private String styleName;
	private long lineStyleID;
	private long pointStyleID;
	private long polygonStyleID;
	private boolean select;

	public MdFeatureThemeStyle(){

	}

	public void finalize() throws Throwable {

	}
	
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getStyleName() {
		return styleName;
	}
	
	public long getLineStyleID() {
		return lineStyleID;
	}

	public void setLineStyleID(long lineStyleID) {
		this.lineStyleID = lineStyleID;
	}

	public long getPointStyleID() {
		return pointStyleID;
	}

	public void setPointStyleID(long pointStyleID) {
		this.pointStyleID = pointStyleID;
	}

	public long getPolygonStyleID() {
		return polygonStyleID;
	}

	public void setPolygonStyleID(long polygonStyleID) {
		this.polygonStyleID = polygonStyleID;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public long getOid() {
		return oid;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public boolean isSelect() {
		return select;
	}

}