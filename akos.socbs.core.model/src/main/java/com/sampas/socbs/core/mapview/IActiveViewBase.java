package com.sampas.socbs.core.mapview;

import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.maplayer.IMap;
import com.sampas.socbs.core.selection.ISelection;
import com.sampas.socbs.core.tools.IProcessContext;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:40
 */
public interface IActiveViewBase {

	/**
	 * 
	 * @param handle
	 */
	public void activate(IControlHandle handle);

	public void clear();

	public void contentsChanged();

	public void deactivate();

	public IEnvelope getExportFrame();

	public IMap getFocusMap();

	public IEnvelope getFullExtent();

	public IDisplay getDisplay();

	public ISelection getSelection();

	public boolean isShowSelection();

	/**
	 * 
	 * @param point
	 */
	public String getTooltip(IPoint point);

	public IEnvelope getViewExtent();

	public boolean IsActive();

	public boolean IsMapActivated();

	/**
	 * 
	 * @param controlHandle
	 * @param dpi
	 * @param pixelBounds
	 * @param visibleBounds
	 * @param processContext
	 */
	public void output(IControlHandle controlHandle, int dpi,
			IEnvelope pixelBounds, IEnvelope visibleBounds,
			IProcessContext processContext);

	public void refresh();

	/**
	 * 
	 * @param handle
	 * @param processContext
	 */
	public void render(IControlHandle handle, IProcessContext processContext);

	/**
	 * 
	 * @param map
	 */
	public void setFocusMap(IMap map);

	/**
	 * 
	 * @param extent
	 */
	public void setFullExtent(IEnvelope extent);

	/**
	 * 
	 * @param activated
	 */
	public void setMapActivated(boolean activated);

	/**
	 * 
	 * @param selection
	 */
	public void setSelection(ISelection selection);

	/**
	 * 
	 * @param show
	 */
	public void setShowSelection(boolean show);

	/**
	 * 
	 * @param extent
	 */
	public void setViewExtent(IEnvelope extent);

}