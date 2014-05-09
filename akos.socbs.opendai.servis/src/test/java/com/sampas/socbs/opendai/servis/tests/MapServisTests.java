package com.sampas.socbs.opendai.servis.tests;

import java.awt.image.BufferedImage;
import com.sampas.akos.common.test.BaseTestCase;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.opendai.servis.IMapServis;
import com.sampas.socbs.opendai.servis.tools.ImageTestViewer;


public class MapServisTests extends BaseTestCase {

	private IMapServis mapServis = null;
	
	private ImageTestViewer imageTestViewer = new ImageTestViewer();
	
	private final static int DEFAULT_MAP_IMG_WIDHT = 300;
	
	private final static int DEFAULT_MAP_IMG_HEIGHT = 300;
	
	private final static int DEFAULT_MAP_IMG_SHOW_TIME = 10000;
	
	protected void onSetUpInTransaction() throws Exception {
		
		setComplete();
		
		mapServis = (IMapServis)applicationContext.getBean("mapServis");
	}

	@Override
	protected String[] getXmlDataFileResource() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "com/sampas/akos/commonTestContext.xml" };
	}
	
	public void testMaximumZoomLevel() {
		
		System.out.println("-------------------- Maximum Zoom Level Test --------------------");
		System.out.println("Maximum Zoom Level = " + mapServis.getMaxZoomLevel());
		System.out.println("-----------------------------------------------------------------");
	}
	
	public void testGetMapAsByteArr() {
		
		byte[] mapBytes = this.mapServis.getMapAsByteArr(28.96165739, 41.04310553, DEFAULT_MAP_IMG_WIDHT, DEFAULT_MAP_IMG_HEIGHT, 5);
		
		System.out.println("-------------------- Get Map As Byte Array Test --------------------");
		
		System.out.println("Alinan Harita Buyuklugu = " + mapBytes.length + " Bytes");
		
		System.out.println("--------------------------------------------------------------------");
		
		this.imageTestViewer.showImageFromByteArray(mapBytes, DEFAULT_MAP_IMG_WIDHT, DEFAULT_MAP_IMG_HEIGHT, DEFAULT_MAP_IMG_SHOW_TIME);
		
		assertNotSame(0, mapBytes.length);
	}
	
	public void testGetMapAsBfrImg() {
	
		BufferedImage mapBufferedImage = this.mapServis.getMapAsBfrImg(28.981812964, 41.03168475, DEFAULT_MAP_IMG_WIDHT, DEFAULT_MAP_IMG_HEIGHT, 2);
		
		System.out.println("-------------------- Get Map As Buffered Image Test --------------------");
		
		System.out.println("Alinan Harita Imgesi Genisligi = " + mapBufferedImage.getWidth());
		
		System.out.println("Alinan Harita Imgesi Yuksekligi = " + mapBufferedImage.getHeight());
		
		System.out.println("------------------------------------------------------------------------");
		
		this.imageTestViewer.showImageFromBufferedImage(mapBufferedImage, DEFAULT_MAP_IMG_WIDHT, DEFAULT_MAP_IMG_HEIGHT, DEFAULT_MAP_IMG_SHOW_TIME);
		
		assertNotSame(0, mapBufferedImage.getWidth());
	}

	public void testGetFeatureLayers() {
		
		IFeatureLayer addressFeatureLayer = this.mapServis.getAddressFeatureLayer();
		
		assertNotNull(addressFeatureLayer);
		
		IFeatureLayer impPlaceFeatureLayer = this.mapServis.getImportantPlaceFeatureLayer();
		
		assertNotNull(impPlaceFeatureLayer);
	}
	
}
