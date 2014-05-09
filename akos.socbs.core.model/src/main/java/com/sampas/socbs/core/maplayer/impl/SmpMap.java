package com.sampas.socbs.core.maplayer.impl;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.event.EventListenerList;
import org.geotools.feature.IllegalAttributeException;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.operation.TransformException;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.CoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.data.wms.impl.GetMapResult;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsImageReturnType;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.display.IDisplayEventsDisplayFinishedEvent;
import com.sampas.socbs.core.display.IDisplayEventsDisplayStartedEvent;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.geometry.impl.SmpDimension;
import com.sampas.socbs.core.geometry.impl.SmpPoint;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.IInteractionLayer;
import com.sampas.socbs.core.maplayer.ILayer;
import com.sampas.socbs.core.maplayer.IMap;
import com.sampas.socbs.core.maplayer.IWMSLayer;
import com.sampas.socbs.core.mapview.IActiveViewEventsAfterDrawEvent;
import com.sampas.socbs.core.mapview.IActiveViewEventsAfterItemDrawEvent;
import com.sampas.socbs.core.mapview.IActiveViewEventsContentsChangedEvent;
import com.sampas.socbs.core.mapview.IActiveViewEventsContentsClearedEvent;
import com.sampas.socbs.core.mapview.IActiveViewEventsCoordinateSystemChangedEvent;
import com.sampas.socbs.core.mapview.IActiveViewEventsFocusMapChangedEvent;
import com.sampas.socbs.core.mapview.IActiveViewEventsItemAddedEvent;
import com.sampas.socbs.core.mapview.IActiveViewEventsItemDeletedEvent;
import com.sampas.socbs.core.mapview.IActiveViewEventsItemReorderedEvent;
import com.sampas.socbs.core.mapview.IActiveViewEventsSelectionChangedEvent;
import com.sampas.socbs.core.mapview.IActiveViewEventsViewRefreshedEvent;
import com.sampas.socbs.core.mapview.IControlHandle;
import com.sampas.socbs.core.mapview.IMapItem;
import com.sampas.socbs.core.mapview.IRefreshedEventListener;
import com.sampas.socbs.core.mapview.impl.SmpActiveViewEventsViewRefreshedEvent;
import com.sampas.socbs.core.selection.ISelection;
import com.sampas.socbs.core.selection.ISelectionParameters;
import com.sampas.socbs.core.style.IWMSNamedStyle;
import com.sampas.socbs.core.tools.IProcessContext;
import com.sampas.socbs.core.tools.impl.UnitTypesEnum;


@SuppressWarnings("unused")
public class SmpMap implements IMap {

	private boolean active = false;
	
	private boolean mapActivated = false;
	
	private boolean showSelection = false;
	
	private double mapScale;
	
	private double referenceScale;
	
	private List<ILayer> layers = new ArrayList<ILayer>();
	
	private List<IMapItem> mapItem = new ArrayList<IMapItem>();
	
	private ICoordinateSystem coordinateSystem = new SmpCoordinateSystem("EPSG:4326");
	
	private String description = "";
	
	private String name;
	
	private UnitTypesEnum distanceUnits = null;
	
	private UnitTypesEnum mapUnits;
	
	private ISelection selection;
	
	private ISelection featureSelection;
	
	private IMap focusMap;
	
	private IEnvelope fullExtent = null;
	
	private IEnvelope exportFrame;
	
	private IDisplay display;
	
	private EventListenerList listenerList = new EventListenerList();
	
	private ICoordinateSystem default_CoordinateSys = new SmpCoordinateSystem("EPSG:4326");

	
	public SmpMap() {

	}

	public boolean IsActive() {
		return this.active;
	}

	public void activate(IControlHandle handle) {
		// TODO Auto-generated method stub
	}

	public void addLayer(ILayer layer) {
		// layer.setCoordinateSystem(this.coordinateSystem);
		IEnvelope envelope = layer.getExtent();
		/* if layer contains visible layer(s) */
		if (envelope != null && layer.isVisible()) {
			
			if (!layer.getCoordinateSystem().getEPSGCode().equals(getCoordinateSystem().getEPSGCode())) {
				//TODO:
				//envelope = transformEnvelope(layer.getExtent(), (SmpCoordinateSystem) layer.getCoordinateSystem(), (SmpCoordinateSystem) this.coordinateSystem);
			}
			setFullExtent(envelope);
		}
		this.layers.add(layer);
	}

