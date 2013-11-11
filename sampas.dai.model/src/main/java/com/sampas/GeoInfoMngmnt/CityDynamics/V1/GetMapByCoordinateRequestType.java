/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.sampas.GeoInfoMngmnt.CityDynamics.V1;

import org.omg.CORBA.Object;



public class GetMapByCoordinateRequestType   {

	private MapProjectionType MapProjection;

	public void setMapProjection(MapProjectionType mapProjection) {
		MapProjection = mapProjection;
	}

	public MapProjectionType getMapProjection() {
		return MapProjection;
	}



} // GetMapByCoordinateRequestType
