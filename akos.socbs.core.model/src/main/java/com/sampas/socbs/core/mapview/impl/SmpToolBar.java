package com.sampas.socbs.core.mapview.impl;

import java.util.List;

import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.display.impl.MapItemTypesEnum;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.maplayer.IMap;
import com.sampas.socbs.core.mapview.IMapToolBar;
import com.sampas.socbs.core.tools.IBitmap;
import com.sampas.socbs.core.tools.IProcessContext;

public class SmpToolBar implements IMapToolBar {

	private MapItemTypesEnum type;
	
	public boolean IsFixedAspectRatio() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean IsFixedSize() {
		// TODO Auto-generated method stub
		return false;
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

	public void fitToBounds(IDisplay display, IEnvelope bounds, boolean changed) {
		// TODO Auto-generated method stub

	}

	public IEnvelope getBounds(IDisplay display, IEnvelope oldBounds) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IBitmap> getIcons() {
		// TODO Auto-generated method stub
		return null;
	}

	public IMap getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void refresh() {
		// TODO Auto-generated method stub

	}

	public void render(IDisplay display, IProcessContext processContext,
			IEnvelope boudingBox) {
		// TODO Auto-generated method stub

	}

	public void setFixedAspectRatio(boolean fixed) {
		// TODO Auto-generated method stub

	}

	public void setIcons(List<IBitmap> icons) {
		// TODO Auto-generated method stub

	}

	public void setMap(IMap map) {
		// TODO Auto-generated method stub

	}

	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	public MapItemTypesEnum getMapItemType() {
		return this.type;
	}

	public void setMapItemType(MapItemTypesEnum type) {
		this.type = type;
		
	}

}
