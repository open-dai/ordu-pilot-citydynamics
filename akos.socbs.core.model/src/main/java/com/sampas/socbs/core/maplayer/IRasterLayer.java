package com.sampas.socbs.core.maplayer;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:44
 */
public interface IRasterLayer extends ILayer {

	public int getMaxHeight();
	
	public void setMaxHeight(int height);
	
	public int getMaxWidth();
	
	public void setMaxWidth(int width);
	
	public boolean isSuitableForRequest();
	
}