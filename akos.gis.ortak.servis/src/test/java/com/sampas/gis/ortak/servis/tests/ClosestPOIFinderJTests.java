package com.sampas.gis.ortak.servis.tests;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import junit.framework.TestCase;
import com.sampas.gis.ortak.poi.search.servis.IClosestPOIFinder;
import com.sampas.gis.ortak.poi.search.servis.impl.AttributeListItem;
import com.sampas.gis.ortak.poi.search.servis.impl.ClosestPOIFinder;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.provider.services.impl.WFSDataStoreSrv;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.data.wfs.impl.WFSDataStore.WfsGMLVersion;
import com.sampas.socbs.core.data.wfs.impl.WFSDataStore.WfsVersion;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpPoint;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayerAttribute;
import com.sampas.socbs.core.test.tools.TestServerMetaData;


public class ClosestPOIFinderJTests extends TestCase {

  	private URL wfsUrl = null;
	
	private String selectLayerName = "GRF_ONEMLI_YERLER";
	
  	private TestServerMetaData testServerMetaData = null;
  	
  	IFeatureLayer impPlacesLayer;
	
	public void globals() {
		
		try {
			
			testServerMetaData = new TestServerMetaData();
			
			this.wfsUrl = new URL(testServerMetaData.getWfsServerAddress());
			
			WFSDataStoreSrv wfsDataStoreSrv = new WFSDataStoreSrv(this.wfsUrl, WfsGMLVersion.gml2, WfsVersion.ver100);
			
			IFeatureDataStore dataProvider = wfsDataStoreSrv.getWfsDataStore();
			
			IFeatureLayerProvider layerProvider = new SmpFeatureLayerProviderSrv();
			
			List<IFeatureLayer> smpLayers = layerProvider.getLayers(dataProvider);
			
			for (int i = 0; i < smpLayers.size(); i++) {
				
				//TODO GRF_ONEMLI_YERLER must come from a metadata
				if(smpLayers.get(i).getName().contains(selectLayerName)) {
					
					impPlacesLayer = smpLayers.get(i);
				}
			}
			
		} catch(Exception ex)  {
			
			System.out.println("Error on getting required objects !");
			
		}
	}
	
	public void testPOIFinderPharmacy() {
		
		globals();
		
		IClosestPOIFinder poiFinder = new ClosestPOIFinder();
		
		IFeatureLayer layer = impPlacesLayer;
		
		ICoordinateSystem coordinateSystem = new SmpCoordinateSystem("EPSG:2320");
		
		//IPoint referancePoint = new SmpPoint(414570, 4544835, coordinateSystem);
		
		IPoint referancePoint = new SmpPoint(412334.5100877482, 4546948.459173043, coordinateSystem);
		
		HashMap<ILayerAttribute, Object> attributes = new HashMap<ILayerAttribute, Object>();
		
		ILayerAttribute attributeOne = impPlacesLayer.getAttributeAt(6); //8
		ILayerAttribute attributeTwo = impPlacesLayer.getAttributeAt(4); //109
		ILayerAttribute attributeThree = impPlacesLayer.getAttributeAt(5); //44
		
		attributes.put(attributeOne, 8);
		attributes.put(attributeTwo, 109);
		attributes.put(attributeThree, 44);
		
		List<ILayerAttribute> attributeList = new ArrayList<ILayerAttribute>();
		attributeList.add(attributeOne);
		attributeList.add(attributeTwo);
		attributeList.add(attributeThree);
		
		IFeature result = poiFinder.findClosestPOI(layer, referancePoint, attributes, attributeList);
		
		/** Second way */
		List<AttributeListItem> attributeList2 = new ArrayList<AttributeListItem>(); 
		attributeList2.add(new AttributeListItem(attributeOne, 8));
		attributeList2.add(new AttributeListItem(attributeTwo, 109));
		attributeList2.add(new AttributeListItem(attributeThree, 44));
		IFeature result2 = poiFinder.findClosestPOI(layer, referancePoint, attributeList2);
		
		////////////////////////////////////////////////////////////////////////////////////////////
		//Check results
		System.out.println("======================================================================");
		System.out.println("Test Adi : POI Finder, Closest Pharmacy JUnit Test");
		System.out.println("Sorgulanan Katman Adı : " + layer.getName());
		System.out.println("Bulunan en yakin ilgi alani aciklamasi : " + result.getAttribute("ACIKLAMA"));
		System.out.println("Ikinci fonksiyon ile bulunan en yakin ilgi alani aciklamasi : " + result2.getAttribute("ACIKLAMA"));
		System.out.println("**********************************************************************" + "\n");
		////////////////////////////////////////////////////////////////////////////////////////////
		
		//Test criteria		
		assertNotSame(0, result.getAttribute("ACIKLAMA"));
		
	}
	
	public void testPOIFinderHospital() {
		
		globals();
		
		IClosestPOIFinder poiFinder = new ClosestPOIFinder();
		
		IFeatureLayer layer = impPlacesLayer;
		
		ICoordinateSystem coordinateSystem = new SmpCoordinateSystem("EPSG:2320");
		
		IPoint referancePoint = new SmpPoint(414570, 4544835, coordinateSystem);
		
		HashMap<ILayerAttribute, Object> attributes = new HashMap<ILayerAttribute, Object>();
		
		ILayerAttribute attributeOne = impPlacesLayer.getAttributeAt(6); //Kullanim Sinif Kodu
		ILayerAttribute attributeTwo = impPlacesLayer.getAttributeAt(5); //Kullanim Detay Kodu
		
		attributes.put(attributeOne, 8);
		attributes.put(attributeTwo, 45);
		
		List<ILayerAttribute> attributeList = new ArrayList<ILayerAttribute>();
		attributeList.add(attributeOne);
		attributeList.add(attributeTwo);
		
		IFeature result = poiFinder.findClosestPOI(layer, referancePoint, attributes, attributeList);
		
		////////////////////////////////////////////////////////////////////////////////////////////
		//Check results
		System.out.println("======================================================================");
		System.out.println("Test Adi : POI Finder, Closest Hospital JUnit Test");
		System.out.println("Sorgulanan Katman Adı : " + layer.getName());
		System.out.println("Bulunan en yakin ilgi alani aciklamasi : " + result.getAttribute("ACIKLAMA"));
		System.out.println("**********************************************************************" + "\n");
		////////////////////////////////////////////////////////////////////////////////////////////
		
		//Test criteria		
		assertNotSame(0, result.getAttribute("ACIKLAMA"));
		
	}
	
}
