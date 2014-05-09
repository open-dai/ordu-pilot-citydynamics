package com.sampas.akos.ortak.model;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;
import java.util.Date;

import org.hibernate.envers.Audited;


@SuppressWarnings("serial")
@Audited
public class Bina extends BaseObject implements IVersionable, ITransactionable, IHistoricalable{

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
	 * @uml.property  name="kurumSabit"
	 * @uml.associationEnd  inverse="bina:com.sampas.akos.ortak.model.KurumSabit"
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
	 * @uml.property  name="yapiAda"
	 * @uml.associationEnd  inverse="bina:com.sampas.akos.ortak.model.YapiAda"
	 */
	private YapiAda yapiAda;

	/**
	 * Getter of the property <tt>yapiAda</tt>
	 * @return  Returns the yapiAda.
	 * @uml.property  name="yapiAda"
	 */
	public YapiAda getYapiAda() {
		return yapiAda;
	}

	/**
	 * Setter of the property <tt>yapiAda</tt>
	 * @param yapiAda  The yapiAda to set.
	 * @uml.property  name="yapiAda"
	 */
	public void setYapiAda(YapiAda yapiAda) {
		this.yapiAda = yapiAda;
	}

	/**
	 * @uml.property  name="site"
	 * @uml.associationEnd  inverse="bina:com.sampas.akos.ortak.model.Site"
	 */
	private Site site;

	/**
	 * Getter of the property <tt>site</tt>
	 * @return  Returns the site.
	 * @uml.property  name="site"
	 */
	public Site getSite() {
		return site;
	}

	/**
	 * Setter of the property <tt>site</tt>
	 * @param site  The site to set.
	 * @uml.property  name="site"
	 */
	public void setSite(Site site) {
		this.site = site;
	}
	
	/**
	 * @uml.property  name="kaloriferEh"
	 */
	private String kaloriferEh = "";

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
	 * @param zoneNo  The zoneNo to set.
	 * @uml.property  name="zoneNo"
	 */
	public void setZoneNo(Long zoneNo) {
		this.zoneNo = zoneNo;
	}
	
	/**
	 * @uml.property  name="kadastroParsel"
	 */
	private KadastroParsel kadastroParsel;

	/**
	 * Getter of the property <tt>id</tt>
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
	 * @uml.property  name="uretimKaynak"
	 */
	private UretimKaynak uretimKaynak;

	/**
	 * Getter of the property <tt>uretimKaynak</tt>
	 * @return  Returns the uretimKaynak.
	 * @uml.property  name="uretimKaynak"
	 */
	public UretimKaynak getUretimKaynak() {
		return uretimKaynak;
	}

	/**
	 * Setter of the property <tt>uretimKaynak</tt>
	 * @param uretimKaynak  The uretimKaynak to set.
	 * @uml.property  name="uretimKaynak"
	 */
	public void setUretimKaynak(UretimKaynak uretimKaynak) {
		this.uretimKaynak = uretimKaynak;
	}

	/**
	 * @uml.property  name="bilgiTipi"
	 */
	private BilgiTipi bilgiTipi;

	/**
	 * Getter of the property <tt>bilgiTipi</tt>
	 * @return  Returns the bilgiTipi.
	 * @uml.property  name="bilgiTipi"
	 */
	public BilgiTipi getBilgiTipi() {
		return bilgiTipi;
	}

	/**
	 * Setter of the property <tt>bilgiTipi</tt>
	 * @param bilgiTipi  The bilgiTipi to set.
	 * @uml.property  name="bilgiTipi"
	 */
	public void setBilgiTipi(BilgiTipi bilgiTipi) {
		this.bilgiTipi = bilgiTipi;
	}
	
	/**
	 * @uml.property  name="binaCatiTur"
	 */
	private BinaCatiTur binaCatiTur;

	/**
	 * Getter of the property <tt>binaCatiTur</tt>
	 * @return  Returns the binaCatiTur.
	 * @uml.property  name="binaCatiTur"
	 */
	public BinaCatiTur getBinaCatiTur() {
		return binaCatiTur;
	}

	/**
	 * Setter of the property <tt>binaCatiTur</tt>
	 * @param binaCatiTur  The binaCatiTur to set.
	 * @uml.property  name="binaCatiTur"
	 */
	public void setBinaCatiTur(BinaCatiTur binaCatiTur) {
		this.binaCatiTur = binaCatiTur;
	}
	
	/**
	 * @uml.property  name="yapiCepheTur"
	 */
	private YapiCepheTur yapiCepheTur;

	/**
	 * Getter of the property <tt>yapiCepheTur</tt>
	 * @return  Returns the yapiCepheTur.
	 * @uml.property  name="yapiCepheTur"
	 */
	public YapiCepheTur getYapiCepheTur() {
		return yapiCepheTur;
	}

