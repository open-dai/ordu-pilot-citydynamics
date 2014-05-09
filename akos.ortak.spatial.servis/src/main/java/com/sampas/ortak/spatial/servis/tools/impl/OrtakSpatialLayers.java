package com.sampas.ortak.spatial.servis.tools.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import com.sampas.akos.common.exception.AkosException;
import com.sampas.ortak.spatial.servis.tools.IAppFeatureLayer;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.provider.services.IOracleDataStoreSrv;
import com.sampas.socbs.core.data.provider.services.IPostGisDataStoreSrv;
import com.sampas.socbs.core.data.provider.services.impl.OracleDataStoreSrv;
import com.sampas.socbs.core.data.provider.services.impl.PostGisDataStoreSrv;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayer;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.impl.SmpFeatureLayer;
import com.sampas.socbs.core.maplayer.impl.SmpLayerNames;
import com.sampas.socbs.geolsa.model.MdAppLayer;
import com.sampas.socbs.geolsa.model.MdDatabaseFeatureLayer;
import com.sampas.socbs.geolsa.model.MdFeatureLayer;
import com.sampas.socbs.geolsa.model.MdLayer;
import com.sampas.socbs.geolsa.model.MdSmpGISApp;
import com.sampas.socbs.geolsa.model.MetadataAppStaticVars;


@SuppressWarnings("unchecked")
public class OrtakSpatialLayers {

	public static String ADDRESS_LAYER_PROP_NAME = "ADDRESSLAYER";
	
	public static String BUILDAREA_LAYER_PROP_NAME = "BUILDAREALAYER";
	
	public static String BUILDING_LAYER_PROP_NAME = "BUILDINGLAYER";
	
	public static String DISTRICT_LAYER_PROP_NAME = "DISTRICTLAYER";
	
	public static String STREET_LAYER_PROP_NAME = "STREETLAYER";
	
	public static String PARCEL_LAYER_PROP_NAME = "PARCELLAYER";
	
	public static String IRRIGATION_CANAL_LAYER_PROP_NAME = "IRRIGATIONCANALLAYER";
	
	public static String IRRIGATION_CANAL_COVER_LAYER_PROP_NAME = "IRRIGATIONCANALCOVERLAYER";
	
	public static String IRRIGATION_WELL_LAYER_PROP_NAME = "IRRIGATIONWELLLAYER";
	
	public static String IRRIGATION_ROAD_LAYER_PROP_NAME = "IRRIGATIONROADLAYER";
	
	public static String IRRIGATION_PARCEL_LAYER_PROP_NAME = "IRRIGATIONPARCELLAYER";
	
	public static String IRRIGATION_RESPONSIBILITY_AREA_LAYER_PROP_NAME = "IRRIGATIONRESPONSIBILITYAREALAYER";
	
	public static String IRRIGATION_SAFE_GUARD_BORDER_LAYER_PROP_NAME = "IRRIGATIONRESPONSIBILITYAREALAYER";
	
	private List<IAppFeatureLayer> appLayers = new ArrayList<IAppFeatureLayer>();
	
	private MdSmpGISApp applicationMeta = null;

	private static final String ORACLE_DB_IDENTIFIER_KEYWORD = "oracle";
	
	private static final int ORACLE_DB_MIN_CONNECTION = 1;
	
	private static final int ORACLE_DB_MAX_CONNECTION = 20;
	
	private static final boolean ORACLE_DB_VALIDATE_CONNECTION = false;
	
	private static final String POSTGIS_DB_IDENTIFIER_KEYWORD = "postgis";
	
	private static final boolean POSTGIS_DB_WKTENABLED = true;
	
	private static final boolean POSTGIS_DB_LOOSEBOX = true;
	
	private Map<String, String> layerBaseObjectRelatedName;
	
