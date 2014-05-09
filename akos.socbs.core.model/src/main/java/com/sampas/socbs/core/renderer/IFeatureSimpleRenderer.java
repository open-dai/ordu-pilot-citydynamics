package com.sampas.socbs.core.renderer;

import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.renderer.impl.RenderPhaseEnum;
import com.sampas.socbs.core.symbology.ISymbol;
import com.sampas.socbs.core.tools.IProcessContext;


/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface IFeatureSimpleRenderer extends IFeatureRenderer {

	public String getDescription();

	public String getLabel();

	/**
	 * 
	 * @param index
	 */


	public ISymbol getSymbol();

	/**
	 * 
	 * @param feature
	 */
	public ISymbol getSymbolOfFeature(IFeature feature);

	/**
	 * 
	 * @param featureLayer
	 * @param display
	 */
	public boolean canRender(IFeatureLayer featureLayer, IDisplay display);

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
	 * @param description
	 */
	public void setDescription(String description);

	/**
	 * 
	 * @param label
	 */
	public void setLabel(String label);

	/**
	 * 
	 * @param symbol
	 */
	public void setSymbol(ISymbol symbol);

}