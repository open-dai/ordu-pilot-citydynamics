package com.sampas.socbs.opendai.servis.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.geotools.geometry.jts.ReferencedEnvelope;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.CoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.IWMSDataStore;
import com.sampas.socbs.core.data.provider.services.impl.OracleDataStoreSrv;
import com.sampas.socbs.core.data.provider.services.impl.PostGisDataStoreSrv;
import com.sampas.socbs.core.data.provider.services.impl.WMSDataStoreSrv;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.IWMSLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.data.providers.impl.SmpWMSLayerProviderSrv;
import com.sampas.socbs.core.data.wms.impl.GetMapResult;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsGMLVersion;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsImageReturnType;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsVersion;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.geometry.impl.SmpDimension;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.IWMSLayer;
import com.sampas.socbs.core.maplayer.impl.SmpLayerNames;
import com.sampas.socbs.core.style.IWMSNamedStyle;
import com.sampas.socbs.core.tools.IImageProcesses;
import com.sampas.socbs.core.tools.impl.ImageProcesses;
import com.sampas.socbs.geolsa.model.MdAppLayer;
import com.sampas.socbs.geolsa.model.MdDatabaseFeatureLayer;
import com.sampas.socbs.geolsa.model.MdLayer;
import com.sampas.socbs.geolsa.model.MdRemoteRasterLayer;
import com.sampas.socbs.geolsa.model.MdSmpGISApp;
import com.sampas.socbs.geolsa.servis.IGeolsaServis;
import com.sampas.socbs.opendai.servis.IMapServis;
import com.sampas.socbs.opendai.servis.metadata.MapMetadata;
import com.sampas.socbs.opendai.servis.tools.ImageTypeConverter;


public class MapServisImpl implements IMapServis {
	
	private static final int FEATURE_LAYER_DEFAULT_NO = 1;
	
	private static final int RASTER_LAYER_DEFAULT_NO = 2;
	
	private static final String DEFAULT_NAMESPACE_SEPERATOR = ":";
	
	private IGeolsaServis geolsaServis;
	
	private MapMetadata mapMetadata = new MapMetadata();
	
	private IWMSLayer referanceLayer = null;
	
	private List<IWMSLayer> wmsLayers = null;
	
	private IEnvelope bbox = null;
	
	private List<IWMSNamedStyle> wmsNamedStyles = null;
	
	private ICoordinateSystem coordinateSystem = null;
	
	private static int mapImgMaxWidth = 2048;
	
	private static int mapImgMaxHeight = 1536;
	
	private int maxZoomLevel = 0;
	
	private IEnvelope transformedBbox = null;
	
	private boolean initializeSuccess = false;
	
	private boolean tryBefore = false;
	
	private IImageProcesses imageProcessor = new ImageProcesses();
	
	private MdSmpGISApp gisAppMetadata;
	
	private IFeatureLayer addressFeatureLayer;
	
	private IFeatureLayer importantPlaceFeatureLayer;
	
	private IFeatureLayer buildingFeatureLayer;
	
	private IFeatureLayer parcelFeatureLayer;	
	
