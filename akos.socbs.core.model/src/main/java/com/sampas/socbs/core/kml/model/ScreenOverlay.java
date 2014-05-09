package com.sampas.socbs.core.kml.model;

import java.util.List;

/**
 * @author  ctosunoglu
 */
public class ScreenOverlay extends Overlay {

	/**
	 * @uml.property  name="overlayX"
	 */
	private Double overlayX;
	/**
	 * @uml.property  name="overlayY"
	 */
	private Double overlayY;
	/**
	 * @uml.property  name="overlayXunits"
	 * @uml.associationEnd  
	 */
	private UnitEnum overlayXunits;
	/**
	 * @uml.property  name="overlayYunits"
	 * @uml.associationEnd  
	 */
	private UnitEnum overlayYunits;
	/**
	 * @uml.property  name="screenX"
	 */
	private Double screenX;
	/**
	 * @uml.property  name="screenY"
	 */
	private Double screenY;
	/**
	 * @uml.property  name="screenXunits"
	 * @uml.associationEnd  
	 */
	private UnitEnum screenXunits;
	/**
	 * @uml.property  name="screenYunits"
	 * @uml.associationEnd  
	 */
	private UnitEnum screenYunits;
	/**
	 * @uml.property  name="rotationX"
	 */
	private Double rotationX;
	/**
	 * @uml.property  name="rotationY"
	 */
	private Double rotationY;
	/**
	 * @uml.property  name="rotationXunits"
	 * @uml.associationEnd  
	 */
	private UnitEnum rotationXunits;
	/**
	 * @uml.property  name="rotationYunits"
	 * @uml.associationEnd  
	 */
	private UnitEnum rotationYunits;
	/**
	 * @uml.property  name="sizeX"
	 */
	private Double sizeX;
	/**
	 * @uml.property  name="sizeY"
	 */
	private Double sizeY;
	/**
	 * @uml.property  name="sizeXunits"
	 * @uml.associationEnd  
	 */
	private UnitEnum sizeXunits;
	/**
	 * @uml.property  name="sizeYunits"
	 * @uml.associationEnd  
	 */
	private UnitEnum sizeYunits;
	/**
	 * @uml.property  name="rotation"
	 */
	private Double rotation;
	
	public ScreenOverlay() {}
	
	public ScreenOverlay(String name, Boolean visibility, Boolean open, AtomAuthor atomAuthor, AtomLink atomLink, String address, String xalAddressDetails, String phoneNumber, String snippet, Integer snippetMaxLines,String description, AbstractView abstractView, TimePrimitive timePrimitive, String styleUrl, List<StyleSelector> styleSelectors, Region region, ExtendedData extendedData, String color, Integer drawOrder, Icon icon, Double overlayX, Double overlayY, UnitEnum overlayXunits, UnitEnum overlayYunits, Double screenX, Double screenY, UnitEnum screenXunits, UnitEnum screenYunits, Double rotationX, Double rotationY, UnitEnum rotationXunits, UnitEnum rotationYunits, Double sizeX, Double sizeY, UnitEnum sizeXunits, UnitEnum sizeYunits, Double rotation) {
		super(name, visibility, open, atomAuthor, atomLink, address, xalAddressDetails, phoneNumber, snippet, snippetMaxLines, description, abstractView, timePrimitive, styleUrl, styleSelectors, region, extendedData, color, drawOrder, icon);
		this.overlayX = overlayX;
		this.overlayY = overlayY;
		this.overlayXunits = overlayXunits;
		this.overlayYunits = overlayYunits;
		this.screenX = screenX;
		this.screenY = screenY;
		this.screenXunits = screenXunits;
		this.screenYunits = screenYunits;
		this.rotationX = rotationX;
		this.rotationY = rotationY;
		this.rotationXunits = rotationXunits;
		this.rotationYunits = rotationYunits;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.sizeXunits = sizeXunits;
		this.sizeYunits = sizeYunits;
		this.rotation = rotation;
	}
	
