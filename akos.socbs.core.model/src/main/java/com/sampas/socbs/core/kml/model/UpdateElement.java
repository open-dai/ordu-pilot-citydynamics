package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public abstract class UpdateElement {

	/**
	 * @uml.property  name="kmlObject"
	 * @uml.associationEnd  
	 */
	private KmlObject kmlObject;

	public UpdateElement() {}
	
	public UpdateElement(KmlObject kmlObject) {
		this.kmlObject = kmlObject;
	}
	
	/**
	 * @return
	 * @uml.property  name="kmlObject"
	 */
	public KmlObject getKmlObject() {
		return kmlObject;
	}

	/**
	 * @param kmlObject
	 * @uml.property  name="kmlObject"
	 */
	public void setKmlObject(KmlObject kmlObject) {
		this.kmlObject = kmlObject;
	}
	
	public abstract void write(Kml kml) throws KmlException;
}
