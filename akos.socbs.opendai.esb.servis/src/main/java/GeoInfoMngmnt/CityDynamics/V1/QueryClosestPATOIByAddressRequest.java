/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package GeoInfoMngmnt.CityDynamics.V1;

import GeoInfoMngmnt.PropertyMngmnt.V1.AddressType;


public class QueryClosestPATOIByAddressRequest {


	private AddressType Address;

	private String PoiType;

	public void setPoiType(String poiType) {
		PoiType = poiType;
	}

	public String getPoiType() {
		return PoiType;
	}


	public AddressType getAddress() {
		return Address;
	}

	public void setAddress(AddressType address) {
		Address = address;
	}

} // queryClosestPatoiByAddressRequestType
