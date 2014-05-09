package com.sampas.socbs.core.kml.model;

import java.util.List;

/**
 * @author  ctosunoglu
 */
public abstract class Overlay extends Feature {

	/**
	 * @uml.property  name="color"
	 */
	private String color;
	/**
	 * @uml.property  name="drawOrder"
	 */
	private Integer drawOrder;
	/**
	 * @uml.property  name="icon"
	 * @uml.associationEnd  
	 */
	private Icon icon;

	public Overlay() {}
	
	public Overlay(String name, Boolean visibility, Boolean open, AtomAuthor atomAuthor, AtomLink atomLink, String address, String xalAddressDetails, String phoneNumber, String snippet, Integer snippetMaxLines,String description, AbstractView abstractView, TimePrimitive timePrimitive, String styleUrl, List<StyleSelector> styleSelectors, Region region, ExtendedData extendedData, String color, Integer drawOrder, Icon icon) {
		super(name, visibility, open, atomAuthor, atomLink, address, xalAddressDetails, phoneNumber, snippet, snippetMaxLines, description, abstractView, timePrimitive, styleUrl, styleSelectors, region, extendedData);
		this.color = color;
		this.drawOrder = drawOrder;
		this.icon = icon;
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
	 * @uml.property  name="drawOrder"
	 */
	public Integer getDrawOrder() {
		return drawOrder;
	}
	
	/**
	 * @param drawOrder
	 * @uml.property  name="drawOrder"
	 */
	public void setDrawOrder(Integer drawOrder) {
		this.drawOrder = drawOrder;
	}
	
	/**
	 * @return
	 * @uml.property  name="icon"
	 */
	public Icon getIcon() {
		return icon;
	}
	
	/**
	 * @param icon
	 * @uml.property  name="icon"
	 */
	public void setIcon(Icon icon) {
		this.icon = icon;
	}
	
	public void writeInner(Kml kml) throws KmlException {
		super.writeInner(kml);
		if (color != null) {
			kml.println("<color>" + color + "</color>");
		}
		if (drawOrder != null) {
			kml.println("<drawOrder>" + drawOrder + "</drawOrder>");
		}
		if (icon != null) {
			icon.write(kml);
		}
	}
}