package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class Data extends KmlObject {

	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="displayName"
	 */
	private String displayName;
	/**
	 * @uml.property  name="value"
	 */
	private String value;
	
	public Data() {}
	
	public Data(String name, String displayName, String value) {
		this.name = name;
		this.displayName = displayName;
		this.value = value;
	}
	
	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 * @uml.property  name="displayName"
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName
	 * @uml.property  name="displayName"
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return
	 * @uml.property  name="value"
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 * @uml.property  name="value"
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<Data name=\"" + name +"\">", 1);
		if (displayName != null) {
			kml.println("<displayName>" + displayName + "</displayName>");
		}
		if (value != null) {
			kml.println("<value>" + value + "</value>");
		}
		kml.println(-1, "</Data>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<Data" + getIdAndTargetIdFormatted(kml) + "></>");
	}

}
