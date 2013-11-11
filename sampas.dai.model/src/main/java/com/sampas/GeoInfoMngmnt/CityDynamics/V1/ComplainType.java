/**
 * ComplainType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.CityDynamics.V1;


/**
 * Complain type definition
 */
public class ComplainType  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String surname;

    private java.lang.String phoneNumber;

    private java.lang.String requestTime;

    private java.lang.String complainType;

    private java.lang.String description;

    private com.sampas.GeoInfoMngmnt.CityDynamics.V1.CoordinateType coordinate;

    private com.sampas.GeoInfoMngmnt.CityDynamics.V1.ImageType complainImage;

    public ComplainType() {
    }

    public ComplainType(
           java.lang.String name,
           java.lang.String surname,
           java.lang.String phoneNumber,
           java.lang.String requestTime,
           java.lang.String complainType,
           java.lang.String description,
           com.sampas.GeoInfoMngmnt.CityDynamics.V1.CoordinateType coordinate,
           com.sampas.GeoInfoMngmnt.CityDynamics.V1.ImageType complainImage) {
           this.name = name;
           this.surname = surname;
           this.phoneNumber = phoneNumber;
           this.requestTime = requestTime;
           this.complainType = complainType;
           this.description = description;
           this.coordinate = coordinate;
           this.complainImage = complainImage;
    }


    /**
     * Gets the name value for this ComplainType.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ComplainType.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the surname value for this ComplainType.
     * 
     * @return surname
     */
    public java.lang.String getSurname() {
        return surname;
    }


    /**
     * Sets the surname value for this ComplainType.
     * 
     * @param surname
     */
    public void setSurname(java.lang.String surname) {
        this.surname = surname;
    }


    /**
     * Gets the phoneNumber value for this ComplainType.
     * 
     * @return phoneNumber
     */
    public java.lang.String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * Sets the phoneNumber value for this ComplainType.
     * 
     * @param phoneNumber
     */
    public void setPhoneNumber(java.lang.String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Gets the requestTime value for this ComplainType.
     * 
     * @return requestTime
     */
    public java.lang.String getRequestTime() {
        return requestTime;
    }


    /**
     * Sets the requestTime value for this ComplainType.
     * 
     * @param requestTime
     */
    public void setRequestTime(java.lang.String requestTime) {
        this.requestTime = requestTime;
    }


    /**
     * Gets the complainType value for this ComplainType.
     * 
     * @return complainType
     */
    public java.lang.String getComplainType() {
        return complainType;
    }


    /**
     * Sets the complainType value for this ComplainType.
     * 
     * @param complainType
     */
    public void setComplainType(java.lang.String complainType) {
        this.complainType = complainType;
    }


    /**
     * Gets the description value for this ComplainType.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ComplainType.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the coordinate value for this ComplainType.
     * 
     * @return coordinate
     */
    public com.sampas.GeoInfoMngmnt.CityDynamics.V1.CoordinateType getCoordinate() {
        return coordinate;
    }


    /**
     * Sets the coordinate value for this ComplainType.
     * 
     * @param coordinate
     */
    public void setCoordinate(com.sampas.GeoInfoMngmnt.CityDynamics.V1.CoordinateType coordinate) {
        this.coordinate = coordinate;
    }


    /**
     * Gets the complainImage value for this ComplainType.
     * 
     * @return complainImage
     */
    public com.sampas.GeoInfoMngmnt.CityDynamics.V1.ImageType getComplainImage() {
        return complainImage;
    }


    /**
     * Sets the complainImage value for this ComplainType.
     * 
     * @param complainImage
     */
    public void setComplainImage(com.sampas.GeoInfoMngmnt.CityDynamics.V1.ImageType complainImage) {
        this.complainImage = complainImage;
    }

   

}