	public void addMapItem(IMapItem mapItem) {

		for (int i = 0; i < getMapItemSize(); i++) {

			if (getMapItem(i).getClass().equals(mapItem.getClass())) {

				System.out.println("SMPMAP : MapItem is Already Exists");

				return;
			}
		}
		this.mapItem.add(mapItem);
	}

	public void clear() {
		// this.layers.removeAll(this.layers);
	}

	public void clearSelection() {
		// TODO Auto-generated method stub
	}

	public double computeDistance(IPoint firstPoint, IPoint secondPoint) {

		SmpPoint smpPoint = (SmpPoint) firstPoint;
		
		SmpPoint smpPoint2 = (SmpPoint) secondPoint;

		return smpPoint.getGeoToolsPoint().distance(smpPoint2.getGeoToolsPoint().getEnvelope());
	}

	public void contentsChanged() {
		// TODO Auto-generated method stub
	}

	public void deactivate() {
		// TODO Auto-generated method stub
	}

	public ICoordinateSystem getCoordinateSystem() {

		if (this.coordinateSystem == null) {

			return default_CoordinateSys;
		}
		return this.coordinateSystem;
	}

	public String getDescription() {
		return this.description;
	}

	public UnitTypesEnum getDistanceUnits() {
		return this.distanceUnits;
	}

	public IEnvelope getExportFrame() {
		return this.exportFrame;
	}

	public ISelection getFeatureSelection() {
		return this.featureSelection;
	}

	public IMap getFocusMap() {
		return this.focusMap;
	}

	public IEnvelope getFullExtent() {
		return this.fullExtent;
	}

	public boolean isMapActivated() {
		return mapActivated;
	}

	public ILayer getLayer(int index) {
		return this.layers.get(index);
	}

	public int getLayersSize() {
		return this.layers.size();
	}

	public IMapItem getMapItem(int index) {
		return this.mapItem.get(index);
	}

	public double getMapScale() {

		if (this.display == null || this.display.getDisplayTransformation() == null) {
			
			return 0;
		}
		return this.display.getDisplayTransformation().getScaleRatio();
	}

	public UnitTypesEnum getMapUnits() {
		return this.mapUnits;
	}

	public String getName() {
		return this.name;
	}

	public double getReferenceScale() {
		return this.referenceScale;
	}

	public IDisplay getDisplay() {
		return this.display;
	}

	public void setDisplay(IDisplay display) {
		
		this.display = display;
		
		if (this.display.getDisplayTransformation() != null) {
			
			if (this.display.getDisplayTransformation().getCoordinateSystem() == null) {
				
				this.display.getDisplayTransformation().setCoordinateSystem(this.coordinateSystem);
			}
		}
	}

	public ISelection getSelection() {
		return this.selection;
	}

	public boolean isShowSelection() {
		return this.showSelection;
	}

	public String getTooltip(IPoint point) {
		// TODO Auto-generated method stub
		return null;
	}

	public IEnvelope getViewExtent() {
		
		if (this.display != null) {
			
			return this.display.getDisplayTransformation().getVisibleBounds();
		} else {
			
			return null;
		}
	}

	public void insertLayer(int index, ILayer layer) {
		this.layers.set(index, layer);
	}

	public void output(IControlHandle controlHandle, int dpi, IEnvelope pixelBounds, IEnvelope visibleBounds, IProcessContext processContext) {
		// TODO Auto-generated method stub

	}

	public void refresh() {
		
		if (getDisplay() != null) {

			getDisplay().clearDevice();
			
			render(null, null);
			
			fireRefreshedEvent();
		}
	}

	// This private class is used to fire Events
	private synchronized void fireRefreshedEvent() {
		
		Object[] listeners = listenerList.getListenerList();
		// Each listener occupies two elements - the first is the listener class
		// and the second is the listener instance
		for (int i = 0; i < listeners.length; i += 2) {
			
			if (listeners[i] == IRefreshedEventListener.class) {
				
				IActiveViewEventsViewRefreshedEvent evt = new SmpActiveViewEventsViewRefreshedEvent(this);
				
				((IRefreshedEventListener) listeners[i + 1]).onViewRefreshed(evt);
			}
		}
	}

