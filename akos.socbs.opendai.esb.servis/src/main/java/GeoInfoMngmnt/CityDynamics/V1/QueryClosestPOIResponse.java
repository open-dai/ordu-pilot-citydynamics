
package GeoInfoMngmnt.CityDynamics.V1;

import java.util.List;

public class QueryClosestPOIResponse  {

	private List<PoiType> Pois;

	public void setPois(List<PoiType> pois) {
		Pois = pois;
	}

	public List<PoiType> getPois() {
		return Pois;
	}

} 
