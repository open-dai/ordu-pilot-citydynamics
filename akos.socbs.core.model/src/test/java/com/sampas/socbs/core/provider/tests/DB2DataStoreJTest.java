package com.sampas.socbs.core.provider.tests;

 import java.util.List;
import junit.framework.TestCase;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.provider.services.IDB2DataStoreSrv;
import com.sampas.socbs.core.data.provider.services.impl.DB2DataStoreSrv;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.test.tools.TestServerMetaData;


public class DB2DataStoreJTest extends TestCase {

	private TestServerMetaData testServerMetaData = null;
	
	public void testDataStore() {

		try {
			
			testServerMetaData = new TestServerMetaData();
			
			String tabSchema = "schemaName";
			String db2DBURL = "192.168.34.216";
			long cacheTimeOut = 10000;
	        
	        IDB2DataStoreSrv db2DataStoreCreatorSrv = new DB2DataStoreSrv(tabSchema, db2DBURL, cacheTimeOut);
	        
			IFeatureDataStore dataProvider = db2DataStoreCreatorSrv.getDB2DataStore();
	        
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
