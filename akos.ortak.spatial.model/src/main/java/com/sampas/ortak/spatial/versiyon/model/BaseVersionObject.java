package com.sampas.ortak.spatial.versiyon.model;

import java.util.Date;
import com.sampas.akos.common.model.BaseObject;


@SuppressWarnings("serial")
public class BaseVersionObject extends BaseObject {

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
	 * @uml.property  name="userSpecVariable"
	 */
	private String userSpecVariable;
	
	/***
	 * Getter of the property <tt>userSpecVariable</tt>
	 * @return  Returns the userSpecVariable.
	 * @uml.property  name="userSpecVariable"
	 */
	public String getUserSpecVariable() {
		return userSpecVariable;
	}

	/**
	 * Setter of the property <tt>userSpecVariable</tt>
	 * @param userSpecVariable  The userSpecVariable to set.
	 * @uml.property  name="userSpecVariable"
	 */	
	public void setUserSpecVariable(String userSpecVariable) {
		this.userSpecVariable = userSpecVariable;
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
	 * @uml.property  name="versiyonNo"
	 */
	private double versiyonNo;
	
	/***
	 * Getter of the property <tt>versiyonNo</tt>
	 * @return  Returns the versiyonNo.
	 * @uml.property  name="versiyonNo"
	 */
	public double getVersiyonNo() {
		return versiyonNo;
	}

	/**
	 * Setter of the property <tt>versiyonNo</tt>
	 * @param versiyonNo  The versiyonNo to set.
	 * @uml.property  name="versiyonNo"
	 */
	public void setVersiyonNo(double versiyonNo) {
		this.versiyonNo = versiyonNo;
	}
	
	/**
	 * @uml.property  name="versiyonTarihi"
	 */
	private Date versiyonTarihi;
	
	/***
	 * Getter of the property <tt>versiyonTarihi</tt>
	 * @return  Returns the versiyonTarihi.
	 * @uml.property  name="versiyonTarihi"
	 */
	public Date getVersiyonTarihi() {
		return versiyonTarihi;
	}
	
	/**
	 * Setter of the property <tt>versiyonTarihi</tt>
	 * @param versiyonTarihi  The versiyonTarihi to set.
	 * @uml.property  name="versiyonTarihi"
	 */
	public void setVersiyonTarihi(Date versiyonTarihi) {
		this.versiyonTarihi = versiyonTarihi;
	}

}
