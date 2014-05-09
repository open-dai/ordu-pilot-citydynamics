package com.sampas.socbs.poi.servis.tests;

import com.sampas.akos.common.test.BaseTestCase;
import com.sampas.socbs.poi.servis.IPOIServis;


public class POIServisTests extends BaseTestCase {


	private IPOIServis poiServis = null;
	
//	private ImageTestViewer imageTestViewer = new ImageTestViewer();
//	
//	private final static int DEFAULT_MAP_IMG_WIDHT = 300;
//	
//	private final static int DEFAULT_MAP_IMG_HEIGHT = 300;
//	
//	private final static int DEFAULT_MAP_IMG_SHOW_TIME = 10000;
	
	protected void onSetUpInTransaction() throws Exception {
		
		setComplete();
		
		poiServis = (IPOIServis)applicationContext.getBean("poiServis");
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
	
	public void testMaxZoomLevel() {
	
		System.out.println(poiServis.getMaxZoomLevel());
		
		assertNotSame(0, poiServis.getMaxZoomLevel());
	}
}
