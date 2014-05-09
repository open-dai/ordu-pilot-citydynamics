package com.sampas.socbs.core.maplayer;

import java.util.List;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.dataset.feature.IFeatureID;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.renderer.IRenderer;

/**
 * Provides access to the properties and methods of a layer based on vector
 * geographic data, which is typically a geodatabase, shapefile, or coverage
 * feature class.
 * Remarks
 * 
 * IGeoFeatureLayer provides access to all properties and methods of IFeatureLayer
 * plus additional properties to access a layer's feature renderer and labeling
 * properties. Fewer layer types support IGeoFeatureLayer compared to
 * IFeatureLayer.
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface IFeatureLayer extends ILayer {
	
	public List<ILayerAttribute> getSmpLayerAttributes();
	
	public void addLayerAttribute(ILayerAttribute layerAttribute);
	
	public void addRenderer(int index,IRenderer renderer);
	
	public void removeLayerAttribute(int index);
	
	public void clearAllAttribute();
	
	public ILayerAttribute getAttributeAt(int index);
	
	public ILayerAttribute getAttributeAt(String columnName);
	
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
	
	public IFeature getFeaturesbyFID(IFeatureID featureID);
	

}