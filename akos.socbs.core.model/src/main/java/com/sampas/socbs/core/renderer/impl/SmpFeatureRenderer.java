package com.sampas.socbs.core.renderer.impl;

import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.mapview.ILegendEntityGroup;
import com.sampas.socbs.core.mapview.ILegendItem;
import com.sampas.socbs.core.renderer.IFeatureRenderer;
import com.sampas.socbs.core.tools.IProcessContext;

public class SmpFeatureRenderer implements IFeatureRenderer {

	public boolean canRender(IFeatureLayer featureLayer, IDisplay display) {
		// TODO Auto-generated method stub
		return false;
	}

	public void prepareFilter(IFeatureLayer featureLayer, IFilter filter) {
		// TODO Auto-generated method stub

	}

	public void render(IFeatureCursor featureCursor,
			RenderPhaseEnum renderPhase, IDisplay display,
			IProcessContext processContext) {
		// TODO Auto-generated method stub

	}

	public ILegendEntityGroup getLegendGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLegendGroupCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ILegendItem getLegendItem() {
		// TODO Auto-generated method stub
		return null;
	}

}
