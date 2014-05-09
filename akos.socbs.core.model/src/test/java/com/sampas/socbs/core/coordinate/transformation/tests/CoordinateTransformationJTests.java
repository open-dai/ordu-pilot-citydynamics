package com.sampas.socbs.core.coordinate.transformation.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.provider.services.impl.WFSDataStoreSrv;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.data.wfs.impl.WFSDataStore.WfsGMLVersion;
import com.sampas.socbs.core.data.wfs.impl.WFSDataStore.WfsVersion;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureCollection;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.geometry.impl.SmpPoint;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayer;
import com.sampas.socbs.core.test.tools.TestServerMetaData;

//public class CoordinateTransformationJTests extends TestCase {
public class CoordinateTransformationJTests extends TestCase {
	
	TestServerMetaData testServerMetaData = null;
	
	ICoordinateSystem smpSourceCRS = null;
	
	ICoordinateSystem smptargetCRS = null;
	
	URL wfsUrl = null;
	
	List<IFeatureLayer> smpLayers = null;
	
	public CoordinateTransformationJTests() {
		
		try {
			globals();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NoSuchAuthorityCodeException e) {
			e.printStackTrace();
		} catch (FactoryException e) {
			e.printStackTrace();
		}
	}
	
	private void globals() throws MalformedURLException, NoSuchAuthorityCodeException, FactoryException {

		//Set Server Address as a string
		testServerMetaData = new TestServerMetaData();
		String WfsServerAddress = testServerMetaData.getWfsServerAddress();
		
		// URL is the address of the OGC WFS server
		wfsUrl = new URL(WfsServerAddress);

		WFSDataStoreSrv wfsDataStoreCreatorSrv = new WFSDataStoreSrv(this.wfsUrl, WfsGMLVersion.gml2, WfsVersion.ver100);
		
		IFeatureDataStore dataProvider = wfsDataStoreCreatorSrv.getWfsDataStore();
		
		IFeatureLayerProvider layerProvider = new SmpFeatureLayerProviderSrv();
		
		smpLayers = layerProvider.getLayers(dataProvider);
		
		smpSourceCRS = new SmpCoordinateSystem("EPSG:2320");
		
		smptargetCRS = new SmpCoordinateSystem("EPSG:4326");
		
	}

	private IFeatureCollection WfsGetFeatureByTypeName(int layerNo) throws MalformedURLException, NoSuchAuthorityCodeException, FactoryException {
		
		//Get feature collection
		IFeatureCollection smpFeatures = smpLayers.get(layerNo).getFeatureCollection();
		
		return(smpFeatures);
	}
	
	public void testFeatureCollectionTransform() throws MismatchedDimensionException, MalformedURLException, NoSuchAuthorityCodeException, FactoryException, TransformException, IllegalAttributeException {
		
		//select layerNo
		//int layerNo = 7;
		int layerNo = testServerMetaData.getLayerDistrict();
		
		ICoordinateSystemTransformers coordinateSystemTransformers = new CoordinateSystemTransformers();
		
		IFeatureCollection orjCollection = smpLayers.get(layerNo).getFeatureCollection();
		
		IFeatureCollection transformedCollection = coordinateSystemTransformers.FeaturesCoordinateTransformer(WfsGetFeatureByTypeName(layerNo), smpSourceCRS, smptargetCRS);
		
		//for (int i = 0; i < orjCollection.getFeaturesCount(); i++) {
		for (int i = 0; i < 1; i++) {
						
			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : Coordinate Transformation for Featurecollection JUnit Test");
			System.out.println("Orjinal geometry noktasi :" + orjCollection.getFeatureAt(i).getDefaultGeometry().getCoordinates()[0].getX() + "," + orjCollection.getFeatureAt(i).getDefaultGeometry().getCoordinates()[0].getY() + ",0");
			System.out.println("Donusturulmus geometry noktasi :" + transformedCollection.getFeatureAt(i).getDefaultGeometry().getCoordinates()[0].getX() + "," + transformedCollection.getFeatureAt(i).getDefaultGeometry().getCoordinates()[0].getY() + ",0");
			System.out.println("**********************************************************************" + "\n");
			////////////////////////////////////////////////////////////////////////////////////////////
			
			assertNotSame(orjCollection.getFeatureAt(i).getDefaultGeometry().getCoordinates()[0].getX(), transformedCollection.getFeatureAt(i).getDefaultGeometry().getCoordinates()[0].getX());
		}
		
	}
	
