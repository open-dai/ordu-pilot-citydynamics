package com.sampas.socbs.core.kml.model;

import java.util.UUID;

/**
 * @author  ctosunoglu
 */
public abstract class KmlObject {

	/**
	 * @uml.property  name="id"
	 */
	private String id;
	/**
	 * @uml.property  name="targetId"
	 */
	private String targetId;
	
	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return
	 * @uml.property  name="targetId"
	 */
	public String getTargetId() {
		return targetId;
	}
	
	/**
	 * @param targetId
	 * @uml.property  name="targetId"
	 */
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public abstract void write(Kml kml) throws KmlException;
	
	public abstract void writeDelete(Kml kml) throws KmlException;
	
	protected String getIdAndTargetIdFormatted(Kml kml) {
		if (kml.isGenerateObjectIds() && id == null) {
			setId(UUID.randomUUID().toString());
		}
		String result = "";
		if (id != null) {
			result += " id=\"" + id + "\"";
		}
		if (targetId != null) {
			result += " targetId=\"" + targetId + "\"";
		}
		return result;
	}
	
	public static int booleanToInt(boolean booleanValue) {
		return (booleanValue? 1 : 0);
	}
	
	@SuppressWarnings("unchecked")
	public static String enumToString(Enum _enum) {
		if (_enum.toString().startsWith("_")) {
			return _enum.toString().substring(1);
		} else {
			return _enum.toString();
		}
	}
}
