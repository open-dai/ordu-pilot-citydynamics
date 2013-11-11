/**
 * PlotType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1;

public class PlotType  extends com.sampas.Common.V1.RecordType  implements java.io.Serializable {
    private java.lang.String description;

    private com.sampas.Common.V1.AreaType area;

    private com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.CadastralAddressType address;

    public PlotType() {
    }

    public PlotType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           java.lang.String description,
           com.sampas.Common.V1.AreaType area,
           com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.CadastralAddressType address) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
        this.description = description;
        this.area = area;
        this.address = address;
    }


    /**
     * Gets the description value for this PlotType.
     *
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this PlotType.
     *
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the area value for this PlotType.
     *
     * @return area
     */
    public com.sampas.Common.V1.AreaType getArea() {
        return area;
    }


    /**
     * Sets the area value for this PlotType.
     *
     * @param area
     */
    public void setArea(com.sampas.Common.V1.AreaType area) {
        this.area = area;
    }


    /**
     * Gets the address value for this PlotType.
     *
     * @return address
     */
    public com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.CadastralAddressType getAddress() {
        return address;
    }


    /**
     * Sets the address value for this PlotType.
     *
     * @param address
     */
    public void setAddress(com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.CadastralAddressType address) {
        this.address = address;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PlotType)) return false;
        PlotType other = (PlotType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.description==null && other.getDescription()==null) ||
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.area==null && other.getArea()==null) ||
             (this.area!=null &&
              this.area.equals(other.getArea()))) &&
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
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getArea() != null) {
            _hashCode += getArea().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
