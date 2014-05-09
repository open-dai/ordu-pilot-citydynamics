package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class ListStyle extends KmlObject {

	/**
	 * @uml.property  name="listItemType"
	 * @uml.associationEnd  
	 */
	private ListItemTypeEnum listItemType;
	/**
	 * @uml.property  name="bgColor"
	 */
	private String bgColor;
	/**
	 * @uml.property  name="itemIconState"
	 */
	private String itemIconState;
	/**
	 * @uml.property  name="href"
	 */
	private String href;
	
	public ListStyle() {}
	
	public ListStyle(ListItemTypeEnum listItemType, String bgColor, String itemIconState, String href) {
		this.listItemType = listItemType;
		this.bgColor = bgColor;
		this.itemIconState = itemIconState;
		this.href = href;
	}
	
	/**
	 * @return
	 * @uml.property  name="listItemType"
	 */
	public ListItemTypeEnum getListItemType() {
		return listItemType;
	}

	/**
	 * @param listItemType
	 * @uml.property  name="listItemType"
	 */
	public void setListItemType(ListItemTypeEnum listItemType) {
		this.listItemType = listItemType;
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
	 * @uml.property  name="itemIconState"
	 */
	public String getItemIconState() {
		return itemIconState;
	}

	/**
	 * @param itemIconState
	 * @uml.property  name="itemIconState"
	 */
	public void setItemIconState(String itemIconState) {
		this.itemIconState = itemIconState;
	}

	/**
	 * @return
	 * @uml.property  name="href"
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href
	 * @uml.property  name="href"
	 */
	public void setHref(String href) {
		this.href = href;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<ListStyle" + getIdAndTargetIdFormatted(kml) + ">", 1);
		if (listItemType != null) {
			kml.println("<listItemType>" + listItemType + "</listItemType>");
		}
		if (bgColor != null) {
			kml.println("<bgColor>" + bgColor + "</bgColor>");
		}
		if (itemIconState != null || href != null) {
			kml.println("<ItemIcon>", 1);
			if (itemIconState != null) {
				kml.println("<state>" + itemIconState + "</state>");
			}
			if (href != null) {
				kml.println("<href>" + href + "</href>");
			}			
			kml.println(-1, "</ItemIcon>");
		}
		kml.println(-1, "</ListStyle>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<ListStyle" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}