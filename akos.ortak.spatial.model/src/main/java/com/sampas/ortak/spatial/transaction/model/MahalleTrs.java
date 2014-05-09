package com.sampas.ortak.spatial.transaction.model;

import java.util.Date;


@SuppressWarnings("serial")
public class MahalleTrs extends BaseTransactionObject {

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
	 * Setter of the property <tt>koy</tt>
	 * @param koy  The koy to set.
	 * @uml.property  name="koy"
	 */
	public void setKoy(String koy) {
		this.koy = koy;
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
	 * @uml.property  name="aktifEh"
	 */
	private String aktifEh = "";

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
	 * @uml.property  name="kurumSabitId"
	 */
	private Long kurumSabitId;

	/**
	 * Getter of the property <tt>kurumSabitId</tt>
	 * @return  Returns the kurumSabitId.
	 * @uml.property  name="kurumSabitId"
	 */
	public Long getKurumSabitId() {
		return kurumSabitId;
	}

	/**
	 * Setter of the property <tt>kurumSabitId</tt>
	 * @param kurumSabitId  The kurumSabitId to set.
	 * @uml.property  name="kurumSabitId"
	 */
	public void setKurumSabitId(Long kurumSabitId) {
		this.kurumSabitId = kurumSabitId;
	}
	
	/**
	 * @uml.property  name="acilisHareketKodId"
	 */
	private Long acilisHareketKodId;

	/**
	 * Getter of the property <tt>acilisHareketKodId</tt>
	 * @return  Returns the acilisHareketKodId.
	 * @uml.property  name="acilisHareketKodId"
	 */
	public Long getAcilisHareketKodId() {
		return acilisHareketKodId;
	}

	/**
	 * Setter of the property <tt>acilisHareketKodId</tt>
	 * @param acilisHareketKodId  The acilisHareketKodId to set.
	 * @uml.property  name="acilisHareketKodId"
	 */
	public void setAcilisHareketKodId(Long acilisHareketKodId) {
		this.acilisHareketKodId = acilisHareketKodId;
	}
	
	/**
	 * @uml.property  name="kapanisHareketKodId"
	 */
	private Long kapanisHareketKodId;

	/**
	 * Getter of the property <tt>kapanisHareketKodId</tt>
	 * @return  Returns the kapanisHareketKodId.
	 * @uml.property  name="kapanisHareketKodId"
	 */
	public Long getKapanisHareketKodId() {
		return kapanisHareketKodId;
	}

	/**
	 * Setter of the property <tt>kapanisHareketKodId</tt>
	 * @param acilisHareketKodId  The kapanisHareketKodId to set.
	 * @uml.property  name="kapanisHareketKodId"
	 */
	public void setKapanisHareketKodId(Long kapanisHareketKodId) {
		this.kapanisHareketKodId = kapanisHareketKodId;
	}
	
	/**
	 * @uml.property  name="grafik"
	 */
	private Long grafik;

	/**
	 * Getter of the property <tt>grafik</tt>
	 * @return  Returns the grafik.
	 * @uml.property  name="grafik"
	 */
	public Long getGrafik() {
		return grafik;
	}

	/**
	 * Setter of the property <tt>grafik</tt>
	 * @param grafik  The grafik to set.
	 * @uml.property  name="grafik"
	 */
	public void setGrafik(Long grafik) {
		this.grafik = grafik;
	}

}
