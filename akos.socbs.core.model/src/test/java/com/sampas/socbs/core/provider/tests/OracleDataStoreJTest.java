package com.sampas.socbs.core.provider.tests;

import java.util.List;

import junit.framework.TestCase;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.provider.services.impl.OracleDataStoreSrv;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.impl.SmpLayerNames;
import com.sampas.socbs.core.test.tools.TestServerMetaData;

public class OracleDataStoreJTest extends TestCase {

	private TestServerMetaData testServerMetaData = null;
	
	private OracleDataStoreSrv oracleDataStoreCreatorSrv = null;
    
	private IFeatureDataStore dataProvider = null;
	
	private IFeatureLayerProvider layerProvider = null;
	
	private void globals() {
		
		try {
			
			testServerMetaData = new TestServerMetaData();
			
	        String namespace = "smpboglu";	        
	        String host = "192.168.34.14";
	        int port = 1521;
	        String instance = "SAMPAS10T";        
	        String user = "BEYOGLU";
	        String passwd = "BEYOGLU";	        
	        String schema = "BEYOGLU";
	        int maxConn = 20;
	        int minConn = 4;
	        Boolean validateConn = false;
			
	        this.oracleDataStoreCreatorSrv = new OracleDataStoreSrv(namespace, host, port, instance, user, passwd, schema, maxConn, minConn, validateConn);
	        
			this.dataProvider = oracleDataStoreCreatorSrv.getOracleDataStore();
			
			this.layerProvider = new SmpFeatureLayerProviderSrv();
			
		} catch (Exception ex) {
			
			System.out.println("Error on creating data store !");
		}
			
	}
	
	public void testDataStoreWithLayerNames() {

		try {
			
			globals();
			
			ILayerNames layerNamesSelected = new SmpLayerNames();
			
			layerNamesSelected.addLayerName(testServerMetaData.getOracleExLayerName1());
			layerNamesSelected.addLayerName(testServerMetaData.getOracleExLayerName2());
			
			List<IFeatureLayer> smpLayers = layerProvider.getLayers(dataProvider, layerNamesSelected);

			IFeatureCollection smpFeatures = smpLayers.get(0).getFeatureCollection();

			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : WfsDataStoreJTest JUnit Test");
			System.out.println("Toplam Katman Sayısı : " + smpLayers.size());
			System.out.println("Sorgulanan Katman Numarası : " + testServerMetaData.getLayerStreet());
			System.out.println("Elde Edilen Nesne Sayısı : " + smpFeatures.getFeaturesCount());
			System.out.println("**********************************************************************" + "\n");
			////////////////////////////////////////////////////////////////////////////////////////////
			
			//Test criteria		
			assertNotSame(0, smpFeatures.getFeaturesCount());
	        
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	public void testDataStore() {

		try {
			
			globals();
			
			List<IFeatureLayer> smpLayers = layerProvider.getLayers(dataProvider);

			//IFeatureCollection smpFeatures = smpLayers.get(testServerMetaData.getLayerStreet()).getFeatureCollection();
			IFeatureCollection smpFeatures = smpLayers.get(0).getFeatureCollection();

			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : WfsDataStoreJTest JUnit Test");
			System.out.println("Toplam Katman Sayısı : " + smpLayers.size());
			System.out.println("Sorgulanan Katman Numarası : " + testServerMetaData.getLayerStreet());
			System.out.println("Elde Edilen Nesne Sayısı : " + smpFeatures.getFeaturesCount());
			System.out.println("**********************************************************************" + "\n");
			////////////////////////////////////////////////////////////////////////////////////////////
			
			//Test criteria		
			assertNotSame(0, smpFeatures.getFeaturesCount());

	        
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
}
