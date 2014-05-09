package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class IconStyle extends ColorStyle {

	/**
	 * @uml.property  name="scale"
	 */
	private Double scale;
	/**
	 * @uml.property  name="heading"
	 */
	private Double heading;
	/**
	 * @uml.property  name="iconHref"
	 */
	private String iconHref;
	/**
	 * @uml.property  name="hotSpotX"
	 */
	private Double hotSpotX;
	/**
	 * @uml.property  name="hotSpotY"
	 */
	private Double hotSpotY;
	/**
	 * @uml.property  name="hotSpotXunits"
	 * @uml.associationEnd  
	 */
	private UnitEnum hotSpotXunits;
	/**
	 * @uml.property  name="hotSpotYunits"
	 * @uml.associationEnd  
	 */
	private UnitEnum hotSpotYunits;
	
	public IconStyle() {}
	
	public IconStyle(String color, ColorModeEnum colorMode, Double scale, Double heading, String iconHref, Double hotSpotX, Double hotSpotY, UnitEnum hotSpotXunits, UnitEnum hotSpotYunits) {
		super(color, colorMode);
		this.scale = scale;
		this.heading = heading;
		this.iconHref = iconHref;
		this.hotSpotX = hotSpotX;
		this.hotSpotY = hotSpotY;
		this.hotSpotXunits = hotSpotXunits;
		this.hotSpotYunits = hotSpotYunits;
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

	/**
	 * @return
	 * @uml.property  name="heading"
	 */
	public Double getHeading() {
		return heading;
	}

	/**
	 * @param heading
	 * @uml.property  name="heading"
	 */
	public void setHeading(Double heading) {
		this.heading = heading;
	}

	/**
	 * @return
	 * @uml.property  name="iconHref"
	 */
	public String getIconHref() {
		return iconHref;
	}

	/**
	 * @param iconHref
	 * @uml.property  name="iconHref"
	 */
	public void setIconHref(String iconHref) {
		this.iconHref = iconHref;
	}

	/**
	 * @return
	 * @uml.property  name="hotSpotX"
	 */
	public Double getHotSpotX() {
		return hotSpotX;
	}

	/**
	 * @param hotSpotX
	 * @uml.property  name="hotSpotX"
	 */
	public void setHotSpotX(Double hotSpotX) {
		this.hotSpotX = hotSpotX;
	}

	/**
	 * @return
	 * @uml.property  name="hotSpotY"
	 */
	public Double getHotSpotY() {
		return hotSpotY;
	}

	/**
	 * @param hotSpotY
	 * @uml.property  name="hotSpotY"
	 */
	public void setHotSpotY(Double hotSpotY) {
		this.hotSpotY = hotSpotY;
	}

	/**
	 * @return
	 * @uml.property  name="hotSpotXunits"
	 */
	public UnitEnum getHotSpotXunits() {
		return hotSpotXunits;
	}

	/**
	 * @param hotSpotXunits
	 * @uml.property  name="hotSpotXunits"
	 */
	public void setHotSpotXunits(UnitEnum hotSpotXunits) {
		this.hotSpotXunits = hotSpotXunits;
	}

	/**
	 * @return
	 * @uml.property  name="hotSpotYunits"
	 */
	public UnitEnum getHotSpotYunits() {
		return hotSpotYunits;
	}

	/**
	 * @param hotSpotYunits
	 * @uml.property  name="hotSpotYunits"
	 */
	public void setHotSpotYunits(UnitEnum hotSpotYunits) {
		this.hotSpotYunits = hotSpotYunits;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<IconStyle" + getIdAndTargetIdFormatted(kml) + ">", 1);
		super.writeInner(kml);
		if (scale != null) {
			kml.println("<scale>" + scale + "</scale>");
		}
		if (heading != null) {
			kml.println("<heading>" + heading + "</heading>");
		}
		if (iconHref != null) {
			kml.println("<Icon>", 1);
			kml.println("<href>" + iconHref + "</href>");
			kml.println(-1, "</Icon>");
		}
		if (hotSpotX != null || hotSpotY != null || hotSpotXunits != null || hotSpotYunits != null) {
			kml.println("<hotSpot" + (hotSpotX != null ? " x=\"" + hotSpotX + "\"" : "") + (hotSpotY != null ? " y=\"" + hotSpotY + "\"" : "") + (hotSpotXunits != null ? " xunits=\"" + hotSpotXunits + "\"" : "") + (hotSpotYunits != null ? " yunits=\"" + hotSpotYunits + "\"" : "") + "/>");
		}
		kml.println(-1, "</IconStyle>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<IconStyle" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}