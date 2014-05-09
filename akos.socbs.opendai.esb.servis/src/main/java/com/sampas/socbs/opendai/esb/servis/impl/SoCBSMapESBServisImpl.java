package com.sampas.socbs.opendai.esb.servis.impl;

import java.util.ArrayList;
import java.util.List;

import Common.V1.ComponentType;
import GeoInfoMngmnt.CityDynamics.V1.CanPoiItem;
import GeoInfoMngmnt.CityDynamics.V1.FindClosestGCRequest;
import GeoInfoMngmnt.CityDynamics.V1.FindClosestGCResponce;
import GeoInfoMngmnt.CityDynamics.V1.GcCollectScheduleItem;
import GeoInfoMngmnt.CityDynamics.V1.GetGCHoursForCanRequest;
import GeoInfoMngmnt.CityDynamics.V1.GetGCHoursForCanResponce;
import GeoInfoMngmnt.CityDynamics.V1.GetInformationForCanRequest;
import GeoInfoMngmnt.CityDynamics.V1.GetInformationForCanResponce;
import GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateRequest;
import GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateResponse;
import GeoInfoMngmnt.CityDynamics.V1.ImageType;
import GeoInfoMngmnt.CityDynamics.V1.ListCansByTypeResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListPOITypesRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListPOITypesResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListPOIsByAreaRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListPOIsByAreaResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListPOIsByTypeRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListPOIsByTypeResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListTheAddressCityResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListTheAddressDistrictRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListTheAddressDistrictResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListTheAddressStreetRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListTheAddressStreetResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListTheAddressTownsRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListTheAddressTownsResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListTheCansByStreetRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListTheCansByStreetResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListTheStreetsRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListTheStreetsResponce;
import GeoInfoMngmnt.CityDynamics.V1.PatoiType;
import GeoInfoMngmnt.CityDynamics.V1.PoiType;
import GeoInfoMngmnt.CityDynamics.V1.QueryBusinessOfficeRequest;
import GeoInfoMngmnt.CityDynamics.V1.QueryBusinessOfficeResponse;
import GeoInfoMngmnt.CityDynamics.V1.QueryCanTypeResponce;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPATOIByAddressRequest;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPATOIByAddressResponse;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPATOIRequest;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPATOIResponse;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPOIRequest;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPOIResponse;
import GeoInfoMngmnt.PropertyMngmnt.V1.AddressType;
import GeoInfoMngmnt.PropertyMngmnt.V1.HourType;
import GeoInfoMngmnt.PropertyMngmnt.V1.PlaceOfBusinessType;








import com.sampas.akos.ortak.model.Bagimsiz;
import com.sampas.akos.ortak.model.KurumSabit;
//import com.sampas.akos.ortak.resim.model.BinaResim;
//import com.sampas.akos.ortak.resim.servis.IOrtakResimServis;
import com.sampas.akos.ortak.servis.OrtakServis;
import com.sampas.gis.ortak.model.PoiTypes;
import com.sampas.ortak.spatial.servis.IOrtakSpatialServis;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.CoordinateSystemTransformers;
import com.sampas.socbs.opendai.servis.IMapServis;
import com.sampas.socbs.opendai.dao.SoCBSOpenDaiESBDaosImpl;
import com.sampas.socbs.opendai.esb.servis.SoCBSMapESBServis;
import com.sampas.socbs.poi.servis.IPOIServis;
import com.sampas.socbs.poi.servis.returntypes.PoiReturnType;

public class SoCBSMapESBServisImpl implements SoCBSMapESBServis {

	private IMapServis mapServis;

	private IPOIServis poiServis;

	private IOrtakSpatialServis ortakSpatialServis;

	private OrtakServis ortakServis;
	
	private SoCBSOpenDaiESBDaosImpl openDAIDAO = new SoCBSOpenDaiESBDaosImpl() ;

