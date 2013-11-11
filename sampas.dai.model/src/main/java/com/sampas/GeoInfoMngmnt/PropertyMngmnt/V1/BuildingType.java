/**
 * BuildingType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1;

public class BuildingType  extends com.sampas.Common.V1.RecordType  implements java.io.Serializable {
    private java.lang.String buildingName;

    private java.lang.String siteName;

    private com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.ZoningAddressType address;

    public BuildingType() {
    }

    public BuildingType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           java.lang.String buildingName,
           java.lang.String siteName,
           com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.ZoningAddressType address) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
        this.buildingName = buildingName;
        this.siteName = siteName;
        this.address = address;
    }


    /**
     * Gets the buildingName value for this BuildingType.
     *
     * @return buildingName
     */
    public java.lang.String getBuildingName() {
        return buildingName;
    }


    /**
     * Sets the buildingName value for this BuildingType.
     *
     * @param buildingName
     */
    public void setBuildingName(java.lang.String buildingName) {
        this.buildingName = buildingName;
    }


    /**
     * Gets the siteName value for this BuildingType.
     *
     * @return siteName
     */
    public java.lang.String getSiteName() {
        return siteName;
    }


    /**
     * Sets the siteName value for this BuildingType.
     *
     * @param siteName
     */
    public void setSiteName(java.lang.String siteName) {
        this.siteName = siteName;
    }


    /**
     * Gets the address value for this BuildingType.
     *
     * @return address
     */
    public com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.ZoningAddressType getAddress() {
        return address;
    }


    /**
     * Sets the address value for this BuildingType.
     *
     * @param address
     */
    public void setAddress(com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.ZoningAddressType address) {
        this.address = address;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuildingType)) return false;
        BuildingType other = (BuildingType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.buildingName==null && other.getBuildingName()==null) ||
             (this.buildingName!=null &&
              this.buildingName.equals(other.getBuildingName()))) &&
            ((this.siteName==null && other.getSiteName()==null) ||
             (this.siteName!=null &&
              this.siteName.equals(other.getSiteName()))) &&
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
        if (getBuildingName() != null) {
            _hashCode += getBuildingName().hashCode();
        }
        if (getSiteName() != null) {
            _hashCode += getSiteName().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }



}
