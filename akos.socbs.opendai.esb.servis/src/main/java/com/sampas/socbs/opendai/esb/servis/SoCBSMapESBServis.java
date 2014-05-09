package com.sampas.socbs.opendai.esb.servis;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import GeoInfoMngmnt.CityDynamics.V1.GetGCHoursForCanRequest;
import GeoInfoMngmnt.CityDynamics.V1.GetGCHoursForCanResponce;
import GeoInfoMngmnt.CityDynamics.V1.GetInformationForCanRequest;
import GeoInfoMngmnt.CityDynamics.V1.GetInformationForCanResponce;
import GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateRequest;
import GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateResponse;
import GeoInfoMngmnt.CityDynamics.V1.ListCansByTypeResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListPOITypesRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListPOITypesResponce;
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
import GeoInfoMngmnt.CityDynamics.V1.QueryBusinessOfficeRequest;
import GeoInfoMngmnt.CityDynamics.V1.QueryBusinessOfficeResponse;
import GeoInfoMngmnt.CityDynamics.V1.QueryCanTypeResponce;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPATOIRequest;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPATOIResponse;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPOIRequest;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPOIResponse;

@WebService
public interface SoCBSMapESBServis {

	/*

	
	@WebMethod(operationName="QueryBusinessOffice")
	public QueryClosestPATOIByAddressResponse queryClosestPATOIByAddress(
			@WebParam(name = "queryClosestPATOIByAddressRequest") QueryClosestPATOIByAddressRequest queryClosestPATOIByAddressRequest);
	

	
	@WebMethod(operationName="QueryBusinessOffice")
	public FindClosestGCResponce findClosestGC(
			@WebParam(name = "findClosestGCRequest") FindClosestGCRequest findClosestGCRequest); 
	
*/
	@WebMethod(operationName="getGCListByType")
	public ListCansByTypeResponce getGCListByType(
			@WebParam(name = "gcType") String poiType);
	
	
	
	@WebMethod(operationName="QueryClosestPATOI")
	public QueryClosestPATOIResponse queryClosestPATOI(
			@WebParam(name = "queryClosestPATOIRequest") QueryClosestPATOIRequest queryClosestPATOIRequest);
	

	@WebMethod(operationName="QueryClosestPOI")
	public QueryClosestPOIResponse queryClosestPOI(
			@WebParam(name = "queryClosestPOIRequest") QueryClosestPOIRequest queryClosestPOIRequest);
	
	
	@WebMethod(operationName="GetMapByCoordinate")
	public GetMapByCoordinateResponse getMapByCoordinate(
			@WebParam(name = "getMapByCoordinateRequest") GetMapByCoordinateRequest getMapByCoordinateRequest);
	
	
	@WebMethod(operationName="QueryBusinessOffice")
	public QueryBusinessOfficeResponse queryBusinessOffice(
			@WebParam(name = "queryBusinessOfficeRequest") QueryBusinessOfficeRequest queryBusinessOfficeRequest);

	@WebMethod(operationName="listPOITypes")
	public ListPOITypesResponce listPOITypes(
			@WebParam(name = "listPOITypesRequest") ListPOITypesRequest listPOITypesRequest); 

	
	@WebMethod(operationName="listPOIsByType")
	public ListPOIsByTypeResponce listPOIsByType(
			@WebParam(name = "listPOIsByTypeRequest") ListPOIsByTypeRequest listPOIsByTypeRequest); 	
	
	
	@WebMethod(operationName="getGCHoursForCan")
	public GetGCHoursForCanResponce getGCHoursForCan(
			@WebParam(name = "getGCHoursForCanRequest") GetGCHoursForCanRequest getGCHoursForCanRequest);
	
	
	@WebMethod(operationName="queryCanType")
	public QueryCanTypeResponce queryCanType(); 	

	@WebMethod(operationName="listTheCansByStreet")
	public ListTheCansByStreetResponce listTheCansByStreet(
			@WebParam(name = "listTheCansByStreetRequest") ListTheCansByStreetRequest listTheCansByStreetRequest); 
	
	@WebMethod(operationName="listTheStreets")
	public ListTheStreetsResponce listTheStreets(
			@WebParam(name = "listTheStreetsRequest") ListTheStreetsRequest listTheStreetsRequest); 
		
	@WebMethod(operationName="getInformationForCan")
	public GetInformationForCanResponce getInformationForCan(
			@WebParam(name = "getInformationForCanRequest") GetInformationForCanRequest getInformationForCanRequest);
	
	@WebMethod(operationName="listTheAddressCity")
	public ListTheAddressCityResponce listTheAddressCity(); 
	
	@WebMethod(operationName="listTheAddressTowns")
	public ListTheAddressTownsResponce listTheAddressTowns(
			@WebParam(name = "listTheAddressTownsRequest") ListTheAddressTownsRequest listTheAddressTownsRequest); 

	@WebMethod(operationName="listTheAddressDistrict")
	public ListTheAddressDistrictResponce listTheAddressDistrict(
			@WebParam(name = "listTheAddressDistrictRequest") ListTheAddressDistrictRequest listTheAddressDistrictRequest); 


