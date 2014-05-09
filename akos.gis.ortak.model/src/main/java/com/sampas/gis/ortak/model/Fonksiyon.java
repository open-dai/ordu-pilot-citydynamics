package com.sampas.gis.ortak.model;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;
import com.sampas.akos.ortak.model.Grafik;
import com.sampas.akos.ortak.model.KurumSabit;


@SuppressWarnings("serial")
public class Fonksiyon extends BaseObject implements IVersionable ,ITransactionable,IHistoricalable{

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
	 * @uml.property  name="plan"
	 */
	private Plan plan;

	/**
	 * Getter of the property <tt>plan</tt>
	 * @return  Returns the plan.
	 * @uml.property  name="plan"
	 */
	public Plan getPlan() {
		return plan;
	}

	/**
	 * Setter of the property <tt>plan</tt>
	 * @param plan  The plan to set.
	 * @uml.property  name="plan"
	 */
	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	/**
	 * @uml.property  name="binaYuksekligi"
	 */
	private Double binaYuksekligi;

	/**
	 * Getter of the property <tt>binaYuksekligi</tt>
	 * @return  Returns the binaYuksekligi.
	 * @uml.property  name="binaYuksekligi"
	 */
	public Double getBinaYuksekligi() {
		return binaYuksekligi;
	}

	/**
	 * Setter of the property <tt>binaYuksekligi</tt>
	 * @param binaYuksekligi  The binaYuksekligi to set.
	 * @uml.property  name="binaYuksekligi"
	 */
	public void setBinaYuksekligi(Double binaYuksekligi) {
		this.binaYuksekligi = binaYuksekligi;
	}

	/**
	 * @uml.property  name="binaDerinligi"
	 */
	private Double binaDerinligi;

	/**
	 * Getter of the property <tt>binaDerinligi</tt>
	 * @return  Returns the binaDerinligi.
	 * @uml.property  name="binaDerinligi"
	 */
	public Double getBinaDerinligi() {
		return binaDerinligi;
	}

	/**
	 * Setter of the property <tt>binaDerinligi</tt>
	 * @param binaDerinligi  The binaDerinligi to set.
	 * @uml.property  name="binaDerinligi"
	 */
	public void setBinaDerinligi(Double binaDerinligi) {
		this.binaDerinligi = binaDerinligi;
	}

	/**
	 * @uml.property  name="binaYuksekligi"
	 */
	private String binaYuksekligiAciklama;

	/**
	 * Getter of the property <tt>binaYuksekligi</tt>
	 * @return  Returns the binaYuksekligi.
	 * @uml.property  name="binaYuksekligi"
	 */
	public String getBinaYuksekligiAciklama() {
		return binaYuksekligiAciklama;
	}

	/**
	 * Setter of the property <tt>binaYuksekligi</tt>
	 * @param binaYuksekligi  The binaYuksekligi to set.
	 * @uml.property  name="binaYuksekligi"
	 */
	public void setBinaYuksekligiAciklama(String binaYuksekligiAciklama) {
		this.binaYuksekligiAciklama = binaYuksekligiAciklama;
	}
	
	/**
	 * @uml.property  name="binaDerinligi"
	 */
	private String binaDerinligiAciklama;

	/**
	 * Getter of the property <tt>binaDerinligi</tt>
	 * @return  Returns the binaDerinligi.
	 * @uml.property  name="binaDerinligi"
	 */
	public String getBinaDerinligiAciklama() {
		return binaDerinligiAciklama;
	}

	/**
	 * Setter of the property <tt>binaDerinligi</tt>
	 * @param binaDerinligi  The binaDerinligi to set.
	 * @uml.property  name="binaDerinligi"
	 */
	public void setBinaDerinligiAciklama(String binaDerinligiAciklama) {
		this.binaDerinligiAciklama = binaDerinligiAciklama;
	}	
	
	
	/**
	 * @uml.property  name="onBahceMesafesi"
	 */
	private Double onBahceMesafesi;

	/**
	 * Getter of the property <tt>onBahceMesafesi</tt>
	 * @return  Returns the onBahceMesafesi.
	 * @uml.property  name="onBahceMesafesi"
	 */
	public Double getOnBahceMesafesi() {
		return onBahceMesafesi;
	}

	/**
	 * Setter of the property <tt>onBahceMesafesi</tt>
	 * @param onBahceMesafesi  The onBahceMesafesi to set.
	 * @uml.property  name="onBahceMesafesi"
	 */
	public void setOnBahceMesafesi(Double onBahceMesafesi) {
		this.onBahceMesafesi = onBahceMesafesi;
	}