	private MdSmpGISApp relatedApplication;
	
//	private ICoordinateSystemTransformers csTransformer = new CoordinateSystemTransformers();
	
	
	private boolean setGlobals() {
		
		try {
			setTryBefore(true);
	//		getMapMetadata().setAppNameForMetadata("kentrehberiv3.0");
			if (getWmsLayers() == null) {
				
				for (MdSmpGISApp tempApplication : getGeolsaServis().readAllCBSUygulamalariMetadata()) {
				//	System.out.println("setGlobals GELUUU 2 : "+tempApplication.getAppName() + " = "+tempApplication.getAppDescriptor() + " = "+tempApplication.getMdAppLayers().size() );
			//		System.out.println("setGlobals GELUUU 3 : "+getMapMetadata().getAppNameForMetadata() );
					if (tempApplication.getAppName().equals(getMapMetadata().getAppNameForMetadata())) {
						
						setRelatedApplication(tempApplication);
						
						setWmsLayers(getLayersFromMetadata(tempApplication, getMapMetadata().getMapLayerNoList()));
						
						setGisAppMetadata(tempApplication);
						
						break;
					}
				} 
			}
			if (getWmsLayers() != null && getWmsLayers().size() > 0) {
				// Means default styles for layers will set
				this.wmsNamedStyles = null;
				// Only Lat, Lon entries accepting
				//this.coordinateSystem = new SmpCoordinateSystem("EPSG:4326");
				
				
				if (referanceLayer == null) {
					
					referanceLayer = getWmsLayers().get(0);
				}
				//This is a palliative solution besause of data and cs mismatches 
				this.coordinateSystem = referanceLayer.getCoordinateSystem();
				
				if (referanceLayer != null) {
				
					if (referanceLayer.getCoordinateSystem() != this.coordinateSystem) {
						
						this.bbox = new SmpBoundingRectangle(referanceLayer.getExtent().getMinX(), referanceLayer.getExtent().getMinY(), referanceLayer.getExtent().getMaxX(), referanceLayer.getExtent().getMaxY());
						
						ICoordinateSystemTransformers transformers = new CoordinateSystemTransformers();
						
						this.transformedBbox = transformers.SmpBoundingRectangleCoordinateTransformer(this.bbox, referanceLayer.getCoordinateSystem(), this.coordinateSystem);
					} else {
						
						this.transformedBbox = new SmpBoundingRectangle(referanceLayer.getExtent().getMaxX(), referanceLayer.getExtent().getMinY(), referanceLayer.getExtent().getMaxX(), referanceLayer.getExtent().getMaxY());
					}
				}
				try {
					
					maxZoomLevel = getMapMetadata().getMaxZoomLevel();
				} catch (Exception ex) {
					
					System.out.println("Error on finding max zoom level from metadata ! ERROR : " + ex);
				}
				try {
					
					List<Integer> layerMetaNumbers = new ArrayList<Integer>();
					//Address layer
					layerMetaNumbers.add(getMapMetadata().getLayerAddressFeature());
					
					List<IFeatureLayer> findedFeatureLayers = getFeatureLayersFromMetadata(getGisAppMetadata(), layerMetaNumbers);
					
					if (findedFeatureLayers != null && findedFeatureLayers.size() == 1) {
						
						setAddressFeatureLayer(findedFeatureLayers.get(0));
					} else if (findedFeatureLayers != null && findedFeatureLayers.size() > 1) {
						
						System.out.println("Find more than one address feature layer !!! FirstOne selected as related layer !!!");
						
						setAddressFeatureLayer(findedFeatureLayers.get(0));
					} else if (findedFeatureLayers == null || findedFeatureLayers.size() == 0) {
						
						System.out.println("Address feature layer could not found !!! Check metadata number !!!");
						
						//return false;
					}
					//Important place layer
					layerMetaNumbers.clear();
					
					layerMetaNumbers.add(getMapMetadata().getLayerImportantPlaceFeature());
					
					findedFeatureLayers.clear();
					
					findedFeatureLayers = getFeatureLayersFromMetadata(getGisAppMetadata(), layerMetaNumbers);
					
					if (findedFeatureLayers != null && findedFeatureLayers.size() == 1) {
						
						setImportantPlaceFeatureLayer(findedFeatureLayers.get(0));
					} else if (findedFeatureLayers != null && findedFeatureLayers.size() > 1) {
						
						System.out.println("Find more than one important place feature layer !!! FirstOne selected as related layer !!!");
						
						setImportantPlaceFeatureLayer(findedFeatureLayers.get(0));
					} else if (findedFeatureLayers == null || findedFeatureLayers.size() == 0) {
						
						System.out.println("Important Place feature layer could not found !!! Check metadata number !!!");
						
						//return false;
					}
					//Building layer
					layerMetaNumbers.clear();
					
					layerMetaNumbers.add(getMapMetadata().getLayerBuildingFeature());
					
					findedFeatureLayers.clear();
					
					findedFeatureLayers = getFeatureLayersFromMetadata(getGisAppMetadata(), layerMetaNumbers);
					
					if (findedFeatureLayers != null && findedFeatureLayers.size() == 1) {
						
						setBuildingFeatureLayer(findedFeatureLayers.get(0));
					} else if (findedFeatureLayers != null && findedFeatureLayers.size() > 1) {
						
						System.out.println("Find more than one building feature layer !!! FirstOne selected as related layer !!!");
						
						setBuildingFeatureLayer(findedFeatureLayers.get(0));
					} else if (findedFeatureLayers == null || findedFeatureLayers.size() == 0) {
						
						System.out.println("Building feature layer could not found !!! Check metadata number !!!");
						
						//return false;
					}
					
					
					//Parcel layer
					layerMetaNumbers.clear();
					
					layerMetaNumbers.add(getMapMetadata().getLayerParcelFeature());
					
					findedFeatureLayers.clear();
					
					findedFeatureLayers = getFeatureLayersFromMetadata(getGisAppMetadata(), layerMetaNumbers);
					
					if (findedFeatureLayers != null && findedFeatureLayers.size() == 1) {
						
						setParcelFeatureLayer(findedFeatureLayers.get(0));
					} else if (findedFeatureLayers != null && findedFeatureLayers.size() > 1) {
						
						System.out.println("Find more than one building feature layer !!! FirstOne selected as related layer !!!");
						
						setParcelFeatureLayer(findedFeatureLayers.get(0));
					} else if (findedFeatureLayers == null || findedFeatureLayers.size() == 0) {
						
						System.out.println("Building feature layer could not found !!! Check metadata number !!!");
						
						//return false;
					}					
					
					
				} catch (Exception ex) {
					
					System.out.println("Error on creating feature layers from metadata ! ERROR : " + ex);
				}
				
				return true;
			} else {
				
				System.out.println("Error on finding related layers from ");
			}
		} catch (Exception ex) {
			
			System.out.println("Error on setting up globals variables ! ERROR : " + ex );
		}
		return false;
	}
	
