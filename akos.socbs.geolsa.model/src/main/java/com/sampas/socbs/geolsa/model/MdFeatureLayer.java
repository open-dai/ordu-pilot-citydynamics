package com.sampas.socbs.geolsa.model;

public class MdFeatureLayer {

	private long oid;
	private int layerSubType;
	private MdRemoteFeatureLayer mdRemoteFeatureLayer;
	private MdFileBasedFeatureLayer mdFileBasedFeatureLayer;
	private MdDatabaseFeatureLayer mdDatabaseFeatureLayer;

	public MdFeatureLayer(){

	}

	public void finalize() throws Throwable {

	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public int getLayerSubType() {
		return layerSubType;
	}

	public void setLayerSubType(int layerSubType) {
		this.layerSubType = layerSubType;
	}

	public MdRemoteFeatureLayer getMdRemoteFeatureLayer() {
		return mdRemoteFeatureLayer;
	}

	public void setMdRemoteFeatureLayer(MdRemoteFeatureLayer mdRemoteFeatureLayer) {
		this.mdRemoteFeatureLayer = mdRemoteFeatureLayer;
	}

	public MdFileBasedFeatureLayer getMdFileBasedFeatureLayer() {
		return mdFileBasedFeatureLayer;
	}

	public void setMdFileBasedFeatureLayer(
			MdFileBasedFeatureLayer mdFileBasedFeatureLayer) {
		this.mdFileBasedFeatureLayer = mdFileBasedFeatureLayer;
	}

	public MdDatabaseFeatureLayer getMdDatabaseFeatureLayer() {
		return mdDatabaseFeatureLayer;
	}

	public void setMdDatabaseFeatureLayer(
			MdDatabaseFeatureLayer mdDatabaseFeatureLayer) {
		this.mdDatabaseFeatureLayer = mdDatabaseFeatureLayer;
	}

}