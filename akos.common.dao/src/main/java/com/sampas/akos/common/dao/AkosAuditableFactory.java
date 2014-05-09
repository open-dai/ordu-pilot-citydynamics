package com.sampas.akos.common.dao;

import java.io.Serializable;

public interface AkosAuditableFactory {

	public Object getDBObject(Object entity, Serializable id);
	
	public boolean checkDirty(Object entity, Object dbObject);
		
	public void saveDBObject(Object entity);

	public Integer auditEntity(Object entity, String operation);
	
	public boolean getHistorical(String a);
}
