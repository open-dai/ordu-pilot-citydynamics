package com.sampas.socbs.core.data;

import java.awt.image.BufferedImage;
import java.util.List;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.data.impl.SmpCapabilities;
import com.sampas.socbs.core.data.wms.impl.GetFeatureInfoResult;
import com.sampas.socbs.core.data.wms.impl.GetMapResult;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsImageReturnType;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsResponseType;
import com.sampas.socbs.core.dataset.feature.impl.SmpMaxFeatureCount;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.geometry.impl.SmpDimension;
import com.sampas.socbs.core.geometry.impl.SmpScrDimension;
import com.sampas.socbs.core.geometry.impl.SmpScrPoint;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.IWMSLayer;
import com.sampas.socbs.core.maplayer.impl.SmpLayer;
import com.sampas.socbs.core.style.IWMSNamedStyle;
import com.sampas.socbs.core.symbology.IStyle;

public interface IWMSDataStore {

	public SmpCapabilities getCapabilities();
	
	public List<IWMSLayer> getLayerDefinitions();
	
	public List<IWMSLayer> getLayerDefinitions(ILayerNames layerNames);
	
	public String[] getLayerNames();
	
	public GetMapResult getMap(SmpLayer[] layers, SmpCoordinateSystem smpCoordinateSystem,
			SmpBoundingRectangle bbox, SmpScrDimension smpScrDimension,
			WmsImageReturnType wmsImageReturnType, String style);
	
	public GetMapResult getMap(String[] layers, SmpCoordinateSystem smpCoordinateSystem,
			SmpBoundingRectangle bbox, SmpScrDimension smpScrDimension,
			WmsImageReturnType wmsImageReturnType, String style);
	
	public GetFeatureInfoResult getFeatureInfo(SmpLayer smpLayer,
			SmpBoundingRectangle bbox, SmpCoordinateSystem smpCoordinateSystem, SmpDimension smpDimension,
			SmpScrPoint smpScrPoint, WmsResponseType wmsResponseType,
			WmsImageReturnType wmsImageReturnType,
			SmpMaxFeatureCount maxFeatureCount);
	
	public GetFeatureInfoResult getFeatureInfo(String layer,
			SmpBoundingRectangle bbox, SmpCoordinateSystem smpCoordinateSystem, SmpDimension smpDimension,
			SmpScrPoint smpScrPoint, WmsResponseType wmsResponseType,
			WmsImageReturnType wmsImageReturnType,
			SmpMaxFeatureCount maxFeatureCount, String style);
	
	public GetMapResult getMap(List<IWMSLayer> layers, List<IWMSNamedStyle> styles, ICoordinateSystem coordinateSystem,
			IEnvelope bbox, IDimension dimension,
			WmsImageReturnType wmsImageReturnType);
	
	public BufferedImage getLayerStyleLegend(IWMSLayer wmsLayer, IStyle style, IDimension iconDimension, WmsImageReturnType returnType);
		
}
