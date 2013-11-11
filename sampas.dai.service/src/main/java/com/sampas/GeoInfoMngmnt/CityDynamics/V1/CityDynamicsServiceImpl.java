package com.sampas.GeoInfoMngmnt.CityDynamics.V1;

import com.sampas.akos.common.dao.BaseDaoSupport;


public class CityDynamicsServiceImpl  extends BaseDaoSupport implements CityDynamicsService {

	private CityDynamicsDao cityDynamicsDao;

	public CityDynamicsDao getCityDynamicsDao() {
		return cityDynamicsDao;
	}

	public void setCityDynamicsDao(CityDynamicsDao cityDynamicsDao) {
		this.cityDynamicsDao = cityDynamicsDao;
	}

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.PatoiType[] queryClosestPatoi(
			com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPatoiRequestType queryClosestPatoi)
			 {
		/*
		com.sampas.GeoInfoMngmnt.CityDynamics.V1.PATOIType[] ret = cityDynamicsService
				.queryClosestPatoi(queryClosestPatoi);
			*/
		//System.out.println("ozg 2: queryClosestPatoi() server response..");



		return cityDynamicsDao.queryClosestPatoi(queryClosestPatoi);
	}

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.PatoiType[] queryClosestPatoiByAddress(
			com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPatoiByAddressRequestType queryClosestPatoiByAddressRequest)
			 {
		System.out.println("queryClosestPatoiByAddress() server response..");
		return cityDynamicsDao.queryClosestPatoiByAddress(queryClosestPatoiByAddressRequest);
	}

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateResponseType getMapByCoordinate(
			com.sampas.GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateRequestType getMapByCoordinate)
			 {
		System.out.println("getMapByCoordinate() server response..");
		return null;
	}

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.PoiType[] queryClosestPoi(
			com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPoiRequestType queryClosestPoi)
			 {
		return cityDynamicsDao.queryClosestPoi(queryClosestPoi);
	}

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.CanPoiItem[] FindClosestGC(com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestGcRequestType queryClosestGcRequestType){
		return cityDynamicsDao.FindClosestGC(queryClosestGcRequestType);
	}

	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.CanType[] QueryCanType() {
		return cityDynamicsDao.QueryCanType();
	}



	public com.sampas.GeoInfoMngmnt.CityDynamics.V1.GcCollectScheduleItem[] GetGCHoursForCan() {
		return cityDynamicsDao.GetGCHoursForCan();
	}
}
