package com.sampas.socbs.core.display.impl;

import com.sampas.socbs.core.display.IDevice;
import com.sampas.socbs.core.display.IDisplayTransformation;
import com.sampas.socbs.core.display.IScreenDisplay;
import com.sampas.socbs.core.display.ITransformEventsBoundsUpdatedEvent;
import com.sampas.socbs.core.display.ITransformEventsDeviceFrameUpdatedEvent;
import com.sampas.socbs.core.display.ITransformEventsResolutionUpdatedEvent;
import com.sampas.socbs.core.display.ITransformEventsRotationUpdatedEvent;
import com.sampas.socbs.core.display.ITransformEventsUnitsUpdatedEvent;
import com.sampas.socbs.core.display.ITransformEventsVisibleBoundsUpdatedEvent;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.tools.IColor;
import com.sampas.socbs.core.tools.IProcessContext;

public class SmpScreenDisplay extends SmpDisplay implements IScreenDisplay {

	
	public SmpScreenDisplay(IEnvelope envelope,
			IDisplayTransformation displayTransformation) {
		super(envelope, displayTransformation);
		// TODO Auto-generated constructor stub
	}


	public void Invalidate(IEnvelope envelope, boolean delete, int cacheID) {
		// TODO Auto-generated method stub
		
	}

	
	public boolean IsCacheDirty(int cacheID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public int addCache() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void boundsUpdated(ITransformEventsBoundsUpdatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void deviceFrameUpdated(ITransformEventsDeviceFrameUpdatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void drawCache(IDevice device, int cacheID,
			IEnvelope deviceRectangle, IEnvelope cacheRectangle) {
		// TODO Auto-generated method stub
		
	}

	
	public int getActiveCacheID() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public IColor getBackgroundColor() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getCacheCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public IGeometry getClipGeometry() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public IProcessContext getProcessContext() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void removeAllCaches() {
		// TODO Auto-generated method stub
		
	}

	
	public void removeCache(int cacheID) {
		// TODO Auto-generated method stub
		
	}

	
	public void resolutionUpdated(ITransformEventsResolutionUpdatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void rotationUpdated(ITransformEventsRotationUpdatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void setActiveCacheID(int cacheID) {
		// TODO Auto-generated method stub
		
	}

	
	public void setBackgroundColor(IColor color) {
		// TODO Auto-generated method stub
		
	}

	
	public void setProcessContext(IProcessContext context) {
		// TODO Auto-generated method stub
		
	}

	
	public void startFeedback() {
		// TODO Auto-generated method stub
		
	}

	
	public void stopFeedback() {
		// TODO Auto-generated method stub
		
	}

	
	public void unitsUpdated(ITransformEventsUnitsUpdatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void validate(int cacheID) {
		// TODO Auto-generated method stub
		
	}

	
	public void visibleBoundsUpdated(
			ITransformEventsVisibleBoundsUpdatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void invalidate(IEnvelope envelope, boolean delete, int cacheID) {
		// TODO Auto-generated method stub
		
	}

}
