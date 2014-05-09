/**
 * POIType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package GeoInfoMngmnt.CityDynamics.V1;


/**
 * POI type definition
 */
public class PoiType   {
    private java.lang.String name;



    private GeoInfoMngmnt.CityDynamics.V1.CoordinateType coordinate;

    private java.lang.String description;

    private java.lang.String poiType;

    private GeoInfoMngmnt.CityDynamics.V1.PoiAttributeType[] attributes;

    private java.lang.Double x;
    private java.lang.Double y;

    public PoiType() {
    }

    public PoiType(
           java.lang.String name,
           GeoInfoMngmnt.CityDynamics.V1.CoordinateType coordinate,
           java.lang.String description,
           java.lang.String poiType,
           GeoInfoMngmnt.CityDynamics.V1.PoiAttributeType[] attributes) {
           this.name = name;
           this.coordinate = coordinate;
           this.description = description;
           this.poiType = poiType;
           this.attributes = attributes;
    }


    /**
     * Gets the name value for this POIType.
     *
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this POIType.
     *
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the coordinate value for this POIType.
     *
     * @return coordinate
     */
    public GeoInfoMngmnt.CityDynamics.V1.CoordinateType getCoordinate() {
        return coordinate;
    }


    /**
     * Sets the coordinate value for this POIType.
     *
     * @param coordinate
     */
    public void setCoordinate(GeoInfoMngmnt.CityDynamics.V1.CoordinateType coordinate) {
        this.coordinate = coordinate;
    }


    /**
     * Gets the description value for this POIType.
     *
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this POIType.
     *
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the poiType value for this POIType.
     *
     * @return poiType
     */
    public java.lang.String getPoiType() {
        return poiType;
    }


    /**
     * Sets the poiType value for this POIType.
     *
     * @param poiType
     */
    public void setPoiType(java.lang.String poiType) {
        this.poiType = poiType;
    }

	public void setX(java.lang.Double x) {
		this.x = x;
	}

	public java.lang.Double getX() {
		return x;
	}

	public void setY(java.lang.Double y) {
		this.y = y;
	}

	public java.lang.Double getY() {
		return y;
	}


}