	public synchronized void addEventListener(IRefreshedEventListener listener) {

		Object[] listeners = listenerList.getListenerList();
		
		for (int i = 0; i < listeners.length; i++) {
			
			if (listeners[i].getClass().equals(listener.getClass())) {
			
				return;
			}
		}
		listenerList.add(IRefreshedEventListener.class, listener);
	}

	public synchronized void removeEventListener(IRefreshedEventListener listener) {
		
		listenerList.remove(IRefreshedEventListener.class, listener);
	}

	public void removeLayer(int index) {

		this.layers.remove(index);
		// reset the bounds value
		this.fullExtent = null;
		// recalculate the bounds value
		for (int i = 0; i < this.layers.size(); i++) {

			setFullExtent(this.layers.get(i).getExtent());
		}
	}

	public void removeMapItem(int index) {
		this.mapItem.remove(index);
	}

	public void render(IControlHandle handle, IProcessContext processContext) {
		// display.startDrawing();
		// foreach layer {
		// layer.render()
		// }
		// display.finishDrawing();
		// RenderEvent event = new RenderEvent("sdsds");
		// this.transformation.setDeviceFrame(handle.getDisplay().getEnvelope());
		try {

			List<IWMSLayer> tmpWmsLayers = new ArrayList<IWMSLayer>();
			
			List<IFeatureLayer> wfsLayers = new ArrayList<IFeatureLayer>();
			
			List<IInteractionLayer> interactiveLayers = new ArrayList<IInteractionLayer>();
			
			List<IWMSNamedStyle> tmpWmsNamedStyles = new ArrayList<IWMSNamedStyle>();

			boolean renderWmsLayer = false;
			
			boolean renderWfsLayer = false;
			
			boolean renderInteractiveLayer = false;

			IWMSNamedStyle namedStyle;
			
			boolean nullStyleExist = false;
			
			for (int i = 0; i < this.layers.size(); i++) {

				if (this.layers.get(i).isVisible()) {
					
					if (this.layers.get(i).getLayerType().equals("wms")) {

						namedStyle = null;
						
						tmpWmsLayers.add((IWMSLayer) this.layers.get(i));
						
						if (this.layers.get(i) instanceof SmpWMSLayer) {

							if (((SmpWMSLayer) this.layers.get(i)).getWmsNamedStyles() != null && ((SmpWMSLayer) this.layers.get(i)).getWmsNamedStyles().size() != 0) {
								
								namedStyle = ((SmpWMSLayer) this.layers.get(i)).getWmsNamedStyles().get(0);
							}
						}
						if (namedStyle == null) {
							
							nullStyleExist = true;
						}
						tmpWmsNamedStyles.add(namedStyle);
						
						renderWmsLayer = true;
					} else if (this.layers.get(i).getLayerType().equals("wfs")) {

						wfsLayers.add((IFeatureLayer) this.layers.get(i));
						
						renderWfsLayer = true;
					} else if (this.layers.get(i).getLayerType().equals("interactive")) {
						
						interactiveLayers.add((IInteractionLayer) this.layers.get(i));
						
						renderInteractiveLayer = true;
					}
				}
			}
			if (nullStyleExist) {
				
				for (int i = 0; i < tmpWmsNamedStyles.size(); i++) {
					
					tmpWmsNamedStyles.set(i, null);
				}
			}
			tmpWmsNamedStyles = null;

			if (renderWmsLayer) {
				// SmpScrDimension smpScrDimension = new SmpScrDimension(
				// (int)this.display.getDisplayTransformation().getDeviceFrame().getWidth(),
				// (int)this.display.getDisplayTransformation().getDeviceFrame().getHeight());
				double envelopeWidth = this.display.getDisplayTransformation().getFittedBounds().getWidth();

				double envelopeHeight = this.display.getDisplayTransformation().getFittedBounds().getHeight();
				
				double deviceFrameWidth = this.display.getDisplayTransformation().getDeviceFrame().getWidth();
				
				double deviceFrameHeight = this.display.getDisplayTransformation().getDeviceFrame().getHeight();
				// RESET Device Frame
				// if(deviceFrameWidth < ( (envelopeWidth*
				// deviceFrameHeight)/envelopeHeight )){
				//
				// deviceFrameHeight = (envelopeHeight*
				// deviceFrameWidth)/envelopeWidth;
				// }
				// else{
				// deviceFrameWidth = (envelopeWidth*
				// deviceFrameHeight)/envelopeHeight;
				// }
				IDimension dimension = new SmpDimension((int) deviceFrameWidth, (int) deviceFrameHeight);

				IEnvelope layerExtent = this.display.getDisplayTransformation().getFittedBounds();
				// new SmpBoundingRectangle(
				// 28.922419441790407,
				// 41.00719264533375,
				// 29.012371781013716,
				// 41.07867164511793);
				GetMapResult getMap = null;

				try {
					
					getMap = ((SmpWMSLayer) tmpWmsLayers.get(0)).getMap(tmpWmsLayers, tmpWmsNamedStyles, (SmpCoordinateSystem) getCoordinateSystem(), layerExtent, dimension, WmsImageReturnType.png);
				} catch (Exception e) {
					
					System.out.println(e.getMessage());

					for (int i = 0; i < e.getStackTrace().length; i++) {

						System.out.println(e.getStackTrace()[i]);
					}
				}
				if (getMap != null) {
					
					System.out.println("WMS Byte Arr len" + getMap.getImage().length);
					
					ImageIcon imgIconDoc = null;

					try {
					
						imgIconDoc = new ImageIcon(getMap.getImage());
					} catch (Exception e) {

						System.out.println(e.getMessage());

						for (int i = 0; i < e.getStackTrace().length; i++) {

							System.out.println(e.getStackTrace()[i]);
						}
					}
					// /* Resmi temp dosyasına bastırmak için*/
					// BufferedImage image = new BufferedImage(
					// (int)this.display.getEnvelope().getWidth(),
					// (int)this.display.getEnvelope().getHeight(),
					// BufferedImage.TYPE_USHORT_555_RGB);
					// //image.createGraphics().drawBytes(getMap.getImage(), 0,
					// getMap.getImage().length, 0, 0);
					//
					//					
					// // ByteArrayOutputStream baos = new
					// ByteArrayOutputStream();
					// // ImageWriter iw =
					// (ImageWriter)ImageIO.getImageWritersByFormatName("png").next();
					// // ImageOutputStream ios =
					// ImageIO.createImageOutputStream(baos);
					// // iw.setOutput(ios);
					// // iw.write(image);
					// // iw.dispose();
					// // ios.close();
					//					
					//					
					// image.createGraphics().drawImage(imgIconDoc.getImage(),
					// null, null);
					//					
					// File tempFile = File.createTempFile("MyFile.png", ".png"
					// );
					// System.out.println(tempFile.getAbsolutePath());
					//					
					//					
					// ImageIO.write( image, "png" /* "png" "jpeg" ... format
					// desired */,
					// tempFile /* target */ );
					this.display.getDevice().getDevice().setColor(Color.BLACK);

					Image tmpimage = null;
					
					try {
					
						tmpimage = imgIconDoc.getImage();

					} catch (Exception e) {
						
						System.out.println(e.getMessage());

						for (int i = 0; i < e.getStackTrace().length; i++) {

							System.out.println(e.getStackTrace()[i]);
						}
					}
					try {

						this.display.getDevice().getDevice().drawImage(tmpimage, null, null);
					} catch (Exception e) {
						
						System.out.println(e.getMessage());

						for (int i = 0; i < e.getStackTrace().length; i++) {

							System.out.println(e.getStackTrace()[i]);
						}
					}
				} else {
					System.err.println("SMP_MAP : CANNOT TAKE IMAGE FROM SERVER");
				}
			}
			if (renderWfsLayer) {

				for (int j = 0; j < wfsLayers.size(); j++) {

					wfsLayers.get(j).render(null, this.display, processContext);
				}
			}
			if (renderInteractiveLayer) {

				for (int j = 0; j < interactiveLayers.size(); j++) {

					interactiveLayers.get(j).render(null, this.display, processContext);
				}
			}
			// TODO: render selected features yani selection listesindeki feature'ları selection symbol ile çiz

			// TODO : render all map items
			if (renderWmsLayer || renderWfsLayer) {

				if (this.featureSelection != null) {

					this.featureSelection.draw(display, null);
				}
			}
			// TODO: Component denemeleri icin kullanim disi
			// if(this.mapItem != null){
			// for (int i = 0; i < this.mapItem.size(); i++) {
			// this.mapItem.get(i).render(this.display, processContext, null);
			// }
			// }
		} catch (Exception e) {
			
			System.err.println("Error : " + e.getMessage());
		}
	}

