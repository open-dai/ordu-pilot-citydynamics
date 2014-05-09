package com.sampas.socbs.geolsa.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.sampas.akos.common.dao.BaseDaoSupport;
import com.sampas.socbs.geolsa.dao.IGeolsaDAO;
import com.sampas.socbs.geolsa.model.MdAppLayer;
import com.sampas.socbs.geolsa.model.MdFeatureThemeStyle;
import com.sampas.socbs.geolsa.model.MdLayerAttributeVisibleName;
import com.sampas.socbs.geolsa.model.MdRasterThemeStyle;
import com.sampas.socbs.geolsa.model.MdSmpGISApp;


@SuppressWarnings("unchecked")
public class GeolsaDAOImpl extends BaseDaoSupport implements IGeolsaDAO {

	public void writeCBSUygulamalariMetadata(MdSmpGISApp gisApplication) {
		
		if (gisApplication != null) {
			
			super.saveOrUpdate(gisApplication);			
		}
	}
	
	public void writeCBSUygulamalariLayer(MdAppLayer mdAppLayer) {
		
		if (mdAppLayer != null) {
			
			super.saveOrUpdate(mdAppLayer);			
		}
	}
	
	public List<MdSmpGISApp> readAllCBSUygulamalariMetadata() {
		
		try {
			
			Criteria criteria = super.getSession().createCriteria(MdSmpGISApp.class, "ga");
		
			return criteria.list();
		} catch (Exception ex) {
			
			System.out.println("Error on reading Application metadata");
			return null;
		}
	}
	
	public MdSmpGISApp readCBSUygulamalariMetadataByCriteria(MdSmpGISApp gisApplication) {
		
		System.out.println("ORNEK ::: 1");
		
		if (gisApplication != null) {
			
			Criteria criteria = super.getSession().createCriteria(MdSmpGISApp.class, "ga");
			
			if (gisApplication.getOid() > 0) {
				
				criteria.add(Restrictions.eq("ga.oid", gisApplication.getOid()));
			}
			if (gisApplication.getAppName() != null && !gisApplication.getAppName().equals("")) {
				
				criteria.add(Restrictions.eq("ga.appName", gisApplication.getAppName()));
			}
			List<MdSmpGISApp> applications = criteria.list();
			
			if (applications.size() > 0) {
				
				return applications.get(0);
			}
			System.out.println("ORNEK ::: 2");
			return null;
		} 
		else {
			return null;
		}
	}
	
	public MdSmpGISApp readCBSUygulamalariMetadataByCriteriaName(String gisApplicationName) {
		
		if (gisApplicationName != null && gisApplicationName != "") {
			
			Criteria criteria = super.getSession().createCriteria(MdSmpGISApp.class, "ga");
			
			criteria.add(Restrictions.eq("ga.appName", gisApplicationName));
			
			List<MdSmpGISApp> applications = criteria.list();
			
			if (applications.size() > 0) {
				
				return applications.get(0);
			}
			return null;
		} 
		else {
			return null;
		}
	}

	public void updateCBSUygulamalariMetadata(MdSmpGISApp gisApplication) {
		
		if (gisApplication != null && gisApplication.getOid() > 0) {
			
			super.saveOrUpdate(gisApplication);
			
		} else {
			
			System.out.println("For write new metadata please use writeCBSUygulamalari function");
		}
	}
	
	public void updateCBSUygulamalariLayer(MdAppLayer appLayer) {
		
		if (appLayer != null && appLayer.getOid() > 0) {
			
			super.saveOrUpdate(appLayer);
			
		} else {
			
			System.out.println("For write new metadata please use writeCBSUygulamalari function");
		}
	}
	
	public void deleteCBSUygulamalariMetadata(MdSmpGISApp gisApplication) {
		
		if (gisApplication != null) {
			
			super.deleteDBObject(gisApplication);			
		}
	}
	
	public void deleteCBSUygulamalariLayer(MdAppLayer appLayer) {
		
		if (appLayer != null) {
			
			super.deleteDBObject(appLayer);			
		}
	}
	
	public void deleteCBSUygulamalariFeatureStyle(MdFeatureThemeStyle featureStyle) {
		
		if (featureStyle != null) {
			
			super.deleteDBObject(featureStyle);			
		}
	}
	
	public void deleteCBSUygulamalariRasterStyle(MdRasterThemeStyle rasterStyle) {
		
		if (rasterStyle != null) {
			
			super.deleteDBObject(rasterStyle);			
		}
	}
	
	public void deleteCBSUygulamalariLayerAttribute(MdLayerAttributeVisibleName layerAttribute) {
		
		if (layerAttribute != null) {
			
			super.deleteDBObject(layerAttribute);			
		}
	}
	
}
