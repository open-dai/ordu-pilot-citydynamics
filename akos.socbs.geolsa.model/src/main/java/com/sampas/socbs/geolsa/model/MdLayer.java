package com.sampas.socbs.geolsa.model;

public class MdLayer {

	private long oid;
	private String layerName;
	private int layerType;
	private MdRasterLayer mdRasterLayer;
	private MdFeatureLayer mdFeatureLayer;
	private String defaultCrs;
	
	public MdLayer(){

	}

	public void finalize() throws Throwable {

	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public int getLayerType() {
		return layerType;
	}

	public void setLayerType(int layerType) {
		this.layerType = layerType;
	}

	public MdRasterLayer getMdRasterLayer() {
		return mdRasterLayer;
	}

	public void setMdRasterLayer(MdRasterLayer mdRasterLayer) {
		this.mdRasterLayer = mdRasterLayer;
	}

	public MdFeatureLayer getMdFeatureLayer() {
		return mdFeatureLayer;
	}

	public void setMdFeatureLayer(MdFeatureLayer mdFeatureLayer) {
		this.mdFeatureLayer = mdFeatureLayer;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getDefaultCrs() {
		return defaultCrs;
	}

	public void setDefaultCrs(String defaultCrs) {
		this.defaultCrs = defaultCrs;
	}

}