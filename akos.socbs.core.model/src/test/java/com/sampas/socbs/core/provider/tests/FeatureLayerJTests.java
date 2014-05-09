package com.sampas.socbs.core.provider.tests;

import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.provider.services.impl.WFSDataStoreSrv;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.data.wfs.impl.WFSDataStore.WfsGMLVersion;
import com.sampas.socbs.core.data.wfs.impl.WFSDataStore.WfsVersion;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.test.tools.TestServerMetaData;

public class FeatureLayerJTests extends TestCase {

	private URL wfsUrl = null;
	
  	private TestServerMetaData testServerMetaData = null;
	
	public FeatureLayerJTests() {
		
	}
	
	public void testFeatureLayerBbox() {

		try {
			
			testServerMetaData = new TestServerMetaData();
			
			this.wfsUrl = new URL(testServerMetaData.getWfsServerAddress());
			
			WFSDataStoreSrv wfsDataStoreCreatorSrv = new WFSDataStoreSrv(this.wfsUrl, WfsGMLVersion.gml2, WfsVersion.ver100);
			
			IFeatureDataStore dataProvider = wfsDataStoreCreatorSrv.getWfsDataStore();
			
			IFeatureLayerProvider layerProvider = new SmpFeatureLayerProviderSrv();
			
			List<IFeatureLayer> smpLayers = layerProvider.getLayers(dataProvider);

			int selectedLayer = testServerMetaData.getLayerStreet();
			selectedLayer = 3;
			
			//define percent of layer percent = 0 means I want a center point bounding box layer
			//change percent for see how feature count change
			double percent = 100;
			double difX = 0;
			double difY = 0;

			//find difference between max and min 
			difX = ((smpLayers.get(selectedLayer).getExtent().getMaxX() - smpLayers.get(selectedLayer).getExtent().getMinX()) * ((100 - percent) / 2)) / 100;
			difY = ((smpLayers.get(selectedLayer).getExtent().getMaxY() - smpLayers.get(selectedLayer).getExtent().getMinY()) * ((100 - percent) / 2)) / 100;
			
			SmpBoundingRectangle smpBbox = new SmpBoundingRectangle(smpLayers.get(selectedLayer).getExtent().getMinX() + difX, smpLayers.get(selectedLayer).getExtent().getMinY() + difY, smpLayers.get(selectedLayer).getExtent().getMaxX() - difX, smpLayers.get(selectedLayer).getExtent().getMaxY() - difY);
			//SmpBoundingRectangle smpBbox = new SmpBoundingRectangle(smpLayers.get(selectedLayer).getExtent().getMinX(), smpLayers.get(selectedLayer).getExtent().getMinY(), smpLayers.get(selectedLayer).getExtent().getMaxX(), smpLayers.get(selectedLayer).getExtent().getMaxY());
			
			IFeatureCollection smpFeatures = smpLayers.get(selectedLayer).getFeaturesByBRectAndFilter(smpBbox, null);
			
			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : Feature Layer BoundingRectangle JUnit Test");
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