	private Long sabitKurumId = 1L; 
	

	private ICoordinateSystemTransformers coordinateSystemTransformers = new CoordinateSystemTransformers();
	
	// private boolean dmServiceUse;



	public ListTheAddressStreetResponce listTheAddressStreet(ListTheAddressStreetRequest listTheAddressStreetRequest) 
	{
		return getOpenDAIDAO().listTheAddressStreet(listTheAddressStreetRequest);
	}	


	
	public QueryBusinessOfficeResponse queryBusinessOffice(QueryBusinessOfficeRequest queryBusinessOfficeRequest)
	{
		QueryBusinessOfficeResponse queryBusinessOfficeResponse = new QueryBusinessOfficeResponse();
		
		List<Bagimsiz> _listBagimsiz =  getOrtakServis().readAllBagimsizByCriteria(null);
		List<PlaceOfBusinessType> _list = new ArrayList<PlaceOfBusinessType>();  
		for(int I = 0;I<_listBagimsiz.size();I++)
		{
			if (_listBagimsiz.get(I).getBagimsizKullanimDetay()!=null)
			{
				if (_listBagimsiz.get(I).getBagimsizKullanimDetay().getIsyeriEh().equals("E"))
				{	
					try
					{
						PlaceOfBusinessType placeOfBusinessType = new PlaceOfBusinessType();
						placeOfBusinessType.setTitle(_listBagimsiz.get(I).getBagimsizAd());
						placeOfBusinessType.setResponsibleNameSurName(_listBagimsiz.get(I).getAciklama());
						placeOfBusinessType.setCompanyName(_listBagimsiz.get(I).getBagimsizAd());
						placeOfBusinessType.setSector(_listBagimsiz.get(I).getBagimsizKullanimDetay().getAciklama());
												
						AddressType addressType = new AddressType();
						addressType.setAddressKind(_listBagimsiz.get(I).getAdres().getAdresTur());
						addressType.setAdress(_listBagimsiz.get(I).getAdres().getAciklama());
						
						ComponentType district = new ComponentType();
						district.setId(_listBagimsiz.get(I).getAdres().getMahalleCaddeSokak().getMahalle().getId().toString());
						district.setDescription(_listBagimsiz.get(I).getAdres().getMahalleCaddeSokak().getMahalle().getAciklama());
						addressType.setDistrict(district);
						
						ComponentType city = new ComponentType();
						city.setId("");
						city.setDescription("");
						addressType.setDistrict(district);
			
						ComponentType street = new ComponentType();
						street.setId(_listBagimsiz.get(I).getAdres().getMahalleCaddeSokak().getCaddeSokak().getId().toString());
						street.setDescription(_listBagimsiz.get(I).getAdres().getMahalleCaddeSokak().getCaddeSokak().getAciklama());
						addressType.setStreet(street);
						
						placeOfBusinessType.setAdress(addressType);
			
						//PhoneNumberType phoneNumber = new PhoneNumberType();
						//phoneNumber.setPhoneNumber(_listBagimsiz.get(I).getBina().ge);
						//placeOfBusinessType.setPhoneNumber(phoneNumber);
						_list.add(placeOfBusinessType);
						
					}catch(Exception ex)
					{
						System.out.println("ERR:"+ex.getMessage());
					}
				
				}
			}
		}
		
		queryBusinessOfficeResponse.setPlaceOfBusinessTypes(_list.toArray(new PlaceOfBusinessType[_list.size()]));
		
		
		return queryBusinessOfficeResponse;
	}	
	
	
	public QueryClosestPATOIResponse queryClosestPATOI(QueryClosestPATOIRequest queryClosestPATOIRequest)
	{
		QueryClosestPATOIResponse queryClosestPATOIResponse = new QueryClosestPATOIResponse();
		//return getOpenDAIDAO().queryClosestPOI(queryClosestPOIRequest);
		
		
		KurumSabit kurumSabit = new KurumSabit();
		kurumSabit.setId(sabitKurumId);

		Double lat = queryClosestPATOIRequest.getMyCoordinate().getLatitude();
		Double lng = queryClosestPATOIRequest.getMyCoordinate().getLongtitude();
		
		
		List<PatoiType> _list= new ArrayList<PatoiType>();
		PoiReturnType poiReturnType = getPoiServis().getClosestPoiFromReferanceCoordinate(kurumSabit,queryClosestPATOIRequest.getPoiType(),lat,lng);
		
		if (poiReturnType.getReturnPoiList()!=null)
		{
			for(int I = 0 ;I<poiReturnType.getReturnPoiList().size();I++)
			{
				
				PoiType poiType = new PoiType();
				poiType.setPoiType(queryClosestPATOIRequest.getPoiType());
				poiType.setName(poiReturnType.getReturnPoiList().get(I).getPoiName());
				poiType.setDescription(""+poiReturnType.getReturnPoiList().get(I).getPoiID());
				poiType.setX(poiReturnType.getReturnPoiList().get(I).getCoordinate()[0]);
				poiType.setY(poiReturnType.getReturnPoiList().get(I).getCoordinate()[1]);
				
				PatoiType patoiType = new PatoiType();
				patoiType.setDistance(0.0);
				patoiType.setComment("");
				patoiType.setIsItCloset("");
				patoiType.setCompanyId(sabitKurumId);
				patoiType.setPoi(poiType);
				patoiType.setTimeOfInterest("");
				patoiType.setX(poiReturnType.getReturnPoiList().get(I).getCoordinate()[0]);
				patoiType.setY(poiReturnType.getReturnPoiList().get(I).getCoordinate()[1]);			
				
				
				_list.add(patoiType);
			}
		}
		queryClosestPATOIResponse.setPatois(_list);
		
		return queryClosestPATOIResponse;
	}
	
