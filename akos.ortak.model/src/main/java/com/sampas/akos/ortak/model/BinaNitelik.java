package com.sampas.akos.ortak.model;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;


@SuppressWarnings("serial")
public class BinaNitelik extends BaseObject implements IVersionable, ITransactionable, IHistoricalable{

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
	 * @uml.property  name="bina"
	 */
	private Bina bina;

	/**
	 * Getter of the property <tt>bina</tt>
	 * @return Returns the bina.
	 * @uml.property  name="bina"
	 */
	public Bina getBina() {
		return bina;
	}

	/**
	 * Setter of the property <tt>bina</tt>
	 * @param bina  The bina to set.
	 * @uml.property  name="bina"
	 */
	public void setBina(Bina bina) {
		this.bina = bina;
	}
	
	/**
	 * @uml.property  name="benzerYapiSayisi"
	 */
	private Integer benzerYapiSayisi;

	/**
	 * Getter of the property <tt>benzerYapiSayisi</tt>
	 * @return  Returns the benzerYapiSayisi.
	 * @uml.property  name="benzerYapiSayisi"
	 */
	public Integer getBenzerYapiSayisi() {
		return benzerYapiSayisi;
	}

	/**
	 * Setter of the property <tt>benzerYapiSayisi</tt>
	 * @param benzerYapiSayisi The benzerYapiSayisi to set.
	 * @uml.property  name="benzerYapiSayisi"
	 */
	public void setBenzerYapiSayisi(Integer benzerYapiSayisi) {
		this.benzerYapiSayisi = benzerYapiSayisi;
	}
	
	/**
	 * @uml.property  name="bagimsizBolumSayisi"
	 */
	private Integer bagimsizBolumSayisi;

	/**
	 * Getter of the property <tt>bagimsizBolumSayisi</tt>
	 * @return  Returns the bagimsizBolumSayisi.
	 * @uml.property  name="bagimsizBolumSayisi"
	 */
	public Integer getBagimsizBolumSayisi() {
		return bagimsizBolumSayisi;
	}
	
	/**
	 * Setter of the property <tt>bagimsizBolumSayisi</tt>
	 * @param bagimsizBolumSayisi The bagimsizBolumSayisi to set.
	 * @uml.property  name="bagimsizBolumSayisi"
	 */
	public void setBagimsizBolumSayisi(Integer bagimsizBolumSayisi) {
		this.bagimsizBolumSayisi = bagimsizBolumSayisi;
	}
	
	/**
	 * @uml.property  name="konutDaireSayisi"
	 */
	private Integer konutDaireSayisi;

	/**
	 * Getter of the property <tt>konutDaireSayisi</tt>
	 * @return  Returns the konutDaireSayisi.
	 * @uml.property  name="konutDaireSayisi"
	 */
	public Integer getKonutDaireSayisi() {
		return konutDaireSayisi;
	}
	
	/**
	 * Setter of the property <tt>konutDaireSayisi</tt>
	 * @param konutDaireSayisi The konutDaireSayisi to set.
	 * @uml.property  name="konutDaireSayisi"
	 */
	public void setKonutDaireSayisi(Integer konutDaireSayisi) {
		this.konutDaireSayisi = konutDaireSayisi;
	}
	
	/**
	 * @uml.property  name="yolKotuAltiKatSayisi"
	 */
	private Integer yolKotuAltiKatSayisi;

	/**
	 * Getter of the property <tt>yolKotuAltiKatSayisi</tt>
	 * @return  Returns the yolKotuAltiKatSayisi.
	 * @uml.property  name="yolKotuAltiKatSayisi"
	 */
	public Integer getYolKotuAltiKatSayisi() {
		return yolKotuAltiKatSayisi;
	}
	
	/**
	 * Setter of the property <tt>yolKotuAltiKatSayisi</tt>
	 * @param yolKotuAltiKatSayisi The yolKotuAltiKatSayisi to set.
	 * @uml.property  name="yolKotuAltiKatSayisi"
	 */
	public void setYolKotuAltiKatSayisi(Integer yolKotuAltiKatSayisi) {
		this.yolKotuAltiKatSayisi = yolKotuAltiKatSayisi;
	}

