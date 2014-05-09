package com.sampas.socbs.core.renderer;

import com.sampas.socbs.core.dataset.raster.IRaster;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.renderer.impl.RenderPhaseEnum;
import com.sampas.socbs.core.tools.IProcessContext;


/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:44
 */
public interface IRasterContinuousIntervalRenderer extends IRasterRenderer {

	/**
	 * 
	 * @param raster
	 */
	public boolean IsCanRender(IRaster raster);

	/**
	 * 
	 * @param raster
	 * @param renderPhase
	 * @param display
	 * @param processContext
	 * @param Update
	 */
	public void render(IRaster raster, RenderPhaseEnum renderPhase, IDisplay display, IProcessContext processContext, boolean Update);

	public void update();

}