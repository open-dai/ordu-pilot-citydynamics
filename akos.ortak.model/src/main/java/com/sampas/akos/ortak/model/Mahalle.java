package com.sampas.akos.ortak.model;

import java.util.Date;

import org.hibernate.envers.Audited;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;


@SuppressWarnings("serial")
@Audited
public class Mahalle extends BaseObject implements IVersionable, ITransactionable, IHistoricalable{

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
	 * @uml.property name="koy"
	 */
	private String koy = "";

	/** 
	 * Getter of the property <tt>koy</tt>
	 * @return  Returns the koy.
	 * @uml.property  name="koy"
	 */
	public String getKoy() {
		return koy;
	}

	/**
	 * @uml.property  name="tuikMahalleKod"
	 */
	private Long tuikMahalleKod;

	/**
	 * Getter of the property <tt>tuikMahalleKod</tt>
	 * @return  Returns the tuikMahalleKod.
	 * @uml.property  name="tuikMahalleKod"
	 */
	public Long getTuikMahalleKod() {
		return tuikMahalleKod;
	}

	/**
	 * Setter of the property <tt>tuikMahalleKod</tt>
	 * @param tuikMahalleKod  The tuikMahalleKod to set.
	 * @uml.property  name="tuikMahalleKod"
	 */
	public void setTuikMahalleKod(Long tuikMahalleKod) {
		this.tuikMahalleKod = tuikMahalleKod;
	}

	/**
	 * @uml.property  name="tuikMahalleTanitimNo"
	 */
	private Long tuikMahalleTanitimNo;

	/**
	 * Getter of the property <tt>tuikMahalleTanitimNo</tt>
	 * @return  Returns the tuikMahalleTanitimNo.
	 * @uml.property  name="tuikMahalleTanitimNo"
	 */
	public Long getTuikMahalleTanitimNo() {
		return tuikMahalleTanitimNo;
	}

	/**
	 * Setter of the property <tt>tuikMahalleTanitimNo</tt>
	 * @param tuikMahalleTanitimNo  The tuikMahalleTanitimNo to set.
	 * @uml.property  name="tuikMahalleTanitimNo"
	 */
	public void setTuikMahalleTanitimNo(Long tuikMahalleTanitimNo) {
		this.tuikMahalleTanitimNo = tuikMahalleTanitimNo;
	}

	/** 
	 * Setter of the property <tt>koy</tt>
	 * @param koy  The koy to set.
	 * @uml.property  name="koy"
	 */
	public void setKoy(String koy) {
		this.koy = koy;
	}

	/**
	 * @uml.property  name="kurumSabit"
	 * @uml.associationEnd  inverse="mahalle:com.sampas.akos.ortak.model.KurumSabit"
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
	private String aktifEh;

	/**
	 * Getter of the property <tt>aktifEh</tt>
	 * @return  Returns the aktifEh.
	 * @uml.property  name="aktifEh"
	 */
	public String getAktifEh() {
		return aktifEh;
	}

	/**
	 * Setter of the property <tt>aktifEh</tt>
	 * @param aktifEh  The aktifEh to set.
	 * @uml.property  name="aktifEh"
	 */
	public void setAktifEh(String aktifEh) {
		this.aktifEh = aktifEh;
	}
	
	/**
	 * @uml.property  name="acilisTarih"
	 */
	private Date acilisTarih;

	/**
	 * Getter of the property <tt>acilisTarih</tt>
	 * @return  Returns the acilisTarih.
	 * @uml.property  name="acilisTarih"
	 */
	public Date getAcilisTarih() {
		return acilisTarih;
	}

	/**
	 * Setter of the property <tt>acilisTarih</tt>
	 * @param acilisTarih  The acilisTarih to set.
	 * @uml.property  name="acilisTarih"
	 */
	public void setAcilisTarih(Date acilisTarih) {
		this.acilisTarih = acilisTarih;
	}

	/**
	 * @uml.property  name="kapanisTarih"
	 */
	private Date kapanisTarih;

	/**
	 * Getter of the property <tt>kapanisTarih</tt>
	 * @return  Returns the kapanisTarih.
	 * @uml.property  name="kapanisTarih"
	 */
	public Date getKapanisTarih() {
		return kapanisTarih;
	}

	/**
	 * Setter of the property <tt>kapanisTarih</tt>
	 * @param kapanisTarih  The kapanisTarih to set.
	 * @uml.property  name="kapanisTarih"
	 */
	public void setKapanisTarih(Date kapanisTarih) {
		this.kapanisTarih = kapanisTarih;
	}
	
	/**
	 * @uml.property  name="acilisHareketKod"
	 */
	private HareketKod acilisHareketKod;

	/**
	 * Getter of the property <tt>acilisHareketKod</tt>
	 * @return  Returns the acilisHareketKod.
	 * @uml.property  name="acilisHareketKod"
	 */
	public HareketKod getAcilisHareketKod() {
		return acilisHareketKod;
	}

	/**
	 * Setter of the property <tt>acilisHareketKod</tt>
	 * @param acilisHareketKod  The acilisHareketKod to set.
	 * @uml.property  name="acilisHareketKod"
	 */
	public void setAcilisHareketKod(HareketKod acilisHareketKod) {
		this.acilisHareketKod = acilisHareketKod;
	}

	/**
	 * @uml.property  name="kapanisHareketKod"
	 */
	private HareketKod kapanisHareketKod;
	
	/**
	 * Getter of the property <tt>kapanisHareketKod</tt>
	 * @return  Returns the kapanisHareketKod.
	 * @uml.property  name="kapanisHareketKod"
	 */
	public HareketKod getKapanisHareketKod() {
		return kapanisHareketKod;
	}

	/**
	 * Setter of the property <tt>kapanisHareketKod</tt>
	 * @param kapanisHareketKod  The kapanisHareketKod to set.
	 * @uml.property  name="kapanisHareketKod"
	 */
	public void setKapanisHareketKod(HareketKod kapanisHareketKod) {
		this.kapanisHareketKod = kapanisHareketKod;
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
	
}
