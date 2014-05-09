package com.sampas.socbs.core.kml.model;

import java.util.List;

/**
 * @author  ctosunoglu
 */
public class ExtendedData extends KmlObject {

	/**
	 * @uml.property  name="dataElements"
	 */
	private List<Data> dataElements;
	/**
	 * @uml.property  name="schemaUrl"
	 */
	private String schemaUrl;
	/**
	 * @uml.property  name="simpleDataElements"
	 */
	private List<SimpleData> simpleDataElements;
	/**
	 * @uml.property  name="nameSpace"
	 */
	private String nameSpace;
	/**
	 * @uml.property  name="customContent"
	 */
	private String customContent;
	
	public ExtendedData() {}
	
	public ExtendedData(List<Data> dataElements, String schemaUrl, List<SimpleData> simpleDataElements, String nameSpace, String customContent) {
		this.dataElements = dataElements;
		this.schemaUrl = schemaUrl;
		this.simpleDataElements = simpleDataElements;
		this.nameSpace = nameSpace;
		this.customContent = customContent;
	}
	
	/**
	 * @return
	 * @uml.property  name="dataElements"
	 */
	public List<Data> getDataElements() {
		return dataElements;
	}

	/**
	 * @param dataElements
	 * @uml.property  name="dataElements"
	 */
	public void setDataElements(List<Data> dataElements) {
		this.dataElements = dataElements;
	}

	/**
	 * @return
	 * @uml.property  name="schemaUrl"
	 */
	public String getSchemaUrl() {
		return schemaUrl;
	}

	/**
	 * @param schemaUrl
	 * @uml.property  name="schemaUrl"
	 */
	public void setSchemaUrl(String schemaUrl) {
		this.schemaUrl = schemaUrl;
	}

	/**
	 * @return
	 * @uml.property  name="simpleDataElements"
	 */
	public List<SimpleData> getSimpleDataElements() {
		return simpleDataElements;
	}

	/**
	 * @param simpleDataElements
	 * @uml.property  name="simpleDataElements"
	 */
	public void setSimpleDataElements(List<SimpleData> simpleDataElements) {
		this.simpleDataElements = simpleDataElements;
	}

	/**
	 * @return
	 * @uml.property  name="nameSpace"
	 */
	public String getNameSpace() {
		return nameSpace;
	}

	/**
	 * @param nameSpace
	 * @uml.property  name="nameSpace"
	 */
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	/**
	 * @return
	 * @uml.property  name="customContent"
	 */
	public String getCustomContent() {
		return customContent;
	}

	/**
	 * @param customContent
	 * @uml.property  name="customContent"
	 */
	public void setCustomContent(String customContent) {
		this.customContent = customContent;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<ExtendedData" + getIdAndTargetIdFormatted(kml) + (nameSpace != null ? " mlns:prefix=\"" + nameSpace + "\"" : "") + ">", 1);
		if (dataElements != null) {
			for (Data data : dataElements) {
				data.write(kml);
			}
		}
		if (schemaUrl != null || simpleDataElements != null) {
			kml.println("<SchemaData" + (schemaUrl != null ? " schemaUrl=\"" + schemaUrl + "\"" : "") + ">", 1);
			if (simpleDataElements != null) {
				for (SimpleData simpleData : simpleDataElements) {
					simpleData.write(kml);
				}
			}
			kml.println("</SchemaData>");
		}
		if (customContent != null) {
			kml.println(customContent);
		}
		kml.println(-1, "</ExtendedData>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<ExtendedData" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}
