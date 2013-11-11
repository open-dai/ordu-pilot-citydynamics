/**
 * DetachedType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1;

public class DetachedType  extends com.sampas.Common.V1.RecordType  implements java.io.Serializable {
    private com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.HousingAddressType address;

    public DetachedType() {
    }

    public DetachedType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.HousingAddressType address) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
        this.address = address;
    }


    /**
     * Gets the address value for this DetachedType.
     *
     * @return address
     */
    public com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.HousingAddressType getAddress() {
        return address;
    }


    /**
     * Sets the address value for this DetachedType.
     *
     * @param address
     */
    public void setAddress(com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.HousingAddressType address) {
        this.address = address;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DetachedType)) return false;
        DetachedType other = (DetachedType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.address==null && other.getAddress()==null) ||
             (this.address!=null &&
              this.address.equals(other.getAddress())));
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
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }



}
