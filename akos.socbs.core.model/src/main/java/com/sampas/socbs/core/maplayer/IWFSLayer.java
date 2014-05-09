package com.sampas.socbs.core.maplayer;

import java.net.URL;
import java.util.List;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:46
 */
public interface IWFSLayer extends ILayer,IFeatureLayer {

	public URL getServiceAdress();
	
	public void setServiceAdress(String serviceAdress);
	
	public String getServeDirectory();

	public void setServeDirectory(String serveDirectory);
	
	public String getServisPage();
	
	public void setServisPage(String servisPage);
	
	public List<String> addVersions(String version);
	
	public void removeVersions(int index);
	
	public int getVersionCount();
	
	public String getVersionAt(int index);
	
}