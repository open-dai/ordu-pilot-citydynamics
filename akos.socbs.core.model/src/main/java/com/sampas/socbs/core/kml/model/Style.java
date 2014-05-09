package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class Style extends StyleSelector {

	/**
	 * @uml.property  name="iconStyle"
	 * @uml.associationEnd  
	 */
	private IconStyle iconStyle;
	/**
	 * @uml.property  name="labelStyle"
	 * @uml.associationEnd  
	 */
	private LabelStyle labelStyle;
	/**
	 * @uml.property  name="lineStyle"
	 * @uml.associationEnd  
	 */
	private LineStyle lineStyle;
	/**
	 * @uml.property  name="polyStyle"
	 * @uml.associationEnd  
	 */
	private PolyStyle polyStyle;
	/**
	 * @uml.property  name="ballonStyle"
	 * @uml.associationEnd  
	 */
	private BallonStyle ballonStyle;
	/**
	 * @uml.property  name="listStyle"
	 * @uml.associationEnd  
	 */
	private ListStyle listStyle;

	public Style() {}
	
	public Style(IconStyle iconStyle, LabelStyle labelStyle, LineStyle lineStyle, PolyStyle polyStyle, BallonStyle ballonStyle, ListStyle listStyle) {
		this.iconStyle = iconStyle;
		this.labelStyle = labelStyle;
		this.lineStyle = lineStyle;
		this.polyStyle = polyStyle;
		this.ballonStyle = ballonStyle;
		this.listStyle = listStyle;
	}
	
	/**
	 * @return
	 * @uml.property  name="iconStyle"
	 */
	public IconStyle getIconStyle() {
		return iconStyle;
	}

	/**
	 * @param iconStyle
	 * @uml.property  name="iconStyle"
	 */
	public void setIconStyle(IconStyle iconStyle) {
		this.iconStyle = iconStyle;
	}

	/**
	 * @return
	 * @uml.property  name="labelStyle"
	 */
	public LabelStyle getLabelStyle() {
		return labelStyle;
	}

	/**
	 * @param labelStyle
	 * @uml.property  name="labelStyle"
	 */
	public void setLabelStyle(LabelStyle labelStyle) {
		this.labelStyle = labelStyle;
	}

	/**
	 * @return
	 * @uml.property  name="lineStyle"
	 */
	public LineStyle getLineStyle() {
		return lineStyle;
	}

	/**
	 * @param lineStyle
	 * @uml.property  name="lineStyle"
	 */
	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}

	/**
	 * @return
	 * @uml.property  name="polyStyle"
	 */
	public PolyStyle getPolyStyle() {
		return polyStyle;
	}

	/**
	 * @param polyStyle
	 * @uml.property  name="polyStyle"
	 */
	public void setPolyStyle(PolyStyle polyStyle) {
		this.polyStyle = polyStyle;
	}

	/**
	 * @return
	 * @uml.property  name="ballonStyle"
	 */
	public BallonStyle getBallonStyle() {
		return ballonStyle;
	}

	/**
	 * @param ballonStyle
	 * @uml.property  name="ballonStyle"
	 */
	public void setBallonStyle(BallonStyle ballonStyle) {
		this.ballonStyle = ballonStyle;
	}

	/**
	 * @return
	 * @uml.property  name="listStyle"
	 */
	public ListStyle getListStyle() {
		return listStyle;
	}

	/**
	 * @param listStyle
	 * @uml.property  name="listStyle"
	 */
	public void setListStyle(ListStyle listStyle) {
		this.listStyle = listStyle;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<Style" + getIdAndTargetIdFormatted(kml) + ">", 1);
		if (iconStyle != null) {
			iconStyle.write(kml);
		}
		if (labelStyle != null) {
			labelStyle.write(kml);
		}
		if (lineStyle != null) {
			lineStyle.write(kml);
		}
		if (polyStyle != null) {
			polyStyle.write(kml);
		}
		if (ballonStyle != null) {
			ballonStyle.write(kml);
		}
		if (listStyle != null) {
			listStyle.write(kml);
		}
		kml.println(-1, "</Style>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<Style" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}