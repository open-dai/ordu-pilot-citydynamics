package com.sampas.socbs.core.maplayer.impl;

import java.util.ArrayList;
import java.util.List;
import org.geotools.data.ows.Layer;
import org.geotools.feature.IllegalAttributeException;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.operation.TransformException;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.CoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureCollection;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.maplayer.ILayer;
import com.sampas.socbs.core.maplayer.ILayerAttribute;
import com.sampas.socbs.core.renderer.IRenderer;
import com.sampas.socbs.core.renderer.impl.RenderPhaseEnum;
import com.sampas.socbs.core.tools.IProcessContext;


@SuppressWarnings("unused")
public class SmpLayer implements ILayer {
	
	private List<ILayerAttribute> smpLayerAttributes = new ArrayList<ILayerAttribute>();
	private String geometryColumnName = "";
	private Layer geoToolsLayer	=	null;
	private List<SmpLayer> smpLayers = new ArrayList<SmpLayer>();
	private List<IRenderer> smpRenderer = new ArrayList<IRenderer>();
	private IEnvelope extent = null;
	private String name = "";
	private boolean visible = false;
	private int layerPriority = 0;
	private ICoordinateSystem coordinateSystem;
	private long id;
	private String userLayerName = "";
	/*layerTypes =  wms,wfs,shapefile,oracle */
	private String layerType;
	private ICoordinateSystemTransformers coordinateSystemTransformers = new CoordinateSystemTransformers();
	private List<ILayerAttribute> layerAttributesFromMetadata = new ArrayList<ILayerAttribute>();
	private String namespace;
	
	public SmpLayer() {

		this.geoToolsLayer = new Layer();
		
	}
	
	public SmpLayer(Layer geoToolsLayer) {

		this.geoToolsLayer = geoToolsLayer;

	}
	
	public Layer getGeoToolsLayer() {

		return (this.geoToolsLayer);

	}
	
	public SmpLayer(String title) {

		this.geoToolsLayer = new Layer(title);
		
	}
	
	public List<ILayerAttribute> getSmpLayerAttributes() {
		
		return  smpLayerAttributes;
	}
	
	public ILayerAttribute getSmpLayerAttribute(String name) {
		
		ILayerAttribute toReturn=null;
		for (int i = 0; i < smpLayerAttributes.size(); i++) {
			if (smpLayerAttributes.get(i).getAttributeName().toUpperCase()==name.toUpperCase()){
				toReturn=smpLayerAttributes.get(i);
				break;
			}
		}
		return toReturn;
	}
	
	public void setSmpLayerAttributes(List<ILayerAttribute> layerAttributes) {
		
		this.smpLayerAttributes = layerAttributes;
	}
	
	public void setGeometryColumnName(String geometryColumnName) {
		this.geometryColumnName = geometryColumnName;
	}

	public String getGeometryColumnName() {
		return geometryColumnName;
	}

	
	public void addLayer(ILayer layer){
		
		this.smpLayers.add((SmpLayer) layer);
		
		if(layer.isVisible()){
			
			
			IEnvelope layerExtent = layer.getExtent();
			// Group Layer Coordinate Reference System should be same with map
			if(! layer.getCoordinateSystem().getEPSGCode().equals(this.coordinateSystem.getEPSGCode())){
				
				layerExtent = transformEnvelope(layer.getExtent(),
						(SmpCoordinateSystem)layer.getCoordinateSystem(),
						(SmpCoordinateSystem)this.coordinateSystem);
								
			}
			setExtent(layerExtent);			
		}	
					
	}

	public void addRenderer(IRenderer renderer){
			
		System.out.println("GROUP LAYER ADDING RENDER ERROR");
		return;

	}

	public IEnvelope getExtent() {
		
		return (this.extent);
	}
	
    public void setExtent(IEnvelope envelope) {
        
        if (this.extent == null) {
        	
            this.extent = envelope;
            
        } else {
        
        	double minX = Math.min( envelope.getMinX() , getExtent().getMinX());
                                           
        	double minY = Math.min(envelope.getMinY() , getExtent().getMinY() );
                         
        	double maxX = Math.max(envelope.getMaxX() , getExtent().getMaxX() );
        
        	double maxY = Math.max(envelope.getMaxY() , getExtent().getMaxY() );
                         
        	this.extent = new SmpBoundingRectangle(minX, minY, maxX, maxY);
        }
        
    }
	
	public ILayer getLayerAt(int index) {
		
		ILayer smpLayer = new SmpLayer();
		smpLayer = this.smpLayers.get(index);
		
		return (smpLayer);
	}
	
	public int getLayerCount() {
		
		return (this.smpLayers.size());
	}
	
	public double getMaximumScale() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public double getMinimumScale() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getName() {

		return (this.name);
	}
	
	public IRenderer getRenderer(int index) {

		return null;
	}
	
	public int getRendererCount() {
		// TODO Auto-generated method stub
		return -1;
	}
	
	public String getTipText(IPoint point, double tolerance) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isVisible() {
		
		return (this.visible);
	}

	
	public void removeLayer(int index) {
		this.smpLayers.remove(index);
		
	}
	
	public void removeRenderer(int index) {
		// TODO Auto-generated method stub
		
	}
	
	public void render(RenderPhaseEnum renderPhase, IDisplay display,
			IProcessContext processContext) {
		
		System.out.println("SmpLayer RenderError -Cannot Rander A GroupLayer-" );
		return;
	}
	