	public void testFeatureTransform() throws MalformedURLException, NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException, IllegalAttributeException {
		
		//select layerNo
		//int layerNo = 7;
		int layerNo = testServerMetaData.getLayerDistrict();
		
		ICoordinateSystemTransformers coordinateSystemTransformers = new CoordinateSystemTransformers();
		
		IFeatureCollection orjCollection = new SmpFeatureCollection(((SmpFeatureCollection)WfsGetFeatureByTypeName(layerNo)).getGeoToolsFeatureCollection());
		
		IFeature transformedFeature = coordinateSystemTransformers.FeatureCoordinateTransformer(WfsGetFeatureByTypeName(layerNo).getFeatureAt(0), smpSourceCRS, smptargetCRS);
			
		////////////////////////////////////////////////////////////////////////////////////////////
		//Check results
		System.out.println("======================================================================");
		System.out.println("Test Adi : Coordinate Transformation for Feature JUnit Test");
		System.out.println("Orjinal geometry noktasi :" + orjCollection.getFeatureAt(0).getDefaultGeometry().getCoordinates()[0].getX() + "," + orjCollection.getFeatureAt(0).getDefaultGeometry().getCoordinates()[0].getY() + ",0");
		System.out.println("Donusturulmus geometry noktasi :" + transformedFeature.getDefaultGeometry().getCoordinates()[0].getX() + "," + transformedFeature.getDefaultGeometry().getCoordinates()[0].getY() + ",0");
		System.out.println("**********************************************************************" + "\n");
		////////////////////////////////////////////////////////////////////////////////////////////
		
		assertNotSame(orjCollection.getFeatureAt(0).getDefaultGeometry().getCoordinates()[0].getX(), transformedFeature.getDefaultGeometry().getCoordinates()[0].getX());

	}
	
	public void testGeometryTransform() throws MalformedURLException, NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException, IllegalAttributeException {
		
		//select layerNo
		//int layerNo = 7;
		int layerNo = testServerMetaData.getLayerDistrict();
		
		ICoordinateSystemTransformers coordinateSystemTransformers = new CoordinateSystemTransformers();
		
		IFeatureCollection orjCollection = new SmpFeatureCollection(((SmpFeatureCollection)WfsGetFeatureByTypeName(layerNo)).getGeoToolsFeatureCollection());
		
		IGeometry transformedGeometry = coordinateSystemTransformers.SmpGeometryCoordinateTransformer(((SmpFeatureCollection)WfsGetFeatureByTypeName(layerNo)).getFeatureAt(0).getDefaultGeometry(), smpSourceCRS, smptargetCRS);
			
		////////////////////////////////////////////////////////////////////////////////////////////
		//Check results
		System.out.println("======================================================================");
		System.out.println("Test Adi : Coordinate Transformation for Geometry JUnit Test");
		System.out.println("Orjinal geometry noktasi :" + orjCollection.getFeatureAt(0).getDefaultGeometry().getCoordinates()[0].getX() + "," + orjCollection.getFeatureAt(0).getDefaultGeometry().getCoordinates()[0].getY() + ",0");
		System.out.println("Donusturulmus geometry noktasi :" + transformedGeometry.getCoordinates()[0].getX() + "," + transformedGeometry.getCoordinates()[0].getY() + ",0");
		System.out.println("**********************************************************************" + "\n");
		////////////////////////////////////////////////////////////////////////////////////////////
		
		assertNotSame(orjCollection.getFeatureAt(0).getDefaultGeometry().getCoordinates()[0].getX(), transformedGeometry.getCoordinates()[0].getX());
	}
	
	public void testBoundingRectangleTransform() throws MalformedURLException, NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException, IllegalAttributeException {
		
		//select layerNo
		//int layerNo = 7;
		int layerNo = testServerMetaData.getLayerDistrict();		

		ILayer smpLayer = this.smpLayers.get(layerNo);
		
		SmpBoundingRectangle smpBBox = new SmpBoundingRectangle(smpLayer.getExtent().getMinX(), smpLayer.getExtent().getMinY(), smpLayer.getExtent().getMaxX(), smpLayer.getExtent().getMaxY());
				
		ICoordinateSystemTransformers coordinateSystemTransformers = new CoordinateSystemTransformers();
		
		IEnvelope transformedBBox = coordinateSystemTransformers.SmpBoundingRectangleCoordinateTransformer(smpBBox, smpSourceCRS, smptargetCRS);
			
		////////////////////////////////////////////////////////////////////////////////////////////
		//Check results
		System.out.println("======================================================================");
		System.out.println("Test Adi : Coordinate Transformation for Bounding Rectangle (BBox) JUnit Test");
		System.out.println("Orjinal geometry noktasi :" + smpBBox.getMinX() + "," + smpBBox.getMinY() + ",0");
		System.out.println("Donusturulmus geometry noktasi :" + transformedBBox.getMinX() + "," + transformedBBox.getMinY() + ",0");
		System.out.println("**********************************************************************" + "\n");
		////////////////////////////////////////////////////////////////////////////////////////////
		
		assertNotSame(smpBBox.getMinX(), transformedBBox.getMinX());
		
		IEnvelope transformedBBoxReverse = coordinateSystemTransformers.SmpBoundingRectangleCoordinateTransformer(transformedBBox, smptargetCRS, smpSourceCRS);
		
		////////////////////////////////////////////////////////////////////////////////////////////
		//Check results
		System.out.println("======================================================================");
		System.out.println("Test Adi : Coordinate Transformation for Bounding Rectangle (BBox) JUnit Test");
		System.out.println("Orjinal geometry noktasi :" + transformedBBox.getMinX() + "," + transformedBBox.getMinY() + ",0");
		System.out.println("Donusturulmus geometry noktasi :" + transformedBBoxReverse.getMinX() + "," + transformedBBoxReverse.getMinY() + ",0");
		System.out.println("**********************************************************************" + "\n");
		////////////////////////////////////////////////////////////////////////////////////////////
		
		assertNotSame(transformedBBox.getMinX(), transformedBBoxReverse.getMinX());
	}
	
