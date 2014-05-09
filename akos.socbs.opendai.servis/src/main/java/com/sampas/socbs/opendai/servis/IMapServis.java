package com.sampas.socbs.opendai.servis;

import java.awt.image.BufferedImage;
import java.util.List;

import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.IWMSLayer;


public interface IMapServis {

	/*
	public QueryClosestPATOIResponse queryClosestPATOI(QueryClosestPATOIRequest queryClosestPATOIRequest);
	
	public QueryClosestPATOIByAddressResponse queryClosestPATOIByAddress(QueryClosestPATOIByAddressRequest queryClosestPATOIByAddressRequest);

	public GetMapByCoordinateResponse getMapByCoordinate(GetMapByCoordinateRequest getMapByCoordinateRequest);

	public QueryClosestPOIResponse queryClosestPOI(QueryClosestPOIRequest queryClosestPOIRequest);

	public FindClosestGCResponce findClosestGC(FindClosestGCRequest findClosestGCRequest); 
	
	public QueryCanTypeResponce queryCanType();
	
	public GetGCHoursForCanResponce getGCHoursForCan(); 
	
	public ListTheStreetsResponce listTheStreets(ListTheStreetsRequest listTheStreetsRequest); 
	
	public ListTheCansByStreetResponce listTheCansByStreet(ListTheCansByStreetRequest listTheCansByStreetRequest); 
	
	public GetInformationForCanResponce getInformationForCan(GetInformationForCanRequest getInformationForCanRequest); 
	
	public ListPOITypesResponce listPOITypes(ListPOITypesRequest listPOITypesRequest); 
	
	public ListPOIsByTypeResponce listPOIsByType(ListPOIsByTypeRequest listPOIsByTypeRequest); 

	public ListPOIsByAreaResponce listPOIsByArea(ListPOIsByAreaRequest listPOIsByAreaRequest); 

	public ListTheAddressCityResponce listTheAddressCity(); 
	
	public ListTheAddressTownsResponce listTheAddressTowns(ListTheAddressTownsRequest listTheAddressTownsRequest); 

	public ListTheAddressDistrictResponce listTheAddressDistrict(ListTheAddressDistrictRequest listTheAddressDistrictRequest); 
	
	public ListTheAddressStreetResponce listTheAddressStreet(ListTheAddressStreetRequest listTheAddressStreetRequest); 

	public QueryBusinessOfficeResponse queryBusinessOffice(QueryBusinessOfficeRequest queryBusinessOfficeRequest);
	
	*/
	
	public byte[] getMapAsByteArr(double lng, double lat, int imageWidth, int imageHeight, int zoomLevel);
	
	public byte[] getMapAsByteArr(IFeature feature, int imageWidth, int imageHeight, int ratio, boolean drawFeature, String colorHex);
	
	public BufferedImage getMapAsBfrImg(double lng, double lat , int imageWidth, int imageHeight, int zoomLevel);
	
	public int getMaxZoomLevel();
	
	public List<IWMSLayer> getMapServiceWmsLayerList();
	
	public IFeatureLayer getAddressFeatureLayer();
		
	public IFeatureLayer getImportantPlaceFeatureLayer();
	
	public IFeatureLayer getBuildingFeatureLayer();
	
	public byte[] getMapAsByteArr(double lng, double lat , int imageWidth, int imageHeight, int zoomLevel, int[] layers);
	
	public byte[] getMapAsByteArr(IFeature feature, int imageWidth, int imageHeight, int ratio, boolean drawFeature, String colorHex, int[] layers);

}