/**
 * PlotType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.PropertyMngmnt.V1;

public class PlotType  extends Common.V1.RecordType {
    private java.lang.String description;

    private Common.V1.AreaType area;

    private GeoInfoMngmnt.PropertyMngmnt.V1.CadastralAddressType address;

    public PlotType() {
    }

    public PlotType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           java.lang.String description,
           Common.V1.AreaType area,
           GeoInfoMngmnt.PropertyMngmnt.V1.CadastralAddressType address) {
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
    public Common.V1.AreaType getArea() {
        return area;
    }


    /**
     * Sets the area value for this PlotType.
     *
     * @param area
     */
    public void setArea(Common.V1.AreaType area) {
        this.area = area;
    }


    /**
     * Gets the address value for this PlotType.
     *
     * @return address
     */
    public GeoInfoMngmnt.PropertyMngmnt.V1.CadastralAddressType getAddress() {
        return address;
    }


    /**
     * Sets the address value for this PlotType.
     *
     * @param address
     */
    public void setAddress(GeoInfoMngmnt.PropertyMngmnt.V1.CadastralAddressType address) {
        this.address = address;
    }

    
}
