package com.sampas.socbs.geolsa.servis.impl;

import java.util.List;
import com.sampas.socbs.geolsa.dao.IGeolsaDAO;
import com.sampas.socbs.geolsa.model.MdAppLayer;
import com.sampas.socbs.geolsa.model.MdFeatureThemeStyle;
import com.sampas.socbs.geolsa.model.MdLayerAttributeVisibleName;
import com.sampas.socbs.geolsa.model.MdRasterThemeStyle;
import com.sampas.socbs.geolsa.model.MdSmpGISApp;
import com.sampas.socbs.geolsa.servis.IGeolsaServis;


public class GeolsaServis implements IGeolsaServis {

	private IGeolsaDAO geolsaDAO;
	
	public void writeCBSUygulamalariMetadata(MdSmpGISApp gisApplication) {
		
		geolsaDAO.writeCBSUygulamalariMetadata(gisApplication);
	}
	
	public void writeCBSUygulamalariLayer(MdAppLayer mdAppLayer) {

		geolsaDAO.writeCBSUygulamalariLayer(mdAppLayer);
	}
	
	public List<MdSmpGISApp> readAllCBSUygulamalariMetadata() {
		
		return (geolsaDAO.readAllCBSUygulamalariMetadata());
	}
	
	public MdSmpGISApp readCBSUygulamalariMetadataByCriteria(MdSmpGISApp gisApplication) {
		
		return (geolsaDAO.readCBSUygulamalariMetadataByCriteria(gisApplication));
	}
	
	public MdSmpGISApp readCBSUygulamalariMetadataByCriteriaName(String gisApplicationName) {
		
		return (geolsaDAO.readCBSUygulamalariMetadataByCriteriaName(gisApplicationName));
	}
	
	public void updateCBSUygulamalariMetadata(MdSmpGISApp gisApplication) {
		
		geolsaDAO.updateCBSUygulamalariMetadata(gisApplication);
	}

	public void updateCBSUygulamalariLayer(MdAppLayer appLayer) {

		geolsaDAO.updateCBSUygulamalariLayer(appLayer);
	}

	public void deleteCBSUygulamalariMetadata(MdSmpGISApp gisApplication) {

		geolsaDAO.deleteCBSUygulamalariMetadata(gisApplication);
	}
	
	public void deleteCBSUygulamalariLayer(MdAppLayer appLayer) {
		
		geolsaDAO.deleteCBSUygulamalariLayer(appLayer);
	}

	public void deleteCBSUygulamalariFeatureStyle(MdFeatureThemeStyle featureStyle) {

		geolsaDAO.deleteCBSUygulamalariFeatureStyle(featureStyle);
	}

	public void deleteCBSUygulamalariRasterStyle(MdRasterThemeStyle rasterStyle) {

		geolsaDAO.deleteCBSUygulamalariRasterStyle(rasterStyle);
	}

	public void deleteCBSUygulamalariLayerAttribute(MdLayerAttributeVisibleName layerAttribute) {

		geolsaDAO.deleteCBSUygulamalariLayerAttribute(layerAttribute);
	}

	public IGeolsaDAO getGeolsaDAO() {
		return geolsaDAO;
	}

	public void setGeolsaDAO(IGeolsaDAO geolsaDAO) {
		this.geolsaDAO = geolsaDAO;
	}

}
