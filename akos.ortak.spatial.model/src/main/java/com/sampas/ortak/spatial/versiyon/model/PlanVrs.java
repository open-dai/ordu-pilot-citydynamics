package com.sampas.ortak.spatial.versiyon.model;

import java.util.Date;


@SuppressWarnings("serial")
public class PlanVrs extends BaseVersionObject {

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
	 * @uml.property  name="planTuruId"
	 */
	private Long planTuruId;

	/**
	 * Getter of the property <tt>planTuru</tt>
	 * @return  Returns the planTuru.
	 * @uml.property  name="planTuru"
	 */
	public Long getPlanTuruId() {
		return planTuruId;
	}

	/**
	 * Setter of the property <tt>planTuruId</tt>
	 * @param planTuruId  The planTuruId to set.
	 * @uml.property  name="planTuruId"
	 */
	public void setPlanTuruId(Long planTuruId) {
		this.planTuruId = planTuruId;
	}
	
}
