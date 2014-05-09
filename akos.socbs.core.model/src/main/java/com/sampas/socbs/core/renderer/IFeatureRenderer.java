package com.sampas.socbs.core.renderer;

import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.mapview.ILegendReport;
import com.sampas.socbs.core.renderer.impl.RenderPhaseEnum;
import com.sampas.socbs.core.tools.IProcessContext;


/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface IFeatureRenderer extends ILegendReport, IRenderer {

	/**
	 * 
	 * @param index
	 */



	/**
	 * Indicates if the specified feature class can be rendered on the given display.
	 * 
	 * @param featureLayer
	 * @param display
	 */
	public boolean canRender(IFeatureLayer featureLayer, IDisplay display);

	/**
	 * Prepares the query filter for the rendering process.
	 * 
	 * @param featureLayer
	 * @param filter
	 */
	public void prepareFilter(IFeatureLayer featureLayer, IFilter filter);

	/**
	 * Draws features from the specified cursor on the given display.
	 * 
	 * @param featureCursor
	 * @param renderPhase
	 * @param display
	 * @param processContext
	 */
	public void render(IFeatureCursor featureCursor, RenderPhaseEnum renderPhase, IDisplay display, IProcessContext processContext);

}