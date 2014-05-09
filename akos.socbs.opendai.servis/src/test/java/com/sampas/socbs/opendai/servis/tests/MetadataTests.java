package com.sampas.socbs.opendai.servis.tests;

import com.sampas.akos.common.test.BaseTestCase;
import com.sampas.socbs.opendai.servis.metadata.MapMetadata;


public class MetadataTests extends BaseTestCase {

	@Override
	protected String[] getXmlDataFileResource() {
		// TODO Auto-generated method stub
		return null;
	}

	MapMetadata mapMetadata = new MapMetadata();
	
	public void testMetadata() {
		
		System.out.println("----------------------------------------------------------------");
		System.out.println("Adres Katman No: " + mapMetadata.getLayerAddress());
		System.out.println("Mahalle Katman No: " + mapMetadata.getLayerDistrict());
		System.out.println("Cadde/Sokak Katman No: " + mapMetadata.getLayerStreet());
		System.out.println("Bina Katman No: " + mapMetadata.getLayerBuilding());
		System.out.println("Yapi Adasi Katman No: " + mapMetadata.getLayerBuildIsland());
		System.out.println("Onemli Yer Katman No: " + mapMetadata.getLayerImportantPlace());
		System.out.println("Raster Katman No: " + mapMetadata.getLayerRaster());
		System.out.println("----------------------------------------------------------------");
		
		assertNotSame(mapMetadata.getLayerAddress(), -1);
		assertNotSame(mapMetadata.getLayerDistrict(), -1);
		assertNotSame(mapMetadata.getLayerStreet(), -1);
		assertNotSame(mapMetadata.getLayerBuilding(), -1);
		assertNotSame(mapMetadata.getLayerImportantPlace(), -1);
		assertNotSame(mapMetadata.getLayerRaster(), -1);
	}

}
