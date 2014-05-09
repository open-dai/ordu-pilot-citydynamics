package com.sampas.ortak.spatial.transaction.model;

import java.util.Date;


@SuppressWarnings("serial")
public class BinaTrs extends BaseTransactionObject {
	
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
	 * @uml.property  name="binaNo"
	 */
	private Long binaNo;

	/**
	 * Getter of the property <tt>binaNo</tt>
	 * @return  Returns the binaNo.
	 * @uml.property  name="binaNo"
	 */
	public Long getBinaNo() {
		return binaNo;
	}

	/**
	 * Setter of the property <tt>binaNo</tt>
	 * @param binaNo  The binaNo to set.
	 * @uml.property  name="binaNo"
	 */
	public void setBinaNo(Long binaNo) {
		this.binaNo = binaNo;
	}

	/**
	 * @uml.property  name="apartmanBlokAd"
	 */
	private String apartmanBlokAd = "";

	/** 
	 * Getter of the property <tt>siteApartmanAd</tt>
	 * @return  Returns the siteApartmanAd.
	 * @uml.property  name="apartmanBlokAd"
	 */
	public String getApartmanBlokAd() {
		return apartmanBlokAd;
	}

	/** 
	 * Setter of the property <tt>siteApartmanAd</tt>
	 * @param siteApartmanAd  The siteApartmanAd to set.
	 * @uml.property  name="apartmanBlokAd"
	 */
	public void setApartmanBlokAd(String apartmanBlokAd) {
		this.apartmanBlokAd = apartmanBlokAd;
	}

	/**
	 * @uml.property  name="asansorEh"
	 */
	private String asansorEh = "";

	/**
	 * Getter of the property <tt>asansorEh</tt>
	 * @return  Returns the asansorEh.
	 * @uml.property  name="asansorEh"
	 */
	public String getAsansorEh() {
		return asansorEh;
	}

	/**
	 * Setter of the property <tt>asansorEh</tt>
	 * @param asansorEh  The asansorEh to set.
	 * @uml.property  name="asansorEh"
	 */
	public void setAsansorEh(String asansorEh) {
		this.asansorEh = asansorEh;
	}
	
	/**
	 * @uml.property  name="arsaAlan"
	 */
	private Double arsaAlan;

	/**
	 * Getter of the property <tt>arsaAlan</tt>
	 * @return  Returns the arsaAlan.
	 * @uml.property  name="arsaAlan"
	 */
	public Double getArsaAlan() {
		return arsaAlan;
	}

