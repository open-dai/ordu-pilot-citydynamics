package com.sampas.socbs.core.maplayer;

import java.util.List;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.renderer.IRenderer;
import com.sampas.socbs.core.renderer.impl.RenderPhaseEnum;
import com.sampas.socbs.core.tools.IProcessContext;

/**
 * method to draw the layer and provides access to generic layer properties.
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface ILayer {
	
	public List <ILayerAttribute> getSmpLayerAttributes();
	
	public ILayerAttribute getSmpLayerAttribute(String name);
	
	public List<ILayerAttribute> getLayerAttributesFromMetadata();
	
	public void addLayerAttributesFromMetadata(ILayerAttribute layerAttribute);
	
	public void addLayerAttributesFromMetadata(int index, ILayerAttribute layerAttribute);
	
	public void removeLayerAttributesFromMetadata(int index);
	
	public void setSmpLayerAttributes(List<ILayerAttribute> layerAttribute);

	public void addLayer(ILayer layer) throws Exception;
	
	public void removeLayer(int index);
	
	public ILayer getLayerAt(int index);
	
	public int getLayerCount();
	
	public IRenderer getRenderer(int index);
	
	public void addRenderer(IRenderer renderer) throws Exception;
	
	public void removeRenderer(int index);
	
	public int getRendererCount();
	
	public void setExtent(IEnvelope envelope);
	/**
	 * The default area of interest for the layer.
	 */
	public IEnvelope getExtent();

	/**
	 * Maximum scale (representative fraction) at which the layer will display.
	 */
	public double getMaximumScale();

	/**
	 * Minimum scale (representative fraction) at which the layer will display.
	 */
	public double getMinimumScale();

	/**
	 * Layer name.
	 */
	public String getName();

	/**
	 * Map tip text at the specified location.
	 * 
	 * @param point
	 * @param tolerance
	 */
	public String getTipText(IPoint point, double tolerance);

	/**
	 * Indicates if the layer is currently visible.
	 */
	public boolean isVisible();

	/**
	 * Draws the layer to the specified display for the given draw phase.
	 * 
	 * @param renderPhase
	 * @param display
	 * @param processContext
	 */
	public void render(RenderPhaseEnum renderPhase, IDisplay display, IProcessContext processContext);

	/**
	 * 
	 * @param coordinateSystem
	 */
	public void setCoordinateSystem(ICoordinateSystem coordinateSystem);

	public ICoordinateSystem getCoordinateSystem();
	/**
	 * Maximum scale (representative fraction) at which the layer will display.
	 * 
	 * @param scale
	 */
	public void setMaximumScale(double scale);

	/**
	 * Minimum scale (representative fraction) at which the layer will display.
	 * 
	 * @param scale
	 */
	public void setMinimumScale(double scale);

	/**
	 * Sets the Layer name.
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * Indicates if the layer is currently visible.
	 * 
	 * @param visible
	 */
	public void setVisible(boolean visible);
	
	public void setRenderer(List<IRenderer> smpRenderer);
	
	public void setGeometryColumnName(String geometryColumnName);
	
	public String getGeometryColumnName();
	
	public List<List<String>> getPointInfo(ICoordinate  coordinate, 
			ICoordinateSystem  coordinateSystem,IEnvelope visibleExtent,double offsetX,double offsetY );

	public List<List<String>> getPointInfo(ICoordinate  coordinate, ICoordinateSystem  coordinateSystem);
	
	public void changeLayerVisibility();
	
	public void setLayerType(String layerType);

	public String getLayerType();
	
	public long getId();
	
	public void setId(long id);
	
	public String getUserLayerName();
	
	public void setUserLayerName(String userLayerName);
	
	public void resetExtent();
	
	public void setLayerPriority(int layerPriority);
	
	public int getLayerPriority();
	
	public String getNamespace();

	public void setNamespace(String nameSpace);
	
}