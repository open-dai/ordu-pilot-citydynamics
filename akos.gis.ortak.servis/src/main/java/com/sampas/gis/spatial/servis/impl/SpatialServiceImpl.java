package com.sampas.gis.spatial.servis.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sampas.gis.ortak.basic.model.SmpBasicException;
import com.sampas.gis.ortak.basic.model.datastore.SmpWFSDataStore;
import com.sampas.gis.ortak.basic.model.geometry.SmpBoundingBox;
import com.sampas.gis.ortak.basic.model.geometry.SmpCoordinate;
import com.sampas.gis.ortak.basic.model.geometry.SmpGeometry;
import com.sampas.gis.ortak.basic.model.geometry.SmpPoint;
import com.sampas.gis.ortak.basic.model.returntype.SmpLayerReturnType;
import com.sampas.gis.ortak.basic.model.returntype.SmpWFSDataStoreReturnType;
import com.sampas.gis.ortak.basic.model.spatial.SmpCoordinateSystem;
import com.sampas.gis.ortak.basic.model.spatial.SmpCoordinateSystems;
import com.sampas.gis.ortak.basic.model.spatial.SmpFeature;
import com.sampas.gis.ortak.basic.model.spatial.SmpFeatureAttribute;
import com.sampas.gis.ortak.basic.model.spatial.SmpFeatureType;
import com.sampas.gis.ortak.basic.model.spatial.SmpLayer;
import com.sampas.gis.ortak.basic.model.spatial.SmpLayerAttribute;
import com.sampas.gis.ortak.basic.model.spatial.SmpLayerDefinitation;
import com.sampas.gis.ortak.basic.model.spatial.SmpLayerName;
import com.sampas.gis.spatial.servis.ISpatialService;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.maplayer.ILayerAttribute;
import com.sampas.socbs.ows.services.IOGCWFSBASE.WfsOutputFormat;
import com.sampas.socbs.ows.services.impl.OGCWFS100Service;
import com.sampas.socbs.ows.services.returntypes.GetFeatureResult;


public class SpatialServiceImpl implements ISpatialService {
	
	private HashMap<SmpWFSDataStore, OGCWFS100Service> wfsDataStoresMap = new HashMap<SmpWFSDataStore, OGCWFS100Service>();
	
	@Override
 	public SmpWFSDataStoreReturnType getWFSDataStore(URL wfsDataStoreURL) {
		
		SmpWFSDataStoreReturnType resultWFSDataStoreReturnType = new SmpWFSDataStoreReturnType();
		
		try {
		
			if (wfsDataStoreURL != null) {
				
				SmpWFSDataStore storedDataStore = getWFSDataStoreFromURL(wfsDataStoreURL);
				
				if (storedDataStore == null) {
					
					OGCWFS100Service newWFSService = new OGCWFS100Service(wfsDataStoreURL, WfsOutputFormat.gml2);
					
					if (newWFSService != null) {
						
						List<com.sampas.socbs.core.maplayer.impl.SmpLayer> layers = newWFSService.getLayerDefinitions();
						
						if (layers != null && layers.size() != 0) {
							
							List<SmpLayerDefinitation> newLayerDefinitations = new ArrayList<SmpLayerDefinitation>();
							
							for (com.sampas.socbs.core.maplayer.impl.SmpLayer tempLayer : layers) {
								
								SmpLayer tempSmpLayer = getSmpLayerFromLayer(tempLayer);
								
								if (tempSmpLayer != null) {
									
									SmpLayerDefinitation newLayerDefinitation = new SmpLayerDefinitation(tempSmpLayer.getLayerName(), tempSmpLayer.getBoundingBox(), tempSmpLayer.getCoordinateSystem());
									
									newLayerDefinitations.add(newLayerDefinitation);
								} else {
									
									resultWFSDataStoreReturnType.setException(new SmpBasicException("Could not convert layer to basic layer !!!"));
								}
							}
							SmpWFSDataStore newWFSDataStore = new SmpWFSDataStore();
							
							newWFSDataStore.setWfsDataStoreURL(wfsDataStoreURL);
							
							newWFSDataStore.setLayerDefinitions(newLayerDefinitations);
							
							resultWFSDataStoreReturnType.setWfsDataStore(newWFSDataStore);
							
							getWfsDataStoresMap().put(newWFSDataStore, newWFSService);
						} else {
							
							resultWFSDataStoreReturnType.setException(new SmpBasicException("Could not found any layer !!!"));
						}
					} else {
						
						resultWFSDataStoreReturnType.setException(new SmpBasicException("Error on creating WFS datastore in null !!!"));
					}
				} else {
					
					resultWFSDataStoreReturnType.setWfsDataStore(storedDataStore);
					
					return resultWFSDataStoreReturnType;
				}
			} else {
				
				resultWFSDataStoreReturnType.setException(new SmpBasicException("WFS data url couldn't be empty !!!"));
			}
		} catch (Exception ex) {
			
			resultWFSDataStoreReturnType.setException(new SmpBasicException("Error on creating WFS datastore !!! ERROR : " + ex.getMessage()));
			
			ex.printStackTrace();
		}
		return resultWFSDataStoreReturnType;
	}
	
