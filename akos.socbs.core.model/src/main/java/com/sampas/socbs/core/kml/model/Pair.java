package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class Pair extends KmlObject {

	/**
	 * @uml.property  name="key"
	 * @uml.associationEnd  
	 */
	private StyleStateEnum key;
	/**
	 * @uml.property  name="styleUrl"
	 */
	private String styleUrl;
	
	public Pair() {}
	
	public Pair(StyleStateEnum key, String styleUrl) {
		this.key = key;
		this.styleUrl = styleUrl;
	}
	
 	/**
	 * @return
	 * @uml.property  name="key"
	 */
 	public StyleStateEnum getKey() {
		return key;
	}

	/**
	 * @param key
	 * @uml.property  name="key"
	 */
	public void setKey(StyleStateEnum key) {
		this.key = key;
	}

	/**
	 * @return
	 * @uml.property  name="styleUrl"
	 */
	public String getStyleUrl() {
		return styleUrl;
	}

	/**
	 * @param styleUrl
	 * @uml.property  name="styleUrl"
	 */
	public void setStyleUrl(String styleUrl) {
		this.styleUrl = styleUrl;
	}

	public void write(Kml kml) throws KmlException {
		// We validate the data
		if (key == null) {
			throw new KmlException("Key missing for Pair");
		}
		if (styleUrl == null) {
			throw new KmlException("StyleUrl missing for Pair");
		}
		kml.println("<Pair" + getIdAndTargetIdFormatted(kml) + ">", 1);
		kml.println("<key>" + key + "</key>");
		kml.println("<styleUrl>" + styleUrl + "</styleUrl>");
		kml.println(-1, "</Pair>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<Pair" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}