package com.sampas.akos.ortak.model;

import com.sampas.akos.common.model.BaseObject;

public class Bolge extends BaseObject {

	private Long bolgeKodu;

	private String aciklama;

	private KurumSabit kurumSabit;

	public Long getBolgeKodu() {
		return bolgeKodu;
	}

	public void setBolgeKodu(Long bolgeKodu) {
		this.bolgeKodu = bolgeKodu;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public KurumSabit getKurumSabit() {
		return kurumSabit;
	}

	public void setKurumSabit(KurumSabit kurumSabit) {
		this.kurumSabit = kurumSabit;
	}

}
