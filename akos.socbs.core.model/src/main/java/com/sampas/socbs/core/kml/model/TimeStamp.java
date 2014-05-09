package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class TimeStamp extends TimePrimitive {

	/**
	 * @uml.property  name="when"
	 */
	private String when;
	
	public TimeStamp() {}
	
	public TimeStamp(String when) {
		this.when = when;
	}
	
	/**
	 * @return
	 * @uml.property  name="when"
	 */
	public String getWhen() {
		return when;
	}

	/**
	 * @param when
	 * @uml.property  name="when"
	 */
	public void setWhen(String when) {
		this.when = when;
	}
	
	public void write(Kml kml) throws KmlException {
		kml.println("<TimeStamp" + getIdAndTargetIdFormatted(kml) + ">", 1);
		if (when != null) {
			kml.println("<when>" + when + "</when>");
		}
		kml.println(-1, "</TimeStamp>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<TimeStamp" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}