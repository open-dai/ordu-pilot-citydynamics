package com.sampas.ortak.spatial.transaction.model;



@SuppressWarnings("serial")
public class SiteTrs extends BaseTransactionObject {

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

}
