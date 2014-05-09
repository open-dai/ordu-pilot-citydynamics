package com.sampas.socbs.geolsa.model;

public class MdRasterLayer {

	private long oid;
	private int layerSubType;
	private String rasterWfsEquvalent;
	private MdDatabaseRasterLayer mdDatabaseRasterLayer;
	private MdRemoteRasterLayer mdRemoteRasterLayer;
	private MdFileBasedRasterLayer mdFileBasedRasterLayer;

	public MdRasterLayer(){

	}

	public void finalize() throws Throwable {

	}

	public int getLayerSubType() {
		return layerSubType;
	}

	public void setLayerSubType(int layerSubType) {
		this.layerSubType = layerSubType;
	}

	public MdDatabaseRasterLayer getMdDatabaseRasterLayer() {
		return mdDatabaseRasterLayer;
	}

	public void setMdDatabaseRasterLayer(
			MdDatabaseRasterLayer mdDatabaseRasterLayer) {
		this.mdDatabaseRasterLayer = mdDatabaseRasterLayer;
	}

	public MdRemoteRasterLayer getMdRemoteRasterLayer() {
		return mdRemoteRasterLayer;
	}

	public void setMdRemoteRasterLayer(MdRemoteRasterLayer mdRemoteRasterLayer) {
		this.mdRemoteRasterLayer = mdRemoteRasterLayer;
	}

	public MdFileBasedRasterLayer getMdFileBasedRasterLayer() {
		return mdFileBasedRasterLayer;
	}

	public void setMdFileBasedRasterLayer(
			MdFileBasedRasterLayer mdFileBasedRasterLayer) {
		this.mdFileBasedRasterLayer = mdFileBasedRasterLayer;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public void setRasterWfsEquvalent(String rasterWfsEquvalent) {
		this.rasterWfsEquvalent = rasterWfsEquvalent;
	}

	public String getRasterWfsEquvalent() {
		return rasterWfsEquvalent;
	}

}