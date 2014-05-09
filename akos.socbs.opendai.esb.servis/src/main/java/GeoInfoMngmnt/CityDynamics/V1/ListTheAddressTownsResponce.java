package GeoInfoMngmnt.CityDynamics.V1;

import Common.V1.AddressTownItem;
import GeoInfoMngmnt.PropertyMngmnt.V1.CountyType;

public class ListTheAddressTownsResponce {
private CountyType[] addressTownItemList;

public CountyType[] getAddressTownItemList() {
	return addressTownItemList;
}

public void setAddressTownItemList(CountyType[] addressTownItemList) {
	this.addressTownItemList = addressTownItemList;
}
}
