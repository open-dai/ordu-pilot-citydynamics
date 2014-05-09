/**
 * CadastralAddressType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.PropertyMngmnt.V1;

public class CadastralAddressType  extends GeoInfoMngmnt.PropertyMngmnt.V1.BaseAddressType  {
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

   

}
