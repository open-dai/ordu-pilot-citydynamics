/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package GeoInfoMngmnt.CityDynamics.V1;

public class GetMapByCoordinateRequest   {

	private MapProjectionType MapProjection;

	public void setMapProjection(MapProjectionType mapProjection) {
		MapProjection = mapProjection;
	}

	public MapProjectionType getMapProjection() {
		return MapProjection;
	}



} 
