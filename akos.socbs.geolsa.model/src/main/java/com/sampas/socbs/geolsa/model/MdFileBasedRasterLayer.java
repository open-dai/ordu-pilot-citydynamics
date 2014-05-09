package com.sampas.socbs.geolsa.model;

import java.util.List;

public class MdFileBasedRasterLayer {

	private long oid;
	private int fileFormat;
	private String fileURL;
	private int rasterBandsize;
	private String rasterType;
	private List<MdRasterThemeStyle> mdRasterThemeStyles;

	public MdFileBasedRasterLayer(){

	}

	public void finalize() throws Throwable {

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

	public int getRasterBandsize() {
		return rasterBandsize;
	}

	public void setRasterBandsize(int rasterBandsize) {
		this.rasterBandsize = rasterBandsize;
	}

	public String getRasterType() {
		return rasterType;
	}

	public void setRasterType(String rasterType) {
		this.rasterType = rasterType;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public long getOid() {
		return oid;
	}

	public List<MdRasterThemeStyle> getMdRasterThemeStyles() {
		return mdRasterThemeStyles;
	}

	public void setMdRasterThemeStyles(List<MdRasterThemeStyle> mdRasterThemeStyles) {
		this.mdRasterThemeStyles = mdRasterThemeStyles;
	}

}