	public QueryClosestPATOIByAddressResponse queryClosestPATOIByAddress(QueryClosestPATOIByAddressRequest queryClosestPATOIByAddressRequest)
	{
		QueryClosestPATOIByAddressResponse queryClosestPATOIByAddressResponse = new QueryClosestPATOIByAddressResponse();
		//return getOpenDAIDAO().queryClosestPOI(queryClosestPOIRequest);

		KurumSabit kurumSabit = new KurumSabit();
		kurumSabit.setId(sabitKurumId);

		List<PatoiType> _list= new ArrayList<PatoiType>();
		
		/*
		PoiReturnType poiReturnType = getPoiServis().get(kurumSabit,queryClosestPATOIRequest.getPoiType(),lat,lng);
		
		for(int I = 0 ;I<poiReturnType.getReturnPoiList().size();I++)
		{
			
			PoiType poiType = new PoiType();
			poiType.setPoiType(queryClosestPATOIRequest.getPoiType());
			poiType.setName(poiReturnType.getReturnPoiList().get(I).getPoiName());
			poiType.setDescription(""+poiReturnType.getReturnPoiList().get(I).getPoiID());
			poiType.setX(poiReturnType.getReturnPoiList().get(I).getCoordinate()[0]);
			poiType.setY(poiReturnType.getReturnPoiList().get(I).getCoordinate()[1]);
			
			PatoiType patoiType = new PatoiType();
			patoiType.setDistancem(0.0);
			patoiType.setAciklama("");
			patoiType.setIcindemi("");
			patoiType.setKurumId(63000L);
			patoiType.setPoi(poiType);
			patoiType.setTimeOfInterest("");
			patoiType.setX(poiReturnType.getReturnPoiList().get(I).getCoordinate()[0]);
			patoiType.setY(poiReturnType.getReturnPoiList().get(I).getCoordinate()[1]);			
			
			
			_list.add(patoiType);
		}
			*/
		
		
		queryClosestPATOIByAddressResponse.setPatois(_list);
	
		return queryClosestPATOIByAddressResponse;
	}

