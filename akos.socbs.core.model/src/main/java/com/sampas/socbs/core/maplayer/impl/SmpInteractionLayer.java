package com.sampas.socbs.core.maplayer.impl;

import java.util.ArrayList;
import java.util.List;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.CoordinateSystemTransformers;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureCollection;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureCursor;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.maplayer.IInteractionLayer;
import com.sampas.socbs.core.processing.services.IFeatureCreator;
import com.sampas.socbs.core.processing.services.impl.SmpFeatureCreator;
import com.sampas.socbs.core.renderer.impl.RenderPhaseEnum;
import com.sampas.socbs.core.tools.IProcessContext;

public class SmpInteractionLayer extends SmpFeatureLayer implements IInteractionLayer {
	
	private List<IFeature> layerFeatures = new ArrayList<IFeature>();
	//List<IFeatureCollection> features = null;

	private ICoordinateSystemTransformers csTransformers = new CoordinateSystemTransformers();
	
	public SmpInteractionLayer(String layerName, List<IFeature> features) {
		
		this.setName(layerName);
		
		this.layerFeatures.addAll(features);		
		
	}
	
	public SmpInteractionLayer(String layerName, IFeatureCollection features) {
		
		if(layerName != null) {
			this.setName(layerName);
		} else {
			this.setName("DefaultInteractionLayer" + Math.random());
		}
		
		if(features != null && features.getFeaturesCount() != 0) {
			for (int i = 0; i < features.getFeaturesCount(); i++) {
				this.layerFeatures.add(features.getFeatureAt(i));		
			}
		}
		
	}
	
	public IFeatureCollection getFeatureCollection() {
		
		IFeatureCollection featureCollection = new SmpFeatureCollection();
		for (int i = 0; i < this.layerFeatures.size(); i++) {
			featureCollection.addFeature(this.layerFeatures.get(i));
		}
		return featureCollection;
	}
	
	public int getFeatureCount() {
		
		return (this.layerFeatures.size());
	}

	
	public String getFeatureTypeAt(int index) {

		return (((SmpFeature) this.layerFeatures.get(index)).getGeoToolFeature().getFeatureType().toString());
	}
	
	public void addInteractionFeature(IFeature feature) {
		if(feature != null)
		this.layerFeatures.add(feature);
	}
	
	public void removeInteractionFeature(IFeature feature) {
		if(feature != null)
		this.layerFeatures.remove(feature);
	}
	
	public String addPinToLayer(String featureType,String featureName,String decription, IPoint smpPoint) {
		
		if (smpPoint != null) {
			
			if (!smpPoint.getCoordinateSystem().getEPSGCode().equals(this.getCoordinateSystem().getEPSGCode())) {
				
				try {

					smpPoint = csTransformers.SmpGeometryCoordinateTransformer(smpPoint, smpPoint.getCoordinateSystem(), this.getCoordinateSystem());
				} catch (Exception ex) {
					
					System.out.println("Error on transforming coordinate system! ERROR: " + ex.getMessage());
					return null;
				}
			}
			IFeatureCreator featureCreater = new SmpFeatureCreator();

			IFeature newFeature = featureCreater.pointFeatureCreator(featureType, featureName,decription, smpPoint);
			
			this.layerFeatures.add(newFeature);
			
			IEnvelope envelope = new SmpBoundingRectangle(smpPoint.getX(),smpPoint.getY(),smpPoint.getX(),smpPoint.getY());
			
			setExtent(envelope);
			
			return (newFeature.getID());
		} else {
			System.out.println("Error on point input !");
			return null;
		}
	}
	
	public void render(RenderPhaseEnum renderPhase, IDisplay display,
			IProcessContext processContext) {

		//long time= System.currentTimeMillis();
		
		/* Interaction Layer has no data provider but it may include features*/
		if(this.layerFeatures != null){
		
			IFeatureCollection collection =  new SmpFeatureCollection();
			for (int i = 0; i < this.layerFeatures.size(); i++) {
				collection.addFeature(this.layerFeatures.get(i));
			}
			IFeatureCursor featureCursor = new SmpFeatureCursor(collection,getSmpLayerAttributes());				 

			/* Now only use one renderer*/

				if(getRendererCount()>0){
					getRenderer(0).render(featureCursor, renderPhase, display, processContext);
				}
				else{
					System.err.println("SMPFEATURELAYER______: Render List has no render");
				}
				
		}
	}

	public void removeInteractionFeature(String landmarkId) {
		
		for (int i = 0; i < this.layerFeatures.size(); i++) {
			
			if(this.layerFeatures.get(i).getID().equals(landmarkId)){
				this.layerFeatures.remove(i);
				break;
			}
		}
	}
	
	public void clearInteractionFeatureList(){
		
		this.layerFeatures.clear();
	}
}
