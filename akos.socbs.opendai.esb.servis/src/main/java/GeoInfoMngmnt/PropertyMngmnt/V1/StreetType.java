/**
 * StreetType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.PropertyMngmnt.V1;

public class StreetType  extends Common.V1.RecordType {
    private Common.V1.ComponentType streetInfo;
    private Common.V1.ComponentType districtInfo;
    
    private java.lang.String length;

    private java.lang.String width;

    public StreetType() {
    }

    public StreetType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           Common.V1.ComponentType streetInfo,
           java.lang.String length,
           java.lang.String width) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
        this.streetInfo = streetInfo;
        this.length = length;
        this.width = width;
    }


    /**
     * Gets the streetInfo value for this StreetType.
     *
     * @return streetInfo
     */
    public Common.V1.ComponentType getStreetInfo() {
        return streetInfo;
    }


    /**
     * Sets the streetInfo value for this StreetType.
     *
     * @param streetInfo
     */
    public void setStreetInfo(Common.V1.ComponentType streetInfo) {
        this.streetInfo = streetInfo;
    }


    /**
     * Gets the length value for this StreetType.
     *
     * @return length
     */
    public java.lang.String getLength() {
        return length;
    }


    /**
     * Sets the length value for this StreetType.
     *
     * @param length
     */
    public void setLength(java.lang.String length) {
        this.length = length;
    }


    /**
     * Gets the width value for this StreetType.
     *
     * @return width
     */
    public java.lang.String getWidth() {
        return width;
    }


    /**
     * Sets the width value for this StreetType.
     *
     * @param width
     */
    public void setWidth(java.lang.String width) {
        this.width = width;
    }

	public Common.V1.ComponentType getDistrictInfo() {
		return districtInfo;
	}

	public void setDistrictInfo(Common.V1.ComponentType districtInfo) {
		this.districtInfo = districtInfo;
	}

   

}
