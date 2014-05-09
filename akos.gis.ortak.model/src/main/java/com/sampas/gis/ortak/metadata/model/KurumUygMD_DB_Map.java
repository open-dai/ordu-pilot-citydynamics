package com.sampas.gis.ortak.metadata.model;

import com.sampas.akos.common.model.BaseObject;

@SuppressWarnings("serial")
public class KurumUygMD_DB_Map extends BaseObject implements Cloneable {

	private Long id;

	private Long kurumId;

	private String kurumAdi;

	private String applicationName;

	private Long coordinateSystem;

	private String geolsaConnectionName;

	private String relatedExtent;

	private Long numZoomLevels;

	private Long minZoomLevel;

	private String isMapExtentFixed;

	private String tileURLs;

	private String tileLayerNames;

	private String tileLayerNameSpaces;

	private String dmServiceUse;

	private String useGoogleSatelliteMapAsBaseMap;

	private String useGoogleHybridMapAsBaseMap;

	private String useGooglePhysicalMapAsBaseMap;

	private String useBingSatelliteMapAsBaseMap;

	private String useBingHybridMapAsBaseMap;

	private String useBingAerialMapAsBaseMap;

	private String useOSMAsBaseMap;

	private String useEmptyLayerAsBaseMap;
	
	private String isEmptyLayerPriorityFirst;

	private String useGoogleSatelliteMapAsOverviewMap;

	private String useBingSatelliteMapAsOverviewMap;

	private String useOSMAsOverviewMap;

	private Long overviewMapFixedLevelNumber;

	private Long overviewMapLayerId;

	private Long tableMaxPages;

	private String multiZoningStatusSupport;

	private String dilDestegiGoruntuleme;

	private String photoFilterTags;

	private String bolgeEh;
	
	private String showStreetView;
	
	private String streetViewApiKey;
	
	private String streetViewSecretKey;
	
	private String streetViewServiceURL;
	
	private String streetViewBaseDataURL;
	
	private String streetViewCoverageURL;
	
	private String binaYuksekligi;

	private String katAdedi;

	private String binaDerinligi;

	private String onBahceMesafesi;

	private String yanBahceMesafesi;

	private String arkaBahceMesafesi;

	private String kaks;

	private String taks;
	
	private String tileLayerVisibility;
	
	private String showPanaroma;
	
	private String panaromaLidarLabel;
	
	private String panaromaMaxDestince;
	
	private String panaromaOlcmeToolbar;
	
	private String panaromaServiceUrl;
	
	private String panaromaShowLidar;
	
	private String panaromaShowTimeLabel;
	
	private String panaromaUsername;
	
	private String panaromaUserpass;
	
	private String tileLayerOpacity;
	
	private String tileLayerScale;
	
