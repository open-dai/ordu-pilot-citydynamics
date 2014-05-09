package com.sampas.socbs.core.tile.impl;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.sampas.socbs.core.data.impl.ATileDataProvider;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.geometry.impl.SmpDimension;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.ITileLayer;
import com.sampas.socbs.core.symbology.IStyle;
import com.sampas.socbs.core.tile.toolbox.BBOX;
import com.sampas.socbs.core.tile.toolbox.CacheFactory;
import com.sampas.socbs.core.tile.toolbox.CacheKeyFactory;
import com.sampas.socbs.core.tile.toolbox.GetCapabilitiesConfiguration;
import com.sampas.socbs.core.tile.toolbox.SRS;

public class TileDataStore extends ATileDataProvider {
	
	private URL tileServerURL;
	
	private String tileServerURLStr;
	
	//Default Version is 1.1.1
	private String tileServerVersion = "1.1.1";
	
	public enum TileImageReturnType {jpeg, png, gif}
	
	public enum TileServerVersion {ver100, ver110, ver111, ver130}
	
	//Default Meta Tiling value is "3x3"
	private String metaTiling = "3x3";
	
	//Default Tile Image Return value is "Image/png"
	private String tileImgReturnType = "image/png";
	
	private String allowCacheByPass = "true";
	
	private int defDimention = 256;
	
	public TileDataStore(URL tileServerURL, TileServerVersion tileServerVersion, TileImageReturnType tileImageReturnType, int metaTiling, boolean allowCacheByPass) {
		
		//this.tileServerURLStr = tileServerURL.toString().replace("\"", "");
		this.tileServerURLStr = tileServerURL.toString();
		
		if (tileServerVersion != null) {
			if (tileServerVersion.toString().compareTo("ver100") == 0) {
				this.tileServerVersion = "1.0.0";
			} else if (tileServerVersion.toString().compareTo("ver110") == 0){
				this.tileServerVersion = "1.1.0";
			} if (tileServerVersion.toString().compareTo("ver111") == 0) {
				this.tileServerVersion = "1.1.1";
			} else if (tileServerVersion.toString().compareTo("ver130") == 0){
				this.tileServerVersion = "1.3.0";
			}		
		}
		
		if (tileImageReturnType != null) {
			if (tileImageReturnType == TileImageReturnType.gif) {
				this.tileImgReturnType = "image/gif";
			} else if (tileImageReturnType == TileImageReturnType.jpeg) {
				this.tileImgReturnType = "image/jpeg";
			} else if (tileImageReturnType == TileImageReturnType.png) {
				this.tileImgReturnType = "image/png";
			} 
		}
		
		if (allowCacheByPass) {
			
			this.allowCacheByPass = "true";
		} else if (!allowCacheByPass) {
			
			this.allowCacheByPass = "false";
		}
		
		if (metaTiling != -1 && metaTiling > 0) {
			
			this.metaTiling = String.valueOf(metaTiling) + "x" + String.valueOf(metaTiling);
		}
	}
	
	public List<ITileLayer> getLayerDefinitions() {
		
		try {
			
			List<TileLayer> tileLayerList = new ArrayList<TileLayer>();
			
			CacheKeyFactory cacheKeyFactory = new CacheKeyFactory();
			
			CacheFactory cacheFactory = new CacheFactory(cacheKeyFactory);
			
			GetCapabilitiesConfiguration getCapabilitiesConfiguration = new GetCapabilitiesConfiguration(cacheFactory, this.tileServerURLStr, this.tileImgReturnType, this.metaTiling, allowCacheByPass);
			
			tileLayerList = getCapabilitiesConfiguration.getTileLayers(true);
			
			if (tileLayerList.size() != 0) {
				
				List<ITileLayer> tileLayers = new ArrayList<ITileLayer>();
				
				for (int i = 0; i < tileLayerList.size(); i++) {
					
					tileLayers.add(tileLayerList.get(i));
				}
				
				return tileLayers;
			} else {
				
				return null;
			}
		
		} catch (Exception ex) {
			
			System.out.println("|Error on getting tile layers ! ERROR : " + ex);
			
			return null;
		}
	}
	
	public List<ITileLayer> getLayerDefinitions(ILayerNames layerNames) {
		
		try {
			
			List<TileLayer> tileLayerList = new ArrayList<TileLayer>();
			
			CacheKeyFactory cacheKeyFactory = new CacheKeyFactory();
			
			CacheFactory cacheFactory = new CacheFactory(cacheKeyFactory);
			
			GetCapabilitiesConfiguration getCapabilitiesConfiguration = new GetCapabilitiesConfiguration(cacheFactory, this.tileServerURLStr, this.tileImgReturnType, this.metaTiling, allowCacheByPass);
			
			tileLayerList = getCapabilitiesConfiguration.getTileLayers(true);
			
			if (tileLayerList.size() != 0) {
				
				List<ITileLayer> tileLayers = new ArrayList<ITileLayer>();
				
				for (int i = 0; i < tileLayerList.size(); i++) {
					
					for (int j = 0; j < layerNames.getLayerNames().size(); j++) {
						
						if (layerNames.getLayerNames().get(j).equals(tileLayerList.get(i).getName())) {
							
							tileLayers.add(tileLayerList.get(i));
						}
					}
				}
				
				if (tileLayers.size() != 0) {
					
					return tileLayers;
				} else {
					
					return null;
				}
				
			} else {
				
				return null;
			}
		
		} catch (Exception ex) {
			
			System.out.println("|Error on getting tile layers ! ERROR : " + ex);
			
			return null;
		}
	}
	

