package com.sampas.socbs.core.kml.model;


/**
 * @author  ctosunoglu
 */
public class LookAt extends AbstractView {

	/**
	 * @uml.property  name="range"
	 */
	private Double range;

	public LookAt() {}
	
	public LookAt(Double longitude, Double latitude, Double altitude, Double heading, Double tilt, AltitudeModeEnum altitudeMode, Double range) {
		super(longitude, latitude, altitude, heading,tilt, altitudeMode);
		this.range = range;
	}
	
	/**
	 * @return
	 * @uml.property  name="range"
	 */
	public Double getRange() {
		return range;
	}

	/**
	 * @param range
	 * @uml.property  name="range"
	 */
	public void setRange(Double range) {
		this.range = range;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<LookAt" + getIdAndTargetIdFormatted(kml) + ">", 1);
		super.writeInner(kml);
		if (range != null) {
			kml.println("<range>" + range + "</range>");
		}
		kml.println(-1, "</LookAt>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<LookAt" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}