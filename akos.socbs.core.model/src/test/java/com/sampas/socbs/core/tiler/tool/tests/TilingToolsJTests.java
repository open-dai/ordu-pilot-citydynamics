package com.sampas.socbs.core.tiler.tool.tests;

import junit.framework.TestCase;

import com.sampas.socbs.core.tiler.tool.impl.TilingRequirements;
import com.sampas.socbs.core.tiler.tool.impl.TilingTool;

public class TilingToolsJTests extends TestCase {
	
	@SuppressWarnings("unused")
	private TilingTool tilingTools;
	
	public void testTilingMetadata() {

		TilingRequirements tilingMetadata = new TilingRequirements();

		assertNotSame("", tilingMetadata.getAppName());
		assertNotSame(null, tilingMetadata.getDBDataStore());
		assertNotSame(null, tilingMetadata.getDBDataStore());
		assertNotSame("", tilingMetadata.getWFSDataStore());
		assertNotSame("", tilingMetadata.getGWCCacheDirectory());
		assertNotSame(0, tilingMetadata.getLayerList().length);
		assertNotSame(0, tilingMetadata.getMaxPyramidLevel());
	}
	
	public void testMainMethod() {
		
		//TilingTool.main(new String[]{"noMatters"});
	}


}
