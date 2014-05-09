package com.sampas.socbs.core.display;

import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.tools.IColor;
import com.sampas.socbs.core.tools.IProcessContext;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:45
 */
public interface IScreenDisplayBase {

	public int addCache();

	/**
	 * 
	 * @param device
	 * @param cacheID
	 * @param deviceRectangle
	 * @param cacheRectangle
	 */
	public void drawCache(IDevice device, int cacheID, IEnvelope deviceRectangle, IEnvelope cacheRectangle);

	public int getActiveCacheID();

	public IColor getBackgroundColor();

	public int getCacheCount();

	public IProcessContext getProcessContext();

	/**
	 * 
	 * @param envelope
	 * @param delete
	 * @param cacheID
	 */
	public void invalidate(IEnvelope envelope, boolean delete, int cacheID);

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
	 * @param context
	 */
	public void setProcessContext(IProcessContext context);

	public void startFeedback();

	public void stopFeedback();

	/**
	 * 
	 * @param cacheID
	 */
	public void validate(int cacheID);

}