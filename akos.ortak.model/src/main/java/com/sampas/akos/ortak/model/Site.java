package com.sampas.akos.ortak.model;

import org.hibernate.envers.Audited;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;


@SuppressWarnings("serial")
@Audited
public class Site extends BaseObject implements IVersionable,ITransactionable,IHistoricalable {

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
	 * @uml.associationEnd  inverse="site:com.sampas.akos.ortak.model.KurumSabit"
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

}
