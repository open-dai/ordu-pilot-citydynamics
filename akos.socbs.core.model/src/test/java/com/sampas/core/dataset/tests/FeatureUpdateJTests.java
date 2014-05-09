package com.sampas.core.dataset.tests;

import java.util.List;
import junit.framework.TestCase;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.provider.services.impl.OracleDataStoreSrv;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureCollection;
import com.sampas.socbs.core.gisdatabase.tools.servis.IGisDatabaseServis;
import com.sampas.socbs.core.gisdatabase.tools.servis.impl.GisDatabaseServis;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.impl.SmpLayerNames;
import com.sampas.socbs.core.test.tools.TestServerMetaData;


public class FeatureUpdateJTests extends TestCase {

	@SuppressWarnings("unused")
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
			
			System.err.println("Error on creating data store !");
		}
			
	}
	
	public void testAddNewFeatures() {
		
		try {
			
			globals();
			
			int testProcessFeatureCntr = 1000;
			
			ILayerNames layerNames = new SmpLayerNames();
			
			layerNames.addLayerName("SOA_GRF_CADDE_SOKAK");
			
			List<IFeatureLayer> smpLayers = layerProvider.getLayers(dataProvider, layerNames);
			
			IFeatureCollection newFeatures = new SmpFeatureCollection();
			
			for (int featureCntr = 0; featureCntr < testProcessFeatureCntr; featureCntr++) {
				
				newFeatures.addFeature(smpLayers.get(0).getFeatureCollection().getFeatureAt(featureCntr).cloneFeature());
			}
			if (newFeatures != null && newFeatures.getFeaturesCount() > 0) {
				
				IGisDatabaseServis gisDatabaseServis = new GisDatabaseServis(this.dataProvider);
				
				gisDatabaseServis.writeFeatures(newFeatures);
			}
			System.out.println("Debug Stopper !");
			
		} catch (Exception ex) {
			
			System.err.println("Error on writing new features !");
		}
		
	}
	
	public void testUpdateMultiFeatures() {
		
		try {
			
			globals();
			
			int testProcessFeatureCntr = 2712;
			
			ILayerNames layerNames = new SmpLayerNames();
			
			layerNames.addLayerName("SOA_GRF_CADDE_SOKAK");
			
			List<IFeatureLayer> smpLayers = layerProvider.getLayers(dataProvider, layerNames);
			
			IFeatureCollection updateFeatures = new SmpFeatureCollection();
			
			IFeatureCollection features = smpLayers.get(0).getFeatureCollection();
			
			int pointsPlace = 0;
			
			Long featureID = 0L;
				
			for (int featureCntr = 0; featureCntr < testProcessFeatureCntr; featureCntr++) {
				
				pointsPlace = features.getFeatureAt(featureCntr).getID().indexOf(".");
				
				featureID = Long.valueOf(features.getFeatureAt(featureCntr).getID().substring(pointsPlace + 1));
				
				if (featureID > 1714) {
					
					features.getFeatureAt(featureCntr).setAttribute("GEOMETRY", ((SmpFeature)smpLayers.get(0).getFeatureCollection().getFeatureAt(featureCntr)).getGeoToolFeature().getDefaultGeometry());
					
					updateFeatures.addFeature(features.getFeatureAt(featureCntr));
				}
			}
			if (updateFeatures != null && updateFeatures.getFeaturesCount() > 0) {
				
				IGisDatabaseServis gisDatabaseServis = new GisDatabaseServis(this.dataProvider);
				
				gisDatabaseServis.updateFeatures(updateFeatures);
			}
			System.out.println("Debug Stopper !");
			
		} catch (Exception ex) {
			
			System.err.println("Error on writing new features ! ERROR: " + ex);
		}
		
	}
	
}
