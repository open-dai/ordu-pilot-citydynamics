package com.sampas.socbs.core.provider.tests;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import junit.framework.TestCase;

import com.sampas.socbs.core.data.IWMSDataStore;
import com.sampas.socbs.core.data.provider.services.impl.WMSDataStoreSrv;
import com.sampas.socbs.core.data.providers.IWMSLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpWMSLayerProviderSrv;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsGMLVersion;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsImageReturnType;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsVersion;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.geometry.impl.SmpDimension;
import com.sampas.socbs.core.maplayer.IWMSLayer;
import com.sampas.socbs.core.symbology.IStyle;
import com.sampas.socbs.core.symbology.impl.SmpStyle;
import com.sampas.socbs.core.test.tools.TestServerMetaData;

public class WmsGetLegendIconJTest extends TestCase{

	private URL wmsUrl = null;
	
	private TestServerMetaData testServerMetaData = null;
	
	List<IWMSLayer> smpLayers;
	
	BufferedImage legendImage;
	
	@SuppressWarnings("static-access")
	public void testGetMap() {

		testServerMetaData = new TestServerMetaData();
		
		try {
			
			this.wmsUrl = new URL(testServerMetaData.getWmsServerAddress());
			
			WMSDataStoreSrv wmsDataStoreCreatorSrv = new WMSDataStoreSrv(this.wmsUrl, WmsVersion.ver111, WmsGMLVersion.gml2);
			
			IWMSDataStore dataProvider = wmsDataStoreCreatorSrv.getWmsDataStore();
			
			IWMSLayerProvider layerProvider = new SmpWMSLayerProviderSrv();
			
			smpLayers = layerProvider.getLayers(dataProvider);
			
			IWMSLayer smpLayer = smpLayers.get(testServerMetaData.getLayerBuilding());
			
			IStyle style = null;
			
			if (smpLayer.getWmsNamedStyles() != null || smpLayer.getWmsNamedStyles().get(0).getName() != null || smpLayer.getWmsNamedStyles().get(0).getName() != "") {
			
				style = new SmpStyle(smpLayer.getWmsNamedStyles().get(0).getName());
			}		
			
			IDimension iconDimension = new SmpDimension();
			iconDimension.setHeight(30);
			iconDimension.setWidth(30);
			
			legendImage = smpLayer.getLayerStyleLegend(style, iconDimension, WmsImageReturnType.png);
			
			//Drawing map to screen if there is no error
			if (legendImage.getHeight() != 0) {

				ImageIcon imgIconDoc = new ImageIcon(legendImage);
				JFrame frame = new JFrame("WMS EXAMPLE MAP");
				JLabel label = new JLabel(imgIconDoc);
				frame.getContentPane().setLayout(new BorderLayout());
				frame.getContentPane().add(label, BorderLayout.CENTER);
				Dimension dimension = new Dimension();
				dimension.setSize(iconDimension.getWidth() + 50, iconDimension.getHeight() + 50);
				frame.setSize(dimension);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocation(50, 50);
				frame.setVisible(true);
				Thread.currentThread().sleep(5000);			

			} else {
				
				System.out.println("ERROR on drawing legend image");
			}
			
			if (legendImage.getHeight() != 0) {
				////////////////////////////////////////////////////////////////////////////////////////////
				//Check results
				System.out.println("======================================================================");
				System.out.println("Test Adi : Wms getLegendGraphic JUnit Test");
				System.out.println("Çekilen legend grafiginin tipi : " + legendImage.getType());
				System.out.println("**********************************************************************" + "\n");
				////////////////////////////////////////////////////////////////////////////////////////////
			}
			
			//Test criteria		
			assertNotSame(0, smpLayers.size());
			assertNotSame(null, legendImage);
			
		} catch (Exception ex) {
			
			System.out.println("Error on finding datastore Error : " + ex);
			assertNotSame(0, smpLayers.size());
			assertNotSame(null, legendImage);
			
		}		
		
	}
	
}
