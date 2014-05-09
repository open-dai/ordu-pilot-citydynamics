package com.sampas.gis.ortak.model;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.ortak.model.KurumSabit;


@SuppressWarnings("serial")
public class PlanTurleri extends BaseObject {

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
	 * @uml.property  name="minOlcek"
	 */
	private long minOlcek;

	/**
	 * Getter of the property <tt>minOlcek</tt>
	 * @return  Returns the minOlcek.
	 * @uml.property  name="minOlcek"
	 */
	public long getMinOlcek() {
		return minOlcek;
	}

	/**
	 * Setter of the property <tt>minOlcek</tt>
	 * @param minOlcek  The minOlcek to set.
	 * @uml.property  name="minOlcek"
	 */
	public void setMinOlcek(long minOlcek) {
		this.minOlcek = minOlcek;
	}

	/**
	 * @uml.property  name="maxOlcek"
	 */
	private long maxOlcek;

	/**
	 * Getter of the property <tt>maxOlcek</tt>
	 * @return  Returns the maxOlcek.
	 * @uml.property  name="maxOlcek"
	 */
	public long getMaxOlcek() {
		return maxOlcek;
	}

	/**
	 * Setter of the property <tt>maxOlcek</tt>
	 * @param maxOlcek  The maxOlcek to set.
	 * @uml.property  name="maxOlcek"
	 */
	public void setMaxOlcek(long maxOlcek) {
		this.maxOlcek = maxOlcek;
	}

	/**
	 * @uml.property  name="planTuruAciklama"
	 */
	private String planTuruAciklama = "";

	/** 
	 * Getter of the property <tt>planTuru</tt>
	 * @return  Returns the planTuru.
	 * @uml.property  name="planTuruAciklama"
	 */
	public String getPlanTuruAciklama() {
		return planTuruAciklama;
	}

	/** 
	 * Setter of the property <tt>planTuru</tt>
	 * @param planTuru  The planTuru to set.
	 * @uml.property  name="planTuruAciklama"
	 */
	public void setPlanTuruAciklama(String planTuruAciklama) {
		this.planTuruAciklama = planTuruAciklama;
	}

	/**
	 * @uml.property  name="kurumSabit"
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
	
	/**
	 * @uml.property  name="aktifEh"
	 */
	private String kullaniliyorEh = "";

	/**
	 * Getter of the property <tt>kullaniliyorEh</tt>
	 * @return  Returns the kullaniliyorEh.
	 * @uml.property  name="kullaniliyorEh"
	 */
	public String getKullaniliyorEh() {
		return kullaniliyorEh;
	}

	/**
	 * Setter of the property <tt>kullaniliyorEh</tt>
	 * @param kullaniliyorEh  The kullaniliyorEh to set.
	 * @uml.property  name="kullaniliyorEh"
	 */
	public void setKullaniliyorEh(String kullaniliyorEh) {
		this.kullaniliyorEh = kullaniliyorEh;
	}

}
