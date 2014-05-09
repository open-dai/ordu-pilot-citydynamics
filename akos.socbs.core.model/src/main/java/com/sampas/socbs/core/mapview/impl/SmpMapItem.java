package com.sampas.socbs.core.mapview.impl;

import java.util.List;

import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.display.impl.MapItemTypesEnum;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpEnvelope;
import com.sampas.socbs.core.maplayer.IMap;
import com.sampas.socbs.core.mapview.IMapItem;
import com.sampas.socbs.core.tools.IBitmap;
import com.sampas.socbs.core.tools.IProcessContext;

public class SmpMapItem implements IMapItem {
	
	private boolean fixedRatio = false;
	private boolean fixedSize  = false;
	private List<IBitmap> icons= null;
	private IMap map = null;
	private String name = "";
	private IEnvelope bounds = null;
	private MapItemTypesEnum type;
	
	public SmpMapItem(){
		
	}

	
	public boolean IsFixedAspectRatio() {
	
		return this.fixedRatio;
	}

	
	public boolean IsFixedSize() {
	
		return this.fixedSize;
	}

	
	public void afterRender(IDisplay display) {
		// TODO Auto-generated method stub

	}

	
	public void beforeRender(IDisplay display) {
		// TODO Auto-generated method stub

	}

	
	public void contentsChanged() {
		// TODO Auto-generated method stub

	}

	
	public void fitToBounds(IDisplay display, IEnvelope bounds,boolean changed) {
		
		 this.bounds.equals(bounds);

	}

	
	public IEnvelope getBounds(IDisplay display, IEnvelope oldBounds) {
		
		if (!this.bounds.equals(oldBounds) ) {  // or reset 
			 
			IEnvelope envelope = display.getEnvelope();
			// Event Fire et changed = true;
			double scaleX = envelope.getWidth() / oldBounds.getWidth();
			double scaleY = envelope.getHeight() / oldBounds.getHeight();
            double scale = 1.0; // stupid compiler!
            
            if (scaleX < scaleY) {/*pick the smaller scale */
                scale = scaleX;
            } else {
                scale = scaleY;
            }
            /* calculate the difference in width and height of the new extent*/
            double deltaX = /*Math.abs*/((envelope.getWidth() / scale) - oldBounds.getWidth());
            double deltaY = /*Math.abs*/((envelope.getHeight() / scale) - oldBounds.getHeight());
                    
            this.bounds = new SmpEnvelope( oldBounds.getMinX() - (deltaX / 2.0),
            		oldBounds.getMinY() - (deltaY / 2.0),
            		oldBounds.getMaxX() + (deltaX / 2.0),
            		oldBounds.getMaxY() + (deltaY / 2.0));
	 
			 
		 }
		
		
		return this.bounds;
	}

	
	public List<IBitmap> getIcons() {
	
		return this.icons;
	}

	
	public IMap getMap() {

		return this.map;
	}

	
	public String getName() {
		
		return this.name;
	}

	
	public void refresh() {
		// TODO Auto-generated method stub

	}

	
	public void render(IDisplay display, IProcessContext processContext,
			IEnvelope boudingBox) {
		// TODO Auto-generated method stub

	}

	
	public void setFixedAspectRatio(boolean fixed) {
	
		this.fixedRatio = fixed;

	}

	
	public void setIcons(List<IBitmap> icons) {
		
		this.icons = icons;

	}

	
	public void setMap(IMap map) {
		
		this.map = map;

	}

	
	public void setName(String name) {
		this.name = name;

	}


	public MapItemTypesEnum getMapItemType() {

		return this.type;
	}


	public void setMapItemType(MapItemTypesEnum type) {
		
		this.type = type;
		
	}

}
