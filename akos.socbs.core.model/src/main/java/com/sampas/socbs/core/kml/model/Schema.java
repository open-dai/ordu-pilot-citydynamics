package com.sampas.socbs.core.kml.model;

import java.util.List;

/**
 * @author  ctosunoglu
 */
public class Schema extends KmlObject {

	/**
	 * @uml.property  name="simpleFields"
	 */
	private List<SimpleField> simpleFields;
	
	public Schema() {}
	
	public Schema(List<SimpleField> simpleFields) {
		this.simpleFields = simpleFields;
	}
	
	/**
	 * @return
	 * @uml.property  name="simpleFields"
	 */
	public List<SimpleField> getSimpleFields() {
		return simpleFields;
	}

	/**
	 * @param simpleFields
	 * @uml.property  name="simpleFields"
	 */
	public void setSimpleFields(List<SimpleField> simpleFields) {
		this.simpleFields = simpleFields;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<Schema" + getIdAndTargetIdFormatted(kml) + ">", 1);
		if (simpleFields != null) {
			for (SimpleField simpleField : simpleFields) {
				simpleField.write(kml);
			}
		}
		kml.println(-1, "</Schema>");
	}

	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<Schema" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}