/**
 * DistrictType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1;

public class DistrictType  extends com.sampas.Common.V1.RecordType  implements java.io.Serializable {
    private com.sampas.Common.V1.ComponentType districtInfo;

    private java.lang.String isVillage;

    private java.lang.String districtTUIKCode;

    public DistrictType() {
    }

    public DistrictType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           com.sampas.Common.V1.ComponentType districtInfo,
           java.lang.String isVillage,
           java.lang.String districtTUIKCode) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
        this.districtInfo = districtInfo;
        this.isVillage = isVillage;
        this.districtTUIKCode = districtTUIKCode;
    }


    /**
     * Gets the districtInfo value for this DistrictType.
     *
     * @return districtInfo
     */
    public com.sampas.Common.V1.ComponentType getDistrictInfo() {
        return districtInfo;
    }


    /**
     * Sets the districtInfo value for this DistrictType.
     *
     * @param districtInfo
     */
    public void setDistrictInfo(com.sampas.Common.V1.ComponentType districtInfo) {
        this.districtInfo = districtInfo;
    }


    /**
     * Gets the isVillage value for this DistrictType.
     *
     * @return isVillage
     */
    public java.lang.String getIsVillage() {
        return isVillage;
    }


    /**
     * Sets the isVillage value for this DistrictType.
     *
     * @param isVillage
     */
    public void setIsVillage(java.lang.String isVillage) {
        this.isVillage = isVillage;
    }


    /**
     * Gets the districtTUIKCode value for this DistrictType.
     *
     * @return districtTUIKCode
     */
    public java.lang.String getDistrictTUIKCode() {
        return districtTUIKCode;
    }


    /**
     * Sets the districtTUIKCode value for this DistrictType.
     *
     * @param districtTUIKCode
     */
    public void setDistrictTUIKCode(java.lang.String districtTUIKCode) {
        this.districtTUIKCode = districtTUIKCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DistrictType)) return false;
        DistrictType other = (DistrictType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.districtInfo==null && other.getDistrictInfo()==null) ||
             (this.districtInfo!=null &&
              this.districtInfo.equals(other.getDistrictInfo()))) &&
            ((this.isVillage==null && other.getIsVillage()==null) ||
             (this.isVillage!=null &&
              this.isVillage.equals(other.getIsVillage()))) &&
            ((this.districtTUIKCode==null && other.getDistrictTUIKCode()==null) ||
             (this.districtTUIKCode!=null &&
              this.districtTUIKCode.equals(other.getDistrictTUIKCode())));
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
        if (getDistrictInfo() != null) {
            _hashCode += getDistrictInfo().hashCode();
        }
        if (getIsVillage() != null) {
            _hashCode += getIsVillage().hashCode();
        }
        if (getDistrictTUIKCode() != null) {
            _hashCode += getDistrictTUIKCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }


}
