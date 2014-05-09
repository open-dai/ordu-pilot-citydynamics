package com.sampas.socbs.core.test.tools;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.provider.services.impl.OracleDataStoreSrv;

public class TestServerMetaData {

	String WfsServerAddress = "http://192.168.34.217:8080/geoserver/wfs";
	
	String WmsServerAddress = "http://192.168.34.217:8080/geoserver/wms";
	
	String TileServerAddress = "http://192.168.34.217:8080/geoserver/gwc/service/wms";
	
  	private OracleDataStoreSrv oracleDataStoreSrv = null;
	
	public IFeatureDataStore getTestDataStore() {
		
//		OracleDataSource oracleDataSource = new OracleDataSource();
//		
//		oracleDataSource.setURL("jdbc:oracle:thin:@venus.sampas.com.tr:1521:sampas");
//		oracleDataSource.setDatabaseName("AKOS_SOA_TEST");
//		oracleDataSource.setUser("AKOS_ENTEGRATION");
//		oracleDataSource.setPassword("AKOS_ENTEGRATION");
//		oracleDataSource.setConnectionCacheName("Cache");
//		oracleDataSource.setConnectionCachingEnabled(true);
//		JDBCDataStoreConfig dataStoreConfig = new JDBCDataStoreConfig("smp", "AKOS_ENTEGRATION", 100);
//		
//		this.dataStore = new OracleDataStore(oracleDataSource, dataStoreConfig);
		
        String namespace = "smpboglu";	        
        String host = "venus";
        int port = 1521;
        String instance = "sampas";        
        String user = "AKOS_ENTEGRATION";
        String passwd = "AKOS_ENTEGRATION";	        
        String schema = "AKOS_ENTEGRATION";
        int maxConn = 20;
        int minConn = 4;
        Boolean validateConn = false;
		
        this.oracleDataStoreSrv = new OracleDataStoreSrv(namespace, host, port, instance, user, passwd, schema, maxConn, minConn, validateConn);
        
        IFeatureDataStore datastore = oracleDataStoreSrv.getOracleDataStore();
		
        return datastore;
	}
	
	//Layer District number
	int layerDistrict = 8;
	
	//Layer Address number
	int layerAddress = 0;
	
	//Layer Street number
	int layerStreet = 3;
	
	//Layer Building number
	int layerBuilding = 2;
	
	//Layer Parcel number
	int layerParcel = 7;
	
	//District Code attribute number
	int districtCode = 4;
	
	//District Name attribute number
	int districtName = 6;
	
	//Address No attribute number
	int addressNo = 4;
	
	//Example test value for tests
	int expVal = 150;
	
	String OracleExLayerName1 = "GRF_CADDE_SOKAK";
	String OracleExLayerName2 = "GRF_MAHALLE";
	
	public int getExpValLower() {
		return expValLower;
	}

	public void setExpValLower(int expValLower) {
		this.expValLower = expValLower;
	}

	public int getExpValUpper() {
		return expValUpper;
	}

	public void setExpValUpper(int expValUpper) {
		this.expValUpper = expValUpper;
	}

	//Example test value for tests
	int expValLower = 160;
	
	//Example test value for tests
	int expValUpper = 170;
	
	public int getExpVal() {
		return expVal;
	}

	public void setExpVal(int expVal) {
		this.expVal = expVal;
	}

	public int getAddressNo() {
		return addressNo;
	}

	public void setAddressNo(int addressNo) {
		this.addressNo = addressNo;
	}
	
	public int getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}

	public int getDistrictName() {
		return districtName;
	}

	public void setDistrictName(int districtName) {
		this.districtName = districtName;
	}

	public int getLayerStreet() {
		return layerStreet;
	}

	public void setLayerStreet(int layerStreet) {
		this.layerStreet = layerStreet;
	}

	public int getLayerAddress() {
		return layerAddress;
	}

	public void setLayerAddress(int layerAddress) {
		this.layerAddress = layerAddress;
	}

	public int getLayerDistrict() {
		return layerDistrict;
	}

	public void setLayerDistrict(int layerDistrict) {
		this.layerDistrict = layerDistrict;
	}

	public TestServerMetaData() {
		
	}

	public String getWfsServerAddress() {
		return WfsServerAddress;
	}

	public void setWfsServerAddress(String wfsServerAddress) {
		WfsServerAddress = wfsServerAddress;
	}

	public String getWmsServerAddress() {
		return WmsServerAddress;
	}

	public void setWmsServerAddress(String wmsServerAddress) {
		WmsServerAddress = wmsServerAddress;
	}

	public int getLayerBuilding() {
		return layerBuilding;
	}

	public void setLayerBuilding(int layerBuilding) {
		this.layerBuilding = layerBuilding;
	}

	public int getLayerParcel() {
		return layerParcel;
	}

	public void setLayerParcel(int layerParcel) {
		this.layerParcel = layerParcel;
	}

	public String getOracleExLayerName1() {
		return OracleExLayerName1;
	}

	public void setOracleExLayerName1(String oracleExLayerName1) {
		OracleExLayerName1 = oracleExLayerName1;
	}

	public String getOracleExLayerName2() {
		return OracleExLayerName2;
	}

	public void setOracleExLayerName2(String oracleExLayerName2) {
		OracleExLayerName2 = oracleExLayerName2;
	}

	public String getTileServerAddress() {
		return TileServerAddress;
	}

	public void setTileServerAddress(String tileServerAddress) {
		TileServerAddress = tileServerAddress;
	}
	
}
