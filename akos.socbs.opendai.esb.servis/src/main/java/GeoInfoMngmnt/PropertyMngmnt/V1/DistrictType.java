/**
 * DistrictType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.PropertyMngmnt.V1;

public class DistrictType  extends Common.V1.RecordType  {
	
	
	
    private Common.V1.ComponentType districtInfo;
    private Common.V1.ComponentType townInfo;
    
    private java.lang.String isVillage;

    private java.lang.String districtTUIKCode;

    public DistrictType() {
    }

    public DistrictType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate,
           Common.V1.ComponentType districtInfo,
           java.lang.String isVillage,
           java.lang.String districtTUIKCode) {
        super(
            id,
            createdBy,
            createdDate,
            updatedBy,
            updatedDate);
        this.districtInfo = districtInfo;
        this.isVillage = isVillage;
        this.districtTUIKCode = districtTUIKCode;
    }


    /**
     * Gets the districtInfo value for this DistrictType.
     *
     * @return districtInfo
     */
    public Common.V1.ComponentType getDistrictInfo() {
        return districtInfo;
    }


    /**
     * Sets the districtInfo value for this DistrictType.
     *
     * @param districtInfo
     */
    public void setDistrictInfo(Common.V1.ComponentType districtInfo) {
        this.districtInfo = districtInfo;
    }


    /**
     * Gets the isVillage value for this DistrictType.
     *
     * @return isVillage
     */
    public java.lang.String getIsVillage() {
        return isVillage;
    }


    /**
     * Sets the isVillage value for this DistrictType.
     *
     * @param isVillage
     */
    public void setIsVillage(java.lang.String isVillage) {
        this.isVillage = isVillage;
    }


    /**
     * Gets the districtTUIKCode value for this DistrictType.
     *
     * @return districtTUIKCode
     */
    public java.lang.String getDistrictTUIKCode() {
        return districtTUIKCode;
    }


    /**
     * Sets the districtTUIKCode value for this DistrictType.
     *
     * @param districtTUIKCode
     */
    public void setDistrictTUIKCode(java.lang.String districtTUIKCode) {
        this.districtTUIKCode = districtTUIKCode;
    }

	public Common.V1.ComponentType getTownInfo() {
		return townInfo;
	}

	public void setTownInfo(Common.V1.ComponentType townInfo) {
		this.townInfo = townInfo;
	}

    

}