	@WebMethod(operationName="listTheAddressStreet")
	public ListTheAddressStreetResponce listTheAddressStreet(
			@WebParam(name = "listTheAddressStreetRequest") ListTheAddressStreetRequest listTheAddressStreetRequest); 
	
	
	
	/*




	@WebMethod(operationName="QueryBusinessOffice")
	public ListPOIsByAreaResponce listPOIsByArea(
			@WebParam(name = "listPOIsByAreaRequest") ListPOIsByAreaRequest listPOIsByAreaRequest); 



	


	
	*/
	
	/*
	@WebMethod(operationName="QueryBusinessOffice")
	public List<BinaRisk> readBinaRiskBilgiByBinaId(Long p_kurumKodu,Long p_binano);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public  List<BinaBelge> readBelgelerByBinaNo(Long p_kurumKodu,Long p_binano);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public  List<KadastroParselResp> getAllDataForCach(Long p_kurumKodu);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public Kullanicilar login(Long p_kurumKodu,String p_username,String p_password);
	
	@WebMethod(operationName="QueryBusinessOffice")	
	public List<KadastroParselResp> readAllBinaByCriteria(Long p_kurumKodu, Long p_sokakKodu, Long p_ada, Long p_parsel,Long p_binaNo,String p_kapiNo) ;	
	
	@WebMethod(operationName="QueryBusinessOffice")
	public List<BinaResp> searchBinaFromKoord(Long p_kurumKodu, double lat,double lon,double farMeter);	
	
	@WebMethod(operationName="QueryBusinessOffice")
	public String[] readBinaResim(Long p_kurumKodu,Long p_binaKodu);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public List<KadastroParselResp> readAllKadastroParselByAdres(Long p_kurumKodu,Long p_sokakKodu,
			String p_kapiNo,String p_altKapiNo) ;	
	
	@WebMethod(operationName="QueryBusinessOffice")
	public List<KadastroParselResp> readAllKadastroParsel(Long p_kurumKodu,Long p_mahalleKodu,	String p_pafta,	Long p_ada,	Long p_parsel) ;
	
	@WebMethod(operationName="QueryBusinessOffice")	
	public List<CaddeSokakResp> readAllCaddeSokakByMahalle(Long p_kurumKodu,Long p_mahalleKodu) ;
	
	@WebMethod(operationName="QueryBusinessOffice")
	public List<MahalleResp> readAllMahalle(Long p_kurumKodu) ;
	
	@WebMethod(operationName="QueryBusinessOffice")
	public ImarDurumuWithMap readImarStatusByParcelKurum(String p_kurumKodu, String Id,
			String sizeW, String sizeH, String ratio) ;
*/
	/*
	@WebMethod(operationName="QueryBusinessOffice")
	public int getMaxZoomLevel();
	
	@WebMethod(operationName="QueryBusinessOffice")
	public String[] getPoiTypes();
	
	@WebMethod(operationName="QueryBusinessOffice")
	public byte[] getMapAsByteArr(double lng, double lat , int imgeGenislik, int imgeYukseklik, int yakinlasmaSeviyesi);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public MapReturnType getMapAsByteArrWithRegularReturnType(double lng, double lat, int imgeGenislik, int imgeYukseklik, int yakinlasmaSeviyesi);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public CoordinatesReturnType getCoordinateFromAddress(String ilAdi, String ilceAdi, String mahalleAdi, String caddeSokakAdi, String kapiNo);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public MapReturnType getMapFromAddress(String ilAdi, String ilceAdi, String mahalleAdi, String caddeSokakAdi, String kapiNo, int imgeGenislik, int imgeYukseklik, int yakinlasmaSeviyesi);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public PoiReturnType getPoiListFromType(String ilAdi, String ilceAdi, String poiType, String poiNameClue);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public PoiReturnType getClosestPoiFromReferanceCoordinate(String ilAdi, String ilceAdi, String poiType, double lat, double lng);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public CoordinatesReturnType getCoordinateFromAddressAndCompConst(KurumSabit kurumSabit, String mahalleAdi, String caddeSokakAdi, String kapiNo);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public MapReturnType getMapFromAddressAndCompConst(KurumSabit kurumSabit, String mahalleAdi, String caddeSokakAdi, String kapiNo, int imgeGenislik, int imgeYukseklik, int yakinlasmaSeviyesi);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public PoiReturnType getPoiListFromTypeAndCompConst(KurumSabit kurumSabit, String poiType, String poiNameClue);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public PoiReturnType getClosestPoiFromReferanceCoordinateAndCompConst(KurumSabit kurumSabit, String poiType, double lat, double lng);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public String getAddressStrFromPoiAndCompConst(KurumSabit kurumSabit, Poi poi);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public SmpAddress getSmpAddressFromCoordinate(double lon, double lat, Long kurumSabitID);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public MapReturnType getMapFromBuildingId(Long buildingId, int imgWidth, int imgHeight, int ratio);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public MapReturnType getMapFromBuildingIdAndLayers(Long buildingId, int imgWidth, int imgHeight, int zoomLevel, int[] layers);
	
	@WebMethod(operationName="QueryBusinessOffice")
	public List<JointFeature> getJointFeatures(List<Long> featureIdList, String type) throws AkosException;
*/		
}