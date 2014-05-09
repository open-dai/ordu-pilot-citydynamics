package com.sampas.akos.ortak.model;

import java.util.Date;

import org.hibernate.envers.Audited;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;


@SuppressWarnings("serial")
@Audited
public class CaddeSokak extends BaseObject implements IVersionable, ITransactionable ,IHistoricalable{

	/***
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
	 * @uml.property  name="meclisKararTarih"
	 */
	private Date meclisKararTarih;

	/**
	 * Getter of the property <tt>meclisKararTarih</tt>
	 * @return  Returns the meclisKararTarih.
	 * @uml.property  name="meclisKararTarih"
	 */
	public Date getMeclisKararTarih() {
		return meclisKararTarih;
	}

	/**
	 * Setter of the property <tt>meclisKararTarih</tt>
	 * @param meclisKararTarih  The meclisKararTarih to set.
	 * @uml.property  name="meclisKararTarih"
	 */
	public void setMeclisKararTarih(Date meclisKararTarih) {
		this.meclisKararTarih = meclisKararTarih;
	}

	/**
	 * @uml.property  name="meclisKararNo"
	 */
	private String meclisKararNo = "";

	/**
	 * Getter of the property <tt>meclisKararNo</tt>
	 * @return  Returns the meclisKararNo.
	 * @uml.property  name="meclisKararNo"
	 */
	public String getMeclisKararNo() {
		return meclisKararNo;
	}

	/**
	 * Setter of the property <tt>meclisKararNo</tt>
	 * @param meclisKararNo  The meclisKararNo to set.
	 * @uml.property  name="meclisKararNo"
	 */
	public void setMeclisKararNo(String meclisKararNo) {
		this.meclisKararNo = meclisKararNo;
	}

	/**
	 * @uml.property  name="tekKapiBaslangicNo"
	 */
	private Long tekKapiBaslangicNo;

	/**
	 * Getter of the property <tt>tekKapiBaslangicNo</tt>
	 * @return  Returns the tekKapiBaslangicNo.
	 * @uml.property  name="tekKapiBaslangicNo"
	 */
	public Long getTekKapiBaslangicNo() {
		return tekKapiBaslangicNo;
	}

	/**
	 * Setter of the property <tt>tekKapiBaslangicNo</tt>
	 * @param tekKapiBaslangicNo  The tekKapiBaslangicNo to set.
	 * @uml.property  name="tekKapiBaslangicNo"
	 */
	public void setTekKapiBaslangicNo(Long tekKapiBaslangicNo) {
		this.tekKapiBaslangicNo = tekKapiBaslangicNo;
	}

	/**
	 * @uml.property  name="tekKapiBitisNo"
	 */
	private Long tekKapiBitisNo;

	/**
	 * Getter of the property <tt>tekKapiBitisNo</tt>
	 * @return  Returns the tekKapiBitisNo.
	 * @uml.property  name="tekKapiBitisNo"
	 */
	public Long getTekKapiBitisNo() {
		return tekKapiBitisNo;
	}

	/**
	 * Setter of the property <tt>tekKapiBitisNo</tt>
	 * @param tekKapiBitisNo  The tekKapiBitisNo to set.
	 * @uml.property  name="tekKapiBitisNo"
	 */
	public void setTekKapiBitisNo(Long tekKapiBitisNo) {
		this.tekKapiBitisNo = tekKapiBitisNo;
	}

	/**
	 * @uml.property  name="ciftKapiBaslangicNo"
	 */
	private Long ciftKapiBaslangicNo;

	/**
	 * Getter of the property <tt>ciftKapiBaslangicNo</tt>
	 * @return  Returns the ciftKapiBaslangicNo.
	 * @uml.property  name="ciftKapiBaslangicNo"
	 */
	public Long getCiftKapiBaslangicNo() {
		return ciftKapiBaslangicNo;
	}

	/**
	 * Setter of the property <tt>ciftKapiBaslangicNo</tt>
	 * @param ciftKapiBaslangicNo  The ciftKapiBaslangicNo to set.
	 * @uml.property  name="ciftKapiBaslangicNo"
	 */
	public void setCiftKapiBaslangicNo(Long ciftKapiBaslangicNo) {
		this.ciftKapiBaslangicNo = ciftKapiBaslangicNo;
	}

	/**
	 * @uml.property  name="ciftKapiBitisNo"
	 */
	private Long ciftKapiBitisNo;

	/**
	 * Getter of the property <tt>ciftKapiBitisNo</tt>
	 * @return  Returns the ciftKapiBitisNo.
	 * @uml.property  name="ciftKapiBitisNo"
	 */
	public Long getCiftKapiBitisNo() {
		return ciftKapiBitisNo;
	}

	/**
	 * Setter of the property <tt>ciftKapiBitisNo</tt>
	 * @param ciftKapiBitisNo  The ciftKapiBitisNo to set.
	 * @uml.property  name="ciftKapiBitisNo"
	 */
	public void setCiftKapiBitisNo(Long ciftKapiBitisNo) {
		this.ciftKapiBitisNo = ciftKapiBitisNo;
	}

	/**
	 * @uml.property  name="kurumSabit"
	 * @uml.associationEnd  inverse="caddeSokak:com.sampas.akos.ortak.model.KurumSabit"
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
	 * @uml.property  name="caddeSokakTur"
	 * @uml.associationEnd  inverse="caddeSokak:com.sampas.akos.ortak.model.CaddeSokakTur"
	 */
	private CaddeSokakTur caddeSokakTur;

	/**
	 * Getter of the property <tt>caddeSokakTur</tt>
	 * @return  Returns the caddeSokakTur.
	 * @uml.property  name="caddeSokakTur"
	 */
	public CaddeSokakTur getCaddeSokakTur() {
		return caddeSokakTur;
	}

	/**
	 * Setter of the property <tt>caddeSokakTur</tt>
	 * @param caddeSokakTur  The caddeSokakTur to set.
	 * @uml.property  name="caddeSokakTur"
	 */
	public void setCaddeSokakTur(CaddeSokakTur caddeSokakTur) {
		this.caddeSokakTur = caddeSokakTur;
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
