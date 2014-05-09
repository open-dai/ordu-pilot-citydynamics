package com.sampas.socbs.geolsa.model;

import java.util.List;

public class MdFileBasedFeatureLayer {

	private long oid;
	private boolean createSpatialIndex;
	private int fileFormat;
	private String fileURL;
	private List<MdFeatureThemeStyle> mdFeatureThemeStyles;

	public MdFileBasedFeatureLayer(){

	}

	public void finalize() throws Throwable {

	}

	public boolean isCreateSpatialIndex() {
		return createSpatialIndex;
	}

	public void setCreateSpatialIndex(boolean createSpatialIndex) {
		this.createSpatialIndex = createSpatialIndex;
	}

	public int getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(int fileFormat) {
		this.fileFormat = fileFormat;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public long getOid() {
		return oid;
	}

	public List<MdFeatureThemeStyle> getMdFeatureThemeStyles() {
		return mdFeatureThemeStyles;
	}

	public void setMdFeatureThemeStyles(List<MdFeatureThemeStyle> mdFeatureThemeStyles) {
		this.mdFeatureThemeStyles = mdFeatureThemeStyles;
	}

}