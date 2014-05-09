package com.sampas.socbs.core.tiler.tool.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.geotools.feature.Feature;
import org.geotools.feature.FeatureType;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.CoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.ITileDataStore;
import com.sampas.socbs.core.data.provider.services.impl.TileDataStoreSrv;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.ITileLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.data.providers.impl.SmpTileLayerProviderSrv;
import com.sampas.socbs.core.dataset.feature.IAttributeType;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureTypeName;
import com.sampas.socbs.core.dataset.feature.impl.SmpAttributeType;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureCollection;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureTypeName;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpCoordinate;
import com.sampas.socbs.core.geometry.impl.SmpDimension;
import com.sampas.socbs.core.geometry.impl.SmpEnvelope;
import com.sampas.socbs.core.gisdatabase.tools.servis.IGisDatabaseServis;
import com.sampas.socbs.core.gisdatabase.tools.servis.impl.GisDatabaseServis;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayer;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.ITileLayer;
import com.sampas.socbs.core.maplayer.impl.SmpLayerNames;
import com.sampas.socbs.core.symbology.IStyle;
import com.sampas.socbs.core.symbology.impl.SmpStyle;
import com.sampas.socbs.core.tile.toolbox.BBOX;
import com.sampas.socbs.core.tile.toolbox.MimeType;
import com.sampas.socbs.core.tiler.tool.ITilingTool;
import com.sampas.socbs.core.tools.impl.ImageProcesses;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTReader;


public class TilingTool implements ITilingTool {
	
	private TilingRequirements tilingRequirements = null;
	private ICoordinateSystem defCoordinateSystem = new SmpCoordinateSystem("EPSG:4326");
	private IEnvelope globalEnvelope;
	private int tileHeaderSize = 16; 
	private ImageProcesses imageProcessor = new ImageProcesses();
	private int cntrByte = 0;
	private List<ILayer> layers;
	private String errorStr = "";
	private String infoStr = "";
	private int delayMs = 300;
	
	
	public TilingTool(List<ILayer> layers, TilingRequirements tilingRequirements) {
		
		this.layers = layers;
		
		this.tilingRequirements = tilingRequirements;
	}
	
	public boolean createTiles(boolean editForGeoWebCache) {
		
		boolean isMetadataSuccess = checkAndSetAppMetadata();
		
		if (isMetadataSuccess) {
			
			boolean isLayerCreateSuccess = checkAndCreateTileLayer();
			
			if (isLayerCreateSuccess) {

				boolean isTilesCreationSuccess = createTilesToLocal(editForGeoWebCache);
				
				if (isTilesCreationSuccess) {
					
					infoStr = "Tile Olusturma islemi basari ile tamamlandi";
					System.out.println(infoStr);
					return true;
				} else {
					
					errorStr = "Tile Olusturma islemi basari ile tamamlanamadi !";
					System.out.println(errorStr);
					return false;
				}
				
			} else {
				
				errorStr = "Error on creating tiles from datastore !";
				System.out.println(errorStr);
				return false;
			}
			
		} else {
			
			errorStr = "Error on reading metadata !";
			System.out.println(errorStr);
			return false;
		}
		
	}

	private  boolean checkAndSetAppMetadata() {

		if (tilingRequirements.getAppName() == null || tilingRequirements.getAppName().equals("")) {
			errorStr = "Error on getting Application name from metadata, please check !";
			System.out.println(errorStr);
			return false;
		} else if (tilingRequirements.getDBDataStore() == null) {
			errorStr = "Error on getting Data Store metadata, please check !";
			System.out.println(errorStr);
			return false;
		} else if (tilingRequirements.getTileLayerName() == null || tilingRequirements.getTileLayerName().equals("")) {
			errorStr = "Error on getting Tile Layer Name from metadata, please check !";
			System.out.println(errorStr);
			return false;
		} else if (tilingRequirements.getGWCCacheDirectory() == null || tilingRequirements.getGWCCacheDirectory().equals("")) {
			errorStr = "Error on getting GWC(GeoWebCache) Local Save Directory Path from metadata, please check !";
			System.out.println(errorStr);
			return false;
		} else if (tilingRequirements.getLayerList() == null || tilingRequirements.getLayerList().length <= 0) {
			errorStr = "Error on getting Laye List from metadata, please check !";
			System.out.println(errorStr);
			return false;
		} else if (tilingRequirements.getLayerList() == null || tilingRequirements.getLayerList().length <= 0) {
			errorStr = "Error on getting Data Store metadata, please check !";
			System.out.println(errorStr);
			return false;
		} else if (tilingRequirements.getMaxPyramidLevel() <= 0) {
			errorStr = "Error on getting Maximum Pyramid Level from metadata, please check !";
			System.out.println(errorStr);
			return false;
		} else {
			return true;
		}
	}
	
