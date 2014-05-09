package com.sampas.socbs.core.coordinatesystem.impl;

import java.util.Iterator;

import org.geotools.feature.IllegalAttributeException;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.NamedIdentifier;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystemTransformers;
import com.sampas.socbs.core.crs.wkts.Wkt2000273;
import com.sampas.socbs.core.crs.wkts.Wkt2000303;
import com.sampas.socbs.core.crs.wkts.Wkt2000333;
import com.sampas.socbs.core.crs.wkts.Wkt2000363;
import com.sampas.socbs.core.crs.wkts.Wkt2000393;
import com.sampas.socbs.core.crs.wkts.Wkt2000423;
import com.sampas.socbs.core.crs.wkts.Wkt2000453;
import com.sampas.socbs.core.crs.wkts.Wkt2001273;
import com.sampas.socbs.core.crs.wkts.Wkt2001303;
import com.sampas.socbs.core.crs.wkts.Wkt2001333;
import com.sampas.socbs.core.crs.wkts.Wkt2001363;
import com.sampas.socbs.core.crs.wkts.Wkt2001393;
import com.sampas.socbs.core.crs.wkts.Wkt2001423;
import com.sampas.socbs.core.crs.wkts.Wkt2001453;
import com.sampas.socbs.core.crs.wkts.Wkt900913;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.geometry.impl.SmpGeometry;
import com.sampas.socbs.core.geometry.impl.SmpPoint;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;


@SuppressWarnings("unused")
public class CoordinateSystemTransformers implements ICoordinateSystemTransformers {

	public IFeatureCollection FeaturesCoordinateTransformer(IFeatureCollection inputFeatures, ICoordinateSystem sourceCoordRefSys, ICoordinateSystem targetCoordRefSys) throws FactoryException, MismatchedDimensionException, TransformException, IllegalAttributeException  {
		
		//IFeatureCollection outputFeatures = new SmpFeatureCollection(((SmpFeatureCollection)inputFeatures).getGeoToolsFeatureCollection());
		IFeatureCollection outputFeatures = inputFeatures.cloneFeatures();
		
		CoordinateReferenceSystem sourceGeoCoordinate;
		
		if (sourceCoordRefSys.getEPSGCode().equals(Wkt900913.EPSG900913)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt900913.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000273.EPSG2000273)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000273.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000303.EPSG2000303)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000303.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000333.EPSG2000333)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000333.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000363.EPSG2000363)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000363.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000393.EPSG2000393)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000393.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000423.EPSG2000423)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000423.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000453.EPSG2000453)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000453.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001273.EPSG2001273)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001273.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001303.EPSG2001303)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001303.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001333.EPSG2001333)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001333.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001363.EPSG2001363)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001363.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001393.EPSG2001393)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001393.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001423.EPSG2001423)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001423.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001453.EPSG2001453)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001453.wkt);
		} else {
			
			sourceGeoCoordinate = CRS.decode(sourceCoordRefSys.getEPSGCode(), true);
		} 
		CoordinateReferenceSystem targetGeoCoordinate;
		
		if (targetCoordRefSys.getEPSGCode().equals(Wkt900913.EPSG900913)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt900913.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000273.EPSG2000273)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000273.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000303.EPSG2000303)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000303.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000333.EPSG2000333)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000333.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000363.EPSG2000363)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000363.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000393.EPSG2000393)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000393.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000423.EPSG2000423)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000423.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000453.EPSG2000453)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000453.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001273.EPSG2001273)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001273.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001303.EPSG2001303)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001303.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001333.EPSG2001333)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001333.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001363.EPSG2001363)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001363.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001393.EPSG2001393)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001393.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001423.EPSG2001423)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001423.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001453.EPSG2001453)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001453.wkt);
		} else {
			
			targetGeoCoordinate = CRS.decode(targetCoordRefSys.getEPSGCode(), true);
		}
