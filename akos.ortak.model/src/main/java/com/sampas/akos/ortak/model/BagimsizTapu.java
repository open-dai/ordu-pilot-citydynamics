package com.sampas.akos.ortak.model;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.ortak.model.Bagimsiz;


@SuppressWarnings("serial")
public class BagimsizTapu extends BaseObject {

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
	 * @uml.property  name="tapu"
	 * @uml.associationEnd  inverse="tapuBagimsiz:com.sampas.akos.numarataj.model.Tapu"
	 */
	private Tapu tapu;

	/**
	 * Getter of the property <tt>tapu</tt>
	 * @return  Returns the tapu.
	 * @uml.property  name="tapu"
	 */
	public Tapu getTapu() {
		return tapu;
	}

	/**
	 * Setter of the property <tt>tapu</tt>
	 * @param tapu  The tapu to set.
	 * @uml.property  name="tapu"
	 */
	public void setTapu(Tapu tapu) {
		this.tapu = tapu;
	}

	/**
	 * @uml.property  name="bagimsiz"
	 * @uml.associationEnd  inverse="bagimsizTapu:com.sampas.akos.ortak.model.Bagimsiz"
	 */
	private Bagimsiz bagimsiz;

	/**
	 * Getter of the property <tt>bagimsiz</tt>
	 * @return  Returns the bagimsiz.
	 * @uml.property  name="bagimsiz"
	 */
	public Bagimsiz getBagimsiz() {
		return bagimsiz;
	}

	/**
	 * Setter of the property <tt>bagimsiz</tt>
	 * @param bagimsiz  The bagimsiz to set.
	 * @uml.property  name="bagimsiz"
	 */
	public void setBagimsiz(Bagimsiz bagimsiz) {
		this.bagimsiz = bagimsiz;
	}

}
