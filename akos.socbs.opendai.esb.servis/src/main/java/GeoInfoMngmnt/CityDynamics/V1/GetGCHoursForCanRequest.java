package GeoInfoMngmnt.CityDynamics.V1;

import Common.V1.AddressDistrictItem;
import Common.V1.AddressStreetItem;

public class GetGCHoursForCanRequest {

	
	private AddressStreetItem addressStreetItem; 
	private AddressDistrictItem addressDistrictItem;

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