	public List<IWMSLayer> getMapServiceWmsLayerList() {
		
		if (!isInitializeSuccess() && !isTryBefore()) {
			
			setInitializeSuccess(setGlobals());
		}
		if (isInitializeSuccess()) {
			
			return getWmsLayers();
		}
		return null;
	}
	
	private List<IWMSLayer> getLayersFromMetadata(MdSmpGISApp application, List<Integer> processNeedLayers) {
		
		try {
			
			List<IWMSLayer> layersFromMetadata = new ArrayList<IWMSLayer>();
			
			List<MdAppLayer> appLayers = application.getMdAppLayers();
			
			int appLayersSize = appLayers.size();
			
			int reqLayersSize = processNeedLayers.size();
			
			Integer[] reqLayers = new Integer[reqLayersSize];
			
			for (int reqLayerCntr = 0; reqLayerCntr < reqLayersSize; reqLayerCntr++) {
			
				reqLayers[reqLayerCntr] = processNeedLayers.get(reqLayerCntr);
			}
			for (int i = 0; i < appLayersSize ; i++) {
				
				for (int j = 0; j < reqLayersSize; j++) {
					
					if (reqLayers[j] == appLayers.get(i).getOid()) {
						
						if (appLayers.get(i).getMdLayer().getLayerType() == RASTER_LAYER_DEFAULT_NO) {
	
							String urlString = "";
	
							MdRemoteRasterLayer rasterLayer = appLayers.get(i).getMdLayer().getMdRasterLayer().getMdRemoteRasterLayer();
							
							urlString += "http://" + rasterLayer.getRemoteDSUrl(); urlString += ":" + rasterLayer.getPortNo(); urlString += "/" + rasterLayer.getQueryPage(); urlString += "/" + rasterLayer.getDirectory();
							
							URL wfsUrl = new URL(urlString);
							
							WMSDataStoreSrv wmsDataStoreCreatorSrv = new WMSDataStoreSrv(wfsUrl, WmsVersion.ver100, WmsGMLVersion.gml2);
							
							IWMSDataStore dataProvider = wmsDataStoreCreatorSrv.getWmsDataStore();
							
							IWMSLayerProvider layerProvider = new SmpWMSLayerProviderSrv();
							
							ILayerNames relatedLayers = new SmpLayerNames();
							
							relatedLayers.setLayerNames(new String[]{appLayers.get(i).getMdLayer().getLayerName()});
							
							List<IWMSLayer> wmsLayers = null;
							
							try {
								
								wmsLayers = layerProvider.getLayers(dataProvider, relatedLayers);

								if (wmsLayers != null && wmsLayers.size() > 0) {
									
									wmsLayers.get(0).setLayerPriority(appLayers.get(i).getLayerPriority());
									
									layersFromMetadata.add(wmsLayers.get(0));
									
								} else {
									
									System.out.println("Error on loading related layers !");
								}
							} catch (Exception ex) {
								
								System.out.println("Error on loading layer check metadata names for layer : " + relatedLayers.getLayerName(0) +" ERROR: " + ex);
							}
						} else {
							
							System.out.println("Layer : " + appLayers.get(i).getMdLayer().getLayerName() + " not a WMS Layer defination !");
						}
					}
				}
			}
			if (layersFromMetadata != null && layersFromMetadata.size() > 0) {

				List<IWMSLayer> resultLayerList = new ArrayList<IWMSLayer>();
				
				int times = layersFromMetadata.size();
				
				for (int layerCntr = 0; layerCntr < times; layerCntr++) {
					
					IWMSLayer tempLayer = getMinPriorityLayer(layersFromMetadata);
					
					resultLayerList.add(tempLayer);
					
					layersFromMetadata.remove(tempLayer);
				}
				layersFromMetadata = resultLayerList;
			}
			return layersFromMetadata;
		} catch (Exception ex) {
	
			System.out.println("Error on creating layers from related datastores ! ERROR: " + ex);
			
			return null;
		}
	}
	
	private IWMSLayer getMinPriorityLayer(List<IWMSLayer> layers) {

		int minLayerIndex = -1;
		
		int min = Integer.MAX_VALUE;
		
		for (int layerCntr = 0; layerCntr < layers.size(); layerCntr++) {
			
			if (layers.get(layerCntr).getLayerPriority() < min) {
				
				min = layers.get(layerCntr).getLayerPriority();
				
				minLayerIndex = layerCntr;
			}
		}
		return layers.get(minLayerIndex);
	}
	
