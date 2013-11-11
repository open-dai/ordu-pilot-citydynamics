/**
 * CoordinateType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.CityDynamics.V1;


/**
 * Coordinate type definition
 */
public class CoordinateType  implements java.io.Serializable {
    private java.lang.String latitude;

    private java.lang.String longtitude;

    public CoordinateType() {
    }

    public CoordinateType(
           java.lang.String latitude,
           java.lang.String longtitude) {
           this.latitude = latitude;
           this.longtitude = longtitude;
    }


    /**
     * Gets the latitude value for this CoordinateType.
     * 
     * @return latitude
     */
    public java.lang.String getLatitude() {
        return latitude;
    }


    /**
     * Sets the latitude value for this CoordinateType.
     * 
     * @param latitude
     */
    public void setLatitude(java.lang.String latitude) {
        this.latitude = latitude;
    }


    /**
     * Gets the longtitude value for this CoordinateType.
     * 
     * @return longtitude
     */
    public java.lang.String getLongtitude() {
        return longtitude;
    }


    /**
     * Sets the longtitude value for this CoordinateType.
     * 
     * @param longtitude
     */
    public void setLongtitude(java.lang.String longtitude) {
        this.longtitude = longtitude;
    }

   

}
