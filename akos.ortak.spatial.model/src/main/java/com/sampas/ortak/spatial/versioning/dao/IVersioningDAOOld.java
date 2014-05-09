package com.sampas.ortak.spatial.versioning.dao;

import java.util.Date;
import java.util.List;
import com.sampas.akos.common.model.IVersionable;
import com.sampas.ortak.spatial.versiyon.model.BaseVersionObject;


public interface IVersioningDAOOld {

	public List<BaseVersionObject> readFeatureVersions(IVersionable versionableObject, String user,Long taskId, Date versionDateBegin, Date versionDateEnd);
	
}
