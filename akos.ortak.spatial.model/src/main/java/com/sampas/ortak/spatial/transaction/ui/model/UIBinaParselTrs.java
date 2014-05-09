package com.sampas.ortak.spatial.transaction.ui.model;

import com.sampas.akos.ortak.model.KadastroParsel;
import com.sampas.ortak.spatial.transaction.model.BinaParselTrs;

public class UIBinaParselTrs {

	BinaParselTrs binaParselTrs;
	
	KadastroParsel kadastroParsel;
	
	public BinaParselTrs getBinaParselTrs() {
		return binaParselTrs;
	}

	public void setBinaParselTrs(BinaParselTrs binaParselTrs) {
		this.binaParselTrs = binaParselTrs;
	}

	public KadastroParsel getKadastroParsel() {
		return kadastroParsel;
	}

	public void setKadastroParsel(KadastroParsel kadastroParsel) {
		this.kadastroParsel = kadastroParsel;
	}
	
	
}
