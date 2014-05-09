package com.sampas.socbs.core.tile.tests;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import junit.framework.TestCase;

import com.sampas.socbs.core.data.ITileDataStore;
import com.sampas.socbs.core.data.provider.services.impl.TileDataStoreSrv;
import com.sampas.socbs.core.data.providers.ITileLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpTileLayerProviderSrv;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.geometry.impl.SmpDimension;
import com.sampas.socbs.core.maplayer.ITileLayer;
import com.sampas.socbs.core.symbology.IStyle;
import com.sampas.socbs.core.symbology.impl.SmpStyle;
import com.sampas.socbs.core.test.tools.TestServerMetaData;
import com.sampas.socbs.core.tile.impl.TileDataStore.TileImageReturnType;
import com.sampas.socbs.core.tile.impl.TileDataStore.TileServerVersion;
import com.sampas.socbs.core.tile.toolbox.BBOX;
import com.sampas.socbs.core.tile.toolbox.SRS;

public class TilingJTests extends TestCase {

	private URL tileServerUrl = null;

	private TestServerMetaData testServerMetaData = new TestServerMetaData();

	private TileServerVersion tileServerVersion = TileServerVersion.ver111;

	private String selectedLayer = "smpboglu:p_grf_bina";

	private String defaultSRS = "EPSG:4326";

	public void testTileRequester() {

		try {

			this.tileServerUrl = new URL(testServerMetaData.getTileServerAddress());

			TileDataStoreSrv tileDataStoreCreatorSrv = new TileDataStoreSrv(this.tileServerUrl, tileServerVersion, TileImageReturnType.png, 0, true);

			ITileDataStore tileDataProvider = tileDataStoreCreatorSrv.getTileDataStore();

			ITileLayerProvider tileLayerProvider = new SmpTileLayerProviderSrv();

			List<ITileLayer> tileLayers = tileLayerProvider.getLayers(tileDataProvider);

			System.out.println("--------------------------------------------------");
			System.out.println("Test Name Tiling Jtest");
			System.out.println("Bulunan katman sayisi : " + tileLayers.size());

			ITileLayer tileLayer = null;

			for (int i = 0; i < tileLayers.size(); i++) {

				if(tileLayers.get(i).getName().equals(selectedLayer)) {

					tileLayer = tileLayers.get(i);
					System.out.println("Selected Layer : " + selectedLayer);
				}
			}

			int layerZoomLevel = 17; // it must come from application
			IDimension tileDimension = new SmpDimension(256, 256);
			
			SRS defSRS = SRS.getSRS(defaultSRS);

			// Example bbox for testing 
						double targetBBoxMinX = 28.95751094;
						double targetBBoxMinY = 41.04139901;
						double targetBBoxMaxX = 28.96482083;
						double targetBBoxMaxY = 41.04512771;


//			double targetBBoxMinX = 28.930094269394992;
//			double targetBBoxMinY = 41.02074600672384;
//			double targetBBoxMaxX = 29.00466706883722;
//			double targetBBoxMaxY = 41.065128850262845;

			//layerZoomLevel = calculateZoom(targetBBoxMinX, targetBBoxMaxX, targetBBoxMinY, targetBBoxMaxY, 800, 600);		
				
			System.out.println("Layer Zoom Level: " + layerZoomLevel);
						
			BBOX targetBBox = new BBOX(targetBBoxMinX, targetBBoxMinY, targetBBoxMaxX, targetBBoxMaxY);

			int[] tileBounds = tileDataProvider.getTileBoundsFromBBox(tileLayer, defSRS, targetBBox, layerZoomLevel);

			List<ITileLayer> requestLayers = new ArrayList<ITileLayer>();

			requestLayers.add(tileLayers.get(21));
			requestLayers.add(tileLayers.get(18));
			requestLayers.add(tileLayers.get(0));
			requestLayers.add(tileLayers.get(2));
			requestLayers.add(tileLayers.get(10));
			requestLayers.add(tileLayers.get(3));
			requestLayers.add(tileLayers.get(1));
			requestLayers.add(tileLayers.get(8));
			requestLayers.add(tileLayers.get(5));
			
			List<IStyle> styles = new ArrayList<IStyle>();

			styles.add(new SmpStyle(""));
			styles.add(new SmpStyle(""));
			styles.add(new SmpStyle(""));
			styles.add(new SmpStyle(""));
			styles.add(new SmpStyle(""));
			styles.add(new SmpStyle(""));
			styles.add(new SmpStyle(""));
			styles.add(new SmpStyle(""));
			styles.add(new SmpStyle(""));

			TileImageReturnType returnType = TileImageReturnType.png;

			int tileNoX = tileBounds[0];	
			int tileNoY = tileBounds[1];

			BufferedImage tileImage = null;//tileDataProvider.getTileWithTileLocation(requestLayers, defSRS, styles, tileNoX, tileNoY, layerZoomLevel, tileDimension, returnType);

			//System.out.println("Elde Edilen goruntunun genisligi ve yuksekligi : " + tileImage.getWidth() + ", " + tileImage.getHeight());

			//			//Example address it must come from web applications context because only context can know that that is the disk and url addresses
			//			File tileDiskAddressDirectory = new File("c:\\Tiles\\");
			//			
			//			String tileAddress = tileDataProvider.getTileAddressFromTileLocation(requestLayers, defSRS, styles, tileNoX, tileNoY, layerZoomLevel, tileDimension, returnType, tileDiskAddressDirectory, 0);
			//			
			//			System.out.println("Istenilen yere kaydedilen Tile'in dosya adi : " + tileAddress);
						System.out.println("--------------------------------------------------");

			tileImage = tileDataProvider.getTileWithTileLocation(requestLayers, defSRS, styles, tileNoX, tileNoY, layerZoomLevel, tileDimension, returnType);
			showImageFromBufferedImage(tileImage);

		} catch (Exception ex){

			System.out.println("Error on creating Tile Layer system error str: " + ex);
		}

	}

