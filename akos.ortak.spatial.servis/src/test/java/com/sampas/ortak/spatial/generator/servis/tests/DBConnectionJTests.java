package com.sampas.ortak.spatial.generator.servis.tests;

import java.util.List;

import com.sampas.akos.common.test.BaseTestCase;
import com.sampas.ortak.spatial.generator.model.DBConnection;
import com.sampas.ortak.spatial.generator.servis.impl.DBConnectionServis;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.impl.SmpLayerNames;


public class DBConnectionJTests extends BaseTestCase {

	private DBConnectionServis dbConnectionServis;
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "com/sampas/akos/commonTestContext.xml" };
	}

	protected void onSetUpInTransaction() throws Exception {
		
		setComplete();
		
		dbConnectionServis = (DBConnectionServis)applicationContext.getBean("dbConnectionServis");
	}
	
	@Override
	protected String[] getXmlDataFileResource() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void testDBConnectionParameters() {
		
		DBConnection dbConnection = dbConnectionServis.getDbConnection();
		
		assertNotNull(dbConnection.getHost());
		assertNotNull(dbConnection.getPort());
		assertNotNull(dbConnection.getInstance());
		assertNotNull(dbConnection.getSchema());
		assertNotNull(dbConnection.getUser());
		assertNotNull(dbConnection.getPassword());
		
	}
	
	public void testCreateFeatureDataStore() {
		
		IFeatureDataStore featureDataStore = dbConnectionServis.getFeatureDataStore();
		
		assertNotNull(featureDataStore);
		
	}
	
	public void testCreateFeatureLayerProvider() {
		
		IFeatureDataStore featureDataStore = dbConnectionServis.getFeatureDataStore();
		
		IFeatureLayerProvider featureLayerProvider = new SmpFeatureLayerProviderSrv();
		
		ILayerNames layerNames = featureLayerProvider.getLayerNames(featureDataStore);
		
		ILayerNames neededLayerNames = new SmpLayerNames();
		
		neededLayerNames.addLayerName(layerNames.getLayerName(0));
		
		List<IFeatureLayer> featureLayers = featureLayerProvider.getLayers(featureDataStore, neededLayerNames);
		
		assertNotSame(featureLayers.size(), 0);
		
	}
	
}