	private Map<MdLayer, IFeatureDataStore> metaLayerToLayerDataStoreMap;
	
	
	public OrtakSpatialLayers(MdSmpGISApp applicationMeta) {
		
		this.applicationMeta = applicationMeta;
		
		if (this.applicationMeta != null) {
			
			InputStream layerRelatedObject = this.getClass().getResourceAsStream("baseobjectmap.properties");
			
			if (layerRelatedObject != null) {
				
				try {
					
					Properties layerRelatedObjectProperties = new Properties();
					
					layerRelatedObjectProperties.load(layerRelatedObject);
					
					Set<Entry<Object, Object>> layerObjectNamesSet = layerRelatedObjectProperties.entrySet();
					
					Iterator<?> layerRelatedObjectIterator = layerObjectNamesSet.iterator();
					
					layerBaseObjectRelatedName = new HashMap<String, String>();
					
					while (layerRelatedObjectIterator.hasNext()) {
						
						Entry layerObjectEntry = (Entry) layerRelatedObjectIterator.next();
	
						layerBaseObjectRelatedName.put((String)layerObjectEntry.getKey(), (String)layerObjectEntry.getValue());
					}
				} catch (Exception ex) {
					
					System.out.println("Error on reading layer names ! ERROR : " + ex);
				}
			} else {
				
				System.out.println("Error on reading layer related object names !");
			}
			InputStream layerNames = this.getClass().getResourceAsStream("layermap.properties");
			
			if (layerNames != null) {
				
				try {
					
					Properties layerProperties = new Properties();
					
					layerProperties.load(layerNames);
					
					Set<Entry<Object, Object>> layersSet = layerProperties.entrySet();
					
					Iterator<?> layerIterator = layersSet.iterator();

					Map<Long, String> layerNumberNameMatch = new HashMap<Long, String>();
					
					while (layerIterator.hasNext()) {
						
						Entry layerEntry = (Entry) layerIterator.next();
						
						layerNumberNameMatch.put(Long.parseLong((String)layerEntry.getValue()), (String)layerEntry.getKey());
					}
					this.appLayers = getAppLayersFromMetaNoList(layerNumberNameMatch, layerBaseObjectRelatedName);
				} catch (Exception ex) {
					
					System.out.println("Error on reading layer names ! ERROR : " + ex);
				}
			} else {
		
				System.out.println("Properties file can't be empty !");
			}		
		} else {
			
			System.out.println("Application Metadata can't be empty !");
		}
	}
	
	public ILayer getLayerFromMetaNo(long layerMetaNo) {
		
		List<MdAppLayer> appLayers = applicationMeta.getMdAppLayers();
		
		for (int layerCntr = 0; layerCntr < appLayers.size(); layerCntr++) {
			
			if (appLayers.get(layerCntr).getOid() == layerMetaNo) {

				if (appLayers.get(layerCntr).getMdLayer().getLayerType() == MetadataAppStaticVars.FEATURE_LAYER) {
					
					IFeatureLayer resultLayer = null;
					
					IFeatureDataStore featureDataStore = null;
					
					IFeatureLayerProvider layerProvider = null;
					
					MdFeatureLayer mdFeatureLayer = appLayers.get(layerCntr).getMdLayer().getMdFeatureLayer();
					
					String nameSpace = "";
					
					if (appLayers.get(layerCntr).getMdLayer().getLayerName().contains(":")) {
						
						nameSpace = appLayers.get(layerCntr).getMdLayer().getLayerName().split(":")[0];
					} else {
						
						nameSpace = "unknown";
					}
					if (mdFeatureLayer.getLayerSubType() == MetadataAppStaticVars.DATABASE_LAYER) {
						
						MdDatabaseFeatureLayer dbFeatureLayer = mdFeatureLayer.getMdDatabaseFeatureLayer();
						
						if (dbFeatureLayer.getDbType().equals(ORACLE_DB_IDENTIFIER_KEYWORD)) {

							try {
								
								IOracleDataStoreSrv oracleDataStoreSrv = new OracleDataStoreSrv(nameSpace, dbFeatureLayer.getDbURL(), dbFeatureLayer.getDbPort(), dbFeatureLayer.getDbName(), dbFeatureLayer.getDbUser(), dbFeatureLayer.getDbPass(), dbFeatureLayer.getDbSchema(), ORACLE_DB_MAX_CONNECTION, ORACLE_DB_MIN_CONNECTION, ORACLE_DB_VALIDATE_CONNECTION);
								
								if (oracleDataStoreSrv.getOracleDataStore() == null) {
								
									System.out.println("Error on creating Oracle Data Store Check Metadata Properties for : \n" +
											"Name Space : " + nameSpace + "\n" +  
											"Host URL : " + dbFeatureLayer.getDbURL() + "\n" + 
											"Port Number : " + dbFeatureLayer.getDbPort() + "\n" + 
											"DataBase Instance Name : " + dbFeatureLayer.getDbName() + "\n" + 
											"Schema : " + dbFeatureLayer.getDbSchema() + "\n" + 
											"User : " + dbFeatureLayer.getDbUser() + "\n" + 
											"Password : " + dbFeatureLayer.getDbPass() + "\n" 										
										);
									 
									return null;
								}
								
								featureDataStore = oracleDataStoreSrv.getOracleDataStore();
								
								layerProvider = new SmpFeatureLayerProviderSrv();
								
								ILayerNames layerNames = new SmpLayerNames();
								
								layerNames.addLayerName(mdFeatureLayer.getMdDatabaseFeatureLayer().getLayerTbName());
								
								resultLayer = layerProvider.getLayers(featureDataStore, layerNames).get(0);
								//If CRS couldn't read from schema
								try {
									
									if (resultLayer.getCoordinateSystem() == null) {
										
										ICoordinateSystem metadataCRS = new SmpCoordinateSystem(appLayers.get(layerCntr).getMdLayer().getDefaultCrs());
										
										resultLayer.setCoordinateSystem(metadataCRS);
										
										System.out.println("CRS " + metadataCRS.getEPSGCode() + " setted for layer : " + appLayers.get(layerCntr).getMdLayer().getLayerName());
									}	
								} catch (Exception ex) {
									
									System.out.println("Error on setting CRS to feature layer ! ERROR : " + ex);
								}
								return resultLayer;
								
							} catch (Exception ex) {
								
								System.out.println("Error on reading feature layer ! ERROR : " + ex);
							}
						}
						if (dbFeatureLayer.getDbType().equals(POSTGIS_DB_IDENTIFIER_KEYWORD)) {
						
							try {
								
								IPostGisDataStoreSrv postGisDataStoreSrv = new PostGisDataStoreSrv(dbFeatureLayer.getDbURL(),dbFeatureLayer.getDbPort(),dbFeatureLayer.getDbName() ,dbFeatureLayer.getDbSchema(), dbFeatureLayer.getDbUser(), dbFeatureLayer.getDbPass(), POSTGIS_DB_WKTENABLED, POSTGIS_DB_LOOSEBOX);
								
								featureDataStore = postGisDataStoreSrv.getPostGisDataStore();
								
								layerProvider = new SmpFeatureLayerProviderSrv();
								
								ILayerNames layerNames = new SmpLayerNames();
								
								layerNames.addLayerName(mdFeatureLayer.getMdDatabaseFeatureLayer().getLayerTbName());
								
								resultLayer = layerProvider.getLayers(featureDataStore, layerNames).get(0);
								
								return resultLayer;
								
							} catch (Exception ex) {
								
								System.out.println("Error on reading feature layer ! ERROR : " + ex);
							}
						}
					}
				}
			}
		}
		return (new SmpFeatureLayer());
	}