	public void showImageFromBufferedImage(BufferedImage imageBuffered) {

		if (imageBuffered != null) {

			try {

				ImageIcon imgIconDoc = new ImageIcon(imageBuffered);
				JFrame frame = new JFrame("WMS EXAMPLE MAP");
				JLabel label = new JLabel(imgIconDoc);
				frame.getContentPane().setLayout(new BorderLayout());
				frame.getContentPane().add(label, BorderLayout.CENTER);
				frame.setSize(256, 256);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocation(200, 200);
				frame.setVisible(true);
				Thread.currentThread();
				Thread.sleep(10000);
			} catch (Exception ex) {

				System.out.println("Error on creating image from byte array");
			}

		} else {

			System.out.println("Error on image input");
		}
	}

	public void showImageFromByteArray(byte[] imageByte) {

		if (imageByte.length != 0) {

			try {

				ImageIcon imgIconDoc = new ImageIcon(imageByte);
				JFrame frame = new JFrame("WMS EXAMPLE MAP");
				JLabel label = new JLabel(imgIconDoc);
				frame.getContentPane().setLayout(new BorderLayout());
				frame.getContentPane().add(label, BorderLayout.CENTER);
				frame.setSize(256, 256);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocation(200, 200);
				frame.setVisible(true);
				Thread.currentThread();
				Thread.sleep(10000);
			} catch (Exception ex) {

				System.out.println("Error on creating image from byte array");
			}

		} else {

			System.out.println("Error on image input");
		}
	}
		
	public Long[] fromLatLngToPxl(Double lat, Double lng, int zoom) {
		 
		Long[] result = new Long[2];
	 		
	 	Long[] pixelRange = new Long[19];
	 
	 	Long pixels = 256l;
	 	Long origin;
	 
	 	Long[] pixelsPerLonDegree = new Long[19];
	 	Long[] pixelsPerLonRadian = new Long[19];
	 
	 	Long o = 0l;
	 		
	 	for (Integer z = 0; z <= 18; z++) {
	 		origin = pixels / 2;
	 		pixelsPerLonDegree[z] = pixels / 360;
	 		pixelsPerLonRadian[z] = ((Double) (pixels / (2 * Math.PI))).longValue();
	 		//pixelOrigo[z][0] = origin;
	 		//pixelOrigo[z][1] = origin;
	 		if (z==zoom) {
	 			o = origin;	
	 			break;
	 		}
	 		pixelRange[z] = pixels;
	 	    pixels *= 2; 
	 	}
	 		
	 	result[0] = Math.round(o.doubleValue() + lng * pixelsPerLonDegree[zoom].doubleValue());
	 	Double siny = Math.sin(Math.toRadians(lat));
	 	siny = (siny < -0.9999) ? -0.9999 : (siny > 0.9999) ? 0.9999 : siny;
	 	result[1] = Math.round(o + 0.5 * Math.log((1 + siny) / (1 - siny)) * -pixelsPerLonRadian[zoom]);
	 	return result;
	 }
	 	
 	public Integer calculateZoom(Double minLng, Double maxLng, Double minLat, Double maxLat, int mapWidth, int mapHeight) {
 		
 		Long[] pixelRange = new Long[19];
 		Long pixels = 256l;
 
 		for (Integer z = 0; z <= 18; z++) {
 			pixelRange[z] = pixels;
 	     	pixels *= 2; 
 		}
 		
 		for (Integer z = 18; z > 0; z--) {
 			Long[] btmLeftPxl = fromLatLngToPxl(minLng, minLat, z);
 			Long[] topRghtPxl = fromLatLngToPxl(maxLng, maxLat, z);
 			if (btmLeftPxl[0] > topRghtPxl[0])
 				btmLeftPxl[0] -= pixelRange[z];
 			if (Math.abs(topRghtPxl[0] - btmLeftPxl[0]) <= mapWidth && Math.abs(topRghtPxl[1] - btmLeftPxl[1]) <= mapHeight)
 				return z;
 		}
 		return 0;
 	}

}
