package com.sampas.socbs.core.coordinatesystem;

import org.geotools.feature.IllegalAttributeException;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.IPoint;


public interface ICoordinateSystemTransformers {

	public IFeatureCollection FeaturesCoordinateTransformer(IFeatureCollection inputFeatures, ICoordinateSystem sourceCoordRefSys, ICoordinateSystem targetCoordRefSys) throws FactoryException, MismatchedDimensionException, TransformException, IllegalAttributeException;
	
	public IFeature FeatureCoordinateTransformer(IFeature inputFeature, ICoordinateSystem sourceCoordRefSys, ICoordinateSystem targetCoordRefSys) throws FactoryException, MismatchedDimensionException, TransformException, IllegalAttributeException;
	
	public IGeometry SmpGeometryCoordinateTransformer(IGeometry smpGeometry, ICoordinateSystem sourceCoordRefSys, ICoordinateSystem targetCoordRefSys) throws NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException, IllegalAttributeException;
	
	public IEnvelope SmpBoundingRectangleCoordinateTransformer(IEnvelope smpBBox, ICoordinateSystem sourceCoordRefSys, ICoordinateSystem targetCoordRefSys) throws NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException, IllegalAttributeException;
	
	public IPoint SmpGeometryCoordinateTransformer(IPoint smpPoint, ICoordinateSystem sourceCoordRefSys, ICoordinateSystem targetCoordRefSys) throws NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException, IllegalAttributeException;
	
	public ICoordinateSystem getCoordinateSystemFromCRS(CoordinateReferenceSystem crs);
}
