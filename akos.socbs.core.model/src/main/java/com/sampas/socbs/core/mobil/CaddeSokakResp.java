package com.sampas.socbs.core.mobil;

import java.util.Date;

public class CaddeSokakResp{

	private Long id;
	private Long parentId;
	private String parentAciklama="";	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String aciklama = "";

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentAciklama() {
		return parentAciklama;
	}

	public void setParentAciklama(String parentAciklama) {
		this.parentAciklama = parentAciklama;
	}



	
}
