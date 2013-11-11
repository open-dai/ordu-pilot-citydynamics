package com.sampas.GeoInfoMngmnt.CityDynamics.V1.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sampas.GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateRequestType;

import com.sampas.GeoInfoMngmnt.CityDynamics.V1.CanPoiItem;
import com.sampas.GeoInfoMngmnt.CityDynamics.V1.CanType;
import com.sampas.GeoInfoMngmnt.CityDynamics.V1.CityDynamicsService;
import com.sampas.GeoInfoMngmnt.CityDynamics.V1.CoordinateType;
import com.sampas.GeoInfoMngmnt.CityDynamics.V1.DistanceFromCoordinates;
import com.sampas.GeoInfoMngmnt.CityDynamics.V1.GcCollectScheduleItem;
import com.sampas.GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateRequestType;
import com.sampas.GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateResponseType;
import com.sampas.GeoInfoMngmnt.CityDynamics.V1.PatoiType;
import com.sampas.GeoInfoMngmnt.CityDynamics.V1.PoiType;
import com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestGcRequestType;
import com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPatoiByAddressRequestType;
import com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPatoiRequestType;
import com.sampas.GeoInfoMngmnt.CityDynamics.V1.QueryClosestPoiRequestType;
import com.sampas.common.model.AkosException;

public class SampasDaiServiceImpl implements CityDynamicsService {

	private CityDynamicsService cityDynamicsService;
	private HibernateTemplate hibernateTemplate;

	public GetMapByCoordinateResponseType getMapByCoordinate(
			GetMapByCoordinateRequestType mapByCoordinate) {
		// TODO Auto-generated method stub
		return null;
	}

	public PatoiType[] queryClosestPatoi(
			QueryClosestPatoiRequestType closestPATOI) {
		System.out.println("ozg 1: queryClosestPatoi() server response..");
		return cityDynamicsService.queryClosestPatoi(closestPATOI);
	}

	public PatoiType[] queryClosestPatoiByAddress(
			QueryClosestPatoiByAddressRequestType closestPATOIByAddressRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	public PoiType[] queryClosestPoi(QueryClosestPoiRequestType closestPOI) {
		// TODO Auto-generated method stub
		return cityDynamicsService.queryClosestPoi(closestPOI);
	}


	public CanPoiItem[] FindClosestGC(QueryClosestGcRequestType queryClosestGcRequestType) {

		return cityDynamicsService.FindClosestGC(queryClosestGcRequestType);

	}

	public CanType[] QueryCanType() {
		return cityDynamicsService.QueryCanType();
	}



	public GcCollectScheduleItem[] GetGCHoursForCan() {
		return cityDynamicsService.GetGCHoursForCan();
	}




	public CityDynamicsService getCityDynamicsService() {
		return cityDynamicsService;
	}

	public void setCityDynamicsService(CityDynamicsService cityDynamicsService) {
		this.cityDynamicsService = cityDynamicsService;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
