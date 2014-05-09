package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class Point extends Geometry {

	/**
	 * @uml.property  name="extrude"
	 */
	private Boolean extrude;
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

	public Point() {}
	
	public Point(Double longitude, Double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Point(Double longitude, Double latitude, Double altitude) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
	}
	
	public Point(Boolean extrude, AltitudeModeEnum altitudeMode, Double longitude, Double latitude, Double altitude) {
		this.extrude = extrude;
		this.altitudeMode = altitudeMode;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
	}
	
	/**
	 * @return
	 * @uml.property  name="extrude"
	 */
	public Boolean getExtrude() {
		return extrude;
	}

	/**
	 * @param extrude
	 * @uml.property  name="extrude"
	 */
	public void setExtrude(Boolean extrude) {
		this.extrude = extrude;
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

	public void write(Kml kml) throws KmlException {
		kml.println("<Point" + getIdAndTargetIdFormatted(kml) + ">", 1);
		if (extrude != null) {
			kml.println("<extrude>" + booleanToInt(extrude) + "</extrude>");
		}
		if (altitudeMode != null) {
			kml.println("<altitudeMode>" + altitudeMode + "</altitudeMode>");
		}
		if (longitude != null && latitude != null) {
			kml.println("<coordinates>" + getLongitudeLatitudeAltitudeString() + "</coordinates>");
		}
		
		kml.println(-1, "</Point>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<Point" + getIdAndTargetIdFormatted(kml) + "></>");
	}
	
	public String getLongitudeLatitudeAltitudeString() {
		return longitude +"," + latitude + (altitude != null? "," + altitude : "");
	}
}
