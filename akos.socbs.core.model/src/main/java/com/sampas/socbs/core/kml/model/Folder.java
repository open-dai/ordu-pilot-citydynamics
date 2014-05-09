package com.sampas.socbs.core.kml.model;

import java.util.List;

public class Folder extends Container {

	private Style style;
	
	private NetworkLink networkLink;
	
	private ScreenOverlay screenOverlay;
	
	public Folder() {}
	
	public Folder(String name, Boolean visibility, Boolean open, AtomAuthor atomAuthor, AtomLink atomLink, String address, String xalAddressDetails, String phoneNumber, String snippet, Integer snippetMaxLines,String description, AbstractView abstractView, TimePrimitive timePrimitive, String styleUrl, List<StyleSelector> styleSelectors, Region region, ExtendedData extendedData, List<Feature> feauters) {
		super(name, visibility, open, atomAuthor, atomLink, address, xalAddressDetails, phoneNumber, snippet, snippetMaxLines, description, abstractView, timePrimitive, styleUrl, styleSelectors, region, extendedData, feauters);
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}
	
	public NetworkLink getNetworkLink() {
		return networkLink;
	}

	public void setNetworkLink(NetworkLink networkLink) {
		this.networkLink = networkLink;
	}
	
	public ScreenOverlay getScreenOverlay() {
		return screenOverlay;
	}

	public void setScreenOverlay(ScreenOverlay screenOverlay) {
		this.screenOverlay = screenOverlay;
	}
	
	public void write(Kml kml) throws KmlException {
		kml.println("<Folder" + getIdAndTargetIdFormatted(kml) + ">", 1);
		if (style != null) {
			style.write(kml);
		}
		if (networkLink != null) {
			networkLink.write(kml);
		}
		if (screenOverlay != null) {
			screenOverlay.write(kml);
		}
		writeInner(kml);
		kml.println(-1, "</Folder>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<Folder" + getIdAndTargetIdFormatted(kml) + "></>");
	}

}