	public void selectByShape(IGeometry shape, ISelectionParameters selectionParameters, int maxSelectionCount) {
		// TODO Auto-generated method stub
	}

	public void selectFeature(ILayer layer, IFeature feature) {
		// TODO Auto-generated method stub
	}

	public void setCoordinateSystem(ICoordinateSystem coordinateSystem) {

		this.coordinateSystem = coordinateSystem;
		
		if (this.display != null) {
			
			this.display.getDisplayTransformation().setCoordinateSystem(coordinateSystem);
		}
	}

	public void setDescription(String description) {
		this.description = description;

	}

	public void setDistanceUnits(UnitTypesEnum units) {
		this.distanceUnits = units;

	}

	public void setFeatureSelection(ISelection selection) {
		this.featureSelection = selection;

	}

	public void setFocusMap(IMap map) {
		this.focusMap = map;

	}

	public void setFullExtent(IEnvelope extent) {

		if (extent == null) {
			
			System.out.println("SMP MAP -Extent Set Error-");
			
			return;
		}
		if (this.fullExtent == null) {

			this.fullExtent = extent;
		} else {

			double minX = Math.min(this.fullExtent.getMinX(), extent.getMinX());
			
			double minY = Math.min(this.fullExtent.getMinY(), extent.getMinY());
			
			double maxX = Math.max(this.fullExtent.getMaxX(), extent.getMaxX());
			
			double maxY = Math.max(this.fullExtent.getMaxY(), extent.getMaxY());

			IEnvelope envelope = new SmpBoundingRectangle(minX, minY, maxX, maxY);
			
			this.fullExtent = envelope;
		}
		/* Use ZoomIn-ZoomOut When calculate VisibleBound */
		// this.transformation.setVisibleBounds(this.fullExtent);
		if (this.fullExtent != null && this.display != null) {
			
			this.display.getDisplayTransformation().setBounds(this.fullExtent);
		}
	}

