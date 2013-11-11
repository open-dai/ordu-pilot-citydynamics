/**
 * PropertyAddressType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1;

public class PropertyAddressType  extends com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.CadastralAddressType  implements java.io.Serializable {
    private java.lang.String parcelNumber;

    public PropertyAddressType() {
    }

    public PropertyAddressType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           java.lang.String plotNumber,
           java.lang.String islandNumber,
           java.lang.String parcelNumber) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate,
            plotNumber,
            islandNumber);
        this.parcelNumber = parcelNumber;
    }


    /**
     * Gets the parcelNumber value for this PropertyAddressType.
     *
     * @return parcelNumber
     */
    public java.lang.String getParcelNumber() {
        return parcelNumber;
    }


    /**
     * Sets the parcelNumber value for this PropertyAddressType.
     *
     * @param parcelNumber
     */
    public void setParcelNumber(java.lang.String parcelNumber) {
        this.parcelNumber = parcelNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PropertyAddressType)) return false;
        PropertyAddressType other = (PropertyAddressType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.parcelNumber==null && other.getParcelNumber()==null) ||
             (this.parcelNumber!=null &&
              this.parcelNumber.equals(other.getParcelNumber())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getParcelNumber() != null) {
            _hashCode += getParcelNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }



}
