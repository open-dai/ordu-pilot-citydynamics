package com.sampas.ortak.spatial.servis.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sampas.gis.ortak.servis.IGisOrtakServis;
import com.sampas.ortak.spatial.dao.IOrtakSpatialDAO;
import com.sampas.ortak.spatial.generator.servis.IDBConnectionServis;
import com.sampas.ortak.spatial.servis.IOrtakSpatialServis;
import com.sampas.ortak.spatial.servis.tools.IAppFeatureLayer;
import com.sampas.ortak.spatial.servis.tools.impl.OrtakSpatialLayers;
import com.sampas.socbs.base.spatial.query.services.impl.SmpIntersectsQuery;
import com.sampas.socbs.base.spatial.query.services.returntypes.IGeomFiltersReturnType;
import com.sampas.socbs.base.spatial.query.services.returntypes.impl.GeomFiltersReturnType;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.CoordinateSystemTransformers;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureID;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureCollection;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.impl.SmpGeometry;
import com.sampas.socbs.core.gisdatabase.tools.servis.IGisDatabaseServis;
import com.sampas.socbs.core.gisdatabase.tools.servis.impl.GisDatabaseServis;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.impl.SmpLayerNames;
import com.sampas.socbs.core.processing.services.IFeatureCreator;
import com.sampas.socbs.core.processing.services.impl.SmpFeatureCreator;
import com.sampas.socbs.core.tools.IFeatureIDTools;
import com.sampas.socbs.core.tools.impl.FeatureIDTools;
import com.sampas.socbs.geolsa.model.MdAppLayer;
import com.sampas.socbs.geolsa.model.MdFeatureThemeStyle;
import com.sampas.socbs.geolsa.model.MdLayerAttributeVisibleName;
import com.sampas.socbs.geolsa.model.MdRasterThemeStyle;
import com.sampas.socbs.geolsa.model.MdSmpGISApp;
import com.sampas.socbs.geolsa.servis.IGeolsaServis;
import com.sampas.socbs.geolsa.servis.impl.GeolsaServis;


public class OrtakSpatialServis implements IOrtakSpatialServis {

	private IGeolsaServis geolsaServis;
	
	private IOrtakSpatialDAO ortakSpatialDAO;
	
	private IDBConnectionServis dbConnectionServis;
	
	private MdSmpGISApp application = null;

	private MdSmpGISApp tempApp = null;
	
	private OrtakSpatialLayers layers = null;
	
	private IGisOrtakServis gisOrtakServis = null;


	public OrtakSpatialServis(String applicationStr) {

		//this.geolsaServis = new GeolsaServis();
	//	List<MdSmpGISApp> applications22 = geolsaServis.readAllCBSUygulamalariMetadata();
	//	System.out.println("BUDUR ISTE : "+applications22.size());
		//setGeolsaServis(geolsaServis);
		
		tempApp = new MdSmpGISApp();
		
		this.tempApp.setAppName(applicationStr);
		
		//checkApplication();
	}
	
	public IFeatureCollection getFeaturesIntersectsWithAnyAddress(IFeature feature, IFeatureLayer featuresLayer) {
		
		if (checkApplication()) {
			
			return (getIntersectionResult(this.layers.getAppLayerByName(OrtakSpatialLayers.ADDRESS_LAYER_PROP_NAME), feature, featuresLayer));
		} else {
			
			System.out.println("Error on cheking application");
			return null;
		}
	}

	public IFeatureCollection getFeaturesIntersectsWithAnyBuildArea(IFeature feature, IFeatureLayer featuresLayer) {
		
		if (checkApplication()) {
			
			return (getIntersectionResult(this.layers.getAppLayerByName(OrtakSpatialLayers.BUILDAREA_LAYER_PROP_NAME), feature, featuresLayer));
		} else {
			
			System.out.println("Error on cheking application");
			return null;
		}
	}

	public IFeatureCollection getFeaturesIntersectsWithAnyBuilding(IFeature feature, IFeatureLayer featuresLayer) {

		if (checkApplication()) {
			
			return (getIntersectionResult(this.layers.getAppLayerByName(OrtakSpatialLayers.BUILDING_LAYER_PROP_NAME), feature, featuresLayer));
		} else {
			
			System.out.println("Error on cheking application");
			return null;
		}
	}

