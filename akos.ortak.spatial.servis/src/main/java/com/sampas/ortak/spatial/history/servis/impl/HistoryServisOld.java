package com.sampas.ortak.spatial.history.servis.impl;

import java.util.Date;
import java.util.List;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.gis.ortak.servis.IGisOrtakServis;
import com.sampas.gis.ortak.tools.impl.EntityMetaData;
import com.sampas.ortak.spatial.history.dao.IHistoryDAOOld;
import com.sampas.ortak.spatial.history.model.BaseHistoryObject;
import com.sampas.socbs.base.feature.services.IFeatureFinderServices;
import com.sampas.socbs.base.history.services.IHistoryExtraAttributes;
import com.sampas.socbs.base.history.services.impl.SmpHistoryExtraAttributes;
import com.sampas.socbs.base.services.transformpersist.common.ITransformPersistExtraAttributes;
import com.sampas.socbs.base.services.transformpersist.common.ITransformPersistFeatureServices;
import com.sampas.socbs.base.services.transformpersist.common.impl.SmpTransformPersistExtraAttributes;
import com.sampas.socbs.base.services.transformpersist.common.impl.SmpTransformPersistFeatureServices;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureTypeName;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureTypeName;
import com.sampas.socbs.core.gisdatabase.tools.servis.IGisDatabaseServis;
import com.sampas.socbs.core.gisdatabase.tools.servis.impl.GisDatabaseServis;
import com.sampas.socbs.core.maplayer.IFeatureLayer;


public class HistoryServisOld {

	IGisOrtakServis gisOrtakServis = null;
	
	IFeatureDataStore featureDataStore = null;
	
	IFeatureLayerProvider featureLayerProvider = null;
	
	List<IFeatureLayer> featureLayers = null;
	
	IGisDatabaseServis gisDatabaseServis = null;
	
	IFeatureFinderServices featureFinderServis;
	
	IHistoryDAOOld historyDAOOld;
	

	public List<BaseHistoryObject> readFeatureHistory(IHistoricalable historicalableObject, String degisiklikYapan,	Date historyDateBegin, Date historyDateEnd, String degisiklikTipi) {

		return historyDAOOld.readFeatureHistory(historicalableObject, degisiklikYapan, historyDateBegin, historyDateEnd, degisiklikTipi);
	}

	public boolean saveFeatureHistory(IHistoricalable historyObject, String degisiklikYapan, Date degisiklikTarihi, String degisiklikTipi) {
	
		boolean isSuccess=false;
		
		try
		{
			Class<?> objectClass=historyObject.getClass();
		
			EntityMetaData entityMetaData = gisOrtakServis.readEntityMetaData(objectClass.getName());
		
			String tableName=entityMetaData.getDbTableName();
			
			IFeatureTypeName featureTypeName=new SmpFeatureTypeName(tableName);
			
			Long id=historyObject.getId();
			
			IFeature  feature = featureFinderServis.getFeatureByFNameAndID(this.featureDataStore, featureTypeName, id.longValue());
			
			//java.sql.Date date = new java.sql.Date(degisiklikTarihi.getTime());//  versionDate.getYear(),versionDate.getMonth(),versionDate.getDay());

			IHistoryExtraAttributes historyExtraAttributes = new SmpHistoryExtraAttributes(degisiklikYapan,degisiklikTarihi,degisiklikTipi);
			
			ITransformPersistExtraAttributes transformPersistExtraAttributes = new SmpTransformPersistExtraAttributes();
			
			transformPersistExtraAttributes.setHistoryExtraAttributes(historyExtraAttributes);
			
			ITransformPersistFeatureServices transformPersistFeatureService = new SmpTransformPersistFeatureServices(this.featureDataStore);
			
			isSuccess = transformPersistFeatureService.transformPersistFeature(feature, transformPersistExtraAttributes);
		} catch (Exception ex) {
		
			isSuccess =false;
			
			System.err.println(ex.getMessage());
		}
		return isSuccess;
	}

	public void setFeatureDataStore(IFeatureDataStore featureDataStore) {

		this.featureDataStore = featureDataStore;

		this.gisDatabaseServis = new GisDatabaseServis(featureDataStore);

	}

	
	//TODO:Getters and Setters Separator
	
	public IGisOrtakServis getGisOrtakServis() {
		return gisOrtakServis;
	}

	public void setGisOrtakServis(IGisOrtakServis gisOrtakServis) {
		this.gisOrtakServis = gisOrtakServis;
	}

	public IFeatureLayerProvider getFeatureLayerProvider() {
		return featureLayerProvider;
	}

	public void setFeatureLayerProvider(IFeatureLayerProvider featureLayerProvider) {
		this.featureLayerProvider = featureLayerProvider;
	}

	public List<IFeatureLayer> getFeatureLayers() {
		return featureLayers;
	}

	public void setFeatureLayers(List<IFeatureLayer> featureLayers) {
		this.featureLayers = featureLayers;
	}

	public IGisDatabaseServis getGisDatabaseServis() {
		return gisDatabaseServis;
	}

	public void setGisDatabaseServis(IGisDatabaseServis gisDatabaseServis) {
		this.gisDatabaseServis = gisDatabaseServis;
	}

	public IFeatureFinderServices getFeatureFinderServis() {
		return featureFinderServis;
	}

	public void setFeatureFinderServis(IFeatureFinderServices featureFinderServis) {
		this.featureFinderServis = featureFinderServis;
	}

	public IFeatureDataStore getFeatureDataStore() {
		return featureDataStore;
	}

	public IHistoryDAOOld getHistoryDAOOld() {
		return historyDAOOld;
	}

	public void setHistoryDAOOld(IHistoryDAOOld historyDAOOld) {
		this.historyDAOOld = historyDAOOld;
	}
	
}
