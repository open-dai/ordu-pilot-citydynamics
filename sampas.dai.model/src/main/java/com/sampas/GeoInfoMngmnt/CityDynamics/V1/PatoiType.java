/**
 * PATOIType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.CityDynamics.V1;


/**
 * PATOI type definition
 */
public class PatoiType  {

	private java.lang.Double x;
	private java.lang.Double y;

	private java.lang.Integer kurumId;
	private java.lang.String aciklama;
	private java.lang.String icindemi;
	private java.lang.Double distancem;


    private com.sampas.GeoInfoMngmnt.CityDynamics.V1.PoiType poi;

    private java.lang.String timeOfInterest;

    /**
     * Gets the poi value for this PATOIType.
     *
     * @return poi
     */
    public com.sampas.GeoInfoMngmnt.CityDynamics.V1.PoiType getPoi() {
        return poi;
    }


    /**
     * Sets the poi value for this PATOIType.
     *
     * @param poi
     */
    public void setPoi(com.sampas.GeoInfoMngmnt.CityDynamics.V1.PoiType poi) {
        this.poi = poi;
    }


    /**
     * Gets the timeOfInterest value for this PATOIType.
     *
     * @return timeOfInterest
     */
    public java.lang.String getTimeOfInterest() {
        return timeOfInterest;
    }


    /**
     * Sets the timeOfInterest value for this PATOIType.
     *
     * @param timeOfInterest
     */
    public void setTimeOfInterest(java.lang.String timeOfInterest) {
        this.timeOfInterest = timeOfInterest;
    }


	public void setKurumId(java.lang.Integer kurumId) {
		this.kurumId = kurumId;
	}


	public java.lang.Integer getKurumId() {
		return kurumId;
	}


	public void setAciklama(java.lang.String aciklama) {
		this.aciklama = aciklama;
	}


	public java.lang.String getAciklama() {
		return aciklama;
	}


	public void setIcindemi(java.lang.String icindemi) {
		this.icindemi = icindemi;
	}


	public java.lang.String getIcindemi() {
		return icindemi;
	}


	public void setDistancem(java.lang.Double distancem) {
		this.distancem = distancem;
	}


	public java.lang.Double getDistancem() {
		return distancem;
	}


	public void setX(java.lang.Double x) {
		this.x = x;
	}


	public java.lang.Double getX() {
		return x;
	}


	public void setY(java.lang.Double y) {
		this.y = y;
	}


	public java.lang.Double getY() {
		return y;
	}



}