	/**
	 * Setter of the property <tt>arsaAlan</tt>
	 * @param arsaAlan  The arsaAlan to set.
	 * @uml.property  name="arsaAlan"
	 */
	public void setArsaAlan(Double arsaAlan) {
		this.arsaAlan = arsaAlan;
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
	 * @uml.property  name="kaloriferEh"
	 */
	private String kaloriferEh;

	/**
	 * Getter of the property <tt>kaloriferEh</tt>
	 * @return  Returns the kaloriferEh.
	 * @uml.property  name="kaloriferEh"
	 */
	public String getKaloriferEh() {
		return kaloriferEh;
	}

	/**
	 * Setter of the property <tt>kaloriferEh</tt>
	 * @param kaloriferEh  The kaloriferEh to set.
	 * @uml.property  name="kaloriferEh"
	 */
	public void setKaloriferEh(String kaloriferEh) {
		this.kaloriferEh = kaloriferEh;
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
	 * @uml.property  name="postaKodu"
	 */
	private String postaKodu = "";

	/**
	 * Getter of the property <tt>postaKodu</tt>
	 * @return  Returns the postaKodu.
	 * @uml.property  name="postaKodu"
	 */
	public String getPostaKodu() {
		return postaKodu;
	}

	/**
	 * Setter of the property <tt>postaKodu</tt>
	 * @param postaKodu  The postaKodu to set.
	 * @uml.property  name="postaKodu"
	 */
	public void setPostaKodu(String postaKodu) {
		this.postaKodu = postaKodu;
	}
	
	/**
	 * @uml.property  name="zoneNo"
	 */
	private Long zoneNo;

	/**
	 * Getter of the property <tt>zoneNo</tt>
	 * @return  Returns the zoneNo.
	 * @uml.property  name="zoneNo"
	 */
	public Long getZoneNo() {
		return zoneNo;
	}

	/**
	 * Setter of the property <tt>zoneNo</tt>
	 * @param zoneBinaNo  The zoneNo to set.
	 * @uml.property  name="zoneNo"
	 */
	public void setZoneNo(Long zoneNo) {
		this.zoneNo = zoneNo;
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
	 * @uml.property  name="yapiAdaId"
	 */
	private Long yapiAdaId;

	/**
	 * Getter of the property <tt>yapiAda</tt>
	 * @return  Returns the yapiAda.
	 * @uml.property  name="yapiAda"
	 */
	public Long getYapiAdaId() {
		return yapiAdaId;
	}

	/**
	 * Setter of the property <tt>yapiAdaId</tt>
	 * @param yapiAdaId  The yapiAdaId to set.
	 * @uml.property  name="yapiAdaId"
	 */
	public void setYapiAdaId(Long yapiAdaId) {
		this.yapiAdaId = yapiAdaId;
	}

	/**
	 * @uml.property  name="siteId"
	 */
	private Long siteId;

	/**
	 * Getter of the property <tt>siteId</tt>
	 * @return  Returns the siteId.
	 * @uml.property  name="siteId"
	 */
	public Long getSiteId() {
		return siteId;
	}

	/**
	 * Setter of the property <tt>siteId</tt>
	 * @param siteId  The siteId to set.
	 * @uml.property  name="siteId"
	 */
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
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
	
	/**
	 * @uml.property  name="uretimKaynakId"
	 */
	private Long uretimKaynakId;

	/**
	 * Getter of the property <tt>uretimKaynakId</tt>
	 * @return  Returns the uretimKaynakId.
	 * @uml.property  name="uretimKaynakId"
	 */
	public Long getUretimKaynakId() {
		return uretimKaynakId;
	}

	/**
	 * Setter of the property <tt>uretimKaynakId</tt>
	 * @param uretimKaynakId  The uretimKaynakId to set.
	 * @uml.property  name="uretimKaynakId"
	 */
	public void setUretimKaynakId(Long uretimKaynakId) {
		this.uretimKaynakId = uretimKaynakId;
	}

	/**
	 * @uml.property  name="bilgiTipiId"
	 */
	private Long bilgiTipiId;

	/**
	 * Getter of the property <tt>bilgiTipiId</tt>
	 * @return  Returns the bilgiTipiId.
	 * @uml.property  name="bilgiTipiId"
	 */
	public Long getBilgiTipiId() {
		return bilgiTipiId;
	}

	/**
	 * Setter of the property <tt>bilgiTipiId</tt>
	 * @param bilgiTipiId  The bilgiTipiId to set.
	 * @uml.property  name="bilgiTipiId"
	 */
	public void setBilgiTipiId(Long bilgiTipiId) {
		this.bilgiTipiId = bilgiTipiId;
	}
	
	/**
	 * @uml.property  name="binaCatiTurId"
	 */
	private Long binaCatiTurId;

	/**
	 * Getter of the property <tt>binaCatiTurId</tt>
	 * @return  Returns the binaCatiTurId.
	 * @uml.property  name="binaCatiTurId"
	 */
	public Long getBinaCatiTurId() {
		return binaCatiTurId;
	}
	
	/**
	 * Setter of the property <tt>binaCatiTurId</tt>
	 * @param binaCatiTurId  The binaCatiTurId to set.
	 * @uml.property  name="binaCatiTurId"
	 */
	public void setBinaCatiTurId(Long binaCatiTurId) {
		this.binaCatiTurId = binaCatiTurId;
	}
	
	/**
	 * @uml.property  name="yapiCepheTurId"
	 */
	private Long yapiCepheTurId;

	/**
	 * Getter of the property <tt>yapiCepheTurId</tt>
	 * @return  Returns the yapiCepheTurId.
	 * @uml.property  name="yapiCepheTurId"
	 */
	public Long getYapiCepheTurId() {
		return yapiCepheTurId;
	}
	
	/**
	 * Setter of the property <tt>yapiCepheTurId</tt>
	 * @param yapiCepheTurId  The yapiCepheTurId to set.
	 * @uml.property  name="yapiCepheTurId"
	 */
	public void setYapiCepheTurId(Long yapiCepheTurId) {
		this.yapiCepheTurId = yapiCepheTurId;
	}
	
	/**
	 * @uml.property  name="dosemeTurId"
	 */
	private Long dosemeTurId;

	/**
	 * Getter of the property <tt>dosemeTurId</tt>
	 * @return  Returns the dosemeTurId.
	 * @uml.property  name="dosemeTurId"
	 */
	public Long getDosemeTurId() {
		return dosemeTurId;
	}
	
	/**
	 * Setter of the property <tt>dosemeTurId</tt>
	 * @param dosemeTurId  The dosemeTurId to set.
	 * @uml.property  name="dosemeTurId"
	 */
	public void setDosemeTurId(Long dosemeTurId) {
		this.dosemeTurId = dosemeTurId;
	}
	
	/**
	 * @uml.property  name="isitmaTurId"
	 */
	private Long isitmaTurId;

	/**
	 * Getter of the property <tt>isitmaTurId</tt>
	 * @return  Returns the isitmaTurId.
	 * @uml.property  name="isitmaTurId"
	 */
	public Long getIsitmaTurId() {
		return isitmaTurId;
	}
	
	/**
	 * Setter of the property <tt>isitmaTurId</tt>
	 * @param isitmaTurId  The isitmaTurId to set.
	 * @uml.property  name="isitmaTurId"
	 */
	public void setIsitmaTurId(Long isitmaTurId) {
		this.isitmaTurId = isitmaTurId;
	}
	
	/**
	 * @uml.property  name="isitmaYakitTurId"
	 */
	private Long isitmaYakitTurId;

	/**
	 * Getter of the property <tt>isitmaYakitTurId</tt>
	 * @return  Returns the isitmaYakitTurId.
	 * @uml.property  name="isitmaYakitTurId"
	 */
	public Long getIsitmaYakitTurId() {
		return isitmaYakitTurId;
	}
	
	/**
	 * Setter of the property <tt>isitmaYakitTurId</tt>
	 * @param isitmaYakitTurId  The isitmaYakitTurId to set.
	 * @uml.property  name="isitmaYakitTurId"
	 */
	public void setIsitmaYakitTurId(Long isitmaYakitTurId) {
		this.isitmaYakitTurId = isitmaYakitTurId;
	}
	
	/**
	 * @uml.property  name="sicakSuTeminTurId"
	 */
	private Long sicakSuTeminTurId;

	/**
	 * Getter of the property <tt>sicakSuTeminTurId</tt>
	 * @return  Returns the sicakSuTeminTurId.
	 * @uml.property  name="sicakSuTeminTurId"
	 */
	public Long getSicakSuTeminTurId() {
		return sicakSuTeminTurId;
	}
	
	/**
	 * Setter of the property <tt>sicakSuTeminTurId</tt>
	 * @param sicakSuTeminTurId  The sicakSuTeminTurId to set.
	 * @uml.property  name="sicakSuTeminTurId"
	 */
	public void setSicakSuTeminTurId(Long sicakSuTeminTurId) {
		this.sicakSuTeminTurId = sicakSuTeminTurId;
	}
	
	/**
	 * @uml.property  name="sicakSuYakitId"
	 */
	private Long sicakSuYakitId;

	/**
	 * Getter of the property <tt>sicakSuYakitId</tt>
	 * @return  Returns the sicakSuYakitId.
	 * @uml.property  name="sicakSuYakitId"
	 */
	public Long getSicakSuYakitId() {
		return sicakSuYakitId;
	}
	
	/**
	 * Setter of the property <tt>sicakSuYakitId</tt>
	 * @param sicakSuYakitId  The sicakSuYakitId to set.
	 * @uml.property  name="sicakSuYakitId"
	 */
	public void setSicakSuYakitId(Long sicakSuYakitId) {
		this.sicakSuYakitId = sicakSuYakitId;
	}

	/**
	 * @uml.property  name="tasiyiciSistemId"
	 */
	private Long tasiyiciSistemId;

	/**
	 * Getter of the property <tt>tasiyiciSistemId</tt>
	 * @return  Returns the tasiyiciSistemId.
	 * @uml.property  name="tasiyiciSistemId"
	 */
	public Long getTasiyiciSistemId() {
		return tasiyiciSistemId;
	}
	
	/**
	 * Setter of the property <tt>tasiyiciSistemId</tt>
	 * @param tasiyiciSistemId  The tasiyiciSistemId to set.
	 * @uml.property  name="tasiyiciSistemId"
	 */
	public void setTasiyiciSistemId(Long tasiyiciSistemId) {
		this.tasiyiciSistemId = tasiyiciSistemId;
	}
	
	/**
	 * @uml.property  name="bagimsizKullanimSinifId"
	 */
	private Long bagimsizKullanimSinifId;

	/**
	 * Getter of the property <tt>bagimsizKullanimSinifId</tt>
	 * @return  Returns the bagimsizKullanimSinifId.
	 * @uml.property  name="bagimsizKullanimSinifId"
	 */
	public Long getBagimsizKullanimSinifId() {
		return bagimsizKullanimSinifId;
	}

	/**
	 * Setter of the property <tt>tasiyiciSistemId</tt>
	 * @param tasiyiciSistemId  The tasiyiciSistemId to set.
	 * @uml.property  name="tasiyiciSistemId"
	 */
	public void setBagimsizKullanimSinifId(Long bagimsizKullanimSinifId) {
		this.bagimsizKullanimSinifId = bagimsizKullanimSinifId;
	}
	
	/**
	 * @uml.property  name="insaatTurId"
	 */
	private Long insaatTurId;

	/**
	 * Getter of the property <tt>insaatTurId</tt>
	 * @return  Returns the insaatTurId.
	 * @uml.property  name="insaatTurId"
	 */
	public Long getInsaatTurId() {
		return insaatTurId;
	}

	/**
	 * Setter of the property <tt>insaatTurId</tt>
	 * @param insaatTurId  The insaatTurId to set.
	 * @uml.property  name="insaainsaatTurIdtTur"
	 */
	public void setInsaatTurId(Long insaatTurId) {
		this.insaatTurId = insaatTurId;
	}
	
	/**
	 * @uml.property  name="insaatSinifId"
	 */
	private Long insaatSinifId;

	/**
	 * Getter of the property <tt>insaatSinifId</tt>
	 * @return  Returns the insaatSinifId.
	 * @uml.property  name="insaatSinifId"
	 */
	public Long getInsaatSinifId() {
		return insaatSinifId;
	}

	/**
	 * Setter of the property <tt>insaatSinifId</tt>
	 * @param insaatSinifId  The insaatSinifId to set.
	 * @uml.property  name="insaatSinifId"
	 */
	public void setInsaatSinifId(Long insaatSinifId) {
		this.insaatSinifId = insaatSinifId;
	}

	
	/**
	 * @uml.property  name="duvarDolguId"
	 */
	private Long duvarDolguId;

	/**
	 * Getter of the property <tt>duvarDolguId</tt>
	 * @return  Returns the duvarDolguId.
	 * @uml.property  name="duvarDolguId"
	 */
	public Long getDuvarDolguId() {
		return duvarDolguId;
	}

	/**
	 * Setter of the property <tt>duvarDolguId</tt>
	 * @param insaatSinifId  The duvarDolguId to set.
	 * @uml.property  name="duvarDolguId"
	 */
	public void setDuvarDolguId(Long duvarDolguId) {
		this.duvarDolguId = duvarDolguId;
	}
}
