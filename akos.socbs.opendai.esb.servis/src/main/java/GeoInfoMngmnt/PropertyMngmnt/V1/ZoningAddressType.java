/**
 * ZoningAddressType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.PropertyMngmnt.V1;

public class ZoningAddressType  extends GeoInfoMngmnt.PropertyMngmnt.V1.AddressType   {
    private java.lang.String doorNumber;

    private java.lang.String subDoorNumber;

    private java.lang.String siteName;

    private java.lang.String blockName;

    public ZoningAddressType() {
    }

    public ZoningAddressType(
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
           java.lang.String blockName) {
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
            country);
        this.doorNumber = doorNumber;
        this.subDoorNumber = subDoorNumber;
        this.siteName = siteName;
        this.blockName = blockName;
    }


    /**
     * Gets the doorNumber value for this ZoningAddressType.
     *
     * @return doorNumber
     */
    public java.lang.String getDoorNumber() {
        return doorNumber;
    }


    /**
     * Sets the doorNumber value for this ZoningAddressType.
     *
     * @param doorNumber
     */
    public void setDoorNumber(java.lang.String doorNumber) {
        this.doorNumber = doorNumber;
    }


    /**
     * Gets the subDoorNumber value for this ZoningAddressType.
     *
     * @return subDoorNumber
     */
    public java.lang.String getSubDoorNumber() {
        return subDoorNumber;
    }


    /**
     * Sets the subDoorNumber value for this ZoningAddressType.
     *
     * @param subDoorNumber
     */
    public void setSubDoorNumber(java.lang.String subDoorNumber) {
        this.subDoorNumber = subDoorNumber;
    }


    /**
     * Gets the siteName value for this ZoningAddressType.
     *
     * @return siteName
     */
    public java.lang.String getSiteName() {
        return siteName;
    }


    /**
     * Sets the siteName value for this ZoningAddressType.
     *
     * @param siteName
     */
    public void setSiteName(java.lang.String siteName) {
        this.siteName = siteName;
    }


    /**
     * Gets the blockName value for this ZoningAddressType.
     *
     * @return blockName
     */
    public java.lang.String getBlockName() {
        return blockName;
    }


    /**
     * Sets the blockName value for this ZoningAddressType.
     *
     * @param blockName
     */
    public void setBlockName(java.lang.String blockName) {
        this.blockName = blockName;
    }

   

}
