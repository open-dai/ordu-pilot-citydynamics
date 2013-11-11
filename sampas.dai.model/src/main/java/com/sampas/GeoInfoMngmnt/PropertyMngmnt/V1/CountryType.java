/**
 * CountryType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1;

public class CountryType  extends com.sampas.Common.V1.RecordType  implements java.io.Serializable {
    private com.sampas.Common.V1.ComponentType info;

    public CountryType() {
    }

    public CountryType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           com.sampas.Common.V1.ComponentType info) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
        this.info = info;
    }


    /**
     * Gets the info value for this CountryType.
     *
     * @return info
     */
    public com.sampas.Common.V1.ComponentType getInfo() {
        return info;
    }


    /**
     * Sets the info value for this CountryType.
     *
     * @param info
     */
    public void setInfo(com.sampas.Common.V1.ComponentType info) {
        this.info = info;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CountryType)) return false;
        CountryType other = (CountryType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.info==null && other.getInfo()==null) ||
             (this.info!=null &&
              this.info.equals(other.getInfo())));
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
        if (getInfo() != null) {
            _hashCode += getInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }



}
