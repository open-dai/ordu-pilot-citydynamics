package com.sampas.socbs.core.tiler.tool.impl;

import java.util.List;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.CoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.ITileDataStore;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.ITileLayer;
import com.sampas.socbs.core.maplayer.impl.SmpLayerNames;
import com.sampas.socbs.core.tile.toolbox.BBOX;
import com.sampas.socbs.core.tile.toolbox.MimeType;

public class MapXmlGenerator {
	
	private TilingRequirements tilingRequirements = null;
	private String errorStr = "";
	private String infoStr = "";
	private IFeatureLayer impPlcLayer = null;
	private ITileLayer tileLayer = null;

	
	
	public MapXmlGenerator(TilingRequirements tilingRequirements) {
		
		this.tilingRequirements = tilingRequirements;
	}

	public void setUpMapXmlFileRequirements() {
		
		try {
			
			this.impPlcLayer = getRelatedImpPlcLayer();
			
			this.tileLayer = getRelatedTileLayer();
			
		} catch (Exception ex) {
			
			this.errorStr = "Error on setup mapXmlGenerator prerequirements ! ERROR : " + ex;
		}
	}
	
	private ITileLayer getRelatedTileLayer() {
		
		try {
			
			ITileDataStore tileDataStore = this.tilingRequirements.getTileDataStore();
			
			ILayerNames layerNames = new SmpLayerNames();
			
			layerNames.addLayerName(this.tilingRequirements.getDsNameSpace() + ":" + this.tilingRequirements.getTileLayerName());
			
			List<ITileLayer> tileLayers = tileDataStore.getLayerDefinitions(layerNames);
			
			if (tileLayers.size() <= 0) {
				
				this.infoStr = "Error on finding tile layer name with : " + this.tilingRequirements.getTileLayerName();
				
				return null;
			} 
			
			ITileLayer tileLayer = tileLayers.get(0);
			
			return tileLayer;
			
		} catch (Exception ex) {

			this.errorStr = "Error on setting tile layer ! ERROR : " + ex;
			
			return null;
		}
	}
	
	private IFeatureLayer getRelatedImpPlcLayer() {
		
		try {
			
			IFeatureDataStore featureDataStore = this.tilingRequirements.getDBDataStore();
			
			IFeatureLayerProvider layerProvider = new SmpFeatureLayerProviderSrv();
			
			ILayerNames layerNames = new SmpLayerNames();
			
			layerNames.addLayerName(this.tilingRequirements.getImpPlcDSLayerName());
			
			List<IFeatureLayer> featureLayers = layerProvider.getLayers(featureDataStore, layerNames);
			
			if (featureLayers.size() <= 0) {
				
				this.infoStr = "Error on finding feature layer name with : " + this.tilingRequirements.getTileLayerName();
				
				return null;
			}
			
			IFeatureLayer impPlcLayer = featureLayers.get(0);
			
			return impPlcLayer;
			
		} catch (Exception ex) {
			
			this.errorStr = "Error on setting important place feature layer ! ERROR : " + ex;
			
			return null;
		}
	}
	
	public String[] getTileProps(IFeature feature, int zoomLevel) {
		
		String[] tileProps = new String[3];
		
		try {
			
			if (this.impPlcLayer == null) {
				
				this.impPlcLayer = this.getImpPlcLayer();
			}
			
			double[] featureBounds = new double[4];
			featureBounds[0] = feature.getDefaultGeometry().getCoordinate().getX();
			featureBounds[1] = feature.getDefaultGeometry().getCoordinate().getY();
			featureBounds[2] = feature.getDefaultGeometry().getCoordinate().getX();
			featureBounds[3] = feature.getDefaultGeometry().getCoordinate().getY();
			
			BBOX correctedBbox = getRelatedExtend(featureBounds);
			
			int[] tileBounds = this.tilingRequirements.getTileDataStore().getTileBoundsFromBBox(tileLayer, tilingRequirements.getDefaultSRS(), correctedBbox, zoomLevel);
			
			FilePathGenerator filePathGenerator =  new FilePathGenerator();
			
			String imgTypeStr = tilingRequirements.getTileImgFileType().name();
			
			MimeType mimeType =  new MimeType(imgTypeStr, imgTypeStr, imgTypeStr, imgTypeStr, true); 
			
			long[] tile = new long[3];
			tile[0] = tileBounds[0];
			tile[1] = tileBounds[1];
			tile[2] = zoomLevel;
			
			String[] resultSet = filePathGenerator.tilePath("", tilingRequirements.getDsNameSpace() + ":" + tilingRequirements.getTileLayerName(), tile, tilingRequirements.getDefaultSRS().getNumber(), mimeType, -1);
			
			if (resultSet == null) {
				
				return null;
			}
			tileProps[0] = resultSet[0] + "\\" + resultSet[1];
			
			BBOX tileBbox = this.tileLayer.getBboxForGridLoc(this.tilingRequirements.getDefaultSRS(), tileBounds, zoomLevel);
			
			double lenghtX = Math.abs(tileBbox.coords[2] - tileBbox.coords[0]);
			double lenghtY = Math.abs(tileBbox.coords[3] - tileBbox.coords[1]);
			
			double offsetX = lenghtX / this.tilingRequirements.getTileDimention();
			double offsetY = lenghtY / this.tilingRequirements.getTileDimention();
			
			double distanceX = correctedBbox.coords[0] - tileBbox.coords[0];
			double distanceY = correctedBbox.coords[1] - tileBbox.coords[1];
			
			double pixLocX = distanceX / offsetX;
			double pixLocY = distanceY / offsetY;
			
			tileProps[1] = String.valueOf((int)pixLocX);
			tileProps[2] = String.valueOf((int)pixLocY);
			
			return tileProps;
			
		} catch (Exception ex) {

			System.out.println("Error on finding important place's location information ! ERROR : " + ex);
			
			return null;
		} 
	}
	
