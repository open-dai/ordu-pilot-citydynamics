package com.sampas.GeoInfoMngmnt.CityDynamics.V1;

import java.util.Date;

public class GcCollectScheduleItem {


	private int kurum_id;
	private int yili;
	private int donem;
	private int gun;
	private int mahalle_id;
	private int cadde_sokak_id;
	private Date baslama_saat;
	private Date bitis_saat;
	private String aciklama;
	public void setKurum_id(int kurum_id) {
		this.kurum_id = kurum_id;
	}
	public int getKurum_id() {
		return kurum_id;
	}
	public void setYili(int yili) {
		this.yili = yili;
	}
	public int getYili() {
		return yili;
	}
	public void setDonem(int donem) {
		this.donem = donem;
	}
	public int getDonem() {
		return donem;
	}
	public void setGun(int gun) {
		this.gun = gun;
	}
	public int getGun() {
		return gun;
	}
	public void setMahalle_id(int mahalle_id) {
		this.mahalle_id = mahalle_id;
	}
	public int getMahalle_id() {
		return mahalle_id;
	}
	public void setCadde_sokak_id(int cadde_sokak_id) {
		this.cadde_sokak_id = cadde_sokak_id;
	}
	public int getCadde_sokak_id() {
		return cadde_sokak_id;
	}
	public void setBaslama_saat(Date baslama_saat) {
		this.baslama_saat = baslama_saat;
	}
	public Date getBaslama_saat() {
		return baslama_saat;
	}
	public void setBitis_saat(Date bitis_saat) {
		this.bitis_saat = bitis_saat;
	}
	public Date getBitis_saat() {
		return bitis_saat;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	public String getAciklama() {
		return aciklama;
	}

}
