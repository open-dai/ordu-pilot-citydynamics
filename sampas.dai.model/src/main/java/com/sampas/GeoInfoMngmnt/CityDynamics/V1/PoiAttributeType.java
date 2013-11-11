/**
 * POIAttributeType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.CityDynamics.V1;


/**
 * POI attribute type definition
 */
public class PoiAttributeType  implements java.io.Serializable {
    private java.lang.String[] name;

    private java.lang.String[] value;

    public PoiAttributeType() {
    }

    public PoiAttributeType(
           java.lang.String[] name,
           java.lang.String[] value) {
           this.name = name;
           this.value = value;
    }


    /**
     * Gets the name value for this POIAttributeType.
     * 
     * @return name
     */
    public java.lang.String[] getName() {
        return name;
    }


    /**
     * Sets the name value for this POIAttributeType.
     * 
     * @param name
     */
    public void setName(java.lang.String[] name) {
        this.name = name;
    }

    public java.lang.String getName(int i) {
        return this.name[i];
    }

    public void setName(int i, java.lang.String _value) {
        this.name[i] = _value;
    }


    /**
     * Gets the value value for this POIAttributeType.
     * 
     * @return value
     */
    public java.lang.String[] getValue() {
        return value;
    }


    /**
     * Sets the value value for this POIAttributeType.
     * 
     * @param value
     */
    public void setValue(java.lang.String[] value) {
        this.value = value;
    }

    public java.lang.String getValue(int i) {
        return this.value[i];
    }

    public void setValue(int i, java.lang.String _value) {
        this.value[i] = _value;
    }

   

}
