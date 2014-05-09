package com.sampas.ortak.spatial.transaction.model;

import java.util.Date;


@SuppressWarnings("serial")
public class AdresTrs extends BaseTransactionObject {

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
	
	/**
	 * @uml.property  name="adresNo"
	 */
	private Long adresNo;

	/**
	 * Getter of the property <tt>adresNo</tt>
	 * @return  Returns the adresNo.
	 * @uml.property  name="adresNo"
	 */
	public Long getAdresNo() {
		return adresNo;
	}

	/**
	 * Setter of the property <tt>adresNo</tt>
	 * @param adresNo  The adresNo to set.
	 * @uml.property  name="adresNo"
	 */
	public void setAdresNo(Long adresNo) {
		this.adresNo = adresNo;
	}

	/**
	 * @uml.property  name="kapiNo"
	 */
	private Long kapiNo;

	/**
	 * Getter of the property <tt>kapiNo</tt>
	 * @return  Returns the kapiNo.
	 * @uml.property  name="kapiNo"
	 */
	public Long getKapiNo() {
		return kapiNo;
	}

	/**
	 * Setter of the property <tt>kapiNo</tt>
	 * @param kapiNo  The kapiNo to set.
	 * @uml.property  name="kapiNo"
	 */
	public void setKapiNo(Long kapiNo) {
		this.kapiNo = kapiNo;
	}

	/**
	 * @uml.property  name="altKapiNo"
	 */
	private String altKapiNo = "";

	/**
	 * Getter of the property <tt>altKapiNo</tt>
	 * @return  Returns the altKapiNo.
	 * @uml.property  name="altKapiNo"
	 */
	public String getAltKapiNo() {
		return altKapiNo;
	}

	/**
	 * Setter of the property <tt>altKapiNo</tt>
	 * @param altKapiNo  The altKapiNo to set.
	 * @uml.property  name="altKapiNo"
	 */
	public void setAltKapiNo(String altKapiNo) {
		this.altKapiNo = altKapiNo;
	}

	/**
	 * @uml.property  name="adresTur"
	 */
	private String adresTur = "";

	/**
	 * Getter of the property <tt>adresTur</tt>
	 * @return  Returns the adresTur.
	 * @uml.property  name="adresTur"
	 */
	public String getAdresTur() {
		return adresTur;
	}

