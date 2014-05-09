package com.sampas.socbs.core.provider.tests;

import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import com.sampas.socbs.core.data.IWMSDataStore;
import com.sampas.socbs.core.data.provider.services.impl.WMSDataStoreSrv;
import com.sampas.socbs.core.data.providers.IWMSLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpWMSLayerProviderSrv;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsGMLVersion;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsVersion;
import com.sampas.socbs.core.maplayer.IWMSLayer;
import com.sampas.socbs.core.test.tools.TestServerMetaData;

public class WmsLayersJTest extends TestCase{

	private URL wfsUrl = null;
	
	private TestServerMetaData testServerMetaData = null;
	
	public void testWmsLayers() {

		testServerMetaData = new TestServerMetaData();
		
		try {
			
			this.wfsUrl = new URL(testServerMetaData.getWmsServerAddress());
			
			WMSDataStoreSrv wmsDataStoreCreatorSrv = new WMSDataStoreSrv(this.wfsUrl, WmsVersion.ver100, WmsGMLVersion.gml2);
			
			IWMSDataStore dataProvider = wmsDataStoreCreatorSrv.getWmsDataStore();
			
			IWMSLayerProvider layerProvider = new SmpWMSLayerProviderSrv();
			
			List<IWMSLayer> smpLayers = layerProvider.getLayers(dataProvider);
			
			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : WmsLayersJTest JUnit Test");
			System.out.println("Bulunan Katman Sayısı : " + smpLayers.size());
			System.out.println("**********************************************************************" + "\n");
			////////////////////////////////////////////////////////////////////////////////////////////
			
			//Test criteria		
			assertNotSame(0, smpLayers.size());
			
		} catch (Exception ex) {
			
		}
	}
	
}
