package GeoInfoMngmnt.CityDynamics.V1;

import Common.V1.AddressDistrictItem;
import Common.V1.AddressStreetItem;

public class ListTheCansByStreetRequest {

	private AddressDistrictItem addressDistrictItem;
	private AddressStreetItem addressStreetItem;

	public AddressDistrictItem getAddressDistrictItem() {
		return addressDistrictItem;
	}

	public void setAddressDistrictItem(AddressDistrictItem addressDistrictItem) {
		this.addressDistrictItem = addressDistrictItem;
	}

	public AddressStreetItem getAddressStreetItem() {
		return addressStreetItem;
	}

	public void setAddressStreetItem(AddressStreetItem addressStreetItem) {
		this.addressStreetItem = addressStreetItem;
	}

}