	/**
	 * @return
	 * @uml.property  name="overlayX"
	 */
	public Double getOverlayX() {
		return overlayX;
	}

	/**
	 * @param overlayX
	 * @uml.property  name="overlayX"
	 */
	public void setOverlayX(Double overlayX) {
		this.overlayX = overlayX;
	}

	/**
	 * @return
	 * @uml.property  name="overlayY"
	 */
	public Double getOverlayY() {
		return overlayY;
	}

	/**
	 * @param overlayY
	 * @uml.property  name="overlayY"
	 */
	public void setOverlayY(Double overlayY) {
		this.overlayY = overlayY;
	}

	/**
	 * @return
	 * @uml.property  name="overlayXunits"
	 */
	public UnitEnum getOverlayXunits() {
		return overlayXunits;
	}

	/**
	 * @param overlayXunits
	 * @uml.property  name="overlayXunits"
	 */
	public void setOverlayXunits(UnitEnum overlayXunits) {
		this.overlayXunits = overlayXunits;
	}

	/**
	 * @return
	 * @uml.property  name="overlayYunits"
	 */
	public UnitEnum getOverlayYunits() {
		return overlayYunits;
	}

	/**
	 * @param overlayYunits
	 * @uml.property  name="overlayYunits"
	 */
	public void setOverlayYunits(UnitEnum overlayYunits) {
		this.overlayYunits = overlayYunits;
	}

	/**
	 * @return
	 * @uml.property  name="screenX"
	 */
	public Double getScreenX() {
		return screenX;
	}

	/**
	 * @param screenX
	 * @uml.property  name="screenX"
	 */
	public void setScreenX(Double screenX) {
		this.screenX = screenX;
	}

	/**
	 * @return
	 * @uml.property  name="screenY"
	 */
	public Double getScreenY() {
		return screenY;
	}

	/**
	 * @param screenY
	 * @uml.property  name="screenY"
	 */
	public void setScreenY(Double screenY) {
		this.screenY = screenY;
	}

	/**
	 * @return
	 * @uml.property  name="screenXunits"
	 */
	public UnitEnum getScreenXunits() {
		return screenXunits;
	}

	/**
	 * @param screenXunits
	 * @uml.property  name="screenXunits"
	 */
	public void setScreenXunits(UnitEnum screenXunits) {
		this.screenXunits = screenXunits;
	}

	/**
	 * @return
	 * @uml.property  name="screenYunits"
	 */
	public UnitEnum getScreenYunits() {
		return screenYunits;
	}

	/**
	 * @param screenYunits
	 * @uml.property  name="screenYunits"
	 */
	public void setScreenYunits(UnitEnum screenYunits) {
		this.screenYunits = screenYunits;
	}

	/**
	 * @return
	 * @uml.property  name="rotationX"
	 */
	public Double getRotationX() {
		return rotationX;
	}

	/**
	 * @param rotationX
	 * @uml.property  name="rotationX"
	 */
	public void setRotationX(Double rotationX) {
		this.rotationX = rotationX;
	}

	/**
	 * @return
	 * @uml.property  name="rotationY"
	 */
	public Double getRotationY() {
		return rotationY;
	}

	/**
	 * @param rotationY
	 * @uml.property  name="rotationY"
	 */
	public void setRotationY(Double rotationY) {
		this.rotationY = rotationY;
	}

	/**
	 * @return
	 * @uml.property  name="rotationXunits"
	 */
	public UnitEnum getRotationXunits() {
		return rotationXunits;
	}

	/**
	 * @param rotationXunits
	 * @uml.property  name="rotationXunits"
	 */
	public void setRotationXunits(UnitEnum rotationXunits) {
		this.rotationXunits = rotationXunits;
	}

	/**
	 * @return
	 * @uml.property  name="rotationYunits"
	 */
	public UnitEnum getRotationYunits() {
		return rotationYunits;
	}

