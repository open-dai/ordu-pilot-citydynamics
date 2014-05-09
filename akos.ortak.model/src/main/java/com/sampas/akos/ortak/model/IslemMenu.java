package com.sampas.akos.ortak.model;

import com.sampas.akos.common.model.BaseObject;


@SuppressWarnings("serial")
public class IslemMenu extends BaseObject {

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
	 * @uml.property  name="link"
	 */
	private String link;

	/**
	 * Getter of the property <tt>link</tt>
	 * @return  Returns the link.
	 * @uml.property  name="link"
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Setter of the property <tt>link</tt>
	 * @param link  The link to set.
	 * @uml.property  name="link"
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @uml.property  name="aciklama"
	 */
	private String aciklama;

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
	 * @uml.property  name="rol"
	 */
	private String rol;

	/**
	 * Getter of the property <tt>rol</tt>
	 * @return  Returns the rol.
	 * @uml.property  name="rol"
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * Setter of the property <tt>rol</tt>
	 * @param rol  The rol to set.
	 * @uml.property  name="rol"
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * @uml.property  name="kurumSabit"
	 * @uml.associationEnd  inverse="islemMenu:com.sampas.akos.ortak.model.KurumSabit"
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