	/**
	 * @uml.property  name="yolKotuUstuKatSayisi"
	 */
	private Integer yolKotuUstuKatSayisi;

	/**
	 * Getter of the property <tt>yolKotuUstuKatSayisi</tt>
	 * @return  Returns the yolKotuUstuKatSayisi.
	 * @uml.property  name="yolKotuUstuKatSayisi"
	 */
	public Integer getYolKotuUstuKatSayisi() {
		return yolKotuUstuKatSayisi;
	}
	
	/**
	 * Setter of the property <tt>yolKotuUstuKatSayisi</tt>
	 * @param yolKotuUstuKatSayisi The yolKotuUstuKatSayisi to set.
	 * @uml.property  name="yolKotuUstuKatSayisi"
	 */
	public void setYolKotuUstuKatSayisi(Integer yolKotuUstuKatSayisi) {
		this.yolKotuUstuKatSayisi = yolKotuUstuKatSayisi;
	}
	
	/**
	 * @uml.property  name="ilaveYukseklik"
	 */
	private Double ilaveYukseklik;

	/**
	 * Getter of the property <tt>ilaveYukseklik</tt>
	 * @return  Returns the ilaveYukseklik.
	 * @uml.property  name="ilaveYukseklik"
	 */
	public Double getIlaveYukseklik() {
		return ilaveYukseklik;
	}
	
	/**
	 * Setter of the property <tt>ilaveYukseklik</tt>
	 * @param ilaveYukseklik The ilaveYukseklik to set.
	 * @uml.property  name="ilaveYukseklik"
	 */
	public void setIlaveYukseklik(Double ilaveYukseklik) {
		this.ilaveYukseklik = ilaveYukseklik;
	}
	
	/**
	 * @uml.property  name="toplamYukseklik"
	 */
	private Double toplamYukseklik;

	/**
	 * Getter of the property <tt>toplamYukseklik</tt>
	 * @return  Returns the toplamYukseklik.
	 * @uml.property  name="toplamYukseklik"
	 */
	public Double getToplamYukseklik() {
		return toplamYukseklik;
	}
	
	/**
	 * Setter of the property <tt>toplamYukseklik</tt>
	 * @param toplamYukseklik The toplamYukseklik to set.
	 * @uml.property  name="toplamYukseklik"
	 */
	public void setToplamYukseklik(Double toplamYukseklik) {
		this.toplamYukseklik = toplamYukseklik;
	}
	
	/**
	 * @uml.property  name="insaatAlani"
	 */
	private Double insaatAlani;

	/**
	 * Getter of the property <tt>insaatAlani</tt>
	 * @return  Returns the insaatAlani.
	 * @uml.property  name="insaatAlani"
	 */
	public Double getInsaatAlani() {
		return insaatAlani;
	}
	
	/**
	 * Setter of the property <tt>insaatAlani</tt>
	 * @param insaatAlani The insaatAlani to set.
	 * @uml.property  name="insaatAlani"
	 */
	public void setInsaatAlani(Double insaatAlani) {
		this.insaatAlani = insaatAlani;
	}
	
	/**
	 * @uml.property  name="toplamMaliyeti"
	 */
	private Double toplamMaliyeti;

	/**
	 * Getter of the property <tt>toplamMaliyeti</tt>
	 * @return  Returns the toplamMaliyeti.
	 * @uml.property  name="toplamMaliyeti"
	 */
	public Double getToplamMaliyeti() {
		return toplamMaliyeti;
	}
	
	/**
	 * Setter of the property <tt>toplamMaliyeti</tt>
	 * @param toplamMaliyeti The toplamMaliyeti to set.
	 * @uml.property  name="toplamMaliyeti"
	 */
	public void setToplamMaliyeti(Double toplamMaliyeti) {
		this.toplamMaliyeti = toplamMaliyeti;
	}
	