	private  boolean createTilesToLocal(boolean editForGeoWebCache) {

		try {
			
			if (layers.size() > 0) {
				
				ILayerNames layerNames = new SmpLayerNames();
				
				for (int i = 0; i < layers.size(); i++) {
					
					layerNames.addLayerName(layers.get(i).getName());
				}
				
				URL tileServerUrl = new URL(tilingRequirements.getTileServerAddress());
				
				TileDataStoreSrv tileDataStoreSrv = new TileDataStoreSrv(tileServerUrl, tilingRequirements.getTileServerVersion(), tilingRequirements.getTileImgFileType(), tilingRequirements.getMetaTilingRange(), tilingRequirements.isEnableCatching());

				ITileDataStore tileDataProvider = tileDataStoreSrv.getTileDataStore();

				ITileLayerProvider tileLayerProvider = new SmpTileLayerProviderSrv();

				List<ITileLayer> tileLayers = tileLayerProvider.getLayers(tileDataProvider, layerNames);
				
				List<ITileLayer> tempTileLayes = new ArrayList<ITileLayer>();
				
				for (int layerCntr = 0; layerCntr < layers.size(); layerCntr++) {
					
					for (int tileLayerCntr = 0; tileLayerCntr < tileLayers.size(); tileLayerCntr++) {
						
						if (layers.get(layerCntr).getName().equals(tileLayers.get(tileLayerCntr).getName())) {
							
							tempTileLayes.add(tileLayers.get(tileLayerCntr));
						}
					}
				}
				
				tileLayers = tempTileLayes;
				
				if (tileLayers.size() > 0) {
					
					int minPyramidLevel = tilingRequirements.getMinPyramidLevel();
					
					int maxPyramidLevel = tilingRequirements.getMaxPyramidLevel();
					
					BBOX bbox = new BBOX(globalEnvelope.getMinX(), globalEnvelope.getMinY(), globalEnvelope.getMaxX(), globalEnvelope.getMaxY());
					
					IDimension tileDimension = new SmpDimension(tilingRequirements.getTileDimention(), tilingRequirements.getTileDimention());
					
					int[] tileBounds = null;
					int tempBoundX = 0;
					int tempBoundY = 0;
					int tileCountX = 0;
					int tileCountY = 0;
					int tileCount = 0;
					
					BufferedImage tileImage = null;
					
					FilePathGenerator filePathGenerator =  new FilePathGenerator();
					
					long[] tiles = new long[3];
					
					String filePathFromMeta = tilingRequirements.getGWCCacheDirectory();
					filePathFromMeta = filePathFromMeta.replace("//", "\\");
					boolean endChr = filePathFromMeta.endsWith("\\");
					if (endChr) {
						
						filePathFromMeta = filePathFromMeta.substring(0, filePathFromMeta.length() - 1);
					}
					
					String imgTypeStr = tilingRequirements.getTileImgFileType().name();
					
					infoStr = "Kayit yapilan klasorun yeri : " + filePathFromMeta;
					System.out.println(infoStr);
					
					long fullTileCount = 0;
					int tempTileCountX = 0;
					int tempTileCountY = 0;
					int tempTileCount = 0;
					
					//Calculating total tile image file count
					for (int tempZoomint = minPyramidLevel; tempZoomint < maxPyramidLevel; tempZoomint++) {
						
						tileBounds = tileDataProvider.getTileBoundsFromBBox(tileLayers.get(0), tilingRequirements.getDefaultSRS(), bbox, tempZoomint);
						
						tempTileCountX = (tileBounds[2] - tileBounds[0]);
						
						tempTileCountY = (tileBounds[3] - tileBounds[1]);
						
						tempTileCount = tempTileCountX * tempTileCountY;
						if (tempTileCount == 0) {
							tempTileCount = 1;
						}
						
						fullTileCount += tempTileCount;
					}
					
					infoStr = "Olusturulacak toplam tile imgesi sayisi : " + fullTileCount;
					System.out.println(infoStr);
					
					byte[] tileHeader = new byte[tileHeaderSize];
					//TODO tile header must generate with geowebcache reference guide but for now
					tileHeader[0] = 0;tileHeader[1] = 0;tileHeader[2] = 0;tileHeader[3] = 0;tileHeader[4] = 0;tileHeader[5] = 0;tileHeader[6] = 0;tileHeader[7] = -56;tileHeader[8] = 0;tileHeader[9] = 0;tileHeader[10] = 0;tileHeader[11] = 0;tileHeader[12] = 0;tileHeader[13] = 0;tileHeader[14] = 0;tileHeader[15] = 0;
					
					FileOutputStream fileOutputStream = null;
					
					File directory = null;
					
					File file = null;
					
					List<IStyle> styles = new ArrayList<IStyle>();
					
					for (int i = 0; i < tileLayers.size(); i++) {
						styles.add(new SmpStyle(""));
					}
					
					for (int zoomint = minPyramidLevel; zoomint < maxPyramidLevel; zoomint++) {
						
						tileBounds = tileDataProvider.getTileBoundsFromBBox(tileLayers.get(0), tilingRequirements.getDefaultSRS(), bbox, zoomint);
						
						tileCountX = (tileBounds[2] - tileBounds[0]);
						
						tileCountY = (tileBounds[3] - tileBounds[1]);
						
						tileCount = tileCountX * tileCountY;
						if (tileCount == 0) {
							tileCount = 1;
						}
						
						tempBoundX = tileBounds[0];
						
						tempBoundY = tileBounds[1];
						
						infoStr = "Katmanlardan zoom seviyesi " + zoomint + " de" + " elde edilecek tile sayisi = " + tileCount;
						System.out.println(infoStr);
						
						for (int xOrdinate = 0; xOrdinate <= tileCountX; xOrdinate++) {
							
							for (int yOrdinate = 0; yOrdinate <= tileCountY; yOrdinate++) {
								
								tileImage = tileDataProvider.getTileWithTileLocation(tileLayers, tilingRequirements.getDefaultSRS(), styles, tempBoundX, tempBoundY, zoomint, tileDimension, tilingRequirements.getTileImgFileType());
								
								MimeType mimeType =  new MimeType(imgTypeStr, imgTypeStr, imgTypeStr, imgTypeStr, true); 
								
								tiles[0] = tempBoundX;
								tiles[1] = tempBoundY;
								tiles[2] = zoomint;
								
								String[] resultSet = filePathGenerator.tilePath(filePathFromMeta, tilingRequirements.getDsNameSpace() + ":" + tilingRequirements.getTileLayerName(), tiles, tilingRequirements.getDefaultSRS().getNumber(), mimeType, -1);
								
								try {
									
									directory = new File(resultSet[0]);
									
									directory.mkdirs();
									
									file = new File(resultSet[0] + "\\" + resultSet[1]);
									
									if(!file.exists()){
									      file.createNewFile();
									}
									
									fileOutputStream = new FileOutputStream(file); 
									
									if (editForGeoWebCache) {
										
										fileOutputStream.write(addHeaderToImage(tileImage, tileHeader));
									} else {
										fileOutputStream.write(imageProcessor.bufferedImageToByteArr(tileImage));
									}
									
									fileOutputStream.flush();
									
									fileOutputStream.close();
									
									infoStr = "Pramid level : " + zoomint + ", Kaydedilen tile grid X : " + tempBoundX + " - Y : " + tempBoundY + ", Kalan tile sayisi = " + --fullTileCount;
									System.out.println(infoStr);
								
								} catch (Exception ex) {
									errorStr = "Error on writing file to filesystem ! ERROR: " + ex;
									System.out.println(errorStr);
								}
								
								//if not wait for some time it throws exeption like;
								//Error on Creating Image from kvp encoding. Error : java.net.BindException: Address already in use: connect
								Thread.sleep(delayMs);
								
								tempBoundY++;
							}
							tempBoundY = tileBounds[1];
							tempBoundX++;						
						}
						tempBoundX = tileBounds[0];
					}
				} else {
					
					errorStr = "No any tile layer found. Check data source and metadata !";
					System.out.println(errorStr);
					return false;
				}
				
				return true;
			}
		} catch (Exception ex) {

			errorStr = "Error on creating new tiles ! ERROR: " + ex;
			System.out.println(errorStr);
		}
		
		return false;
	}

