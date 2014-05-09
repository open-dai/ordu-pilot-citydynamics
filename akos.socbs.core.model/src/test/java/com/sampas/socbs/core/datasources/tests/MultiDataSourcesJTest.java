package com.sampas.socbs.core.datasources.tests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import junit.framework.TestCase;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.data.IWMSDataStore;
import com.sampas.socbs.core.data.provider.services.impl.WMSDataStoreSrv;
import com.sampas.socbs.core.data.providers.IWMSLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpWMSLayerProviderSrv;
import com.sampas.socbs.core.data.wms.impl.GetMapResult;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsGMLVersion;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsImageReturnType;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsVersion;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.geometry.impl.SmpDimension;
import com.sampas.socbs.core.maplayer.IWMSLayer;
import com.sampas.socbs.core.style.IWMSNamedStyle;
import com.sampas.socbs.core.test.tools.TestServerMetaData;


@SuppressWarnings("unused")
public class MultiDataSourcesJTest extends TestCase {

	private URL wfsUrl = null;
	private URL wmsUrl = null;
  	private TestServerMetaData testServerMetaData = new TestServerMetaData();

	
	@SuppressWarnings("static-access")
	public void testMultiDataSourcesJTest(){
		

			try {
				
				this.wmsUrl = new URL(testServerMetaData.getWmsServerAddress());
				
				WMSDataStoreSrv wmsDataStoreCreatorSrv = new WMSDataStoreSrv(this.wmsUrl, WmsVersion.ver100, WmsGMLVersion.gml2);
				
				IWMSDataStore dataProvider = wmsDataStoreCreatorSrv.getWmsDataStore();
				
				IWMSLayerProvider layerProvider = new SmpWMSLayerProviderSrv();
				
				List<IWMSLayer> smpLayers = layerProvider.getLayers(dataProvider);
				
				//Creating list of want to show layers 
				List<IWMSLayer> rasterLayers = new ArrayList<IWMSLayer>();
				rasterLayers.add(smpLayers.get(2));
				//rasterLayers.add(smpLayers.get(testServerMetaData.getLayerParcel()));
				
				//rasterLayers.get(0).set
				
				List<IWMSNamedStyle> wmsNamedStyles = new ArrayList<IWMSNamedStyle>();
				//wmsNamedStyles.add(new WMSNamedStyle("boglu_orc_building_01"));
				//wmsNamedStyles.add(new WMSNamedStyle("boglu_orc_parcel_01"));
//				wmsNamedStyles.add(new WMSNamedStyle("polygon"));
//				wmsNamedStyles.add(new WMSNamedStyle("polygon"));
				
				ICoordinateSystem coordinateSystem = new SmpCoordinateSystem("EPSG:4326");
				
				IDimension dimension = new SmpDimension(400, 1000);
				
				IEnvelope bbox = new SmpBoundingRectangle(smpLayers.get(testServerMetaData.getLayerDistrict()).getExtent().getMinX(), smpLayers.get(testServerMetaData.getLayerDistrict()).getExtent().getMinY(), smpLayers.get(testServerMetaData.getLayerDistrict()).getExtent().getMaxX(), smpLayers.get(testServerMetaData.getLayerDistrict()).getExtent().getMaxY());
				
				GetMapResult getMap = dataProvider.getMap(rasterLayers, wmsNamedStyles, coordinateSystem, bbox, dimension, WmsImageReturnType.png);
			
				System.out.println("WMS Byte Arr len"+getMap.getImage().length);
			    ImageIcon imgIconDoc = new ImageIcon(getMap.getImage());
				
				BufferedImage img = new BufferedImage(400,
						300, BufferedImage.TYPE_INT_RGB);
						Graphics2D g = img.createGraphics();
						g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

						g.setColor(Color.WHITE);
						g.fillRect(0, 0, 400, 300);
						g.setColor(Color.BLACK);						
						
						g.drawImage(imgIconDoc.getImage(), null, null);
						
						//g.drawBytes(getMap.getImage(), 0, getMap.getImage().length, 0, 0);
						
						g.drawLine(0,0, 100,100);
				
//				testServerMetaData = new TestServerMetaData();
//				
//				this.wfsUrl = new URL(testServerMetaData.getWfsServerAddress());
//				
//				WFSDataStoreCreatorSrv wfsDataStoreCreatorSrv = new WFSDataStoreCreatorSrv(this.wfsUrl, WfsGMLVersion.gml2, WfsVersion.ver100);
//				
//				IFeatureDataProvider wfsDataProvider = wfsDataStoreCreatorSrv.getWfsDataStore();
//				
//				IFeatureLayerProvider wfsLayerProvider = new SmpFeatureLayerProviderSrv();
//				
//				List<IFeatureLayer> wfsSmpLayers = wfsLayerProvider.getLayers(wfsDataProvider);
//
//				IFeatureCollection smpFeatures = wfsSmpLayers.get(testServerMetaData.getLayerStreet()).getFeatureCollection();
//				
//				
//				for (int i = 0; i < smpFeatures.getFeaturesCount(); i++) {
//					
//					
//					
//				}
						
						
			if (getMap.getException().compareTo("") == 0) {

				//ImageIcon imgIconDoc = new ImageIcon(img);
				imgIconDoc = new ImageIcon(img);
				JFrame frame = new JFrame("WMS EXAMPLE MAP");
				JLabel label = new JLabel(imgIconDoc);
				frame.getContentPane().setLayout(new BorderLayout());
				frame.getContentPane().add(label, BorderLayout.CENTER);
				Dimension mapDimension = new Dimension();
				mapDimension.setSize(Double.parseDouble(String.valueOf(dimension.getWidth())), Double.parseDouble(String.valueOf(dimension.getHeight())));
				frame.setSize(mapDimension);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocation(50, 50);
				frame.setVisible(true);
				Thread.currentThread().sleep(15000);

			} else {
				System.out.println("ERROR : " + getMap.getException());
			}

			assertNotSame("", getMap.getException());
			assertNotSame(0,getMap.getImage().length);
			}
			
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			
}
}