	/**
	 * @uml.property  name="icmeSuTuru"
	 */
	private Integer icmeSuTuru;

	/**
	 * Getter of the property <tt>icmeSuTuru</tt>
	 * @return  Returns the icmeSuTuru.
	 * @uml.property  name="icmeSuTuru"
	 */
	public Integer getIcmeSuTuru() {
		return icmeSuTuru;
	}
	
	/**
	 * Setter of the property <tt>icmeSuTuru</tt>
	 * @param icmeSuTuru The icmeSuTuru to set.
	 * @uml.property  name="icmeSuTuru"
	 */
	public void setIcmeSuTuru(Integer icmeSuTuru) {
		this.icmeSuTuru = icmeSuTuru;
	}
	
	/**
	 * @uml.property  name="atikSuTuru"
	 */
	private Integer atikSuTuru;

	/**
	 * Getter of the property <tt>atikSuTuru</tt>
	 * @return  Returns the atikSuTuru.
	 * @uml.property  name="atikSuTuru"
	 */
	public Integer getAtikSuTuru() {
		return atikSuTuru;
	}

	
	/**
	 * Setter of the property <tt>atikSuTuru</tt>
	 * @param atikSuTuru The atikSuTuru to set.
	 * @uml.property  name="atikSuTuru"
	 */
	public void setAtikSuTuru(Integer atikSuTuru) {
		this.atikSuTuru = atikSuTuru;
	}
	
	/**
	 * @uml.property  name="bosEh"
	 */
	private String bosEh;

	/**
	 * Getter of the property <tt>bosEh</tt>
	 * @return  Returns the bosEh.
	 * @uml.property  name="bosEh"
	 */
	public String getBosEh() {
		return bosEh;
	}
	
	/**
	 * Setter of the property <tt>bosEh</tt>
	 * @param bosEh The bosEh to set.
	 * @uml.property  name="bosEh"
	 */
	public void setBosEh(String bosEh) {
		this.bosEh = bosEh;
	}
	
	/**
	 * @uml.property  name="ulusalBinaNo"
	 */
	private String ulusalBinaNo;

	/**
	 * Getter of the property <tt>ulusalBinaNo</tt>
	 * @return  Returns the ulusalBinaNo.
	 * @uml.property  name="ulusalBinaNo"
	 */
	public String getUlusalBinaNo() {
		return ulusalBinaNo;
	}
	
	/**
	 * Setter of the property <tt>ulusalBinaNo</tt>
	 * @param ulusalBinaNo The ulusalBinaNo to set.
	 * @uml.property  name="ulusalBinaNo"
	 */
	public void setUlusalBinaNo(String ulusalBinaNo) {
		this.ulusalBinaNo = ulusalBinaNo;
	}
	
	/**
	 * @uml.property  name="tabanAlani"
	 */
	private Double tabanAlani;

	/**
	 * Getter of the property <tt>tabanAlani</tt>
	 * @return  Returns the tabanAlani.
	 * @uml.property  name="tabanAlani"
	 */
	public Double getTabanAlani() {
		return tabanAlani;
	}
	
	/**
	 * Setter of the property <tt>ulusalBinaNo</tt>
	 * @param tabanAlani The tabanAlani to set.
	 * @uml.property  name="tabanAlani"
	 */
	public void setTabanAlani(Double tabanAlani) {
		this.tabanAlani = tabanAlani;
	}
	
	/**
	 * @uml.property  name="tabanAlani"
	 */
	private String aciklama;

	/**
	 * Getter of the property <tt>tabanAlani</tt>
	 * @return  Returns the tabanAlani.
	 * @uml.property  name="tabanAlani"
	 */
	public String getAciklama() {
		return aciklama;
	}
	
	/**
	 * Setter of the property <tt>tabanAlani</tt>
	 * @param tabanAlani The tabanAlani to set.
	 * @uml.property  name="tabanAlani"
	 */
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	
	/**
	 * @uml.property  name="ilaveKatSayisi"
	 */
	private Integer ilaveKatSayisi;

