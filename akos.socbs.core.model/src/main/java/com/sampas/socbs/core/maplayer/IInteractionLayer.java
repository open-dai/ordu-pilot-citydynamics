package com.sampas.socbs.core.maplayer;

import java.util.List;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.dataset.feature.IFeatureID;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IPoint;

public interface IInteractionLayer extends ILayer {
	
	public List<ILayerAttribute> getSmpLayerAttributes();
	
	public void addLayerAttribute(ILayerAttribute layerAttribute);
	
	public void removeLayerAttribute(int index);
	
	public void clearAllAttribute();
	
	public ILayerAttribute getAttributeAt(int index);
	
	public int getAttributeCount();
	
	public void setSmpFeatureCollection(IFeatureCollection smpFeatureCollection);
	
	public IFeatureCollection getFeatureCollection();
	
	public int getFeatureCount();
	
	public String getFeatureTypeAt(int index);
		
	/**
	 * Indicates if layer is selectable.
	 */
	public boolean isSelectable();

	/**
	 * Indicates if symbols are scaled for the layer.
	 */
	public boolean isSymbolsScaled();

	/**
	 * Creates a cursor based upon the search criteria.
	 * 
	 * @param filter
	 */
	public IFeatureCursor search(IFilter filter);

	/**
	 * Indicates if layer is selectable.
	 * 
	 * @param selectable
	 */
	public void setSelectable(boolean selectable);

	/**
	 * 
	 * @param isSymbolsScaled
	 */
	public void setSymbolsScaled(boolean isSymbolsScaled);
	
	public void setDataProvider(IFeatureDataStore dataStore);	

	public IFeatureCollection getFeaturesByBRectAndFilter(IEnvelope smpBbox, IFilter smpFilter);
	
	public IFeatureCollection getFeaturesbyFIDs(List<IFeatureID> featureIDs);
	
	
	//Additional Interaction layer functions
	public void addInteractionFeature(IFeature feature);
	
	public void removeInteractionFeature(IFeature feature);
	
	public String addPinToLayer(String featureType,String featureName,String description, IPoint smpPoint);
	
	public void clearInteractionFeatureList();

}