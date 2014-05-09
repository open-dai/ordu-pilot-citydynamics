package com.sampas.socbs.poi.servis.metadata.impl;

import com.sampas.gis.ortak.model.PoiTypes;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.opendai.servis.metadata.MapMetadata;


public class POIServisMetadata {

//	private String addressName = "";
	
	private String importantPlaceName = "";
	
//	private OracleDataStoreSrv oracleDataStoreSrv = null;
	
	private MapMetadata mapMetadata = null;
	
	private PoiTypes poiTypes = null;
	
	//set this poi names to metadata
//	private String[] poiNameTypesStr    = {"Hastane", "Eczane", "Kilise", "Cami", "Egitim", "Konaklama", "Sinagog", "Tiyatro", "Sinema", "Restaurant", "Ulasim-Turizm", "Kulturel", "Resmi-Kurum", "Konsolosluk", "Vakif", "Tarihi-Eser", "Otel"};
//	private String[] poiKullanimSinifID = {"8",       "8",      "2",      "2",    "3",      "12",        "2",       "5",       "5",      "11",         "12",            "5",        "7",           "7",           "10",    "12",          "12"};
//	private String[] poiKullanimGrupID  = {"45",      "44",     "6",      "6",    "10",     "88",        "6",       "27",      "26",     "85",         "",              "",         "",            "36",          "67",    "89",          "88"};
//	private String[] poiKullanimDetayID = {"",        "109",    "17",     "15",   "",       "",          "21",      "",        "",       "",           "",              "",         "",            "",            "",      "",            "310"};
	
	private String[] poiNameTypesStr    = {"Hastane", "Eczane", "Kilise", "Cami", "Egitim", "Konaklama", "Sinagog", "Tiyatro", "Sinema", "Ulasim-Turizm", "Konsolosluk", "Vakif", "Tarihi-Eser", "Otel"};
	private String[] poiKullanimSinifID = {"8",       "8",      "2",      "2",    "3",      "12",        "2",       "5",       "5",      "12",            "7",           "10",    "12",          "12"};
	private String[] poiKullanimGrupID  = {"45",      "44",     "6",      "6",    "10",     "88",        "6",       "27",      "26",     "",              "36",          "67",    "89",          "88"};
	private String[] poiKullanimDetayID = {"",        "109",    "17",     "15",   "",       "",          "21",      "",        "",       "",              "",            "",      "",            "310"};
	
	private String imptPlcUIDAttName = "FID";
	
	public POIServisMetadata() {
		
		if(this.mapMetadata == null) {
			
			this.mapMetadata = new MapMetadata();
		}
	}
	
//	public IFeatureDataStore getMetaSimulatedDataStore() {
//		
//		if (this.oracleDataStoreSrv == null) {
//			
//	        String namespace = mapMetadata.getDsNameSpace();	        
//	        
//	        String host = mapMetadata.getDsHost();
//	        
//	        int port = mapMetadata.getDsPort();
//	        
//	        String instance = mapMetadata.getDsInstance();
//	        
//	        String user = mapMetadata.getDsUser();
//	        
//	        String passwd = mapMetadata.getDsPass();  
//	        
//	        String schema = mapMetadata.getDsSchema();
//	        
//	        int maxConn = 20;
//	        
//	        int minConn = 4;
//	        
//	        Boolean validateConn = false;
//			
//	        this.oracleDataStoreSrv = new OracleDataStoreSrv(namespace, host, port, instance, user, passwd, schema, maxConn, minConn, validateConn);
//		}
//		IFeatureDataStore datastore = oracleDataStoreSrv.getOracleDataStore();
//		
//        return datastore;
//	}
	
//	public IFeatureTypeName getMetaSimulatedAddressFeatureTypeName() {
//		
//		if (this.addressName.equals("")) {
//			
//			this.addressName = mapMetadata.getAddressName();		
//		}
//		IFeatureTypeName featureTypeName = new SmpFeatureTypeName(this.addressName);
//		
//		return (featureTypeName);
//	}
	
//	public IFeatureTypeName getMetaSimulatedImportantPlaceFeatureTypeName() {
//		
//		if (this.importantPlaceName.equals("")) {
//			
//			this.importantPlaceName = mapMetadata.getImportantPlaceName();
//		}
//		IFeatureTypeName featureTypeName = new SmpFeatureTypeName(this.importantPlaceName);
//		
//		return (featureTypeName);
//	}
	
//	public ICoordinateSystem getDataSimulatedLayerCoordinateSystem() {
//		
//		String EPSG = mapMetadata.getAreaEPSGCode();
//		
//		if (EPSG != null && EPSG.contains("EPSG")) {
//
//			ICoordinateSystem coordinateSystem = new SmpCoordinateSystem(EPSG);
//			
//			return coordinateSystem;
//		} else {
//			
//			System.out.println("Error on finding regular EPSG code from metadata !");
//			
//			return null;
//		}
//	}
	
//	public String getSimulatedAddressUIDCName() {
//		
//		return (this.mapMetadata.getAddressUIDCName());
//	}
	
