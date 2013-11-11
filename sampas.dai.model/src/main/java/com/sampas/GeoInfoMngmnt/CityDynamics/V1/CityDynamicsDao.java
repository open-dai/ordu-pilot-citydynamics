package com.sampas.GeoInfoMngmnt.CityDynamics.V1;


public interface CityDynamicsDao {

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.PatoiType[] queryClosestPatoi(
			com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPatoiRequestType queryClosestPatoi)
			;

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.PatoiType[] queryClosestPatoiByAddress(
			com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPatoiByAddressRequestType queryClosestPatoiByAddressRequest)
			;

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateResponseType getMapByCoordinate(
			com.sampas.GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateRequestType getMapByCoordinate)
			;

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.PoiType[] queryClosestPoi(
			com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPoiRequestType queryClosestPoi)
		;
	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.CanPoiItem[] FindClosestGC(com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestGcRequestType queryClosestGcRequestType);

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.CanType[] QueryCanType() ;



	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.GcCollectScheduleItem[] GetGCHoursForCan() ;


}
