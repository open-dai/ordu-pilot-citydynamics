package com.sampas.akos.ortak.model;


import org.hibernate.envers.Audited;

import com.sampas.akos.common.model.BaseObject;


@SuppressWarnings("serial")
@Audited
public class BagimsizKullanimSinif extends BaseObject {

	/**
	 * @uml.property  name="id"
	 */
	private Long id;

	/**
	 * Getter of the property <tt>id</tt>
	 * 
	 * @return Returns the id.
	 * @uml.property name="id"
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter of the property <tt>id</tt>
	 * 
	 * @param id
	 *            The id to set.
	 * @uml.property name="id"
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
	 * 
	 * @return Returns the aciklama.
	 * @uml.property name="aciklama"
	 */
	public String getAciklama() {
		return aciklama;
	}

	/**
	 * Setter of the property <tt>aciklama</tt>
	 * 
	 * @param aciklama
	 *            The aciklama to set.
	 * @uml.property name="aciklama"
	 */
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	
}
