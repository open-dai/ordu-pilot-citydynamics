package com.sampas.socbs.core.kml.model;

import java.util.List;

/**
 * @author  ctosunoglu
 */
public class LinearRing extends Geometry {

	/**
	 * @uml.property  name="extrude"
	 */
	private Boolean extrude;
	/**
	 * @uml.property  name="tessellate"
	 */
	private Boolean tessellate;
	/**
	 * @uml.property  name="altitudeMode"
	 * @uml.associationEnd  
	 */
	private AltitudeModeEnum altitudeMode;
	/**
	 * @uml.property  name="coordinates"
	 */
	private List<Point> coordinates;
	
	public LinearRing() {}
	
	public LinearRing(Boolean extrude, Boolean tessellate, AltitudeModeEnum altitudeMode, List<Point> coordinates) {
		this.extrude = extrude;
		this.tessellate = tessellate;
		this.altitudeMode = altitudeMode;
		this.coordinates = coordinates;
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
	 * @uml.property  name="tessellate"
	 */
	public Boolean getTessellate() {
		return tessellate;
	}

	/**
	 * @param tessellate
	 * @uml.property  name="tessellate"
	 */
	public void setTessellate(Boolean tessellate) {
		this.tessellate = tessellate;
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
	 * @uml.property  name="coordinates"
	 */
	public List<Point> getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates
	 * @uml.property  name="coordinates"
	 */
	public void setCoordinates(List<Point> coordinates) {
		this.coordinates = coordinates;
	}

	public void write(Kml kml) throws KmlException {
		// We validate the data
		if (coordinates == null || coordinates.size() < 3) {
			throw new KmlException("LineString must contain at least 3 points");
		}
		
		kml.println("<LinearRing" + getIdAndTargetIdFormatted(kml) + ">", 1);
		if (extrude != null) {
			kml.println("<extrude>" + booleanToInt(extrude) + "</extrude>");
		}
		if (tessellate != null) {
			kml.println("<tessellate>" + booleanToInt(tessellate) + "</tessellate>");
		}
		if (altitudeMode != null) {
			kml.println("<altitudeMode>" + altitudeMode + "</altitudeMode>");
		}
		kml.print("<coordinates>");
		boolean firstLoop = true;
		for (Point point : coordinates) {
			if (firstLoop) {
				firstLoop = false;
			} else {
				kml.print(" ");
			}
			kml.print(point.getLongitudeLatitudeAltitudeString());
		}
		// We add the first coordinate to the end, as KML require the first coordinate to be equal to the last
		kml.print(" " + coordinates.get(0).getLongitudeLatitudeAltitudeString());
		
		kml.println("</coordinates>");

		kml.println(-1, "</LinearRing>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<LinearRing" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}