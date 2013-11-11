package com.sampas.GeoInfoMngmnt.CityDynamics.V1;

public class QueryClosestGcRequestType {
	private CoordinateType Coordinate;

	private CanType canType;

	public void setCoordinate(CoordinateType coordinate) {
		Coordinate = coordinate;
	}

	public CoordinateType getCoordinate() {
		return Coordinate;
	}

	public void setCanType(CanType canType) {
		this.canType = canType;
	}

	public CanType getCanType() {
		return canType;
	}


}
