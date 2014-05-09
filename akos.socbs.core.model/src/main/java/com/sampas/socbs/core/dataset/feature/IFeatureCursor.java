package com.sampas.socbs.core.dataset.feature;

import java.util.List;

import com.sampas.socbs.core.maplayer.ILayerAttribute;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface IFeatureCursor {
	
	/*
	 *  Delete the existing Feature in the database corresponding to the current position of the cursor.
	 */
	public void deleteFeature();	
	/*
	 * The index of the field with the specified name.
	 */	
	public int findField(String name);	
	/*
	 * The fields Collection for this cursor.
	 */	
	public List<ILayerAttribute> getFields();
	/*
	 * Insert a new Feature into the database using the property values in the input buffer.
	 */
	public Object insertFeature(IFeatureBuffer buffer);
	/*
	 * Advance the position of the cursor by one and return the Feature object at that position.
	 */
	public IFeature nextFeature();
	/*
	 * Update the existing Feature in the database corresponding to the current position of the cursor.
	 */	
	public void updateFeature(IFeature object);
	
	public IFeature getFeature();
	
	public int getFeatureSize();

}