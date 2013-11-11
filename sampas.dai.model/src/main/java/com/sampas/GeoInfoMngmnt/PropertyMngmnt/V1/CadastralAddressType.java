/**
 * CadastralAddressType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1;

public class CadastralAddressType  extends com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.BaseAddressType  implements java.io.Serializable {
    private java.lang.String plotNumber;

    private java.lang.String islandNumber;

    public CadastralAddressType() {
    }

    public CadastralAddressType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           java.lang.String plotNumber,
           java.lang.String islandNumber) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
        this.plotNumber = plotNumber;
        this.islandNumber = islandNumber;
    }


    /**
     * Gets the plotNumber value for this CadastralAddressType.
     *
     * @return plotNumber
     */
    public java.lang.String getPlotNumber() {
        return plotNumber;
    }


    /**
     * Sets the plotNumber value for this CadastralAddressType.
     *
     * @param plotNumber
     */
    public void setPlotNumber(java.lang.String plotNumber) {
        this.plotNumber = plotNumber;
    }


    /**
     * Gets the islandNumber value for this CadastralAddressType.
     *
     * @return islandNumber
     */
    public java.lang.String getIslandNumber() {
        return islandNumber;
    }


    /**
     * Sets the islandNumber value for this CadastralAddressType.
     *
     * @param islandNumber
     */
    public void setIslandNumber(java.lang.String islandNumber) {
        this.islandNumber = islandNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CadastralAddressType)) return false;
        CadastralAddressType other = (CadastralAddressType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.plotNumber==null && other.getPlotNumber()==null) ||
             (this.plotNumber!=null &&
              this.plotNumber.equals(other.getPlotNumber()))) &&
            ((this.islandNumber==null && other.getIslandNumber()==null) ||
             (this.islandNumber!=null &&
              this.islandNumber.equals(other.getIslandNumber())));
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
        if (getPlotNumber() != null) {
            _hashCode += getPlotNumber().hashCode();
        }
        if (getIslandNumber() != null) {
            _hashCode += getIslandNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }



}