	/**
	 * Setter of the property <tt>adresTur</tt>
	 * @param adresTur  The adresTur to set.
	 * @uml.property  name="adresTur"
	 */
	public void setAdresTur(String adresTur) {
		this.adresTur = adresTur;
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
	 * @uml.property  name="pdaEh"
	 */
	private String pdaEh = "";

	/**
	 * Getter of the property <tt>pdaEh</tt>
	 * @return  Returns the pdaEh.
	 * @uml.property  name="pdaEh"
	 */
	public String getPdaEh() {
		return pdaEh;
	}

	/**
	 * Setter of the property <tt>pdaEh</tt>
	 * @param pdaEh  The pdaEh to set.
	 * @uml.property  name="pdaEh"
	 */
	public void setPdaEh(String pdaEh) {
		this.pdaEh = pdaEh;
	}
	
	/**
	 * @uml.property  name="pdaUserName"
	 */
	private String pdaUserName = "";

	/**
	 * Getter of the property <tt>pdaUserName</tt>
	 * @return  Returns the pdaUserName.
	 * @uml.property  name="pdaUserName"
	 */
	public String getPdaUserName() {
		return pdaUserName;
	}

	/**
	 * Setter of the property <tt>pdaUserName</tt>
	 * @param pdaUserName  The pdaUserName to set.
	 * @uml.property  name="pdaUserName"
	 */
	public void setPdaUserName(String pdaUserName) {
		this.pdaUserName = pdaUserName;
	}
	
	/**
	 * @uml.property  name="pdaIslemTarihi"
	 */
	private Date pdaIslemTarihi;

	/**
	 * Getter of the property <tt>pdaIslemTarihi</tt>
	 * @return  Returns the pdaIslemTarihi.
	 * @uml.property  name="pdaIslemTarihi"
	 */
	public Date getPdaIslemTarihi() {
		return pdaIslemTarihi;
	}

	/**
	 * Setter of the property <tt>pdaIslemTarihi</tt>
	 * @param pdaIslemTarihi  The pdaIslemTarihi to set.
	 * @uml.property  name="pdaIslemTarihi"
	 */
	public void setPdaIslemTarihi(Date pdaIslemTarihi) {
		this.pdaIslemTarihi = pdaIslemTarihi;
	}

	/**
	 * @uml.property  name="acilisTarihi"
	 */
	private Date acilisTarihi;

	/**
	 * Getter of the property <tt>acilisTarihi</tt>
	 * @return  Returns the acilisTarihi.
	 * @uml.property  name="acilisTarihi"
	 */
	public Date getAcilisTarihi() {
		return acilisTarihi;
	}

	/**
	 * Setter of the property <tt>acilisTarihi</tt>
	 * @param acilisTarihi  The acilisTarihi to set.
	 * @uml.property  name="acilisTarihi"
	 */
	public void setAcilisTarihi(Date acilisTarihi) {
		this.acilisTarihi = acilisTarihi;
	}
	
	/**
	 * @uml.property  name="kapanisTarihi"
	 */
	private Date kapanisTarihi;

	/**
	 * Getter of the property <tt>kapanisTarihi</tt>
	 * @return  Returns the kapanisTarihi.
	 * @uml.property  name="kapanisTarihi"
	 */
	public Date getKapanisTarihi() {
		return kapanisTarihi;
	}

	/**
	 * Setter of the property <tt>kapanisTarihi</tt>
	 * @param kapanisTarihi  The kapanisTarihi to set.
	 * @uml.property  name="kapanisTarihi"
	 */
	public void setKapanisTarihi(Date kapanisTarihi) {
		this.kapanisTarihi = kapanisTarihi;
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
	 * @uml.property  name="mahalleCaddeId"
	 */
	private Long mahalleCaddeId;

	/**
	 * Getter of the property <tt>mahalleCaddeId</tt>
	 * @return  Returns the mahalleCaddeId.
	 * @uml.property  name="mahalleCaddeId"
	 */
	public Long getMahalleCaddeId() {
		return mahalleCaddeId;
	}

	/**
	 * Setter of the property <tt>mahalleCaddeId</tt>
	 * @param mahalleCaddeId  The mahalleCaddeId to set.
	 * @uml.property  name="mahalleCaddeId"
	 */
	public void setMahalleCaddeId(Long mahalleCaddeId) {
		this.mahalleCaddeId = mahalleCaddeId;
	}
	
	/**
	 * @uml.property  name="binaId"
	 */
	private Long binaId;

	/**
	 * Getter of the property <tt>binaId</tt>
	 * @return  Returns the binaId.
	 * @uml.property  name="binaId"
	 */
	public Long getBinaId() {
		return binaId;
	}

	/**
	 * Setter of the property <tt>binaId</tt>
	 * @param binaId  The binaId to set.
	 * @uml.property  name="binaId"
	 */
	public void setBinaId(Long binaId) {
		this.binaId = binaId;
	}
	
	/**
	 * @uml.property  name="kadastroParselId"
	 */
	private Long kadastroParselId;

	/**
	 * Getter of the property <tt>kadastroParselId</tt>
	 * @return  Returns the kadastroParselId.
	 * @uml.property  name="kadastroParselId"
	 */
	public Long getKadastroParselId() {
		return kadastroParselId;
	}

	/**
	 * Setter of the property <tt>kadastroParselId</tt>
	 * @param kadastroParselId  The kadastroParselId to set.
	 * @uml.property  name="kadastroParselId"
	 */
	public void setKadastroParselId(Long kadastroParselId) {
		this.kadastroParselId = kadastroParselId;
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
	
}
