package com.sampas.socbs.core.data.providers.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureType;
import org.geotools.referencing.CRS;
import org.geotools.referencing.NamedIdentifier;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
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
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpEnvelope;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayerAttribute;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.ILayersNameAndPD_CRS;
import com.sampas.socbs.core.maplayer.impl.SmpFeatureLayer;
import com.sampas.socbs.core.maplayer.impl.SmpLayerAttribute;
import com.sampas.socbs.core.maplayer.impl.SmpLayerNames;


public class SmpFeatureLayerProviderSrv implements IFeatureLayerProvider {

	//Logger logger = Logger.getLogger("com.sampas.socbs.core.data.providers.impl");
	
	public SmpFeatureLayerProviderSrv() {
		
	}
	
	public ILayerNames getLayerNames(IFeatureDataStore dataStore) {
		
		ILayerNames layerNames = new SmpLayerNames();
		
		try {

			layerNames.setLayerNames(dataStore.getTypeNames());
			
			return (layerNames);
			
		} catch (Exception ex) {
			
			System.out.println("Error on taking layer names !");
			
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<IFeatureLayer> getLayers(IFeatureDataStore dataStore) {
		
		//logger.setLevel(Level.WARNING);
		String[] layerNames = null;
		
		try {
			
			layerNames = dataStore.getTypeNames();			
		} catch (Exception ex) {
			
			System.out.println("Error on taking layer names !");
			return null;
		}
		if (layerNames != null && layerNames.length != 0) {
			
			List<IFeatureLayer> smpLayers = new ArrayList<IFeatureLayer>();
	
			try {
	
				for (int i = 0; i < layerNames.length; i++) {					
					
					smpLayers.add(new SmpFeatureLayer());
					smpLayers.get(i).setName(layerNames[i]);
					
					FeatureSource featureSource = dataStore.getFeatureSource(smpLayers.get(i).getName());
					FeatureType schema = featureSource.getSchema();
					
					if (schema.getDefaultGeometry() != null) {
						
						CoordinateReferenceSystem crs = schema.getDefaultGeometry().getCoordinateSystem();
					
						IEnvelope smpEnvelope = null;
						
						if (featureSource.getBounds() != null) {
							
							smpEnvelope = new SmpEnvelope(featureSource.getBounds().getMinY(), featureSource.getBounds().getMinX(), featureSource.getBounds().getMaxY(), featureSource.getBounds().getMaxX());							
						} 
						
						if (crs != null) {
							if (crs.getIdentifiers().iterator().hasNext()) {					
						
								Iterator<NamedIdentifier> crsIterator = crs.getIdentifiers().iterator();					
								NamedIdentifier namedIdentifier = crsIterator.next();					
								SmpCoordinateSystem smpCoordinateSystem = new SmpCoordinateSystem(namedIdentifier.getCodeSpace() + ":" + namedIdentifier.getCode());
								smpLayers.get(i).setCoordinateSystem(smpCoordinateSystem);
							}
						} else {
							
							//logger.info("User must set CRS");
							System.out.println("Layer: " + smpLayers.get(i).getName() + " (1) has not any CRS");
						}
						smpLayers.get(i).setGeometryColumnName(schema.getDefaultGeometry().getLocalName());
						
						smpLayers.get(i).setExtent(smpEnvelope);
					} else {
						
						System.out.println("Layer: " + smpLayers.get(i).getName() + " has not any geometry");
					}
					//ColumnNamesForLayer columnNamesForLayer = new ColumnNamesForLayer(this.wfsUrl, typeLayers.get(i));
					
					//List<SmpLayerAttribute> smpLayerAttributes = columnNamesForLayer.getAttributesForLayer();
					List<SmpLayerAttribute> smpLayerAttributes = getLayerAttributes(schema);
					
					List<ILayerAttribute> layerAttributes = new ArrayList<ILayerAttribute>();
					
					for (int j = 0; j < smpLayerAttributes.size(); j++) {
						
						layerAttributes.add(smpLayerAttributes.get(j));
					}
					smpLayers.get(i).setSmpLayerAttributes(layerAttributes);			
					//Set dataStore to layer
				    smpLayers.get(i).setDataProvider(dataStore);
				}
				return (smpLayers);
	
			} catch (Exception ex) {
	
				System.out.println("Error on creating layers from source. Try take layers use with ILayerName interface ! ERROR: " + ex);
				
				return (smpLayers);
			}
		} else {
			
			System.out.println("Layer names can't be null or empty !");
			
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<IFeatureLayer> getLayers(IFeatureDataStore dataStore, ILayerNames layerNames) {
		//logger.setLevel(Level.WARNING);
		if (layerNames != null && layerNames.getLayerNames().size() != 0) {
		
			List<IFeatureLayer> smpLayers = new ArrayList<IFeatureLayer>();
	
			try {
	
				for (int i = 0; i < layerNames.getLayerNames().size(); i++) {					
					
					smpLayers.add(new SmpFeatureLayer());
					
					if (layerNames.getLayerName(i).contains(":")) { 
					
						smpLayers.get(i).setNamespace(layerNames.getLayerName(i).split(":")[0]);
					}
					smpLayers.get(i).setName(layerNames.getLayerName(i));
					
					FeatureSource featureSource = dataStore.getFeatureSource(smpLayers.get(i).getName());			
					
					FeatureType schema = featureSource.getSchema();
					
					try {
						
						if (schema.getDefaultGeometry() != null) {
							
							CoordinateReferenceSystem crs = schema.getDefaultGeometry().getCoordinateSystem();
						
							if (featureSource.getBounds() != null) {
							
								SmpEnvelope smpEnvelope = new SmpEnvelope(featureSource.getBounds().getMinY(), featureSource.getBounds().getMinX(), featureSource.getBounds().getMaxY(), featureSource.getBounds().getMaxX());
								
								smpLayers.get(i).setExtent(smpEnvelope);
							}
							if (crs != null) {
								
								if (crs.getIdentifiers().iterator().hasNext()) {					
							
									Iterator<NamedIdentifier> crsIterator = crs.getIdentifiers().iterator();					
									
									NamedIdentifier namedIdentifier = crsIterator.next();					
									
									SmpCoordinateSystem smpCoordinateSystem = new SmpCoordinateSystem(namedIdentifier.getCodeSpace() + ":" + namedIdentifier.getCode());
									
									smpLayers.get(i).setCoordinateSystem(smpCoordinateSystem);
								} else {
									//TODO there is some of layers couldn't find EPSG Code so this part must fix when a full EPSG table find or updated.

									//For custom defined coordinate systems
									if (crs.toWKT().contains("TM 27 3 DERECE ITRF96")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2000273"));
									} else if (crs.toWKT().contains("TM 30 3 DERECE ITRF96")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2000303"));
									} else if (crs.toWKT().contains("TM 33 3 DERECE ITRF96")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2000333"));
									}  else if (crs.toWKT().contains("TM 36 3 DERECE ITRF96")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2000363"));
									}  else if (crs.toWKT().contains("TM 39 3 DERECE ITRF96")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2000393"));
									} else if (crs.toWKT().contains("TM 42 3 DERECE ITRF96")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2000423"));
									} else if (crs.toWKT().contains("TM 45 3 DERECE ITRF96")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2000453"));
									} else if (crs.toWKT().contains("TM 27 3 DERECE 2001 ITRF96")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2001273"));
									} else if (crs.toWKT().contains("TM 30 3 DERECE 2001 ITRF96")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2001303"));
									} else if (crs.toWKT().contains("TM 33 3 DERECE 2001 ITRF96")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2001333"));
									}  else if (crs.toWKT().contains("TM 36 3 DERECE 2001 ITRF96")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2001363"));
									}  else if (crs.toWKT().contains("TM 39 3 DERECE 2001 ITRF96")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2001393"));
									} else if (crs.toWKT().contains("TM 42 3 DERECE 2001 ITRF96")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2001423"));
									} else if (crs.toWKT().contains("TM 45 3 DERECE 2001 ITRF96")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2001453"));
									} else 	if (crs.toWKT().contains("TM 27 3")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2319"));
									} else if (crs.toWKT().contains("TM 30 3")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2320"));
									} else if (crs.toWKT().contains("TM 33 3")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2321"));
									}  else if (crs.toWKT().contains("TM 36 3")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2322"));
									}  else if (crs.toWKT().contains("TM 39 3")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2323"));
									} else if (crs.toWKT().contains("TM 42 3")) {
										
										smpLayers.get(i).setCoordinateSystem(new SmpCoordinateSystem("EPSG:2324"));
									}
								}
							} else {
								//logger.info("User must set CRS");
								System.out.println("Layer: " + smpLayers.get(i).getName() + " (2) has not any CRS");
							}
							smpLayers.get(i).setGeometryColumnName(schema.getDefaultGeometry().getLocalName());
						} else {
							
							System.out.println("Layer: " + smpLayers.get(i).getName() + " has not any geometry");
						}
					} catch (Exception ex) {
						//if problem with geometry type do not need to catch
						System.out.println("ERROR on getting default geometry for layer " + smpLayers.get(i).getName());
					}
					//ColumnNamesForLayer columnNamesForLayer = new ColumnNamesForLayer(this.wfsUrl, typeLayers.get(i));
					
					//List<SmpLayerAttribute> smpLayerAttributes = columnNamesForLayer.getAttributesForLayer();
					List<SmpLayerAttribute> smpLayerAttributes = getLayerAttributes(schema);
					
					List<ILayerAttribute> layerAttributes = new ArrayList<ILayerAttribute>();
					
					for (int j = 0; j < smpLayerAttributes.size(); j++) {
						
						layerAttributes.add(smpLayerAttributes.get(j));
					}
					smpLayers.get(i).setSmpLayerAttributes(layerAttributes);			
					//Set dataStore to layer
				    smpLayers.get(i).setDataProvider(dataStore);
				}
				return (smpLayers);
			} catch (Exception ex) {
	
				System.out.println("Error on creating features layers ! (1) ERROR : " + ex);
				
				return (smpLayers);
			}
		} else {
			
			System.out.println("Layer names can't be null or empty !");
			
			return null;
		}
	}
	
	private List<SmpLayerAttribute> getLayerAttributes(FeatureType schema) {
		
		List<SmpLayerAttribute> smpLayerAttributes = new ArrayList<SmpLayerAttribute>();
		
		for (int i = 0; i < schema.getAttributeCount(); i++) {
			
			smpLayerAttributes.add(new SmpLayerAttribute());
			
			smpLayerAttributes.get(i).setAttributeId(i);
			
			smpLayerAttributes.get(i).setAttributeName(schema.getAttributeType(i).getLocalName());
			//TODO Metadata can make private names for every column
			smpLayerAttributes.get(i).setAttributeColumnName(schema.getAttributeType(i).getLocalName());
		}
		return (smpLayerAttributes);
	}
	
	@SuppressWarnings("unchecked")
	public List<IFeatureLayer> getLayers(IFeatureDataStore dataStore, ILayersNameAndPD_CRS layersNameAndPD_CRS) {
		//logger.setLevel(Level.WARNING);
		if (layersNameAndPD_CRS != null && layersNameAndPD_CRS.getLayersNameAndPD_CRS().size() != 0) {
		
			List<IFeatureLayer> smpLayers = new ArrayList<IFeatureLayer>();
	
			try {
				
				for (Iterator<?> itr = layersNameAndPD_CRS.getLayersNameAndPD_CRS().keySet().iterator(); itr.hasNext();) {
					
					String keyName = itr.next().toString();
					
					if (keyName != null){
						
						IFeatureLayer smpLayer = new SmpFeatureLayer();
					
						smpLayer.setName(keyName);
						
						smpLayers.add(smpLayer);
						
						FeatureSource featureSource = dataStore.getFeatureSource(smpLayer.getName());			
						
						FeatureType schema = featureSource.getSchema();
						
						if (schema.getDefaultGeometry() != null) {
							
							CoordinateReferenceSystem crs = schema.getDefaultGeometry().getCoordinateSystem();
							//if crs is null that means this layer has a custom CRS need to set manually
							if (crs == null) {
								
								if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName) != null) {
								
									if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName).equals("EPSG:2000273")) {
										
										crs = CRS.parseWKT(Wkt2000273.wkt);
									} else if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName).equals("EPSG:2000303")) {
										
										crs = CRS.parseWKT(Wkt2000303.wkt);
									} else if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName).equals("EPSG:2000333")) {
										
										crs = CRS.parseWKT(Wkt2000333.wkt);
									}  else if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName).equals("EPSG:2000363")) {
										
										crs = CRS.parseWKT(Wkt2000363.wkt);
									}  else if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName).equals("EPSG:2000393")) {
										
										crs = CRS.parseWKT(Wkt2000393.wkt);
									} else if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName).equals("EPSG:2000423")) {
										
										crs = CRS.parseWKT(Wkt2000423.wkt);
									} else if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName).equals("EPSG:2000453")) {
										
										crs = CRS.parseWKT(Wkt2000453.wkt);
									} else if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName).equals("EPSG:2001273")) {
										
										crs = CRS.parseWKT(Wkt2001273.wkt);
									} else if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName).equals("EPSG:2001303")) {
										
										crs = CRS.parseWKT(Wkt2001303.wkt);
									} else if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName).equals("EPSG:2001333")) {
										
										crs = CRS.parseWKT(Wkt2001333.wkt);
									} else if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName).equals("EPSG:2001363")) {
										
										crs = CRS.parseWKT(Wkt2001363.wkt);
									} else if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName).equals("EPSG:2001393")) {
										
										crs = CRS.parseWKT(Wkt2001393.wkt);
									} else if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName).equals("EPSG:2001423")) {
										
										crs = CRS.parseWKT(Wkt2001423.wkt);
									} else if (layersNameAndPD_CRS.getLayersNameAndPD_CRS().get(keyName).equals("EPSG:2001453")) {
										
										crs = CRS.parseWKT(Wkt2001453.wkt);
									}
								}
							}
							if (featureSource.getBounds() != null) {
							
								SmpEnvelope smpEnvelope = new SmpEnvelope(featureSource.getBounds().getMinY(), featureSource.getBounds().getMinX(), featureSource.getBounds().getMaxY(), featureSource.getBounds().getMaxX());
								
								smpLayer.setExtent(smpEnvelope);
							}
							if (crs != null) {
								
								if (crs.getIdentifiers().iterator().hasNext()) {
							
									Iterator<NamedIdentifier> crsIterator = crs.getIdentifiers().iterator();					
									
									NamedIdentifier namedIdentifier = crsIterator.next();					
									
									SmpCoordinateSystem smpCoordinateSystem = new SmpCoordinateSystem(namedIdentifier.getCodeSpace() + ":" + namedIdentifier.getCode());
									
									smpLayer.setCoordinateSystem(smpCoordinateSystem);
								} else {
									//TODO there is some of layers couldn't find EPSG Code so this part must fix when a full EPSG table find or updated.
									//For custom defined coordinate systems
									if (crs.toWKT().contains("TM 27 3 DERECE ITRF96")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2000273"));
									} else if (crs.toWKT().contains("TM 30 3 DERECE ITRF96")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2000303"));
									} else if (crs.toWKT().contains("TM 33 3 DERECE ITRF96")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2000333"));
									}  else if (crs.toWKT().contains("TM 36 3 DERECE ITRF96")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2000363"));
									}  else if (crs.toWKT().contains("TM 39 3 DERECE ITRF96")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2000393"));
									} else if (crs.toWKT().contains("TM 42 3 DERECE ITRF96")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2000423"));
									} else if (crs.toWKT().contains("TM 45 3 DERECE ITRF96")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2000453"));
									} else if (crs.toWKT().contains("TM 27 3 DERECE 2001 ITRF96")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2001273"));
									} else if (crs.toWKT().contains("TM 30 3 DERECE 2001 ITRF96")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2001303"));
									} else if (crs.toWKT().contains("TM 33 3 DERECE 2001 ITRF96")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2001333"));
									}  else if (crs.toWKT().contains("TM 36 3 DERECE 2001 ITRF96")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2001363"));
									}  else if (crs.toWKT().contains("TM 39 3 DERECE 2001 ITRF96")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2001393"));
									} else if (crs.toWKT().contains("TM 42 3 DERECE 2001 ITRF96")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2001423"));
									} else if (crs.toWKT().contains("TM 45 3 DERECE 2001 ITRF96")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2001453"));
									} else if (crs.toWKT().contains("TM 27 3")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2319"));
									} else if (crs.toWKT().contains("TM 30 3")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2320"));
									} else if (crs.toWKT().contains("TM 33 3")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2321"));
									}  else if (crs.toWKT().contains("TM 36 3")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2322"));
									}  else if (crs.toWKT().contains("TM 39 3")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2323"));
									} else if (crs.toWKT().contains("TM 42 3")) {
										
										smpLayer.setCoordinateSystem(new SmpCoordinateSystem("EPSG:2324"));
									}
								}
							} else {
								//logger.info("User must set CRS");
								System.out.println("Layer: " + smpLayer.getName() + " (3) has not any CRS");
							}
							smpLayer.setGeometryColumnName(schema.getDefaultGeometry().getLocalName());
							
						} else {
							
							System.out.println("Layer: " + smpLayer.getName() + " has not any geometry");
						}
						//ColumnNamesForLayer columnNamesForLayer = new ColumnNamesForLayer(this.wfsUrl, typeLayers.get(i));
						
						//List<SmpLayerAttribute> smpLayerAttributes = columnNamesForLayer.getAttributesForLayer();
						List<SmpLayerAttribute> smpLayerAttributes = getLayerAttributes(schema);
						
						List<ILayerAttribute> layerAttributes = new ArrayList<ILayerAttribute>();
						
						for (int j = 0; j < smpLayerAttributes.size(); j++) {
							
							layerAttributes.add(smpLayerAttributes.get(j));
						}
						smpLayer.setSmpLayerAttributes(layerAttributes);			
						//Set dataStore to layer
					    smpLayer.setDataProvider(dataStore);
					}
				}
				return (smpLayers);
	
			} catch (Exception ex) {
	
				System.out.println("Error on creating features layers ! (2) ERROR : " + ex);
				
				return (smpLayers);
			}
		} else {
			
			System.out.println("Layer names can't be null or empty !");
			
			return null;
		}
	}

}
