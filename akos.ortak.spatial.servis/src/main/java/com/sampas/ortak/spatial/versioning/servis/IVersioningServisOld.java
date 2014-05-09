package com.sampas.ortak.spatial.versioning.servis;

import java.util.Date;
import java.util.List;
import com.sampas.akos.common.model.IVersionable;
import com.sampas.ortak.spatial.versiyon.model.BaseVersionObject;
import com.sampas.socbs.core.data.IFeatureDataStore;


public interface IVersioningServisOld {

	public List<BaseVersionObject> readFeatureVersions(IVersionable versionableObject);
	
	public List<BaseVersionObject> readFeatureVersions(IVersionable versionableObject, String user);
	
	public List<BaseVersionObject> readFeatureVersions(IVersionable versionableObject, Date versionSaveDate);
	
	public List<BaseVersionObject> readFeatureVersions(IVersionable versionableObject, Date versionDateBegin, Date versionDateEnd);
	
	public List<BaseVersionObject> readFeatureVersions(IVersionable versionableObject, Long taskId);
	
	public List<BaseVersionObject> readFeatureVersions(IVersionable versionableObject, String user, Date versionSaveDate);
	
	public List<BaseVersionObject> readFeatureVersions(IVersionable versionableObject, String user,Long taskId, Date versionDateBegin, Date versionDateEnd);
	
	public boolean saveFeatureVersion(IVersionable versionableObject, String user, Date versionDate, Long taskId);
	
	public void setFeatureDataStore(IFeatureDataStore featureDataStore);
	
}
