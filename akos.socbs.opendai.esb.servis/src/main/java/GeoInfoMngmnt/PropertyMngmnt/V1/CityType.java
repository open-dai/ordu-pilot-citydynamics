/**
 * CityType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.PropertyMngmnt.V1;

public class CityType  extends Common.V1.RecordType   {
    private Common.V1.ComponentType info;

    public CityType() {
    }

    public CityType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           Common.V1.ComponentType info) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
        this.info = info;
    }


    /**
     * Gets the info value for this CityType.
     *
     * @return info
     */
    public Common.V1.ComponentType getInfo() {
        return info;
    }


    /**
     * Sets the info value for this CityType.
     *
     * @param info
     */
    public void setInfo(Common.V1.ComponentType info) {
        this.info = info;
    }

   


}
