package GeoInfoMngmnt.CityDynamics.V1;

import Common.V1.AddressDistrictItem;
import GeoInfoMngmnt.PropertyMngmnt.V1.DistrictType;

public class ListTheAddressDistrictResponce {

	
	private DistrictType[] addressDistrictItem;

	public DistrictType[] getAddressDistrictItem() {
		return addressDistrictItem;
	}

	public void setAddressDistrictItem(DistrictType[] addressDistrictItem) {
		this.addressDistrictItem = addressDistrictItem;
	}


}