	/**
	 * Setter of the property <tt>yapiCepheTur</tt>
	 * @param yapiCepheTur  The yapiCepheTur to set.
	 * @uml.property  name="yapiCepheTur"
	 */
	public void setYapiCepheTur(YapiCepheTur yapiCepheTur) {
		this.yapiCepheTur = yapiCepheTur;
	}
	
	/**
	 * @uml.property  name="dosemeTur"
	 */
	private DosemeTur dosemeTur;

	/**
	 * Getter of the property <tt>dosemeTur</tt>
	 * @return  Returns the dosemeTur.
	 * @uml.property  name="dosemeTur"
	 */
	public DosemeTur getDosemeTur() {
		return dosemeTur;
	}

	/**
	 * Setter of the property <tt>dosemeTur</tt>
	 * @param dosemeTur  The dosemeTur to set.
	 * @uml.property  name="dosemeTur"
	 */
	public void setDosemeTur(DosemeTur dosemeTur) {
		this.dosemeTur = dosemeTur;
	}
	
	/**
	 * @uml.property  name="isitmaTur"
	 */
	private IsitmaTur isitmaTur;

	/**
	 * Getter of the property <tt>isitmaTur</tt>
	 * @return  Returns the isitmaTur.
	 * @uml.property  name="isitmaTur"
	 */
	public IsitmaTur getIsitmaTur() {
		return isitmaTur;
	}

	/**
	 * Setter of the property <tt>isitmaTur</tt>
	 * @param isitmaTur The isitmaTur to set.
	 * @uml.property  name="isitmaTur"
	 */
	public void setIsitmaTur(IsitmaTur isitmaTur) {
		this.isitmaTur = isitmaTur;
	}
	
	/**
	 * @uml.property  name="isitmaYakitTur"
	 */
	private YakitTur isitmaYakitTur;

	/**
	 * Getter of the property <tt>isitmaYakitTur</tt>
	 * @return  Returns the isitmaYakitTur.
	 * @uml.property  name="isitmaYakitTur"
	 */
	public YakitTur getIsitmaYakitTur() {
		return isitmaYakitTur;
	}

	/**
	 * Setter of the property <tt>isitmaYakitTur</tt>
	 * @param isitmaYakitTur The isitmaYakitTur to set.
	 * @uml.property  name="isitmaYakitTur"
	 */
	public void setIsitmaYakitTur(YakitTur isitmaYakitTur) {
		this.isitmaYakitTur = isitmaYakitTur;
	}
	
	/**
	 * @uml.property  name="sicakSuTeminTur"
	 */
	private SicakSuTeminTur sicakSuTeminTur;

	/**
	 * Getter of the property <tt>sicakSuTeminTur</tt>
	 * @return  Returns the sicakSuTeminTur.
	 * @uml.property  name="sicakSuTeminTur"
	 */
	public SicakSuTeminTur getSicakSuTeminTur() {
		return sicakSuTeminTur;
	}

	/**
	 * Setter of the property <tt>sicakSuTeminTur</tt>
	 * @param sicakSuTeminTur The sicakSuTeminTur to set.
	 * @uml.property  name="sicakSuTeminTur"
	 */
	public void setSicakSuTeminTur(SicakSuTeminTur sicakSuTeminTur) {
		this.sicakSuTeminTur = sicakSuTeminTur;
	}
	
	/**
	 * @uml.property  name="sicakSuYakit"
	 */
	private YakitTur sicakSuYakit;

	/**
	 * Getter of the property <tt>sicakSuYakit</tt>
	 * @return  Returns the sicakSuYakit.
	 * @uml.property  name="sicakSuYakit"
	 */
	public YakitTur getSicakSuYakit() {
		return sicakSuYakit;
	}

	/**
	 * Setter of the property <tt>sicakSuYakit</tt>
	 * @param sicakSuYakit The sicakSuYakit to set.
	 * @uml.property  name="sicakSuYakit"
	 */
	public void setSicakSuYakit(YakitTur sicakSuYakit) {
		this.sicakSuYakit = sicakSuYakit;
	}
	
	/**
	 * @uml.property  name="tasiyiciSistem"
	 */
	private TasiyiciSistem tasiyiciSistem;

	/**
	 * Getter of the property <tt>tasiyiciSistem</tt>
	 * @return  Returns the tasiyiciSistem.
	 * @uml.property  name="tasiyiciSistem"
	 */
	public TasiyiciSistem getTasiyiciSistem() {
		return tasiyiciSistem;
	}

	/**
	 * Setter of the property <tt>tasiyiciSistem</tt>
	 * @param tasiyiciSistem The tasiyiciSistem to set.
	 * @uml.property  name="tasiyiciSistem"
	 */
	public void setTasiyiciSistem(TasiyiciSistem tasiyiciSistem) {
		this.tasiyiciSistem = tasiyiciSistem;
	}	
	
	/**
	 * @uml.property  name="bagimsizKullanimSinif"
	 */
	private BagimsizKullanimSinif bagimsizKullanimSinif;