	public IFeatureCollection getFeaturesIntersectsWithAnyDistrict(IFeature feature, IFeatureLayer featuresLayer) {
		
		if (checkApplication()) {
			
			return (getIntersectionResult(this.layers.getAppLayerByName(OrtakSpatialLayers.DISTRICT_LAYER_PROP_NAME), feature, featuresLayer));
		} else {
			
			System.out.println("Error on cheking application");
			return null;
		}
	}

	public boolean isFeatureIntersectWithAnyAddress(IFeature feature, IFeatureLayer featuresLayer) {

		if (checkApplication()) {
			
			if (getIntersectionResult(this.layers.getAppLayerByName(OrtakSpatialLayers.ADDRESS_LAYER_PROP_NAME), feature, featuresLayer).getFeaturesCount() > 0) {
				
				return true;
			} else {
				return false;
			}			
		} else {
			
			System.out.println("Error on cheking application");
			return false;
		}
	}

	public boolean isFeatureIntersectWithAnyBuildArea(IFeature feature, IFeatureLayer featuresLayer) {

		if (checkApplication()) {
			
			IFeatureCollection features = getIntersectionResult(this.layers.getAppLayerByName(OrtakSpatialLayers.BUILDAREA_LAYER_PROP_NAME), feature, featuresLayer);
			
			if (features != null &&  features.getFeaturesCount() > 0) {
				
				return true;
			} else {
				return false;
			}
		} else {
			
			System.out.println("Error on cheking application");
			return false;
		}
	}
	
	public boolean isFeatureIntersectWithAnyBuilding(IFeature feature, IFeatureLayer featuresLayer) {
		
		if (checkApplication()) {
			
			IFeatureCollection features = getIntersectionResult(this.layers.getAppLayerByName(OrtakSpatialLayers.BUILDING_LAYER_PROP_NAME), feature, featuresLayer);
			
			if (features != null && features.getFeaturesCount() > 0) {
				
				return true;
			} 
			return false;
		} else {
			
			System.out.println("Error on cheking application");
			return false;
		}
	}
	
	public boolean isFeatureIntersectWithAnyDistrict(IFeature feature, IFeatureLayer featuresLayer) {
		
		if (checkApplication()) {
			
			IFeatureCollection features = getIntersectionResult(this.layers.getAppLayerByName(OrtakSpatialLayers.DISTRICT_LAYER_PROP_NAME), feature, featuresLayer);
			
			if (features != null && features.getFeaturesCount() > 0) {
				
				return true;
			} else {
				return false;
			}
		} else {
			
			System.out.println("Error on cheking application");
			return false;
		}
	}

	
	public IFeatureCollection getFeaturesIntersectsWithAnyParcel(IFeature feature, IFeatureLayer featuresLayer) {
		
		if (checkApplication()) {
			
			return (getIntersectionResult(this.layers.getAppLayerByName(OrtakSpatialLayers.PARCEL_LAYER_PROP_NAME), feature, featuresLayer));
		} else {
			
			System.out.println("Error on cheking application");
			return null;
		}
	}
	
	
	private IFeatureCollection getIntersectionResult(IAppFeatureLayer relatedLayer, IFeature feature, IFeatureLayer featuresLayer) {
		
		if (relatedLayer.getFeatureLayer().getCoordinateSystem() == null || featuresLayer.getCoordinateSystem() == null) {
			
			System.out.println("Error on finding coordinate system for one or two of related layer(s) !");
			
			return null;
		}
		IFeature tempFeature = null;
		
		if (relatedLayer.getFeatureLayer().getCoordinateSystem() != null && featuresLayer.getCoordinateSystem() != null && !relatedLayer.getFeatureLayer().getCoordinateSystem().getEPSGCode().equals(featuresLayer.getCoordinateSystem().getEPSGCode())) {
			
			try {
				
				ICoordinateSystemTransformers transformer = new CoordinateSystemTransformers();
				
				IFeatureCreator featureCreator = new SmpFeatureCreator();
				
				tempFeature = featureCreator.cloneFeatureCreator(feature);
				
				transformer.FeatureCoordinateTransformer(tempFeature, featuresLayer.getCoordinateSystem(), relatedLayer.getFeatureLayer().getCoordinateSystem());				
			} catch (Exception ex) {
				
				System.out.println("Error on transforming coordinates beetween coordinate Systems ! ERROR : " + ex);
			}
		} else {
		
			tempFeature = feature;
		}
		
		IGeomFiltersReturnType filterResult = new GeomFiltersReturnType();
		
		SmpIntersectsQuery intersectsQuery = new SmpIntersectsQuery();
		
		filterResult = intersectsQuery.intersects(relatedLayer.getFeatureLayer(), tempFeature);
		
		return filterResult.getFeatures();
	}
	
