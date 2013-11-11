/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.sampas.GeoInfoMngmnt.CityDynamics.V1;

public class QueryClosestPoiRequestType  {

	private CoordinateType Coordinate;

	private String PoiType;

	public void setCoordinate(CoordinateType coordinate) {
		Coordinate = coordinate;
	}

	public CoordinateType getCoordinate() {
		return Coordinate;
	}

	public void setPoiType(String poiType) {
		PoiType = poiType;
	}

	public String getPoiType() {
		return PoiType;
	}

} // queryClosestPoiRequestType
