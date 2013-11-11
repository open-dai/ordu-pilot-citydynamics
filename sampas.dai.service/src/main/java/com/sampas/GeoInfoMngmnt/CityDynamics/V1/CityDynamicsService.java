package com.sampas.GeoInfoMngmnt.CityDynamics.V1;


public interface CityDynamicsService {

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.PatoiType[] queryClosestPatoi(
			com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPatoiRequestType p_queryClosestPatoi)
			;

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.PatoiType[] queryClosestPatoiByAddress(
			 com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPatoiByAddressRequestType p_queryClosestPatoiByAddressRequest)
			;

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateResponseType getMapByCoordinate(
			com.sampas.GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateRequestType p_getMapByCoordinate)
			;

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.PoiType[] queryClosestPoi(
			com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPoiRequestType p_queryClosestPoi)
		;


	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.CanPoiItem[] FindClosestGC(com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestGcRequestType queryClosestGcRequestType);

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.CanType[] QueryCanType() ;

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.GcCollectScheduleItem[] GetGCHoursForCan() ;

	/*
	 * public com.sampas.GeoInfoMngmnt.CityDynamics.V1.PATOIType[]
	 * queryClosestPatoiByAddress(com.sampas.GeoInfoMngmnt.CityDynamics.V1.
	 * queryClosestPatoiByAddressRequestType queryClosestPatoiByAddressRequest)
	 * throws java.rmi.RemoteException {
	 * com.sampas.GeoInfoMngmnt.CityDynamics.V1.PATOIType[] ret =
	 * cityDynamicsService
	 * .queryClosestPatoiByAddress(queryClosestPatoiByAddressRequest); return
	 * ret; }
	 *
	 * public
	 * com.sampas.GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateResponseType
	 * getMapByCoordinate
	 * (com.sampas.GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateRequestType
	 * getMapByCoordinate) throws java.rmi.RemoteException {
	 * com.sampas.GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateResponseType
	 * ret = cityDynamicsService.getMapByCoordinate(getMapByCoordinate); return
	 * ret; }
	 *
	 * public com.sampas.GeoInfoMngmnt.CityDynamics.V1.POIType[]
	 * queryClosestPoi(
	 * com.sampas.GeoInfoMngmnt.CityDynamics.V1.queryClosestPoiRequestType
	 * queryClosestPoi) throws java.rmi.RemoteException {
	 * com.sampas.GeoInfoMngmnt.CityDynamics.V1.POIType[] ret =
	 * cityDynamicsService.queryClosestPoi(queryClosestPoi); return ret; }
	 */
}
