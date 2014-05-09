package com.sampas.gis.ortak.metadata.model;

import java.lang.reflect.Method;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.util.AkosStringUtil;

@SuppressWarnings("serial")
public class KurumUygMetadata extends BaseObject {

	private Long id;

	private Long kurumId;

	private String applicationName;

	private Long coordinateSystem;

	private String geolsaConnectionName;

	private Double[] relatedExtent;

	private Short numZoomLevels;

	private Short minZoomLevel;

	private Boolean isMapExtentFixed;

	private String[] tileURLs;

	private String[] tileLayerNames;

	private String[] tileLayerNameSpaces;

	private Boolean dmServiceUse;

	private Boolean useGoogleSatelliteMapAsBaseMap;

	private Boolean useGoogleHybridMapAsBaseMap;

	private Boolean useGooglePhysicalMapAsBaseMap;

	private Boolean useBingSatelliteMapAsBaseMap;

	private Boolean useBingHybridMapAsBaseMap;

	private Boolean useBingAerialMapAsBaseMap;

	private Boolean useOSMAsBaseMap;

	private Boolean useEmptyLayerAsBaseMap;
	
	private Boolean isEmptyLayerPriorityFirst;

	private Boolean useGoogleSatelliteMapAsOverviewMap;

	private Boolean useBingSatelliteMapAsOverviewMap;

	private Boolean useOSMAsOverviewMap;

	private Short overviewMapFixedLevelNumber;

	private Short overviewMapLayerId;

	private Short tableMaxPages;

	private Boolean multiZoningStatusSupport;

	private Boolean dilDestegiGoruntuleme;

	private String[] photoFilterTags;
	
	private String bolgeEh;
	
	private Boolean showStreetView;
	
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

	private String[] tileLayerVisibility;
	
	private Boolean showPanaroma;
	
	private String panaromaLidarLabel;
	
	private String panaromaMaxDestince;
	
	private Boolean panaromaOlcmeToolbar;
	
	private String panaromaServiceUrl;
	
	private Boolean panaromaShowLidar;
	
	private Boolean panaromaShowTimeLabel;
	
	private String panaromaUsername;
	
	private String panaromaUserpass;
	
	private String[] tileLayerOpacity;
	
	private String[] tileLayerScale;
	
	private String[] tileLayerIsBaselayer;
	
	public String[] getTileLayerVisibility()
	{
		return this.tileLayerVisibility;
	}
	
