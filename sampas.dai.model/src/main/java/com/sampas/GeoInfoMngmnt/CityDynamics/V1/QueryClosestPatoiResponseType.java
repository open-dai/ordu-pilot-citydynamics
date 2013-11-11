/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.sampas.GeoInfoMngmnt.CityDynamics.V1;

import java.util.List;



public class QueryClosestPatoiResponseType  {

	private List<PatoiType> Patois;

	public void setPatois(List<PatoiType> patois) {
		Patois = patois;
	}

	public List<PatoiType> getPatois() {
		return Patois;
	}

} // queryClosestPatoiResponseType
