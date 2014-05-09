package com.sampas.socbs.core.display;

import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.symbology.ISymbol;
import com.sampas.socbs.core.tools.IColor;
import com.sampas.socbs.core.tools.IProcessContext;

/**
 * The IScreenDisplay interface manages the display attributes of a screen.
 * IScreenDisplay also handles other issues specific to windows including the
 * backing store, scrolling, and invalidation.
 * 
 * Two objects currently implement IScreenDisplay, AppDisplay and ScreenDisplay.
 * Each object's implementation of IScreenDisplay is slightly different; look at
 * the help for a particular member for more details.
 * @version 1.0
 * @created 07-Kas-2008 13:35:45
 */
public interface IScreenDisplay extends IScreenDisplayBase, IDisplay, ITransformEvents {

	public int addCache();

	/**
	 * 
	 * @param event
	 */
	public void boundsUpdated(ITransformEventsBoundsUpdatedEvent event);

	/**
	 * 
	 * @param event
	 */
	public void deviceFrameUpdated(ITransformEventsDeviceFrameUpdatedEvent event);

	/**
	 * 
	 * @param event
	 */
	public void displayFinished(IDisplayEventsDisplayFinishedEvent event);

	/**
	 * 
	 * @param event
	 */
	public void displayInvalidated(IDisplayEventsDisplayInvalidatedEvent event);

	/**
	 * 
	 * @param event
	 */
	public void displayStarted(IDisplayEventsDisplayStartedEvent event);

	/**
	 * 
	 * @param device
	 * @param cacheID
	 * @param deviceRectangle
	 * @param cacheRectangle
	 */
	public void drawCache(IDevice device, int cacheID, IEnvelope deviceRectangle, IEnvelope cacheRectangle);

	/**
	 * 
	 * @param rectangle
	 */
	public void drawRectangle(IEnvelope rectangle);

	/**
	 * 
	 * @param baseLine
	 * @param text
	 */
	public void drawText(IGeometry baseLine,String text);

	public void finishDrawing();

	public int getActiveCacheID();

	public IColor getBackgroundColor();

	public int getCacheCount();

	public IEnvelope getClipEnvelope();

	public IGeometry getClipGeometry();

	public IDisplayTransformation getDisplayTransformation();

	public IProcessContext getProcessContext();

	/**
	 * 
	 * @param envelope
	 * @param delete
	 * @param cacheID
	 */
	public void Invalidate(IEnvelope envelope, boolean delete, int cacheID);

	/**
	 * 
	 * @param cacheID
	 */
	public boolean IsCacheDirty(int cacheID);

	public void removeAllCaches();

	/**
	 * 
	 * @param cacheID
	 */
	public void removeCache(int cacheID);

	/**
	 * 
	 * @param event
	 */
	public void resolutionUpdated(ITransformEventsResolutionUpdatedEvent event);

	/**
	 * 
	 * @param event
	 */
	public void rotationUpdated(ITransformEventsRotationUpdatedEvent event);

	/**
	 * 
	 * @param cacheID
	 */
	public void setActiveCacheID(int cacheID);

	/**
	 * 
	 * @param color
	 */
	public void setBackgroundColor(IColor color);

	/**
	 * 
	 * @param displayTransformation
	 */
	public void setDisplayTransformation(IDisplayTransformation displayTransformation);

	/**
	 * 
	 * @param context
	 */
	public void setProcessContext(IProcessContext context);

	/**
	 * 
	 * @param symbol
	 */
	public void setSymbol(ISymbol symbol);

	/**
	 * 
	 * @param device
	 * @param cacheID
	 */
	public void startDrawing(IDevice device, int cacheID);

	public void startFeedback();

	public void stopFeedback();

	/**
	 * 
	 * @param event
	 */
	public void unitsUpdated(ITransformEventsUnitsUpdatedEvent event);

	/**
	 * 
	 * @param cacheID
	 */
	public void validate(int cacheID);

	/**
	 * 
	 * @param event
	 */
	public void visibleBoundsUpdated(ITransformEventsVisibleBoundsUpdatedEvent event);

}