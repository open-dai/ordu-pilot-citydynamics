package com.sampas.ortak.spatial.history.model;

import java.util.Date;


@SuppressWarnings("serial")
public class BinaTesisatHst extends BaseHistoryObject {

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
	 * @uml.property  name="binaId"
	 */
	private Long binaId;

	/**
	 * Getter of the property <tt>binaId</tt>
	 * @return Returns the binaId.
	 * @uml.property  name="binaId"
	 */
	public Long getBinaId() {
		return binaId;
	}

	/**
	 * Setter of the property <tt>binaId</tt>
	 * @param binaId The binaId to set.
	 * @uml.property  name="binaId"
	 */
	public void setBinaId(Long binaId) {
		this.binaId = binaId;
	}
	
	/**
	 * @uml.property  name="tesisatTurId"
	 */
	private Long tesisatTurId;

	/**
	 * Getter of the property <tt>tesisatTurId</tt>
	 * @return Returns the tesisatTurId.
	 * @uml.property  name="tesisatTurId"
	 */
	public Long getTesisatTurId() {
		return tesisatTurId;
	}

	/**
	 * Setter of the property <tt>tesisatTurId</tt>
	 * @param tesisatTurId  The tesisatTurId to set.
	 * @uml.property  name="tesisatTurId"
	 */
	public void setTesisatTurId(Long tesisatTurId) {
		this.tesisatTurId = tesisatTurId;
	}
	
	/**
	 * @uml.property  name="aktifEh"
	 */
	private String aktifEh = "";

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
	
	/**
	 * @uml.property  name="pdaEh"
	 */
	private String pdaEh = "";

	/**
	 * Getter of the property <tt>pdaEh</tt>
	 * @return  Returns the pdaEh.
	 * @uml.property  name="pdaEh"
	 */
	public String getPdaEh() {
		return pdaEh;
	}

	/**
	 * Setter of the property <tt>pdaEh</tt>
	 * @param pdaEh  The pdaEh to set.
	 * @uml.property  name="pdaEh"
	 */
	public void setPdaEh(String pdaEh) {
		this.pdaEh = pdaEh;
	}
	
	/**
	 * @uml.property  name="pdaUserName"
	 */
	private String pdaUserName = "";

	/**
	 * Getter of the property <tt>pdaUserName</tt>
	 * @return  Returns the pdaUserName.
	 * @uml.property  name="pdaUserName"
	 */
	public String getPdaUserName() {
		return pdaUserName;
	}

	/**
	 * Setter of the property <tt>pdaUserName</tt>
	 * @param pdaUserName  The pdaUserName to set.
	 * @uml.property  name="pdaUserName"
	 */
	public void setPdaUserName(String pdaUserName) {
		this.pdaUserName = pdaUserName;
	}
	
	/**
	 * @uml.property  name="pdaIslemTarihi"
	 */
	private Date pdaIslemTarihi;

	/**
	 * Getter of the property <tt>pdaIslemTarihi</tt>
	 * @return  Returns the pdaIslemTarihi.
	 * @uml.property  name="pdaIslemTarihi"
	 */
	public Date getPdaIslemTarihi() {
		return pdaIslemTarihi;
	}

	/**
	 * Setter of the property <tt>pdaIslemTarihi</tt>
	 * @param pdaIslemTarihi  The pdaIslemTarihi to set.
	 * @uml.property  name="pdaIslemTarihi"
	 */
	public void setPdaIslemTarihi(Date pdaIslemTarihi) {
		this.pdaIslemTarihi = pdaIslemTarihi;
	}
	
}