//		MathTransform transform = CRS.findMathTransform(sourceGeoCoordinate, targetGeoCoordinate);
		MathTransform transform = CRS.findMathTransform(sourceGeoCoordinate, targetGeoCoordinate, true);
		
		Geometry targetGeometry = null;
		
		for (int i = 0; i < inputFeatures.getFeaturesCount(); i++) {
			//Test for coordinates before change		
			//System.out.println(inputFeatures.getFeatureAt(i).getGeoToolFeature().getDefaultGeometry().getCoordinates()[1].x);
			try {
				
				targetGeometry = JTS.transform((Geometry)((SmpFeature) outputFeatures.getFeatureAt(i)).getGeoToolFeature().getDefaultGeometry(), transform);
	
				((SmpFeature) outputFeatures.getFeatureAt(i)).getGeoToolFeature().setDefaultGeometry(targetGeometry);
			} catch (Exception ex) {
				
				System.out.println("ERROR on transforming feature ID : " + outputFeatures.getFeatureAt(i).getID());
			}
			//if need is change the original features geometry use next row and comment previous one and its decleration
			//inputFeatures.getFeatureAt(i).getGeoToolFeature().setDefaultGeometry(targetGeometry);
			
			//Test for coordinates after change
			//System.out.println(inputFeatures.getFeatureAt(i).getGeoToolFeature().getDefaultGeometry().getCoordinates()[1].x);
		}
		return (outputFeatures);
	}
	
	public IFeature FeatureCoordinateTransformer(IFeature inputFeature, ICoordinateSystem sourceCoordRefSys, ICoordinateSystem targetCoordRefSys) throws FactoryException, MismatchedDimensionException, TransformException, IllegalAttributeException {
		
		//IFeature outputFeature = new SmpFeature(((SmpFeature)inputFeature).getGeoToolFeature());
		IFeature outputFeature = inputFeature.cloneFeature();
		
		CoordinateReferenceSystem sourceGeoCoordinate;
		
		if (sourceCoordRefSys.getEPSGCode().equals(Wkt900913.EPSG900913)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt900913.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000273.EPSG2000273)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000273.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000303.EPSG2000303)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000303.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000333.EPSG2000333)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000333.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000363.EPSG2000363)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000363.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000393.EPSG2000393)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000393.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000423.EPSG2000423)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000423.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000453.EPSG2000453)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000453.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001273.EPSG2001273)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001273.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001303.EPSG2001303)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001303.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001333.EPSG2001333)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001333.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001363.EPSG2001363)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001363.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001393.EPSG2001393)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001393.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001423.EPSG2001423)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001423.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001453.EPSG2001453)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000453.wkt);
		} else {
			
			sourceGeoCoordinate = CRS.decode(sourceCoordRefSys.getEPSGCode(), true);
		}
		CoordinateReferenceSystem targetGeoCoordinate;
		
		if (targetCoordRefSys.getEPSGCode().equals(Wkt900913.EPSG900913)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt900913.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000273.EPSG2000273)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000273.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000303.EPSG2000303)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000303.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000333.EPSG2000333)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000333.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000363.EPSG2000363)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000363.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000393.EPSG2000393)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000393.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000423.EPSG2000423)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000423.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000453.EPSG2000453)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000453.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001273.EPSG2001273)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001273.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001303.EPSG2001303)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001303.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001333.EPSG2001333)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001333.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001363.EPSG2001363)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001363.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001393.EPSG2001393)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001393.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001423.EPSG2001423)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001423.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001453.EPSG2001453)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001453.wkt);
		} else {
			
			targetGeoCoordinate = CRS.decode(targetCoordRefSys.getEPSGCode(), true);
		}
