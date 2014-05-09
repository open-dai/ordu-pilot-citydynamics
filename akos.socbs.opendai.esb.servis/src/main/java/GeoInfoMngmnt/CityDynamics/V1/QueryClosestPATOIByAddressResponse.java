/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package GeoInfoMngmnt.CityDynamics.V1;


import java.util.List;

public class QueryClosestPATOIByAddressResponse  {

	private List<PatoiType> Patois;

	public void setPatois(List<PatoiType> patois) {
		Patois = patois;
	}

	public List<PatoiType> getPatois() {
		return Patois;
	}

} // queryClosestPatoiByAddressResponseType
