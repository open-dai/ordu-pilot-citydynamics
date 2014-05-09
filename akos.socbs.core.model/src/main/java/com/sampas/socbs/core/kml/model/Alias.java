package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class Alias extends KmlObject {

	/**
	 * @uml.property  name="targetHref"
	 */
	private String targetHref;
	/**
	 * @uml.property  name="sourceHref"
	 */
	private String sourceHref;
	
	public Alias() {}
	
	public Alias(String targetHref, String sourceHref) {
		this.targetHref = targetHref;
		this.sourceHref = sourceHref;
	}
	
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
	 * @uml.property  name="sourceHref"
	 */
	public String getSourceHref() {
		return sourceHref;
	}

	/**
	 * @param sourceHref
	 * @uml.property  name="sourceHref"
	 */
	public void setSourceHref(String sourceHref) {
		this.sourceHref = sourceHref;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<Alias" + getIdAndTargetIdFormatted(kml) + ">", 1);
		if (targetHref != null) {
			kml.println("<targetHref>" + targetHref + "</targetHref>");
		}
		if (sourceHref != null) {
			kml.println("<sourceHref>" + sourceHref + "</sourceHref>");
		}
		kml.println(-1, "</Alias>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<Alias" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}