package com.sampas.socbs.core.kml.model;


/**
 * @author  ctosunoglu
 */
public abstract class AbstractView extends KmlObject {
	
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
	 * @uml.property  name="altitudeMode"
	 * @uml.associationEnd  
	 */
	private AltitudeModeEnum altitudeMode;

	public AbstractView() {}
	
	public AbstractView(Double longitude, Double latitude, Double altitude, Double heading, Double tilt, AltitudeModeEnum altitudeMode) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.heading = heading;
		this.tilt = tilt;
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

	public void writeInner(Kml kml) throws KmlException {
		if (longitude != null) {
			kml.println("<longitude>" + longitude + "</longitude>");
		}
		if (latitude != null) {
			kml.println("<latitude>" + latitude + "</latitude>");
		}
		if (altitude != null) {
			kml.println("<altitude>" + altitude + "</altitude>");
		}
		if (heading != null) {
			kml.println("<heading>" + heading + "</heading>");
		}
		if (tilt != null) {
			kml.println("<tilt>" + tilt + "</tilt>");
		}
		if (altitudeMode != null) {
			kml.println("<altitudeMode>" + altitudeMode + "</altitudeMode>");
		}
	}
}
