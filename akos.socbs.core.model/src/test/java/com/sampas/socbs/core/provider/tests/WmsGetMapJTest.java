package com.sampas.socbs.core.provider.tests;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import junit.framework.TestCase;
import org.geotools.feature.IllegalAttributeException;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.operation.TransformException;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.CoordinateSystemTransformers;
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
import com.sampas.socbs.core.style.impl.WMSNamedStyle;
import com.sampas.socbs.core.test.tools.TestServerMetaData;


public class WmsGetMapJTest extends TestCase{

	private URL wmsUrl = null;
	
	private TestServerMetaData testServerMetaData = null;
	
	@SuppressWarnings("static-access")
	public void testGetMap() {

		testServerMetaData = new TestServerMetaData();
		
		try {
			
			this.wmsUrl = new URL(testServerMetaData.getWmsServerAddress());
			
			WMSDataStoreSrv wmsDataStoreCreatorSrv = new WMSDataStoreSrv(this.wmsUrl, WmsVersion.ver100, WmsGMLVersion.gml2);
			
			IWMSDataStore dataProvider = wmsDataStoreCreatorSrv.getWmsDataStore();
			
			IWMSLayerProvider layerProvider = new SmpWMSLayerProviderSrv();
			
			List<IWMSLayer> smpLayers = layerProvider.getLayers(dataProvider);
			
			//Creating list of want to show layers 
			List<IWMSLayer> wmsLayers = new ArrayList<IWMSLayer>();
		//	wmsLayers.add(smpLayers.get(testServerMetaData.getLayerBuilding()));
		//	wmsLayers.add(smpLayers.get(testServerMetaData.getLayerParcel()));
			wmsLayers.add(smpLayers.get(21));
			
//			for (int i = 0; i < smpLayers.size(); i++) {
//				
//				if(smpLayers.get(i).getName().equals("smpboglu:GRF_MAHALLE")){
//					wmsLayers.add(smpLayers.get(i));
//				}
//			}
			
			
			List<IWMSNamedStyle> wmsNamedStyles = new ArrayList<IWMSNamedStyle>();
			wmsNamedStyles.add(new WMSNamedStyle("raster_boglu_sattelite"));
	//		wmsNamedStyles.add(new WMSNamedStyle("building_orc"));
	//		wmsNamedStyles.add(new WMSNamedStyle("parcel_orc"));			
	//		wmsNamedStyles.add(new WMSNamedStyle(""));
			
//			wmsNamedStyles.add(new WMSNamedStyle("polygon"));
			
			ICoordinateSystem coordinateSystem = new SmpCoordinateSystem("EPSG:2320");
			
			IDimension dimension = new SmpDimension(1500, 1500);
			
			SmpBoundingRectangle bbox = new SmpBoundingRectangle(smpLayers.get(testServerMetaData.getLayerDistrict()).getExtent().getMinX(), smpLayers.get(testServerMetaData.getLayerDistrict()).getExtent().getMinY(), smpLayers.get(testServerMetaData.getLayerDistrict()).getExtent().getMaxX(), smpLayers.get(testServerMetaData.getLayerDistrict()).getExtent().getMaxY());
			
		//	bbox = (SmpBoundingRectangle) transformEnvelope(bbox, smpLayers.get(0).getCoordinateSystem(), coordinateSystem);
			
			GetMapResult getMap = dataProvider.getMap(wmsLayers, wmsNamedStyles, coordinateSystem, bbox, dimension, WmsImageReturnType.png);
			
			//Drawing map to screen if there is no error
			if (getMap.getException().compareTo("") == 0) {

				ImageIcon imgIconDoc = new ImageIcon(getMap.getImage());
				JFrame frame = new JFrame("WMS EXAMPLE MAP");
				JLabel label = new JLabel(imgIconDoc);
				frame.getContentPane().setLayout(new BorderLayout());
				frame.getContentPane().add(label, BorderLayout.CENTER);
				Dimension mapDimension = new Dimension();
				mapDimension.setSize(Double.parseDouble(String.valueOf(dimension.getWidth() + 100)), Double.parseDouble(String.valueOf(dimension.getHeight() + 100)));
				frame.setSize(mapDimension);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocation(50, 50);
				frame.setVisible(true);
				Thread.currentThread().sleep(50000);			

			} else {
				System.out.println("ERROR : " + getMap.getException());
			}
			
			if (getMap.getException().equals("")) {
				////////////////////////////////////////////////////////////////////////////////////////////
				//Check results
				System.out.println("======================================================================");
				System.out.println("Test Adi : WmsLayersJTest JUnit Test");
				System.out.println("Bulunan Katman Sayısı : " + smpLayers.size());
				System.out.println("Çekilen veri büyüklüğü : " + getMap.getImage().length);
				System.out.println("**********************************************************************" + "\n");
				////////////////////////////////////////////////////////////////////////////////////////////
			}
			
			//Test criteria		
			assertNotSame(0, smpLayers.size());
			assertNotSame(null, getMap.getImage());
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}		
		
	}
	
	@SuppressWarnings("unused")
	private IEnvelope transformEnvelope(IEnvelope envelope, ICoordinateSystem fromCooSys,ICoordinateSystem toCooSys) {

		ICoordinateSystemTransformers coordinateSystemTransformers = new CoordinateSystemTransformers();

		IEnvelope layerExtent = null;

		try {

			layerExtent = coordinateSystemTransformers.SmpBoundingRectangleCoordinateTransformer(
					envelope,
					fromCooSys, 
					toCooSys);

		} catch (MismatchedDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAuthorityCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAttributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return layerExtent;
	}
	
}
