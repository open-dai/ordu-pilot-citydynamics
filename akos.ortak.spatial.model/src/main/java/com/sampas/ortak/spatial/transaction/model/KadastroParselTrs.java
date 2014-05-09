package com.sampas.ortak.spatial.transaction.model;

import java.util.Date;


@SuppressWarnings("serial")
public class KadastroParselTrs extends BaseTransactionObject {
	
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
	 * @uml.property  name="parsel"
	 */
	private Long parsel;

	/**
	 * Getter of the property <tt>parsel</tt>
	 * @return  Returns the parsel.
	 * @uml.property  name="parsel"
	 */
	public Long getParsel() {
		return parsel;
	}

	/**
	 * Setter of the property <tt>parsel</tt>
	 * @param parsel  The parsel to set.
	 * @uml.property  name="parsel"
	 */
	public void setParsel(Long parsel) {
		this.parsel = parsel;
	}

	/**
	 * @uml.property  name="alan"
	 */
	private Double alan;

	/**
	 * Getter of the property <tt>alan</tt>
	 * @return  Returns the alan.
	 * @uml.property  name="alan"
	 */
	public Double getAlan() {
		return alan;
	}

	/**
	 * Setter of the property <tt>alan</tt>
	 * @param alan  The alan to set.
	 * @uml.property  name="alan"
	 */
	public void setAlan(Double alan) {
		this.alan = alan;
	}

	/**
	 * @uml.property  name="geciciAda"
	 */
	private String geciciAda = "";

	/**
	 * Getter of the property <tt>geciciAda</tt>
	 * @return  Returns the geciciAda.
	 * @uml.property  name="geciciAda"
	 */
	public String getGeciciAda() {
		return geciciAda;
	}

	/**
	 * Setter of the property <tt>geciciAda</tt>
	 * @param geciciAda  The geciciAda to set.
	 * @uml.property  name="geciciAda"
	 */
	public void setGeciciAda(String geciciAda) {
		this.geciciAda = geciciAda;
	}

	/**
	 * @uml.property  name="geciciParsel"
	 */
	private String geciciParsel = "";

	/**
	 * Getter of the property <tt>geciciParsel</tt>
	 * @return  Returns the geciciParsel.
	 * @uml.property  name="geciciParsel"
	 */
	public String getGeciciParsel() {
		return geciciParsel;
	}

	/**
	 * Setter of the property <tt>geciciParsel</tt>
	 * @param geciciParsel  The geciciParsel to set.
	 * @uml.property  name="geciciParsel"
	 */
	public void setGeciciParsel(String geciciParsel) {
		this.geciciParsel = geciciParsel;
	}

	/**
	 * @uml.property  name="ada"
	 */
	private Long ada;

	/**
	 * Getter of the property <tt>ada</tt>
	 * @return  Returns the ada.
	 * @uml.property  name="ada"
	 */
	public Long getAda() {
		return ada;
	}

	/**
	 * Setter of the property <tt>ada</tt>
	 * @param ada  The ada to set.
	 * @uml.property  name="ada"
	 */
	public void setAda(Long ada) {
		this.ada = ada;
	}

	/**
	 * @uml.property  name="pafta"
	 */
	private String pafta = "";

	/**
	 * Getter of the property <tt>pafta</tt>
	 * @return  Returns the pafta.
	 * @uml.property  name="pafta"
	 */
	public String getPafta() {
		return pafta;
	}

	/**
	 * Setter of the property <tt>pafta</tt>
	 * @param pafta  The pafta to set.
	 * @uml.property  name="pafta"
	 */
	public void setPafta(String pafta) {
		this.pafta = pafta;
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
	 * @uml.property  name="mahalleId"
	 */
	private Long mahalleId;

	/**
	 * Getter of the property <tt>mahalleId</tt>
	 * @return  Returns the mahalleId.
	 * @uml.property  name="mahalleId"
	 */
	public Long getMahalleId() {
		return mahalleId;
	}

	/**
	 * Setter of the property <tt>mahalleId</tt>
	 * @param mahalleId  The mahalleId to set.
	 * @uml.property  name="mahalleId"
	 */
	public void setMahalleId(Long mahalleId) {
		this.mahalleId = mahalleId;
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
	 * @uml.property  name="tapuMahalleId"
	 */
	private Long tapuMahalleId;

	/**
	 * Getter of the property <tt>tapuMahalleId</tt>
	 * @return  Returns the tapuMahalleId.
	 * @uml.property  name="tapuMahalleId"
	 */
	public Long getTapuMahalleId() {
		return tapuMahalleId;
	}
	
	/**
	 * Setter of the property <tt>tapuMahalleId</tt>
	 * @param tapuMahalleId  The tapuMahalleId to set.
	 * @uml.property  name="tapuMahalleId"
	 */
	public void setTapuMahalleId(Long tapuMahalleId) {
		this.tapuMahalleId = tapuMahalleId;
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
	 * @uml.property  name="kapanisHareketKodId"
	 */
	private Long kadastroParselTipiId;

	/**
	 * Getter of the property <tt>kadastroParselTipiId</tt>
	 * @return  Returns the kadastroParselTipiId.
	 * @uml.property  name="kadastroParselTipiId"
	 */
	public Long getKadastroParselTipiId() {
		return kadastroParselTipiId;
	}
	
	/**
	 * Setter of the property <tt>kadastroParselTipiId</tt>
	 * @param kadastroParselTipiId  The kadastroParselTipiId to set.
	 * @uml.property  name="kadastroParselTipiId"
	 */
	public void setKadastroParselTipiId(Long kadastroParselTipiId) {
		this.kadastroParselTipiId = kadastroParselTipiId;
	}
	
}
