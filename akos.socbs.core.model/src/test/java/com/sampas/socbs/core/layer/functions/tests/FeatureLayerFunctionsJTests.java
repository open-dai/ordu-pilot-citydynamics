package com.sampas.socbs.core.layer.functions.tests;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.provider.services.impl.WFSDataStoreSrv;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.data.wfs.impl.WFSDataStore.WfsGMLVersion;
import com.sampas.socbs.core.data.wfs.impl.WFSDataStore.WfsVersion;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureID;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureID;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.test.tools.TestServerMetaData;

public class FeatureLayerFunctionsJTests extends TestCase {

	private URL wfsUrl = null;
	
  	private TestServerMetaData testServerMetaData = null;
	
	public void testFeatureLayerFIDsFilter() {

		try {
			
			testServerMetaData = new TestServerMetaData();
			
			this.wfsUrl = new URL(testServerMetaData.getWfsServerAddress());
			
			WFSDataStoreSrv wfsDataStoreCreatorSrv = new WFSDataStoreSrv(this.wfsUrl, WfsGMLVersion.gml2, WfsVersion.ver100);
			
			IFeatureDataStore dataProvider = wfsDataStoreCreatorSrv.getWfsDataStore();
			
			IFeatureLayerProvider layerProvider = new SmpFeatureLayerProviderSrv();
			
			List<IFeatureLayer> smpLayers = layerProvider.getLayers(dataProvider);

			int selectedLayer = testServerMetaData.getLayerStreet();
			selectedLayer = 9;

			IFeatureCollection layerAllFeatures = smpLayers.get(selectedLayer).getFeatureCollection();
			
			List<IFeatureID> featureIDs = new ArrayList<IFeatureID>();
			
			//Generating example data
			featureIDs.add(new SmpFeatureID(layerAllFeatures.getFeatureAt(1).getID()));
			featureIDs.add(new SmpFeatureID(layerAllFeatures.getFeatureAt(3).getID()));
			featureIDs.add(new SmpFeatureID(layerAllFeatures.getFeatureAt(5).getID()));
			
			IFeatureCollection smpFeatures = smpLayers.get(selectedLayer).getFeaturesbyFIDs(featureIDs);
			
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
