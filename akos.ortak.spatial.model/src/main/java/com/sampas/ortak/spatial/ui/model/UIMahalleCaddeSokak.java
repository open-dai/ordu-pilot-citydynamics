package com.sampas.ortak.spatial.ui.model;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.ortak.model.MahalleCaddeSokak;


@SuppressWarnings("serial")
public class UIMahalleCaddeSokak extends BaseObject {


	public Long getId() {
		return 0L;
	}
	
	private String aciklama;
	
	private MahalleCaddeSokak distStreet;

	
	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public MahalleCaddeSokak getDistStreet() {
		return distStreet;
	}

	public void setDistStreet(MahalleCaddeSokak distStreet) {
		this.distStreet = distStreet;
	}

	

}
