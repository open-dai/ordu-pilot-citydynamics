/**
 * DetachedType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.PropertyMngmnt.V1;

public class DetachedType  extends Common.V1.RecordType  {
    private GeoInfoMngmnt.PropertyMngmnt.V1.HousingAddressType address;

    public DetachedType() {
    }

    public DetachedType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           GeoInfoMngmnt.PropertyMngmnt.V1.HousingAddressType address) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
        this.address = address;
    }


    /**
     * Gets the address value for this DetachedType.
     *
     * @return address
     */
    public GeoInfoMngmnt.PropertyMngmnt.V1.HousingAddressType getAddress() {
        return address;
    }


    /**
     * Sets the address value for this DetachedType.
     *
     * @param address
     */
    public void setAddress(GeoInfoMngmnt.PropertyMngmnt.V1.HousingAddressType address) {
        this.address = address;
    }

   



}
