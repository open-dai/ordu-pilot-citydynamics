/**
 * AreaType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.Common.V1;


/**
 * Alan tipi
 */
public class AreaType  implements java.io.Serializable {
    /* Birimler, M2-Metrekare */
    private java.lang.String unit;

    /* Alan degeri */
    private java.lang.String value;

    public AreaType() {
    }

    public AreaType(
           java.lang.String unit,
           java.lang.String value) {
           this.unit = unit;
           this.value = value;
    }


    /**
     * Gets the unit value for this AreaType.
     *
     * @return unit   * Birimler, M2-Metrekare
     */
    public java.lang.String getUnit() {
        return unit;
    }


    /**
     * Sets the unit value for this AreaType.
     *
     * @param unit   * Birimler, M2-Metrekare
     */
    public void setUnit(java.lang.String unit) {
        this.unit = unit;
    }


    /**
     * Gets the value value for this AreaType.
     *
     * @return value   * Alan degeri
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this AreaType.
     *
     * @param value   * Alan degeri
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }


}
