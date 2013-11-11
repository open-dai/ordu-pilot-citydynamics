/**
 * BaseAddressType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1;

public class BaseAddressType  extends com.sampas.Common.V1.RecordType  implements java.io.Serializable {
    public BaseAddressType() {
    }

    public BaseAddressType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BaseAddressType)) return false;
        BaseAddressType other = (BaseAddressType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj);
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
        __hashCodeCalc = false;
        return _hashCode;
    }


}
