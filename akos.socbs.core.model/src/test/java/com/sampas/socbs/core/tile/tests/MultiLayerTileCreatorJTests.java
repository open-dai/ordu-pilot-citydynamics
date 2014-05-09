package com.sampas.socbs.core.tile.tests;

import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import com.sampas.socbs.core.data.ITileDataStore;
import com.sampas.socbs.core.data.provider.services.impl.TileDataStoreSrv;
import com.sampas.socbs.core.data.providers.ITileLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpTileLayerProviderSrv;
import com.sampas.socbs.core.maplayer.ITileLayer;
import com.sampas.socbs.core.test.tools.TestServerMetaData;
import com.sampas.socbs.core.tile.impl.TileDataStore.TileImageReturnType;
import com.sampas.socbs.core.tile.impl.TileDataStore.TileServerVersion;
import com.sampas.socbs.core.tile.toolbox.SRS;

public class MultiLayerTileCreatorJTests extends TestCase {

	private URL tileServerUrl = null;

	private TestServerMetaData testServerMetaData = new TestServerMetaData();

	private TileServerVersion tileServerVersion = TileServerVersion.ver111;

	private String defaultSRS = "EPSG:4326";
	
	
	@SuppressWarnings("unused")
	public void testTileRequester() {

		try {

			this.tileServerUrl = new URL(testServerMetaData.getTileServerAddress());

			TileDataStoreSrv tileDataStoreCreatorSrv = new TileDataStoreSrv(this.tileServerUrl, tileServerVersion, TileImageReturnType.png, 0, true);

			ITileDataStore tileDataProvider = tileDataStoreCreatorSrv.getTileDataStore();

			ITileLayerProvider tileLayerProvider = new SmpTileLayerProviderSrv();

			List<ITileLayer> tileLayers = tileLayerProvider.getLayers(tileDataProvider);

			//select a layer
			ITileLayer selectedTileLayer =  tileLayers.get(0);
			
			ITileLayer tileLayer = null;
			
			tileLayer = selectedTileLayer;
			
			SRS defSRS = SRS.getSRS(defaultSRS);
			
//			BBOX targetBBox = new BBOX(selectedTileLayer.getExtent().getMinX(), selectedTileLayer.getExtent().getMinY(), selectedTileLayer.getExtent().getMax(), selectedTileLayer.getExtent().getMinY());
//			
//			int[] tileBounds = tileDataProvider.getTileBoundsFromBBox(tileLayer, defSRS, targetBBox, layerZoomLevel);
			
			//TODO not a finished test
			
			
			
			
//			System.out.println("--------------------------------------------------");
//			System.out.println("Test Name Tiling Jtest");
//			System.out.println("Bulunan katman sayisi : " + tileLayers.size());
//
//			ITileLayer tileLayer = null;
//
//			for (int i = 0; i < tileLayers.size(); i++) {
//
//				if(tileLayers.get(i).getName().equals(selectedLayer)) {
//
//					tileLayer = tileLayers.get(i);
//					System.out.println("Selected Layer : " + selectedLayer);
//				}
//			}
//
//			int layerZoomLevel = 17; // it must come from application
//			IDimension tileDimension = new SmpDimension(256, 256);
//			
//			SRS defSRS = SRS.getSRS(defaultSRS);
//
//			// Example bbox for testing 
//						double targetBBoxMinX = 28.95751094;
//						double targetBBoxMinY = 41.04139901;
//						double targetBBoxMaxX = 28.96482083;
//						double targetBBoxMaxY = 41.04512771;
//
//
////			double targetBBoxMinX = 28.930094269394992;
////			double targetBBoxMinY = 41.02074600672384;
////			double targetBBoxMaxX = 29.00466706883722;
////			double targetBBoxMaxY = 41.065128850262845;
//
//			//layerZoomLevel = calculateZoom(targetBBoxMinX, targetBBoxMaxX, targetBBoxMinY, targetBBoxMaxY, 800, 600);		
//				
//			System.out.println("Layer Zoom Level: " + layerZoomLevel);
//						
//			BBOX targetBBox = new BBOX(targetBBoxMinX, targetBBoxMinY, targetBBoxMaxX, targetBBoxMaxY);
//
//			int[] tileBounds = tileDataProvider.getTileBoundsFromBBox(tileLayer, defSRS, targetBBox, layerZoomLevel);
//
//			List<ITileLayer> requestLayers = new ArrayList<ITileLayer>();
//
//			requestLayers.add(tileLayers.get(21));
//			requestLayers.add(tileLayers.get(18));
//			requestLayers.add(tileLayers.get(0));
//			requestLayers.add(tileLayers.get(2));
//			requestLayers.add(tileLayers.get(10));
//			requestLayers.add(tileLayers.get(3));
//			requestLayers.add(tileLayers.get(1));
//			requestLayers.add(tileLayers.get(8));
//			requestLayers.add(tileLayers.get(5));
//			
//			List<IStyle> styles = new ArrayList<IStyle>();
//
//			styles.add(new SmpStyle(""));
//			styles.add(new SmpStyle(""));
//			styles.add(new SmpStyle(""));
//			styles.add(new SmpStyle(""));
//			styles.add(new SmpStyle(""));
//			styles.add(new SmpStyle(""));
//			styles.add(new SmpStyle(""));
//			styles.add(new SmpStyle(""));
//			styles.add(new SmpStyle(""));
//
//			TileImageReturnType returnType = TileImageReturnType.png;
//
//			int tileNoX = tileBounds[0];	
//			int tileNoY = tileBounds[1];
//
//			BufferedImage tileImage = null;//tileDataProvider.getTileWithTileLocation(requestLayers, defSRS, styles, tileNoX, tileNoY, layerZoomLevel, tileDimension, returnType);
//
//			//System.out.println("Elde Edilen goruntunun genisligi ve yuksekligi : " + tileImage.getWidth() + ", " + tileImage.getHeight());
//
//			//			//Example address it must come from web applications context because only context can know that that is the disk and url addresses
//			//			File tileDiskAddressDirectory = new File("c:\\Tiles\\");
//			//			
//			//			String tileAddress = tileDataProvider.getTileAddressFromTileLocation(requestLayers, defSRS, styles, tileNoX, tileNoY, layerZoomLevel, tileDimension, returnType, tileDiskAddressDirectory, 0);
//			//			
//			//			System.out.println("Istenilen yere kaydedilen Tile'in dosya adi : " + tileAddress);
//						System.out.println("--------------------------------------------------");
//
//			tileImage = tileDataProvider.getTileWithTileLocation(requestLayers, defSRS, styles, tileNoX, tileNoY, layerZoomLevel, tileDimension, returnType);
//			showImageFromBufferedImage(tileImage);

		} catch (Exception ex){

			System.out.println("Error on creating Tile Layer system error str: " + ex);
		}

	}
	
	
}