	public GetMapByCoordinateResponse getMapByCoordinate(GetMapByCoordinateRequest getMapByCoordinateRequest)
	{
		GetMapByCoordinateResponse getMapByCoordinateResponse = new GetMapByCoordinateResponse();

		int[] layerArray = new int[] { 8, 1, 16, 14 };

		layerArray[0] = 1;
		layerArray[1] = 2;
		layerArray[2] = 4;
		layerArray[3] = 8;
		
		//public MapReturnType getMapAsByteArr(double lng, double lat, int imageWidth, int imageHeight, int zoomLevel, int[] layers) {
		ImageType imageType = new ImageType();
		Double lat = getMapByCoordinateRequest.getMapProjection().getCoordinate().getLatitude();
		Double lng = getMapByCoordinateRequest.getMapProjection().getCoordinate().getLongtitude();
		
		/*
		FeatureIDTools featureIDTools = new FeatureIDTools();

		MapReturnType mapReturnType = new MapReturnType();

		IAppFeatureLayer appFeatureLayer = getOrtakSpatialServis().getLayers()
				.getAppLayerByName(OrtakSpatialLayers.PARCEL_LAYER_PROP_NAME);

		Long parselId = Long.parseLong(Id.toString());

		IFeatureID featureID = featureIDTools.createFeatureIDWithFTypeAndID(
				appFeatureLayer.getFeatureLayer().getName(), parselId);

		IFeature feature1 = appFeatureLayer.getFeatureLayer().getFeaturesbyFID(
				featureID);
		
		*/
		
		byte[] imageByte= getMapServis().getMapAsByteArr(lng,lat,getMapByCoordinateRequest.getMapProjection().getWidth(),getMapByCoordinateRequest.getMapProjection().getHeight(),10,layerArray);		
		
		imageType.setImageContents(imageByte);
		
		getMapByCoordinateResponse.setImageType(imageType);
		
		return getMapByCoordinateResponse;
	}

	public QueryClosestPOIResponse queryClosestPOI(QueryClosestPOIRequest queryClosestPOIRequest)
	{
		QueryClosestPOIResponse queryClosestPOIResponse = new QueryClosestPOIResponse();
		//return getOpenDAIDAO().queryClosestPOI(queryClosestPOIRequest);
		
		
		KurumSabit kurumSabit = new KurumSabit();
		kurumSabit.setId(sabitKurumId);

		System.out.println("GELLL"+queryClosestPOIRequest.getCoordinate().getLatitude());
		System.out.println("GELLL1"+queryClosestPOIRequest.getCoordinate().getLongtitude());
		
		Double lat = queryClosestPOIRequest.getCoordinate().getLatitude();
		Double lng = queryClosestPOIRequest.getCoordinate().getLongtitude();
		String poiTip = "";
		if (queryClosestPOIRequest.getPoiType()!=null)
		{
			poiTip = queryClosestPOIRequest.getPoiType();
		}
		
		List<PoiType> _list= new ArrayList<PoiType>();
		PoiReturnType poiReturnType = getPoiServis().getClosestPoiFromReferanceCoordinate(kurumSabit,poiTip,lat,lng);
		
		if (poiReturnType!=null)
		{
			if (poiReturnType.getReturnPoiList()!=null)
			{
				for(int I = 0 ;I<poiReturnType.getReturnPoiList().size();I++)
				{
					PoiType poiType = new PoiType();
					poiType.setPoiType(queryClosestPOIRequest.getPoiType());
					poiType.setName(poiReturnType.getReturnPoiList().get(I).getPoiName());
					poiType.setDescription(""+poiReturnType.getReturnPoiList().get(I).getPoiID());
					poiType.setX(poiReturnType.getReturnPoiList().get(I).getCoordinate()[0]);
					poiType.setY(poiReturnType.getReturnPoiList().get(I).getCoordinate()[1]);
					_list.add(poiType);
					System.out.println(poiReturnType.getReturnPoiList().get(I).getPoiID()+" = "+poiReturnType.getReturnPoiList().get(I).getPoiName()+" = ");
				}
			}
		}
		queryClosestPOIResponse.setPois(_list);
		
		return queryClosestPOIResponse;
	}