	public void setRenderer(List<IRenderer> smpRenderer) {
		
		this.smpRenderer = smpRenderer;
		
	}

	public void setMaximumScale(double scale) {
		// TODO Auto-generated method stub
		
	}
	
	public void setMinimumScale(double scale) {
		// TODO Auto-generated method stub
		
	}
	
	public void setName(String name) {

		this.name = name;
	}
	
	public void setVisible(boolean visible) {

		this.visible = visible;		
	}
		
	public void setCoordinateSystem(ICoordinateSystem coordinateSystem) {
		
		this.coordinateSystem = coordinateSystem;
		
	}
	
	public ICoordinateSystem getCoordinateSystem() {
		
		return (this.coordinateSystem);
	}

	public List<List<String>> getPointInfo(ICoordinate  coordinate,
			ICoordinateSystem  coordinateSystem,IEnvelope visibleExtent,double offsetX,double offsetY) {
		// TODO Auto-generated method stub		
		return null;
	}

	public void changeLayerVisibility() {
		
		/* If Layer have children, reset their visibility& transform extends */
		
		setVisible(!isVisible());
		
		/*IF Layer is visible, add its extent*/
		if(isVisible()){
			
			// if group layer set its extent
			if(this.smpLayers.size() !=0){
				for (int i = 0; i <this.smpLayers.size(); i++) {
					this.smpLayers.get(i).setVisible(true);
					IEnvelope envelope = this.smpLayers.get(i).getExtent();
					/* if coo system is diff from layer transform and reset*/
					if(!this.smpLayers.get(i).getCoordinateSystem().getEPSGCode().equals(this.coordinateSystem)){
						
						envelope = transformEnvelope(this.smpLayers.get(i).getExtent(),
									(SmpCoordinateSystem)this.smpLayers.get(i).getCoordinateSystem(),
									(SmpCoordinateSystem)this.coordinateSystem);
												
					}
					setExtent(envelope);
				}
			}
			
		}
		
	}

	public void resetExtent() {
		
		this.extent=null;
		
		if(this.smpLayers.size() !=0){
			for (int i = 0; i < this.smpLayers.size(); i++) {				
				if(this.smpLayers.get(i).isVisible()){
					setExtent(this.smpLayers.get(i).getExtent());
				}
			}
		}

		
	}

	public void setLayerType(String layerType) {
		this.layerType = layerType;
	}

	public String getLayerType() {
		return layerType;
	}
	
	public String getUserLayerName() {

		return this.userLayerName;
	}

	public void setUserLayerName(String userLayerName) {

		this.userLayerName = userLayerName;
	}
	public IFeature transformFeature(IFeature feature, SmpCoordinateSystem fromCooSys,SmpCoordinateSystem toCooSys){
		
	
		try {
			feature = coordinateSystemTransformers.FeatureCoordinateTransformer(feature, fromCooSys, toCooSys);
		} catch (MismatchedDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAttributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return feature;
		
	}
	
	public IGeometry transformGeometry(IGeometry geometry, SmpCoordinateSystem fromCooSys,SmpCoordinateSystem toCooSys){
	
		try {
			geometry = coordinateSystemTransformers.SmpGeometryCoordinateTransformer(geometry, fromCooSys, toCooSys);
		} catch (MismatchedDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAttributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return geometry;
	}
	
	public IEnvelope transformEnvelope(IEnvelope envelope, SmpCoordinateSystem fromCooSys,SmpCoordinateSystem toCooSys) {
			
		IEnvelope layerExtent = null;
		
		try {
			
			layerExtent = coordinateSystemTransformers.SmpBoundingRectangleCoordinateTransformer(
					envelope,
					fromCooSys, 
					toCooSys);
						
		} catch (MismatchedDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAuthorityCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAttributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return layerExtent;
	}
	protected IFeatureCollection transformFeatureCollection(
			IFeatureCollection featureCollection,
			ICoordinateSystem sourceCoordinateSystem,
			ICoordinateSystem targetCoordinateSystem) {
		
		IFeatureCollection collection = new SmpFeatureCollection();
		IFeature transformedFeature;
		try{
			for (int j = 0; j < featureCollection.getFeaturesCount(); j++) {
							
				transformedFeature = 
					coordinateSystemTransformers.FeatureCoordinateTransformer(featureCollection.getFeatureAt(j), 
							sourceCoordinateSystem, 
							targetCoordinateSystem);
				collection.addFeature(transformedFeature);
	
			}
		} catch (MismatchedDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAttributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return collection;
	}

	public void addLayerAttributesFromMetadata(ILayerAttribute layerAttribute) {

		this.layerAttributesFromMetadata.add(layerAttribute);
	}

	public void addLayerAttributesFromMetadata(int index,
			ILayerAttribute layerAttribute) {

		this.layerAttributesFromMetadata.add(index,layerAttribute);
	}

	public List<ILayerAttribute> getLayerAttributesFromMetadata() {
		
		return this.layerAttributesFromMetadata;
	}

	public void removeLayerAttributesFromMetadata(int index) {
		
		this.layerAttributesFromMetadata.remove(index);	
	}

	public void setLayerPriority(int layerPriority) {
		this.layerPriority = layerPriority;
	}

	public int getLayerPriority() {
		return layerPriority;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public List<List<String>> getPointInfo(ICoordinate coordinate, ICoordinateSystem coordinateSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNamespace() {
		
		if (getName().contains(":")) {
			
			setNamespace(getName().split(":")[0]);
		}
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
}