	@SuppressWarnings("null")
	@Override
	public SmpLayerReturnType getLayerFromWFSDataStore(SmpWFSDataStore wfsDataStore, SmpLayerName layerName, SmpBoundingBox boundingBox) {

		SmpLayerReturnType resultLayerReturnType = new SmpLayerReturnType();
		
		try {
			
			if (wfsDataStore == null || layerName == null) {
				
				resultLayerReturnType.setException(new SmpBasicException("WFS data store or layer name couldn't be empty !!!"));
			} else {
				
				OGCWFS100Service wfsService = getWFSServiceFromWFSDataStore(wfsDataStore);
				
				if (wfsService == null) {
					
					resultLayerReturnType.setException(new SmpBasicException("Related wfs service couldn't find !!!"));
				} else {
					
					com.sampas.socbs.core.maplayer.impl.SmpLayer layer = getLayerFromWFSService(wfsService, layerName);
					
					if (layer != null) {
						
						IFeatureCollection features = null;
						
						if (boundingBox == null) {
							
							GetFeatureResult featureResult = wfsService.getFeatureByTypeName(layer);
							
							if (featureResult != null) {
								
								features = featureResult.getFeatures();
							} else {
								
								resultLayerReturnType.setException(new SmpBasicException("Error on getting features by name !!! ERROR : " + featureResult.getException()));
							}
						} else {
							
							SmpBoundingRectangle boundingRectangle = new SmpBoundingRectangle(boundingBox.getMinsPoint().getCoordinate().getX(), boundingBox.getMinsPoint().getCoordinate().getY(), boundingBox.getMaxsPoint().getCoordinate().getX(), boundingBox.getMaxsPoint().getCoordinate().getY());
							
							GetFeatureResult featureResult = wfsService.getFeatureByBbox(layer, boundingRectangle);
							
							if (featureResult != null) {
								
								features = featureResult.getFeatures();
							} else {
								
								resultLayerReturnType.setException(new SmpBasicException("Error on getting features by bbox !!! ERROR : " + featureResult.getException()));
							}
						}
						if (features != null) {
							
							SmpLayer resultLayer = getSmpLayerFromLayer(layer);
							
							List<SmpFeature> layerFeatureList = new ArrayList<SmpFeature>();
							
							List<SmpFeatureAttribute> featureAttributes;
							
							SmpFeature newSmpFeature;
							
							SmpFeatureType newSmpFeatureType;
							
							try {
							
								for (int featureCntr = 0; featureCntr < features.getFeaturesCount(); featureCntr++) {
									
									newSmpFeature = new SmpFeature();
									
									newSmpFeatureType = new SmpFeatureType();
									
									featureAttributes = new ArrayList<SmpFeatureAttribute>();
									
									for (ILayerAttribute templayerAttribute : layer.getSmpLayerAttributes()) {
										
										if (!templayerAttribute.getAttributeName().toLowerCase().equals("geom") && !templayerAttribute.getAttributeName().toLowerCase().equals("geometry")) {
											
											SmpFeatureAttribute newFeatureAttribute = new SmpFeatureAttribute();
											
											newFeatureAttribute.setName(templayerAttribute.getAttributeName());
											
											newFeatureAttribute.setValue(features.getFeatureAt(featureCntr).getAttribute(templayerAttribute.getAttributeName()));
											
											featureAttributes.add(newFeatureAttribute);
										}
									}
									newSmpFeatureType.setFeatureAttributes(featureAttributes);
									
									//newSmpFeatureType.setNamespace(new URI(layer.getName()));
									
									newSmpFeatureType.setTypeName(layer.getName());
									
									newSmpFeature.setFeatureType(newSmpFeatureType);
									
									List<SmpCoordinate> coordinates;
									
									if (!features.getFeatureAt(featureCntr).isGeometryEmpty()) {
										
										coordinates = new ArrayList<SmpCoordinate>();
										
										for (ICoordinate tempCoordinate : features.getFeatureAt(featureCntr).getDefaultGeometry().getCoordinates()) {
											
											SmpCoordinate newCoordinate = new SmpCoordinate(tempCoordinate.getX(), tempCoordinate.getY());
											
											coordinates.add(newCoordinate);
										}
										newSmpFeature.setGeometry(new SmpGeometry(resultLayer.getCoordinateSystem(), coordinates));
									}
									layerFeatureList.add(newSmpFeature);
								}
							} catch (Exception ex) {
								
								resultLayerReturnType.setException(new SmpBasicException("Error on converting features to basic !!! ERROR : " + ex.getMessage()));
								
								ex.printStackTrace();
							}
							resultLayer.setFeatures(layerFeatureList);
							
							resultLayerReturnType.setLayer(resultLayer);
						}
					} else {
						
						resultLayerReturnType.setException(new SmpBasicException("Related layer couldn't find from wfs service !!!"));
					}
				}
			}
		} catch (Exception ex) {
			
			resultLayerReturnType.setException(new SmpBasicException("Error on creating feature layer from WFS DataStore !!!"));
		}
		return resultLayerReturnType;
	}

