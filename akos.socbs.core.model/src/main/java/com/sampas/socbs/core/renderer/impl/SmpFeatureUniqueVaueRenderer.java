package com.sampas.socbs.core.renderer.impl;

import java.util.HashMap;

import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.mapview.ILegendEntityGroup;
import com.sampas.socbs.core.mapview.ILegendItem;
import com.sampas.socbs.core.renderer.IFeatureUniqueValueRenderer;
import com.sampas.socbs.core.symbology.ISymbol;
import com.sampas.socbs.core.tools.IProcessContext;

public class SmpFeatureUniqueVaueRenderer implements
		IFeatureUniqueValueRenderer {

	private String defaultLabel = "";
	private ISymbol defaultSymbol = null;
	private boolean useDefaultSymbol = false;
	private String valueColumnName = "";
	private HashMap<String, FeatureRendererUniqueValue> keyValue = new HashMap<String, FeatureRendererUniqueValue>();
	
	
	public boolean canRender(IFeatureLayer featureLayer, IDisplay display) {
		// TODO Auto-generated method stub
		// TODO determines whether feature layer can be drawn by this renderer.
		return true;
	}

	
	public boolean IsUseDefaultSymbol() {
		// TODO Auto-generated method stub
		return this.useDefaultSymbol;
	}

	
	public void addValue(String value, String heading, String label, ISymbol symbol) {
		// TODO Auto-generated method stub
		FeatureRendererUniqueValue fruv = new FeatureRendererUniqueValue(heading, label, symbol); 
		this.keyValue.put(value, fruv);
	}

	public String getDefaultLabel() {
		// TODO Auto-generated method stub
		return this.defaultLabel;
	}

	
	public ISymbol getDefaultSymbol() {
		// TODO Auto-generated method stub
		return this.defaultSymbol;
	}

	
	public String getValueColumnName() {
		// TODO Auto-generated method stub
		return this.valueColumnName;
	}

	
	public String getHeading(String value) {
		// TODO Auto-generated method stub
		return this.keyValue.get(value).getHeading();
	}

	
	public String getLabel(String value) {
		// TODO Auto-generated method stub
		return this.keyValue.get(value).getLabel();
	}

	
	public ILegendEntityGroup getLegendGroup(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ISymbol getSymbol(String value) {
		// TODO Auto-generated method stub
		return this.keyValue.get(value).getSymbol();
	}

	
	public ISymbol getSymbol(IFeature feature) {
		// TODO Auto-generated method stub
		
		// TODO 
		//Object value = feature.getAttribute(valueColumnName);
		
		Object value = "";
		
		return this.keyValue.get(value).getSymbol();
	}

	
	public String getValue(int index) {
		// TODO Auto-generated method stub
		// TODO
		return null;
	}

	
	public int getValueCount() {
		// TODO Auto-generated method stub
		return this.keyValue.size();
	}

	
	public void prepareFilter(IFeatureLayer featureLayer, IFilter filter) {
		// TODO Auto-generated method stub

	}

	
	public void removeValue(String value) {
		// TODO Auto-generated method stub
		this.keyValue.remove(value);
	}

	
	public void render(IFeatureCursor featureCursor,
			RenderPhaseEnum renderPhase, IDisplay display,
			IProcessContext processContext) {
		
		// Alternatif 1:
			// foreach (feature in featureCursor) {
			// 	  ISymbol symbol = this.getSymbol(feature);
		 	//    symbol.setDevice(display.getDevice(), display.getDisplayTransformation());
		 	//    symbol.draw(feature.getGeometry());
		    //    symbol.resetDevice();
			// }
		
		

		// Alternatif 2:
		// foreach (feature in featureCursor) {
		//    
		// 	  ISymbol symbol = this.getSymbol(feature);
		//    display.setSymbol(symbol);
		//    if (feature.GeometryType == point) {
		//        display.drawPoint(feature.Geometry);
		// 	  }
		// }

		
	}

	
	public void setDefaultLabel(String label) {
		// TODO Auto-generated method stub
		this.defaultLabel = label;
	}

	
	public void setDefaultSymbol(ISymbol symbol) {
		// TODO Auto-generated method stub
		this.defaultSymbol = symbol;
	}

	
	public void setValueColumnName(String fieldName) {
		// TODO Auto-generated method stub
		this.valueColumnName = fieldName;
	}


	
	public void setHeading(String value, String heading) {
		// TODO Auto-generated method stub
		this.keyValue.get(value).setHeading(heading);
	}

	
	public void setLabel(String value, String label) {
		// TODO Auto-generated method stub
		this.keyValue.get(value).setLabel(label);
	}

	
	public void setSymbol(String value, ISymbol symbol) {
		// TODO Auto-generated method stub
		this.keyValue.get(value).setSymbol(symbol);
	}

	
	public void setUseDefaultSymbol(boolean use) {
		// TODO Auto-generated method stub
		this.useDefaultSymbol = use;
	}

	
	public void setValue(int index, String value) {
		// TODO Auto-generated method stub

	}

	
	public int getLegendGroupCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public ILegendItem getLegendItem() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ILegendEntityGroup getILegendEntityGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setILegendEntityGroup(ILegendEntityGroup legendEntityGroup) {
		// TODO Auto-generated method stub

	}


	public ILegendEntityGroup getLegendGroup() {
		// TODO Auto-generated method stub
		return null;
	}

//	
//	public void setRenderer(GTRenderer renderer) {
//		// TODO Auto-generated method stub
//
//	}
//
//	
//	public void paint(IDisplay display, IEnvelope paintArea, IEnvelope mapArea) {
//		// TODO Auto-generated method stub
//
//	}
//
//	
//	public void setContext(MapContext mapContext) {
//		// TODO Auto-generated method stub
//
//	}
//	
//	
//	public IMapContext getContext() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
