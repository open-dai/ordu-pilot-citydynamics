package akos.socbs.geolsa.servis.tests;

import java.util.ArrayList;
import java.util.List;
import com.sampas.akos.common.test.BaseTestCase;
import com.sampas.socbs.geolsa.model.MdAppLayer;
import com.sampas.socbs.geolsa.model.MdDatabaseFeatureLayer;
import com.sampas.socbs.geolsa.model.MdDatabaseRasterLayer;
import com.sampas.socbs.geolsa.model.MdFeatureLayer;
import com.sampas.socbs.geolsa.model.MdFeatureThemeStyle;
import com.sampas.socbs.geolsa.model.MdFileBasedFeatureLayer;
import com.sampas.socbs.geolsa.model.MdFileBasedRasterLayer;
import com.sampas.socbs.geolsa.model.MdLayer;
import com.sampas.socbs.geolsa.model.MdLayerAttributeVisibleName;
import com.sampas.socbs.geolsa.model.MdRasterLayer;
import com.sampas.socbs.geolsa.model.MdRasterThemeStyle;
import com.sampas.socbs.geolsa.model.MdRemoteFeatureLayer;
import com.sampas.socbs.geolsa.model.MdRemoteRasterLayer;
import com.sampas.socbs.geolsa.model.MdSmpGISApp;
import com.sampas.socbs.geolsa.model.MetadataAppStaticVars;
import com.sampas.socbs.geolsa.servis.IGeolsaServis;

public class GeolsaServisJTests extends BaseTestCase {

