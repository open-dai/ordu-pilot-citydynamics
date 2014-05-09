package com.sampas.socbs.core.kml.model;

import java.util.List;

/**
 * @author  ctosunoglu
 */
public class MultiGeometry extends Geometry {

	/**
	 * @uml.property  name="geometries"
	 */
	private List<Geometry> geometries;
	
	public MultiGeometry() {}
	
	public MultiGeometry(List<Geometry> geometries) {
		this.geometries = geometries;
	}
	
	/**
	 * @return
	 * @uml.property  name="geometries"
	 */
	public List<Geometry> getGeometries() {
		return geometries;
	}

	/**
	 * @param geometries
	 * @uml.property  name="geometries"
	 */
	public void setGeometries(List<Geometry> geometries) {
		this.geometries = geometries;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<MultiGeometry" + getIdAndTargetIdFormatted(kml) + ">", 1);
		if (geometries != null) {
			for (Geometry geometry : geometries) {
				geometry.write(kml);
			}
		}
		kml.println(-1, "</MultiGeometry>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<MultiGeometry" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}