/**
 * ParcelType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.PropertyMngmnt.V1;

public class ParcelType  extends Common.V1.RecordType  {
    private java.lang.String description;

    private GeoInfoMngmnt.PropertyMngmnt.V1.PropertyAddressType address;

    public ParcelType() {
    }

    public ParcelType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           java.lang.String description,
           GeoInfoMngmnt.PropertyMngmnt.V1.PropertyAddressType address) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
        this.description = description;
        this.address = address;
    }


    /**
     * Gets the description value for this ParcelType.
     *
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ParcelType.
     *
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the address value for this ParcelType.
     *
     * @return address
     */
    public GeoInfoMngmnt.PropertyMngmnt.V1.PropertyAddressType getAddress() {
        return address;
    }


    /**
     * Sets the address value for this ParcelType.
     *
     * @param address
     */
    public void setAddress(GeoInfoMngmnt.PropertyMngmnt.V1.PropertyAddressType address) {
        this.address = address;
    }

    



}