	public ICoordinateSystem getDataSimulatedTargetCoordinateSystem() {
		
		ICoordinateSystem coordinateSystem = new SmpCoordinateSystem("EPSG:4326");
		
		return coordinateSystem;
	}

	public void setImportantPlaceName(String importantPlaceName) {
		this.importantPlaceName = importantPlaceName;
	}

	public String getImportantPlaceName() {
		return importantPlaceName;
	}

	public void setPoiTypes(PoiTypes poiTypes) {
		this.poiTypes = poiTypes;
	}

	public PoiTypes getPoiTypes() {
		
		if (this.poiTypes == null) {
			
			this.poiTypes = new PoiTypes();
			
			for (int i = 0; i < poiNameTypesStr.length; i++) {
				
				this.poiTypes.addPoiTypes(poiNameTypesStr[i]);
			}
		}
		
		return poiTypes;
	}
	
	public Long getPoiKullanimDetayID(String poitypeName) {
		
		String tempStr = "";
		
		for (int i = 0; i < this.poiKullanimDetayID.length; i++) {
			
			if(this.poiNameTypesStr[i].equals(poitypeName)) {
				
				tempStr = this.poiKullanimDetayID[i];
				
				try {
					
					return (Long.decode(tempStr));
				} catch (Exception ex) {
					//This means this important place has not got kullanimDetayKodu
//					System.out.println("Error on finding kullanimDetayKodu ! ERROR : " + ex);
				}
				break;
			}
		}
		return -1L;
	}
	
	public Long getPoiKullanimGrupID(String poitypeName) {
		
		String tempStr = "";
		
		for (int i = 0; i < this.poiKullanimGrupID.length; i++) {
			
			if(this.poiNameTypesStr[i].equals(poitypeName)) {
				
				tempStr = this.poiKullanimGrupID[i];
				
				try {
					
					return (Long.decode(tempStr));
				} catch (Exception ex) {
					
					System.out.println("Error on finding kullanimGrupKodu ! ERROR : " + ex);
				}
				break;
			}
		}
		return -1L;
	}
	
	public Long getPoiKullanimSinifID(String poitypeName) {
		
		String tempStr = "";
		
		for (int i = 0; i < this.poiKullanimSinifID.length; i++) {
			
			if(this.poiNameTypesStr[i].equals(poitypeName)) {
				
				tempStr = this.poiKullanimSinifID[i];
				try {
					
					return (Long.decode(tempStr));
				} catch (Exception ex) {
					
					System.out.println("Error on finding kullanimSinifKodu ! ERROR : " + ex);
				}
				break;
			}
		}
		return -1L;
	}

	public String getImptPlcUIDAttName() {
		return imptPlcUIDAttName;
	}

	public void setImptPlcUIDAttName(String imptPlcUIDAttName) {
		this.imptPlcUIDAttName = imptPlcUIDAttName;
	}
	
}
