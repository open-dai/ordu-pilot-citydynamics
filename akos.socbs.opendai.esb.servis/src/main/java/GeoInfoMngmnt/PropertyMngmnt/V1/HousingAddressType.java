/**
 * HousingAddressType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.PropertyMngmnt.V1;

public class HousingAddressType  extends GeoInfoMngmnt.PropertyMngmnt.V1.ZoningAddressType   {
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
           Common.V1.ComponentType street,
           Common.V1.ComponentType district,
           Common.V1.ComponentType county,
           Common.V1.ComponentType city,
           Common.V1.ComponentType country,
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

   
}