	private boolean checkApplication() {
		
		if (this.application == null) {
		
			MdSmpGISApp appMeta = this.geolsaServis.readCBSUygulamalariMetadataByCriteria(tempApp);
			
			if (appMeta != null) {
				
				this.application = appMeta;
				
				if (this.layers == null) {
					
					this.layers = new OrtakSpatialLayers(application);
				}
				return true;
			} else {
				
				System.out.println("Error on Finding Application Name : "+ tempApp.getAppName() + " Conrol needed XML for Application Name Value !");
				
				return false;
			}
		} else {
			
			if (this.layers == null) {
				
				this.layers = new OrtakSpatialLayers(application);
			}
			return true;
		}
	}
	
	public IAppFeatureLayer getAppLayerFromName(String layerName) {
		
		if (layerName != null && layerName != "") {
			if (checkApplication()) {
				return (this.layers.getAppLayerByName(layerName));
			}
		} 
		return null;
	}

	public boolean updateGeometryAttributes(String featureTypeName, Map<Long,IGeometry> idGeometryMap) {

		if (featureTypeName != null && featureTypeName != "" && idGeometryMap != null && idGeometryMap.size() > 0) {
			
			ILayerNames layerNames = new SmpLayerNames();
			
			layerNames.addLayerName(featureTypeName);

			IFeatureLayerProvider layerProvider = new SmpFeatureLayerProviderSrv();
			
			List<IFeatureLayer> featureLayers = layerProvider.getLayers(getDbConnectionServis().getFeatureDataStore(), layerNames);
			
			if (featureLayers.size() > 0) {
			
				IFeatureIDTools idTools = new FeatureIDTools();
				
				IFeatureCollection features = new SmpFeatureCollection();
				
				for (Long tempFeatureId : idGeometryMap.keySet()) {
				
					IFeature tempFeature = featureLayers.get(0).getFeaturesbyFID(idTools.createFeatureIDWithFTypeAndID(featureTypeName, tempFeatureId));
					
					if (tempFeature == null) {
					
						System.out.println("Error finding feature from featureId and update feature geometry can't made !");
						
						return false;
					}
					try {
						
						((SmpFeature)tempFeature).setDefaultGeometry((SmpGeometry)idGeometryMap.get(tempFeatureId));
						
						features.addFeature(tempFeature);
					} catch (Exception ex) {
						
						System.err.println("Error on writing geometry to temp feature ! ERROR: " + ex);
						
						return false;
					} 
				}
				IGisDatabaseServis gisDatabaseServis = new GisDatabaseServis(getDbConnectionServis().getFeatureDataStore());
				
				return gisDatabaseServis.updateFeatures(features);
			} else {
				
				System.err.println("Error on finding related feature layer ! Check connected DB for layers exist; " + this.getAppLayerFromName(OrtakSpatialLayers.STREET_LAYER_PROP_NAME).getFeatureLayer().getName() + " and " + featureTypeName);
				
				return false;
			}
		} else {
			
			System.err.println("Error reletedObject ID not setted !");
			
			return false;
		}
	}
	
	public boolean updateGeometryAttribute(String sourceFeatureTypeName, String targetFeatureTypeName, Long sourceFeatureId, Long targetFeatureId) {
		
		if (sourceFeatureTypeName != null && sourceFeatureTypeName != "" && sourceFeatureId != null && sourceFeatureId > 0 && targetFeatureId != null && targetFeatureId > 0) {
			
			IFeatureCollection sourceFeatureList = getFeaturesWithIDs(new Long[]{sourceFeatureId}, sourceFeatureTypeName);
			
			if (sourceFeatureList != null && sourceFeatureList.getFeaturesCount() == 1) {
				
				if (sourceFeatureList.getFeatureAt(0).getDefaultGeometry() != null) {
					
					IFeatureCollection targetFeatureList = getFeaturesWithIDs(new Long[]{targetFeatureId}, targetFeatureTypeName);
					
					if (targetFeatureList != null && targetFeatureList.getFeaturesCount() == 1) {
						
						Map<Long, IGeometry> idGeometryMap = new HashMap<Long, IGeometry>();
						
						idGeometryMap.put(targetFeatureId, sourceFeatureList.getFeatureAt(0).getDefaultGeometry());
						
						return updateGeometryAttributes(targetFeatureTypeName, idGeometryMap);
					}
				}
			}
		}
		return false;
	}
	
