package com.sampas.GeoInfoMngmnt.CityDynamics.V1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface CityDynamicsService {

	@WebMethod
	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.PatoiType[] queryClosestPatoi(
			@WebParam(name = "p_queryClosestPatoi") com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPatoiRequestType p_queryClosestPatoi);

	@WebMethod
	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.PatoiType[] queryClosestPatoiByAddress(
			@WebParam(name = "p_queryClosestPatoiByAddressRequest") com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPatoiByAddressRequestType p_queryClosestPatoiByAddressRequest);

	@WebMethod
	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateResponseType getMapByCoordinate(
			@WebParam(name = "p_getMapByCoordinate") com.sampas.GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateRequestType p_getMapByCoordinate);

	@WebMethod
	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.PoiType[] queryClosestPoi(@WebParam(name = "closestPOI")  com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPoiRequestType closestPOI) ;

	@WebMethod
	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.CanPoiItem[] FindClosestGC(@WebParam(name = "queryClosestGcRequestType")  com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestGcRequestType queryClosestGcRequestType) ;

	@WebMethod
	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.CanType[] QueryCanType() ;


	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.GcCollectScheduleItem[] GetGCHoursForCan();



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
