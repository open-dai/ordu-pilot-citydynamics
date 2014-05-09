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
import com.sampas.socbs.core.symbology.IChartSymbol;
import com.sampas.socbs.core.tools.IProcessContext;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface IFeatureChartRenderer extends IFeatureRenderer, IChartRenderer, IDataNormalization, IRendererFields {

	/**
	 * 
	 * @param fieldName
	 * @param fieldAlias
	 */
	public void addField(String fieldName, String fieldAlias);

	/**
	 * 
	 * @param featureLayer
	 * @param display
	 */
	public boolean canRender(IFeatureLayer featureLayer, IDisplay display);

	public IChartSymbol getChartSymbol();

	/**
	 * 
	 * @param index
	 */
	public String getField(int index);

	/**
	 * 
	 * @param index
	 */
	public String getFieldAlias(int index);

	public int getFieldCount();

	/**
	 * 
	 * @param index
	 */
	public double getFieldTotal(int index);

	public String getLabel();

	/**
	 * 
	 * @param index
	 */
	public ILegendEntityGroup getLegendGroup(int index);

	public String getNormalizationField();

	public String getNormalizationFieldAlias();

	public double getNormalizationTotal();

	public DataNormalizationTypesEnum getNormalizationType();

	/**
	 * 
	 * @param feature
	 */
	public void getSymbolOfFeature(IFeature feature);

	/**
	 * 
	 * @param featureLayer
	 * @param filter
	 */
	public void prepareFilter(IFeatureLayer featureLayer, IFilter filter);

	/**
	 * 
	 * @param fieldName
	 */
	public void removeField(String fieldName);

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
	 * @param symbol
	 */
	public void setChartSymbol(IChartSymbol symbol);

	/**
	 * 
	 * @param index
	 * @param fieldName
	 */
	public void setField(int index, String fieldName);

	/**
	 * 
	 * @param index
	 * @param fieldAlias
	 */
	public void setFieldAlias(int index, String fieldAlias);

	/**
	 * 
	 * @param index
	 * @param total
	 */
	public void setFieldTotal(int index, double total);

	/**
	 * 
	 * @param label
	 */
	public void setLabel(String label);

	/**
	 * 
	 * @param fieldName
	 */
	public void setNormalizationField(String fieldName);

	/**
	 * 
	 * @param alias
	 */
	public void setNormalizationFieldAlias(String alias);

	/**
	 * 
	 * @param total
	 */
	public void setNormalizationTotal(double total);

	/**
	 * 
	 * @param type
	 */
	public void setNormalizationType(DataNormalizationTypesEnum type);

}