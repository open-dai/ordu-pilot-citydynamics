package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class LabelStyle extends ColorStyle {

	/**
	 * @uml.property  name="scale"
	 */
	private Double scale;
	
	public LabelStyle() {}
	
	public LabelStyle(String color, ColorModeEnum colorMode, Double scale) {
		super(color, colorMode);
		this.scale = scale;
	}
	
	/**
	 * @return
	 * @uml.property  name="scale"
	 */
	public Double getScale() {
		return scale;
	}

	/**
	 * @param scale
	 * @uml.property  name="scale"
	 */
	public void setScale(Double scale) {
		this.scale = scale;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<LabelStyle" + getIdAndTargetIdFormatted(kml) + ">", 1);
		super.writeInner(kml);
		if (scale != null) {
			kml.println("<scale>" + scale + "</scale>");
		}
		kml.println(-1, "</LabelStyle>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<LabelStyle" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}