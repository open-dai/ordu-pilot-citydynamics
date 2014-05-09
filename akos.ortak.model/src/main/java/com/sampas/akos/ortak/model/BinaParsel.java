package com.sampas.akos.ortak.model;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;


@SuppressWarnings("serial")
public class BinaParsel extends BaseObject implements IVersionable ,ITransactionable, IHistoricalable {

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
	 * @uml.associationEnd  inverse="binaParsel:com.sampas.akos.ortak.model.Bina"
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
	 * @uml.associationEnd  inverse="binaParsel:com.sampas.akos.ortak.model.KadastroParsel"
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
	 * @uml.property  name="aktifEh"
	 */
	private String aktifEh;

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

}