	/**
	 * @param rotationYunits
	 * @uml.property  name="rotationYunits"
	 */
	public void setRotationYunits(UnitEnum rotationYunits) {
		this.rotationYunits = rotationYunits;
	}

	/**
	 * @return
	 * @uml.property  name="sizeX"
	 */
	public Double getSizeX() {
		return sizeX;
	}

	/**
	 * @param sizeX
	 * @uml.property  name="sizeX"
	 */
	public void setSizeX(Double sizeX) {
		this.sizeX = sizeX;
	}

	/**
	 * @return
	 * @uml.property  name="sizeY"
	 */
	public Double getSizeY() {
		return sizeY;
	}

	/**
	 * @param sizeY
	 * @uml.property  name="sizeY"
	 */
	public void setSizeY(Double sizeY) {
		this.sizeY = sizeY;
	}

	/**
	 * @return
	 * @uml.property  name="sizeXunits"
	 */
	public UnitEnum getSizeXunits() {
		return sizeXunits;
	}

	/**
	 * @param sizeXunits
	 * @uml.property  name="sizeXunits"
	 */
	public void setSizeXunits(UnitEnum sizeXunits) {
		this.sizeXunits = sizeXunits;
	}

	/**
	 * @return
	 * @uml.property  name="sizeYunits"
	 */
	public UnitEnum getSizeYunits() {
		return sizeYunits;
	}

	/**
	 * @param sizeYunits
	 * @uml.property  name="sizeYunits"
	 */
	public void setSizeYunits(UnitEnum sizeYunits) {
		this.sizeYunits = sizeYunits;
	}

	/**
	 * @return
	 * @uml.property  name="rotation"
	 */
	public Double getRotation() {
		return rotation;
	}

	/**
	 * @param rotation
	 * @uml.property  name="rotation"
	 */
	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<ScreenOverlay" + getIdAndTargetIdFormatted(kml) + ">", 1);
		super.writeInner(kml);
		if (overlayX != null || overlayY != null || overlayXunits != null || overlayYunits != null) {
			kml.println("<overlayXY" + (overlayX != null ? " x=\"" + overlayX + "\"" : "") + (overlayY != null ? " y=\"" + overlayY + "\"" : "") + (overlayXunits != null ? " xunits=\"" + overlayXunits + "\"" : "") + (overlayYunits != null ? " yunits=\"" + overlayYunits + "\"" : "") + "/>");
		}
		if (screenX != null || screenY != null || screenXunits != null || screenYunits != null) {
			kml.println("<screenXY" + (screenX != null ? " x=\"" + screenX + "\"" : "") + (screenY != null ? " y=\"" + screenY + "\"" : "") + (screenXunits != null ? " xunits=\"" + screenXunits + "\"" : "") + (screenYunits != null ? " yunits=\"" + screenYunits + "\"" : "") + "/>");
		}
		if (rotationX != null || rotationY != null || rotationXunits != null || rotationYunits != null) {
			kml.println("<rotationXY" + (rotationX != null ? " x=\"" + rotationX + "\"" : "") + (rotationY != null ? " y=\"" + rotationY + "\"" : "") + (rotationXunits != null ? " xunits=\"" + rotationXunits + "\"" : "") + (rotationYunits != null ? " yunits=\"" + rotationYunits + "\"" : "") + "/>");
		}
		if (sizeX != null || sizeY != null || sizeXunits != null || sizeYunits != null) {
			kml.println("<sizeXY" + (sizeX != null ? " x=\"" + sizeX + "\"" : "") + (sizeY != null ? " y=\"" + sizeY + "\"" : "") + (sizeXunits != null ? " xunits=\"" + sizeXunits + "\"" : "") + (sizeYunits != null ? " yunits=\"" + sizeYunits + "\"" : "") + "/>");
		}
		if (rotation != null) {
			kml.println("<rotation>" + rotation + "</rotation>"); 
		}
		kml.println(-1, "</ScreenOverlay>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<ScreenOverlay" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}