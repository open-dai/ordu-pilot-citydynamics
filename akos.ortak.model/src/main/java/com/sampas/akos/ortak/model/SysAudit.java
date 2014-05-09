package com.sampas.akos.ortak.model;

import com.sampas.akos.common.model.BaseObject;


public class SysAudit extends BaseObject {

	/**
	 * @uml.property  name="id"
	 */
	private Long id;

	/**
	 * Getter of the property <tt>id</tt>
	 * @return  Returns the id.
	 * @uml.property  name="id"
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter of the property <tt>id</tt>
	 * @param id  The id to set.
	 * @uml.property  name="id"
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @uml.property  name="sinifAd"
	 */
	private String sinifAd;

	/**
	 * Getter of the property <tt>sinifAd</tt>
	 * @return  Returns the sinifAd.
	 * @uml.property  name="sinifAd"
	 */
	public String getSinifAd() {
		return sinifAd;
	}

	/**
	 * Setter of the property <tt>sinifAd</tt>
	 * @param sinifAd  The sinifAd to set.
	 * @uml.property  name="sinifAd"
	 */
	public void setSinifAd(String sinifAd) {
		this.sinifAd = sinifAd;
	}

	/**
	 * @uml.property  name="aciklama"
	 */
	private String aciklama;

	/**
	 * Getter of the property <tt>aciklama</tt>
	 * @return  Returns the aciklama.
	 * @uml.property  name="aciklama"
	 */
	public String getAciklama() {
		return aciklama;
	}

	/**
	 * Setter of the property <tt>aciklama</tt>
	 * @param aciklama  The aciklama to set.
	 * @uml.property  name="aciklama"
	 */
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	/**
	 * @uml.property  name="izlenebilirEh"
	 */
	private String izlenebilirEh;

	/**
	 * Getter of the property <tt>izlenebilirEh</tt>
	 * @return  Returns the izlenebilirEh.
	 * @uml.property  name="izlenebilirEh"
	 */
	public String getIzlenebilirEh() {
		return izlenebilirEh;
	}

	/**
	 * Setter of the property <tt>izlenebilirEh</tt>
	 * @param izlenebilirEh  The izlenebilirEh to set.
	 * @uml.property  name="izlenebilirEh"
	 */
	public void setIzlenebilirEh(String izlenebilirEh) {
		this.izlenebilirEh = izlenebilirEh;
	}

	/**
	 * @uml.property  name="kaydedilebilirEh"
	 */
	private String kaydedilebilirEh;

	/**
	 * Getter of the property <tt>kaydedilebilirEh</tt>
	 * @return  Returns the kaydedilebilirEh.
	 * @uml.property  name="kaydedilebilirEh"
	 */
	public String getKaydedilebilirEh() {
		return kaydedilebilirEh;
	}

	/**
	 * Setter of the property <tt>kaydedilebilirEh</tt>
	 * @param kaydedilebilirEh  The kaydedilebilirEh to set.
	 * @uml.property  name="kaydedilebilirEh"
	 */
	public void setKaydedilebilirEh(String kaydedilebilirEh) {
		this.kaydedilebilirEh = kaydedilebilirEh;
	}

	/** 
	 * @uml.property name="kurumSabit"
	 * @uml.associationEnd inverse="sysAudit:com.sampas.akos.ortak.model.KurumSabit"
	 */
	private KurumSabit kurumSabit;

	/** 
	 * Getter of the property <tt>kurumSabit</tt>
	 * @return  Returns the kurumSabit.
	 * @uml.property  name="kurumSabit"
	 */
	public KurumSabit getKurumSabit() {
		return kurumSabit;
	}

	/** 
	 * Setter of the property <tt>kurumSabit</tt>
	 * @param kurumSabit  The kurumSabit to set.
	 * @uml.property  name="kurumSabit"
	 */
	public void setKurumSabit(KurumSabit kurumSabit) {
		this.kurumSabit = kurumSabit;
	}

}
