package GeoInfoMngmnt.CityDynamics.V1;

import Common.V1.AddressCityItem;
import GeoInfoMngmnt.PropertyMngmnt.V1.CityType;

public class ListTheAddressCityResponce {
	private CityType[] addressCityItemList;

	public CityType[] getAddressCityItemList() {
		return addressCityItemList;
	}

	public void setAddressCityItemList(CityType[] addressCityItemList) {
		this.addressCityItemList = addressCityItemList;
	}
}
