package com.sampas.socbs.geolsa.servis;

import java.util.List;
import com.sampas.socbs.geolsa.model.MdAppLayer;
import com.sampas.socbs.geolsa.model.MdFeatureThemeStyle;
import com.sampas.socbs.geolsa.model.MdLayerAttributeVisibleName;
import com.sampas.socbs.geolsa.model.MdRasterThemeStyle;
import com.sampas.socbs.geolsa.model.MdSmpGISApp;


public interface IGeolsaServis {

	public abstract List<MdSmpGISApp> readAllCBSUygulamalariMetadata();
	
	public abstract MdSmpGISApp readCBSUygulamalariMetadataByCriteria(MdSmpGISApp gisApplication);
	
	public MdSmpGISApp readCBSUygulamalariMetadataByCriteriaName(String gisApplicationName);
	
	public abstract void writeCBSUygulamalariMetadata(MdSmpGISApp gisApplication);
	
	public abstract void writeCBSUygulamalariLayer(MdAppLayer mdAppLayer);
	
	public abstract void updateCBSUygulamalariMetadata(MdSmpGISApp gisApplication);
	
	public abstract void updateCBSUygulamalariLayer(MdAppLayer appLayer);
	
	public abstract void deleteCBSUygulamalariMetadata(MdSmpGISApp gisApplication);
	
	public abstract void deleteCBSUygulamalariLayer(MdAppLayer appLayer);
	
	public abstract void deleteCBSUygulamalariFeatureStyle(MdFeatureThemeStyle featureStyle);	
	
	public abstract void deleteCBSUygulamalariRasterStyle(MdRasterThemeStyle rasterStyle);
	
	public abstract void deleteCBSUygulamalariLayerAttribute(MdLayerAttributeVisibleName layerAttribute);
	
}