	/**
	 * Getter of the property <tt>ilaveKatSayisi</tt>
	 * @return  Returns the ilaveKatSayisi.
	 * @uml.property  name="ilaveKatSayisi"
	 */
	public Integer getIlaveKatSayisi() {
		return ilaveKatSayisi;
	}

	/**
	 * Setter of the property <tt>ilaveKatSayisi</tt>
	 * @param ilaveKatSayisi The ilaveKatSayisi to set.
	 * @uml.property  name="ilaveKatSayisi"
	 */
	public void setIlaveKatSayisi(Integer ilaveKatSayisi) {
		this.ilaveKatSayisi = ilaveKatSayisi;
	}
	
	/**
	 * @uml.property  name="toplamKatSayisi"
	 */
	private Integer toplamKatSayisi;

	/**
	 * Getter of the property <tt>toplamKatSayisi</tt>
	 * @return  Returns the toplamKatSayisi.
	 * @uml.property  name="toplamKatSayisi"
	 */
	public Integer getToplamKatSayisi() {
		return toplamKatSayisi;
	}
	
	/**
	 * Setter of the property <tt>toplamKatSayisi</tt>
	 * @param toplamKatSayisi The toplamKatSayisi to set.
	 * @uml.property  name="toplamKatSayisi"
	 */
	public void setToplamKatSayisi(Integer toplamKatSayisi) {
		this.toplamKatSayisi = toplamKatSayisi;
	}
	
	/**
	 * @uml.property  name="toplamKatSayisi"
	 */
	private Double yolKotuAltiYukseklik;

	/**
	 * Getter of the property <tt>toplamKatSayisi</tt>
	 * @return  Returns the toplamKatSayisi.
	 * @uml.property  name="toplamKatSayisi"
	 */
	public Double getYolKotuAltiYukseklik() {
		return yolKotuAltiYukseklik;
	}
	
	/**
	 * Setter of the property <tt>toplamKatSayisi</tt>
	 * @param toplamKatSayisi The toplamKatSayisi to set.
	 * @uml.property  name="toplamKatSayisi"
	 */
	public void setYolKotuAltiYukseklik(Double yolKotuAltiYukseklik) {
		this.yolKotuAltiYukseklik = yolKotuAltiYukseklik;
	}
	
	/**
	 * @uml.property  name="yolKotuUstuYukseklik"
	 */
	private Double yolKotuUstuYukseklik;

	/**
	 * Getter of the property <tt>yolKotuUstuYukseklik</tt>
	 * @return  Returns the yolKotuUstuYukseklik.
	 * @uml.property  name="yolKotuUstuYukseklik"
	 */
	public Double getYolKotuUstuYukseklik() {
		return yolKotuUstuYukseklik;
	}
	
	/**
	 * Setter of the property <tt>yolKotuUstuYukseklik</tt>
	 * @param yolKotuUstuYukseklik The yolKotuUstuYukseklik to set.
	 * @uml.property  name="yolKotuUstuYukseklik"
	 */
	public void setYolKotuUstuYukseklik(Double yolKotuUstuYukseklik) {
		this.yolKotuUstuYukseklik = yolKotuUstuYukseklik;
	}
	
	/**
	 * @uml.property  name="tarihiEserEh"
	 */
	private String tarihiEserEh = "";

	/**
	 * Getter of the property <tt>tarihiEserEh</tt>
	 * @return  Returns the tarihiEserEh.
	 * @uml.property  name="tarihiEserEh"
	 */
	public String getTarihiEserEh() {
		return tarihiEserEh;
	}

	/**
	 * Setter of the property <tt>tarihiEserEh</tt>
	 * @param tarihiEserEh  The tarihiEserEh to set.
	 * @uml.property  name="tarihiEserEh"
	 */
	public void setTarihiEserEh(String tarihiEserEh) {
		this.tarihiEserEh = tarihiEserEh;
	}
}
