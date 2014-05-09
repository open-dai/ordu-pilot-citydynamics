package com.sampas.socbs.core.renderer;

import com.sampas.socbs.core.dataset.feature.IDataNormalization;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.dataset.feature.impl.DataNormalizationTypesEnum;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.mapview.ILegendEntityGroup;
import com.sampas.socbs.core.renderer.impl.RenderPhaseEnum;
import com.sampas.socbs.core.symbology.IFillSymbol;
import com.sampas.socbs.core.symbology.ISymbol;
import com.sampas.socbs.core.tools.IProcessContext;


/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface IFeatureDiscreteIntervalRenderer extends IFeatureRenderer, IDataNormalization {

	public IFillSymbol getBackgroundSymbol();

	/**
	 * 
	 * @param index
	 */
	public void getBreak(int index);

	public int getBreakCount();

	public String getField();

	/**
	 * 
	 * @param index
	 */
	public String getLabel(int index);

	/**
	 * 
	 * @param index
	 */
	public ILegendEntityGroup getLegendGroup(int index);

	public double getMinimumBreak();

	public String getNormalizationField();

	public String getNormalizationFieldAlias();

	public double getNormalizationTotal();

	public DataNormalizationTypesEnum getNormalizationType();

	/**
	 * 
	 * @param index
	 */
	public ISymbol getSymbol(int index);

	/**
	 * 
	 * @param feature
	 */
	public void getSymbolOfFeature(IFeature feature);

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
	 * @param index
	 * @param value
	 */
	public void setBreak(int index, double value);

	/**
	 * 
	 * @param count
	 */
	public void setBreakCount(int count);

	/**
	 * 
	 * @param index
	 * @param description
	 */
	public void setDescription(int index, String description);

	/**
	 * 
	 * @param fieldName
	 */
	public void setField(String fieldName);

	public void setLabel(int index, String label);

	public void setMinimumBreak(double value);

	public void setNormalizationField(String fieldName);

	public void setNormalizationFieldAlias(String alias);

	public void setNormalizationTotal(double total);

	public void setNormalizationType(DataNormalizationTypesEnum type);

	/**
	 * 
	 * @param index
	 * @param symbol
	 */
	public void setSymbol(int index, ISymbol symbol);

}