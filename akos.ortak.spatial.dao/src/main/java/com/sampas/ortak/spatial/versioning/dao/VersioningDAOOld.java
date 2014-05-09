package com.sampas.ortak.spatial.versioning.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.sampas.akos.common.dao.BaseDaoSupport;
import com.sampas.akos.common.model.IVersionable;
import com.sampas.ortak.spatial.versiyon.model.BaseVersionObject;


@SuppressWarnings("unchecked")
public class VersioningDAOOld extends BaseDaoSupport implements IVersioningDAOOld {

	public List<BaseVersionObject> readFeatureVersions(IVersionable versionableObject, String user, Long taskId, Date versionDateBegin, Date versionDateEnd) {
		
		List<BaseVersionObject> toReturn = null;
		
		if (versionableObject != null && versionableObject.getId() != null && versionableObject.getId() > 0){
			
			List<Object> params = new ArrayList<Object>();
			
			params.add(versionableObject.getId());
			
			String objectClassName = versionableObject.getClass().getSimpleName();
			
			String versionObjectClassName = objectClassName +"Vrs";
			
			String hql = "Select vrs from " + versionObjectClassName + " as vrs where vrs.nesneId=?";  
			
			if (user != null && !user.trim().equals("")){
				
				hql += " AND vrs.islemYapan=?";
				
				params.add(user);
			}
			if (taskId != null && taskId.longValue() > 0){
				
				hql += " AND vrs.kullaniciOzelKod=?";
				
				params.add(taskId);
			}
			if (versionDateBegin!=null){
				
				hql += " AND vrs.versiyonTarihi>=?";
				
				params.add(versionDateBegin);
			}
			if (versionDateEnd!=null){
				hql += " AND vrs.versiyonTarihi<=?";
				
				params.add(versionDateEnd);
			}
			try {
			
				toReturn=(List<BaseVersionObject>)super.readDBObjectByHQL(hql,params.toArray());
			} catch (Exception e) {
			
				toReturn = null;
				
				e.printStackTrace();
			}
		} else {
			
			System.err.println("Error on BaseVersionObject VersionObjectId value must greater than zero !");
		}
		return toReturn;
	}
	
}
