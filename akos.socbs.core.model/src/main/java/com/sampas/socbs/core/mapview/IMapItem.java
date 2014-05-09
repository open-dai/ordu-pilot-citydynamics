package com.sampas.socbs.core.mapview;

import java.util.List;

import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.display.impl.MapItemTypesEnum;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.maplayer.IMap;
import com.sampas.socbs.core.tools.IBitmap;
import com.sampas.socbs.core.tools.IProcessContext;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:44
 */
public interface IMapItem {

	/**
	 * 
	 * @param display
	 */
	public void afterRender(IDisplay display);

	/**
	 * 
	 * @param display
	 */
	public void beforeRender(IDisplay display);

	public void contentsChanged();

	/**
	 * 
	 * @param display
	 * @param bounds
	 */
	public void fitToBounds(IDisplay display, IEnvelope bounds,boolean changed);

	/**
	 * 
	 * @param display
	 * @param oldBounds
	 */
	public IEnvelope getBounds(IDisplay display, IEnvelope oldBounds);

	public List<IBitmap> getIcons();

	public IMap getMap();

	public String getName();

	public boolean IsFixedAspectRatio();

	public boolean IsFixedSize();

	public void refresh();

	/**
	 * 
	 * @param display
	 * @param processContext
	 * @param boudingBox
	 */
	public void render(IDisplay display, IProcessContext processContext,
			IEnvelope boudingBox);

	/**
	 * 
	 * @param fixed
	 */
	public void setFixedAspectRatio(boolean fixed);

	/**
	 * 
	 * @param icons
	 */
	public void setIcons(List<IBitmap> icons);

	/**
	 * 
	 * @param map
	 */
	public void setMap(IMap map);

	/**
	 * 
	 * @param name
	 */
	public void setName(String name);
	
	public MapItemTypesEnum getMapItemType();
	
	public void setMapItemType(MapItemTypesEnum type);

}