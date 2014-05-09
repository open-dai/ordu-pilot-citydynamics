package com.sampas.socbs.core.kml.model;


/**
 * @author  ctosunoglu
 */
public class Camera extends AbstractView {

	/**
	 * @uml.property  name="roll"
	 */
	private Double roll;

	public Camera() {}
	
	public Camera(Double longitude, Double latitude, Double altitude, Double heading, Double tilt, AltitudeModeEnum altitudeMode, Double roll) {
		super(longitude, latitude, altitude, heading,tilt, altitudeMode);
		this.roll = roll;
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

	public void write(Kml kml) throws KmlException {
		kml.println("<Camera" + getIdAndTargetIdFormatted(kml) + ">", 1);
		super.writeInner(kml);
		if (roll != null) {
			kml.println("<roll>" + roll + "</roll>");
		}
		kml.println(-1, "</Camera>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<Camera" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}