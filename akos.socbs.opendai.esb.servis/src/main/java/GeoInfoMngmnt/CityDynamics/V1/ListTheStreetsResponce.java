package GeoInfoMngmnt.CityDynamics.V1;

import GeoInfoMngmnt.PropertyMngmnt.V1.StreetType;

public class ListTheStreetsResponce {
	
	private StreetType[] streetTypeList;

	public StreetType[] getStreetTypeList() {
		return streetTypeList;
	}

	public void setStreetTypeList(StreetType[] streetTypeList) {
		this.streetTypeList = streetTypeList;
	}
	
}