//		MathTransform transform = CRS.findMathTransform(sourceGeoCoordinate, targetGeoCoordinate);
		MathTransform transform = CRS.findMathTransform(sourceGeoCoordinate, targetGeoCoordinate, true);
		
		Geometry targetGeometry = null;
			
		//Test for coordinates before change		
		//System.out.println(inputFeature.getGeoToolFeature().getDefaultGeometry().getCoordinates()[1].x);
		try {
			
			targetGeometry = JTS.transform((Geometry)((SmpFeature)outputFeature).getGeoToolFeature().getDefaultGeometry(), transform);
			
			((SmpFeature)outputFeature).getGeoToolFeature().setDefaultGeometry(targetGeometry);
		} catch (Exception ex) {
			
			System.out.println("ERROR on transforming feature ID : " + ((SmpFeature)outputFeature).getID());
		}
		//if need is change the original features geometry use next row and comment previous one and its decleration
		//inputFeature.getGeoToolFeature().setDefaultGeometry(targetGeometry);
			
		//Test for coordinates after change
		//System.out.println(inputFeature.getGeoToolFeature().getDefaultGeometry().getCoordinates()[1].x);
		
		return (outputFeature);
	}
	
	public IGeometry SmpGeometryCoordinateTransformer(IGeometry smpGeometry, ICoordinateSystem sourceCoordRefSys, ICoordinateSystem targetCoordRefSys) throws NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException, IllegalAttributeException {
		
		CoordinateReferenceSystem sourceGeoCoordinate;
		
		if (sourceCoordRefSys.getEPSGCode().equals(Wkt900913.EPSG900913)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt900913.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000273.EPSG2000273)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000273.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000303.EPSG2000303)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000303.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000333.EPSG2000333)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000333.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000363.EPSG2000363)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000363.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000393.EPSG2000393)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000393.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000423.EPSG2000423)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000423.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000453.EPSG2000453)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000453.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001273.EPSG2001273)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001273.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001303.EPSG2001303)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001303.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001333.EPSG2001333)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001333.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001363.EPSG2001363)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001363.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001393.EPSG2001393)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001393.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001423.EPSG2001423)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001423.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001453.EPSG2001453)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001453.wkt);
		} else {
			
			sourceGeoCoordinate = CRS.decode(sourceCoordRefSys.getEPSGCode(), true);
		}
		CoordinateReferenceSystem targetGeoCoordinate;
		
		if (targetCoordRefSys.getEPSGCode().equals(Wkt900913.EPSG900913)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt900913.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000273.EPSG2000273)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000273.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000303.EPSG2000303)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000303.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000333.EPSG2000333)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000333.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000363.EPSG2000363)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000363.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000393.EPSG2000393)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000393.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000423.EPSG2000423)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000423.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000453.EPSG2000453)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000453.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001273.EPSG2001273)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001273.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001303.EPSG2001303)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001303.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001333.EPSG2001333)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001333.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001363.EPSG2001363)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001363.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001393.EPSG2001393)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001393.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001423.EPSG2001423)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001423.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001453.EPSG2001453)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000453.wkt);
		} else {
			
			targetGeoCoordinate = CRS.decode(targetCoordRefSys.getEPSGCode(), true);
		}