	public ListPOIsByTypeResponce listPOIsByType(ListPOIsByTypeRequest listPOIsByTypeRequest) 
	{
		ListPOIsByTypeResponce listPOIsByTypeResponce = new ListPOIsByTypeResponce();
		KurumSabit kurumSabit = new KurumSabit();
		kurumSabit.setId(sabitKurumId);

		List<PoiType> _list= new ArrayList<PoiType>();
		PoiReturnType poiReturnType = getPoiServis().getPoiListFromType(kurumSabit,listPOIsByTypeRequest.getPoiType(),"");
		if (poiReturnType.getReturnPoiList()!=null)
		{			
			for(int I = 0 ;I<poiReturnType.getReturnPoiList().size();I++)
			{
				PoiType poiType = new PoiType();
				poiType.setPoiType(listPOIsByTypeRequest.getPoiType());
				poiType.setName(poiReturnType.getReturnPoiList().get(I).getPoiName());
				poiType.setDescription(""+poiReturnType.getReturnPoiList().get(I).getPoiID());
				poiType.setX(poiReturnType.getReturnPoiList().get(I).getCoordinate()[0]);
				poiType.setY(poiReturnType.getReturnPoiList().get(I).getCoordinate()[1]);
				_list.add(poiType);
				System.out.println(poiReturnType.getReturnPoiList().get(I).getPoiID()+" = "+poiReturnType.getReturnPoiList().get(I).getPoiName()+" = ");
			}
		}		
		listPOIsByTypeResponce.setPoiList(_list.toArray(new PoiType[_list.size()]));
		
		return listPOIsByTypeResponce;
	}

	public ListPOIsByAreaResponce listPOIsByArea(ListPOIsByAreaRequest listPOIsByAreaRequest) 
	{
		ListPOIsByAreaResponce listPOIsByAreaResponce = new ListPOIsByAreaResponce();
		KurumSabit kurumSabit = new KurumSabit();
		kurumSabit.setId(sabitKurumId);

		List<PoiType> _list= new ArrayList<PoiType>();
		PoiReturnType poiReturnType = getPoiServis().getClosestPoiFromReferanceCoordinate(kurumSabit, listPOIsByAreaRequest.getPoiType(), listPOIsByAreaRequest.getLatitude(), listPOIsByAreaRequest.getLongitude());

		for(int I = 0 ;I<poiReturnType.getReturnPoiList().size();I++)
		{
			PoiType poiType = new PoiType();
			poiType.setPoiType(listPOIsByAreaRequest.getPoiType());
			poiType.setName(poiReturnType.getReturnPoiList().get(I).getPoiName());
			poiType.setDescription(""+poiReturnType.getReturnPoiList().get(I).getPoiID());
			poiType.setX(poiReturnType.getReturnPoiList().get(I).getCoordinate()[0]);
			poiType.setY(poiReturnType.getReturnPoiList().get(I).getCoordinate()[1]);
			_list.add(poiType);
			System.out.println(poiReturnType.getReturnPoiList().get(I).getPoiID()+" = "+poiReturnType.getReturnPoiList().get(I).getPoiName()+" = ");
		}
		
		listPOIsByAreaResponce.setPoiList(_list.toArray(new PoiType[_list.size()]));
		
		return listPOIsByAreaResponce;
	}	
	
	
	
	public FindClosestGCResponce findClosestGC(FindClosestGCRequest findClosestGCRequest) 
	{
		return getOpenDAIDAO().findClosestGC(findClosestGCRequest);
	}
	
