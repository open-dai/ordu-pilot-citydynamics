package com.sampas.gis.ortak.metadata.model;

import com.sampas.akos.common.model.BaseObject;

@SuppressWarnings("serial")
public class KurumUygMD_Adaptor extends BaseObject {

	private KurumUygMetadata kurumUygMetadata;

	public KurumUygMD_Adaptor(KurumUygMD_DB_Map kurumUygMD_DB_Map) {

		if (kurumUygMD_DB_Map == null) {

			kurumUygMetadata = null;
		} else {

			kurumUygMetadata = new KurumUygMetadata();
			
			kurumUygMetadata.setId( kurumUygMD_DB_Map.getId() );
			
			kurumUygMetadata.setKurumId(kurumUygMD_DB_Map.getKurumId());
			
			kurumUygMetadata.setApplicationName(kurumUygMD_DB_Map.getApplicationName());
			
			kurumUygMetadata.setCoordinateSystem(kurumUygMD_DB_Map.getCoordinateSystem());
			
			kurumUygMetadata.setGeolsaConnectionName(kurumUygMD_DB_Map.getGeolsaConnectionName());
			
			kurumUygMetadata.setRelatedExtent(kurumUygMD_DB_Map.getRelatedExtent());
			
			kurumUygMetadata.setNumZoomLevels(kurumUygMD_DB_Map.getNumZoomLevels());
			
			kurumUygMetadata.setMinZoomLevel(kurumUygMD_DB_Map.getMinZoomLevel());
			
			kurumUygMetadata.setIsMapExtentFixed(kurumUygMD_DB_Map.getIsMapExtentFixed());
			
			kurumUygMetadata.setTileURLs(kurumUygMD_DB_Map.getTileURLs());
			
			kurumUygMetadata.setTileLayerNames( kurumUygMD_DB_Map.getTileLayerNames() );
			
			kurumUygMetadata.setTileLayerNameSpaces(kurumUygMD_DB_Map.getTileLayerNameSpaces());
			
			kurumUygMetadata.setDmServiceUse( kurumUygMD_DB_Map.getDmServiceUse() );
			
			kurumUygMetadata.setUseGoogleSatelliteMapAsBaseMap(kurumUygMD_DB_Map.getUseGoogleSatelliteMapAsBaseMap());
			
			kurumUygMetadata.setUseGoogleHybridMapAsBaseMap( kurumUygMD_DB_Map.getUseGoogleHybridMapAsBaseMap() );
			
			kurumUygMetadata.setUseGooglePhysicalMapAsBaseMap(kurumUygMD_DB_Map.getUseGooglePhysicalMapAsBaseMap());
			
			kurumUygMetadata.setUseBingSatelliteMapAsBaseMap(kurumUygMD_DB_Map.getUseBingSatelliteMapAsBaseMap());
			
			kurumUygMetadata.setUseBingHybridMapAsBaseMap(kurumUygMD_DB_Map.getUseBingHybridMapAsBaseMap());
			
			kurumUygMetadata.setUseBingAerialMapAsBaseMap(kurumUygMD_DB_Map.getUseBingAerialMapAsBaseMap());
			
			kurumUygMetadata.setUseOSMAsBaseMap(kurumUygMD_DB_Map.getUseOSMAsBaseMap());
			
			kurumUygMetadata.setUseEmptyLayerAsBaseMap(kurumUygMD_DB_Map.getUseEmptyLayerAsBaseMap());
			
			kurumUygMetadata.setIsEmptyLayerPriorityFirst(kurumUygMD_DB_Map.getIsEmptyLayerPriorityFirst());
			
			kurumUygMetadata.setUseGoogleSatelliteMapAsOverviewMap(kurumUygMD_DB_Map.getUseGoogleSatelliteMapAsOverviewMap());
			
			kurumUygMetadata.setUseBingSatelliteMapAsOverviewMap(kurumUygMD_DB_Map.getUseBingSatelliteMapAsOverviewMap());
			
			kurumUygMetadata.setUseOSMAsOverviewMap(kurumUygMD_DB_Map.getUseOSMAsOverviewMap());
			
			kurumUygMetadata.setOverviewMapFixedLevelNumber(kurumUygMD_DB_Map.getOverviewMapFixedLevelNumber());
			
			kurumUygMetadata.setOverviewMapLayerId(kurumUygMD_DB_Map.getOverviewMapLayerId());
			
			kurumUygMetadata.setTableMaxPages(kurumUygMD_DB_Map.getTableMaxPages());
			
			kurumUygMetadata.setMultiZoningStatusSupport(kurumUygMD_DB_Map.getMultiZoningStatusSupport());
			
			kurumUygMetadata.setDilDestegiGoruntuleme(kurumUygMD_DB_Map.getDilDestegiGoruntuleme());
			
			kurumUygMetadata.setPhotoFilterTags(kurumUygMD_DB_Map.getPhotoFilterTags());

			kurumUygMetadata.setBolgeEh(kurumUygMD_DB_Map.getBolgeEh());
			
			kurumUygMetadata.setShowStreetView(kurumUygMD_DB_Map.getShowStreetView());
			
			kurumUygMetadata.setStreetViewApiKey(kurumUygMD_DB_Map.getStreetViewApiKey());
			
			kurumUygMetadata.setStreetViewSecretKey(kurumUygMD_DB_Map.getStreetViewSecretKey());
			
			kurumUygMetadata.setStreetViewServiceURL(kurumUygMD_DB_Map.getStreetViewServiceURL());
			
			kurumUygMetadata.setStreetViewBaseDataURL(kurumUygMD_DB_Map.getStreetViewBaseDataURL());
			
			kurumUygMetadata.setStreetViewCoverageURL(kurumUygMD_DB_Map.getStreetViewCoverageURL());
			
			kurumUygMetadata.setBinaYuksekligi(kurumUygMD_DB_Map.getBinaYuksekligi());
			
			kurumUygMetadata.setKatAdedi(kurumUygMD_DB_Map.getKatAdedi());
			
			kurumUygMetadata.setBinaDerinligi(kurumUygMD_DB_Map.getBinaDerinligi());
			
			kurumUygMetadata.setOnBahceMesafesi(kurumUygMD_DB_Map.getOnBahceMesafesi());
			
			kurumUygMetadata.setYanBahceMesafesi(kurumUygMD_DB_Map.getYanBahceMesafesi());
			
			kurumUygMetadata.setArkaBahceMesafesi(kurumUygMD_DB_Map.getArkaBahceMesafesi());
			
			kurumUygMetadata.setKaks(kurumUygMD_DB_Map.getKaks());
			
			kurumUygMetadata.setTaks(kurumUygMD_DB_Map.getTaks());
			
			kurumUygMetadata.setTileLayerVisibility(kurumUygMD_DB_Map.getTileLayerVisibility());
			
			kurumUygMetadata.setShowPanaroma(kurumUygMD_DB_Map.getShowPanaroma());
			
			kurumUygMetadata.setPanaromaLidarLabel(kurumUygMD_DB_Map.getPanaromaLidarLabel());
			
			kurumUygMetadata.setPanaromaMaxDestince(kurumUygMD_DB_Map.getPanaromaMaxDestince());
			
			kurumUygMetadata.setPanaromaOlcmeToolbar(kurumUygMD_DB_Map.getPanaromaOlcmeToolbar());
			
			kurumUygMetadata.setPanaromaServiceUrl(kurumUygMD_DB_Map.getPanaromaServiceUrl());
			
			kurumUygMetadata.setPanaromaShowLidar(kurumUygMD_DB_Map.getPanaromaShowLidar());
			
			kurumUygMetadata.setPanaromaShowTimeLabel(kurumUygMD_DB_Map.getPanaromaShowTimeLabel());
			
			kurumUygMetadata.setPanaromaUsername(kurumUygMD_DB_Map.getPanaromaUsername());
			
			kurumUygMetadata.setPanaromaUserpass(kurumUygMD_DB_Map.getPanaromaUserpass());
			
			kurumUygMetadata.setTileLayerIsBaselayer(kurumUygMD_DB_Map.getTileLayerIsBaselayer());
			
			kurumUygMetadata.setTileLayerOpacity(kurumUygMD_DB_Map.getTileLayerOpacity());
			
			kurumUygMetadata.setTileLayerScale(kurumUygMD_DB_Map.getTileLayerScale());
		}
	}

	public KurumUygMetadata getKurumUygMetadata() {
		return kurumUygMetadata;
	}

}
