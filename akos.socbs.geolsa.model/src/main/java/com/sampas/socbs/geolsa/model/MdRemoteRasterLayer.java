package com.sampas.socbs.geolsa.model;

import java.util.List;

public class MdRemoteRasterLayer {

	private long oid;
	private String defaultSRS;
	private String directory;
	private int maxHeight;
	private int maxWidth;
	private int portNo;
	private String queryPage;
	private String remoteDSGmlVersion;
	private String remoteDSUrl;
	private String remoteDSVersion;
	private List<MdRasterThemeStyle> mdRasterThemeStyles;

	public MdRemoteRasterLayer(){

	}

	public void finalize() throws Throwable {

	}

	public String getDefaultSRS() {
		return defaultSRS;
	}

	public void setDefaultSRS(String defaultSRS) {
		this.defaultSRS = defaultSRS;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	public int getPortNo() {
		return portNo;
	}

	public void setPortNo(int portNo) {
		this.portNo = portNo;
	}

	public String getQueryPage() {
		return queryPage;
	}

	public void setQueryPage(String queryPage) {
		this.queryPage = queryPage;
	}

	public String getRemoteDSGmlVersion() {
		return remoteDSGmlVersion;
	}

	public void setRemoteDSGmlVersion(String remoteDSGmlVersion) {
		this.remoteDSGmlVersion = remoteDSGmlVersion;
	}

	public String getRemoteDSUrl() {
		return remoteDSUrl;
	}

	public void setRemoteDSUrl(String remoteDSUrl) {
		this.remoteDSUrl = remoteDSUrl;
	}

	public String getRemoteDSVersion() {
		return remoteDSVersion;
	}

	public void setRemoteDSVersion(String remoteDSVersion) {
		this.remoteDSVersion = remoteDSVersion;
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