	/**
	 * Getter of the property <tt>bagimsizKullanimSinif</tt>
	 * @return  Returns the bagimsizKullanimSinif.
	 * @uml.property  name="bagimsizKullanimSinif"
	 */
	public BagimsizKullanimSinif getBagimsizKullanimSinif() {
		return bagimsizKullanimSinif;
	}

	/**
	 * Setter of the property <tt>bagimsizKullanimSinif</tt>
	 * @param bagimsizKullanimSinif  The bagimsizKullanimSinif to set.
	 * @uml.property  name="bagimsizKullanimSinif"
	 */
	public void setBagimsizKullanimSinif(BagimsizKullanimSinif bagimsizKullanimSinif) {
		this.bagimsizKullanimSinif = bagimsizKullanimSinif;
	}

	/**
	 * @uml.property  name="insaatSinif"
	 * @uml.associationEnd  inverse="bina:com.sampas.akos.ortak.model.InsaatSinif"
	 */
	private InsaatSinif insaatSinif;

	/**
	 * Getter of the property <tt>insaatSinif</tt>
	 * @return  Returns the insaatSinif.
	 * @uml.property  name="insaatSinif"
	 */
	public InsaatSinif getInsaatSinif() {
		return insaatSinif;
	}

	/**
	 * Setter of the property <tt>insaatSinif</tt>
	 * @param insaatSinif  The insaatSinif to set.
	 * @uml.property  name="insaatSinif"
	 */
	public void setInsaatSinif(InsaatSinif insaatSinif) {
		this.insaatSinif = insaatSinif;
	}

	/**
	 * @uml.property  name="insaatTur"
	 * @uml.associationEnd  inverse="bina:com.sampas.akos.ortak.model.InsaatTur"
	 */
	private InsaatTur insaatTur;

	/**
	 * Getter of the property <tt>insaatTur</tt>
	 * @return  Returns the insaatTur.
	 * @uml.property  name="insaatTur"
	 */
	public InsaatTur getInsaatTur() {
		return insaatTur;
	}

	/**
	 * Setter of the property <tt>insaatTur</tt>
	 * @param insaatTur  The insaatTur to set.
	 * @uml.property  name="insaatTur"
	 */
	public void setInsaatTur(InsaatTur insaatTur) {
		this.insaatTur = insaatTur;
	}

	
	/**
	 * @uml.property  name="duvarDolguMadde"
	 * @uml.associationEnd  inverse="bina:com.sampas.akos.ortak.model.DuvarDolguMadde"
	 */
	private DuvarDolguMadde duvarDolguMadde;

	/**
	 * Getter of the property <tt>duvarDolguMadde</tt>
	 * @return  Returns the duvarDolguMadde.
	 * @uml.property  name="duvarDolguMadde"
	 */
	public DuvarDolguMadde getDuvarDolguMadde() {
		return duvarDolguMadde;
	}

	/**
	 * Setter of the property <tt>duvarDolguMadde</tt>
	 * @param duvarDolguMadde  The duvarDolguMadde to set.
	 * @uml.property  name="duvarDolguMadde"
	 */
	public void setDuvarDolguMadde(DuvarDolguMadde duvarDolguMadde) {
		this.duvarDolguMadde = duvarDolguMadde;
	}

	/**
	 * @uml.property  name="uavtGuncellemeTarih"
	 */
	private Date uavtGuncellemeTarih;

	/**
	 * Getter of the property <tt>uavtGuncellemeTarih</tt>
	 * @return  Returns the uavtGuncellemeTarih.
	 * @uml.property  name="uavtGuncellemeTarih"
	 */
	public Date getUavtGuncellemeTarih() {
		return uavtGuncellemeTarih;
	}

	/**
	 * Setter of the property <tt>uavtGuncellemeTarih</tt>
	 * @param uavtGuncellemeTarih  The uavtGuncellemeTarih to set.
	 * @uml.property  name="uavtGuncellemeTarih"
	 */
	public void setUavtGuncellemeTarih(Date uavtGuncellemeTarih) {
		this.uavtGuncellemeTarih = uavtGuncellemeTarih;
	}

	/**
	 * @uml.property  name="zoneBinaNo"
	 */
	private Long zoneBinaNo;

	/**
	 * Getter of the property <tt>zoneBinaNo</tt>
	 * @return  Returns the zoneBinaNo.
	 * @uml.property  name="zoneBinaNo"
	 */
	public Long getZoneBinaNo() {
		return zoneBinaNo;
	}

	/**
	 * Setter of the property <tt>zoneBinaNo</tt>
	 * @param zoneBinaNo  The zoneBinaNo to set.
	 * @uml.property  name="zoneBinaNo"
	 */
	public void setZoneBinaNo(Long zoneBinaNo) {
		this.zoneBinaNo = zoneBinaNo;
	}
	
	
	
}
