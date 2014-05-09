/**
 * CountyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.PropertyMngmnt.V1;

public class CountyType  extends Common.V1.RecordType   {
    private Common.V1.ComponentType info;

    public CountyType() {
    }

    public CountyType(
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
     * Gets the info value for this CountyType.
     *
     * @return info
     */
    public Common.V1.ComponentType getInfo() {
        return info;
    }


    /**
     * Sets the info value for this CountyType.
     *
     * @param info
     */
    public void setInfo(Common.V1.ComponentType info) {
        this.info = info;
    }

   


}
