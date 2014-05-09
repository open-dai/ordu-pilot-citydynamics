package com.sampas.gis.ortak.model;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.ortak.model.BagimsizKullanimDetay;
import com.sampas.akos.ortak.model.BagimsizKullanimGrup;
import com.sampas.akos.ortak.model.BagimsizKullanimSinif;
import com.sampas.akos.ortak.model.KurumSabit;


@SuppressWarnings("serial")
public class OnemliYerKriterleri extends BaseObject {

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
	 * @uml.property  name="kullanimDetayi"
	 */
	private BagimsizKullanimDetay kullanimDetayi;

	/**
	 * Getter of the property <tt>kullanimDetayi</tt>
	 * @return  Returns the kullanimDetayi.
	 * @uml.property  name="kullanimDetayi"
	 */
	public BagimsizKullanimDetay getKullanimDetayi() {
		return kullanimDetayi;
	}

	/**
	 * Setter of the property <tt>kullanimDetayi</tt>
	 * @param kullanimDetayi  The kullanimDetayi to set.
	 * @uml.property  name="kullanimDetayi"
	 */
	public void setKullanimDetayi(BagimsizKullanimDetay kullanimDetayi) {
		this.kullanimDetayi = kullanimDetayi;
	}

	/**
	 * @uml.property  name="kullanimGrubu"
	 */
	private BagimsizKullanimGrup kullanimGrubu;

	/**
	 * Getter of the property <tt>kullanimGrubu</tt>
	 * @return  Returns the kullanimGrubu.
	 * @uml.property  name="kullanimGrubu"
	 */
	public BagimsizKullanimGrup getKullanimGrubu() {
		return kullanimGrubu;
	}

	/**
	 * Setter of the property <tt>kullanimGrubu</tt>
	 * @param kullanimGrubu  The kullanimGrubu to set.
	 * @uml.property  name="kullanimGrubu"
	 */
	public void setKullanimGrubu(BagimsizKullanimGrup kullanimGrubu) {
	
		this.kullanimGrubu = kullanimGrubu;
	}

	/**
	 * @uml.property  name="kullanimSinifi"
	 */
	private BagimsizKullanimSinif kullanimSinifi;

	/**
	 * Getter of the property <tt>kullanimSinifi</tt>
	 * @return  Returns the kullanimSinifi.
	 * @uml.property  name="kullanimSinifi"
	 */
	public BagimsizKullanimSinif getKullanimSinifi() {
		return kullanimSinifi;
	}

	/**
	 * Setter of the property <tt>kullanimSinifi</tt>
	 * @param kullanimSinifi  The kullanimSinifi to set.
	 * @uml.property  name="kullanimSinifi"
	 */
	public void setKullanimSinifi(BagimsizKullanimSinif kullanimSinifi) {
		this.kullanimSinifi = kullanimSinifi;
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

}