	public BufferedImage getMapAsBfrImg(double lng, double lat, int imageWidth, int imageHeight, int zoomLevel) {
		
		if (!isInitializeSuccess() && !isTryBefore()) {
			
			setInitializeSuccess(setGlobals());
		}
		if (isInitializeSuccess()) {
			
			byte[] mapByteArray = getMapAsByteArr(lng, lat, imageWidth, imageHeight, zoomLevel);
			
			ImageTypeConverter imgConverter = new ImageTypeConverter();
			
			BufferedImage bufferedImage = imgConverter.ByteArrToBufferedImg(mapByteArray);

			return bufferedImage;
		}
		return null;
	}

	public byte[] getMapAsByteArr(double lng, double lat, int imageWidth, int imageHeight, int zoomLevel) {
		
		if (!isInitializeSuccess() && !isTryBefore()) {
			
			setInitializeSuccess(setGlobals());
		}
		//if (isInitializeSuccess() && imageWidth < mapImgMaxWidth && imageHeight < mapImgMaxHeight && zoomLevel <= maxZoomLevel && imageWidth > 0 && imageHeight > 0 && zoomLevel > 0) {
		if (isInitializeSuccess() && imageWidth < mapImgMaxWidth && imageHeight < mapImgMaxHeight && imageWidth > 0 && imageHeight > 0 && zoomLevel > 0) {
		
			this.bbox = getEnvelope(lng, lat, zoomLevel);
			
			IDimension dimension = new SmpDimension(imageWidth, imageHeight);
			
			try {
				//////////////////////////////////////////////////////////////////////////////////////////////
				//TODO: there is an error on merge images function it runs in tilling toll but not run in here
				GetMapResult tempGetMapResult = getWmsLayers().get(0).getMap(getWmsLayers(), wmsNamedStyles, coordinateSystem, bbox, dimension, WmsImageReturnType.png);
				
				if (tempGetMapResult.getImage() != null && tempGetMapResult.getImage().length > 0) {
					
					return tempGetMapResult.getImage();
				} else {
					
					System.out.println("Error on rendering layers !!! ERROR: " + tempGetMapResult.getException());
				}
				return null;
			} catch (Exception ex) {
				
				System.out.println("Error on getMap request ! ERROR : " + ex);
			}
		} else {
			
			System.out.println("Input parameters are not suitible for Get Map Request !");
		}
		return null;
	}
	
	private IEnvelope getEnvelope(double lng, double lat, int zoomLevel) {	
		
		if (!isInitializeSuccess() && !isTryBefore()) {
			
			setInitializeSuccess(setGlobals());
		}
		if (isInitializeSuccess()) {
		
			try {
			
				double differenceX = transformedBbox.getMaxX() - transformedBbox.getMinX();
				
				double differenceY = transformedBbox.getMaxY() - transformedBbox.getMinY();
				
				double xOffset = 0;
				
				double yOffset = 0;
				
				if(zoomLevel > 0) {
					
					xOffset = differenceX / (Math.pow(2, zoomLevel));
					
					yOffset = differenceY / (Math.pow(2, zoomLevel));
				} else {
					
					System.out.println("Zoom level must set bigger than zero.");
					
					return null;
				}
				IEnvelope envelope = new SmpBoundingRectangle(lng - xOffset, lat - yOffset, lng + xOffset, lat + yOffset);
				
				return envelope;
			} catch (Exception ex) {
				
				System.out.println("Error on calculating bounding box ! ERROR : " + ex);
			}
		}
		return null;
	}

	public int getMaxZoomLevel() {
		
		if (!isInitializeSuccess() && !isTryBefore()) {
			
			setInitializeSuccess(setGlobals());
		}
		if (isInitializeSuccess()) {
		
			return maxZoomLevel;
		}
		return -1;
	}
	
	private List<IFeatureLayer> getFeatureLayersFromMetadata(MdSmpGISApp application,List<Integer> layerMetaNumbers) {
		
		List<IFeatureLayer> featureLayerList = new ArrayList<IFeatureLayer>();
		
		List<MdAppLayer> appLayers = application.getMdAppLayers();
		
		int appLayersSize = appLayers.size();
		
		int reqLayersSize = layerMetaNumbers.size();
		
		Integer[] reqLayers = new Integer[reqLayersSize];
		
		for (int reqLayerCntr = 0; reqLayerCntr < reqLayersSize; reqLayerCntr++) {
		
			reqLayers[reqLayerCntr] = layerMetaNumbers.get(reqLayerCntr);
		}
		for (int i = 0; i < appLayersSize ; i++) {
			
			for (int j = 0; j < reqLayersSize; j++) {
				
				if (reqLayers[j] == appLayers.get(i).getOid()) {
					
					if (appLayers.get(i).getMdLayer().getLayerType() == FEATURE_LAYER_DEFAULT_NO) {
						
						IFeatureDataStore featureDataStore = getFeatureDataStore(appLayers.get(i).getMdLayer()); 
						
						if (featureDataStore != null) {
							
							IFeatureLayerProvider featureLayerProvider = new SmpFeatureLayerProviderSrv();
							
							ILayerNames layerNames = new SmpLayerNames();
							
							layerNames.addLayerName(splitStringByParameter(appLayers.get(i).getMdLayer().getLayerName(), DEFAULT_NAMESPACE_SEPERATOR, 1));
							
							List<IFeatureLayer> featureLayers = featureLayerProvider.getLayers(featureDataStore, layerNames);
							
							if (featureLayers != null && featureLayers.size() == 1) {
							
								if (featureLayers.get(0).getCoordinateSystem() == null) {
									
									featureLayers.get(0).setCoordinateSystem(new SmpCoordinateSystem(appLayers.get(i).getMdLayer().getDefaultCrs()));
								}
								featureLayerList.add(featureLayers.get(0));
							}
						}
					}
				}
			}
		}
		return featureLayerList;
	}

