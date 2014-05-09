package com.sampas.ortak.spatial.transaction.model;

import java.util.Date;
import com.sampas.akos.common.model.BaseObject;


@SuppressWarnings("serial")
public class BaseTransactionObject extends BaseObject {

	/**
	 * @uml.property  name="id"
	 */
	private Long id;
	
	/***
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
	 * @uml.property  name="nesneId"
	 */
	private Long nesneId;

	/***
	 * Getter of the property <tt>nesneId</tt>
	 * @return  Returns the nesneId.
	 * @uml.property  name="nesneId"
	 */
	public Long getNesneId() {
		return nesneId;
	}

	/**
	 * Setter of the property <tt>nesneId</tt>
	 * @param nesneId  The nesneId to set.
	 * @uml.property  name="nesneId"
	 */
	public void setNesneId(Long nesneId) {
		this.nesneId = nesneId;
	}
	
	/**
	 * @uml.property  name="surecId"
	 */
	private Long surecId;

	/***
	 * Getter of the property <tt>surecId</tt>
	 * @return  Returns the surecId.
	 * @uml.property  name="surecId"
	 */
	public Long getSurecId() {
		return surecId;
	}

	/**
	 * Setter of the property <tt>surecId</tt>
	 * @param surecId  The surecId to set.
	 * @uml.property  name="surecId"
	 */
	public void setSurecId(Long surecId) {
		this.surecId = surecId;
	}

	/**
	 * @uml.property  name="taskId"
	 */
	private Long taskId;

	/***
	 * Getter of the property <tt>taskId</tt>
	 * @return  Returns the taskId.
	 * @uml.property  name="taskId"
	 */
	public Long getTaskId() {
		return taskId;
	}
	
	/**
	 * Setter of the property <tt>taskId</tt>
	 * @param taskId  The taskId to set.
	 * @uml.property  name="taskId"
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	/**
	 * @uml.property  name="islemiYapan"
	 */
	private String islemiYapan;

	/***
	 * Getter of the property <tt>islemiYapan</tt>
	 * @return  Returns the islemiYapan.
	 * @uml.property  name="islemiYapan"
	 */
	public String getIslemiYapan() {
		return islemiYapan;
	}

	/**
	 * Setter of the property <tt>islemiYapan</tt>
	 * @param islemiYapan  The islemiYapan to set.
	 * @uml.property  name="islemiYapan"
	 */
	public void setIslemiYapan(String islemiYapan) {
		this.islemiYapan = islemiYapan;
	}
	
	/**
	 * @uml.property  name="islemTarihi"
	 */
	private Date islemTarihi;

	/***
	 * Getter of the property <tt>islemTarihi</tt>
	 * @return  Returns the islemTarihi.
	 * @uml.property  name="islemTarihi"
	 */
	public Date getIslemTarihi() {
		return islemTarihi;
	}

	/**
	 * Setter of the property <tt>islemTarihi</tt>
	 * @param islemTarihi  The islemTarihi to set.
	 * @uml.property  name="islemTarihi"
	 */
	public void setIslemTarihi(Date islemTarihi) {
		this.islemTarihi = islemTarihi;
	}
	
	/**
	 * @uml.property  name="onayDurumu"
	 */
	private String onayDurumu;

	/***
	 * Getter of the property <tt>onayDurumu</tt>
	 * @return  Returns the onayDurumu.
	 * @uml.property  name="onayDurumu"
	 */
	public String getOnayDurumu() {
		return onayDurumu;
	}

	/**
	 * Setter of the property <tt>onayDurumu</tt>
	 * @param onayDurumu  The onayDurumu to set.
	 * @uml.property  name="onayDurumu"
	 */
	public void setOnayDurumu(String onayDurumu) {
		this.onayDurumu = onayDurumu;
	}
	
}
