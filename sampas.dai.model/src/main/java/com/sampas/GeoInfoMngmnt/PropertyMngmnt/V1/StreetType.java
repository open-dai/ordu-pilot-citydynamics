/**
 * StreetType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1;

public class StreetType  extends com.sampas.Common.V1.RecordType  implements java.io.Serializable {
    private com.sampas.Common.V1.ComponentType streetInfo;

    private java.lang.String length;

    private java.lang.String width;

    public StreetType() {
    }

    public StreetType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           com.sampas.Common.V1.ComponentType streetInfo,
           java.lang.String length,
           java.lang.String width) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
        this.streetInfo = streetInfo;
        this.length = length;
        this.width = width;
    }


    /**
     * Gets the streetInfo value for this StreetType.
     *
     * @return streetInfo
     */
    public com.sampas.Common.V1.ComponentType getStreetInfo() {
        return streetInfo;
    }


    /**
     * Sets the streetInfo value for this StreetType.
     *
     * @param streetInfo
     */
    public void setStreetInfo(com.sampas.Common.V1.ComponentType streetInfo) {
        this.streetInfo = streetInfo;
    }


    /**
     * Gets the length value for this StreetType.
     *
     * @return length
     */
    public java.lang.String getLength() {
        return length;
    }


    /**
     * Sets the length value for this StreetType.
     *
     * @param length
     */
    public void setLength(java.lang.String length) {
        this.length = length;
    }


    /**
     * Gets the width value for this StreetType.
     *
     * @return width
     */
    public java.lang.String getWidth() {
        return width;
    }


    /**
     * Sets the width value for this StreetType.
     *
     * @param width
     */
    public void setWidth(java.lang.String width) {
        this.width = width;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StreetType)) return false;
        StreetType other = (StreetType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.streetInfo==null && other.getStreetInfo()==null) ||
             (this.streetInfo!=null &&
              this.streetInfo.equals(other.getStreetInfo()))) &&
            ((this.length==null && other.getLength()==null) ||
             (this.length!=null &&
              this.length.equals(other.getLength()))) &&
            ((this.width==null && other.getWidth()==null) ||
             (this.width!=null &&
              this.width.equals(other.getWidth())));
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
        if (getStreetInfo() != null) {
            _hashCode += getStreetInfo().hashCode();
        }
        if (getLength() != null) {
            _hashCode += getLength().hashCode();
        }
        if (getWidth() != null) {
            _hashCode += getWidth().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }


}
