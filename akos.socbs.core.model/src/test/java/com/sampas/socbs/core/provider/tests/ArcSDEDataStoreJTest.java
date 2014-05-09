package com.sampas.socbs.core.provider.tests;

import java.util.List;
import junit.framework.TestCase;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.provider.services.impl.ArcSDEDataStoreSrv;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.test.tools.TestServerMetaData;


public class ArcSDEDataStoreJTest extends TestCase {

	private TestServerMetaData testServerMetaData = null;
	
	public void testDataStore() {

		try {
			
			testServerMetaData = new TestServerMetaData();
			
			String dbtype = "arcsde";
	        String server = "192.168.34.216";
	        String port = "1234";
	        String instance = "Beyoglu";        
	        String user = "postgres";
	        String password = "dbusocbs";
	        
	        ArcSDEDataStoreSrv arcSDEDataStoreCreatorSrv = new ArcSDEDataStoreSrv(dbtype, server, port, instance, user, password);
	        
			IFeatureDataStore dataProvider = arcSDEDataStoreCreatorSrv.getArcSDEDataStore();
	        
			IFeatureLayerProvider layerProvider = new SmpFeatureLayerProviderSrv();
			
			List<IFeatureLayer> smpLayers = layerProvider.getLayers(dataProvider);

			IFeatureCollection smpFeatures = smpLayers.get(testServerMetaData.getLayerStreet()).getFeatureCollection();

			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : WfsDataStoreJTest JUnit Test");
			//System.out.println("Sorgulanan Katman Numarası : " + testServerMetaData.getLayerStreet());
			System.out.println("Sorgulanan Katman Adı : " + smpLayers.get(testServerMetaData.getLayerStreet()).getName());
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
