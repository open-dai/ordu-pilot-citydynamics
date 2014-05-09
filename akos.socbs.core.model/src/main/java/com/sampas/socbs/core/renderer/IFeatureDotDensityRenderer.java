package com.sampas.socbs.core.renderer;

import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.mapview.ILegendEntityGroup;
import com.sampas.socbs.core.renderer.impl.RenderPhaseEnum;
import com.sampas.socbs.core.tools.IProcessContext;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface IFeatureDotDensityRenderer extends IFeatureRenderer, IRendererFields {

	/**
	 * 
	 * @param fieldName
	 * @param fieldAlias
	 */
	public void addField(String fieldName, String fieldAlias);

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
	public ILegendEntityGroup getLegendGroup(int index);


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

}