	public List<IAppFeatureLayer> getAppLayersFromMetaNoList(Map<Long, String> layerNumberNameMatch, Map<String, String> layerClassRelationMap) {
		
		List<IAppFeatureLayer> resultAppLayerList = new ArrayList<IAppFeatureLayer>();

		Map<String, MdLayer> relatedLayerNameMetaLayerMap = new HashMap<String, MdLayer>();
		
		for (MdAppLayer tempAppLayer : this.applicationMeta.getMdAppLayers()) {
			
			for (Long tempLayerMetaNo : layerNumberNameMatch.keySet()) {
				
				if (tempAppLayer.getOid() == tempLayerMetaNo) {

					if (tempAppLayer.getMdLayer().getLayerType() == MetadataAppStaticVars.FEATURE_LAYER) {
						
						relatedLayerNameMetaLayerMap.put(layerNumberNameMatch.get(tempLayerMetaNo), tempAppLayer.getMdLayer());
					} else {
						//TODO:
					}
				} else {
					//TODO:
				}
			}
		}
		if (relatedLayerNameMetaLayerMap.size() > 0) {
			
			List<MdLayer> metaLayerList = new ArrayList<MdLayer>();
			
			for (String tempAppLayerName : relatedLayerNameMetaLayerMap.keySet()) {
				
				metaLayerList.add(relatedLayerNameMetaLayerMap.get(tempAppLayerName));
			}
			IFeatureLayerProvider featureLayerProvider = new SmpFeatureLayerProviderSrv();
			
			List<IFeatureLayer> resultLayerList = new ArrayList<IFeatureLayer>();
			
			List<Map<IFeatureDataStore, ILayerNames>> unifiedFeatureDataStoreAndRelatedLayersList = getUnifiedFeatureStoresWithRelatedLayers(metaLayerList);
			
			if (unifiedFeatureDataStoreAndRelatedLayersList != null && unifiedFeatureDataStoreAndRelatedLayersList.size() > 0) {
			
				for (Map<IFeatureDataStore, ILayerNames> tempFeatureDataStoreAndLayerNames : unifiedFeatureDataStoreAndRelatedLayersList) {
					
					for (IFeatureDataStore tempFeatureStore : tempFeatureDataStoreAndLayerNames.keySet()) {
						
						List<IFeatureLayer> mappedFeatureLayers = featureLayerProvider.getLayers(tempFeatureStore, tempFeatureDataStoreAndLayerNames.get(tempFeatureStore));
						
						if (mappedFeatureLayers != null && mappedFeatureLayers.size() > 0) {
							
							for (IFeatureLayer tempFeatureLayer : mappedFeatureLayers) {
								
								if (tempFeatureLayer.getCoordinateSystem() == null) {
									
									MdLayer relatedMetaLayer = null;
									
									for (Long tempMetaLayerID : layerNumberNameMatch.keySet()) {
										
										relatedMetaLayer = relatedLayerNameMetaLayerMap.get(layerNumberNameMatch.get(tempMetaLayerID));
										
										String searchLayerName = "";
										
										if (relatedMetaLayer.getLayerName().contains(":")) {
											
											searchLayerName = relatedMetaLayer.getLayerName().split(":")[1];
										} else {
											
											searchLayerName = relatedMetaLayer.getLayerName();
										}
										if (searchLayerName.equals(tempFeatureLayer.getName())) {
											
											break;
										} else {
											//No need to do anything
										}
									}
									//Related Meta Layer setted before
									if (relatedMetaLayer != null && relatedMetaLayer.getDefaultCrs() != null && !relatedMetaLayer.getDefaultCrs().equals("")) {
										
										ICoordinateSystem coordinateSystem = new SmpCoordinateSystem(relatedMetaLayer.getDefaultCrs());
										
										tempFeatureLayer.setCoordinateSystem(coordinateSystem);
										
										System.out.println("Coordinate System : " + coordinateSystem.getEPSGCode() + " is setted to Layer : " + tempFeatureLayer.getName());
									} else {
										//TODO:
									}
								} else {
									//No need to do anything
								}
							}
							resultLayerList.addAll(mappedFeatureLayers);
						} else {
							//No need to do anything
						}
					}
				}
				if (resultLayerList.size() > 0) {
					
					for (IFeatureLayer tempFeatureLayer : resultLayerList) {
						
						for (String tempLayerName : relatedLayerNameMetaLayerMap.keySet()) {
							
							String searchLayerName = "";
							
							if (relatedLayerNameMetaLayerMap.get(tempLayerName).getLayerName().contains(":")) {
								
								searchLayerName = relatedLayerNameMetaLayerMap.get(tempLayerName).getLayerName().split(":")[1];
							} else {
								searchLayerName = relatedLayerNameMetaLayerMap.get(tempLayerName).getLayerName();
							}
							if (searchLayerName.equals(tempFeatureLayer.getName())) {

								for (String tempLayerSysName: layerClassRelationMap.keySet()) {
									
									if (tempLayerSysName.equals(tempLayerName)) {
										
										resultAppLayerList.add(new AppFeatureLayer(tempLayerName, layerClassRelationMap.get(tempLayerSysName), tempFeatureLayer));
										
										break;
									}
								}
							} else {
								//TODO:
							}
						}
					}
				} else {
					//TODO:
				}
			} else {
				//TODO:
			}
		} else {
			//TODO:
		}
		return resultAppLayerList;
	}
	