	private IFeatureDataStore getFeatureDataStore(MdLayer featureLayer) {

		if (featureLayer != null) {
			
			if (featureLayer.getMdFeatureLayer() != null) {
				//TODO:Feature database data source implemented only
				if (featureLayer.getMdFeatureLayer().getMdDatabaseFeatureLayer() != null) {
					//TODO:Find database type not possible this must fix from metadata
					try {
						
						MdDatabaseFeatureLayer dataBaseFeatureLayer = featureLayer.getMdFeatureLayer().getMdDatabaseFeatureLayer();
						
						OracleDataStoreSrv oracleDataStoreCreatorSrv = new OracleDataStoreSrv(splitStringByParameter(featureLayer.getLayerName(), DEFAULT_NAMESPACE_SEPERATOR, 0), dataBaseFeatureLayer.getDbURL(), dataBaseFeatureLayer.getDbPort(), dataBaseFeatureLayer.getDbName(), dataBaseFeatureLayer.getDbUser(), dataBaseFeatureLayer.getDbPass(), dataBaseFeatureLayer.getDbUser(), 50, 1, true); 
						
						IFeatureDataStore featureDataStore = oracleDataStoreCreatorSrv.getOracleDataStore();
						
						return featureDataStore;

					} catch (Exception ex) {

						//System.out.println("Error on creating datastore for layer = " + featureLayer.getLayerName());
					}
					try {
						
						MdDatabaseFeatureLayer dataBaseFeatureLayer = featureLayer.getMdFeatureLayer().getMdDatabaseFeatureLayer();
						
						PostGisDataStoreSrv postGisDataStoreSrv = new PostGisDataStoreSrv(dataBaseFeatureLayer.getDbURL(), dataBaseFeatureLayer.getDbPort(), dataBaseFeatureLayer.getDbName(), dataBaseFeatureLayer.getDbUser(), dataBaseFeatureLayer.getDbUser(), dataBaseFeatureLayer.getDbPass(), true, true); 
						
						IFeatureDataStore featureDataStore = postGisDataStoreSrv.getPostGisDataStore();
						
						return featureDataStore;

					} catch (Exception ex) {

						//System.out.println("Error on creating datastore for layer = " + featureLayer.getLayerName());
					}
				}
			}
		}
		return null;
	}

	private String splitStringByParameter(String splitNeedString, String splitParameter, Integer splitedPartNo) {
		
		String resultString = splitNeedString;
		
		try {
			
			resultString = splitNeedString.split(splitParameter)[splitedPartNo];
		} catch (Exception ex) {
			
			System.out.println("Error on spliting string !!!");
		}
		return resultString;
	}
	
	public IFeatureLayer getAddressFeatureLayer() {
		
		if (!isInitializeSuccess() && !isTryBefore()) {
			System.out.println("getAddressFeatureLayer SUCCESS 1");
			setInitializeSuccess(setGlobals());
		} else
		{
			System.out.println("getAddressFeatureLayer NOT SUCCESS 1");
		}
		return addressFeatureLayer;
	}
	
	public IFeatureLayer getImportantPlaceFeatureLayer() {
		
		if (!isInitializeSuccess() && !isTryBefore()) {
			
			setInitializeSuccess(setGlobals());
		}
		return importantPlaceFeatureLayer;
	}
	
	
	//TODO: Getters and Setters Separator
	
	public IGeolsaServis getGeolsaServis() {
		return geolsaServis;
	}

	public void setGeolsaServis(IGeolsaServis geolsaServis) {
		this.geolsaServis = geolsaServis;
	}

	public MapMetadata getMapMetadata() {
		return mapMetadata;
	}
	
	public void setMapMetadata(MapMetadata mapMetadata) {
		this.mapMetadata = mapMetadata;
	}

	public boolean isInitializeSuccess() {
		return initializeSuccess;
	}

	public void setInitializeSuccess(boolean initializeSuccess) {
		this.initializeSuccess = initializeSuccess;
	}

	public boolean isTryBefore() {
		return tryBefore;
	}

	public void setTryBefore(boolean tryBefore) {
		this.tryBefore = tryBefore;
	}

	public List<IWMSLayer> getWmsLayers() {
		return wmsLayers;
	}