	public String[][] getLevelRowColumns(int zoomLevel) {
		
		try {
		
			String[][] fullResultSet = null;
			
			if (this.impPlcLayer == null) {
				
				this.impPlcLayer = this.getImpPlcLayer();
			}
			
			//is there a problem ???
			double[] layerBounds = new double[4];
			layerBounds[0] = this.impPlcLayer.getExtent().getMinY();
			layerBounds[1] = this.impPlcLayer.getExtent().getMinX();
			layerBounds[2] = this.impPlcLayer.getExtent().getMaxY();
			layerBounds[3] = this.impPlcLayer.getExtent().getMaxX();		
			
			int[] tileBounds = this.tilingRequirements.getTileDataStore().getTileBoundsFromBBox(tileLayer, tilingRequirements.getDefaultSRS(), getRelatedExtend(layerBounds), zoomLevel);
			
			FilePathGenerator filePathGenerator =  new FilePathGenerator();
			
			String imgTypeStr = tilingRequirements.getTileImgFileType().name();
			
			MimeType mimeType =  new MimeType(imgTypeStr, imgTypeStr, imgTypeStr, imgTypeStr, true); 
			
			long[] tile = new long[3];
			tile[0] = 0;
			tile[1] = 0;
			tile[2] = zoomLevel;
			
			int axis = tileBounds[2]- tileBounds[0];
			int apsis = tileBounds[3]- tileBounds[1];
			
			fullResultSet = new String[axis + 1][apsis + 1];
			
			String[] resultSet = null;
			
			for (int rowCnt = 0; rowCnt <= axis; rowCnt++) {
				
				tile[0] = tileBounds[0] + rowCnt;
				
				for (int columnCnt = 0; columnCnt <= apsis ; columnCnt++) {
					
					tile[1] = tileBounds[1] + columnCnt;
					
					resultSet = filePathGenerator.tilePath("", tilingRequirements.getDsNameSpace() + ":" + tilingRequirements.getTileLayerName(), tile, tilingRequirements.getDefaultSRS().getNumber(), mimeType, -1);
					if (resultSet != null) {
						
						fullResultSet[rowCnt][columnCnt] = resultSet[0] + "\\" + resultSet[1];
					}
				}
			}
			
			return fullResultSet;
			
		}
		catch (Exception ex) {
			
			System.out.println("Error on creating row and column map ! ERROR : " + ex);
			return null;
		}
		
	}
	
	private BBOX getRelatedExtend(double[] relatedExtend) {
		
		try {
			
			ICoordinateSystem sourceCoordinateSystem = this.impPlcLayer.getCoordinateSystem();
			
			///////////////////////////////////////////////////////
			//TODO THIS WILL REMOVE WHEN REAL SPATIAL DB INITIALIZE
			if (sourceCoordinateSystem == null) {
				
				sourceCoordinateSystem = new SmpCoordinateSystem("EPSG:4326");
			}
			///////////////////////////////////////////////////////
			
			ICoordinateSystem targetCoordinateSystem = new SmpCoordinateSystem(this.tilingRequirements.getDefaultSRS().toString());
			
			BBOX layerExtend = null;
			
			if (!sourceCoordinateSystem.getEPSGCode().equals(targetCoordinateSystem.getEPSGCode())) {
				
				SmpBoundingRectangle smpBBox = new SmpBoundingRectangle(relatedExtend[0], relatedExtend[1], relatedExtend[2], relatedExtend[3]);
				
				ICoordinateSystemTransformers coordinateSystemTransformers = new CoordinateSystemTransformers();
				
				IEnvelope transformedBBox = coordinateSystemTransformers.SmpBoundingRectangleCoordinateTransformer(smpBBox, sourceCoordinateSystem, targetCoordinateSystem);
				
				layerExtend = new BBOX(transformedBBox.getMinX(), transformedBBox.getMinY(), transformedBBox.getMaxX(), transformedBBox.getMaxY());
			} else {
				
				layerExtend = new BBOX(relatedExtend[0], relatedExtend[1], relatedExtend[2], relatedExtend[3]);
			}
			
			return layerExtend;
			
		} catch (Exception ex) {

			System.out.println("Error on calculating layer Extend ! ERROR : " + ex);
			return null;
		}
	}
	
	
	
	//TODO Getters and Setters Location Separator
	
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

	public IFeatureLayer getImpPlcLayer() {
		return impPlcLayer;
	}

	public void setImpPlcLayer(IFeatureLayer impPlcLayer) {
		this.impPlcLayer = impPlcLayer;
	}

	public ITileLayer getTileLayer() {
		return tileLayer;
	}

	public void setTileLayer(ITileLayer tileLayer) {
		this.tileLayer = tileLayer;
	}

}