	public List<Map<IFeatureDataStore, ILayerNames>> getUnifiedFeatureStoresWithRelatedLayers(List<MdLayer> metaLayerList) {
		
		try {
			
			List<Map<IFeatureDataStore, ILayerNames>> resultFeatureStoreLayerNamesList = new ArrayList<Map<IFeatureDataStore,ILayerNames>>();
			
			if (metaLayerList != null && metaLayerList.size() > 0) {
				
				Map<MdLayer, MdLayer> metaLayerMatchMap = new HashMap<MdLayer, MdLayer>();
				
				for (MdLayer tempMetaLayer : metaLayerList) {
					
					for (MdLayer checkMetaLayer : metaLayerList) {
						
						if (tempMetaLayer.getMdFeatureLayer().getLayerSubType() == MetadataAppStaticVars.DATABASE_LAYER) {
							
							if (isFeatureDataBaseMetaLayersAreSame(tempMetaLayer, checkMetaLayer)) {
								
								metaLayerMatchMap.put(tempMetaLayer, checkMetaLayer);
							} else {
								//TODO:
							}
						} else if (tempMetaLayer.getMdFeatureLayer().getLayerSubType() == MetadataAppStaticVars.FILEBASED_LAYER) {
							//TODO: For other type of vector source
						} else if (tempMetaLayer.getMdFeatureLayer().getLayerSubType() == MetadataAppStaticVars.REMOTE_LAYER) {
							//TODO: For other type of vector source
						}
					}
				}
				List<MdLayer> choosedMetaLayerList = new ArrayList<MdLayer>();
				
				Map<MdLayer, List<MdLayer>> relatedMetaLayerList = new HashMap<MdLayer, List<MdLayer>>();
				//Find singleton metaLayers for feature data source
				for (MdLayer tempMetaLayer : metaLayerMatchMap.keySet()) {
	
					if (!isChosedBefore(choosedMetaLayerList, tempMetaLayer)) {
						
						choosedMetaLayerList.add(tempMetaLayer);
						
						List<MdLayer> choosedLayerRelatedLayers = new ArrayList<MdLayer>();
						
						 for (MdLayer tempMetaLayer_2 : metaLayerMatchMap.keySet()) {
							
							if (metaLayerMatchMap.get(tempMetaLayer) == metaLayerMatchMap.get(tempMetaLayer_2)) {
								
								choosedMetaLayerList.add(tempMetaLayer_2);
								
								choosedLayerRelatedLayers.add(tempMetaLayer_2);
							} else {
								//TODO:
							}
						}
						relatedMetaLayerList.put(tempMetaLayer, choosedLayerRelatedLayers);
					} else {
						//TODO:
					}
				}
				if (relatedMetaLayerList.size() > 0) {
					
					for (MdLayer tempMetaLayer : relatedMetaLayerList.keySet()) {
						
						if (tempMetaLayer.getMdFeatureLayer().getLayerSubType() == MetadataAppStaticVars.DATABASE_LAYER) {
							
							IFeatureDataStore newDataStore = getFeatureDataStoreForRelatedMetaLayer(tempMetaLayer);
							
							if (newDataStore != null) {
								
								Map<IFeatureDataStore, ILayerNames> newFeatureLayerInteraction = new HashMap<IFeatureDataStore, ILayerNames>();
								
								newFeatureLayerInteraction.put(newDataStore, getLayerNamesFromMetaLayerList(relatedMetaLayerList.get(tempMetaLayer)));
								
								resultFeatureStoreLayerNamesList.add(newFeatureLayerInteraction);
							} else {
								//TODO:
							}
						} else if (tempMetaLayer.getMdFeatureLayer().getLayerSubType() == MetadataAppStaticVars.FILEBASED_LAYER) {
							//TODO: For other type of vector source
						} else if (tempMetaLayer.getMdFeatureLayer().getLayerSubType() == MetadataAppStaticVars.REMOTE_LAYER) {
							//TODO: For other type of vector source
						}
					}
				} else {
					//TODO:
				}
			} else {
				//TODO:
			}
			return resultFeatureStoreLayerNamesList;
		} catch (Exception ex) {
			//TODO:
			return null;
		}
	}
	
