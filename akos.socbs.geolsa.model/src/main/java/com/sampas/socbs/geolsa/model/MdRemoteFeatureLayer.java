package com.sampas.socbs.geolsa.model;

import java.util.List;

public class MdRemoteFeatureLayer {

	private long oid;
	private String directory;
	private int portNo;
	private String queryPage;
	private String remoteDSUrl;
	private String remoteDSVersion;
	private List<MdFeatureThemeStyle> mdFeatureThemeStyles;

	public MdRemoteFeatureLayer(){

	}

	public void finalize() throws Throwable {

	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
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

	public List<MdFeatureThemeStyle> getMdFeatureThemeStyles() {
		return mdFeatureThemeStyles;
	}

	public void setMdFeatureThemeStyles(List<MdFeatureThemeStyle> mdFeatureThemeStyles) {
		this.mdFeatureThemeStyles = mdFeatureThemeStyles;
	}

}