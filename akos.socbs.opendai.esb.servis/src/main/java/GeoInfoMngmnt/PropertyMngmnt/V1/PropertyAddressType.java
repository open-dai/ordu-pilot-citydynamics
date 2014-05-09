/**
 * PropertyAddressType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.PropertyMngmnt.V1;

public class PropertyAddressType  extends GeoInfoMngmnt.PropertyMngmnt.V1.CadastralAddressType {
    private java.lang.String parcelNumber;

    public PropertyAddressType() {
    }

    public PropertyAddressType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           java.lang.String plotNumber,
           java.lang.String islandNumber,
           java.lang.String parcelNumber) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate,
            plotNumber,
            islandNumber);
        this.parcelNumber = parcelNumber;
    }


    /**
     * Gets the parcelNumber value for this PropertyAddressType.
     *
     * @return parcelNumber
     */
    public java.lang.String getParcelNumber() {
        return parcelNumber;
    }


    /**
     * Sets the parcelNumber value for this PropertyAddressType.
     *
     * @param parcelNumber
     */
    public void setParcelNumber(java.lang.String parcelNumber) {
        this.parcelNumber = parcelNumber;
    }


}
