package com.sampas.socbs.core.selection.impl;

import java.awt.Color;

import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureCursor;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.renderer.IFeatureSimpleRenderer;
import com.sampas.socbs.core.renderer.IRenderer;
import com.sampas.socbs.core.renderer.impl.SmpFeatureSimpleRenderer;
import com.sampas.socbs.core.selection.ISelection;
import com.sampas.socbs.core.symbology.ILineSymbol;
import com.sampas.socbs.core.symbology.IPolygonSymbol;
import com.sampas.socbs.core.symbology.impl.SmpLineSymbol;
import com.sampas.socbs.core.symbology.impl.SmpPolygonSymbol;
import com.sampas.socbs.core.tools.IColor;
import com.sampas.socbs.core.tools.ITrackCancel;
import com.sampas.socbs.core.tools.impl.SmpRGBColor;

public class SmpSelection implements ISelection {

	private boolean clear= false;
	private boolean copy= false;
	private boolean cut= false;
	private boolean paste= false;
	private boolean selectAll= false;
	private IFeatureCollection featureCollection;
	private IRenderer selectedRender;
	
	public Boolean canClear() {

		return this.clear;
	}

	
	public Boolean canCopy() {

		return this.copy;
	}

	
	public Boolean canCut() {

		return this.cut;
	}

	
	public Boolean canPaste() {

		return this.paste;
	}

	
	public Boolean canSelectAll() {

		return this.selectAll;
	}

	
	public void clear() {
		// TODO Auto-generated method stub

	}

	
	public void copy() {
		// TODO Auto-generated method stub

	}

	
	public void cut() {
		// TODO Auto-generated method stub

	}

	
	public void draw(IDisplay display, ITrackCancel cancelTracker) {
		
		if(this.selectedRender == null){
			defaultRenderer();
		}
		
		IFeatureCursor featureCursor = new SmpFeatureCursor(this.featureCollection,null);				 

		this.selectedRender.render(featureCursor, null, display, null);
		
	}

	
	public void paste() {
		// TODO Auto-generated method stub

	}

	
	public void selectAll() {
		// TODO Auto-generated method stub

	}


	public void setFeatureCollection(IFeatureCollection featureCollection) {
		this.featureCollection = featureCollection;
	}


	public IFeatureCollection getFeatureCollection() {
		return featureCollection;
	}


	public void setSelectedRender(IRenderer selectedRender) {
		this.selectedRender = selectedRender;
	}


	public IRenderer getSelectedRender() {
		return selectedRender;
	}
	private void defaultRenderer(){
		
		/* Setting Renderer */
		ILineSymbol lineSymbol = new SmpLineSymbol();		
		IColor color = new SmpRGBColor(Color.GREEN);			
		lineSymbol.setColor(color);
		lineSymbol.setWidth(3.0f);
//		float[] dashPhase ={5.0f,5.0f};
//		lineSymbol.setDashline(dashPhase);
	
		IPolygonSymbol polygonSymbol = new SmpPolygonSymbol();
		IColor color2 = new SmpRGBColor(Color.LIGHT_GRAY);
		polygonSymbol.setColor(color2);
		polygonSymbol.setOutline(lineSymbol);
		
		IFeatureSimpleRenderer featureSimpleRenderer = new SmpFeatureSimpleRenderer();
		featureSimpleRenderer.setSymbol(polygonSymbol);
		
		this.selectedRender = featureSimpleRenderer;
	}

}