	@Override
	protected String[] getXmlDataFileResource() {

		return new String[]{};
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "com/sampas/socbs/commonTestContext.xml" };
	}
	
	IGeolsaServis geolsaServis;
	
	protected void onSetUpInTransaction() throws Exception {
		
		setComplete();		
		
		this.geolsaServis = (IGeolsaServis) applicationContext.getBean("metadataServis");
		
	}
	
	public void testWriteNewAppMetadata(){
		
		try {
			
			List<MdRasterThemeStyle> rasterThemeStyles_1 = new ArrayList<MdRasterThemeStyle>();
			MdRasterThemeStyle rasterThemeStyle_1 = new MdRasterThemeStyle();
			rasterThemeStyle_1.setStyleName("Raster_Style_1");
			rasterThemeStyles_1.add(rasterThemeStyle_1);
			MdRasterThemeStyle rasterThemeStyle_2 = new MdRasterThemeStyle();
			rasterThemeStyle_2.setStyleName("Raster_Style_2");
			rasterThemeStyles_1.add(rasterThemeStyle_2);
			MdRasterThemeStyle rasterThemeStyle_3 = new MdRasterThemeStyle();
			rasterThemeStyle_3.setStyleName("Raster_Style_3");
			rasterThemeStyles_1.add(rasterThemeStyle_3);
			
			List<MdRasterThemeStyle> rasterThemeStyles_2 = new ArrayList<MdRasterThemeStyle>();
			MdRasterThemeStyle rasterThemeStyle_4 = new MdRasterThemeStyle();
			rasterThemeStyle_4.setStyleName("Raster_Style_4");
			rasterThemeStyles_2.add(rasterThemeStyle_4);
			MdRasterThemeStyle rasterThemeStyle_5 = new MdRasterThemeStyle();
			rasterThemeStyle_5.setStyleName("Raster_Style_5");
			rasterThemeStyles_2.add(rasterThemeStyle_5);
			MdRasterThemeStyle rasterThemeStyle_6 = new MdRasterThemeStyle();
			rasterThemeStyle_6.setStyleName("Raster_Style_6");
			rasterThemeStyles_2.add(rasterThemeStyle_6);
			
			List<MdRasterThemeStyle> rasterThemeStyles_3 = new ArrayList<MdRasterThemeStyle>();
			MdRasterThemeStyle rasterThemeStyle_7 = new MdRasterThemeStyle();
			rasterThemeStyle_7.setStyleName("Raster_Style_7");
			rasterThemeStyles_3.add(rasterThemeStyle_7);
			MdRasterThemeStyle rasterThemeStyle_8 = new MdRasterThemeStyle();
			rasterThemeStyle_8.setStyleName("Raster_Style_8");
			rasterThemeStyles_3.add(rasterThemeStyle_8);
			MdRasterThemeStyle rasterThemeStyle_9 = new MdRasterThemeStyle();
			rasterThemeStyle_9.setStyleName("Raster_Style_9");
			rasterThemeStyles_3.add(rasterThemeStyle_9);
			
			List<MdFeatureThemeStyle> featureThemeStyles_1 = new ArrayList<MdFeatureThemeStyle>();
			MdFeatureThemeStyle featureThemeStyle_1 = new MdFeatureThemeStyle();
			featureThemeStyle_1.setStyleName("Feature_Style_1");
			featureThemeStyle_1.setPointStyleID(1);
			featureThemeStyle_1.setLineStyleID(2);		
			featureThemeStyle_1.setPolygonStyleID(3);
			featureThemeStyles_1.add(featureThemeStyle_1);
			MdFeatureThemeStyle featureThemeStyle_2 = new MdFeatureThemeStyle();
			featureThemeStyle_2.setStyleName("Feature_Style_2");
			featureThemeStyle_2.setPointStyleID(1);
			featureThemeStyle_2.setLineStyleID(2);		
			featureThemeStyle_2.setPolygonStyleID(3);
			featureThemeStyles_1.add(featureThemeStyle_2);
			MdFeatureThemeStyle featureThemeStyle_3 = new MdFeatureThemeStyle();
			featureThemeStyle_3.setStyleName("Feature_Style_3");
			featureThemeStyle_3.setPointStyleID(1);
			featureThemeStyle_3.setLineStyleID(2);		
			featureThemeStyle_3.setPolygonStyleID(3);
			featureThemeStyles_1.add(featureThemeStyle_3);
			
			List<MdFeatureThemeStyle> featureThemeStyles_2 = new ArrayList<MdFeatureThemeStyle>();
			MdFeatureThemeStyle featureThemeStyle_4 = new MdFeatureThemeStyle();
			featureThemeStyle_4.setStyleName("Feature_Style_4");
			featureThemeStyle_4.setPointStyleID(1);
			featureThemeStyle_4.setLineStyleID(2);		
			featureThemeStyle_4.setPolygonStyleID(3);
			featureThemeStyles_2.add(featureThemeStyle_4);
			MdFeatureThemeStyle featureThemeStyle_5 = new MdFeatureThemeStyle();
			featureThemeStyle_5.setStyleName("Feature_Style_5");
			featureThemeStyle_5.setPointStyleID(1);
			featureThemeStyle_5.setLineStyleID(2);		
			featureThemeStyle_5.setPolygonStyleID(3);
			featureThemeStyles_2.add(featureThemeStyle_5);
			MdFeatureThemeStyle featureThemeStyle_6 = new MdFeatureThemeStyle();
			featureThemeStyle_6.setStyleName("Feature_Style_6");
			featureThemeStyle_6.setPointStyleID(1);
			featureThemeStyle_6.setLineStyleID(2);		
			featureThemeStyle_6.setPolygonStyleID(3);
			featureThemeStyles_2.add(featureThemeStyle_6);
			
			List<MdFeatureThemeStyle> featureThemeStyles_3 = new ArrayList<MdFeatureThemeStyle>();
			MdFeatureThemeStyle featureThemeStyle_7 = new MdFeatureThemeStyle();
			featureThemeStyle_7.setStyleName("Feature_Style_7");
			featureThemeStyle_7.setPointStyleID(1);
			featureThemeStyle_7.setLineStyleID(2);		
			featureThemeStyle_7.setPolygonStyleID(3);
			featureThemeStyles_3.add(featureThemeStyle_7);
			MdFeatureThemeStyle featureThemeStyle_8 = new MdFeatureThemeStyle();
			featureThemeStyle_8.setStyleName("Feature_Style_8");
			featureThemeStyle_8.setPointStyleID(1);
			featureThemeStyle_8.setLineStyleID(2);		
			featureThemeStyle_8.setPolygonStyleID(3);
			featureThemeStyles_3.add(featureThemeStyle_8);
			MdFeatureThemeStyle featureThemeStyle_9 = new MdFeatureThemeStyle();
			featureThemeStyle_9.setStyleName("Feature_Style_9");
			featureThemeStyle_9.setPointStyleID(1);
			featureThemeStyle_9.setLineStyleID(2);		
			featureThemeStyle_9.setPolygonStyleID(3);
			featureThemeStyles_3.add(featureThemeStyle_9);
			
			MdDatabaseRasterLayer databaseRasterLayer = new MdDatabaseRasterLayer();
			databaseRasterLayer.setDbName("NewDatabaseRasterLayer");
			databaseRasterLayer.setDbURL("AnyRasterURL");
			databaseRasterLayer.setMdRasterThemeStyles(rasterThemeStyles_1);
			
			MdFileBasedRasterLayer fileBasedRasterLayer = new MdFileBasedRasterLayer();
			fileBasedRasterLayer.setFileURL("AnyRasterURL");
			fileBasedRasterLayer.setFileFormat(1);
			fileBasedRasterLayer.setMdRasterThemeStyles(rasterThemeStyles_2);
			
			MdRemoteRasterLayer remoteRasterLayer = new MdRemoteRasterLayer();
			remoteRasterLayer.setRemoteDSUrl("AnyRemoteURL");
			remoteRasterLayer.setQueryPage("ExtraDirectory");
			remoteRasterLayer.setMdRasterThemeStyles(rasterThemeStyles_3);
			
			MdDatabaseFeatureLayer databaseFeatureLayer = new MdDatabaseFeatureLayer();
			databaseFeatureLayer.setDbName("NewDatabaseFeatureLayer");
			databaseFeatureLayer.setDbURL("AnyFeatureURL");
			databaseFeatureLayer.setMdFeatureThemeStyles(featureThemeStyles_1);
			
			MdFileBasedFeatureLayer fileBasedFeatureLayer = new MdFileBasedFeatureLayer();
			fileBasedFeatureLayer.setFileURL("AnyFeatureURL");
			fileBasedFeatureLayer.setFileFormat(1);
			fileBasedFeatureLayer.setMdFeatureThemeStyles(featureThemeStyles_2);
			
			MdRemoteFeatureLayer remoteFeatureLayer = new MdRemoteFeatureLayer();
			remoteFeatureLayer.setRemoteDSUrl("AnyRemoteURL");
			remoteFeatureLayer.setQueryPage("ExtraDirectory");
			remoteFeatureLayer.setMdFeatureThemeStyles(featureThemeStyles_3);
			
			MdFeatureLayer featureLayer_1 = new MdFeatureLayer();
			featureLayer_1.setLayerSubType(MetadataAppStaticVars.DATABASE_LAYER);
			featureLayer_1.setMdDatabaseFeatureLayer(databaseFeatureLayer);
			
			MdFeatureLayer featureLayer_2 = new MdFeatureLayer();
			featureLayer_2.setLayerSubType(MetadataAppStaticVars.FILEBASED_LAYER);
			featureLayer_2.setMdFileBasedFeatureLayer(fileBasedFeatureLayer);
			
			MdFeatureLayer featureLayer_3 = new MdFeatureLayer();
			featureLayer_3.setLayerSubType(MetadataAppStaticVars.REMOTE_LAYER);
			featureLayer_3.setMdRemoteFeatureLayer(remoteFeatureLayer);
			
			MdRasterLayer rasterLayer_1 = new MdRasterLayer();
			rasterLayer_1.setLayerSubType(MetadataAppStaticVars.DATABASE_LAYER);
			rasterLayer_1.setRasterWfsEquvalent("Wfs_Layer_1");
			rasterLayer_1.setMdDatabaseRasterLayer(databaseRasterLayer);
			
			MdRasterLayer rasterLayer_2 = new MdRasterLayer();
			rasterLayer_2.setLayerSubType(MetadataAppStaticVars.FILEBASED_LAYER);
			rasterLayer_2.setRasterWfsEquvalent("Wfs_Layer_2");
			rasterLayer_2.setMdFileBasedRasterLayer(fileBasedRasterLayer);
			
			MdRasterLayer rasterLayer_3 = new MdRasterLayer();
			rasterLayer_3.setLayerSubType(MetadataAppStaticVars.REMOTE_LAYER);
			rasterLayer_3.setRasterWfsEquvalent("Wfs_Layer_3");
			rasterLayer_3.setMdRemoteRasterLayer(remoteRasterLayer);
			
			MdLayer layer_1 = new MdLayer();
			layer_1.setLayerName("Layer_1");
			layer_1.setLayerType(MetadataAppStaticVars.FEATURE_LAYER);
			layer_1.setMdFeatureLayer(featureLayer_1);
			
			MdLayer layer_2 = new MdLayer();
			layer_2.setLayerName("Layer_2");
			layer_2.setLayerType(MetadataAppStaticVars.FEATURE_LAYER);
			layer_2.setMdFeatureLayer(featureLayer_2);
			
			MdLayer layer_3 = new MdLayer();
			layer_3.setLayerName("Layer_3");
			layer_3.setLayerType(MetadataAppStaticVars.FEATURE_LAYER);
			layer_3.setMdFeatureLayer(featureLayer_3);
			
			MdLayer layer_4 = new MdLayer();
			layer_4.setLayerName("Layer_4");
			layer_4.setLayerType(MetadataAppStaticVars.RASTER_LAYER);
			layer_4.setMdRasterLayer(rasterLayer_1);
			
			MdLayer layer_5 = new MdLayer();
			layer_5.setLayerName("Layer_5");
			layer_5.setLayerType(MetadataAppStaticVars.RASTER_LAYER);
			layer_5.setMdRasterLayer(rasterLayer_2);
			
			MdLayer layer_6 = new MdLayer();
			layer_6.setLayerName("Layer_6");
			layer_6.setLayerType(MetadataAppStaticVars.RASTER_LAYER);
			layer_6.setMdRasterLayer(rasterLayer_3);
			
			MdLayerAttributeVisibleName attributeVisibleName_1 = new MdLayerAttributeVisibleName();
			attributeVisibleName_1.setAttributeName("Name_1");
			attributeVisibleName_1.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_2 = new MdLayerAttributeVisibleName();
			attributeVisibleName_2.setAttributeName("ID_1");
			attributeVisibleName_2.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_3 = new MdLayerAttributeVisibleName();
			attributeVisibleName_3.setAttributeName("Description_1");
			attributeVisibleName_3.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_4 = new MdLayerAttributeVisibleName();
			attributeVisibleName_4.setAttributeName("Name_2");
			attributeVisibleName_4.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_5 = new MdLayerAttributeVisibleName();
			attributeVisibleName_5.setAttributeName("ID_2");
			attributeVisibleName_5.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_6 = new MdLayerAttributeVisibleName();
			attributeVisibleName_6.setAttributeName("Description_2");
			attributeVisibleName_6.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_7 = new MdLayerAttributeVisibleName();
			attributeVisibleName_7.setAttributeName("Name_3");
			attributeVisibleName_7.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_8 = new MdLayerAttributeVisibleName();
			attributeVisibleName_8.setAttributeName("ID_3");
			attributeVisibleName_8.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_9 = new MdLayerAttributeVisibleName();
			attributeVisibleName_9.setAttributeName("Description_3");
			attributeVisibleName_9.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_10 = new MdLayerAttributeVisibleName();
			attributeVisibleName_10.setAttributeName("Name_4");
			attributeVisibleName_10.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_11 = new MdLayerAttributeVisibleName();
			attributeVisibleName_11.setAttributeName("ID_4");
			attributeVisibleName_11.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_12 = new MdLayerAttributeVisibleName();
			attributeVisibleName_12.setAttributeName("Description_4");
			attributeVisibleName_12.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_13 = new MdLayerAttributeVisibleName();
			attributeVisibleName_13.setAttributeName("Name_5");
			attributeVisibleName_13.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_14 = new MdLayerAttributeVisibleName();
			attributeVisibleName_14.setAttributeName("ID_5");
			attributeVisibleName_14.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_15 = new MdLayerAttributeVisibleName();
			attributeVisibleName_15.setAttributeName("Description_5");
			attributeVisibleName_15.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_16 = new MdLayerAttributeVisibleName();
			attributeVisibleName_16.setAttributeName("Name_6");
			attributeVisibleName_16.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_17 = new MdLayerAttributeVisibleName();
			attributeVisibleName_17.setAttributeName("ID_6");
			attributeVisibleName_17.setAttributeVisibility(true);
			
			MdLayerAttributeVisibleName attributeVisibleName_18 = new MdLayerAttributeVisibleName();
			attributeVisibleName_18.setAttributeName("Description_6");
			attributeVisibleName_18.setAttributeVisibility(true);
	
			List<MdLayerAttributeVisibleName> attributeVisibleNames_1 = new ArrayList<MdLayerAttributeVisibleName>();
			attributeVisibleNames_1.add(attributeVisibleName_1);
			attributeVisibleNames_1.add(attributeVisibleName_2);
			attributeVisibleNames_1.add(attributeVisibleName_3);
			
			List<MdLayerAttributeVisibleName> attributeVisibleNames_2 = new ArrayList<MdLayerAttributeVisibleName>();
			attributeVisibleNames_2.add(attributeVisibleName_4);
			attributeVisibleNames_2.add(attributeVisibleName_5);
			attributeVisibleNames_2.add(attributeVisibleName_6);
			
			List<MdLayerAttributeVisibleName> attributeVisibleNames_3 = new ArrayList<MdLayerAttributeVisibleName>();
			attributeVisibleNames_3.add(attributeVisibleName_7);
			attributeVisibleNames_3.add(attributeVisibleName_8);
			attributeVisibleNames_3.add(attributeVisibleName_9);
			
			List<MdLayerAttributeVisibleName> attributeVisibleNames_4 = new ArrayList<MdLayerAttributeVisibleName>();
			attributeVisibleNames_4.add(attributeVisibleName_10);
			attributeVisibleNames_4.add(attributeVisibleName_11);
			attributeVisibleNames_4.add(attributeVisibleName_12);
			
			List<MdLayerAttributeVisibleName> attributeVisibleNames_5 = new ArrayList<MdLayerAttributeVisibleName>();
			attributeVisibleNames_5.add(attributeVisibleName_13);
			attributeVisibleNames_5.add(attributeVisibleName_14);
			attributeVisibleNames_5.add(attributeVisibleName_15);
			
			List<MdLayerAttributeVisibleName> attributeVisibleNames_6 = new ArrayList<MdLayerAttributeVisibleName>();
			attributeVisibleNames_6.add(attributeVisibleName_16);
			attributeVisibleNames_6.add(attributeVisibleName_17);
			attributeVisibleNames_6.add(attributeVisibleName_18);
			
			List<MdAppLayer> appLayers = new ArrayList<MdAppLayer>();
			MdAppLayer appLayer_1 = new MdAppLayer();
			appLayer_1.setLayerDescForApp("App_Layer_1");
			appLayer_1.setLayerPriority(1);
			appLayer_1.setMdLayerAttributeVisibleNames(attributeVisibleNames_1);
			appLayer_1.setMdLayer(layer_1);
			appLayers.add(appLayer_1);
			
			MdAppLayer appLayer_2 = new MdAppLayer();
			appLayer_2.setLayerDescForApp("App_Layer_2");
			appLayer_2.setLayerPriority(2);
			appLayer_2.setMdLayerAttributeVisibleNames(attributeVisibleNames_2);
			appLayer_2.setMdLayer(layer_2);
			appLayers.add(appLayer_2);
			
			MdAppLayer appLayer_3 = new MdAppLayer();
			appLayer_3.setLayerDescForApp("App_Layer_3");
			appLayer_3.setLayerPriority(3);
			appLayer_3.setMdLayerAttributeVisibleNames(attributeVisibleNames_3);
			appLayer_3.setMdLayer(layer_3);
			appLayers.add(appLayer_3);
			
			MdAppLayer appLayer_4 = new MdAppLayer();
			appLayer_4.setLayerDescForApp("App_Layer_4");
			appLayer_4.setLayerPriority(4);
			appLayer_4.setMdLayerAttributeVisibleNames(attributeVisibleNames_4);
			appLayer_4.setMdLayer(layer_4);
			appLayers.add(appLayer_4);
			
			MdAppLayer appLayer_5 = new MdAppLayer();
			appLayer_5.setLayerDescForApp("App_Layer_5");
			appLayer_5.setLayerPriority(5);
			appLayer_5.setMdLayerAttributeVisibleNames(attributeVisibleNames_5);
			appLayer_5.setMdLayer(layer_5);
			appLayers.add(appLayer_5);
			
			MdAppLayer appLayer_6 = new MdAppLayer();
			appLayer_6.setLayerDescForApp("App_Layer_6");
			appLayer_6.setLayerPriority(6);
			appLayer_6.setMdLayerAttributeVisibleNames(attributeVisibleNames_6);
			appLayer_6.setMdLayer(layer_6);
			appLayers.add(appLayer_6);
			
			MdSmpGISApp smpGISApp = new MdSmpGISApp();
			smpGISApp.setAppName("Application_1");
			smpGISApp.setAppDescriptor("Description");
			smpGISApp.setMdAppLayers(appLayers);
			
			geolsaServis.writeCBSUygulamalariMetadata(smpGISApp);
		
			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : Metadata Service Create New Full Metadata JUnit Test");
			System.out.println("Eklenen Uygulama Metadatası Adı : " + smpGISApp.getAppName());			
			System.out.println("**********************************************************************" + "\n");
			////////////////////////////////////////////////////////////////////////////////////////////
			
			//Test criteria		
			assertNotNull(smpGISApp);
			
		} catch (Exception ex) {
			System.out.println("Error on writing metadata");
		}
	}
	
	public void testReadAppMetadata() {
			
			try { 
				
				List<MdSmpGISApp> allAppMetadata = geolsaServis.readAllCBSUygulamalariMetadata();
				
				////////////////////////////////////////////////////////////////////////////////////////////
				//Check results
				System.out.println("======================================================================");
				System.out.println("Test Adi : Metadata Service Read All App Metadata JUnit Test");
				System.out.println("Kayıtlı Uygulama Metadata Sayısı : " + allAppMetadata.size());	
				System.out.println("**********************************************************************" + "\n");
				////////////////////////////////////////////////////////////////////////////////////////////
				
				//Test criteria		
				assertNotSame(0, allAppMetadata.size());
				
				
				MdSmpGISApp sampleSmpGISApp = new MdSmpGISApp();
				sampleSmpGISApp.setOid(allAppMetadata.get(0).getOid());
				
				MdSmpGISApp appMetadata = geolsaServis.readCBSUygulamalariMetadataByCriteria(sampleSmpGISApp);
				
				////////////////////////////////////////////////////////////////////////////////////////////
				//Check results
				System.out.println("======================================================================");
				System.out.println("Test Adi : Metadata Service Read App Metadata By Criteria JUnit Test");
				System.out.println("Uygulama Metadatası Adı : " + appMetadata.getAppName());	
				System.out.println("Uygulama Metadatasındaki Katman Sayısı : " + appMetadata.getMdAppLayers().size());	
				System.out.println("**********************************************************************" + "\n");
				////////////////////////////////////////////////////////////////////////////////////////////
				
				//Test criteria		
				assertNotSame(null, appMetadata);
				

				
			} catch (Exception ex) {
				System.out.println("Error on reading application metadata : " + ex);
			}
	}
	
	public void testUpdateAppMetadata() {
		
		try {
			
			List<MdSmpGISApp> allAppMetadata = geolsaServis.readAllCBSUygulamalariMetadata();
			
			allAppMetadata.get(0).setAppName("Updated_Metadata");
			geolsaServis.updateCBSUygulamalariMetadata(allAppMetadata.get(0));
			
			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : Metadata Service Update App Metadata By Criteria JUnit Test");
			System.out.println("Güncellenen Metadata Yeni Adı : " + allAppMetadata.get(0).getAppName());	
			System.out.println("**********************************************************************" + "\n");
			////////////////////////////////////////////////////////////////////////////////////////////
			
			//Test criteria		
			assertNotSame("", allAppMetadata.get(0).getAppName());
			
		} catch (Exception ex) {
			System.out.println("Error on updating application metadata : " + ex);
		}
	}
	
	public void testDeleteAppMetadata() {
		
		try { 

			List<MdSmpGISApp> allAppMetadata = geolsaServis.readAllCBSUygulamalariMetadata();
			
			if (allAppMetadata.get(0)!= null) {
				
				geolsaServis.deleteCBSUygulamalariMetadata(allAppMetadata.get(0));
				
				////////////////////////////////////////////////////////////////////////////////////////////
				//Check results
				System.out.println("======================================================================");
				System.out.println("Test Adi : Metadata Service Delete App Metadata JUnit Test");
				System.out.println("Silinen Metadata Adı : " + allAppMetadata.get(0).getAppName());	
				System.out.println("**********************************************************************" + "\n");
				////////////////////////////////////////////////////////////////////////////////////////////
				
				//Test criteria		
				assertNotSame(null, allAppMetadata.get(0));
				
			} else {
				
				System.out.println("Error on finding metadata for delete");
			}
			
		} catch (Exception ex) {
			System.out.println("Error on deleting application metadata : " + ex);
		}
	}
	
}