	public void setTileLayerVisibility(String tileLayerVisibility)
	{
		if( tileLayerVisibility != null ){
			this.tileLayerVisibility=AkosStringUtil.convertCSVToStringArray(tileLayerVisibility);
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
		if( coordinateSystem != null )
			this.coordinateSystem = coordinateSystem;
		else{
			System.out.println( "ERROR ON READING coordinateSystem FROM METADATA(DATABASE)" );
			this.coordinateSystem = 2320L;
		}
	}

	public String getGeolsaConnectionName() {
		return geolsaConnectionName;
	}

	public void setGeolsaConnectionName(String geolsaConnectionName) {
		if( geolsaConnectionName != null && !geolsaConnectionName.trim().equals("") )
			this.geolsaConnectionName = geolsaConnectionName;
		else{
			System.out.println( "ERROR ON READING geolsaConnectionName FROM METADATA(DATABASE)" );
			this.geolsaConnectionName = "kentrehberiv3.0";
		}
	}

	public Double[] getRelatedExtent() {
		return relatedExtent;
	}

	public void setRelatedExtent(Double[] relatedExtent) {
		this.relatedExtent = relatedExtent;
	}
	
	public void setRelatedExtent(String relatedExtent) {
		try{
			setRelatedExtent( AkosStringUtil.convertCSVToDoubleArray(relatedExtent) );
		}catch( NumberFormatException nfe ){
			nfe.printStackTrace();
		}
		
		if( getRelatedExtent() == null || getRelatedExtent().length == 0 ){
			System.out.println( "ERROR ON READING relatedExtent FROM METADATA(DATABASE)" );
			
			Double[] tempRelatedExtent = new Double[4]; 
			tempRelatedExtent = new Double[4];
			tempRelatedExtent[0] = -180D;
			tempRelatedExtent[1] = -90D;
			tempRelatedExtent[2] = 180D;
			tempRelatedExtent[3] = 90D;
			
			setRelatedExtent(tempRelatedExtent);
		}
	}

	public Short getNumZoomLevels() {
		return numZoomLevels;
	}

	public void setNumZoomLevels(Short numZoomLevels) {
		this.numZoomLevels = numZoomLevels;
	}
	
	public void setNumZoomLevels(Long numZoomLevels) {
		if( numZoomLevels != null ){
			setNumZoomLevels( (short)((long)numZoomLevels) );
		}else{
			System.out.println( "ERROR ON READING numZoomLevels FROM METADATA(DATABASE)" );
			setNumZoomLevels( (short)7 );
		}
	}

	public Short getMinZoomLevel() {
		return minZoomLevel;
	}

	public void setMinZoomLevel(Short minZoomLevel) {
		this.minZoomLevel = minZoomLevel;
	}

	public void setMinZoomLevel(Long minZoomLevel) {
		if( minZoomLevel != null ){
			setMinZoomLevel((short)( (long)minZoomLevel ));
		}else{
			System.out.println( "ERROR ON READING minZoomLevel FROM METADATA(DATABASE)" );
			setMinZoomLevel( (short)14 );
		}
	}
	
	public Boolean getIsMapExtentFixed() {
		return isMapExtentFixed;
	}

	public void setIsMapExtentFixed(Boolean isMapExtentFixed) {
		this.isMapExtentFixed = isMapExtentFixed;
	}
	
	public void setIsMapExtentFixed(String isMapExtentFixed) {
		setBooleanValue("isMapExtentFixed", isMapExtentFixed);
		
		if( getIsMapExtentFixed() == null ){
			System.out.println( "ERROR ON READING isMapExtentFixed FROM METADATA(DATABASE)" );
			setIsMapExtentFixed( Boolean.FALSE );		
		}
	}

	public String[] getTileURLs() {
		return tileURLs;
	}

	public void setTileURLs(String[] tileURLs) {
		this.tileURLs = tileURLs;
	}
	
	public void setTileURLs(String tileURLs) {
		if( tileURLs != null ){
			setTileURLs( AkosStringUtil.convertCSVToStringArray(tileURLs) );
		}
		if( getTileURLs() == null || getTileURLs().length == 0 ){
			System.out.println( "ERROR ON READING tileURLs FROM METADATA(DATABASE)" );
			setTileURLs( new String[]{"http\\://192.168.34.234:8090/geoserver/gwc/service/wms"} );
		}
	}

	public String[] getTileLayerNames() {
		return tileLayerNames;
	}

	public void setTileLayerNames(String[] tileLayerNames) {
		this.tileLayerNames = tileLayerNames;
	}
	
	public void setTileLayerNames(String tileLayerNames) {
		if( tileLayerNames != null ){
			setTileLayerNames( AkosStringUtil.convertCSVToStringArray(tileLayerNames) );
		}
		if( getTileLayerNames() == null || getTileLayerNames().length == 0 ){
			System.out.println( "ERROR ON READING tileLayerNames FROM METADATA(DATABASE)" );
			setTileLayerNames( new String[]{"SMP_MAP_TILELAYER"} );
		}
	}

	public String[] getTileLayerNameSpaces() {
		return tileLayerNameSpaces;
	}

	public void setTileLayerNameSpaces(String[] tileLayerNameSpaces) {
		this.tileLayerNameSpaces = tileLayerNameSpaces;
	}
	
	public void setTileLayerNameSpaces(String tileLayerNameSpaces) {
		if( tileLayerNameSpaces != null ){
			setTileLayerNameSpaces( AkosStringUtil.convertCSVToStringArray(tileLayerNameSpaces) );
		}
		if( getTileLayerNameSpaces() == null || getTileLayerNameSpaces().length == 0 ){
			System.out.println( "ERROR ON READING tileLayerNameSpaces FROM METADATA(DATABASE)" );
			setTileLayerNameSpaces( new String[]{"Harita"} );
		}
	}

	public Boolean getDmServiceUse() {
		return dmServiceUse;
	}

	public void setDmServiceUse(Boolean dmServiceUse) {
		this.dmServiceUse = dmServiceUse;
	}
	
	public void setDmServiceUse(String dmServiceUse) {
		setBooleanValue("dmServiceUse", dmServiceUse);
		
		if( getDmServiceUse() == null ){
			System.out.println( "ERROR ON READING dmServiceUse FROM METADATA(DATABASE)" );
			setDmServiceUse( Boolean.FALSE );		
		}
	}

	public Boolean getUseGoogleSatelliteMapAsBaseMap() {
		return useGoogleSatelliteMapAsBaseMap;
	}

	public void setUseGoogleSatelliteMapAsBaseMap(
			Boolean useGoogleSatelliteMapAsBaseMap) {
		this.useGoogleSatelliteMapAsBaseMap = useGoogleSatelliteMapAsBaseMap;
	}
	
	public void setUseGoogleSatelliteMapAsBaseMap(
			String useGoogleSatelliteMapAsBaseMap) {
		setBooleanValue("useGoogleSatelliteMapAsBaseMap", useGoogleSatelliteMapAsBaseMap);
		
		if( getUseGoogleSatelliteMapAsBaseMap() == null ){
			System.out.println( "ERROR ON READING useGoogleSatelliteMapAsBaseMap FROM METADATA(DATABASE)" );
			setUseGoogleSatelliteMapAsBaseMap( Boolean.FALSE );		
		}
	}

	public Boolean getUseGoogleHybridMapAsBaseMap() {
		return useGoogleHybridMapAsBaseMap;
	}

	public void setUseGoogleHybridMapAsBaseMap(
			Boolean useGoogleHybridMapAsBaseMap) {
		this.useGoogleHybridMapAsBaseMap = useGoogleHybridMapAsBaseMap;
	}
	
	public void setUseGoogleHybridMapAsBaseMap(
			String useGoogleHybridMapAsBaseMap) {
		setBooleanValue("useGoogleHybridMapAsBaseMap", useGoogleHybridMapAsBaseMap);
		
		if( getUseGoogleHybridMapAsBaseMap() == null ){
			System.out.println( "ERROR ON READING useGoogleHybridMapAsBaseMap FROM METADATA(DATABASE)" );
			setUseGoogleHybridMapAsBaseMap( Boolean.FALSE );		
		}
	}

	public Boolean getUseGooglePhysicalMapAsBaseMap() {
		return useGooglePhysicalMapAsBaseMap;
	}

	public void setUseGooglePhysicalMapAsBaseMap(
			Boolean useGooglePhysicalMapAsBaseMap) {
		this.useGooglePhysicalMapAsBaseMap = useGooglePhysicalMapAsBaseMap;
	}
	
	public void setUseGooglePhysicalMapAsBaseMap(
			String useGooglePhysicalMapAsBaseMap) {
		setBooleanValue("useGooglePhysicalMapAsBaseMap", useGooglePhysicalMapAsBaseMap);
		
		if( getUseGooglePhysicalMapAsBaseMap() == null ){
			System.out.println( "ERROR ON READING useGooglePhysicalMapAsBaseMap FROM METADATA(DATABASE)" );
			setUseGooglePhysicalMapAsBaseMap( Boolean.FALSE );		
		}
	}
	
	private void setBooleanValue( String propertyName, Object value ){
		try{
			if( propertyName != null  ){
				Boolean param = null;
				
				if( value instanceof String ){
					String valueStr = (String)value;
					if( valueStr != null && ( valueStr.equalsIgnoreCase("true") || valueStr.equalsIgnoreCase("false") ) ){
						param = new Boolean( valueStr.toLowerCase() );
					}
				}
				
				String mtdName = "set" + (propertyName.charAt(0) + "").toUpperCase() + propertyName.substring(1); 
				Method method = this.getClass().getMethod(mtdName, Boolean.class);
				method.invoke(this, param);
			}
		}catch( Exception e){}
	}

	public Boolean getUseBingSatelliteMapAsBaseMap() {
		return useBingSatelliteMapAsBaseMap;
	}

	public void setUseBingSatelliteMapAsBaseMap(
			Boolean useBingSatelliteMapAsBaseMap) {
		this.useBingSatelliteMapAsBaseMap = useBingSatelliteMapAsBaseMap;
	}
	
	public void setUseBingSatelliteMapAsBaseMap(
			String useBingSatelliteMapAsBaseMap) {
		setBooleanValue("useBingSatelliteMapAsBaseMap", useBingSatelliteMapAsBaseMap);
		
		if( getUseBingSatelliteMapAsBaseMap() == null ){
			System.out.println( "ERROR ON READING useBingSatelliteMapAsBaseMap FROM METADATA(DATABASE)" );
			setUseBingSatelliteMapAsBaseMap( Boolean.FALSE );		
		}
	}

	public Boolean getUseBingHybridMapAsBaseMap() {
		return useBingHybridMapAsBaseMap;
	}

	public void setUseBingHybridMapAsBaseMap(Boolean useBingHybridMapAsBaseMap) {
		this.useBingHybridMapAsBaseMap = useBingHybridMapAsBaseMap;
	}
	
	public void setUseBingHybridMapAsBaseMap(String useBingHybridMapAsBaseMap) {
		setBooleanValue("useBingHybridMapAsBaseMap", useBingHybridMapAsBaseMap);
		
		if( getUseBingHybridMapAsBaseMap() == null ){
			System.out.println( "ERROR ON READING useBingHybridMapAsBaseMap FROM METADATA(DATABASE)" );
			setUseBingHybridMapAsBaseMap( Boolean.FALSE );		
		}
	}

	public Boolean getUseBingAerialMapAsBaseMap() {
		return useBingAerialMapAsBaseMap;
	}

	public void setUseBingAerialMapAsBaseMap(Boolean useBingAerialMapAsBaseMap) {
		this.useBingAerialMapAsBaseMap = useBingAerialMapAsBaseMap;
	}
	
	public void setUseBingAerialMapAsBaseMap(String useBingAerialMapAsBaseMap) {
		setBooleanValue("useBingAerialMapAsBaseMap", useBingAerialMapAsBaseMap);
		
		if( getUseBingAerialMapAsBaseMap() == null ){
			System.out.println( "ERROR ON READING useBingAerialMapAsBaseMap FROM METADATA(DATABASE)" );
			setUseBingAerialMapAsBaseMap( Boolean.FALSE );	
		}
	}

	public Boolean getUseOSMAsBaseMap() {
		return useOSMAsBaseMap;
	}

	public void setUseOSMAsBaseMap(Boolean useOSMAsBaseMap) {
		this.useOSMAsBaseMap = useOSMAsBaseMap;
	}
	
	public void setUseOSMAsBaseMap(String useOSMAsBaseMap) {
		setBooleanValue("useOSMAsBaseMap", useOSMAsBaseMap);
		
		if( getUseOSMAsBaseMap() == null ){
			System.out.println( "ERROR ON READING useOSMAsBaseMap FROM METADATA(DATABASE)" );
			setUseOSMAsBaseMap( Boolean.FALSE );
		}
	}

	public Boolean getUseEmptyLayerAsBaseMap() {
		return useEmptyLayerAsBaseMap;
	}

	public void setUseEmptyLayerAsBaseMap(Boolean useEmptyLayerAsBaseMap) {
		this.useEmptyLayerAsBaseMap = useEmptyLayerAsBaseMap;
	}
	
	public void setUseEmptyLayerAsBaseMap(String useEmptyLayerAsBaseMap) {
		setBooleanValue("useEmptyLayerAsBaseMap", useEmptyLayerAsBaseMap);
		
		if( getUseEmptyLayerAsBaseMap() == null ){
			System.out.println( "ERROR ON READING useEmptyLayerAsBaseMap FROM METADATA(DATABASE)" );
			setUseEmptyLayerAsBaseMap( Boolean.FALSE );
		}
	}

	public Boolean getUseGoogleSatelliteMapAsOverviewMap() {
		return useGoogleSatelliteMapAsOverviewMap;
	}

	public void setUseGoogleSatelliteMapAsOverviewMap(
			Boolean useGoogleSatelliteMapAsOverviewMap) {
		this.useGoogleSatelliteMapAsOverviewMap = useGoogleSatelliteMapAsOverviewMap;
	}
	
	public void setUseGoogleSatelliteMapAsOverviewMap(
			String useGoogleSatelliteMapAsOverviewMap) {
		setBooleanValue("useGoogleSatelliteMapAsOverviewMap", useGoogleSatelliteMapAsOverviewMap);
		
		if( getUseGoogleSatelliteMapAsOverviewMap() == null ){
			System.out.println( "ERROR ON READING useGoogleSatelliteMapAsOverviewMap FROM METADATA(DATABASE)" );
			setUseGoogleSatelliteMapAsOverviewMap( Boolean.FALSE );
		}
	}

	public Boolean getUseBingSatelliteMapAsOverviewMap() {
		return useBingSatelliteMapAsOverviewMap;
	}

	public void setUseBingSatelliteMapAsOverviewMap(
			Boolean useBingSatelliteMapAsOverviewMap) {
		this.useBingSatelliteMapAsOverviewMap = useBingSatelliteMapAsOverviewMap;
	}
	
	public void setUseBingSatelliteMapAsOverviewMap(
			String useBingSatelliteMapAsOverviewMap) {
		setBooleanValue("useBingSatelliteMapAsOverviewMap", useBingSatelliteMapAsOverviewMap);
		
		if( getUseBingSatelliteMapAsOverviewMap() == null ){
			System.out.println( "ERROR ON READING useBingSatelliteMapAsOverviewMap FROM METADATA(DATABASE)" );
			setUseBingSatelliteMapAsOverviewMap( Boolean.FALSE );
		}
	}

	public Boolean getUseOSMAsOverviewMap() {
		return useOSMAsOverviewMap;
	}

	public void setUseOSMAsOverviewMap(Boolean useOSMAsOverviewMap) {
		this.useOSMAsOverviewMap = useOSMAsOverviewMap;
	}
	
	public void setUseOSMAsOverviewMap(String useOSMAsOverviewMap) {
		setBooleanValue("useOSMAsOverviewMap", useOSMAsOverviewMap);
		
		if( getUseOSMAsOverviewMap() == null ){
			System.out.println( "ERROR ON READING useOSMAsOverviewMap FROM METADATA(DATABASE)" );
			setUseOSMAsOverviewMap( Boolean.FALSE );
		}
	}

	public Short getOverviewMapFixedLevelNumber() {
		return overviewMapFixedLevelNumber;
	}

	public void setOverviewMapFixedLevelNumber(Short overviewMapFixedLevelNumber) {
		this.overviewMapFixedLevelNumber = overviewMapFixedLevelNumber;
	}
	
	public void setOverviewMapFixedLevelNumber(Long overviewMapFixedLevelNumber) {
		if( overviewMapFixedLevelNumber != null ){
			setOverviewMapFixedLevelNumber( (short)((long)overviewMapFixedLevelNumber) );
		}else{
			System.out.println( "ERROR ON READING overviewMapFixedLevelNumber FROM METADATA(DATABASE)" );
			setOverviewMapFixedLevelNumber( (short)12 );
		}
	}

	public Short getOverviewMapLayerId() {
		return overviewMapLayerId;
	}

	public void setOverviewMapLayerId(Short overviewMapLayerId) {
		this.overviewMapLayerId = overviewMapLayerId;
	}
	
	public void setOverviewMapLayerId(Long overviewMapLayerId) {
		if( overviewMapLayerId != null ){
			setOverviewMapLayerId( (short)((long)overviewMapLayerId) );
		}else{
			System.out.println( "ERROR ON READING overviewMapLayerId FROM METADATA(DATABASE)" );
			setOverviewMapLayerId( (short)10 );
		}
	}

	public Short getTableMaxPages() {
		return tableMaxPages;
	}

	public void setTableMaxPages(Short tableMaxPages) {
		this.tableMaxPages = tableMaxPages;
	}
	
	public void setTableMaxPages(Long tableMaxPages) {
		if( tableMaxPages != null ){
			setTableMaxPages( (short)((long)tableMaxPages) );
		}else{
			System.out.println( "ERROR ON READING tableMaxPages FROM METADATA(DATABASE)" );
			setTableMaxPages( (short)10 );
		}
	}

	public Boolean getMultiZoningStatusSupport() {
		return multiZoningStatusSupport;
	}

	public void setMultiZoningStatusSupport(Boolean multiZoningStatusSupport) {
		this.multiZoningStatusSupport = multiZoningStatusSupport;
	}
	
	public void setMultiZoningStatusSupport(String multiZoningStatusSupport) {
		setBooleanValue("multiZoningStatusSupport", multiZoningStatusSupport);
		
		if( getMultiZoningStatusSupport() == null ){
			System.out.println( "ERROR ON READING multiZoningStatusSupport FROM METADATA(DATABASE)" );
			setMultiZoningStatusSupport( Boolean.FALSE );
		}
	}

	public Boolean getDilDestegiGoruntuleme() {
		return dilDestegiGoruntuleme;
	}

	public void setDilDestegiGoruntuleme(Boolean dilDestegiGoruntuleme) {
		this.dilDestegiGoruntuleme = dilDestegiGoruntuleme;
	}
	
	public void setDilDestegiGoruntuleme(String dilDestegiGoruntuleme) {
		setBooleanValue("dilDestegiGoruntuleme", dilDestegiGoruntuleme);
		
		if( getDilDestegiGoruntuleme() == null ){
			System.out.println( "ERROR ON READING dilDestegiGoruntuleme FROM METADATA(DATABASE)" );
			setDilDestegiGoruntuleme( Boolean.FALSE );
		}
	}

	public String[] getPhotoFilterTags() {
		return photoFilterTags;
	}

	public void setPhotoFilterTags(String[] photoFilterTags) {
		this.photoFilterTags = photoFilterTags;
	}

	public void setPhotoFilterTags(String photoFilterTags) {
		if( photoFilterTags != null ){
			setPhotoFilterTags( AkosStringUtil.convertCSVToStringArray(photoFilterTags) );
		}
		if( getPhotoFilterTags() == null || getPhotoFilterTags().length == 0 ){
			System.out.println( "ERROR ON READING photoFilterTags FROM METADATA(DATABASE)" );
			setPhotoFilterTags( new String[]{"AKOS", "ADMIN"} );
		}
	}

	public String getBolgeEh() {
		return bolgeEh;
	}

	public void setBolgeEh(String bolgeEh) {
		
		if( bolgeEh != null ) {
			
			this.bolgeEh = bolgeEh;
		}
		else {
		
			this.bolgeEh = "H";
		}
	}

	public Boolean getShowStreetView() {
		return showStreetView;
	}

	public void setShowStreetView(Boolean showStreetView) {
		this.showStreetView = showStreetView;
	}
	
	public void setShowStreetView(String showStreetView) {
		
		setBooleanValue("showStreetView", showStreetView);
		
		if( getShowStreetView() == null ){
			
			System.out.println( "ERROR ON READING showStreetView FROM METADATA(DATABASE)" );
			
			setShowStreetView( Boolean.FALSE );
		}
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

	public Boolean getIsEmptyLayerPriorityFirst() {
		return isEmptyLayerPriorityFirst;
	}

	public void setIsEmptyLayerPriorityFirst(Boolean isEmptyLayerPriorityFirst) {
		this.isEmptyLayerPriorityFirst = isEmptyLayerPriorityFirst;
	}
	
	public void setIsEmptyLayerPriorityFirst(String isEmptyLayerPriorityFirst) {
		
		setBooleanValue("isEmptyLayerPriorityFirst", isEmptyLayerPriorityFirst);
		
		if( getIsEmptyLayerPriorityFirst() == null ){
			System.out.println( "ERROR ON READING IsEmptyLayerPriorityFirst FROM METADATA(DATABASE)" );
			setIsEmptyLayerPriorityFirst( Boolean.FALSE );
		}
	}
	
	public Boolean getShowPanaroma()
	{
		return this.showPanaroma;
	}
	public void setShowPanaroma(Boolean _showPanaroma)
	{
		this.showPanaroma=_showPanaroma;
	}
	public void setShowPanaroma(String _showPanaroma)
	{
		setBooleanValue("showPanaroma", _showPanaroma);
		if( getShowPanaroma() == null ){
			
			System.out.println( "ERROR ON READING showPanaroma FROM METADATA(DATABASE)" );
			
			setShowPanaroma( Boolean.FALSE );
		}
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
	public Boolean getPanaromaOlcmeToolbar()
	{
		return this.panaromaOlcmeToolbar;
	}
	public void setPanaromaOlcmeToolbar(Boolean _panaromaOlcmeToolbar)
	{
		this.panaromaOlcmeToolbar=_panaromaOlcmeToolbar;
	}
	public void setPanaromaOlcmeToolbar(String _panaromaOlcmeToolbar)
	{
		setBooleanValue("panaromaOlcmeToolbar", _panaromaOlcmeToolbar);
		if( getPanaromaOlcmeToolbar() == null ){
			
			System.out.println( "ERROR ON READING panaromaOlcmeToolbar FROM METADATA(DATABASE)" );
			
			setPanaromaOlcmeToolbar( Boolean.FALSE );
		}
	}
	public String getPanaromaServiceUrl()
	{
		return this.panaromaServiceUrl;
	}
	
	public void setPanaromaServiceUrl(String _panaromaServiceUrl)
	{
		this.panaromaServiceUrl=_panaromaServiceUrl;
	}
	public Boolean getPanaromaShowLidar()
	{
		return this.panaromaShowLidar;
	}
	public void setPanaromaShowLidar(Boolean _panaromaShowLidar)
	{
		
		this.panaromaShowLidar=_panaromaShowLidar;
	}
	public void setPanaromaShowLidar(String _panaromaShowLidar)
	{
		setBooleanValue("panaromaShowLidar", _panaromaShowLidar);
		if( getPanaromaShowLidar() == null ){
			
			System.out.println( "ERROR ON READING panaromaShowLidar FROM METADATA(DATABASE)" );
			
			setPanaromaShowLidar( Boolean.FALSE );
		}
		
	}
	public Boolean getPanaromaShowTimeLabel()
	{
		return this.panaromaShowTimeLabel;
	}
	
	public void setPanaromaShowTimeLabel(Boolean _panaromaShowTimeLabel)
	{
		this.panaromaShowTimeLabel=_panaromaShowTimeLabel;
	}
	public void setPanaromaShowTimeLabel(String _panaromaShowTimeLabel)
	{
		setBooleanValue("panaromaShowTimeLabel", _panaromaShowTimeLabel);
		if( getPanaromaShowLidar() == null ){
			
			System.out.println( "ERROR ON READING panaromaShowTimeLabel FROM METADATA(DATABASE)" );
			
			setPanaromaShowTimeLabel( Boolean.FALSE );
		}
		
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

	public String[] getTileLayerOpacity() {
		return tileLayerOpacity;
	}

	public void setTileLayerOpacity(String tileLayerOpacity) {
		if( tileLayerOpacity != null ){
			this.tileLayerOpacity=AkosStringUtil.convertCSVToStringArray(tileLayerOpacity);
		}
		
	}

	public String[] getTileLayerScale() {
		return tileLayerScale;
	}

	public void setTileLayerScale(String tileLayerScale) {
		if( tileLayerScale != null ){
			this.tileLayerScale=AkosStringUtil.convertCSVToStringArray(tileLayerScale);
		}		
	}

	public String[] getTileLayerIsBaselayer() {
		return tileLayerIsBaselayer;
	}

	public void setTileLayerIsBaselayer(String tileLayerIsBaselayer) {
		if( tileLayerIsBaselayer != null ){
			this.tileLayerIsBaselayer=AkosStringUtil.convertCSVToStringArray(tileLayerIsBaselayer);
		}
		
	}

}