	public QueryCanTypeResponce queryCanType() 
	{
		return getOpenDAIDAO().queryCanType();
	}
	
	public GetGCHoursForCanResponce getGCHoursForCan( GetGCHoursForCanRequest getGCHoursForCanRequest) 
	{
		return getOpenDAIDAO().getGCHoursForCan(getGCHoursForCanRequest);
	}
	
	public ListCansByTypeResponce getGCListByType(String poiType)
	{
		ListCansByTypeResponce listPOIsByTypeResponce = new ListCansByTypeResponce();
		KurumSabit kurumSabit = new KurumSabit();
		kurumSabit.setId(sabitKurumId);

		List<CanPoiItem> _list= new ArrayList<CanPoiItem>();
		PoiReturnType poiReturnType = getPoiServis().getPoiListFromType(kurumSabit,poiType,"");
		if (poiReturnType.getReturnPoiList()!=null)
		{			
			for(int I = 0 ;I<poiReturnType.getReturnPoiList().size();I++)
			{
				CanPoiItem canPoiItem = new CanPoiItem();
				//canPoiItem.setPoiType(poiType);
				//canPoiItem.setName(poiReturnType.getReturnPoiList().get(I).getPoiName());
				//canPoiItem.setDescription(""+poiReturnType.getReturnPoiList().get(I).getPoiID());
				canPoiItem.setX(poiReturnType.getReturnPoiList().get(I).getCoordinate()[0]);
				canPoiItem.setY(poiReturnType.getReturnPoiList().get(I).getCoordinate()[1]);
				List<GcCollectScheduleItem> _listSchedule= new ArrayList<GcCollectScheduleItem>();
				
				GcCollectScheduleItem gcCollectScheduleItem = new GcCollectScheduleItem();				
				HourType hourTypeStart = new HourType();
				hourTypeStart.setValue("06:00:00");
				HourType hourTypeFinish = new HourType();
				hourTypeFinish.setValue("06:30:00");
				gcCollectScheduleItem.setStartTime(hourTypeStart);
				gcCollectScheduleItem.setFinishTime(hourTypeFinish);
				_listSchedule.add(gcCollectScheduleItem);
				
				
				GcCollectScheduleItem gcCollectScheduleItem1 = new GcCollectScheduleItem();				
				HourType hourTypeStart4 = new HourType();
				hourTypeStart4.setValue("10:00:00");
				HourType hourTypeFinish4 = new HourType();
				hourTypeFinish4.setValue("10:30:00");
				gcCollectScheduleItem1.setStartTime(hourTypeStart4);
				gcCollectScheduleItem1.setFinishTime(hourTypeFinish4);
				_listSchedule.add(gcCollectScheduleItem1);
				
				GcCollectScheduleItem gcCollectScheduleItem2 = new GcCollectScheduleItem();				
				HourType hourTypeStart1 = new HourType();
				hourTypeStart1.setValue("16:00:00");
				HourType hourTypeFinish1 = new HourType();
				hourTypeFinish1.setValue("16:30:00");
				gcCollectScheduleItem2.setStartTime(hourTypeStart1);
				gcCollectScheduleItem2.setFinishTime(hourTypeFinish1);
				_listSchedule.add(gcCollectScheduleItem2);
				
				GcCollectScheduleItem gcCollectScheduleItem3 = new GcCollectScheduleItem();				
				HourType hourTypeStart2 = new HourType();
				hourTypeStart2.setValue("20:00:00");
				HourType hourTypeFinish2 = new HourType();
				hourTypeFinish2.setValue("20:30:00");
				gcCollectScheduleItem3.setStartTime(hourTypeStart2);
				gcCollectScheduleItem3.setFinishTime(hourTypeFinish2);
				
				_listSchedule.add(gcCollectScheduleItem3);
				//gcCollectScheduleItem.se
				canPoiItem.setGcCollectScheduleItem(_listSchedule.toArray(new GcCollectScheduleItem[_listSchedule.size()]));
				
				_list.add(canPoiItem);
				System.out.println(poiReturnType.getReturnPoiList().get(I).getPoiID()+" = "+poiReturnType.getReturnPoiList().get(I).getPoiName()+" = ");
			}
		}		
		listPOIsByTypeResponce.setCanList(_list.toArray(new CanPoiItem[_list.size()]));
		
		return listPOIsByTypeResponce;
	}
	
	
	
