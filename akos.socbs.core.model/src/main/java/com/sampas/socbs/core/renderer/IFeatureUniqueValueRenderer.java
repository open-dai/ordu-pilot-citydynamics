package com.sampas.socbs.core.renderer;

import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.mapview.ILegendEntityGroup;
import com.sampas.socbs.core.renderer.impl.RenderPhaseEnum;
import com.sampas.socbs.core.symbology.ISymbol;
import com.sampas.socbs.core.tools.IProcessContext;


/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface IFeatureUniqueValueRenderer extends IFeatureRenderer {

	/**
	 * 
	 * @param value
	 * @param heading
	 * @param symbol
	 */
	public void addValue(String value, String heading, String label, ISymbol symbol);

	public String getDefaultLabel();

	public ISymbol getDefaultSymbol();

	/**
	 * 
	 * @param index
	 */
	public String getValueColumnName();

	/**
	 * 
	 * @param value
	 */
	public String getHeading(String value);

	/**
	 * 
	 * @param value
	 */
	public String getLabel(String value);

	/**
	 * 
	 * @param index
	 */
	public ILegendEntityGroup getLegendGroup(int index);


	/**
	 * 
	 * @param value
	 */
	public ISymbol getSymbol(String value);

	/**
	 * 
	 * @param feature
	 */
	public ISymbol getSymbol(IFeature feature);

	/**
	 * 
	 * @param index
	 */
	public String getValue(int index);

	public int getValueCount();

	/**
	 * 
	 * @param featureLayer
	 * @param display
	 */
	public boolean canRender(IFeatureLayer featureLayer, IDisplay display);

	public boolean IsUseDefaultSymbol();

	/**
	 * 
	 * @param featureLayer
	 * @param filter
	 */
	public void prepareFilter(IFeatureLayer featureLayer, IFilter filter);

	/**
	 * 
	 * @param value
	 */
	public void removeValue(String value);

	/**
	 * 
	 * @param featureCursor
	 * @param renderPhase
	 * @param display
	 * @param processContext
	 */
	public void render(IFeatureCursor featureCursor, RenderPhaseEnum renderPhase, IDisplay display, IProcessContext processContext);

	/**
	 * 
	 * @param label
	 */
	public void setDefaultLabel(String label);

	/**
	 * 
	 * @param symbol
	 */
	public void setDefaultSymbol(ISymbol symbol);

	/**
	 * 
	 * @param fieldIndex
	 * @param fieldName
	 */
	public void setValueColumnName(String fieldName);

	/**
	 * 
	 * @param value
	 * @param heading
	 */
	public void setHeading(String value, String heading);

	/**
	 * 
	 * @param value
	 * @param label
	 */
	public void setLabel(String value, String label);

	/**
	 * 
	 * @param value
	 * @param symbol
	 */
	public void setSymbol(String value, ISymbol symbol);

	/**
	 * 
	 * @param use
	 */
	public void setUseDefaultSymbol(boolean use);

	/**
	 * 
	 * @param index
	 * @param value
	 */
	public void setValue(int index, String value);

}