	public void testSpecificCoordinatesTransformation() {
		
		List<IPoint> points = new ArrayList<IPoint>();
		
		ICoordinateSystem testPointsCoordinateSystem = new SmpCoordinateSystem("EPSG:4326");
		
//		IPoint point1 = new SmpPoint(413257.75533878, 4546327.28556538, testPointsCoordinateSystem);
//		points.add(point1);
//		IPoint point2 = new SmpPoint(413261.02691024, 4546333.17439400, testPointsCoordinateSystem);
//		points.add(point2);
//		IPoint point3 = new SmpPoint(413261.81208739, 4546342.20393122, testPointsCoordinateSystem);
//		points.add(point3);
//		IPoint point4 = new SmpPoint(413262.33553882, 4546350.18656557, testPointsCoordinateSystem);
//		points.add(point4);
//		IPoint point5 = new SmpPoint(413263.51330455, 4546359.08523993, testPointsCoordinateSystem);
//		points.add(point5);
//		IPoint point6 = new SmpPoint(413264.69107027, 4546365.89010856, testPointsCoordinateSystem);
//		points.add(point6);
//		IPoint point7 = new SmpPoint(413265.21452171, 4546372.69497720, testPointsCoordinateSystem);
//		points.add(point7);
//		IPoint point8 = new SmpPoint(413265.60711028, 4546381.07020012, testPointsCoordinateSystem);
//		points.add(point8);
		
//		IPoint point1 = new SmpPoint(410537, 4543098, testPointsCoordinateSystem);
//		points.add(point1);
//		IPoint point2 = new SmpPoint(410537, 4548880, testPointsCoordinateSystem);
//		points.add(point2);
//		IPoint point3 = new SmpPoint(415980, 4548880, testPointsCoordinateSystem);
//		points.add(point3);
//		IPoint point4 = new SmpPoint(415980, 4543098, testPointsCoordinateSystem);
//		points.add(point4);
		
		IPoint point1 = new SmpPoint(28.98114,41.04236, testPointsCoordinateSystem);
		points.add(point1);
		IPoint point2 = new SmpPoint(28.98094,41.04211, testPointsCoordinateSystem);
		points.add(point2);
		IPoint point3 = new SmpPoint(28.98077,41.04156, testPointsCoordinateSystem);
		points.add(point3);
		IPoint point4 = new SmpPoint(28.98061,41.04175, testPointsCoordinateSystem);
		points.add(point4);
		IPoint point5 = new SmpPoint(28.98041,41.04156, testPointsCoordinateSystem);
		points.add(point5);
		
		ICoordinateSystem targetCoordinateSystem = new SmpCoordinateSystem("EPSG:2320");
		
		ICoordinateSystemTransformers coordinateSystemTransformers = new CoordinateSystemTransformers();

		List<IPoint> resultPoints = new ArrayList<IPoint>();
		
		int glbCntr = 0;
		
		for (IPoint point : points) {
			
			if (!point.getCoordinateSystem().getEPSGCode().equals(targetCoordinateSystem.getEPSGCode())) {
				try {

					resultPoints.add(coordinateSystemTransformers.SmpGeometryCoordinateTransformer(point, testPointsCoordinateSystem, targetCoordinateSystem));
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				resultPoints.add(point);
			}
			
			System.out.println("--------------------------------------------------");
			System.out.println("Point " + glbCntr + " X before process : " + point.getX() + " ----- Point " + glbCntr + " Y before process : " + point.getY());
			System.out.println("Point " + glbCntr + " X after process : " + resultPoints.get(glbCntr).getX() + " ----- Point " + glbCntr + " Y after process : " + resultPoints.get(glbCntr).getY());
			System.out.println("--------------------------------------------------");
			
			glbCntr++;
		}

	}
	
}