	private String tileLayerIsBaselayer;

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException ex) {
			return null;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getKurumId() {
		return kurumId;
	}

	public void setKurumId(Long kurumId) {
		this.kurumId = kurumId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public Long getCoordinateSystem() {
		return coordinateSystem;
	}

	public void setCoordinateSystem(Long coordinateSystem) {
		this.coordinateSystem = coordinateSystem;
	}

	public String getGeolsaConnectionName() {
		return geolsaConnectionName;
	}

	public void setGeolsaConnectionName(String geolsaConnectionName) {
		this.geolsaConnectionName = geolsaConnectionName;
	}

	public String getRelatedExtent() {
		return relatedExtent;
	}

	public void setRelatedExtent(String relatedExtent) {
		this.relatedExtent = relatedExtent;
	}

	public Long getNumZoomLevels() {
		return numZoomLevels;
	}

	public void setNumZoomLevels(Long numZoomLevels) {
		this.numZoomLevels = numZoomLevels;
	}

	public Long getMinZoomLevel() {
		return minZoomLevel;
	}

	public void setMinZoomLevel(Long minZoomLevel) {
		this.minZoomLevel = minZoomLevel;
	}

	public String getIsMapExtentFixed() {
		return isMapExtentFixed;
	}

	public void setIsMapExtentFixed(String isMapExtentFixed) {
		this.isMapExtentFixed = isMapExtentFixed;
	}

	public String getTileURLs() {
		return tileURLs;
	}

	public void setTileURLs(String tileURLs) {
		this.tileURLs = tileURLs;
	}

	public String getTileLayerNames() {
		return tileLayerNames;
	}

	public void setTileLayerNames(String tileLayerNames) {
		this.tileLayerNames = tileLayerNames;
	}

	public String getTileLayerNameSpaces() {
		return tileLayerNameSpaces;
	}

	public void setTileLayerNameSpaces(String tileLayerNameSpaces) {
		this.tileLayerNameSpaces = tileLayerNameSpaces;
	}

	public String getDmServiceUse() {
		return dmServiceUse;
	}

	public void setDmServiceUse(String dmServiceUse) {
		this.dmServiceUse = dmServiceUse;
	}

	public String getUseGoogleSatelliteMapAsBaseMap() {
		return useGoogleSatelliteMapAsBaseMap;
	}

	public void setUseGoogleSatelliteMapAsBaseMap(
			String useGoogleSatelliteMapAsBaseMap) {
		this.useGoogleSatelliteMapAsBaseMap = useGoogleSatelliteMapAsBaseMap;
	}

	public String getUseGoogleHybridMapAsBaseMap() {
		return useGoogleHybridMapAsBaseMap;
	}

	public void setUseGoogleHybridMapAsBaseMap(
			String useGoogleHybridMapAsBaseMap) {
		this.useGoogleHybridMapAsBaseMap = useGoogleHybridMapAsBaseMap;
	}

	public String getUseGooglePhysicalMapAsBaseMap() {
		return useGooglePhysicalMapAsBaseMap;
	}

	public void setUseGooglePhysicalMapAsBaseMap(
			String useGooglePhysicalMapAsBaseMap) {
		this.useGooglePhysicalMapAsBaseMap = useGooglePhysicalMapAsBaseMap;
	}

	public String getUseBingSatelliteMapAsBaseMap() {
		return useBingSatelliteMapAsBaseMap;
	}

	public void setUseBingSatelliteMapAsBaseMap(
			String useBingSatelliteMapAsBaseMap) {
		this.useBingSatelliteMapAsBaseMap = useBingSatelliteMapAsBaseMap;
	}

	public String getUseBingHybridMapAsBaseMap() {
		return useBingHybridMapAsBaseMap;
	}

	public void setUseBingHybridMapAsBaseMap(String useBingHybridMapAsBaseMap) {
		this.useBingHybridMapAsBaseMap = useBingHybridMapAsBaseMap;
	}

	public String getUseBingAerialMapAsBaseMap() {
		return useBingAerialMapAsBaseMap;
	}

	public void setUseBingAerialMapAsBaseMap(String useBingAerialMapAsBaseMap) {
		this.useBingAerialMapAsBaseMap = useBingAerialMapAsBaseMap;
	}

	public String getUseOSMAsBaseMap() {
		return useOSMAsBaseMap;
	}

	public void setUseOSMAsBaseMap(String useOSMAsBaseMap) {
		this.useOSMAsBaseMap = useOSMAsBaseMap;
	}

	public String getUseEmptyLayerAsBaseMap() {
		return useEmptyLayerAsBaseMap;
	}

	public void setUseEmptyLayerAsBaseMap(String useEmptyLayerAsBaseMap) {
		this.useEmptyLayerAsBaseMap = useEmptyLayerAsBaseMap;
	}

	public String getUseGoogleSatelliteMapAsOverviewMap() {
		return useGoogleSatelliteMapAsOverviewMap;
	}

	public void setUseGoogleSatelliteMapAsOverviewMap(
			String useGoogleSatelliteMapAsOverviewMap) {
		this.useGoogleSatelliteMapAsOverviewMap = useGoogleSatelliteMapAsOverviewMap;
	}

	public String getUseBingSatelliteMapAsOverviewMap() {
		return useBingSatelliteMapAsOverviewMap;
	}

	public void setUseBingSatelliteMapAsOverviewMap(
			String useBingSatelliteMapAsOverviewMap) {
		this.useBingSatelliteMapAsOverviewMap = useBingSatelliteMapAsOverviewMap;
	}

	public String getUseOSMAsOverviewMap() {
		return useOSMAsOverviewMap;
	}

	public void setUseOSMAsOverviewMap(String useOSMAsOverviewMap) {
		this.useOSMAsOverviewMap = useOSMAsOverviewMap;
	}

	public Long getOverviewMapFixedLevelNumber() {
		return overviewMapFixedLevelNumber;
	}

	public void setOverviewMapFixedLevelNumber(Long overviewMapFixedLevelNumber) {
		this.overviewMapFixedLevelNumber = overviewMapFixedLevelNumber;
	}

	public Long getOverviewMapLayerId() {
		return overviewMapLayerId;
	}

	public void setOverviewMapLayerId(Long overviewMapLayerId) {
		this.overviewMapLayerId = overviewMapLayerId;
	}

	public Long getTableMaxPages() {
		return tableMaxPages;
	}

	public void setTableMaxPages(Long tableMaxPages) {
		this.tableMaxPages = tableMaxPages;
	}

	public String getMultiZoningStatusSupport() {
		return multiZoningStatusSupport;
	}

	public void setMultiZoningStatusSupport(String multiZoningStatusSupport) {
		this.multiZoningStatusSupport = multiZoningStatusSupport;
	}

	public String getDilDestegiGoruntuleme() {
		return dilDestegiGoruntuleme;
	}

	public void setDilDestegiGoruntuleme(String dilDestegiGoruntuleme) {
		this.dilDestegiGoruntuleme = dilDestegiGoruntuleme;
	}

	public String getPhotoFilterTags() {
		return photoFilterTags;
	}

	public void setPhotoFilterTags(String photoFilterTags) {
		this.photoFilterTags = photoFilterTags;
	}

	public String getKurumAdi() {
		return kurumAdi;
	}

	public void setKurumAdi(String kurumAdi) {
		this.kurumAdi = kurumAdi;
	}

	public String getBolgeEh() {
		return bolgeEh;
	}

	public void setBolgeEh(String bolgeEh) {
		this.bolgeEh = bolgeEh;
	}

	public String getShowStreetView() {
		return showStreetView;
	}

	public void setShowStreetView(String showStreetView) {
		this.showStreetView = showStreetView;
	}
	
	public String getStreetViewApiKey() {
		return streetViewApiKey;
	}

	public void setStreetViewApiKey(String streetViewApiKey) {
		this.streetViewApiKey = streetViewApiKey;
	}

	public String getStreetViewSecretKey() {
		return streetViewSecretKey;
	}

	public void setStreetViewSecretKey(String streetViewSecretKey) {
		this.streetViewSecretKey = streetViewSecretKey;
	}

	public String getStreetViewServiceURL() {
		return streetViewServiceURL;
	}

	public void setStreetViewServiceURL(String streetViewServiceURL) {
		this.streetViewServiceURL = streetViewServiceURL;
	}

	public String getStreetViewBaseDataURL() {
		return streetViewBaseDataURL;
	}

	public void setStreetViewBaseDataURL(String streetViewBaseDataURL) {
		this.streetViewBaseDataURL = streetViewBaseDataURL;
	}

	public String getStreetViewCoverageURL() {
		return streetViewCoverageURL;
	}

	public void setStreetViewCoverageURL(String streetViewCoverageURL) {
		this.streetViewCoverageURL = streetViewCoverageURL;
	}

	public String getBinaYuksekligi() {
		return binaYuksekligi;
	}

	public void setBinaYuksekligi(String binaYuksekligi) {
		this.binaYuksekligi = binaYuksekligi;
	}

	public String getKatAdedi() {
		return katAdedi;
	}

	public void setKatAdedi(String katAdedi) {
		this.katAdedi = katAdedi;
	}

	public String getBinaDerinligi() {
		return binaDerinligi;
	}

	public void setBinaDerinligi(String binaDerinligi) {
		this.binaDerinligi = binaDerinligi;
	}

	public String getOnBahceMesafesi() {
		return onBahceMesafesi;
	}

	public void setOnBahceMesafesi(String onBahceMesafesi) {
		this.onBahceMesafesi = onBahceMesafesi;
	}

	public String getYanBahceMesafesi() {
		return yanBahceMesafesi;
	}

	public void setYanBahceMesafesi(String yanBahceMesafesi) {
		this.yanBahceMesafesi = yanBahceMesafesi;
	}

	public String getArkaBahceMesafesi() {
		return arkaBahceMesafesi;
	}

	public void setArkaBahceMesafesi(String arkaBahceMesafesi) {
		this.arkaBahceMesafesi = arkaBahceMesafesi;
	}

	public String getKaks() {
		return kaks;
	}

	public void setKaks(String kaks) {
		this.kaks = kaks;
	}

	public String getTaks() {
		return taks;
	}

	public void setTaks(String taks) {
		this.taks = taks;
	}

	public String getIsEmptyLayerPriorityFirst() {
		return isEmptyLayerPriorityFirst;
	}

	public void setIsEmptyLayerPriorityFirst(String isEmptyLayerPriorityFirst) {
		this.isEmptyLayerPriorityFirst = isEmptyLayerPriorityFirst;
	}
	
	public String getTileLayerVisibility()
	{
		return this.tileLayerVisibility;
	}
	
	public void setTileLayerVisibility(String tileLayerVisibility)
	{
		this.tileLayerVisibility=tileLayerVisibility;
	}
	
	public String getShowPanaroma()
	{
		return this.showPanaroma;
	}
	
	public void setShowPanaroma(String _showPanaroma)
	{
		this.showPanaroma=_showPanaroma;
	}
	public String getPanaromaLidarLabel()
	{
		return this.panaromaLidarLabel;
	}
	
	public void setPanaromaLidarLabel(String _panaromaLidarLabel)
	{
		this.panaromaLidarLabel=_panaromaLidarLabel;
	}
	public String getPanaromaMaxDestince()
	{
		return this.panaromaMaxDestince;
	}
	
	public void setPanaromaMaxDestince(String _panaromaMaxDestince)
	{
		this.panaromaMaxDestince=_panaromaMaxDestince;
	}
	public String getPanaromaOlcmeToolbar()
	{
		return this.panaromaOlcmeToolbar;
	}
	
	public void setPanaromaOlcmeToolbar(String _panaromaOlcmeToolbar)
	{
		this.panaromaOlcmeToolbar=_panaromaOlcmeToolbar;
	}
	public String getPanaromaServiceUrl()
	{
		return this.panaromaServiceUrl;
	}
	
	public void setPanaromaServiceUrl(String _panaromaServiceUrl)
	{
		this.panaromaServiceUrl=_panaromaServiceUrl;
	}
	public String getPanaromaShowLidar()
	{
		return this.panaromaShowLidar;
	}
	
	public void setPanaromaShowLidar(String _panaromaShowLidar)
	{
		this.panaromaShowLidar=_panaromaShowLidar;
	}
	public String getPanaromaShowTimeLabel()
	{
		return this.panaromaShowTimeLabel;
	}
	
	public void setPanaromaShowTimeLabel(String _panaromaShowTimeLabel)
	{
		this.panaromaShowTimeLabel=_panaromaShowTimeLabel;
	}
	public String getPanaromaUsername()
	{
		return this.panaromaUsername;
	}
	
	public void setPanaromaUsername(String _panaromaUsername)
	{
		this.panaromaUsername=_panaromaUsername;
	}
	public String getPanaromaUserpass()
	{
		return this.panaromaUserpass;
	}
	
	public void setPanaromaUserpass(String _panaromaUserpass)
	{
		this.panaromaUserpass=_panaromaUserpass;
	}

	public String getTileLayerOpacity() {
		return tileLayerOpacity;
	}

	public void setTileLayerOpacity(String tileLayerOpacity) {
		this.tileLayerOpacity = tileLayerOpacity;
	}

	public String getTileLayerScale() {
		return tileLayerScale;
	}

	public void setTileLayerScale(String tileLayerScale) {
		this.tileLayerScale = tileLayerScale;
	}

	public String getTileLayerIsBaselayer() {
		return tileLayerIsBaselayer;
	}

	public void setTileLayerIsBaselayer(String tileLayerIsBaselayer) {
		this.tileLayerIsBaselayer = tileLayerIsBaselayer;
	}

}
