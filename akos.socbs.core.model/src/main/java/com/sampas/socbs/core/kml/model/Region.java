package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class Region extends KmlObject {

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
	 * @uml.property  name="minAltitude"
	 */
	private Double minAltitude;
	/**
	 * @uml.property  name="maxAltitude"
	 */
	private Double maxAltitude;
	/**
	 * @uml.property  name="altitudeMode"
	 * @uml.associationEnd  
	 */
	private AltitudeModeEnum altitudeMode;
	/**
	 * @uml.property  name="minLodPixels"
	 */
	private Double minLodPixels;
	/**
	 * @uml.property  name="maxLodPixels"
	 */
	private Double maxLodPixels;
	/**
	 * @uml.property  name="minFadeExtent"
	 */
	private Double minFadeExtent;
	/**
	 * @uml.property  name="maxFadeExtent"
	 */
	private Double maxFadeExtent;
	
	public Region() {}
	
	public Region(Double north, Double south, Double east, Double west, Double minAltitude, Double maxAltitude, AltitudeModeEnum altitudeMode, Double minLodPixels, Double maxLodPixels, Double minFadeExtent, Double maxFadeExtent) {
		this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
		this.minAltitude = minAltitude;
		this.maxAltitude = maxAltitude;
		this.altitudeMode = altitudeMode;
		this.minLodPixels = minLodPixels;
		this.maxLodPixels = maxLodPixels;
		this.minFadeExtent = minFadeExtent;
		this.maxFadeExtent = maxFadeExtent;
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
	 * @uml.property  name="minAltitude"
	 */
	public Double getMinAltitude() {
		return minAltitude;
	}

	/**
	 * @param minAltitude
	 * @uml.property  name="minAltitude"
	 */
	public void setMinAltitude(Double minAltitude) {
		this.minAltitude = minAltitude;
	}

	/**
	 * @return
	 * @uml.property  name="maxAltitude"
	 */
	public Double getMaxAltitude() {
		return maxAltitude;
	}

	/**
	 * @param maxAltitude
	 * @uml.property  name="maxAltitude"
	 */
	public void setMaxAltitude(Double maxAltitude) {
		this.maxAltitude = maxAltitude;
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
	 * @uml.property  name="minLodPixels"
	 */
	public Double getMinLodPixels() {
		return minLodPixels;
	}

	/**
	 * @param minLodPixels
	 * @uml.property  name="minLodPixels"
	 */
	public void setMinLodPixels(Double minLodPixels) {
		this.minLodPixels = minLodPixels;
	}

	/**
	 * @return
	 * @uml.property  name="maxLodPixels"
	 */
	public Double getMaxLodPixels() {
		return maxLodPixels;
	}

	/**
	 * @param maxLodPixels
	 * @uml.property  name="maxLodPixels"
	 */
	public void setMaxLodPixels(Double maxLodPixels) {
		this.maxLodPixels = maxLodPixels;
	}

	/**
	 * @return
	 * @uml.property  name="minFadeExtent"
	 */
	public Double getMinFadeExtent() {
		return minFadeExtent;
	}

	/**
	 * @param minFadeExtent
	 * @uml.property  name="minFadeExtent"
	 */
	public void setMinFadeExtent(Double minFadeExtent) {
		this.minFadeExtent = minFadeExtent;
	}

	/**
	 * @return
	 * @uml.property  name="maxFadeExtent"
	 */
	public Double getMaxFadeExtent() {
		return maxFadeExtent;
	}

	/**
	 * @param maxFadeExtent
	 * @uml.property  name="maxFadeExtent"
	 */
	public void setMaxFadeExtent(Double maxFadeExtent) {
		this.maxFadeExtent = maxFadeExtent;
	}

	public void write(Kml kml) throws KmlException {
		// We validate the data
		if (north == null) {
			throw new KmlException("north is required in Region");
		}
		if (south == null) {
			throw new KmlException("south is required in Region");
		}
		if (east == null) {
			throw new KmlException("east is required in Region");
		}
		if (west == null) {
			throw new KmlException("west is required in Region");
		}
		
		kml.println("<Region" + getIdAndTargetIdFormatted(kml) + ">", 1);
		kml.println("<LatLonAltBox>", 1);
		kml.println("<north>" + north + "</north>");
		kml.println("<south>" + south + "</south>");
		kml.println("<east>" + east + "</east>");
		kml.println("<west>" + west + "</west>");
		kml.println(-1, "</LatLonAltBox>");
		if (minAltitude != null) {
			kml.println("<minAltitude>" + minAltitude + "</minAltitude>");
		}
		if (maxAltitude != null) {
			kml.println("<maxAltitude>" + maxAltitude + "</maxAltitude>");
		}
		if (altitudeMode!= null) {
			kml.println("<altitudeMode>" + altitudeMode + "</altitudeMode>");
		}
		if (minLodPixels != null || maxLodPixels != null || minFadeExtent != null || maxFadeExtent != null) {
			kml.println("<Lod>", 1);
			if (minLodPixels != null) {
				kml.println("<minLodPixels>" + minLodPixels + "</minLodPixels>");
			}
			if (maxLodPixels != null) {
				kml.println("<maxLodPixels>" + maxLodPixels + "</maxLodPixels>");
			}
			if (minFadeExtent != null) {
				kml.println("<minFadeExtent>" + minFadeExtent + "</minFadeExtent>");
			}
			if (minFadeExtent != null) {
				kml.println("<minFadeExtent>" + minFadeExtent + "</minFadeExtent>");
			}
			if (maxFadeExtent != null) {
				kml.println("<maxFadeExtent>" + maxFadeExtent + "</maxFadeExtent>");
			}
			kml.println(-1, "<Lod>");
		}
		kml.println(-1, "</Region>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<Region" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}