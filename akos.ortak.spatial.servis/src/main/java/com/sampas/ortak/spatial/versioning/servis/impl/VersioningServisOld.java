package com.sampas.ortak.spatial.versioning.servis.impl;

import java.util.Date;
import java.util.List;
import com.sampas.akos.common.model.IVersionable;
import com.sampas.gis.ortak.servis.IGisOrtakServis;
import com.sampas.gis.ortak.tools.impl.EntityMetaData;
import com.sampas.ortak.spatial.versioning.dao.IVersioningDAOOld;
import com.sampas.ortak.spatial.versiyon.model.BaseVersionObject;
import com.sampas.socbs.base.feature.services.IFeatureFinderServices;
import com.sampas.socbs.base.services.transformpersist.common.ITransformPersistExtraAttributes;
import com.sampas.socbs.base.services.transformpersist.common.ITransformPersistFeatureServices;
import com.sampas.socbs.base.services.transformpersist.common.impl.SmpTransformPersistExtraAttributes;
import com.sampas.socbs.base.services.transformpersist.common.impl.SmpTransformPersistFeatureServices;
import com.sampas.socbs.base.versioning.services.IVersionExtraAttributes;
import com.sampas.socbs.base.versioning.services.impl.SmpVersionExtraAttributes;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureTypeName;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureTypeName;
import com.sampas.socbs.core.gisdatabase.tools.servis.IGisDatabaseServis;
import com.sampas.socbs.core.gisdatabase.tools.servis.impl.GisDatabaseServis;
import com.sampas.socbs.core.maplayer.IFeatureLayer;


public class VersioningServisOld {

	IGisOrtakServis searchService = null;
	
	IFeatureDataStore featureDataStore = null;
	
	IFeatureLayerProvider featureLayerProvider = null;
	
	List<IFeatureLayer> featureLayers = null;
	
	IGisDatabaseServis gisDatabaseServis = null;
	
	IFeatureFinderServices featureFinderServis;
	
	IVersioningDAOOld versioningDaoOld;
	
	
	public boolean saveFeatureVersion(IVersionable versionableObject, String user, Date versionDate, Long taskId) {
		
		boolean isSuccess=false;
		
		try
		{
			Class<?> objectClass = versionableObject.getClass();
		
			EntityMetaData entityMetaData = searchService.readEntityMetaData(objectClass.getName());
		
			String tableName=entityMetaData.getDbTableName();
			
			IFeatureTypeName featureTypeName=new SmpFeatureTypeName(tableName);
			
			Long id=versionableObject.getId();
			
			IFeature  feature=featureFinderServis.getFeatureByFNameAndID(this.featureDataStore, featureTypeName, id.longValue());
			
			java.sql.Date date = new java.sql.Date(versionDate.getTime());//  versionDate.getYear(),versionDate.getMonth(),versionDate.getDay());
//			date.setHours(versionDate.getHours());
//			date.setMinutes(versionDate.getMinutes());
//			date.setSeconds(versionDate.getSeconds());
			IVersionExtraAttributes versionExtraAttributes = new SmpVersionExtraAttributes(taskId, user, date);
			
			ITransformPersistExtraAttributes transformPersistExtraAttributes = new SmpTransformPersistExtraAttributes();
			
			transformPersistExtraAttributes.setVersionExtraAttributes(versionExtraAttributes);
			
			ITransformPersistFeatureServices transformPersistFeatureService = new SmpTransformPersistFeatureServices(this.featureDataStore);
			
			isSuccess = transformPersistFeatureService.transformPersistFeature(feature, transformPersistExtraAttributes);
		}
		catch(Exception ex){
		
			isSuccess = false;
			
			System.err.println(ex.getMessage());
		}
		
		return isSuccess;
	}

	public List<BaseVersionObject> readFeatureVersions(
			IVersionable versionableObject) {
		return versioningDaoOld.readFeatureVersions(versionableObject, null,null, null, null);
	}

	public List<BaseVersionObject> readFeatureVersions(
			IVersionable versionableObject, String user) {
		return versioningDaoOld.readFeatureVersions(versionableObject, user,null, null, null);
		
	}

	public List<BaseVersionObject> readFeatureVersions(
			IVersionable versionableObject, Date versionSaveDate) {
		return versioningDaoOld.readFeatureVersions(versionableObject, null,null, versionSaveDate, null);
	}

	public List<BaseVersionObject> readFeatureVersions(
			IVersionable versionableObject, Date versionDateBegin,
			Date versionDateEnd) {
		return versioningDaoOld.readFeatureVersions(versionableObject, null,null, versionDateBegin, versionDateEnd);
		
	}

	public List<BaseVersionObject> readFeatureVersions(
			IVersionable versionableObject, Long taskId) {
		return versioningDaoOld.readFeatureVersions(versionableObject, null,taskId, null, null);
	}

	public List<BaseVersionObject> readFeatureVersions(
			IVersionable versionableObject, String user, Date versionSaveDate) {
		return versioningDaoOld.readFeatureVersions(versionableObject, user,null, versionSaveDate, null);
	}

	public List<BaseVersionObject> readFeatureVersions(
			IVersionable versionableObject, String user,Long taskId, Date versionDateBegin,
			Date versionDateEnd) {
		return versioningDaoOld.readFeatureVersions(versionableObject, user,taskId, versionDateBegin, versionDateEnd);
	}

	public void setFeatureDataStore(IFeatureDataStore featureDataStore) {
		this.featureDataStore=featureDataStore;
		this.gisDatabaseServis = new GisDatabaseServis(featureDataStore);
	}

	public IFeatureFinderServices getFeatureFinderServis() {
		return featureFinderServis;
	}

	public void setFeatureFinderServis(IFeatureFinderServices featureFinderServis) {
		this.featureFinderServis = featureFinderServis;
	}

	public IVersioningDAOOld getVersioningDaoOld() {
		return versioningDaoOld;
	}

	public void setVersioningDaoOld(IVersioningDAOOld versioningDaoOld) {
		this.versioningDaoOld = versioningDaoOld;
	}
	
}
