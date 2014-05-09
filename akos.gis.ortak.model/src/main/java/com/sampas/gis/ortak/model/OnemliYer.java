package com.sampas.gis.ortak.model;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;
import com.sampas.akos.ortak.model.BagimsizKullanimDetay;
import com.sampas.akos.ortak.model.BagimsizKullanimGrup;
import com.sampas.akos.ortak.model.BagimsizKullanimSinif;
import com.sampas.akos.ortak.model.Bagimsiz;
import com.sampas.akos.ortak.model.Grafik;
import com.sampas.akos.ortak.model.KurumSabit;


@SuppressWarnings("serial")
public class OnemliYer extends BaseObject implements IVersionable ,ITransactionable,IHistoricalable{

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
	 * @uml.property  name="kullanimDetayi"
	 */
	private BagimsizKullanimDetay kullanimDetayi;

	/** 
	 * Getter of the property <tt>kullanimDetay</tt>
	 * @return  Returns the kullanimDetay.
	 * @uml.property  name="kullanimDetayi"
	 */
	public BagimsizKullanimDetay getKullanimDetayi() {
		return kullanimDetayi;
	}

	/** 
	 * Setter of the property <tt>kullanimDetay</tt>
	 * @param kullanimDetay  The kullanimDetay to set.
	 * @uml.property  name="kullanimDetayi"
	 */
	public void setKullanimDetayi(BagimsizKullanimDetay kullanimDetayi) {
		this.kullanimDetayi = kullanimDetayi;
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
	 * @uml.property  name="bagimsiz"
	 */
	private Bagimsiz bagimsiz;

	/**
	 * Getter of the property <tt>bagimsiz</tt>
	 * @return  Returns the bagimsiz.
	 * @uml.property  name="bagimsiz"
	 */
	public Bagimsiz getBagimsiz() {
		return bagimsiz;
	}

	/**
	 * Setter of the property <tt>bagimsiz</tt>
	 * @param bagimsiz  The bagimsiz to set.
	 * @uml.property  name="bagimsiz"
	 */
	public void setBagimsiz(Bagimsiz bagimsiz) {
		this.bagimsiz = bagimsiz;
	}

	/**
	 * @uml.property  name="grafik"
	 */
	private Grafik grafik;

	/**
	 * Getter of the property <tt>grafik</tt>
	 * @return  Returns the grafik.
	 * @uml.property  name="grafik"
	 */
	public Grafik getGrafik() {
		return grafik;
	}

	/**
	 * Setter of the property <tt>grafik</tt>
	 * @param grafik  The grafik to set.
	 * @uml.property  name="grafik"
	 */
	public void setGrafik(Grafik grafik) {
		this.grafik = grafik;
	}

	/**
	 * @uml.property  name="aciklama"
	 */
	private String aciklama = "";

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
	 * @uml.property  name="telefon"
	 */
	private String telefon = "";

	/**
	 * Getter of the property <tt>telefon</tt>
	 * @return  Returns the telefon.
	 * @uml.property  name="telefon"
	 */
	public String getTelefon() {
		return telefon;
	}

	/**
	 * Setter of the property <tt>telefon</tt>
	 * @param telefon  The telefon to set.
	 * @uml.property  name="telefon"
	 */
	public void setTelefon(String telefon) {
		this.telefon = telefon;
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
