package com.sampas.gis.ortak.model;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;


@SuppressWarnings("serial")
public class PlanNot extends BaseObject implements IVersionable ,ITransactionable,IHistoricalable {

	private Long id;
	
	private String aciklama;
	
	private Long sira;
	
	private String notTipi;
	
	private String baslik;
	
	private String altBaslik;
	
	private String defaultEh;
	
	private Plan plan;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public Long getSira() {
		return sira;
	}

	public void setSira(Long sira) {
		this.sira = sira;
	}

	public String getNotTipi() {
		return notTipi;
	}

	public void setNotTipi(String notTipi) {
		this.notTipi = notTipi;
	}

	public String getBaslik() {
		return baslik;
	}

	public void setBaslik(String baslik) {
		this.baslik = baslik;
	}

	public String getAltBaslik() {
		return altBaslik;
	}

	public void setAltBaslik(String altBaslik) {
		this.altBaslik = altBaslik;
	}

	public String getDefaultEh() {
		return defaultEh;
	}

	public void setDefaultEh(String defaultEh) {
		this.defaultEh = defaultEh;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

}
