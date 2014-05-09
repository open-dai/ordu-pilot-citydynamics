package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public abstract class ColorStyle extends KmlObject {

	/**
	 * @uml.property  name="color"
	 */
	private String color;
	/**
	 * @uml.property  name="colorMode"
	 * @uml.associationEnd  
	 */
	private ColorModeEnum colorMode;
	
	public ColorStyle() {}
	
	public ColorStyle(String color, ColorModeEnum colorMode) {
		this.color = color;
		this.colorMode = colorMode;
	}
	
	/**
	 * @return
	 * @uml.property  name="color"
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * @param color
	 * @uml.property  name="color"
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * @return
	 * @uml.property  name="colorMode"
	 */
	public ColorModeEnum getColorMode() {
		return colorMode;
	}
	
	/**
	 * @param colorMode
	 * @uml.property  name="colorMode"
	 */
	public void setColorMode(ColorModeEnum colorMode) {
		this.colorMode = colorMode;
	}
	
	public void writeInner(Kml kml) throws KmlException {
		if (color != null) {
			kml.println("<color>" + color + "</color>");
		}
		if (colorMode != null) {
			kml.println("<colorMode>" + colorMode + "</colorMode>");
		}
	}
}