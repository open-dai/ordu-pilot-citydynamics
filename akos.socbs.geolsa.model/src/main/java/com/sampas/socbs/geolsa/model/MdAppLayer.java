package com.sampas.socbs.geolsa.model;

import java.util.List;

public class MdAppLayer {

	private long oid;
	private boolean layerAppsSystemLayer;
	private boolean laverVisibility;
	private String layerDescForApp;
	private int layerMaxScale;
	private int layerMinScale;
	private int layerPriority;
	private boolean legendNeeded;
	private MdLayer mdLayer;
	private List<MdLayerAttributeVisibleName> mdLayerAttributeVisibleNames;
	private boolean select;

	public MdAppLayer(){

	}

	public void finalize() throws Throwable {

	}

	public boolean isLayerAppsSystemLayer() {
		return layerAppsSystemLayer;
	}

	public void setLayerAppsSystemLayer(boolean layerAppsSystemLayer) {
		this.layerAppsSystemLayer = layerAppsSystemLayer;
	}

	public boolean isLaverVisibility() {
		return laverVisibility;
	}

	public void setLaverVisibility(boolean laverVisibility) {
		this.laverVisibility = laverVisibility;
	}

	public String getLayerDescForApp() {
		return layerDescForApp;
	}

	public void setLayerDescForApp(String layerDescForApp) {
		this.layerDescForApp = layerDescForApp;
	}

	public int getLayerMaxScale() {
		return layerMaxScale;
	}

	public void setLayerMaxScale(int layerMaxScale) {
		this.layerMaxScale = layerMaxScale;
	}

	public int getLayerMinScale() {
		return layerMinScale;
	}

	public void setLayerMinScale(int layerMinScale) {
		this.layerMinScale = layerMinScale;
	}

	public int getLayerPriority() {
		return layerPriority;
	}

	public void setLayerPriority(int layerPriority) {
		this.layerPriority = layerPriority;
	}

	public boolean isLegendNeeded() {
		return legendNeeded;
	}

	public void setLegendNeeded(boolean legendNeeded) {
		this.legendNeeded = legendNeeded;
	}

	public MdLayer getMdLayer() {
		return mdLayer;
	}

	public void setMdLayer(MdLayer mdLayer) {
		this.mdLayer = mdLayer;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public List<MdLayerAttributeVisibleName> getMdLayerAttributeVisibleNames() {
		return mdLayerAttributeVisibleNames;
	}

	public void setMdLayerAttributeVisibleNames(List<MdLayerAttributeVisibleName> mdLayerAttributeVisibleNames) {
		this.mdLayerAttributeVisibleNames = mdLayerAttributeVisibleNames;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public boolean isSelect() {
		return select;
	}

}