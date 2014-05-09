package com.sampas.socbs.core.data.impl;

import java.util.List;

import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.data.IWMSDataStore;
import com.sampas.socbs.core.data.wms.impl.GetFeatureInfoResult;
import com.sampas.socbs.core.data.wms.impl.GetMapResult;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsImageReturnType;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsResponseType;
import com.sampas.socbs.core.dataset.feature.impl.SmpMaxFeatureCount;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.geometry.impl.SmpDimension;
import com.sampas.socbs.core.geometry.impl.SmpScrDimension;
import com.sampas.socbs.core.geometry.impl.SmpScrPoint;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.IWMSLayer;
import com.sampas.socbs.core.maplayer.impl.SmpLayer;

public abstract class AWMSDataProvider implements IWMSDataStore {
	
	public abstract SmpCapabilities getCapabilities();
	
	public abstract List<IWMSLayer> getLayerDefinitions();
	
	public abstract List<IWMSLayer> getLayerDefinitions(ILayerNames layerNames);
	
	public abstract String[] getLayerNames();
	
	public abstract GetMapResult getMap(SmpLayer[] layers, SmpCoordinateSystem smpCoordinateSystem,
			SmpBoundingRectangle bbox, SmpScrDimension smpScrDimension,
			WmsImageReturnType wmsImageReturnType, String style);
	
	public abstract GetMapResult getMap(String[] layers, SmpCoordinateSystem smpCoordinateSystem,
			SmpBoundingRectangle bbox, SmpScrDimension smpScrDimension,
			WmsImageReturnType wmsImageReturnType, String style);
	
	public abstract GetFeatureInfoResult getFeatureInfo(SmpLayer smpLayer,
			SmpBoundingRectangle bbox, SmpCoordinateSystem smpCoordinateSystem, SmpDimension smpDimension,
			SmpScrPoint smpScrPoint, WmsResponseType wmsResponseType,
			WmsImageReturnType wmsImageReturnType,
			SmpMaxFeatureCount maxFeatureCount);
	
	public abstract GetFeatureInfoResult getFeatureInfo(String layer,
			SmpBoundingRectangle bbox, SmpCoordinateSystem smpCoordinateSystem, SmpDimension smpDimension,
			SmpScrPoint smpScrPoint, WmsResponseType wmsResponseType,
			WmsImageReturnType wmsImageReturnType,
			SmpMaxFeatureCount maxFeatureCount, String style);
	

}
