package com.sampas.socbs.core.kml.model;

import java.util.List;

/**
 * @author  ctosunoglu
 */
public class Polygon extends Geometry {

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
	 * @uml.property  name="outerBoundary"
	 * @uml.associationEnd  
	 */
	private LinearRing outerBoundary;
	/**
	 * @uml.property  name="innerBoundaries"
	 */
	private List<LinearRing> innerBoundaries;
	
	public Polygon() {}
	
	public Polygon(Boolean extrude, Boolean tessellate, AltitudeModeEnum altitudeMode, LinearRing outerBoundary, List<LinearRing> innerBoundaries) {
		this.extrude = extrude;
		this.tessellate = tessellate;
		this.altitudeMode = altitudeMode;
		this.outerBoundary = outerBoundary;
		this.innerBoundaries = innerBoundaries;
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
	 * @uml.property  name="outerBoundary"
	 */
	public LinearRing getOuterBoundary() {
		return outerBoundary;
	}

	/**
	 * @param outerBoundary
	 * @uml.property  name="outerBoundary"
	 */
	public void setOuterBoundary(LinearRing outerBoundary) {
		this.outerBoundary = outerBoundary;
	}

	/**
	 * @return
	 * @uml.property  name="innerBoundaries"
	 */
	public List<LinearRing> getInnerBoundaries() {
		return innerBoundaries;
	}

	/**
	 * @param innerBoundaries
	 * @uml.property  name="innerBoundaries"
	 */
	public void setInnerBoundaries(List<LinearRing> innerBoundaries) {
		this.innerBoundaries = innerBoundaries;
	}

	public void write(Kml kml) throws KmlException {
		// We validate the data
		if (outerBoundary == null) {
			throw new KmlException("An outerBoundary is required in a Polygon");
		}
		
		kml.println("<Polygon" + getIdAndTargetIdFormatted(kml) + ">", 1);
		if (extrude != null) {
			kml.println("<extrude>" + booleanToInt(extrude) + "</extrude>");
		}
		if (tessellate != null) {
			kml.println("<tessellate>" + booleanToInt(tessellate) + "</tessellate>");
		}
		if (altitudeMode != null) {
			kml.println("<altitudeMode>" + altitudeMode + "</altitudeMode>");
		}
		
		kml.println("<outerBoundaryIs>", 1);
		outerBoundary.write(kml);
		kml.println(-1, "</outerBoundaryIs>");
		
		if (innerBoundaries != null) {
			for (LinearRing innerBounadry : innerBoundaries) {
				kml.println("<innerBoundaryIs>", 1);
				innerBounadry.write(kml);
				kml.println(-1, "</innerBoundaryIs>");
			}
		}
		kml.println(-1, "</Polygon>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<Polygon" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}