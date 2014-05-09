package com.sampas.socbs.geolsa.model;

public class MdRasterThemeStyle {

	private long oid;
	private long styleID;
	private String styleName;
	private boolean select;

	public MdRasterThemeStyle(){

	}

	public void finalize() throws Throwable {

	}

	public long getStyleID() {
		return styleID;
	}

	public void setStyleID(long styleID) {
		this.styleID = styleID;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
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