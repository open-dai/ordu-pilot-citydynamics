package com.sampas.socbs.core.renderer;

import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.renderer.impl.RenderPhaseEnum;
import com.sampas.socbs.core.tools.IProcessContext;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:45
 */
public interface IRenderer {

	public void render(IFeatureCursor featureCursor, RenderPhaseEnum renderPhase,
			IDisplay display, IProcessContext processContext);

	

}