	private ILayerNames getLayerNamesFromMetaLayerList(List<MdLayer> metaLayerList) {
		
		ILayerNames resultLayerNames = new SmpLayerNames();
		
		if (metaLayerList != null && metaLayerList.size() > 0) {
			
			for (MdLayer tempMetaLayer : metaLayerList) {
				
				String pureLayerName = "";
				
				if (tempMetaLayer.getLayerName().contains(":")) {
					
					pureLayerName = tempMetaLayer.getLayerName().split(":")[1];
				} else {
					pureLayerName = tempMetaLayer.getLayerName();
				}
				resultLayerNames.addLayerName(pureLayerName);
			}
			return resultLayerNames;
		} else {
			//TODO:
		}
		return null;
	}
	
	private IFeatureDataStore getFeatureDataStoreForRelatedMetaLayer(MdLayer metaLayer) throws AkosException {
		
		String nameSpace = "";
		
		if (metaLayer.getLayerName().contains(":")) {
			
			nameSpace = metaLayer.getLayerName().split(":")[0];
		} else {
			
			nameSpace = "unkn";
		}
		IFeatureDataStore newFeatureDataStore = null;
		
		MdDatabaseFeatureLayer dbFeatureLayer = metaLayer.getMdFeatureLayer().getMdDatabaseFeatureLayer();
		
		if (dbFeatureLayer.getDbType().equals(ORACLE_DB_IDENTIFIER_KEYWORD)) {
			
			try {
				
				IOracleDataStoreSrv oracleDataStoreSrv = new OracleDataStoreSrv(nameSpace, dbFeatureLayer.getDbURL(), dbFeatureLayer.getDbPort(), dbFeatureLayer.getDbName(), dbFeatureLayer.getDbUser(), dbFeatureLayer.getDbPass(), dbFeatureLayer.getDbSchema(), ORACLE_DB_MAX_CONNECTION, ORACLE_DB_MIN_CONNECTION, ORACLE_DB_VALIDATE_CONNECTION);
				
				if (oracleDataStoreSrv.getOracleDataStore() == null) {
				
					System.out.println("Error on creating Layer ID : " + dbFeatureLayer.getOid() + ", Layer Name : " + dbFeatureLayer.getLayerTbName());
					
					System.out.println("Error on creating Oracle Data Store Check Metadata Properties for : \n" +
							"Name Space : " + nameSpace + "\n" +  
							"Host URL : " + dbFeatureLayer.getDbURL() + "\n" + 
							"Port Number : " + dbFeatureLayer.getDbPort() + "\n" + 
							"DataBase Instance Name : " + dbFeatureLayer.getDbName() + "\n" + 
							"Schema : " + dbFeatureLayer.getDbSchema() + "\n" + 
							"User : " + dbFeatureLayer.getDbUser() + "\n" + 
							"Password : " + dbFeatureLayer.getDbPass() + "\n"
							);
					
					throw new AkosException("com.sampas.socbs.ortakSpatial.featureDataStore.error.storeCreation.oracle", null);
				} else {
					
					newFeatureDataStore = oracleDataStoreSrv.getOracleDataStore();
					
					getMetaLayerToLayerDataStoreMap().put(metaLayer, newFeatureDataStore);
					
					return(oracleDataStoreSrv.getOracleDataStore());
				}
			} catch (Exception ex) {
				
				System.err.println("Error on creating Oracle Data Store ! ERROR : " + ex);
			}
		} else if (dbFeatureLayer.getDbType().equals(POSTGIS_DB_IDENTIFIER_KEYWORD)) {
			
			try {
				
				IPostGisDataStoreSrv postGisDataStoreSrv = new PostGisDataStoreSrv(dbFeatureLayer.getDbURL(),dbFeatureLayer.getDbPort(),dbFeatureLayer.getDbName() ,dbFeatureLayer.getDbSchema(), dbFeatureLayer.getDbUser(), dbFeatureLayer.getDbPass(), POSTGIS_DB_WKTENABLED, POSTGIS_DB_LOOSEBOX);
				
				if (postGisDataStoreSrv.getPostGisDataStore() == null) {
					
					System.out.println("Error on creating Layer ID : " + dbFeatureLayer.getOid() + ", Layer Name : " + dbFeatureLayer.getLayerTbName());
					
					System.out.println("Error on creating PostGIS Data Store Check Metadata Properties for : \n" +
							"Name Space : " + nameSpace + "\n" +  
							"Host URL : " + dbFeatureLayer.getDbURL() + "\n" + 
							"Port Number : " + dbFeatureLayer.getDbPort() + "\n" + 
							"DataBase Instance Name : " + dbFeatureLayer.getDbName() + "\n" + 
							"Schema : " + dbFeatureLayer.getDbSchema() + "\n" + 
							"User : " + dbFeatureLayer.getDbUser() + "\n" + 
							"Password : " + dbFeatureLayer.getDbPass() + "\n"
							);
					
					throw new AkosException("com.sampas.socbs.ortakSpatial.featureDataStore.error.storeCreation.postgis", null);
				} else {
					
					newFeatureDataStore = postGisDataStoreSrv.getPostGisDataStore();
					
					getMetaLayerToLayerDataStoreMap().put(metaLayer, newFeatureDataStore);
					
					return(postGisDataStoreSrv.getPostGisDataStore());
				}
			} catch (Exception ex) {
				
				System.err.println("Error on creating PostGIS Data Store ! ERROR : " + ex);
			}
		}
		return null;
	}
	
