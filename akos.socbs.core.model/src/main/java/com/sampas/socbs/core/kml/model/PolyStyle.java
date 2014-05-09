package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class PolyStyle extends ColorStyle {

	/**
	 * @uml.property  name="fill"
	 */
	private Boolean fill;
	/**
	 * @uml.property  name="outline"
	 */
	private Boolean outline;
	
	public PolyStyle() {}
	
	public PolyStyle(String color, ColorModeEnum colorMode, Boolean fill, Boolean outline) {
		super(color, colorMode);
		this.fill = fill;
		this.outline = outline;
	}
	
	/**
	 * @return
	 * @uml.property  name="fill"
	 */
	public Boolean getFill() {
		return fill;
	}

	/**
	 * @param fill
	 * @uml.property  name="fill"
	 */
	public void setFill(Boolean fill) {
		this.fill = fill;
	}

	/**
	 * @return
	 * @uml.property  name="outline"
	 */
	public Boolean getOutline() {
		return outline;
	}

	/**
	 * @param outline
	 * @uml.property  name="outline"
	 */
	public void setOutline(Boolean outline) {
		this.outline = outline;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<PolyStyle" + getIdAndTargetIdFormatted(kml) + ">", 1);
		super.writeInner(kml);
		if (fill != null) {
			kml.println("<fill>" + booleanToInt(fill) + "</fill>");
		}
		if (outline != null) {
			kml.println("<outline>" + booleanToInt(outline) + "</outline>");
		}
		kml.println(-1, "</PolyStyle>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<PolyStyle" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}