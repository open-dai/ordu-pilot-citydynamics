package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class TimeSpan extends TimePrimitive {

	/**
	 * @uml.property  name="begin"
	 */
	private String begin;
	/**
	 * @uml.property  name="end"
	 */
	private String end;
	
	public TimeSpan() {}
	
	public TimeSpan(String begin, String end) {
		this.begin = begin;
		this.end = end;
	}
	
	/**
	 * @return
	 * @uml.property  name="begin"
	 */
	public String getBegin() {
		return begin;
	}

	/**
	 * @param begin
	 * @uml.property  name="begin"
	 */
	public void setBegin(String begin) {
		this.begin = begin;
	}

	/**
	 * @return
	 * @uml.property  name="end"
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * @param end
	 * @uml.property  name="end"
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<TimeSpan" + getIdAndTargetIdFormatted(kml) + ">", 1);
		if (begin != null) {
			kml.println("<begin>" + begin + "</begin>");
		}
		if (end != null) {
			kml.println("<end>" + end + "</end>");
		}
		kml.println(-1, "</TimeSpan>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<TimeSpan" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}