	public int[] getTileBoundsFromBBox(ITileLayer tileLayer, SRS gridSRS, BBOX boundingBox, int zoomLevel) {

		try {
			
			Grid layerGrid = tileLayer.getGrid(gridSRS);
			
			GridCalculator gridCalculator = new GridCalculator(layerGrid);
	
			int[][] coveredGridsByBbox = gridCalculator.coveredGridLevels(boundingBox);
			
			//zoom level is between 0 - 30 
			int[] coveredGridsByZoomLevel = coveredGridsByBbox[zoomLevel];
			
			return coveredGridsByZoomLevel;
		} catch (Exception ex) {
			
			System.out.println("Error on calculating tile bounds ! ERROR : " + ex);
			return null;
		}
		
	}
	
	public BufferedImage getTileWithTileLocation(List<ITileLayer> tileLayers,  SRS layerSRS, List<IStyle> styles, int tileNoX, int tileNoY, int layerZoomLevel, IDimension tileDimension, TileImageReturnType returnType) {
		
		if (tileLayers != null && tileLayers.size() != 0 && layerSRS != null && styles != null && styles.size() != 0 && tileNoX >= 0 && tileNoY >= 0 && layerZoomLevel >= 0 && tileLayers.size() == styles.size()) {

			if (tileDimension == null) {
				tileDimension = new SmpDimension(defDimention, defDimention);
			}
			
			if (returnType == null) {
				returnType = TileImageReturnType.jpeg;
			}
			
			try {
			
				//This action done for find real Y axis tile number (it can be a bug)
				tileNoY = tileNoY * 2;
				
				BufferedImage tileImage = null;
				
				BufferedImage tempTileImage = null;
				
				for (int i = 0; i < tileLayers.size(); i++) {
					
					ITileLayer tileLayer = tileLayers.get(i);	
					
					IStyle style = styles.get(i);
										
					Grid layerGrid = tileLayer.getGrid(layerSRS);
						
					double fullGridMaxX = layerGrid.getGridBounds().coords[2];
					double fullGridMaxY = layerGrid.getGridBounds().coords[3];
					
					double stepLenghtX = (fullGridMaxX / Math.pow(2, layerZoomLevel));
					double stepLenghtY = (fullGridMaxY / Math.pow(2, layerZoomLevel));
					
					double minX = (tileNoX * stepLenghtX) - fullGridMaxX;
					double minY = (tileNoY * stepLenghtY) - fullGridMaxY;
					
					double maxX = minX + stepLenghtX;
					double maxY = minY + stepLenghtY;
					
					BBOX tileBbox = new BBOX(minX, minY, maxX, maxY);
					
					String tileRequest = tileServerURLStr + "?LAYERS=" + tileLayer.getName() + "&FORMAT=image/" + returnType + "&SERVICE=WMS&VERSION=" + tileServerVersion + "&REQUEST=GetMap&STYLES=" + style.getName() + "&SRS=" + layerSRS + "&BBOX=" + tileBbox.coords[0] + "," + tileBbox.coords[1] + "," + tileBbox.coords[2] + "," + tileBbox.coords[3] + "&WIDTH=" + (int)tileDimension.getWidth() + "&HEIGHT=" + (int)tileDimension.getHeight(); 
					
					tempTileImage = getTileImageFromURL(tileRequest);
					
					
					//TODO comment after tests
//					System.out.println(tileRequest);
//					ImageProcesses imageProcesses = new ImageProcesses();
//					imageProcesses.showImageFromBufferedImage(tempTileImage, 5);
					
					
					if (tileImage == null) {
						
						tileImage = new BufferedImage(tempTileImage.getWidth(), tempTileImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
					}
					
					tileImage = mergeImages(tileImage, tempTileImage);
					
				}
				
				return (tileImage);
				
			} catch (Exception ex) {
				
				System.out.println("Error on creating total tile image ! ERROR: " + ex);
					
				BufferedImage resqueTileImage = new BufferedImage((int)tileDimension.getWidth(), (int)tileDimension.getHeight(), BufferedImage.TYPE_INT_ARGB);
				
				return resqueTileImage;
			}
		
		} else {
			
			System.out.println("Error in get tile parameters ! Be sure all parameters are true");
			return null;
		}
	}
	
	private BufferedImage getTileImageFromURL(String request) {

		try {
			URL url = new URL(request);

			InputStream ImageInputStream = url.openStream();
			
			BufferedImage tileImage = ImageIO.read(ImageInputStream);
			
			return (tileImage);

		} catch (Exception ex) {
			
			System.out.println("Error on Creating Image from kvp encoding. Error : " + ex );
			return null;
		} 
	}
	
	public URL getTileServerURL() {
		return tileServerURL;
	}

	public void setTileServerURL(URL tileServerURL) {
		this.tileServerURL = tileServerURL;
	}
	
	public BufferedImage  mergeImages(BufferedImage imageBase, BufferedImage imageNew) {
		
		try {
			BufferedImage resultImage = new BufferedImage(imageBase.getWidth(), imageBase.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
			Graphics2D resultGraph2 = resultImage.createGraphics();
			
			resultGraph2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            
			resultGraph2.drawImage(imageBase, 0, 0, null);
			
			resultGraph2.drawImage(imageNew, 0, 0, null);
			
			resultGraph2.dispose();
			
			return resultImage;
		} catch (Exception ex) {
			
			System.out.println("Error on merging images ! ERROR: " + ex);
			return null;
		}
	}
	
	public int TakeRandomFromInt(int maxLimit) {
		
		int randomIndex = 1;
		
		Random randomNumber = new Random();
		
		randomIndex = randomNumber.nextInt(maxLimit);
		
		return (randomIndex);
	}
	
}
