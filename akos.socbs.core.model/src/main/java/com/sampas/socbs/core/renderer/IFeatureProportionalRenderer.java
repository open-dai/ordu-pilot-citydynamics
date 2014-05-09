package com.sampas.socbs.core.renderer;

import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.mapview.ILegendEntityGroup;
import com.sampas.socbs.core.renderer.impl.RenderPhaseEnum;
import com.sampas.socbs.core.symbology.IFillSymbol;
import com.sampas.socbs.core.symbology.ISymbol;
import com.sampas.socbs.core.tools.IProcessContext;
import com.sampas.socbs.core.tools.impl.UnitTypesEnum;
import com.sampas.socbs.core.tools.impl.ValueMeaningsEnum;


/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface IFeatureProportionalRenderer extends IFeatureRenderer {

	public IFillSymbol getBackgroundSymbol();

	/**
	 * 
	 * @param index
	 */

	public ILegendEntityGroup getLegendGroup(int index);

	public String getNormalizationFieldName();

	public ISymbol getNormSymbol();

	/**
	 * 
	 * @param feature
	 */
	public void getSymbolOfFeature(IFeature feature);

	public String getValueFieldName();

	public ValueMeaningsEnum getValueMeaning();

	public UnitTypesEnum getValueUnit();

	/**
	 * 
	 * @param featureLayer
	 * @param display
	 */
	public boolean IsCanRender(IFeatureLayer featureLayer, IDisplay display);

	/**
	 * 
	 * @param featureLayer
	 * @param filter
	 */
	public void prepareFilter(IFeatureLayer featureLayer, IFilter filter);

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
	 * @param fillSymbol
	 */
	public void setBackgroundSymbol(IFillSymbol fillSymbol);

	/**
	 * 
	 * @param value
	 */
	public void setMaxDataValue(double value);

	/**
	 * 
	 * @param value
	 */
	public void setMinDataValue(double value);

	/**
	 * 
	 * @param fieldame
	 */
	public void setNormalizationFieldName(String fieldame);

	/**
	 * 
	 * @param symbol
	 */
	public void setNormSymbol(ISymbol symbol);

	/**
	 * 
	 * @param fieldName
	 */
	public void setValueFieldName(String fieldName);

	/**
	 * 
	 * @param valueMeaning
	 */
	public void setValueMeaning(ValueMeaningsEnum valueMeaning);

	/**
	 * 
	 * @param unit
	 */
	public void setValueUnit(UnitTypesEnum unit);

}