	private SmpLayer getSmpLayerFromLayer(com.sampas.socbs.core.maplayer.impl.SmpLayer layer) {
		
		try {
			
			SmpLayer newSmpLayer = new SmpLayer();
			
			newSmpLayer.setLayerName(new SmpLayerName(layer.getName()));
			
			if (layer.getCoordinateSystem() != null && layer.getCoordinateSystem().getEPSGCode() != null) {
				
				newSmpLayer.setCoordinateSystem(new SmpCoordinateSystem(layer.getCoordinateSystem().getEPSGCode()));
			} else {
				
				newSmpLayer.setCoordinateSystem(new SmpCoordinateSystem(SmpCoordinateSystems.WGS_84));
			}
			if (layer.getExtent() != null) {
				
				SmpCoordinate minsCoordinate = new SmpCoordinate(layer.getExtent().getMinX(), layer.getExtent().getMinY());
				
				SmpCoordinate maxsCoordinate = new SmpCoordinate(layer.getExtent().getMaxX(), layer.getExtent().getMaxY());
				
				SmpBoundingBox tempLayerBBox = new SmpBoundingBox(new SmpPoint(minsCoordinate, newSmpLayer.getCoordinateSystem()), new SmpPoint(maxsCoordinate, newSmpLayer.getCoordinateSystem()));
				
				newSmpLayer.setBoundingBox(tempLayerBBox);
			}
			if (layer.getSmpLayerAttributes() != null && layer.getSmpLayerAttributes().size() != 0) {
				
				List<SmpLayerAttribute> newLayerAttributes = new ArrayList<SmpLayerAttribute>();
				
				for (ILayerAttribute tempLayerAttribute : layer.getSmpLayerAttributes()) {
					
					SmpLayerAttribute newLayerAttribute = new SmpLayerAttribute();
					
					newLayerAttribute.setName(tempLayerAttribute.getAttributeName());
					
					newLayerAttributes.add(newLayerAttribute);
				}
				newSmpLayer.setLayerAttributes(newLayerAttributes);
			}
			return newSmpLayer;
		} catch(Exception ex) {
			
			System.err.println("Error on converting Layer to SmpLayer !!! ERROR : " + ex.getMessage());
			
			ex.printStackTrace();
			
			return null;
		}
	}

	private com.sampas.socbs.core.maplayer.impl.SmpLayer getLayerFromWFSService(OGCWFS100Service wfsService, SmpLayerName layerName) {

		if (wfsService != null) {

			for (OGCWFS100Service tempWFSService : getWfsDataStoresMap().values()) {
				
				if (tempWFSService.equals(wfsService)) {
					
					List<com.sampas.socbs.core.maplayer.impl.SmpLayer> layers = wfsService.getLayerDefinitions();
					
					for (com.sampas.socbs.core.maplayer.impl.SmpLayer tempLayer : layers) {
						
						if (tempLayer.getName().equals(layerName.getLayerName())) {
							
							return tempLayer;
						}
					}
				}
			}
		}
		return null;
	}

	private SmpWFSDataStore getWFSDataStoreFromURL(URL wfsDataStoreURL) {

		if (wfsDataStoreURL != null) {
			
			for (SmpWFSDataStore tempWFSDataStore : getWfsDataStoresMap().keySet()) {
				
				if (tempWFSDataStore.getWfsDataStoreURL().equals(wfsDataStoreURL)) {
					
					return tempWFSDataStore;
				}
			}
		}
		return null;
	}
	
	private OGCWFS100Service getWFSServiceFromWFSDataStore(SmpWFSDataStore wfsDataStore) {

		if (wfsDataStore != null) {
			
			for (SmpWFSDataStore tempWFSDataStore : getWfsDataStoresMap().keySet()) {
				
				if (tempWFSDataStore.getWfsDataStoreURL().equals(wfsDataStore.getWfsDataStoreURL())) {
					
					return getWfsDataStoresMap().get(tempWFSDataStore);
				}
			}
		}
		return null;
	}
	
	public Map<SmpWFSDataStore, OGCWFS100Service> getWfsDataStoresMap() {
		return wfsDataStoresMap;
	}

	public void setWfsDataStoresMap(HashMap<SmpWFSDataStore, OGCWFS100Service> wfsDataStoresMap) {
		this.wfsDataStoresMap = wfsDataStoresMap;
	}

}