package com.sampas.socbs.core.kml.model;

import java.util.List;

/**
 * @author  ctosunoglu
 */
public class NetworkLink extends Feature {
	
	private Boolean refreshVisibility;
	private Boolean flyToView;
	/**
	 * @uml.property  name="link"
	 * @uml.associationEnd  
	 */
	private Link link;
	
	public NetworkLink() {}
	
	public NetworkLink(String name, Boolean visibility, Boolean open, AtomAuthor atomAuthor, AtomLink atomLink, String address, String xalAddressDetails, String phoneNumber, String snippet, Integer snippetMaxLines,String description, AbstractView abstractView, TimePrimitive timePrimitive, String styleUrl, List<StyleSelector> styleSelectors, Region region, ExtendedData extendedData, Boolean refreshVisibility, Boolean flyToView, Link link) {
		super(name, visibility, open, atomAuthor, atomLink, address, xalAddressDetails, phoneNumber, snippet, snippetMaxLines, description, abstractView, timePrimitive, styleUrl, styleSelectors, region, extendedData);
		this.refreshVisibility = refreshVisibility;
		this.flyToView = flyToView;
		this.link = link;
	}
	
	public boolean isRefreshVisibility() {
		return refreshVisibility;
	}
	
	public void setRefreshVisibility(boolean refreshVisibility) {
		this.refreshVisibility = refreshVisibility;
	}
	
	public Boolean isFlyToView() {
		return flyToView;
	}
	
	/**
	 * @param flyToView
	 * @uml.property  name="flyToView"
	 */
	public void setFlyToView(Boolean flyToView) {
		this.flyToView = flyToView;
	}

	/**
	 * @return
	 * @uml.property  name="link"
	 */
	public Link getLink() {
		return link;
	}

	/**
	 * @param link
	 * @uml.property  name="link"
	 */
	public void setLink(Link link) {
		this.link = link;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<NetworkLink" + getIdAndTargetIdFormatted(kml) + ">", 1);
		writeInner(kml);
		if (refreshVisibility != null) {
			kml.println("<refreshVisibility>" + booleanToInt(refreshVisibility) + "</refreshVisibility>");
		}
		if (flyToView != null) {
			kml.println("<flyToView>" + booleanToInt(flyToView) + "</flyToView>");
		}
		if (link != null) {
			link.write(kml);
		}
		kml.println(-1, "</NetworkLink>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<NetworkLink" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}