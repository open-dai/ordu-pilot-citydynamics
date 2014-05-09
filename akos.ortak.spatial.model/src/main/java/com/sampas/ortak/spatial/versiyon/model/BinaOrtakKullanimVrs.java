package com.sampas.ortak.spatial.versiyon.model;

import java.util.Date;


@SuppressWarnings("serial")
public class BinaOrtakKullanimVrs extends BaseVersionObject {

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
	 * @param binaId  The binaId to set.
	 * @uml.property  name="binaId"
	 */
	public void setBinaId(Long binaId) {
		this.binaId = binaId;
	}
	
	/**
	 * @uml.property  name="ortakKullanimTurId"
	 */
	private Long ortakKullanimTurId;

	/**
	 * Getter of the property <tt>ortakKullanimTurId</tt>
	 * @return Returns the ortakKullanimTurId.
	 * @uml.property  name="ortakKullanimTurId"
	 */
	public Long getOrtakKullanimTurId() {
		return ortakKullanimTurId;
	}

	/**
	 * Setter of the property <tt>ortakKullanimTurId</tt>
	 * @param ortakKullanimTurId The ortakKullanimTurId to set.
	 * @uml.property  name="ortakKullanimTurId"
	 */
	public void setOrtakKullanimTurId(Long ortakKullanimTurId) {
		this.ortakKullanimTurId = ortakKullanimTurId;
	}
	
	/**
	 * @uml.property  name="ortakKullanimTur"
	 */
	private Double alaniAdedi;

	/**
	 * Getter of the property <tt>ortakKullanimTur</tt>
	 * @return Returns the ortakKullanimTur.
	 * @uml.property  name="ortakKullanimTur"
	 */
	public Double getAlaniAdedi() {
		return alaniAdedi;
	}

	/**
	 * Setter of the property <tt>ortakKullanimTur</tt>
	 * @param ortakKullanimTur The ortakKullanimTur to set.
	 * @uml.property  name="ortakKullanimTur"
	 */
	public void setAlaniAdedi(Double alaniAdedi) {
		this.alaniAdedi = alaniAdedi;
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