	public void setWmsLayers(List<IWMSLayer> wmsLayers) {
		this.wmsLayers = wmsLayers;
	}

	public IImageProcesses getImageProcessor() {
		return imageProcessor;
	}

	public void setImageProcessor(IImageProcesses imageProcessor) {
		this.imageProcessor = imageProcessor;
	}
	
	public MdSmpGISApp getGisAppMetadata() {
		return gisAppMetadata;
	}

	public void setGisAppMetadata(MdSmpGISApp gisAppMetadata) {
		this.gisAppMetadata = gisAppMetadata;
	}

	public void setAddressFeatureLayer(IFeatureLayer addressFeatureLayer) {
		this.addressFeatureLayer = addressFeatureLayer;
	}
	
	public void setImportantPlaceFeatureLayer(IFeatureLayer importantPlaceFeatureLayer) {
		this.importantPlaceFeatureLayer = importantPlaceFeatureLayer;
	}

	public IFeatureLayer getBuildingFeatureLayer() {
		
		if (!isInitializeSuccess() && !isTryBefore()) {
			
			setInitializeSuccess(setGlobals());
		}
		return buildingFeatureLayer;
	}

	public void setBuildingFeatureLayer(IFeatureLayer buildingFeatureLayer) {
		this.buildingFeatureLayer = buildingFeatureLayer;
	}

	public IFeatureLayer getParcelFeatureLayer() {
		if (!isInitializeSuccess() && !isTryBefore()) {
			
			setInitializeSuccess(setGlobals());
		}
		return parcelFeatureLayer;
	}

	public void setParcelFeatureLayer(IFeatureLayer parcelFeatureLayer) {
		this.parcelFeatureLayer = parcelFeatureLayer;
	}	
	
	
	public byte[] getMapAsByteArr(double lng, double lat, int imageWidth, int imageHeight, int zoomLevel, int[] layers) {
		
		if (!isInitializeSuccess() && !isTryBefore()) {
			
			setInitializeSuccess(setGlobals());
		}
		//if (isInitializeSuccess() && imageWidth < mapImgMaxWidth && imageHeight < mapImgMaxHeight && zoomLevel <= maxZoomLevel && imageWidth > 0 && imageHeight > 0 && zoomLevel > 0 && layers != null && layers.length > 0) {
		if (isInitializeSuccess() && imageWidth < mapImgMaxWidth && imageHeight < mapImgMaxHeight && imageWidth > 0 && imageHeight > 0 && zoomLevel > 0 && layers != null && layers.length > 0) {
			
			this.bbox = getEnvelope(lng, lat, zoomLevel);
			
			IDimension dimension = new SmpDimension(imageWidth, imageHeight);
			
			try {
				//////////////////////////////////////////////////////////////////////////////////////////////
				//TODO: there is an error on merge images function it runs in tilling toll but not run in here
				GetMapResult tempGetMapResult = getWmsLayers().get(0).getMap(getWmsLayersByIdList(layers), wmsNamedStyles, coordinateSystem, bbox, dimension, WmsImageReturnType.png);
				
				if (tempGetMapResult.getImage() != null && tempGetMapResult.getImage().length > 0) {
					
					return tempGetMapResult.getImage();
				} else {
					
					System.out.println("Error on rendering layers !!! ERROR: " + tempGetMapResult.getException());
				}
				return null;
			} catch (Exception ex) {
				
				System.out.println("Error on getMap request ! ERROR : " + ex);
			}
		} else {
			
			System.out.println("Input parameters are not suitible for Get Map Request !");
		}
		return null;
	}

	public MdSmpGISApp getRelatedApplication() {
		return relatedApplication;
	}

	public void setRelatedApplication(MdSmpGISApp relatedApplication) {
		this.relatedApplication = relatedApplication;
	}

	public List<IWMSLayer> getWmsLayersByIdList(int[] layers) {
		
		List<Integer> tempIntList = new ArrayList<Integer>(); 
		
		for (int tempLayer : layers) {
			
			tempIntList.add(tempLayer);
		}
		return getLayersFromMetadata(getRelatedApplication(), tempIntList);
	}

	public byte[] getMapAsByteArr(IFeature feature, int imageWidth, int imageHeight, int ratio, boolean drawFeature, String colorHex) {
		
		if (!isInitializeSuccess() && !isTryBefore()) {
			
			setInitializeSuccess(setGlobals());
		}
		if (isInitializeSuccess() && imageWidth < mapImgMaxWidth && imageHeight < mapImgMaxHeight && imageWidth > 0 && imageHeight > 0) {
			
			if (feature != null && feature.getDefaultGeometry() != null){
				
				this.bbox = getEnvelope(feature, ratio);
				
				IDimension dimension = new SmpDimension(imageWidth, imageHeight);
				
				try {
					
					GetMapResult tempGetMapResult = getWmsLayers().get(0).getMap(getWmsLayers(), wmsNamedStyles, coordinateSystem, bbox, dimension, WmsImageReturnType.png);
					
					if (tempGetMapResult.getImage() != null && tempGetMapResult.getImage().length > 0) {
						
						if (drawFeature) {
							
							return drawFeatureToMap(tempGetMapResult.getImage(), feature, bbox);
						} else {
							
							return tempGetMapResult.getImage();
						}
					} else {
						
						System.out.println("Error on rendering layers !!! ERROR: " + tempGetMapResult.getException());
					}
					return null;
				} catch (Exception ex) {
					
					System.out.println("Error on getMap request ! ERROR : " + ex);
				}
			} else {
				
				System.out.println("Get map could not work with null feature !");
			}
		} else {
			
			System.out.println("Input parameters are not suitible for Get Map Request !");
		}
		return null;
	}

