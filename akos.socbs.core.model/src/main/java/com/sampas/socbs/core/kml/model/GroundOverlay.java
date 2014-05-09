package com.sampas.socbs.core.kml.model;

import java.util.List;

/**
 * @author  ctosunoglu
 */
public class GroundOverlay extends Overlay {

	/**
	 * @uml.property  name="altitude"
	 */
	private Double altitude;
	/**
	 * @uml.property  name="altitudeMode"
	 * @uml.associationEnd  
	 */
	private AltitudeModeEnum altitudeMode;
	/**
	 * @uml.property  name="north"
	 */
	private Double north;
	/**
	 * @uml.property  name="south"
	 */
	private Double south;
	/**
	 * @uml.property  name="east"
	 */
	private Double east;
	/**
	 * @uml.property  name="west"
	 */
	private Double west;
	/**
	 * @uml.property  name="rotation"
	 */
	private Double rotation;
	
	public GroundOverlay() {}
	
	public GroundOverlay(String name, Boolean visibility, Boolean open, AtomAuthor atomAuthor, AtomLink atomLink, String address, String xalAddressDetails, String phoneNumber, String snippet, Integer snippetMaxLines,String description, AbstractView abstractView, TimePrimitive timePrimitive, String styleUrl, List<StyleSelector> styleSelectors, Region region, ExtendedData extendedData, String color, Integer drawOrder, Icon icon, Double alititude, AltitudeModeEnum altitudeMode, Double north, Double south, Double east, Double west, Double rotation) {
		super(name, visibility, open, atomAuthor, atomLink, address, xalAddressDetails, phoneNumber, snippet, snippetMaxLines, description, abstractView, timePrimitive, styleUrl, styleSelectors, region, extendedData, color, drawOrder, icon);
		this.altitude = alititude;
		this.altitudeMode = altitudeMode;
		this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
		this.rotation = rotation;
	}
	
	/**
	 * @return
	 * @uml.property  name="altitude"
	 */
	public Double getAltitude() {
		return altitude;
	}

	/**
	 * @param altitude
	 * @uml.property  name="altitude"
	 */
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	/**
	 * @return
	 * @uml.property  name="altitudeMode"
	 */
	public AltitudeModeEnum getAltitudeMode() {
		return altitudeMode;
	}

	/**
	 * @param altitudeMode
	 * @uml.property  name="altitudeMode"
	 */
	public void setAltitudeMode(AltitudeModeEnum altitudeMode) {
		this.altitudeMode = altitudeMode;
	}

	/**
	 * @return
	 * @uml.property  name="north"
	 */
	public Double getNorth() {
		return north;
	}

	/**
	 * @param north
	 * @uml.property  name="north"
	 */
	public void setNorth(Double north) {
		this.north = north;
	}

	/**
	 * @return
	 * @uml.property  name="south"
	 */
	public Double getSouth() {
		return south;
	}

	/**
	 * @param south
	 * @uml.property  name="south"
	 */
	public void setSouth(Double south) {
		this.south = south;
	}

	/**
	 * @return
	 * @uml.property  name="east"
	 */
	public Double getEast() {
		return east;
	}

	/**
	 * @param east
	 * @uml.property  name="east"
	 */
	public void setEast(Double east) {
		this.east = east;
	}

	/**
	 * @return
	 * @uml.property  name="west"
	 */
	public Double getWest() {
		return west;
	}

	/**
	 * @param west
	 * @uml.property  name="west"
	 */
	public void setWest(Double west) {
		this.west = west;
	}

	/**
	 * @return
	 * @uml.property  name="rotation"
	 */
	public Double getRotation() {
		return rotation;
	}

	/**
	 * @param rotation
	 * @uml.property  name="rotation"
	 */
	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<GroundOverlay" + getIdAndTargetIdFormatted(kml) + ">", 1);
		super.writeInner(kml);
		if (altitude != null) {
			kml.println("<altitude>" + altitude + "</altitude>");
		}
		if (altitudeMode != null) {
			kml.println("<altitudeMode>" + altitudeMode + "</altitudeMode>");
		}
		if (north != null || south != null || east != null || west != null || rotation != null) {
			kml.println("<LatLonBox>", 1);
			if (north != null) {
				kml.println("<north>" + north + "</north>");
			}
			if (south != null) {
				kml.println("<south>" + south + "</south>");
			}
			if (east != null) {
				kml.println("<east>" + east + "</east>");
			}
			if (west != null) {
				kml.println("<west>" + west + "</west>");
			}
			if (rotation != null) {
				kml.println("<rotation>" + rotation + "</rotation>");
			}
			kml.println(-1, "</LatLonBox>");
		}
		kml.println(-1, "</GroundOverlay>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<GroundOverlay" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}