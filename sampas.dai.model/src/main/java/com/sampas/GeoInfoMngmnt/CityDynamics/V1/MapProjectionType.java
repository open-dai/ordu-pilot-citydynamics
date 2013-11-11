/**
 * MapProjectionType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.CityDynamics.V1;


/**
 * Map projection type definition
 */
public class MapProjectionType  implements java.io.Serializable {
    private com.sampas.GeoInfoMngmnt.CityDynamics.V1.CoordinateType coordinate;

    private java.lang.String offset;

    private java.lang.String dimension;

    public MapProjectionType() {
    }

    public MapProjectionType(
           com.sampas.GeoInfoMngmnt.CityDynamics.V1.CoordinateType coordinate,
           java.lang.String offset,
           java.lang.String dimension) {
           this.coordinate = coordinate;
           this.offset = offset;
           this.dimension = dimension;
    }


    /**
     * Gets the coordinate value for this MapProjectionType.
     * 
     * @return coordinate
     */
    public com.sampas.GeoInfoMngmnt.CityDynamics.V1.CoordinateType getCoordinate() {
        return coordinate;
    }


    /**
     * Sets the coordinate value for this MapProjectionType.
     * 
     * @param coordinate
     */
    public void setCoordinate(com.sampas.GeoInfoMngmnt.CityDynamics.V1.CoordinateType coordinate) {
        this.coordinate = coordinate;
    }


    /**
     * Gets the offset value for this MapProjectionType.
     * 
     * @return offset
     */
    public java.lang.String getOffset() {
        return offset;
    }


    /**
     * Sets the offset value for this MapProjectionType.
     * 
     * @param offset
     */
    public void setOffset(java.lang.String offset) {
        this.offset = offset;
    }


    /**
     * Gets the dimension value for this MapProjectionType.
     * 
     * @return dimension
     */
    public java.lang.String getDimension() {
        return dimension;
    }


    /**
     * Sets the dimension value for this MapProjectionType.
     * 
     * @param dimension
     */
    public void setDimension(java.lang.String dimension) {
        this.dimension = dimension;
    }

  

}
