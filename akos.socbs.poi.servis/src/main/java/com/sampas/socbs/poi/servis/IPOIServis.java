package com.sampas.socbs.poi.servis;

import java.util.List;

import com.sampas.akos.common.exception.AkosException;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.gis.ortak.model.Poi;
import com.sampas.gis.ortak.servis.IGisOrtakServis;
import com.sampas.ortak.spatial.servis.IOrtakSpatialServis;
import com.sampas.socbs.base.model.MapReturnType;
import com.sampas.socbs.poi.model.JointFeature;
import com.sampas.socbs.poi.servis.returntypes.CoordinatesReturnType;
import com.sampas.socbs.poi.servis.returntypes.PoiReturnType;


public interface IPOIServis {

	public IGisOrtakServis getGisOrtakServis();
	
	public int getMaxZoomLevel();
	
	public MapReturnType getMapAsByteArr(double lng, double lat, int imageWidth, int imageHeight, int zoomLevel);
	
	public CoordinatesReturnType getCoordinateFromAddress(String ilAdi, String ilceAdi, String mahalleAdi, String caddeSokakAdi, String kapiNo);
	
	public MapReturnType getMapFromAddress(String ilAdi, String ilceAdi, String mahalleAdi, String caddeSokakAdi, String kapiNo, int imgeGenislik, int imgeYukseklik, int yakinlasmaSeviyesi);
	
	public String[] getPoiTypes();
	
	public PoiReturnType getPoiListFromType(String ilAdi, String ilceAdi, String poiType, String poiNameClue);
	
	public PoiReturnType getClosestPoiFromReferanceCoordinate(String ilAdi, String ilceAdi, String poiType, double lat, double lng);
	
//	public String getAddressStrFromPoi(Poi poi);
	
	public CoordinatesReturnType getCoordinateFromAddress(KurumSabit kurumSabit, String mahalleAdi, String caddeSokakAdi, String kapiNo);
	
	public MapReturnType getMapFromAddress(KurumSabit kurumSabit, String mahalleAdi, String caddeSokakAdi, String kapiNo, int imgeGenislik, int imgeYukseklik, int yakinlasmaSeviyesi);
	
	public PoiReturnType getPoiListFromType(KurumSabit kurumSabit, String poiType, String poiNameClue);
	
	public PoiReturnType getClosestPoiFromReferanceCoordinate(KurumSabit kurumSabit, String poiType, double lat, double lng);
	
	public String getAddressStrFromPoiAndCompConst(KurumSabit kurumSabit, Poi poi);

	public MapReturnType getMapFromBuildingId(Long buildingId, int imgWidth, int imgHeight, int zoomLevel);
	
	public MapReturnType getMapFromBuildingIdAndLayers(Long buildingId, int imgWidth, int imgHeight, int zoomLevel,int[] layers);
	
	public List<JointFeature> getJointFeatures(List<Long> featureIdList, String type) throws AkosException; 

	public IOrtakSpatialServis getOrtakSpatialServis();
}