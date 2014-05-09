package com.sampas.socbs.core.maplayer;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.data.IWMSDataStore;
import com.sampas.socbs.core.data.wms.impl.GetFeatureInfoResult;
import com.sampas.socbs.core.data.wms.impl.GetMapResult;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsImageReturnType;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpDimension;
import com.sampas.socbs.core.geometry.impl.SmpScrPoint;
import com.sampas.socbs.core.style.IWMSNamedStyle;
import com.sampas.socbs.core.style.impl.WMSNamedStyle;
import com.sampas.socbs.core.symbology.IStyle;


public interface IWMSLayer extends ILayer, IRasterLayer {
	
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
		
	public void setMaxImgHeight(int height);
	
	public void setMaxImgWidth(int width);
	
	public IWMSDataStore getWMSDataProvider();
	
	public void setWMSDataProvider(IWMSDataStore wmsDataProvider);
	
	public int getMaxHeight();
	
	public void setMaxHeight(int height);
	
	public int getMaxWidth();
	
	public void setMaxWidth(int width);
	
	public boolean isSuitableForRequest();
	
	public  List<IWMSLayer> getLayerDefinitions();
	
	public List<WMSNamedStyle> getWmsNamedStyles();
	
	public void setWmsNamedStyles(List<WMSNamedStyle> wmsNamedStyles);
	
	public  GetMapResult getMap(ILayer[] layers, ICoordinateSystem coordinateSystem,
			IEnvelope bbox, IDimension scrDimension,
			WmsImageReturnType wmsImageReturnType, String style);
	
	public  GetMapResult getMap(String[] layers, ICoordinateSystem coordinateSystem,
			IEnvelope bbox, IDimension scrDimension,
			WmsImageReturnType wmsImageReturnType, String style);
	
	public GetMapResult getMap(List<IWMSLayer> layers, List<IWMSNamedStyle> styles, ICoordinateSystem coordinateSystem,
			IEnvelope bbox, IDimension dimension,
			WmsImageReturnType wmsImageReturnType);
	
	public  GetFeatureInfoResult getFeatureInfo(SmpDimension smpDimension,
			SmpScrPoint smpScrPoint);
	
	public BufferedImage getLayerStyleLegend(IStyle style, IDimension iconDimension, WmsImageReturnType returnType);

	public void setDisplayLegend(boolean legendNeeded);
	
	public boolean isDisplayLegend();
	
}