	private IEnvelope getEnvelope(IFeature feature, int ratio) {
		
		if (feature != null && feature.getDefaultGeometry() != null) {
			
			try {
				
				ReferencedEnvelope featureEnvelope =((SmpFeature)feature).getGeoToolFeature().getBounds();
				
				double differenceX = featureEnvelope.getMaxX() - featureEnvelope.getMinX();
				
				double differenceY = featureEnvelope.getMaxY() - featureEnvelope.getMinY();
				
				double offset = 100;
				
				if (differenceX > differenceY) {
					
					offset = differenceX * (ratio - 100) / 100; 
				} else {
					
					offset = differenceY * (ratio - 100) / 100;
				}

				
				System.out.println("featureEnvelope.getMaxX : "+featureEnvelope.getMaxX());
				System.out.println("featureEnvelope.getMaxY : "+featureEnvelope.getMaxY());				

				System.out.println("featureEnvelope.getMinX : "+featureEnvelope.getMinX());
				System.out.println("featureEnvelope.getMinY : "+featureEnvelope.getMinY());				
				
				
				System.out.println("differenceX : "+differenceX);
				System.out.println("differenceY : "+differenceY);
				System.out.println("offset : "+offset);
				
				
				IEnvelope envelope = new SmpBoundingRectangle(featureEnvelope.getMinX() - offset, featureEnvelope.getMinY() - offset, featureEnvelope.getMaxX() + offset, featureEnvelope.getMaxY() + offset);
	
				//CoordinateReferenceSystem crs = featureEnvelope.getCoordinateReferenceSystem();

				//ICoordinateSystem featureCS = csTransformer.getCoordinateSystemFromCRS(crs);
				
				//ICoordinateSystem targetCS = new SmpCoordinateSystem("EPSG:4326");
				
				//envelope = csTransformer.SmpBoundingRectangleCoordinateTransformer(envelope, featureCS, targetCS);
				
				return envelope;
			} catch (Exception ex) {
				
				System.out.println("Error on calculating bounding box from feature ! ERROR : " + ex);
			}
			
		} else {

			System.out.println("Envelope cannot calculate from null feature !");
		}
		return null;
	}

