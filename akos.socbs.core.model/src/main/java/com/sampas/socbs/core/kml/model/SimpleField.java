package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class SimpleField extends KmlObject {

	/**
	 * @uml.property  name="simpleFieldType"
	 * @uml.associationEnd  
	 */
	private SimpleFieldTypeEnum simpleFieldType;
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="displayName"
	 */
	private String displayName;
	
	public SimpleField() {}
	
	public SimpleField(SimpleFieldTypeEnum simpleFieldType, String name, String displayName) {
		this.simpleFieldType = simpleFieldType;
		this.name = name;
		this.displayName = displayName;
	}
	
	/**
	 * @return
	 * @uml.property  name="simpleFieldType"
	 */
	public SimpleFieldTypeEnum getSimpleFieldType() {
		return simpleFieldType;
	}

	/**
	 * @param simpleFieldType
	 * @uml.property  name="simpleFieldType"
	 */
	public void setSimpleFieldType(SimpleFieldTypeEnum simpleFieldType) {
		this.simpleFieldType = simpleFieldType;
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

	public void write(Kml kml) throws KmlException {
		kml.println("<SimpleField" + getIdAndTargetIdFormatted(kml) + (simpleFieldType != null ? " type=\"" + enumToString(simpleFieldType) + "\"" : "") + (name != null ? " name=\"" + name + "\"" : "") + ">", 1);
		if (displayName != null) {
			kml.println("<displayName>" + displayName + "</displayName>");
		}
		kml.println(-1, "</SimpleField>");
	}

	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<SimpleField" + getIdAndTargetIdFormatted(kml) + "></>");
	}

}