	public boolean updateGeometryAttributes(String sourceFeatureTypeName, String targetFeatureTypeName, Map<Long, Long> sourceTargetCorresponderMap) {

		if (sourceFeatureTypeName != null && targetFeatureTypeName != null && sourceTargetCorresponderMap != null && sourceFeatureTypeName != "" && targetFeatureTypeName != "" && sourceTargetCorresponderMap.size() > 0) {
			
			FeatureIDTools featureIDTools = new FeatureIDTools(); 
			
			ILayerNames layerNames = new SmpLayerNames();
			
			layerNames.addLayerName(sourceFeatureTypeName);
			
			layerNames.addLayerName(targetFeatureTypeName);
			
			IFeatureLayerProvider layerProvider = new SmpFeatureLayerProviderSrv();
			
			List<IFeatureLayer> featureLayers = layerProvider.getLayers(getDbConnectionServis().getFeatureDataStore(), layerNames);
			
			IFeatureLayer sourceLayer = null;
			
			IFeatureLayer targetLayer = null;
			
			if (featureLayers != null && featureLayers.size() == 2) {
				
				for (IFeatureLayer tempFeatureLayer : featureLayers) {
					
					if (featureIDTools.getFeatureTypeFromLayerName(tempFeatureLayer.getName()).equals(sourceFeatureTypeName)) {
						
						sourceLayer = tempFeatureLayer;
					}
					if (featureIDTools.getFeatureTypeFromLayerName(tempFeatureLayer.getName()).equals(targetFeatureTypeName)) {
						
						targetLayer = tempFeatureLayer;
					}
				}
				if (sourceLayer != null && targetLayer != null) {
					
					List<IFeatureID> sourceFeatureIdList = new ArrayList<IFeatureID>();
					
					List<IFeatureID> targetFeatureIdList = new ArrayList<IFeatureID>();
					
					for (Long tempSourceFeatureId : sourceTargetCorresponderMap.keySet()) {
						
						sourceFeatureIdList.add(featureIDTools.createFeatureIDWithFTypeAndID(sourceFeatureTypeName, tempSourceFeatureId));
						
						targetFeatureIdList.add(featureIDTools.createFeatureIDWithFTypeAndID(targetFeatureTypeName, sourceTargetCorresponderMap.get(tempSourceFeatureId)));
					}
					IFeatureCollection sourceFeatureCollection = sourceLayer.getFeaturesbyFIDs(sourceFeatureIdList);
					
					IFeatureCollection targetFeatureCollection = targetLayer.getFeaturesbyFIDs(targetFeatureIdList);
					
					if (sourceFeatureCollection != null && targetFeatureCollection != null && sourceFeatureCollection.getFeaturesCount() == targetFeatureCollection.getFeaturesCount()) {
						
						IFeatureCollection featureCollection = new SmpFeatureCollection();
						
						for (int featureCntr = 0; featureCntr < sourceFeatureCollection.getFeaturesCount(); featureCntr++) {
	
							try {
								if (sourceFeatureCollection.getFeatureAt(featureCntr).getDefaultGeometry() != null) {
								
									((SmpFeature)targetFeatureCollection.getFeatureAt(featureCntr)).setDefaultGeometry((SmpGeometry)sourceFeatureCollection.getFeatureAt(featureCntr).getDefaultGeometry());
									
									featureCollection.addFeature(targetFeatureCollection.getFeatureAt(featureCntr));
								}
							} catch (Exception ex) {

								System.err.println("Error on assigning geometry to feature !");
							}
						}
						IGisDatabaseServis gisDatabaseServis = new GisDatabaseServis(getDbConnectionServis().getFeatureDataStore());
						
						return gisDatabaseServis.updateFeatures(featureCollection);
					}
				}
			}
		}
		return false;
	}
	
	public boolean saveObjects(Object[] objects) {

		return this.ortakSpatialDAO.saveObjects(objects);
	}
	
