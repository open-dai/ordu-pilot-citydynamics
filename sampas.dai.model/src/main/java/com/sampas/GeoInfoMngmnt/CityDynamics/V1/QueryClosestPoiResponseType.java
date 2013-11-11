/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.sampas.GeoInfoMngmnt.CityDynamics.V1;

import java.util.List;

public class QueryClosestPoiResponseType  {

	private List<PoiType> Pois;

	public void setPois(List<PoiType> pois) {
		Pois = pois;
	}

	public List<PoiType> getPois() {
		return Pois;
	}

} // queryClosestPoiResponseType
