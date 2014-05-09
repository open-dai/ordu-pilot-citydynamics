package com.sampas.akos.common.dao;

import org.hibernate.envers.RevisionType;

public class BaseRevisionObject {
	private Object object;
	private AkosRevisionEntity revEntity;
	private RevisionType revType;

	public BaseRevisionObject(Object object, AkosRevisionEntity revEntity,
			RevisionType revType) {
		this.object = object;
		this.revEntity = revEntity;
		this.revType = revType;
	}

	public BaseRevisionObject(Object[] obj) {
		if (obj != null && obj.length == 3) {
			this.object = obj[0];
			this.revEntity = (AkosRevisionEntity) obj[1];
			this.revType = (RevisionType) obj[2];
		}
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public AkosRevisionEntity getRevEntity() {
		return revEntity;
	}

	public void setRevEntity(AkosRevisionEntity revEntity) {
		this.revEntity = revEntity;
	}

	public RevisionType getRevType() {
		return revType;
	}

	public void setRevType(RevisionType revType) {
		this.revType = revType;
	}
}