	public boolean saveObjectsFromList(List<Object> objectList) {

		Object[] tempObjectArray = new Object[objectList.size()];
		
		for (int objCntr = 0; objCntr < tempObjectArray.length; objCntr++) {
			
			tempObjectArray[objCntr] = objectList.get(objCntr);
		}
		return this.ortakSpatialDAO.saveObjects(tempObjectArray);
	}
	
	public IFeatureCollection getFeaturesWithIDs(Long[] relatedIDs, String featureLayerName) {
		
		ILayerNames layerNames = new SmpLayerNames();
		
		layerNames.addLayerName(featureLayerName);
		
		IFeatureLayerProvider layerProvider = new SmpFeatureLayerProviderSrv();
		System.out.println("GELEN : "+layerNames.getLayerNames().size());
		List<IFeatureLayer> featureLayers = layerProvider.getLayers(dbConnectionServis.getFeatureDataStore(), layerNames);
		
		if (featureLayers.size() > 0) {
			
			IFeatureIDTools idTools = new FeatureIDTools();
			
			IFeatureCollection features = new SmpFeatureCollection();
			
			for (int relObjCntr = 0; relObjCntr < relatedIDs.length; relObjCntr++) {
			
				IFeature tempFeature = featureLayers.get(0).getFeaturesbyFID(idTools.createFeatureIDWithFTypeAndID(featureLayerName, relatedIDs[relObjCntr]));
				
				if (tempFeature != null) {
					
					features.addFeature(tempFeature);
				}
			}
			return features;			
		}
		return null;
	}
	
	public IDBConnectionServis getDbConnectionServis() {
		return dbConnectionServis;
	}
	
	public void setDbConnectionServis(IDBConnectionServis dbConnectionServis) {
		this.dbConnectionServis = dbConnectionServis;
	}
	
	public IOrtakSpatialDAO getOrtakSpatialDAO() {
		return ortakSpatialDAO;
	}
	
	public void setOrtakSpatialDAO(IOrtakSpatialDAO ortakSpatialDAO) {
		this.ortakSpatialDAO = ortakSpatialDAO;
	}
	
	public IGeolsaServis getGeolsaServis() {
		return geolsaServis;
	}
	
	public void setGeolsaServis(IGeolsaServis geolsaServis) {
		this.geolsaServis = geolsaServis;
	}
	
	public IGisOrtakServis getGisOrtakServis() {
		return gisOrtakServis;
	}

	public void setGisOrtakServis(IGisOrtakServis gisOrtakServis) {
		this.gisOrtakServis = gisOrtakServis;
	}
	
	public OrtakSpatialLayers getLayers() {
		
		if (application == null) {
		
			checkApplication(); 
		}
		return layers;
	}
	
	private IFeatureCollection getIntersectionResult(IGeometry geometry, IFeatureLayer relatedLayer) {
		
		if (geometry != null && relatedLayer != null ) {
			
			IGeomFiltersReturnType filterResult = new GeomFiltersReturnType();
			
			SmpIntersectsQuery intersectsQuery = new SmpIntersectsQuery();
			
			filterResult = intersectsQuery.intersects(relatedLayer, geometry);
			
			return filterResult.getFeatures();
		} else {
			
			System.out.println("getIntersectionResult function can't call with null arguments");
		}
		return null;
	}
	
	public IFeatureCollection getFeatureIntersectsWithAnyRelated(IGeometry geometry, IFeatureLayer featuresLayer) {

		if (checkApplication()) {
			
			return (getIntersectionResult(geometry, featuresLayer));
		} else {
			
			System.out.println("Error on setting up application !!!");
			
			return null;
		}
	}
	
	
	
	
	
	public IFeature getFeaturesWithID(Long relatedID, String featureLayerName) {
		
		ILayerNames layerNames = new SmpLayerNames();
		
		layerNames.addLayerName(featureLayerName);
		
		IFeatureLayerProvider layerProvider = new SmpFeatureLayerProviderSrv();
		
		IFeatureDataStore _ita = getDbConnectionServis().getFeatureDataStore();
		
		List<IFeatureLayer> featureLayers = layerProvider.getLayers(_ita, layerNames);
		
		if (featureLayers.size() > 0) {
			
			IFeatureIDTools idTools = new FeatureIDTools();
			
			IFeature resultFeature = featureLayers.get(0).getFeaturesbyFID(idTools.createFeatureIDWithFTypeAndID(featureLayerName, relatedID));
						
			return resultFeature;
		}
		return null;
	}

}