	/**
	 * @uml.property  name="yanBahceMesafesi"
	 */
	private Double yanBahceMesafesi;

	/**
	 * Getter of the property <tt>yanBahceMesafesi</tt>
	 * @return  Returns the yanBahceMesafesi.
	 * @uml.property  name="yanBahceMesafesi"
	 */
	public Double getYanBahceMesafesi() {
		return yanBahceMesafesi;
	}

	/**
	 * Setter of the property <tt>yanBahceMesafesi</tt>
	 * @param yanBahceMesafesi  The yanBahceMesafesi to set.
	 * @uml.property  name="yanBahceMesafesi"
	 */
	public void setYanBahceMesafesi(Double yanBahceMesafesi) {
		this.yanBahceMesafesi = yanBahceMesafesi;
	}

	/**
	 * @uml.property  name="arkaBahceMesafesi"
	 */
	private Double arkaBahceMesafesi;

	/**
	 * Getter of the property <tt>arkaBahceMesafesi</tt>
	 * @return  Returns the arkaBahceMesafesi.
	 * @uml.property  name="arkaBahceMesafesi"
	 */
	public Double getArkaBahceMesafesi() {
		return arkaBahceMesafesi;
	}

	/**
	 * Setter of the property <tt>arkaBahceMesafesi</tt>
	 * @param arkaBahceMesafesi  The arkaBahceMesafesi to set.
	 * @uml.property  name="arkaBahceMesafesi"
	 */
	public void setArkaBahceMesafesi(Double arkaBahceMesafesi) {
		this.arkaBahceMesafesi = arkaBahceMesafesi;
	}

	/**
	 * @uml.property  name="kotNoktasiNo"
	 */
	private String kotNoktasiNo = "";

	/**
	 * Getter of the property <tt>kotNoktasiNo</tt>
	 * @return  Returns the kotNoktasiNo.
	 * @uml.property  name="kotNoktasiNo"
	 */
	public String getKotNoktasiNo() {
		return kotNoktasiNo;
	}

	/**
	 * Setter of the property <tt>kotNoktasiNo</tt>
	 * @param kotNoktasiNo  The kotNoktasiNo to set.
	 * @uml.property  name="kotNoktasiNo"
	 */
	public void setKotNoktasiNo(String kotNoktasiNo) {
		this.kotNoktasiNo = kotNoktasiNo;
	}

	/**
	 * @uml.property  name="insaatNizami"
	 */
	private String insaatNizami = "";

	/**
	 * Getter of the property <tt>insaatNizami</tt>
	 * @return  Returns the insaatNizami.
	 * @uml.property  name="insaatNizami"
	 */
	public String getInsaatNizami() {
		return insaatNizami;
	}

	/**
	 * Setter of the property <tt>insaatNizami</tt>
	 * @param insaatNizami  The insaatNizami to set.
	 * @uml.property  name="insaatNizami"
	 */
	public void setInsaatNizami(String insaatNizami) {
		this.insaatNizami = insaatNizami;
	}

	/**
	 * @uml.property  name="taks"
	 */
	private String taks = "";

	/**
	 * Getter of the property <tt>taks</tt>
	 * @return  Returns the taks.
	 * @uml.property  name="taks"
	 */
	public String getTaks() {
		return taks;
	}

	/**
	 * Setter of the property <tt>taks</tt>
	 * @param taks  The taks to set.
	 * @uml.property  name="taks"
	 */
	public void setTaks(String taks) {
		this.taks = taks;
	}

	/**
	 * @uml.property  name="kaks"
	 */
	private String kaks = "";

	/**
	 * Getter of the property <tt>kaks</tt>
	 * @return  Returns the kaks.
	 * @uml.property  name="kaks"
	 */
	public String getKaks() {
		return kaks;
	}