	private byte[] addHeaderToImage(BufferedImage pureImage, byte[] tileHeader) {
		
		try {

			byte[] pureImgBytes = imageProcessor.bufferedImageToByteArr(pureImage);
			
			byte[] newTileBytes = new byte[pureImgBytes.length + tileHeaderSize];
			
			for (int i = 0; i < tileHeaderSize; i++) {
				
				newTileBytes[i] = tileHeader[i];
			}
			
			for (int k = tileHeaderSize; k < newTileBytes.length; k++) {
				
				newTileBytes[k] = pureImgBytes[cntrByte];
				cntrByte++;
			}
			cntrByte = 0;
			
			return (newTileBytes);
		
		} catch (Exception ex) {

			errorStr = "Error on creating new tiles ! ERROR: " + ex;
			System.out.println(errorStr);
			return null;
		}
	}

	private  IEnvelope getMaxEnvelopeFromLayers(List<ILayer> layersFEnv) {
		
		try {
			
			if (layersFEnv == null || layersFEnv.size() <= 0) {
				
				return null;
			}
			
			double minX = Double.MAX_VALUE;
			double maxX = Double.MIN_VALUE;
			double minY = Double.MAX_VALUE;
			double maxY = Double.MIN_VALUE;
			
			IEnvelope tempEnvelope = new SmpEnvelope(Double.MAX_VALUE, Double.MAX_VALUE, Double.MIN_VALUE, Double.MAX_VALUE);
			
			//for (int i = 0; i < layersFEnv.size(); i++) {
			for (int i = 0; i < 1; i++) {
				
				try {
					
					if (layersFEnv.get(i).getCoordinateSystem().equals(defCoordinateSystem)) {
						
						tempEnvelope = layersFEnv.get(i).getExtent();
					} else {
						
						ICoordinateSystemTransformers coorTransformers = new CoordinateSystemTransformers();
						
						tempEnvelope = coorTransformers.SmpBoundingRectangleCoordinateTransformer(layersFEnv.get(i).getExtent(), layersFEnv.get(i).getCoordinateSystem(), defCoordinateSystem);
					}
					
				} catch (Exception ex) {
					
					errorStr = "Error on taking envelope from layer : " + layersFEnv.get(i).getName();
					System.out.println(errorStr);
					return null;
				}
				
				if (tempEnvelope.getMinX() < minX) {
					
					minX = tempEnvelope.getMinX();
				}
				if (tempEnvelope.getMinY() < minY) {
					
					minY = tempEnvelope.getMinY();
				}
				if (tempEnvelope.getMaxX() > maxX) {
					
					maxX = tempEnvelope.getMaxX();
				}
				if (tempEnvelope.getMaxY() > maxY) {
					
					maxY = tempEnvelope.getMaxY();
				}
			}
			
			IEnvelope resultEnvelope = new SmpEnvelope(minX, minY, maxX, maxY);
			
			return (resultEnvelope);
			
		} catch (Exception ex) {
			
			errorStr = "Error on calculating Envelope from ! ERROR: " + ex;
			System.out.println(errorStr);
			return null;
		}
	}
	
