package com.sampas.socbs.core.maplayer;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.mapview.IMapItem;
import com.sampas.socbs.core.selection.ISelection;
import com.sampas.socbs.core.selection.ISelectionParameters;
import com.sampas.socbs.core.tools.impl.UnitTypesEnum;


/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:43
 */
public interface IMapBase {

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

	public void clearSelection();

	/**
	 * 
	 * @param firstPoint
	 * @param secondPoint
	 */
	public double computeDistance(IPoint firstPoint, IPoint secondPoint);

	public ICoordinateSystem getCoordinateSystem();

	public String getDescription();

	public UnitTypesEnum getDistanceUnits();

	public ISelection getFeatureSelection();

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

	public double getMapScale();

	public UnitTypesEnum getMapUnits();

	public String getName();

	public double getReferenceScale();

	public IEnvelope getViewExtent();

	/**
	 * 
	 * @param index
	 * @param layer
	 */
	public void insertLayer(int index, ILayer layer);

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
	 * @param extent
	 */
	public void setViewExtent(IEnvelope extent);

}