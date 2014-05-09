package com.sampas.akos.ortak.model;


import org.hibernate.envers.Audited;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.ortak.model.BagimsizKullanimDetay;


@SuppressWarnings("serial")
@Audited
public class BagimsizKullanimDetay extends BaseObject {

	/***
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
	 * @uml.property  name="isyeriEh"
	 */
	private String isyeriEh = "";

	/**
	 * Getter of the property <tt>isyeriEh</tt>
	 * @return  Returns the isyeriEh.
	 * @uml.property  name="isyeriEh"
	 */
	public String getIsyeriEh() {
		return isyeriEh;
	}

	/**
	 * Setter of the property <tt>isyeriEh</tt>
	 * @param isyeriEh  The isyeriEh to set.
	 * @uml.property  name="isyeriEh"
	 */
	public void setIsyeriEh(String isyeriEh) {
		this.isyeriEh = isyeriEh;
	}

	/**
	 * @uml.property  name="kullanimTip"
	 */
	private String kullanimTip = "";

	/**
	 * Getter of the property <tt>kullanimTip</tt>
	 * @return  Returns the kullanimTip.
	 * @uml.property  name="kullanimTip"
	 */
	public String getKullanimTip() {
		return kullanimTip;
	}

	/**
	 * Setter of the property <tt>kullanimTip</tt>
	 * @param kullanimTip  The kullanimTip to set.
	 * @uml.property  name="kullanimTip"
	 */
	public void setKullanimTip(String kullanimTip) {
		this.kullanimTip = kullanimTip;
	}

	/**
	 * @uml.property  name="kullanimSekli"
	 * @uml.associationEnd  inverse="bagimsizKullanimDetay:com.sampas.akos.ortak.model.KullanimSekli"
	 */
	private KullanimSekli kullanimSekli;

	/**
	 * Getter of the property <tt>kullanimSekli</tt>
	 * @return  Returns the kullanimSekli.
	 * @uml.property  name="kullanimSekli"
	 */
	public KullanimSekli getKullanimSekli() {
		return kullanimSekli;
	}

	/**
	 * Setter of the property <tt>kullanimSekli</tt>
	 * @param kullanimSekli  The kullanimSekli to set.
	 * @uml.property  name="kullanimSekli"
	 */
	public void setKullanimSekli(KullanimSekli kullanimSekli) {
		this.kullanimSekli = kullanimSekli;
	}
	
	/**
	 * @uml.property  name="bagimsizKullanimGrup"
	 */
	private BagimsizKullanimGrup bagimsizKullanimGrup;

	/**
	 * Getter of the property <tt>bagimsizKullanimGrup</tt>
	 * @return  Returns the bagimsizKullanimGrup.
	 * @uml.property  name="bagimsizKullanimGrup"
	 */
	public BagimsizKullanimGrup getBagimsizKullanimGrup() {
		return bagimsizKullanimGrup;
	}

	/**
	 * Setter of the property <tt>bagimsizKullanimGrup</tt>
	 * @param bagimsizKullanimGrup  The bagimsizKullanimGrup to set.
	 * @uml.property  name="bagimsizKullanimGrup"
	 */
	public void setBagimsizKullanimGrup(BagimsizKullanimGrup bagimsizKullanimGrup) {
		this.bagimsizKullanimGrup = bagimsizKullanimGrup;
	}

}
