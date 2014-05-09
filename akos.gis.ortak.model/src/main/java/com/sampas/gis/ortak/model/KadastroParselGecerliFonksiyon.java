package com.sampas.gis.ortak.model;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.ortak.model.KadastroParsel;
import com.sampas.akos.ortak.model.KurumSabit;


@SuppressWarnings("serial")
public class KadastroParselGecerliFonksiyon extends BaseObject  {

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
	 * @uml.property  name="fonksiyon"
	 */
	private Fonksiyon fonksiyon;

	/**
	 * Getter of the property <tt>fonksiyon</tt>
	 * @return  Returns the fonksiyon.
	 * @uml.property  name="fonksiyon"
	 */
	public Fonksiyon getFonksiyon() {
		return fonksiyon;
	}

	/**
	 * Setter of the property <tt>fonksiyon</tt>
	 * @param fonksiyon  The fonksiyon to set.
	 * @uml.property  name="fonksiyon"
	 */
	public void setFonksiyon(Fonksiyon fonksiyon) {
		this.fonksiyon = fonksiyon;
	}

	/**
	 * @uml.property  name="kadastroParsel"
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
	
	/**
	 * @uml.property  name="kesisimOrani"
	 */
	private Double kesisimOrani;

	/**
	 * Getter of the property <tt>kesisimOrani</tt>
	 * @return  Returns the kesisimOrani.
	 * @uml.property  name="kesisimOrani"
	 */
	public Double getKesisimOrani() {
		return kesisimOrani;
	}

	/**
	 * Setter of the property <tt>kesisimOrani</tt>
	 * @param kesisimOrani  The kesisimOrani to set.
	 * @uml.property  name="kesisimOrani"
	 */
	public void setKesisimOrani(Double kesisimOrani) {
		this.kesisimOrani = kesisimOrani;
	}
	
	/**
	 * @uml.property  name="kesisimAlani"
	 */
	private Double kesisimAlani;

	/**
	 * Getter of the property <tt>kesisimAlani</tt>
	 * @return  Returns the kesisimAlani.
	 * @uml.property  name="kesisimAlani"
	 */
	public Double getKesisimAlani() {
		return kesisimAlani;
	}

	/**
	 * Setter of the property <tt>kesisimAlani</tt>
	 * @param kesisimAlani  The kesisimAlani to set.
	 * @uml.property  name="kesisimAlani"
	 */
	public void setKesisimAlani(Double kesisimAlani) {
		this.kesisimAlani = kesisimAlani;
	}

}
