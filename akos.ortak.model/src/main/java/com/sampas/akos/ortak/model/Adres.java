package com.sampas.akos.ortak.model;

import java.util.Date;

import org.hibernate.envers.Audited;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;


@SuppressWarnings("serial")
@Audited
public class Adres extends BaseObject implements IVersionable,ITransactionable,IHistoricalable {

	/**
	 * @uml.property  name="id"
	 */
	private Long id;

	/***
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
	 * @uml.property  name="mahalleCaddeSokak"
	 */
	private MahalleCaddeSokak mahalleCaddeSokak;

	/**
	 * Getter of the property <tt>mahalleCaddeSokak</tt>
	 * @return  Returns the mahalleCaddeSokak.
	 * @uml.property  name="mahalleCaddeSokak"
	 */
	public MahalleCaddeSokak getMahalleCaddeSokak() {
		return mahalleCaddeSokak;
	}

	/**
	 * Setter of the property <tt>mahalleCaddeSokak</tt>
	 * @param mahalleCaddeSokak  The mahalleCaddeSokak to set.
	 * @uml.property  name="mahalleCaddeSokak"
	 */
	public void setMahalleCaddeSokak(MahalleCaddeSokak mahalleCaddeSokak) {
		this.mahalleCaddeSokak = mahalleCaddeSokak;
	}
	
	/**
	 * @uml.property  name="bina"
	 */
	private Bina bina;

	/**
	 * Getter of the property <tt>bina</tt>
	 * @return  Returns the bina.
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
	 * @uml.property  name="kadastroParsel"
	 */
	private KadastroParsel kadastroParsel;

	/**
	 * Getter of the property <tt>kadastroParsel</tt>
	 * @return  Returns the kadastroParsel.
	 * @uml.property  name="bina"
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
	 * @uml.property  name="uavtBinaKodu"
	 */
	private Long uavtBinaKodu;

	/**
	 * Getter of the property <tt>uavtBinaKodu</tt>
	 * @return  Returns the uavtBinaKodu.
	 * @uml.property  name="uavtBinaKodu"
	 */
	public Long getUavtBinaKodu() {
		return uavtBinaKodu;
	}

	/**
	 * Setter of the property <tt>uavtBinaKodu</tt>
	 * @param uavtBinaKodu  The uavtBinaKodu to set.
	 * @uml.property  name="uavtBinaKodu"
	 */
	public void setUavtBinaKodu(Long uavtBinaKodu) {
		this.uavtBinaKodu = uavtBinaKodu;
	}

	/**
	 * @uml.property  name="esbinakodu"
	 */
	private Long esbinakodu;

	/**
	 * Getter of the property <tt>esbinakodu</tt>
	 * @return  Returns the esbinakodu.
	 * @uml.property  name="esbinakodu"
	 */
	public Long getEsbinakodu() {
		return esbinakodu;
	}

	/**
	 * Setter of the property <tt>esbinakodu</tt>
	 * @param esbinakodu  The esbinakodu to set.
	 * @uml.property  name="esbinakodu"
	 */
	public void setEsbinakodu(Long esbinakodu) {
		this.esbinakodu = esbinakodu;
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
	
}
