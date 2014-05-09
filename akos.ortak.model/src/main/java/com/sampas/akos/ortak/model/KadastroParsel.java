package com.sampas.akos.ortak.model;

import java.util.Date;

import org.hibernate.envers.Audited;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;


@SuppressWarnings("serial")
@Audited
public class KadastroParsel extends BaseObject implements IVersionable, ITransactionable, IHistoricalable{

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
	 * @uml.associationEnd  inverse="kadastroParsel:com.sampas.akos.ortak.model.KurumSabit"
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
	 * @uml.property  name="mahalle"
	 */
	private Mahalle mahalle;

	/**
	 * Getter of the property <tt>mahalle</tt>
	 * @return  Returns the mahalle.
	 * @uml.property  name="mahalle"
	 */
	public Mahalle getMahalle() {
		return mahalle;
	}

	/**
	 * Setter of the property <tt>mahalle</tt>
	 * @param mahalle  The mahalle to set.
	 * @uml.property  name="mahalle"
	 */
	public void setMahalle(Mahalle mahalle) {
		this.mahalle = mahalle;
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
	 * @uml.property  name="tapuMahalle"
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
	 * @uml.property  name="acilisHareketKod"
	 */
	private KadastroHareketKod acilisHareketKod;

	/**
	 * Getter of the property <tt>acilisHareketKod</tt>
	 * @return  Returns the acilisHareketKod.
	 * @uml.property  name="acilisHareketKod"
	 */
	public KadastroHareketKod getAcilisHareketKod() {
		return acilisHareketKod;
	}

	/**
	 * Setter of the property <tt>acilisHareketKod</tt>
	 * @param acilisHareketKod  The acilisHareketKod to set.
	 * @uml.property  name="acilisHareketKod"
	 */
	public void setAcilisHareketKod(KadastroHareketKod acilisHareketKod) {
		this.acilisHareketKod = acilisHareketKod;
	}

	/**
	 * @uml.property  name="kapanisHareketKod"
	 */
	private KadastroHareketKod kapanisHareketKod;
	
	/**
	 * Getter of the property <tt>kapanisHareketKod</tt>
	 * @return  Returns the kapanisHareketKod.
	 * @uml.property  name="kapanisHareketKod"
	 */
	public KadastroHareketKod getKapanisHareketKod() {
		return kapanisHareketKod;
	}

	/**
	 * Setter of the property <tt>kapanisHareketKod</tt>
	 * @param kapanisHareketKod  The kapanisHareketKod to set.
	 * @uml.property  name="kapanisHareketKod"
	 */
	public void setKapanisHareketKod(KadastroHareketKod kapanisHareketKod) {
		this.kapanisHareketKod = kapanisHareketKod;
	}
	
	/**
	 * @uml.property  name="kadastroParselTipi"
	 */
	private KadastroParselTipi kadastroParselTipi;

	/**
	 * Getter of the property <tt>kadastroParselTipi</tt>
	 * @return  Returns the kadastroParselTipi.
	 * @uml.property  name="kadastroParselTipi"
	 */
	public KadastroParselTipi getKadastroParselTipi() {
		return kadastroParselTipi;
	}

	/**
	 * Setter of the property <tt>kadastroParselTipi</tt>
	 * @param kadastroParselTipi  The kadastroParselTipi to set.
	 * @uml.property  name="kadastroParselTipi"
	 */
	public void setKadastroParselTipi(KadastroParselTipi kadastroParselTipi) {
		this.kadastroParselTipi = kadastroParselTipi;
	}
	
	/**
	 * @uml.property  name="bolge"
	 */
	private Bolge bolge;

	/**
	 * Getter of the property <tt>bolge</tt>
	 * @return  Returns the bolge.
	 * @uml.property  name="bolge"
	 */
	public Bolge getBolge() {
		return bolge;
	}

	/**
	 * Setter of the property <tt>bolge</tt>
	 * @param bolge  The bolge to set.
	 * @uml.property  name="bolge"
	 */
	public void setBolge(Bolge bolge) {
		this.bolge = bolge;
	}
	
	

}
