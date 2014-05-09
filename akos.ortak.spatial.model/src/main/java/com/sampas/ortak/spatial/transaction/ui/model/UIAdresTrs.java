package com.sampas.ortak.spatial.transaction.ui.model;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.ortak.spatial.transaction.model.AdresTrs;


@SuppressWarnings("serial")
public class UIAdresTrs extends BaseObject{

	private AdresTrs adresTrs;
	
	private String aciklama;

	public AdresTrs getAdresTrs() {
		return adresTrs;
		
	}

	public void setAdresTrs(AdresTrs adresTrs) {
		this.adresTrs = adresTrs;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	
}
