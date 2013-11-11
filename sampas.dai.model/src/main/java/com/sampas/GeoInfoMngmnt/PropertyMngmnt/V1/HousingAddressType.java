/**
 * HousingAddressType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1;

public class HousingAddressType  extends com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.ZoningAddressType  implements java.io.Serializable {
    private java.lang.String floorNumber;

    private java.lang.String suitNumber;

    private java.lang.String subSuitNumber;

    public HousingAddressType() {
    }

    public HousingAddressType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           java.lang.String addressKind,
           java.lang.String freeFormatAddress,
           java.lang.String postalCode,
           java.lang.String isActive,
           com.sampas.Common.V1.ComponentType street,
           com.sampas.Common.V1.ComponentType district,
           com.sampas.Common.V1.ComponentType county,
           com.sampas.Common.V1.ComponentType city,
           com.sampas.Common.V1.ComponentType country,
           java.lang.String doorNumber,
           java.lang.String subDoorNumber,
           java.lang.String siteName,
           java.lang.String blockName,
           java.lang.String floorNumber,
           java.lang.String suitNumber,
           java.lang.String subSuitNumber) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate,
            addressKind,
            freeFormatAddress,
            postalCode,
            isActive,
            street,
            district,
            county,
            city,
            country,
            doorNumber,
            subDoorNumber,
            siteName,
            blockName);
        this.floorNumber = floorNumber;
        this.suitNumber = suitNumber;
        this.subSuitNumber = subSuitNumber;
    }


    /**
     * Gets the floorNumber value for this HousingAddressType.
     *
     * @return floorNumber
     */
    public java.lang.String getFloorNumber() {
        return floorNumber;
    }


    /**
     * Sets the floorNumber value for this HousingAddressType.
     *
     * @param floorNumber
     */
    public void setFloorNumber(java.lang.String floorNumber) {
        this.floorNumber = floorNumber;
    }


    /**
     * Gets the suitNumber value for this HousingAddressType.
     *
     * @return suitNumber
     */
    public java.lang.String getSuitNumber() {
        return suitNumber;
    }


    /**
     * Sets the suitNumber value for this HousingAddressType.
     *
     * @param suitNumber
     */
    public void setSuitNumber(java.lang.String suitNumber) {
        this.suitNumber = suitNumber;
    }


    /**
     * Gets the subSuitNumber value for this HousingAddressType.
     *
     * @return subSuitNumber
     */
    public java.lang.String getSubSuitNumber() {
        return subSuitNumber;
    }


    /**
     * Sets the subSuitNumber value for this HousingAddressType.
     *
     * @param subSuitNumber
     */
    public void setSubSuitNumber(java.lang.String subSuitNumber) {
        this.subSuitNumber = subSuitNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HousingAddressType)) return false;
        HousingAddressType other = (HousingAddressType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.floorNumber==null && other.getFloorNumber()==null) ||
             (this.floorNumber!=null &&
              this.floorNumber.equals(other.getFloorNumber()))) &&
            ((this.suitNumber==null && other.getSuitNumber()==null) ||
             (this.suitNumber!=null &&
              this.suitNumber.equals(other.getSuitNumber()))) &&
            ((this.subSuitNumber==null && other.getSubSuitNumber()==null) ||
             (this.subSuitNumber!=null &&
              this.subSuitNumber.equals(other.getSubSuitNumber())));
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
        if (getFloorNumber() != null) {
            _hashCode += getFloorNumber().hashCode();
        }
        if (getSuitNumber() != null) {
            _hashCode += getSuitNumber().hashCode();
        }
        if (getSubSuitNumber() != null) {
            _hashCode += getSubSuitNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }



}
