package com.sampas.socbs.core.maplayer;

import java.util.List;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.display.IDisplayEventsDisplayFinishedEvent;
import com.sampas.socbs.core.display.IDisplayEventsDisplayStartedEvent;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.mapview.IActiveView;
import com.sampas.socbs.core.mapview.IControlHandle;
import com.sampas.socbs.core.mapview.IMapItem;
import com.sampas.socbs.core.mapview.IRefreshedEventListener;
import com.sampas.socbs.core.selection.ISelection;
import com.sampas.socbs.core.selection.ISelectionParameters;
import com.sampas.socbs.core.tools.IProcessContext;
import com.sampas.socbs.core.tools.impl.UnitTypesEnum;


/**
 * Use the IMap interface to display data from various data sources.
 * 
 * The IMap interface is a starting point for many of the tasks one does with a
 * Map. For example, use IMap to add, delete, and access map layers containing
 * data from various sources including feature layers and graphics layers;
 * associate map surround objects (legends, scale bars, etc) with the Map; access
 * the various properties of a Map including the area of interest, the current map
 * units, and the spatial reference; select features and access the Map's current
 * selection.
 * Remarks
 * 
 * The IMap interface is a starting point for many of the tasks one does with a
 * map. For example, you can use IMap to add, delete, and access map layers
 * containing data from various sources, including feature layers and graphics
 * layers; associate map item objects (legends, scale bars, and so on) with the
 * map; access the various properties of a map, including the area of interest,
 * the current map units, and the spatial reference; select features and access
 * the Map object's current selection.
 * 
 * The Map object, manages a collection of Layer objects. Each layer has a spatial
 * reference. A spatial reference defines is a precision and a coordinate system.
 * @version 1.0
 * @created 07-Kas-2008 13:35:43
 */
public interface IMap extends IMapBase, IActiveView {

	/*
	 * Size of Layers Array
	 */
	public int getLayersSize();
	/**
	 * 
	 * @param handle
	 */
	public void activate(IControlHandle handle);

	/**
	 * 
	 * @param layer
	 */
	public void addLayer(ILayer layer);

	/**
	 * 
	 * @param mapItem
	 */
	public void addMapItem(IMapItem mapItem);

	public void clear();

	public void clearSelection();

	/**
	 * 
	 * @param firstPoint
	 * @param secondPoint
	 */
	public double computeDistance(IPoint firstPoint, IPoint secondPoint);

	public void contentsChanged();

	public void deactivate();
	
	public void displayStarted(IDisplayEventsDisplayStartedEvent theEvent);
	
	public void displayFinished(IDisplayEventsDisplayFinishedEvent theEvent);

	public ICoordinateSystem getCoordinateSystem();

	public String getDescription();

	public UnitTypesEnum getDistanceUnits();

	public IEnvelope getExportFrame();

	public ISelection getFeatureSelection();

	public IMap getFocusMap();

	public IEnvelope getFullExtent();

	public boolean isMapActivated();

	/**
	 * 
	 * @param index
	 */
	public ILayer getLayer(int index);

	/**
	 * 
	 * @param index
	 */
	public IMapItem getMapItem(int index);
	
	public int getMapItemSize();

	public double getMapScale();

	public UnitTypesEnum getMapUnits();

	public String getName();

	public double getReferenceScale();

	public ISelection getSelection();

	public boolean isShowSelection();

	/**
	 * 
	 * @param point
	 */
	public String getTooltip(IPoint point);

	public IEnvelope getViewExtent();

	/**
	 * 
	 * @param index
	 * @param layer
	 */
	public void insertLayer(int index, ILayer layer);

	public boolean IsActive();

	/**
	 * 
	 * @param controlHandle
	 * @param dpi
	 * @param pixelBounds
	 * @param visibleBounds
	 * @param processContext
	 */
	public void output(IControlHandle controlHandle, int dpi, IEnvelope pixelBounds, IEnvelope visibleBounds, IProcessContext processContext);

	public void refresh();

	/**
	 * 
	 * @param index
	 */
	public void removeLayer(int index);

	/**
	 * 
	 * @param index
	 */
	public void removeMapItem(int index);

	/**
	 * 
	 * @param handle
	 * @param processContext
	 */
	public void render(IControlHandle handle, IProcessContext processContext);

	/**
	 * 
	 * @param shape
	 * @param selectionParameters
	 * @param maxSelectionCount
	 */
	public void selectByShape(IGeometry shape, ISelectionParameters selectionParameters, int maxSelectionCount);

	/**
	 * 
	 * @param layer
	 * @param feature
	 */
	public void selectFeature(ILayer layer, IFeature feature);

	/**
	 * 
	 * @param coordinateSystem
	 */
	public void setCoordinateSystem(ICoordinateSystem coordinateSystem);

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description);

	/**
	 * 
	 * @param units
	 */
	public void setDistanceUnits(UnitTypesEnum units);

	/**
	 * 
	 * @param selection
	 */
	public void setFeatureSelection(ISelection selection);

	/**
	 * 
	 * @param map
	 */
	public void setFocusMap(IMap map);

	/**
	 * 
	 * @param extent
	 */
	public void setFullExtent(IEnvelope extent);


	/**
	 * 
	 * @param scale
	 */
	public void setMapScale(double scale);

	/**
	 * 
	 * @param units
	 */
	public void setMapUnits(UnitTypesEnum units);

	/**
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * 
	 * @param scale
	 */
	public void setReferenceScale(double scale);

	/**
	 * 
	 * @param selection
	 */
	public void setSelection(ISelection selection);

	/**
	 * 
	 * @param show
	 */
	public void setShowSelection(boolean show);

	/**
	 * 
	 * @param extent
	 */
	public void setViewExtent(IEnvelope extent);
	
	public List<List<String>> getPointInfo(ICoordinate coordinateMin,long layerId,double offsetX,double offsetY);
	
	/*
	 * changes a layer's visibility which layer is a raster,feature or shape type, is in a groupLayer
	 * and reset the extent value
	 */
	public void changeLayerVisibility(long id);

	public void changeLayerVisibilityAll(boolean isEnable);
	
	public ILayer findLayer(long id);
	
	public IDisplay getDisplay();
	
	public void setDisplay(IDisplay display);
	
	public void addEventListener(IRefreshedEventListener listener);
	
	public void removeEventListener(IRefreshedEventListener listener);
	
	public void resetExtent();
	
}