	public ListTheStreetsResponce listTheStreets(ListTheStreetsRequest listTheStreetsRequest) 
	{
		return getOpenDAIDAO().listTheStreets(listTheStreetsRequest);	
	}
	
	public ListTheCansByStreetResponce listTheCansByStreet(ListTheCansByStreetRequest listTheCansByStreetRequest) 
	{
		return getOpenDAIDAO().listTheCansByStreet(listTheCansByStreetRequest);
	}	
	
	
	public GetInformationForCanResponce getInformationForCan(GetInformationForCanRequest getInformationForCanRequest) 
	{
		System.out.println("testt");
		return getOpenDAIDAO().getInformationForCan(getInformationForCanRequest);
	}
	
	public ListPOITypesResponce listPOITypes(ListPOITypesRequest listPOITypesRequest) 
	{
		ListPOITypesResponce listPOITypesResponce = new ListPOITypesResponce();

		String[] _list= getPoiServis().getPoiTypes();
		
		PoiTypes poiTypes = new PoiTypes();
		List<String> _lstStr = new ArrayList<String>();
		for(int I =0;I<_list.length;I++)
		{
			_lstStr.add(_list[I]);
		}
		
		poiTypes.setPoiTypes(_lstStr);
		listPOITypesResponce.setPoiTypes(poiTypes);
		
		
		return listPOITypesResponce;
	}
	


	public ListTheAddressCityResponce listTheAddressCity() 
	{
		return getOpenDAIDAO().listTheAddressCity();
	}	
	
	
	public ListTheAddressTownsResponce listTheAddressTowns(ListTheAddressTownsRequest listTheAddressTownsRequest) 
	{
		return getOpenDAIDAO().listTheAddressTowns(listTheAddressTownsRequest);
	}
	
	public ListTheAddressDistrictResponce listTheAddressDistrict(ListTheAddressDistrictRequest listTheAddressDistrictRequest) 
	{
		return getOpenDAIDAO().listTheAddressDistrict(listTheAddressDistrictRequest);
	}	
	


/*
	public SaveComplainResponse saveComplain(SaveComplainRequest saveComplainRequest)
	{
		return null;
	}

	public queryComplainResponse queryComplain(QueryComplainRequest queryComplainRequest)
	{
		return null;
	}
*/	
	
	
	
	
	
	
	
	
	
	public IMapServis getMapServis() {
		return mapServis;
	}

	public void setMapServis(IMapServis mapServis) {
		this.mapServis = mapServis;
	}

	public IPOIServis getPoiServis() {
		return poiServis;
	}

	public void setPoiServis(IPOIServis poiServis) {
		this.poiServis = poiServis;
	}
	
	
	public IOrtakSpatialServis getOrtakSpatialServis() {
		return ortakSpatialServis;
	}

	public void setOrtakSpatialServis(IOrtakSpatialServis ortakSpatialServis) {
		this.ortakSpatialServis = ortakSpatialServis;
	}


	public OrtakServis getOrtakServis() {
		return ortakServis;
	}

	public void setOrtakServis(OrtakServis ortakServis) {
		this.ortakServis = ortakServis;
	}


	public SoCBSOpenDaiESBDaosImpl getOpenDAIDAO() {
		return openDAIDAO;
	}

	public void setOpenDAIDAO(SoCBSOpenDaiESBDaosImpl openDAIDAO) {
		this.openDAIDAO = openDAIDAO;
	}


}