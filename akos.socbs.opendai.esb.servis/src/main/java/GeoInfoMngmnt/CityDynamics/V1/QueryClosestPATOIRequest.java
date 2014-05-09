
package GeoInfoMngmnt.CityDynamics.V1;

import java.util.List;


public class QueryClosestPATOIRequest {

	private CoordinateType MyCoordinate;

	private String PoiType;

	List<Object> SearchCriterias;

	public void setMyCoordinate(CoordinateType myCoordinate) {
		MyCoordinate = myCoordinate;
	}

	public CoordinateType getMyCoordinate() {
		return MyCoordinate;
	}

	public void setPoiType(String poiType) {
		PoiType = poiType;
	}

	public String getPoiType() {
		return PoiType;
	}

} // queryClosestPatoiRequestType