	/**
	 * Setter of the property <tt>kaks</tt>
	 * @param kaks  The kaks to set.
	 * @uml.property  name="kaks"
	 */
	public void setKaks(String kaks) {
		this.kaks = kaks;
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
	 * @uml.property  name="kurumSabit"
	 * @uml.associationEnd  inverse="adres:com.sampas.akos.ortak.model.KurumSabit"
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
	
	private String onBahceMesafesiAciklama = "";
	
	public String getOnBahceMesafesiAciklama() {
		return onBahceMesafesiAciklama;
	}

	public void setOnBahceMesafesiAciklama(String onBahceMesafesiAciklama) {
		this.onBahceMesafesiAciklama = onBahceMesafesiAciklama;
	}

	private String yanBahceMesafesiAciklama = "";
	
	public String getYanBahceMesafesiAciklama() {
		return yanBahceMesafesiAciklama;
	}

	public void setYanBahceMesafesiAciklama(String yanBahceMesafesiAciklama) {
		this.yanBahceMesafesiAciklama = yanBahceMesafesiAciklama;
	}

	private String arkaBahceMesafesiAciklama = "";
	
	public String getArkaBahceMesafesiAciklama() {
		return arkaBahceMesafesiAciklama;
	}

	public void setArkaBahceMesafesiAciklama(String arkaBahceMesafesiAciklama) {
		this.arkaBahceMesafesiAciklama = arkaBahceMesafesiAciklama;
	}
	
	private Long katAdedi;
	
	public Long getKatAdedi() {
		return katAdedi;
	}
	
	private String katAdediAciklama;

	public String getKatAdediAciklama() {
		return katAdediAciklama;
	}

	public void setKatAdediAciklama(String katAdediAciklama) {
		this.katAdediAciklama = katAdediAciklama;
	}

	public void setKatAdedi(Long katAdedi) {
		this.katAdedi = katAdedi;
	}
	
	private String fonksiyonAdi;

	public String getFonksiyonAdi() {
		return fonksiyonAdi;
	}

	public void setFonksiyonAdi(String fonksiyonAdi) {
		this.fonksiyonAdi = fonksiyonAdi;
	}
	
	private Double emsal;

	public Double getEmsal() {
		return emsal;
	}

	public void setEmsal(Double emsal) {
		this.emsal = emsal;
	}
	
	private Double tahsisAlani;

	public Double getTahsisAlani() {
		return tahsisAlani;
	}

	public void setTahsisAlani(Double tahsisAlani) {
		this.tahsisAlani = tahsisAlani;
	}
	
	private Double yogunluk;

	public Double getYogunluk() {
		return yogunluk;
	}

	public void setYogunluk(Double yogunluk) {
		this.yogunluk = yogunluk;
	}
	
	private Double toplamInsaatAlani;
	
	public Double getToplamInsaatAlani() {
		return toplamInsaatAlani;
	}

	public void setToplamInsaatAlani(Double toplamInsaatAlani) {
		this.toplamInsaatAlani = toplamInsaatAlani;
	}

	private String kaksAciklama;
	
	public String getKaksAciklama() {
		return kaksAciklama;
	}

	public void setKaksAciklama(String kaksAciklama) {
		this.kaksAciklama = kaksAciklama;
	}

	private String taksAciklama;

	public String getTaksAciklama() {
		return taksAciklama;
	}

	public void setTaksAciklama(String taksAciklama) {
		this.taksAciklama = taksAciklama;
	}
	
	private String meclisKararOnay;

	public String getMeclisKararOnay() {
		return meclisKararOnay;
	}

	public void setMeclisKararOnay(String meclisKararOnay) {
		this.meclisKararOnay = meclisKararOnay;
	}
	
	private Double ifrazAlan;

	public void setIfrazAlan(Double data)
	{
		this.ifrazAlan=data;
	}
	public Double getIfrazAlan()
	{
	return this.ifrazAlan;
	}
	
	private Double ifrazCephe;
	public void setIfrazCephe(Double data)
	{
		this.ifrazCephe=data;
	}
	public Double getIfrazCephe()
	{
	return this.ifrazCephe;
	}
	
	private Double ifrazDerinlik;
	public void setIfrazDerinlik(Double data)
	{
		this.ifrazDerinlik=data;
	}
	public Double getIfrazDerinlik()
	{
	return this.ifrazDerinlik;
	}
	
	private String ifrazAlanAciklama;
	private String ifrazCepheAciklama;
	private String ifrazDerinlikAciklama;
	
	public void setIfrazAlanAciklama(String data)
	{
		this.ifrazAlanAciklama=data;
	}
	public String getIfrazAlanAciklama()
	{
	return this.ifrazAlanAciklama;
	}		
	public void setIfrazCepheAciklama(String data)
	{
		this.ifrazCepheAciklama=data;
	}
	public String getIfrazCepheAciklama()
	{
	return this.ifrazCepheAciklama;
	}		
	public void setIfrazDerinlikAciklama(String data)
	{
		this.ifrazDerinlikAciklama=data;
	}
	public String getIfrazDerinlikAciklama()
	{
	return this.ifrazDerinlikAciklama;
	}
	private String insaatNizamiAciklama;
	public void setInsaatNizamiAciklama(String data)
	{
		this.insaatNizamiAciklama=data;
	}
	public String getInsaatNizamiAciklama()
	{
	return this.insaatNizamiAciklama;
	}
}
