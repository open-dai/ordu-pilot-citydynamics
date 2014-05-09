package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class SimpleData extends KmlObject {

	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="value"
	 */
	private String value;
	
	public SimpleData() {}
	
	public SimpleData(String name, String value) {
		this.name = name;
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
		kml.println("<SimpleData name=\"" + name +"\">" + value + "</SimpleData>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<SimpleData" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}
