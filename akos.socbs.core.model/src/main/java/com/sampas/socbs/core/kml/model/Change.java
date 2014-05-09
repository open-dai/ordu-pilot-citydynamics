package com.sampas.socbs.core.kml.model;

public class Change extends UpdateElement {

	public Change() {}
	
	public Change(KmlObject kmlObject) {
		setKmlObject(kmlObject);
	}
	
	public void write(Kml kml) throws KmlException {
		kml.println("<Change>", 1);
		getKmlObject().write(kml);
		kml.println(-1, "</Change>");
	}
}
