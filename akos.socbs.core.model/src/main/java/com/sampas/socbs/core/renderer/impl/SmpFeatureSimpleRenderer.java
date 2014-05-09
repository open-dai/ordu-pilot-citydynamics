package com.sampas.socbs.core.renderer.impl;

import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.mapview.ILegendEntityGroup;
import com.sampas.socbs.core.mapview.ILegendItem;
import com.sampas.socbs.core.renderer.IFeatureSimpleRenderer;
import com.sampas.socbs.core.symbology.ISymbol;
import com.sampas.socbs.core.tools.IProcessContext;


public class SmpFeatureSimpleRenderer implements IFeatureSimpleRenderer {

	private String description;
	private String label;
	private ILegendEntityGroup legendEntityGroup = null;
	private ISymbol symbol;
	
	public SmpFeatureSimpleRenderer(){
		
	}

	public boolean canRender(IFeatureLayer featureLayer, IDisplay display) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getDescription() {

		return this.description;
	}

	public String getLabel() {

		return this.label;
	}

	public ILegendEntityGroup getLegendGroup() {

		return this.legendEntityGroup;
	}

	public ISymbol getSymbol() {

		return this.symbol;
	}

	public ISymbol getSymbolOfFeature(IFeature feature) {

		return legendEntityGroup.getLegendEntity(feature.getID()).getSymbol();
	}

	public void prepareFilter(IFeatureLayer featureLayer, IFilter filter) {
		// TODO Auto-generated method stub

	}

	public void render(IFeatureCursor featureCursor,
			RenderPhaseEnum renderPhase, IDisplay display,
			IProcessContext processContext) {
		
		// TODO addLegendEntity to Legend
		
		// TODO All Layers are in map coo system
		
		// TODO Transform Coordinates Map2Screen in display
		
		display.setSymbol(this.symbol);
			
		for (int i = 0; i < featureCursor.getFeatureSize(); i++) {
			
			//System.out.println(i);
			
			if(featureCursor.getFeature().isGeometryEmpty()){
				
				// TODO: Collect feature id that has empty geometry 
				System.out.println(i);

			}
	
			else{
					//System.out.println(i+" "+featureCursor.getFeature().getDefaultGeometry().getGeometryType());
					// featureCursor.getFeature().getAttribute("GEOMETRY")
					if(featureCursor.getFeature().getDefaultGeometry().getGeometryType().equals("LineString") ){
						
						display.drawLine(featureCursor.getFeature().getDefaultGeometry().getCoordinates());
										
						}
		
					else if( featureCursor.getFeature().getDefaultGeometry().getGeometryType().equals("MultiLineString") ){
								
						for(int j=0;j<featureCursor.getFeature().getDefaultGeometry().getNumGeometries();j++){
		
							display.drawLine(featureCursor.getFeature().getDefaultGeometry().getCoordinates());	
						}
									
					}
					
					else if( featureCursor.getFeature().getDefaultGeometry().getGeometryType().toString().equals("Point") ){
												
						display.drawPoint(featureCursor.getFeature().getDefaultGeometry().getCoordinates());
						
					}
					
					else if( featureCursor.getFeature().getDefaultGeometry().getGeometryType().toString().equals("Polygon") ){
		
						display.drawPolygon(featureCursor.getFeature().getDefaultGeometry().getCoordinates());
						
					}
					else if( featureCursor.getFeature().getFeatureType().toString().equals("MultiPolygon") ){
		
						for(int j=0;j<featureCursor.getFeature().getDefaultGeometry().getNumGeometries();j++){
		
							display.drawPolygon(featureCursor.getFeature().getDefaultGeometry().getCoordinates());	
						}
										
					}
			}
			featureCursor.nextFeature();
		}
	
			System.out.println("Finish a layer draw");
	}

	public void setDescription(String description) {
		
		this.description = description;
	}

	public void setLabel(String label) {

		this.label = label;

	}

	public void setSymbol(ISymbol symbol) {
	
		this.symbol = symbol;

	}

	public int getLegendGroupCount() {
		
		return this.legendEntityGroup.getLegendEntityGroupSize();
	}

	public ILegendItem getLegendItem() {
		// TODO Auto-generated method stub
		return null;
	}


}
