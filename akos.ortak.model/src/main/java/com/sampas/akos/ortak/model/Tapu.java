package com.sampas.akos.ortak.model;

import com.sampas.akos.common.model.BaseObject;
import java.util.Date;

import com.sampas.akos.ortak.model.KadastroParsel;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.model.TapuMahalle;


@SuppressWarnings("serial")
public class Tapu extends BaseObject {

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
	 * @uml.property  name="tapuMahalle"
	 * @uml.associationEnd  inverse="tapu:com.sampas.akos.ortak.model.TapuMahalle"
	 */
	private TapuMahalle tapuMahalle;

	/**
	 * Getter of the property <tt>tapuMahalle</tt>
	 * @return  Returns the tapuMahalle.
	 * @uml.property  name="tapuMahalle"
	 */
	public TapuMahalle getTapuMahalle() {
		return tapuMahalle;
	}

	/**
	 * Setter of the property <tt>tapuMahalle</tt>
	 * @param tapuMahalle  The tapuMahalle to set.
	 * @uml.property  name="tapuMahalle"
	 */
	public void setTapuMahalle(TapuMahalle tapuMahalle) {
		this.tapuMahalle = tapuMahalle;
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
	 * @uml.property  name="adres"
	 */
	private String adres = "";

	/**
	 * Getter of the property <tt>adres</tt>
	 * @return  Returns the adres.
	 * @uml.property  name="adres"
	 */
	public String getAdres() {
		return adres;
	}

	/**
	 * Setter of the property <tt>adres</tt>
	 * @param adres  The adres to set.
	 * @uml.property  name="adres"
	 */
	public void setAdres(String adres) {
		this.adres = adres;
	}

	/**
	 * @uml.property  name="ad"
	 */
	private String ad = "";

	/** 
	 * Getter of the property <tt>ada</tt>
	 * @return  Returns the ada.
	 * @uml.property  name="ad"
	 */
	public String getAd() {
		return ad;
	}

	/** 
	 * Setter of the property <tt>ada</tt>
	 * @param ada  The ada to set.
	 * @uml.property  name="ad"
	 */
	public void setAd(String ad) {
		this.ad = ad;
	}

	/**
	 * @uml.property  name="anaKutukZeminId"
	 */
	private Long anaKutukZeminId;

	/**
	 * Getter of the property <tt>anaKutukZeminId</tt>
	 * @return  Returns the anaKutukZeminId.
	 * @uml.property  name="anaKutukZeminId"
	 */
	public Long getAnaKutukZeminId() {
		return anaKutukZeminId;
	}

	/**
	 * Setter of the property <tt>anaKutukZeminId</tt>
	 * @param anaKutukZeminId  The anaKutukZeminId to set.
	 * @uml.property  name="anaKutukZeminId"
	 */
	public void setAnaKutukZeminId(Long anaKutukZeminId) {
		this.anaKutukZeminId = anaKutukZeminId;
	}

	/**
	 * @uml.property  name="anaKutukCiltNo"
	 */
	private Long anaKutukCiltNo;

	/**
	 * Getter of the property <tt>anaKutukCiltNo</tt>
	 * @return  Returns the anaKutukCiltNo.
	 * @uml.property  name="anaKutukCiltNo"
	 */
	public Long getAnaKutukCiltNo() {
		return anaKutukCiltNo;
	}

	/**
	 * Setter of the property <tt>anaKutukCiltNo</tt>
	 * @param anaKutukCiltNo  The anaKutukCiltNo to set.
	 * @uml.property  name="anaKutukCiltNo"
	 */
	public void setAnaKutukCiltNo(Long anaKutukCiltNo) {
		this.anaKutukCiltNo = anaKutukCiltNo;
	}

	/**
	 * @uml.property  name="anaKutukSayfaNo"
	 */
	private Long anaKutukSayfaNo;

	/**
	 * Getter of the property <tt>anaKutukSayfaNo</tt>
	 * @return  Returns the anaKutukSayfaNo.
	 * @uml.property  name="anaKutukSayfaNo"
	 */
	public Long getAnaKutukSayfaNo() {
		return anaKutukSayfaNo;
	}

	/**
	 * Setter of the property <tt>anaKutukSayfaNo</tt>
	 * @param anaKutukSayfaNo  The anaKutukSayfaNo to set.
	 * @uml.property  name="anaKutukSayfaNo"
	 */
	public void setAnaKutukSayfaNo(Long anaKutukSayfaNo) {
		this.anaKutukSayfaNo = anaKutukSayfaNo;
	}

	/**
	 * @uml.property  name="arsaPay"
	 */
	private Double arsaPay;

	/**
	 * Getter of the property <tt>arsaPay</tt>
	 * @return  Returns the arsaPay.
	 * @uml.property  name="arsaPay"
	 */
	public Double getArsaPay() {
		return arsaPay;
	}

	/**
	 * Setter of the property <tt>arsaPay</tt>
	 * @param arsaPay  The arsaPay to set.
	 * @uml.property  name="arsaPay"
	 */
	public void setArsaPay(Double arsaPay) {
		this.arsaPay = arsaPay;
	}

	/**
	 * @uml.property  name="arsaPayda"
	 */
	private Double arsaPayda;

	/**
	 * Getter of the property <tt>arsaPayda</tt>
	 * @return  Returns the arsaPayda.
	 * @uml.property  name="arsaPayda"
	 */
	public Double getArsaPayda() {
		return arsaPayda;
	}

	/**
	 * Setter of the property <tt>arsaPayda</tt>
	 * @param arsaPayda  The arsaPayda to set.
	 * @uml.property  name="arsaPayda"
	 */
	public void setArsaPayda(Double arsaPayda) {
		this.arsaPayda = arsaPayda;
	}

	/**
	 * @uml.property  name="babaAd"
	 */
	private String babaAd = "";

	/**
	 * Getter of the property <tt>babaAd</tt>
	 * @return  Returns the babaAd.
	 * @uml.property  name="babaAd"
	 */
	public String getBabaAd() {
		return babaAd;
	}

	/**
	 * Setter of the property <tt>babaAd</tt>
	 * @param babaAd  The babaAd to set.
	 * @uml.property  name="babaAd"
	 */
	public void setBabaAd(String babaAd) {
		this.babaAd = babaAd;
	}

	/**
	 * @uml.property  name="blokNo"
	 */
	private String blokNo = "";

	/**
	 * Getter of the property <tt>blokNo</tt>
	 * @return  Returns the blokNo.
	 * @uml.property  name="blokNo"
	 */
	public String getBlokNo() {
		return blokNo;
	}

	/**
	 * Setter of the property <tt>blokNo</tt>
	 * @param blokNo  The blokNo to set.
	 * @uml.property  name="blokNo"
	 */
	public void setBlokNo(String blokNo) {
		this.blokNo = blokNo;
	}

	/**
	 * @uml.property  name="bolumCins"
	 */
	private String bolumCins = "";

	/**
	 * Getter of the property <tt>bolumCins</tt>
	 * @return  Returns the bolumCins.
	 * @uml.property  name="bolumCins"
	 */
	public String getBolumCins() {
		return bolumCins;
	}

	/**
	 * Setter of the property <tt>bolumCins</tt>
	 * @param bolumCins  The bolumCins to set.
	 * @uml.property  name="bolumCins"
	 */
	public void setBolumCins(String bolumCins) {
		this.bolumCins = bolumCins;
	}

	/**
	 * @uml.property  name="bolumNo"
	 */
	private String bolumNo = "";

	/**
	 * Getter of the property <tt>bolumNo</tt>
	 * @return  Returns the bolumNo.
	 * @uml.property  name="bolumNo"
	 */
	public String getBolumNo() {
		return bolumNo;
	}

	/**
	 * Setter of the property <tt>bolumNo</tt>
	 * @param bolumNo  The bolumNo to set.
	 * @uml.property  name="bolumNo"
	 */
	public void setBolumNo(String bolumNo) {
		this.bolumNo = bolumNo;
	}

	/**
	 * @uml.property  name="cinsiyet"
	 */
	private String cinsiyet = "";

	/**
	 * Getter of the property <tt>cinsiyet</tt>
	 * @return  Returns the cinsiyet.
	 * @uml.property  name="cinsiyet"
	 */
	public String getCinsiyet() {
		return cinsiyet;
	}

	/**
	 * Setter of the property <tt>cinsiyet</tt>
	 * @param cinsiyet  The cinsiyet to set.
	 * @uml.property  name="cinsiyet"
	 */
	public void setCinsiyet(String cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	/**
	 * @uml.property  name="dogumTarih"
	 */
	private Date dogumTarih;

	/**
	 * Getter of the property <tt>dogumTarih</tt>
	 * @return  Returns the dogumTarih.
	 * @uml.property  name="dogumTarih"
	 */
	public Date getDogumTarih() {
		return dogumTarih;
	}

	/**
	 * Setter of the property <tt>dogumTarih</tt>
	 * @param dogumTarih  The dogumTarih to set.
	 * @uml.property  name="dogumTarih"
	 */
	public void setDogumTarih(Date dogumTarih) {
		this.dogumTarih = dogumTarih;
	}

	/**
	 * @uml.property  name="dogumYer"
	 */
	private String dogumYer = "";

	/**
	 * Getter of the property <tt>dogumYer</tt>
	 * @return  Returns the dogumYer.
	 * @uml.property  name="dogumYer"
	 */
	public String getDogumYer() {
		return dogumYer;
	}

	/**
	 * Setter of the property <tt>dogumYer</tt>
	 * @param dogumYer  The dogumYer to set.
	 * @uml.property  name="dogumYer"
	 */
	public void setDogumYer(String dogumYer) {
		this.dogumYer = dogumYer;
	}

	/**
	 * @uml.property  name="edinSebep"
	 */
	private String edinSebep = "";

	/**
	 * Getter of the property <tt>edinSebep</tt>
	 * @return  Returns the edinSebep.
	 * @uml.property  name="edinSebep"
	 */
	public String getEdinSebep() {
		return edinSebep;
	}

	/**
	 * Setter of the property <tt>edinSebep</tt>
	 * @param edinSebep  The edinSebep to set.
	 * @uml.property  name="edinSebep"
	 */
	public void setEdinSebep(String edinSebep) {
		this.edinSebep = edinSebep;
	}

	/**
	 * @uml.property  name="hissePay"
	 */
	private Double hissePay;

	/**
	 * Getter of the property <tt>hissePay</tt>
	 * @return  Returns the hissePay.
	 * @uml.property  name="hissePay"
	 */
	public Double getHissePay() {
		return hissePay;
	}

	/**
	 * Setter of the property <tt>hissePay</tt>
	 * @param hissePay  The hissePay to set.
	 * @uml.property  name="hissePay"
	 */
	public void setHissePay(Double hissePay) {
		this.hissePay = hissePay;
	}

	/**
	 * @uml.property  name="hissePayda"
	 */
	private Double hissePayda;

	/**
	 * Getter of the property <tt>hissePayda</tt>
	 * @return  Returns the hissePayda.
	 * @uml.property  name="hissePayda"
	 */
	public Double getHissePayda() {
		return hissePayda;
	}

	/**
	 * Setter of the property <tt>hissePayda</tt>
	 * @param hissePayda  The hissePayda to set.
	 * @uml.property  name="hissePayda"
	 */
	public void setHissePayda(Double hissePayda) {
		this.hissePayda = hissePayda;
	}

	/**
	 * @uml.property  name="iktisapTarih"
	 */
	private Date iktisapTarih;

	/**
	 * Getter of the property <tt>iktisapTarih</tt>
	 * @return  Returns the iktisapTarih.
	 * @uml.property  name="iktisapTarih"
	 */
	public Date getIktisapTarih() {
		return iktisapTarih;
	}

	/**
	 * Setter of the property <tt>iktisapTarih</tt>
	 * @param iktisapTarih  The iktisapTarih to set.
	 * @uml.property  name="iktisapTarih"
	 */
	public void setIktisapTarih(Date iktisapTarih) {
		this.iktisapTarih = iktisapTarih;
	}

	/**
	 * @uml.property  name="kapanma"
	 */
	private String kapanma = "";

	/**
	 * Getter of the property <tt>kapanma</tt>
	 * @return  Returns the kapanma.
	 * @uml.property  name="kapanma"
	 */
	public String getKapanma() {
		return kapanma;
	}

	/**
	 * Setter of the property <tt>kapanma</tt>
	 * @param kapanma  The kapanma to set.
	 * @uml.property  name="kapanma"
	 */
	public void setKapanma(String kapanma) {
		this.kapanma = kapanma;
	}

	/**
	 * @uml.property  name="kapiNo"
	 */
	private String kapiNo = "";

	/**
	 * Getter of the property <tt>kapiNo</tt>
	 * @return  Returns the kapiNo.
	 * @uml.property  name="kapiNo"
	 */
	public String getKapiNo() {
		return kapiNo;
	}

	/**
	 * Setter of the property <tt>kapiNo</tt>
	 * @param kapiNo  The kapiNo to set.
	 * @uml.property  name="kapiNo"
	 */
	public void setKapiNo(String kapiNo) {
		this.kapiNo = kapiNo;
	}

	/**
	 * @uml.property  name="katKutukZeminId"
	 */
	private Long katKutukZeminId;

	/**
	 * Getter of the property <tt>katKutukZeminId</tt>
	 * @return  Returns the katKutukZeminId.
	 * @uml.property  name="katKutukZeminId"
	 */
	public Long getKatKutukZeminId() {
		return katKutukZeminId;
	}

	/**
	 * Setter of the property <tt>katKutukZeminId</tt>
	 * @param katKutukZeminId  The katKutukZeminId to set.
	 * @uml.property  name="katKutukZeminId"
	 */
	public void setKatKutukZeminId(Long katKutukZeminId) {
		this.katKutukZeminId = katKutukZeminId;
	}

	/**
	 * @uml.property  name="katKutukCiltNo"
	 */
	private Long katKutukCiltNo;

	/**
	 * Getter of the property <tt>katKutukCiltNo</tt>
	 * @return  Returns the katKutukCiltNo.
	 * @uml.property  name="katKutukCiltNo"
	 */
	public Long getKatKutukCiltNo() {
		return katKutukCiltNo;
	}

	/**
	 * Setter of the property <tt>katKutukCiltNo</tt>
	 * @param katKutukCiltNo  The katKutukCiltNo to set.
	 * @uml.property  name="katKutukCiltNo"
	 */
	public void setKatKutukCiltNo(Long katKutukCiltNo) {
		this.katKutukCiltNo = katKutukCiltNo;
	}

	/**
	 * @uml.property  name="katKutukSayfaNo"
	 */
	private Long katKutukSayfaNo;

	/**
	 * Getter of the property <tt>katKutukSayfaNo</tt>
	 * @return  Returns the katKutukSayfaNo.
	 * @uml.property  name="katKutukSayfaNo"
	 */
	public Long getKatKutukSayfaNo() {
		return katKutukSayfaNo;
	}

	/**
	 * Setter of the property <tt>katKutukSayfaNo</tt>
	 * @param katKutukSayfaNo  The katKutukSayfaNo to set.
	 * @uml.property  name="katKutukSayfaNo"
	 */
	public void setKatKutukSayfaNo(Long katKutukSayfaNo) {
		this.katKutukSayfaNo = katKutukSayfaNo;
	}

	/**
	 * @uml.property  name="katNo"
	 */
	private String katNo = "";

	/**
	 * Getter of the property <tt>katNo</tt>
	 * @return  Returns the katNo.
	 * @uml.property  name="katNo"
	 */
	public String getKatNo() {
		return katNo;
	}

	/**
	 * Setter of the property <tt>katNo</tt>
	 * @param katNo  The katNo to set.
	 * @uml.property  name="katNo"
	 */
	public void setKatNo(String katNo) {
		this.katNo = katNo;
	}

	/**
	 * @uml.property  name="kayittar"
	 */
	private String kayittar = "";

	/**
	 * Getter of the property <tt>kayittar</tt>
	 * @return  Returns the kayittar.
	 * @uml.property  name="kayittar"
	 */
	public String getKayittar() {
		return kayittar;
	}

	/**
	 * Setter of the property <tt>kayittar</tt>
	 * @param kayittar  The kayittar to set.
	 * @uml.property  name="kayittar"
	 */
	public void setKayittar(String kayittar) {
		this.kayittar = kayittar;
	}

	/**
	 * @uml.property   name="kurumSabit"
	 * @uml.associationEnd   inverse="tapu:com.sampas.akos.ortak2.model.KurumSabit"
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
	 * @uml.property  name="mevki"
	 */
	private String mevki = "";

	/**
	 * Getter of the property <tt>mevki</tt>
	 * @return  Returns the mevki.
	 * @uml.property  name="mevki"
	 */
	public String getMevki() {
		return mevki;
	}

	/**
	 * Setter of the property <tt>mevki</tt>
	 * @param mevki  The mevki to set.
	 * @uml.property  name="mevki"
	 */
	public void setMevki(String mevki) {
		this.mevki = mevki;
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
	 * @uml.property  name="parsel"
	 */
	private String parsel = "";

	/**
	 * Getter of the property <tt>parsel</tt>
	 * @return  Returns the parsel.
	 * @uml.property  name="parsel"
	 */
	public String getParsel() {
		return parsel;
	}

	/**
	 * Setter of the property <tt>parsel</tt>
	 * @param parsel  The parsel to set.
	 * @uml.property  name="parsel"
	 */
	public void setParsel(String parsel) {
		this.parsel = parsel;
	}

	/**
	 * @uml.property  name="kadastroParsel"
	 * @uml.associationEnd  inverse="tapu:com.sampas.akos.ortak.model.KadastroParsel"
	 */
	private KadastroParsel kadastroParsel;

	/**
	 * Getter of the property <tt>kadastroParsel</tt>
	 * @return  Returns the kadastroParsel.
	 * @uml.property  name="kadastroParsel"
	 */
	public KadastroParsel getKadastroParsel() {
		return kadastroParsel;
	}

	/**
	 * Setter of the property <tt>kadastroParsel</tt>
	 * @param kadastroParsel  The kadastroParsel to set.
	 * @uml.property  name="kadastroParsel"
	 */
	public void setKadastroParsel(KadastroParsel kadastroParsel) {
		this.kadastroParsel = kadastroParsel;
	}

	/**
	 * @uml.property  name="soyad"
	 */
	private String soyad = "";

	/**
	 * Getter of the property <tt>soyad</tt>
	 * @return  Returns the soyad.
	 * @uml.property  name="soyad"
	 */
	public String getSoyad() {
		return soyad;
	}

	/**
	 * Setter of the property <tt>soyad</tt>
	 * @param soyad  The soyad to set.
	 * @uml.property  name="soyad"
	 */
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	/**
	 * @uml.property  name="tapuKat"
	 */
	private String tapuKat = "";

	/**
	 * Getter of the property <tt>tapuKat</tt>
	 * @return  Returns the tapuKat.
	 * @uml.property  name="tapuKat"
	 */
	public String getTapuKat() {
		return tapuKat;
	}

	/**
	 * Setter of the property <tt>tapuKat</tt>
	 * @param tapuKat  The tapuKat to set.
	 * @uml.property  name="tapuKat"
	 */
	public void setTapuKat(String tapuKat) {
		this.tapuKat = tapuKat;
	}

	/**
	 * @uml.property  name="tasinmazCins"
	 */
	private String tasinmazCins = "";

	/**
	 * Getter of the property <tt>tasinmazCins</tt>
	 * @return  Returns the tasinmazCins.
	 * @uml.property  name="tasinmazCins"
	 */
	public String getTasinmazCins() {
		return tasinmazCins;
	}

	/**
	 * Setter of the property <tt>tasinmazCins</tt>
	 * @param tasinmazCins  The tasinmazCins to set.
	 * @uml.property  name="tasinmazCins"
	 */
	public void setTasinmazCins(String tasinmazCins) {
		this.tasinmazCins = tasinmazCins;
	}

	/**
	 * @uml.property  name="tcKimlikNo"
	 */
	private Long tcKimlikNo;

	/**
	 * Getter of the property <tt>tcKimlikNo</tt>
	 * @return  Returns the tcKimlikNo.
	 * @uml.property  name="tcKimlikNo"
	 */
	public Long getTcKimlikNo() {
		return tcKimlikNo;
	}

	/**
	 * Setter of the property <tt>tcKimlikNo</tt>
	 * @param tcKimlikNo  The tcKimlikNo to set.
	 * @uml.property  name="tcKimlikNo"
	 */
	public void setTcKimlikNo(Long tcKimlikNo) {
		this.tcKimlikNo = tcKimlikNo;
	}

	/**
	 * @uml.property  name="yuzolcum"
	 */
	private Double yuzolcum;

	/**
	 * Getter of the property <tt>yuzolcum</tt>
	 * @return  Returns the yuzolcum.
	 * @uml.property  name="yuzolcum"
	 */
	public Double getYuzolcum() {
		return yuzolcum;
	}

	/**
	 * Setter of the property <tt>yuzolcum</tt>
	 * @param yuzolcum  The yuzolcum to set.
	 * @uml.property  name="yuzolcum"
	 */
	public void setYuzolcum(Double yuzolcum) {
		this.yuzolcum = yuzolcum;
	}

	/**
	 * @uml.property  name="tapuMulkiyetTip"
	 * @uml.associationEnd  inverse="tapu:com.sampas.akos.numarataj.model.TapuMulkiyetTip"
	 */
	private TapuMulkiyetTip tapuMulkiyetTip;

	/**
	 * Getter of the property <tt>tapuMulkiyetTip</tt>
	 * @return  Returns the tapuMulkiyetTip.
	 * @uml.property  name="tapuMulkiyetTip"
	 */
	public TapuMulkiyetTip getTapuMulkiyetTip() {
		return tapuMulkiyetTip;
	}

	/**
	 * Setter of the property <tt>tapuMulkiyetTip</tt>
	 * @param tapuMulkiyetTip  The tapuMulkiyetTip to set.
	 * @uml.property  name="tapuMulkiyetTip"
	 */
	public void setTapuMulkiyetTip(TapuMulkiyetTip tapuMulkiyetTip) {
		this.tapuMulkiyetTip = tapuMulkiyetTip;
	}

	/**
	 * @uml.property  name="ada"
	 */
	private String ada = "";

	/** 
	 * Getter of the property <tt>adi</tt>
	 * @return  Returns the adi.
	 * @uml.property  name="ada"
	 */
	public String getAda() {
		return ada;
	}

	/** 
	 * Setter of the property <tt>adi</tt>
	 * @param adi  The adi to set.
	 * @uml.property  name="ada"
	 */
	public void setAda(String ada) {
		this.ada = ada;
	}

}
