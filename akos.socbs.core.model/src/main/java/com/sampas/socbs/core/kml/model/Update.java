package com.sampas.socbs.core.kml.model;

import java.util.List;

/**
 * @author  ctosunoglu
 */
public class Update extends KmlObject {

	/**
	 * @uml.property  name="targetHref"
	 */
	private String targetHref;
	/**
	 * @uml.property  name="updateElements"
	 */
	private List<UpdateElement> updateElements;
	
	/**
	 * @return
	 * @uml.property  name="targetHref"
	 */
	public String getTargetHref() {
		return targetHref;
	}

	/**
	 * @param targetHref
	 * @uml.property  name="targetHref"
	 */
	public void setTargetHref(String targetHref) {
		this.targetHref = targetHref;
	}

	/**
	 * @return
	 * @uml.property  name="updateElements"
	 */
	public List<UpdateElement> getUpdateElements() {
		return updateElements;
	}

	/**
	 * @param updateElements
	 * @uml.property  name="updateElements"
	 */
	public void setUpdateElements(List<UpdateElement> updateElements) {
		this.updateElements = updateElements;
	}

	public void write(Kml kml) throws KmlException {
		// We validate the data
		if (targetHref == null) {
			throw new KmlException("targetHref cannot be null in Update");
		}
		kml.println("<Update" + getIdAndTargetIdFormatted(kml) + ">", 1);
		kml.println("<targetHref>" + targetHref + "</targetHref>");
		if (updateElements != null) {
			for (UpdateElement updateElement : updateElements) {
				updateElement.write(kml);
			}
		}
		kml.println(-1, "</Update>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<Update" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}