	private byte[] drawFeatureToMap(byte[] orjinalMap, IFeature feature, IEnvelope bound) {

		try {
		
			if (orjinalMap != null && orjinalMap.length != 0 && feature != null && feature.getDefaultGeometry() != null) {
				
				InputStream inputStream = new ByteArrayInputStream(orjinalMap);
				
				BufferedImage pureMap = javax.imageio.ImageIO.read(inputStream);
				
				BufferedImage scaledImage = new BufferedImage(pureMap.getWidth(), pureMap.getHeight(), BufferedImage.TYPE_INT_ARGB);
				
	            Graphics2D device = scaledImage.createGraphics();
	            
	            device.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	            
	            device.drawImage(pureMap, 0, 0, null);
	            
	            //feature = csTransformer.FeatureCoordinateTransformer(feature, csTransformer.getCoordinateSystemFromCRS(((SmpFeature)feature).getGeoToolFeature().getBounds().getCoordinateReferenceSystem()), new SmpCoordinateSystem("EPSG:4326"));
	            
	            if (feature.getDefaultGeometry().getGeometryType().equals("Polygon")) {
    				
	            	ICoordinate[] coordinates = feature.getDefaultGeometry().getCoordinates();
		            
	            	int[] xPointArray = new int[coordinates.length]; 
	            	int[] yPointArray = new int[coordinates.length]; 
	            	
	            	int coordCntr = 0;
	            	
		            for (ICoordinate tempCoordinate : coordinates) {
						//pixelX = ((x1 - minX).(maxP - minP)) / (maxX - minX)
		            	xPointArray[coordCntr] = ((int) (((tempCoordinate.getX() - bound.getMinX())*(pureMap.getWidth())) / (bound.getMaxX() - bound.getMinX())));
		            	//pixelY = ((y1 - minY).(maxP - minP)) / (maxY - minY)
		            	yPointArray[coordCntr] = pureMap.getHeight() - ((int) (((tempCoordinate.getY() - bound.getMinY())*(pureMap.getHeight())) / (bound.getMaxY() - bound.getMinY())));
		            	coordCntr++;
					}
		    		// Fill in polygon
		    		//device.setColor(Color.LIGHT_GRAY);
		    		//device.fillPolygon(xPointArray, yPointArray, coordinates.length);
		    		//Draw polygon surface
		    		device.setColor(Color.BLUE);
		    		Stroke s =  new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
		    		device.setStroke(s);
		    		device.drawPolygon(xPointArray, yPointArray, coordinates.length);
				}  
	            if (feature.getDefaultGeometry().getGeometryType().equals("Line")) {
    				
	            	ICoordinate[] coordinates = feature.getDefaultGeometry().getCoordinates();
		            
	            	int[] xPointArray = new int[coordinates.length]; 
	            	int[] yPointArray = new int[coordinates.length]; 
	            	
	            	int coordCntr = 0;
	            	
		            for (ICoordinate tempCoordinate : coordinates) {
						//pixelX = ((x1 - minX).(maxP - minP)) / (maxX - minX)
		            	xPointArray[coordCntr] = ((int) (((tempCoordinate.getX() - bound.getMinX())*(pureMap.getWidth())) / (bound.getMaxX() - bound.getMinX())));
		            	//pixelY = ((y1 - minY).(maxP - minP)) / (maxY - minY)
		            	yPointArray[coordCntr] = pureMap.getHeight() - ((int) (((tempCoordinate.getY() - bound.getMinY())*(pureMap.getHeight())) / (bound.getMaxY() - bound.getMinY())));
		            	coordCntr++;
					}
		    		//Draw polygon surface
		    		device.setColor(Color.BLUE);
		    		Stroke s =  new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
		    		device.setStroke(s);
		    		device.drawPolyline(xPointArray, yPointArray, coordinates.length);
				}
	            if (feature.getDefaultGeometry().getGeometryType().equals("Point")) {
    				
	            	ICoordinate coordinate = feature.getDefaultGeometry().getCoordinate();
					//pixelX = ((x1 - minX).(maxP - minP)) / (maxX - minX)
		            int	xPoint = ((int) (((coordinate.getX() - bound.getMinX())*(pureMap.getWidth())) / (bound.getMaxX() - bound.getMinX())));
	            	//pixelY = ((y1 - minY).(maxP - minP)) / (maxY - minY)
		            int yPoint = pureMap.getHeight() - ((int) (((coordinate.getY() - bound.getMinY())*(pureMap.getHeight())) / (bound.getMaxY() - bound.getMinY())));
		    		// Fill in polygon
		    		device.setColor(Color.LIGHT_GRAY);
		    		device.fillRect(xPoint, yPoint, 1, 1);
		            //Draw polygon surface
		    		device.setColor(Color.BLUE);
		    		Stroke s =  new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
		    		device.setStroke(s);
		    		device.drawRect(xPoint, yPoint, 1, 1);
				}   
				IImageProcesses imageProcesses = new ImageProcesses();
	
				byte[] resultbyteArrImage = imageProcesses.bufferedImageToByteArr(scaledImage);
				
				return resultbyteArrImage;
			} else {
				
				return orjinalMap;
			}
			
		} catch (Exception ex) {

			System.out.println("Error on adding pin to map !" + ex);
			
			return null;
		}
	}

	public byte[] getMapAsByteArr(IFeature feature, int imageWidth, int imageHeight, int ratio, boolean drawFeature, String colorHex, int[] layers) {
		
		if (!isInitializeSuccess() && !isTryBefore()) {
			
			setInitializeSuccess(setGlobals());
		}
		if (isInitializeSuccess() && imageWidth < mapImgMaxWidth && imageHeight < mapImgMaxHeight && imageWidth > 0 && imageHeight > 0) {
			
			if (feature != null && feature.getDefaultGeometry() != null){
				
				this.bbox = getEnvelope(feature, ratio);
				
				IDimension dimension = new SmpDimension(imageWidth, imageHeight);
				
				try {
					
					GetMapResult tempGetMapResult = getWmsLayers().get(0).getMap(getWmsLayersByIdList(layers), wmsNamedStyles, coordinateSystem, bbox, dimension, WmsImageReturnType.png);
					
					if (tempGetMapResult.getImage() != null && tempGetMapResult.getImage().length > 0) {
						
						if (drawFeature) {
							
							return drawFeatureToMap(tempGetMapResult.getImage(), feature, bbox);
						} else {
							
							return tempGetMapResult.getImage();
						}
					} else {
						
						System.out.println("Error on rendering layers !!! ERROR: " + tempGetMapResult.getException());
					}
					return null;
				} catch (Exception ex) {
					
					System.out.println("Error on getMap request ! ERROR : " + ex);
				}
			} else {
				
				System.out.println("Get map could not work with null feature !");
			}
		} else {
			
			System.out.println("Input parameters are not suitible for Get Map Request !");
		}
		return null;
	}

	
}