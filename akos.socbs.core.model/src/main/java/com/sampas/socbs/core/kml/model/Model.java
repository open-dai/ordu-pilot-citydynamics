package com.sampas.socbs.core.kml.model;

import java.util.List;

/**
 * @author  ctosunoglu
 */
public class Model extends Geometry {

	/**
	 * @uml.property  name="altitudeMode"
	 * @uml.associationEnd  
	 */
	private AltitudeModeEnum altitudeMode;
	/**
	 * @uml.property  name="longitude"
	 */
	private Double longitude;
	/**
	 * @uml.property  name="latitude"
	 */
	private Double latitude;
	/**
	 * @uml.property  name="altitude"
	 */
	private Double altitude;
	/**
	 * @uml.property  name="heading"
	 */
	private Double heading;
	/**
	 * @uml.property  name="tilt"
	 */
	private Double tilt;
	/**
	 * @uml.property  name="roll"
	 */
	private Double roll;
	/**
	 * @uml.property  name="scaleX"
	 */
	private Double scaleX;
	/**
	 * @uml.property  name="scaleY"
	 */
	private Double scaleY;
	/**
	 * @uml.property  name="scaleZ"
	 */
	private Double scaleZ;
	/**
	 * @uml.property  name="link"
	 * @uml.associationEnd  
	 */
	private Link link;
	/**
	 * @uml.property  name="resource"
	 */
	private List<Alias> resourceMap;
	
	public Model() {}
	
	public Model(AltitudeModeEnum altitudeMode, Double longitude, Double latitude, Double altitude, Double heading, Double tilt, Double roll, Double scaleX, Double scaleY, Double scaleZ, Link link, List<Alias> resourceMap) {
		this.altitudeMode = altitudeMode;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.heading = heading;
		this.tilt = tilt;
		this.roll = roll;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.scaleZ = scaleZ;
		this.link = link;
		this.resourceMap = resourceMap;
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
	 * @uml.property  name="longitude"
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 * @uml.property  name="longitude"
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return
	 * @uml.property  name="latitude"
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 * @uml.property  name="latitude"
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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
	 * @uml.property  name="heading"
	 */
	public Double getHeading() {
		return heading;
	}

	/**
	 * @param heading
	 * @uml.property  name="heading"
	 */
	public void setHeading(Double heading) {
		this.heading = heading;
	}

	/**
	 * @return
	 * @uml.property  name="tilt"
	 */
	public Double getTilt() {
		return tilt;
	}

	/**
	 * @param tilt
	 * @uml.property  name="tilt"
	 */
	public void setTilt(Double tilt) {
		this.tilt = tilt;
	}

	/**
	 * @return
	 * @uml.property  name="roll"
	 */
	public Double getRoll() {
		return roll;
	}

	/**
	 * @param roll
	 * @uml.property  name="roll"
	 */
	public void setRoll(Double roll) {
		this.roll = roll;
	}

	/**
	 * @return
	 * @uml.property  name="scaleX"
	 */
	public Double getScaleX() {
		return scaleX;
	}

	/**
	 * @param scaleX
	 * @uml.property  name="scaleX"
	 */
	public void setScaleX(Double scaleX) {
		this.scaleX = scaleX;
	}

	/**
	 * @return
	 * @uml.property  name="scaleY"
	 */
	public Double getScaleY() {
		return scaleY;
	}

	/**
	 * @param scaleY
	 * @uml.property  name="scaleY"
	 */
	public void setScaleY(Double scaleY) {
		this.scaleY = scaleY;
	}

	/**
	 * @return
	 * @uml.property  name="scaleZ"
	 */
	public Double getScaleZ() {
		return scaleZ;
	}

	/**
	 * @param scaleZ
	 * @uml.property  name="scaleZ"
	 */
	public void setScaleZ(Double scaleZ) {
		this.scaleZ = scaleZ;
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

	/**
	 * @return
	 * @uml.property  name="resource"
	 */
	public List<Alias> getResourceMap() {
		return resourceMap;
	}

	/**
	 * @param resourceMap
	 * @uml.property  name="resource"
	 */
	public void setResourceMap(List<Alias> resourceMap) {
		this.resourceMap = resourceMap;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<Model" + getIdAndTargetIdFormatted(kml) + ">", 1);
		if (altitudeMode != null) {
			kml.println("<altitudeMode>" + altitudeMode + "</altitudeMode>");
		}
		if (longitude != null || latitude != null || altitude != null) {
			kml.println("<Location>", 1);
			if (longitude != null) {				
				kml.println("<longitude>" + longitude + "</longitude>");
			}
			if (latitude != null) {				
				kml.println("<latitude>" + latitude + "</latitude>");
			}
			if (altitude != null) {
				kml.println("<altitude>" + altitude + "</altitude>");
			}
			kml.println(-1, "</Location>");
		}
		if (heading != null || tilt != null || roll != null) {
			kml.println("<Orientation>", 1);
			if (heading != null) {				
				kml.println("<heading>" + heading + "</heading>");
			}
			if (tilt != null) {				
				kml.println("<tilt>" + tilt + "</tilt>");
			}
			if (roll != null) {
				kml.println("<roll>" + roll + "</roll>");
			}
			kml.println(-1, "</Orientation>");
		}
		if (scaleX != null || scaleY != null || scaleZ != null) {
			kml.println("<Scale>", 1);
			if (scaleX != null) {				
				kml.println("<x>" + scaleX + "</x>");
			}
			if (scaleY != null) {				
				kml.println("<y>" + scaleY+ "</y>");
			}
			if (scaleZ != null) {
				kml.println("<z>" + scaleZ + "</z>");
			}
			kml.println(-1, "</Scale>");
		}
		if (link != null) {
			link.write(kml);
		}
		if (resourceMap != null) {
			kml.println("<ResourceMap>", -1);
			for (Alias alias : resourceMap) {
				alias.write(kml);
			}
			kml.println(-1, "</ResourceMap>");
		}
		kml.println(-1, "</Model>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<Model" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}