	public void setMapActivated(boolean activated) {
		this.mapActivated = activated;
	}

	public void setMapScale(double scale) {
		this.mapScale = scale;
	}

	public void setMapUnits(UnitTypesEnum units) {
		this.mapUnits = units;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setReferenceScale(double scale) {
		this.referenceScale = scale;
	}

	public void setSelection(ISelection selection) {
		this.selection = selection;
	}

	public void setShowSelection(boolean show) {
		this.showSelection = show;
	}

	public void setViewExtent(IEnvelope extent) {

		if (display != null) {
			
			this.display.getDisplayTransformation().setVisibleBounds(extent);
		}
	}

	public boolean IsMapActivated() {
		return this.mapActivated;
	}

	public void afterDraw(IActiveViewEventsAfterDrawEvent event) {
		// TODO Auto-generated method stub
	}

	public void afterItemDraw(IActiveViewEventsAfterItemDrawEvent event) {
		// TODO Auto-generated method stub
	}

	public void contentsChanged(IActiveViewEventsContentsChangedEvent event) {
		// TODO Auto-generated method stub
	}

	public void contentsCleared(IActiveViewEventsContentsClearedEvent event) {
		// TODO Auto-generated method stub
	}

	public void coordinateSystemChanged(IActiveViewEventsCoordinateSystemChangedEvent event) {
		// TODO Auto-generated method stub
	}

	public void focusMapChanged(IActiveViewEventsFocusMapChangedEvent event) {
		// TODO Auto-generated method stub
	}

	public void itemAdded(IActiveViewEventsItemAddedEvent event) {
		// TODO Auto-generated method stub
	}

	public void itemDeleted(IActiveViewEventsItemDeletedEvent event) {
		// TODO Auto-generated method stub
	}

	public void itemReordered(IActiveViewEventsItemReorderedEvent event) {
		// TODO Auto-generated method stub
	}

	public void selectionChanged(IActiveViewEventsSelectionChangedEvent event) {
		// TODO Auto-generated method stub
	}

	public void viewRefreshed(IActiveViewEventsViewRefreshedEvent event) {
		// TODO Auto-generated method stub
	}

	public void displayStarted(IDisplayEventsDisplayStartedEvent theEvent) {
		// TODO Auto-generated method stub
	}

	public void displayFinished(IDisplayEventsDisplayFinishedEvent theEvent) {
		// TODO Auto-generated method stub
	}

	public List<List<String>> getPointInfo(ICoordinate coordinate, long layerId, double offsetX, double offsetY) {

		ILayer layer = findLayer(layerId);
		// double offsetX =
		// this.display.getDisplayTransformation().getFittedBounds().getWidth()
		// * 0.01;
		// double offsetY =
		// this.display.getDisplayTransformation().getFittedBounds().getHeight()*
		// 0.01;
		if (layer != null) {
			// return
			// layer.getPointInfo(coordinate,this.coordinateSystem,this.display.getDisplayTransformation().getFittedBounds(),offsetX,offsetY);
			return layer.getPointInfo(coordinate, this.coordinateSystem);
		}
		return null;
	}

	public void resetExtent() {
		/* initialize the extends */
		IEnvelope oldVisible = this.display.getDisplayTransformation().getVisibleBounds();
		
		this.fullExtent = null;
		
		this.display.getDisplayTransformation().setBounds(null);
		
		boolean nonLayerSelected = true;

		for (int i = 0; i < this.layers.size(); i++) {

			IEnvelope envelope = this.layers.get(i).getExtent();

			if (envelope != null) {

				if (this.layers.get(i).isVisible()) {

					nonLayerSelected = false;
					
					if (!this.layers.get(i).getCoordinateSystem().getEPSGCode().equals(getCoordinateSystem().getEPSGCode())) {

						envelope = transformEnvelope(this.layers.get(i).getExtent(), (SmpCoordinateSystem) this.layers.get(i).getCoordinateSystem(), (SmpCoordinateSystem) getCoordinateSystem());
					} else {
						
						envelope = this.layers.get(i).getExtent();
					}
					setFullExtent(envelope);
				}
			}
		}
		/* set back to old visible Extent */
		if (oldVisible != null && !nonLayerSelected) {
			
			this.display.getDisplayTransformation().setVisibleBounds(oldVisible);
		}
	}

	public void changeLayerVisibilityAll(boolean isEnable) {

		for (int i = 0; i < this.layers.size(); i++) {
			
			this.layers.get(i).setVisible(isEnable);
			
			changeLayerVisibility(this.layers.get(i).getId());
		}
	}

	public void changeLayerVisibility(long id) {

		ILayer targetLayer = findLayer(id);
		
		targetLayer.changeLayerVisibility();
		/* make layer visible and add its extent to fullExtent */
		IEnvelope envelope = targetLayer.getExtent();

		if (envelope != null) {
			
			if (targetLayer.isVisible()) {
				
				IEnvelope oldVisible = this.display.getDisplayTransformation().getVisibleBounds();
				
				if (!targetLayer.getCoordinateSystem().getEPSGCode().equals(getCoordinateSystem().getEPSGCode())) {

					envelope = transformEnvelope(targetLayer.getExtent(), (SmpCoordinateSystem) targetLayer.getCoordinateSystem(), (SmpCoordinateSystem) getCoordinateSystem());
				}
				setFullExtent(envelope);

				if (oldVisible != null) {
				
					this.display.getDisplayTransformation().setVisibleBounds(oldVisible);
				}
			} else {
				
				resetExtent();
			}
			/*
			 * Resetting visibleBounds if not null, fitted Bounds is changing
			 * after reset extend
			 */
		}
	}

	public ILayer findLayer(long id) {

		for (int i = 0; i < this.layers.size(); i++) {

			if (this.layers.get(i).getId() == id) {

				return this.layers.get(i);
			} else if (this.layers.get(i).getLayerCount() != 0) {

				findLayerRecursively(this.layers.get(i), id);
			}
		}
		return null;
	}

	private ILayer findLayerRecursively(ILayer layer, long id) {

		for (int i = 0; i < layer.getLayerCount(); i++) {

			if (layer.getLayerAt(i).getId() == id) {

				return layer.getLayerAt(i);
			} else if (layer.getLayerAt(i).getLayerCount() != 0) {

				findLayerRecursively(layer.getLayerAt(i), id);
			}
		}
		return null;
	}

	private IEnvelope transformEnvelope(IEnvelope envelope, SmpCoordinateSystem fromCooSys, SmpCoordinateSystem toCooSys) {

		ICoordinateSystemTransformers coordinateSystemTransformers = new CoordinateSystemTransformers();

		IEnvelope layerExtent = null;

		try {

			layerExtent = coordinateSystemTransformers.SmpBoundingRectangleCoordinateTransformer(envelope, fromCooSys, toCooSys);

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

	public int getMapItemSize() {
		return this.mapItem.size();
	}
}