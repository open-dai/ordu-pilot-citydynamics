/**
 * AddressType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.PropertyMngmnt.V1;


/**
 * Address type definition
 */
public class AddressType  extends GeoInfoMngmnt.PropertyMngmnt.V1.BaseAddressType {
    private java.lang.String addressKind;

    private java.lang.String freeFormatAddress;

    private java.lang.String postalCode;

    private java.lang.String isActive;

    private Common.V1.ComponentType street;

    private Common.V1.ComponentType district;

    private Common.V1.ComponentType county;

    private Common.V1.ComponentType city;

    private Common.V1.ComponentType country;

    private java.lang.String adress;    
    
    
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
           Common.V1.ComponentType street,
           Common.V1.ComponentType district,
           Common.V1.ComponentType county,
           Common.V1.ComponentType city,
           Common.V1.ComponentType country) {
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
    public Common.V1.ComponentType getStreet() {
        return street;
    }


    /**
     * Sets the street value for this AddressType.
     *
     * @param street
     */
    public void setStreet(Common.V1.ComponentType street) {
        this.street = street;
    }


    /**
     * Gets the district value for this AddressType.
     *
     * @return district
     */
    public Common.V1.ComponentType getDistrict() {
        return district;
    }


    /**
     * Sets the district value for this AddressType.
     *
     * @param district
     */
    public void setDistrict(Common.V1.ComponentType district) {
        this.district = district;
    }


    /**
     * Gets the county value for this AddressType.
     *
     * @return county
     */
    public Common.V1.ComponentType getCounty() {
        return county;
    }


    /**
     * Sets the county value for this AddressType.
     *
     * @param county
     */
    public void setCounty(Common.V1.ComponentType county) {
        this.county = county;
    }


    /**
     * Gets the city value for this AddressType.
     *
     * @return city
     */
    public Common.V1.ComponentType getCity() {
        return city;
    }


    /**
     * Sets the city value for this AddressType.
     *
     * @param city
     */
    public void setCity(Common.V1.ComponentType city) {
        this.city = city;
    }


    /**
     * Gets the country value for this AddressType.
     *
     * @return country
     */
    public Common.V1.ComponentType getCountry() {
        return country;
    }


    /**
     * Sets the country value for this AddressType.
     *
     * @param country
     */
    public void setCountry(Common.V1.ComponentType country) {
        this.country = country;
    }

	public java.lang.String getAdress() {
		return adress;
	}

	public void setAdress(java.lang.String adress) {
		this.adress = adress;
	}

   


}