	private boolean isChosedBefore(List<MdLayer> choosedMetaLayerList, MdLayer metaLayer) {

		for (MdLayer tempMetaLayer : choosedMetaLayerList) {
			
			if (tempMetaLayer.getOid() == metaLayer.getOid()) {
				
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unused")
	private IFeatureDataStore getUniqueFeatureDataStore(MdLayer layerMeta) {
		
		try {
			
			if (layerMeta == null || layerMeta.getLayerType() != MetadataAppStaticVars.FEATURE_LAYER) {
				
				return null;
			} else {
				
				String nameSpace = "";
				
				if (layerMeta.getLayerName().contains(":")) {
					
					nameSpace = layerMeta.getLayerName().split(":")[0];
				} else {
					
					nameSpace = "unkn";
				}
				if (layerMeta.getMdFeatureLayer().getLayerSubType() == MetadataAppStaticVars.DATABASE_LAYER) {
					
					boolean isAnyLayerProviderFound = false;
					
					if (getMetaLayerToLayerDataStoreMap().size() > 0) {
						
						for (MdLayer tempMdLayer : getMetaLayerToLayerDataStoreMap().keySet()) {
							
							if (isFeatureDataBaseMetaLayersAreSame(tempMdLayer, layerMeta)) {
								
								isAnyLayerProviderFound = true;
								
								return getMetaLayerToLayerDataStoreMap().get(tempMdLayer);
							}
						}
					}
					if (!isAnyLayerProviderFound) {
						
						IFeatureDataStore newFeatureDataStore = null;
						
						MdDatabaseFeatureLayer dbFeatureLayer = layerMeta.getMdFeatureLayer().getMdDatabaseFeatureLayer();
						
						if (dbFeatureLayer.getDbType().equals(ORACLE_DB_IDENTIFIER_KEYWORD)) {
							
							try {
								
								IOracleDataStoreSrv oracleDataStoreSrv = new OracleDataStoreSrv(nameSpace, dbFeatureLayer.getDbURL(), dbFeatureLayer.getDbPort(), dbFeatureLayer.getDbName(), dbFeatureLayer.getDbUser(), dbFeatureLayer.getDbPass(), dbFeatureLayer.getDbSchema(), ORACLE_DB_MAX_CONNECTION, ORACLE_DB_MIN_CONNECTION, ORACLE_DB_VALIDATE_CONNECTION);
								
								if (oracleDataStoreSrv.getOracleDataStore() == null) {
									
									System.out.println("Error on creating Layer ID : " + dbFeatureLayer.getOid() + ", Layer Name : " + dbFeatureLayer.getLayerTbName());
									
									System.out.println("Error on creating Oracle Data Store Check Metadata Properties for : \n" +
											"Name Space : " + nameSpace + "\n" +  
											"Host URL : " + dbFeatureLayer.getDbURL() + "\n" + 
											"Port Number : " + dbFeatureLayer.getDbPort() + "\n" + 
											"DataBase Instance Name : " + dbFeatureLayer.getDbName() + "\n" + 
											"Schema : " + dbFeatureLayer.getDbSchema() + "\n" + 
											"User : " + dbFeatureLayer.getDbUser() + "\n" + 
											"Password : " + dbFeatureLayer.getDbPass() + "\n"
											);
									return null;
								} else {
									
									newFeatureDataStore = oracleDataStoreSrv.getOracleDataStore();
									
									getMetaLayerToLayerDataStoreMap().put(layerMeta, newFeatureDataStore);
									
									return(oracleDataStoreSrv.getOracleDataStore());
								}
							} catch (Exception ex) {
								
								System.err.println("Error on creating Oracle Data Store ! ERROR : " + ex);
							}
						} else if (dbFeatureLayer.getDbType().equals(POSTGIS_DB_IDENTIFIER_KEYWORD)) {
							
							try {
								
								IPostGisDataStoreSrv postGisDataStoreSrv = new PostGisDataStoreSrv(dbFeatureLayer.getDbURL(),dbFeatureLayer.getDbPort(),dbFeatureLayer.getDbName() ,dbFeatureLayer.getDbSchema(), dbFeatureLayer.getDbUser(), dbFeatureLayer.getDbPass(), POSTGIS_DB_WKTENABLED, POSTGIS_DB_LOOSEBOX);
								
								if (postGisDataStoreSrv.getPostGisDataStore() == null) {
									
									System.out.println("Error on creating Layer ID : " + dbFeatureLayer.getOid() + ", Layer Name : " + dbFeatureLayer.getLayerTbName());
									
									System.out.println("Error on creating PostGIS Data Store Check Metadata Properties for : \n" +
											"Name Space : " + nameSpace + "\n" +  
											"Host URL : " + dbFeatureLayer.getDbURL() + "\n" + 
											"Port Number : " + dbFeatureLayer.getDbPort() + "\n" + 
											"DataBase Instance Name : " + dbFeatureLayer.getDbName() + "\n" + 
											"Schema : " + dbFeatureLayer.getDbSchema() + "\n" + 
											"User : " + dbFeatureLayer.getDbUser() + "\n" + 
											"Password : " + dbFeatureLayer.getDbPass() + "\n"
											);
									return null;
								} else {
									
									newFeatureDataStore = postGisDataStoreSrv.getPostGisDataStore();
									
									getMetaLayerToLayerDataStoreMap().put(layerMeta, newFeatureDataStore);
									
									return(postGisDataStoreSrv.getPostGisDataStore());
								}
							} catch (Exception ex) {
								
								System.err.println("Error on creating PostGIS Data Store ! ERROR : " + ex);
							}
						}
					} else {
						//TODO:
					}
				} else if (layerMeta.getMdFeatureLayer().getLayerSubType() == MetadataAppStaticVars.FILEBASED_LAYER) {
					//TODO: need implementation
				} else if (layerMeta.getMdFeatureLayer().getLayerSubType() == MetadataAppStaticVars.REMOTE_LAYER) {
					//TODO: need implementation
				}
			}
		} catch (Exception ex) {
			
			System.err.println("ERROR on creating Feature Layer Data Store. ERROR : " + ex.getMessage());
		}
		return null;
	}
	
	private boolean isFeatureDataBaseMetaLayersAreSame(MdLayer baseMetaLayer, MdLayer searchMetaLayer) {
		
		try {
			
			if (baseMetaLayer.getMdFeatureLayer().getMdDatabaseFeatureLayer().getDbURL().equals(searchMetaLayer.getMdFeatureLayer().getMdDatabaseFeatureLayer().getDbURL()) &&
					baseMetaLayer.getMdFeatureLayer().getMdDatabaseFeatureLayer().getDbPort() == searchMetaLayer.getMdFeatureLayer().getMdDatabaseFeatureLayer().getDbPort() &&
					baseMetaLayer.getMdFeatureLayer().getMdDatabaseFeatureLayer().getDbName().equals(searchMetaLayer.getMdFeatureLayer().getMdDatabaseFeatureLayer().getDbName()) &&
					baseMetaLayer.getMdFeatureLayer().getMdDatabaseFeatureLayer().getDbSchema().equals(searchMetaLayer.getMdFeatureLayer().getMdDatabaseFeatureLayer().getDbSchema()) &&
					baseMetaLayer.getMdFeatureLayer().getMdDatabaseFeatureLayer().getDbUser().equals(searchMetaLayer.getMdFeatureLayer().getMdDatabaseFeatureLayer().getDbUser())) {
				
				return true;
			} else {
				
				return false;
			}
		} catch (Exception ex) {
			
			System.out.println("ERROR on getting database metadata properties for layer : " + searchMetaLayer.getLayerName());
			
			return false;
		}
	}
	
	public List<IAppFeatureLayer> getAppLayers() {
		
		return this.appLayers;
	}
	
	public IAppFeatureLayer getAppLayerByName(String appLayerName) {
		
		 for (IAppFeatureLayer applayer : this.appLayers) {
			 
			 if (applayer.getLayerSystemName().equals(appLayerName)) {
				 
				return applayer;
			}
		 }
		 return null;
	}
	
	public String getLayerRelatedObjectName(String searchKey) {
		
		for (String tempBaseObjectName : layerBaseObjectRelatedName.keySet()) {
			
			if (tempBaseObjectName.equals(searchKey)) {
				
				return layerBaseObjectRelatedName.get(tempBaseObjectName);
			}
		}
		return null;
	}
	
	public Map<MdLayer, IFeatureDataStore> getMetaLayerToLayerDataStoreMap() {
		
		if (this.metaLayerToLayerDataStoreMap == null) {
			
			setMetaLayerToLayerDataStoreMap(new HashMap<MdLayer, IFeatureDataStore>());
		}
		
		return metaLayerToLayerDataStoreMap;
	}

	public void setMetaLayerToLayerDataStoreMap(Map<MdLayer, IFeatureDataStore> metaLayerToLayerDataStoreMap) {
		this.metaLayerToLayerDataStoreMap = metaLayerToLayerDataStoreMap;
	}
	
}