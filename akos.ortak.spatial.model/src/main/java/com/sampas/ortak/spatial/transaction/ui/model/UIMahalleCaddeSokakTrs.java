package com.sampas.ortak.spatial.transaction.ui.model;

import com.sampas.ortak.spatial.transaction.model.BaseTransactionObject;
import com.sampas.ortak.spatial.transaction.model.MahalleCaddeSokakTrs;


@SuppressWarnings("serial")
public class UIMahalleCaddeSokakTrs extends BaseTransactionObject {

	private String aciklama;
	
	private MahalleCaddeSokakTrs distStreetTrs;

	
	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public MahalleCaddeSokakTrs getDistStreetTrs() {
		return distStreetTrs;
	}

	public void setDistStreetTrs(MahalleCaddeSokakTrs distStreetTrs) {
		this.distStreetTrs = distStreetTrs;
	}

}
