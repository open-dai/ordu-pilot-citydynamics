/**
 * BuildingType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.PropertyMngmnt.V1;

public class BuildingType  extends Common.V1.RecordType  {
    private java.lang.String buildingName;

    private java.lang.String siteName;

    private GeoInfoMngmnt.PropertyMngmnt.V1.ZoningAddressType address;

    public BuildingType() {
    }

    public BuildingType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           java.lang.String buildingName,
           java.lang.String siteName,
           GeoInfoMngmnt.PropertyMngmnt.V1.ZoningAddressType address) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
        this.buildingName = buildingName;
        this.siteName = siteName;
        this.address = address;
    }


    /**
     * Gets the buildingName value for this BuildingType.
     *
     * @return buildingName
     */
    public java.lang.String getBuildingName() {
        return buildingName;
    }


    /**
     * Sets the buildingName value for this BuildingType.
     *
     * @param buildingName
     */
    public void setBuildingName(java.lang.String buildingName) {
        this.buildingName = buildingName;
    }


    /**
     * Gets the siteName value for this BuildingType.
     *
     * @return siteName
     */
    public java.lang.String getSiteName() {
        return siteName;
    }


    /**
     * Sets the siteName value for this BuildingType.
     *
     * @param siteName
     */
    public void setSiteName(java.lang.String siteName) {
        this.siteName = siteName;
    }


    /**
     * Gets the address value for this BuildingType.
     *
     * @return address
     */
    public GeoInfoMngmnt.PropertyMngmnt.V1.ZoningAddressType getAddress() {
        return address;
    }


    /**
     * Sets the address value for this BuildingType.
     *
     * @param address
     */
    public void setAddress(GeoInfoMngmnt.PropertyMngmnt.V1.ZoningAddressType address) {
        this.address = address;
    }

   



}
