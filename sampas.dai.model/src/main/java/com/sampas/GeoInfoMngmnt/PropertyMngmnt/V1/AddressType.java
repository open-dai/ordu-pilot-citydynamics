/**
 * AddressType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1;


/**
 * Address type definition
 */
public class AddressType  extends com.sampas.GeoInfoMngmnt.PropertyMngmnt.V1.BaseAddressType  implements java.io.Serializable {
    private java.lang.String addressKind;

    private java.lang.String freeFormatAddress;

    private java.lang.String postalCode;

    private java.lang.String isActive;

    private com.sampas.Common.V1.ComponentType street;

    private com.sampas.Common.V1.ComponentType district;

    private com.sampas.Common.V1.ComponentType county;

    private com.sampas.Common.V1.ComponentType city;

    private com.sampas.Common.V1.ComponentType country;

    public AddressType() {
    }

    public AddressType(
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
           com.sampas.Common.V1.ComponentType country) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
        this.addressKind = addressKind;
        this.freeFormatAddress = freeFormatAddress;
        this.postalCode = postalCode;
        this.isActive = isActive;
        this.street = street;
        this.district = district;
        this.county = county;
        this.city = city;
        this.country = country;
    }


    /**
     * Gets the addressKind value for this AddressType.
     *
     * @return addressKind
     */
    public java.lang.String getAddressKind() {
        return addressKind;
    }


    /**
     * Sets the addressKind value for this AddressType.
     *
     * @param addressKind
     */
    public void setAddressKind(java.lang.String addressKind) {
        this.addressKind = addressKind;
    }


    /**
     * Gets the freeFormatAddress value for this AddressType.
     *
     * @return freeFormatAddress
     */
    public java.lang.String getFreeFormatAddress() {
        return freeFormatAddress;
    }


    /**
     * Sets the freeFormatAddress value for this AddressType.
     *
     * @param freeFormatAddress
     */
    public void setFreeFormatAddress(java.lang.String freeFormatAddress) {
        this.freeFormatAddress = freeFormatAddress;
    }


    /**
     * Gets the postalCode value for this AddressType.
     *
     * @return postalCode
     */
    public java.lang.String getPostalCode() {
        return postalCode;
    }


    /**
     * Sets the postalCode value for this AddressType.
     *
     * @param postalCode
     */
    public void setPostalCode(java.lang.String postalCode) {
        this.postalCode = postalCode;
    }


    /**
     * Gets the isActive value for this AddressType.
     *
     * @return isActive
     */
    public java.lang.String getIsActive() {
        return isActive;
    }


    /**
     * Sets the isActive value for this AddressType.
     *
     * @param isActive
     */
    public void setIsActive(java.lang.String isActive) {
        this.isActive = isActive;
    }


    /**
     * Gets the street value for this AddressType.
     *
     * @return street
     */
    public com.sampas.Common.V1.ComponentType getStreet() {
        return street;
    }


    /**
     * Sets the street value for this AddressType.
     *
     * @param street
     */
    public void setStreet(com.sampas.Common.V1.ComponentType street) {
        this.street = street;
    }


    /**
     * Gets the district value for this AddressType.
     *
     * @return district
     */
    public com.sampas.Common.V1.ComponentType getDistrict() {
        return district;
    }


    /**
     * Sets the district value for this AddressType.
     *
     * @param district
     */
    public void setDistrict(com.sampas.Common.V1.ComponentType district) {
        this.district = district;
    }


    /**
     * Gets the county value for this AddressType.
     *
     * @return county
     */
    public com.sampas.Common.V1.ComponentType getCounty() {
        return county;
    }


    /**
     * Sets the county value for this AddressType.
     *
     * @param county
     */
    public void setCounty(com.sampas.Common.V1.ComponentType county) {
        this.county = county;
    }


    /**
     * Gets the city value for this AddressType.
     *
     * @return city
     */
    public com.sampas.Common.V1.ComponentType getCity() {
        return city;
    }


    /**
     * Sets the city value for this AddressType.
     *
     * @param city
     */
    public void setCity(com.sampas.Common.V1.ComponentType city) {
        this.city = city;
    }


    /**
     * Gets the country value for this AddressType.
     *
     * @return country
     */
    public com.sampas.Common.V1.ComponentType getCountry() {
        return country;
    }


    /**
     * Sets the country value for this AddressType.
     *
     * @param country
     */
    public void setCountry(com.sampas.Common.V1.ComponentType country) {
        this.country = country;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddressType)) return false;
        AddressType other = (AddressType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.addressKind==null && other.getAddressKind()==null) ||
             (this.addressKind!=null &&
              this.addressKind.equals(other.getAddressKind()))) &&
            ((this.freeFormatAddress==null && other.getFreeFormatAddress()==null) ||
             (this.freeFormatAddress!=null &&
              this.freeFormatAddress.equals(other.getFreeFormatAddress()))) &&
            ((this.postalCode==null && other.getPostalCode()==null) ||
             (this.postalCode!=null &&
              this.postalCode.equals(other.getPostalCode()))) &&
            ((this.isActive==null && other.getIsActive()==null) ||
             (this.isActive!=null &&
              this.isActive.equals(other.getIsActive()))) &&
            ((this.street==null && other.getStreet()==null) ||
             (this.street!=null &&
              this.street.equals(other.getStreet()))) &&
            ((this.district==null && other.getDistrict()==null) ||
             (this.district!=null &&
              this.district.equals(other.getDistrict()))) &&
            ((this.county==null && other.getCounty()==null) ||
             (this.county!=null &&
              this.county.equals(other.getCounty()))) &&
            ((this.city==null && other.getCity()==null) ||
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.country==null && other.getCountry()==null) ||
             (this.country!=null &&
              this.country.equals(other.getCountry())));
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
        if (getAddressKind() != null) {
            _hashCode += getAddressKind().hashCode();
        }
        if (getFreeFormatAddress() != null) {
            _hashCode += getFreeFormatAddress().hashCode();
        }
        if (getPostalCode() != null) {
            _hashCode += getPostalCode().hashCode();
        }
        if (getIsActive() != null) {
            _hashCode += getIsActive().hashCode();
        }
        if (getStreet() != null) {
            _hashCode += getStreet().hashCode();
        }
        if (getDistrict() != null) {
            _hashCode += getDistrict().hashCode();
        }
        if (getCounty() != null) {
            _hashCode += getCounty().hashCode();
        }
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }


}
