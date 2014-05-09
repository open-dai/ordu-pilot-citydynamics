package com.sampas.gis.ortak.model;

import java.util.Date;
import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;
import com.sampas.akos.ortak.model.Grafik;
import com.sampas.akos.ortak.model.KurumSabit;


@SuppressWarnings("serial")
public class Plan extends BaseObject implements IVersionable,ITransactionable,IHistoricalable{

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
	 * @uml.property  name="adi"
	 */
	private String adi = "";

	/**
	 * Getter of the property <tt>adi</tt>
	 * @return  Returns the adi.
	 * @uml.property  name="adi"
	 */
	public String getAdi() {
		return adi;
	}

	/**
	 * Setter of the property <tt>adi</tt>
	 * @param adi  The adi to set.
	 * @uml.property  name="adi"
	 */
	public void setAdi(String adi) {
		this.adi = adi;
	}

	/**
	 * @uml.property  name="planNo"
	 */
	private String planNo = "";

	/**
	 * Getter of the property <tt>planNo</tt>
	 * @return  Returns the planNo.
	 * @uml.property  name="planNo"
	 */
	public String getPlanNo() {
		return planNo;
	}

	/**
	 * Setter of the property <tt>planNo</tt>
	 * @param planNo  The planNo to set.
	 * @uml.property  name="planNo"
	 */
	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	/**
	 * @uml.property  name="onayTarihi"
	 */
	private Date onayTarihi;

	/**
	 * Getter of the property <tt>onayTarihi</tt>
	 * @return  Returns the onayTarihi.
	 * @uml.property  name="onayTarihi"
	 */
	public Date getOnayTarihi() {
		return onayTarihi;
	}

	/**
	 * Setter of the property <tt>onayTarihi</tt>
	 * @param onayTarihi  The onayTarihi to set.
	 * @uml.property  name="onayTarihi"
	 */
	public void setOnayTarihi(Date onayTarihi) {
	
		this.onayTarihi = onayTarihi;
	}

	/**
	 * @uml.property  name="olcek"
	 */
	private long olcek;

	/**
	 * Getter of the property <tt>olcek</tt>
	 * @return  Returns the olcek.
	 * @uml.property  name="olcek"
	 */
	public long getOlcek() {
		return olcek;
	}

	/**
	 * Setter of the property <tt>olcek</tt>
	 * @param olcek  The olcek to set.
	 * @uml.property  name="olcek"
	 */
	public void setOlcek(long olcek) {
		this.olcek = olcek;
	}

	/**
	 * @uml.property  name="planTuru"
	 */
	private PlanTurleri planTuru;

	/**
	 * Getter of the property <tt>planTuru</tt>
	 * @return  Returns the planTuru.
	 * @uml.property  name="planTuru"
	 */
	public PlanTurleri getPlanTuru() {
		return planTuru;
	}

	/**
	 * Setter of the property <tt>planTuru</tt>
	 * @param planTuru  The planTuru to set.
	 * @uml.property  name="planTuru"
	 */
	public void setPlanTuru(PlanTurleri planTuru) {
		this.planTuru = planTuru;
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
	
	private Date tadilatPlaniTasdikTarihi;

	/**
	 * Getter of the property <tt>tadilatPlaniTasdikTarihi</tt>
	 * @return  Returns the tadilatPlaniTasdikTarihi.
	 * @uml.property  name="tadilatPlaniTasdikTarihi"
	 */
	public Date getTadilatPlaniTasdikTarihi() {
		return tadilatPlaniTasdikTarihi;
	}

	/**
	 * Setter of the property <tt>onayTarihi</tt>
	 * @param tadilatPlaniTasdikTarihi  The tadilatPlaniTasdikTarihi to set.
	 * @uml.property  name="data"
	 */
	public void setTadilatPlaniTasdikTarihi(Date data) {
	
		this.tadilatPlaniTasdikTarihi = data;
	}
	
	private long aktifMi;

	public long getAktifMi() {
		return aktifMi;
	}

	public void setAktifMi(long aktifMi) {
		this.aktifMi = aktifMi;
	}
}