	private  boolean checkAndCreateTileLayer() {

		try {
			
			if (layers == null && layers.size() <= 0) {
				
				errorStr = "Error on taking layers !";
				System.out.println(errorStr);
				return false;
			}
			
			IEnvelope maxEnvelope = getMaxEnvelopeFromLayers(layers);
			
			if (maxEnvelope == null) {

				errorStr = "Error on creating tile layer calculated envelope is null, check metadata !";
				System.out.println(errorStr);
				return false;
			}
			
			globalEnvelope = maxEnvelope;
			
			IFeatureDataStore featureDataStore = tilingRequirements.getDBDataStore();
			
			IGisDatabaseServis gisDatabaseServis = new GisDatabaseServis(featureDataStore);
			
			IFeatureTypeName featureTypeName = new SmpFeatureTypeName(tilingRequirements.getTileLayerName());
			
			List<IAttributeType> attributeItems = new ArrayList<IAttributeType>();
			IAttributeType attributeTypeOne = new SmpAttributeType(tilingRequirements.getTileLayerGeomColumnName(), Polygon.class);
			attributeItems.add(attributeTypeOne);
			IAttributeType attributeTypeTwo = new SmpAttributeType("ACIKLAMA", String.class);
			attributeItems.add(attributeTypeTwo);
			
			String[] dsFeatureTypeNames = tilingRequirements.getDBDataStore().getTypeNames();
			boolean isTypeCreated = false;
			
			for (int i = 0; i < dsFeatureTypeNames.length; i++) {
				if (dsFeatureTypeNames[i].equals(featureTypeName.getFeatureTypeName())) {
					
					isTypeCreated = true;
					break;
				}
			} 
			
			if (!isTypeCreated) {
				
				boolean isTypeCreateSuccess = gisDatabaseServis.createFeatureType(featureTypeName, attributeItems);
				
				if (!isTypeCreateSuccess) {
					
					errorStr = "Error on creating tile layer, please check your metadata !";
					System.out.println(errorStr);
					return false;
				}
				
				List<ICoordinate> coordinates = new ArrayList<ICoordinate>();
				ICoordinate minXminY = new SmpCoordinate(maxEnvelope.getMinX(),maxEnvelope.getMinY());
				coordinates.add(minXminY);
				ICoordinate minXmaxY = new SmpCoordinate(maxEnvelope.getMinX(),maxEnvelope.getMaxY());
				coordinates.add(minXmaxY);
				ICoordinate maxXmaxY = new SmpCoordinate(maxEnvelope.getMaxX(),maxEnvelope.getMaxY());
				coordinates.add(maxXmaxY);
				ICoordinate maxXminY = new SmpCoordinate(maxEnvelope.getMaxX(),maxEnvelope.getMinY());
				coordinates.add(maxXminY);
				
				String coordinatesStr = "";
				
				for (int i = 0; i < coordinates.size(); i++) {
					
					coordinatesStr = coordinatesStr + coordinates.get(i).getX() + " " + coordinates.get(i).getY() + ","; 
				}
				coordinatesStr = coordinatesStr + coordinates.get(0).getX() + " " + coordinates.get(0).getY();
				
				WKTReader wktReader = new WKTReader();
				
				Polygon geometry = (Polygon) wktReader.read("POLYGON((" + coordinatesStr + "))");
				
				FeatureType featureType = featureDataStore.getSchema(featureTypeName.getFeatureTypeName());
				
				Object attributes[] = new Object[2];
				attributes[0] = geometry;
				
//				String layerStr = "";
//				
//				for (int i = 0; i < layers.size(); i++) {
//					
//					layerStr = layerStr + layers.get(i).getName() + ", ";
//				}
				
				attributes[1] = "Calculated Extend layers: " + layers.size();
				
				Feature feature = featureType.create(attributes);
				
				IFeature smpFeature = new SmpFeature(feature);
				
				IFeatureCollection features = new SmpFeatureCollection();
				
				features.addFeature(smpFeature);
				
				boolean isFeatureWriteSuccess = gisDatabaseServis.writeFeatures(features);
				
				if (isFeatureWriteSuccess) {
					infoStr = "Adding new referance feature to tile layer !";
					System.out.println(infoStr);
				}
				return isFeatureWriteSuccess;
			} else {
				
				infoStr = "Tile layer name: " + tilingRequirements.getTileLayerName() + " already exist !";
				System.out.println(infoStr);
				
				try {
					
					IFeatureDataStore wfsDataStore = tilingRequirements.getWFSDataStore();
					
					IFeatureLayerProvider wfsLayerProvider = new SmpFeatureLayerProviderSrv();
					
					List<IFeatureLayer> wfsLayers = wfsLayerProvider.getLayers(wfsDataStore);
					
					boolean isDummyLayerOnWFSOK = false;
					
					for (int i = 0; i < wfsLayers.size(); i++) {
						
						if (wfsLayers.get(i).getName().equals(tilingRequirements.getDsNameSpace() + ":" + tilingRequirements.getTileLayerName())) {
							
							isDummyLayerOnWFSOK = true;
							break;
						}
					}
					
					if (!isDummyLayerOnWFSOK) {
					
						System.out.println("--------------------------------------------------------------------------------");
						System.out.println("NEXT STEP : Tile Layer must add to GeoServer");
						System.out.println("Use information for creating new data store to geoserver");
						System.out.println("Database Type : " + tilingRequirements.getDsType());
						System.out.println("NameSpace : " + tilingRequirements.getDsNameSpace());
						System.out.println("Host Address : " + tilingRequirements.getDsHost());
						System.out.println("Port Number : " + tilingRequirements.getDsPort());
						System.out.println("Instance : " + tilingRequirements.getDsInstance());
						System.out.println("User: " + tilingRequirements.getDsUser());
						System.out.println("Pass : " + tilingRequirements.getDsPass());
						System.out.println("Schema : " + tilingRequirements.getDsSchema());
						System.out.println("NEXT STEP : Then create tile layer tiles on gwc (Not mandatory)");
						System.out.println("NEXT STEP : Then run this application again");
						System.out.println("--------------------------------------------------------------------------------");
						
					}
					
					return true;
					
				} catch (Exception ex) {

					errorStr = "Error on cheking tile layer on related WFS from metadata ! ERROR: " + ex;
					System.out.println(errorStr);
					return false;
				}
			}
		
		} catch (Exception ex) {

			errorStr = "Error on creating tile layer ! ERROR: " + ex;
			System.out.println(errorStr);
			return false;
		}
	}

	public String getErrorStr() {
		return errorStr;
	}

	public void setErrorStr(String errorStr) {
		this.errorStr = errorStr;
	}

	public String getInfoStr() {
		return infoStr;
	}

	public void setInfoStr(String infoStr) {
		this.infoStr = infoStr;
	}
}