//		MathTransform transform = CRS.findMathTransform(sourceGeoCoordinate, targetGeoCoordinate);
		MathTransform transform = CRS.findMathTransform(sourceGeoCoordinate, targetGeoCoordinate, true);
		
		Geometry targetGeoGeometry = null;
			
		//Test for coordinates before change
		//System.out.println(smpGeometry.getGeoToolGeometry().getCoordinates()[0].x);
		SmpGeometry targetGeometry;
		
		try {
		
			targetGeoGeometry = JTS.transform(((SmpGeometry)smpGeometry).getGeoToolGeometry(), transform);			
			
			targetGeometry = new SmpGeometry(targetGeoGeometry);
		} catch (Exception ex) {

			targetGeometry = null;
			
			System.out.println("ERROR on transforming geometry !!!");
		}
		//Test for coordinates after change
		//System.out.println(targetGeoGeometry.getCoordinates()[0].x);
		return (targetGeometry);
	}
	
	public IEnvelope SmpBoundingRectangleCoordinateTransformer(IEnvelope smpBBox, ICoordinateSystem sourceCoordRefSys, ICoordinateSystem targetCoordRefSys) throws NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException, IllegalAttributeException {
		//Test for coordinates before change
//		System.out.println("----------------------- COORDINATE TRANSFORMATION --------------------------------------------");
//		System.out.print("Source CRS : " + sourceCoordRefSys.getEPSGCode() + " Before transformation MinX: " + smpBBox.getMinX());
//		System.out.print(" MinY: " + smpBBox.getMinY());
//		System.out.print(" MaxX: " + smpBBox.getMaxX());
//		System.out.println(" MaxY: " + smpBBox.getMaxY());
		
		CoordinateReferenceSystem sourceGeoCoordinate;
		
		if (sourceCoordRefSys.getEPSGCode().equals(Wkt900913.EPSG900913)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt900913.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000273.EPSG2000273)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000273.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000303.EPSG2000303)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000303.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000333.EPSG2000333)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000333.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000363.EPSG2000363)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000363.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000393.EPSG2000393)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000393.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000423.EPSG2000423)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000423.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000453.EPSG2000453)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000453.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001273.EPSG2001273)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001273.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001303.EPSG2001303)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001303.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001333.EPSG2001333)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001333.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001363.EPSG2001363)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001363.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001393.EPSG2001393)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001393.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001423.EPSG2001423)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001423.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001453.EPSG2001453)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001453.wkt);
		} else {
			
			sourceGeoCoordinate = CRS.decode(sourceCoordRefSys.getEPSGCode(), true);
		}
		CoordinateReferenceSystem targetGeoCoordinate;
		
		if (targetCoordRefSys.getEPSGCode().equals(Wkt900913.EPSG900913)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt900913.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000273.EPSG2000273)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000273.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000303.EPSG2000303)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000303.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000333.EPSG2000333)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000333.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000363.EPSG2000363)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000363.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000393.EPSG2000393)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000393.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000423.EPSG2000423)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000423.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000453.EPSG2000453)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000453.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001273.EPSG2001273)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001273.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001303.EPSG2001303)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001303.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001333.EPSG2001333)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001333.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001363.EPSG2001363)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001363.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001393.EPSG2001393)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001393.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001423.EPSG2001423)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001423.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001453.EPSG2001453)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001453.wkt);
		} else {
			
			targetGeoCoordinate = CRS.decode(targetCoordRefSys.getEPSGCode(), true);
		}
		SmpBoundingRectangle smpTransferedBbox;
		
		try {
			//MathTransform transform = CRS.findMathTransform(sourceGeoCoordinate, targetGeoCoordinate);
			MathTransform transform = CRS.findMathTransform(sourceGeoCoordinate, targetGeoCoordinate, true);
			
			GeometryFactory geometryFactory = new GeometryFactory();	
			
	        Coordinate coordinateMins = new Coordinate(smpBBox.getMinX(), smpBBox.getMinY());        
	        Point bBoxMins = geometryFactory.createPoint(coordinateMins);        
	        
	        Coordinate coordinateMaxs = new Coordinate(smpBBox.getMaxX(), smpBBox.getMaxY());
	        Point bBoxMaxs = geometryFactory.createPoint(coordinateMaxs);
	        
	        Point bBoxTrMins = (Point)JTS.transform((Geometry)bBoxMins, transform);
	        
	        Point bBoxTrMaxs = (Point)JTS.transform((Geometry)bBoxMaxs, transform);		
			//Test for coordinates before change
//	     	System.out.print("Target CRS : " + targetCoordRefSys.getEPSGCode() + " After transformation MinX: " + bBoxTrMaxs.getX());
//			System.out.print(" MinY: " + bBoxTrMaxs.getY());
//			System.out.print(" MaxX: " + bBoxTrMaxs.getX());
//			System.out.println(" MaxY: " + bBoxTrMaxs.getY());
//			System.out.println("----------------------------------------------------------------------------------------------");
			
	        smpTransferedBbox = new SmpBoundingRectangle(bBoxTrMins.getX(), bBoxTrMins.getY(), bBoxTrMaxs.getX(), bBoxTrMaxs.getY());
		} catch (Exception ex) {
        	
			System.err.println("ERROR on transforming bounding box !!!");
			
        	smpTransferedBbox = null;
        }
		return (smpTransferedBbox);
	}
	
	public IPoint SmpGeometryCoordinateTransformer(IPoint smpPoint, ICoordinateSystem sourceCoordRefSys, ICoordinateSystem targetCoordRefSys) throws NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException, IllegalAttributeException {
		
		CoordinateReferenceSystem sourceGeoCoordinate;
		
		if (sourceCoordRefSys.getEPSGCode().equals(Wkt900913.EPSG900913)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt900913.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000273.EPSG2000273)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000273.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000303.EPSG2000303)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000303.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000333.EPSG2000333)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000333.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000363.EPSG2000363)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000363.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000393.EPSG2000393)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000393.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000423.EPSG2000423)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000423.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2000453.EPSG2000453)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2000453.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001273.EPSG2001273)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001273.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001303.EPSG2001303)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001303.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001333.EPSG2001333)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001333.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001363.EPSG2001363)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001363.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001393.EPSG2001393)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001393.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001423.EPSG2001423)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001423.wkt);
		} else if (sourceCoordRefSys.getEPSGCode().equals(Wkt2001453.EPSG2001453)) {
			
			sourceGeoCoordinate = CRS.parseWKT(Wkt2001453.wkt);
		} else {
			
			sourceGeoCoordinate = CRS.decode(sourceCoordRefSys.getEPSGCode(), true);
		}
		CoordinateReferenceSystem targetGeoCoordinate;
		
		if (targetCoordRefSys.getEPSGCode().equals(Wkt900913.EPSG900913)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt900913.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000273.EPSG2000273)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000273.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000303.EPSG2000303)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000303.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000333.EPSG2000333)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000333.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000363.EPSG2000363)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000363.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000393.EPSG2000393)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000393.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000423.EPSG2000423)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000423.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2000453.EPSG2000453)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2000453.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001273.EPSG2001273)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001273.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001303.EPSG2001303)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001303.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001333.EPSG2001333)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001333.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001363.EPSG2001363)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001363.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001393.EPSG2001393)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001393.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001423.EPSG2001423)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001423.wkt);
		} else if (targetCoordRefSys.getEPSGCode().equals(Wkt2001453.EPSG2001453)) {
			
			targetGeoCoordinate = CRS.parseWKT(Wkt2001453.wkt);
		} else {
			
			targetGeoCoordinate = CRS.decode(targetCoordRefSys.getEPSGCode(), true);
		}
		IPoint resultPoint;
		
		try {
//			MathTransform transform = CRS.findMathTransform(sourceGeoCoordinate, targetGeoCoordinate);
			MathTransform transform = CRS.findMathTransform(sourceGeoCoordinate, targetGeoCoordinate, true);
			
			Geometry targetGeoGeometry = null;
				
			//Test for coordinates before change
			//System.out.println(smpGeometry.getGeoToolGeometry().getCoordinates()[0].x);
				
			targetGeoGeometry = JTS.transform(((SmpPoint)smpPoint).getGeoToolsPoint(), transform);			
			
			SmpGeometry targetGeometry = new SmpGeometry(targetGeoGeometry);
			
			resultPoint = new SmpPoint(targetGeoGeometry.getCentroid().getX(), targetGeoGeometry.getCentroid().getY(), targetCoordRefSys); 
			
			//Test for coordinates after change
			//System.out.println(targetGeoGeometry.getCoordinates()[0].x);
		} catch (Exception ex) {
	    	
			System.err.println("ERROR on transforming point !!!");
			
			resultPoint = null;
	    }
		return (resultPoint);
	}

	
	@SuppressWarnings("unchecked")
	public ICoordinateSystem getCoordinateSystemFromCRS(CoordinateReferenceSystem crs) {
		
		ICoordinateSystem resultCS = null;
		
		if (crs != null) {
			
			if (crs.getIdentifiers().iterator().hasNext()) {
		
				Iterator<NamedIdentifier> crsIterator = crs.getIdentifiers().iterator();					
				
				NamedIdentifier namedIdentifier = crsIterator.next();					
				
				SmpCoordinateSystem smpCoordinateSystem = new SmpCoordinateSystem(namedIdentifier.getCodeSpace() + ":" + namedIdentifier.getCode());
				
				resultCS = smpCoordinateSystem;
			} else {
				//For custom defined ITRF and UTM referances
				if (crs.toWKT().contains("TM 27 3 DERECE ITRF96")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2000273");
				} else if (crs.toWKT().contains("TM 30 3 DERECE ITRF96")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2000303");
				} else if (crs.toWKT().contains("TM 33 3 DERECE ITRF96")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2000333");
				}  else if (crs.toWKT().contains("TM 36 3 DERECE ITRF96")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2000363");
				}  else if (crs.toWKT().contains("TM 39 3 DERECE ITRF96")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2000393");
				} else if (crs.toWKT().contains("TM 42 3 DERECE ITRF96")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2000423");
				} else if (crs.toWKT().contains("TM 45 3 DERECE ITRF96")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2000453");
				} else if (crs.toWKT().contains("TM 27 3 DERECE 2001 ITRF96")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2001273");
				} else if (crs.toWKT().contains("TM 30 3 DERECE 2001 ITRF96")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2001303");
				} else if (crs.toWKT().contains("TM 33 3 DERECE 2001 ITRF96")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2001333");
				}  else if (crs.toWKT().contains("TM 36 3 DERECE 2001 ITRF96")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2001363");
				}  else if (crs.toWKT().contains("TM 39 3 DERECE 2001 ITRF96")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2001393");
				} else if (crs.toWKT().contains("TM 42 3 DERECE 2001 ITRF96")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2001423");
				} else if (crs.toWKT().contains("TM 45 3 DERECE 2001 ITRF96")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2001453");
				} else 
				//There is a conflict in databases about ED50 and ITRF descriptions
				if (crs.toWKT().contains("TM 27 3 DERECE")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2000273");
				} else if (crs.toWKT().contains("TM 30 3 DERECE")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2000303");
				} else if (crs.toWKT().contains("TM 33 3 DERECE")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2000333");
				}  else if (crs.toWKT().contains("TM 36 3 DERECE")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2000363");
				}  else if (crs.toWKT().contains("TM 39 3 DERECE")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2000393");
				} else if (crs.toWKT().contains("TM 42 3 DERECE")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2000423");
				} else if (crs.toWKT().contains("TM 45 3 DERECE")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2000453");
				} else 
					if (crs.toWKT().contains("TM 27 3 DERECE 2001")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2001273");
				} else if (crs.toWKT().contains("TM 30 3 DERECE 2001")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2001303");
				} else if (crs.toWKT().contains("TM 33 3 DERECE 2001")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2001333");
				}  else if (crs.toWKT().contains("TM 36 3 DERECE 2001")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2001363");
				}  else if (crs.toWKT().contains("TM 39 3 DERECE 2001")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2001393");
				} else if (crs.toWKT().contains("TM 42 3 DERECE 2001")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2001423");
				} else if (crs.toWKT().contains("TM 45 3 DERECE 2001")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2001453");
				} else if (crs.toWKT().contains("TM 27 3")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2319");
				} else if (crs.toWKT().contains("TM 30 3")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2320");
				} else if (crs.toWKT().contains("TM 33 3")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2321");
				}  else if (crs.toWKT().contains("TM 36 3")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2322");
				}  else if (crs.toWKT().contains("TM 39 3")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2323");
				} else if (crs.toWKT().contains("TM 42 3")) {
					
					resultCS = new SmpCoordinateSystem("EPSG:2324");
				}
			}
		} else {
			System.out.println("Error on converting CRS to ICRS !!! crs can not be null !!!");
		}
		return resultCS;
	}
	
}
