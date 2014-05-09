/**
 * PATOIType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.CityDynamics.V1;


/**
 * PATOI type definition
 */
public class PatoiType  {

	private java.lang.Double x;
	private java.lang.Double y;

	private java.lang.Long companyId;
	private java.lang.String comment;
	private java.lang.String isItCloset;
	private java.lang.Double distance;


    private GeoInfoMngmnt.CityDynamics.V1.PoiType poi;

    private java.lang.String timeOfInterest;

    /**
     * Gets the poi value for this PATOIType.
     *
     * @return poi
     */
    public GeoInfoMngmnt.CityDynamics.V1.PoiType getPoi() {
        return poi;
    }


    /**
     * Sets the poi value for this PATOIType.
     *
     * @param poi
     */
    public void setPoi(GeoInfoMngmnt.CityDynamics.V1.PoiType poi) {
        this.poi = poi;
    }


    /**
     * Gets the timeOfInterest value for this PATOIType.
     *
     * @return timeOfInterest
     */
    public java.lang.String getTimeOfInterest() {
        return timeOfInterest;
    }


    /**
     * Sets the timeOfInterest value for this PATOIType.
     *
     * @param timeOfInterest
     */
    public void setTimeOfInterest(java.lang.String timeOfInterest) {
        this.timeOfInterest = timeOfInterest;
    }


	public java.lang.Double getX() {
		return x;
	}


	public void setX(java.lang.Double x) {
		this.x = x;
	}


	public java.lang.Double getY() {
		return y;
	}


	public void setY(java.lang.Double y) {
		this.y = y;
	}


	public java.lang.Long getCompanyId() {
		return companyId;
	}


	public void setCompanyId(java.lang.Long companyId) {
		this.companyId = companyId;
	}


	public java.lang.String getComment() {
		return comment;
	}


	public void setComment(java.lang.String comment) {
		this.comment = comment;
	}


	public java.lang.String getIsItCloset() {
		return isItCloset;
	}


	public void setIsItCloset(java.lang.String isItCloset) {
		this.isItCloset = isItCloset;
	}


	public java.lang.Double getDistance() {
		return distance;
	}


	public void setDistance(java.lang.Double distance) {
		this.distance = distance;
	}




}
