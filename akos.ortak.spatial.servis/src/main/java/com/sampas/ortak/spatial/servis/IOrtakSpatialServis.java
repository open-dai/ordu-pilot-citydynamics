package com.sampas.ortak.spatial.servis;

import java.util.List;
import java.util.Map;

import com.sampas.gis.ortak.servis.IGisOrtakServis;
import com.sampas.ortak.spatial.servis.tools.IAppFeatureLayer;
import com.sampas.ortak.spatial.servis.tools.impl.OrtakSpatialLayers;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.geolsa.servis.IGeolsaServis;


public interface IOrtakSpatialServis {
	
	public IGeolsaServis getGeolsaServis();
	
	public IAppFeatureLayer getAppLayerFromName(String layerName);
	
	public boolean updateGeometryAttributes(String featureTypeName, Map<Long,IGeometry> idGeometryMap);
	
	public boolean updateGeometryAttributes(String sourceFeatureTypeName, String targetFeatureTypeName, Map<Long, Long> corresponderIdMap);
	
	public boolean saveObjects(Object[] objects);
	
	public boolean saveObjectsFromList(List<Object> objectList);
	
	public IFeatureCollection getFeaturesIntersectsWithAnyAddress(IFeature feature, IFeatureLayer featuresLayer);
	
	public IFeatureCollection getFeaturesIntersectsWithAnyBuildArea(IFeature feature, IFeatureLayer featuresLayer);
	
	public IFeatureCollection getFeaturesIntersectsWithAnyBuilding(IFeature feature, IFeatureLayer featuresLayer);
	
	public IFeatureCollection getFeaturesIntersectsWithAnyDistrict(IFeature feature, IFeatureLayer featuresLayer);
	
	public IFeatureCollection getFeaturesIntersectsWithAnyParcel(IFeature feature, IFeatureLayer featuresLayer) ;
		
	
	public boolean isFeatureIntersectWithAnyAddress(IFeature feature, IFeatureLayer featuresLayer);
	
	public boolean isFeatureIntersectWithAnyBuildArea(IFeature feature, IFeatureLayer featuresLayer);
	
	public boolean isFeatureIntersectWithAnyBuilding(IFeature feature, IFeatureLayer featuresLayer);
	
	public boolean isFeatureIntersectWithAnyDistrict(IFeature feature, IFeatureLayer featuresLayer);
	
	public IFeatureCollection getFeaturesWithIDs(Long[] relatedIDs, String featureLayerName);
	
	public IFeature getFeaturesWithID(Long relatedID, String featureLayerName);
	
	public OrtakSpatialLayers getLayers();
	
	public IFeatureCollection getFeatureIntersectsWithAnyRelated(IGeometry geometry, IFeatureLayer featuresLayer);
	
	public IGisOrtakServis getGisOrtakServis();
	
}