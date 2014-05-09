package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class LineStyle extends ColorStyle {

	/**
	 * @uml.property  name="width"
	 */
	private Double width;
	
	public LineStyle() {}
	
	public LineStyle(String color, ColorModeEnum colorMode, Double width) {
		super(color, colorMode);
		this.width = width;
	}
	
	/**
	 * @return
	 * @uml.property  name="width"
	 */
	public Double getWidth() {
		return width;
	}

	/**
	 * @param width
	 * @uml.property  name="width"
	 */
	public void setWidth(Double width) {
		this.width = width;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<LineStyle" + getIdAndTargetIdFormatted(kml) + ">", 1);
		super.writeInner(kml);
		if (width != null) {
			kml.println("<width>" + width + "</width>");
		}
		kml.println(-1, "</LineStyle>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<LineStyle" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}