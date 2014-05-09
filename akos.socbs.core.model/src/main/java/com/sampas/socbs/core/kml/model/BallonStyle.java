package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class BallonStyle extends KmlObject {

	/**
	 * @uml.property  name="bgColor"
	 */
	private String bgColor;
	/**
	 * @uml.property  name="textColor"
	 */
	private String textColor;
	/**
	 * @uml.property  name="text"
	 */
	private String text;
	/**
	 * @uml.property  name="displayMode"
	 * @uml.associationEnd  
	 */
	private DisplayModeEnum displayMode;
	
	public BallonStyle() {}
	
	public BallonStyle(String bgColor, String textColor, String text, DisplayModeEnum displayMode) {
		this.bgColor = bgColor;
		this.textColor = textColor;
		this.text = text;
		this.displayMode = displayMode;
	}
	
	/**
	 * @return
	 * @uml.property  name="bgColor"
	 */
	public String getBgColor() {
		return bgColor;
	}

	/**
	 * @param bgColor
	 * @uml.property  name="bgColor"
	 */
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	/**
	 * @return
	 * @uml.property  name="textColor"
	 */
	public String getTextColor() {
		return textColor;
	}

	/**
	 * @param textColor
	 * @uml.property  name="textColor"
	 */
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	/**
	 * @return
	 * @uml.property  name="text"
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 * @uml.property  name="text"
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return
	 * @uml.property  name="displayMode"
	 */
	public DisplayModeEnum getDisplayMode() {
		return displayMode;
	}

	/**
	 * @param displayMode
	 * @uml.property  name="displayMode"
	 */
	public void setDisplayMode(DisplayModeEnum displayMode) {
		this.displayMode = displayMode;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<BallonStyle" + getIdAndTargetIdFormatted(kml) + ">", 1);
		if (bgColor != null) {
			kml.println("<bgColor>" + bgColor + "</bgColor>");
		}
		if (textColor != null) {
			kml.println("<textColor>" + textColor + "</textColor>");
		}
		if (text != null) {
			kml.println("<text>" + text + "</text>");
		}
		if (displayMode != null) {
			kml.println("<displayMode>" + (displayMode == DisplayModeEnum._default ? "default" : displayMode) + "</displayMode>");
		}
		kml.println(-1, "</BallonStyle>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<BallonStyle" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}