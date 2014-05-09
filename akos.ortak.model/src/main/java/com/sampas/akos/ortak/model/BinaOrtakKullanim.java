package com.sampas.akos.ortak.model;

import java.util.Date;
import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;


@SuppressWarnings("serial")
public class BinaOrtakKullanim extends BaseObject implements IVersionable, ITransactionable, IHistoricalable{

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
	 */
	private Bina bina;

	/**
	 * Getter of the property <tt>bina</tt>
	 * @return Returns the bina.
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
	 * @uml.property  name="ortakKullanimTur"
	 */
	private OrtakKullanimTur ortakKullanimTur;

	/**
	 * Getter of the property <tt>ortakKullanimTur</tt>
	 * @return Returns the ortakKullanimTur.
	 * @uml.property  name="ortakKullanimTur"
	 */
	public OrtakKullanimTur getOrtakKullanimTur() {
		return ortakKullanimTur;
	}

	/**
	 * Setter of the property <tt>ortakKullanimTur</tt>
	 * @param ortakKullanimTur The ortakKullanimTur to set.
	 * @uml.property  name="ortakKullanimTur"
	 */
	public void setOrtakKullanimTur(OrtakKullanimTur ortakKullanimTur) {
		this.ortakKullanimTur = ortakKullanimTur;
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
