package com.sampas.ortak.spatial.history.model;

import java.util.Date;
import com.sampas.akos.common.model.BaseObject;


@SuppressWarnings("serial")
public class BaseHistoryObject extends BaseObject {

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
	 * @uml.property  name="degisiklikYapan"
	 */
	private String degisiklikYapan;
	
	/***
	 * Getter of the property <tt>degisiklikYapan</tt>
	 * @return  Returns the degisiklikYapan.
	 * @uml.property  name="degisiklikYapan"
	 */
	public String getDegisiklikYapan() {
		return degisiklikYapan;
	}

	/**
	 * Setter of the property <tt>degisiklikYapan</tt>
	 * @param degisiklikYapan  The degisiklikYapan to set.
	 * @uml.property  name="degisiklikYapan"
	 */
	public void setDegisiklikYapan(String degisiklikYapan) {
		this.degisiklikYapan = degisiklikYapan;
	}

	/**
	 * @uml.property  name="degisiklikTarihi"
	 */
	private Date degisiklikTarihi; 
	
	/***
	 * Getter of the property <tt>degisiklikTarihi</tt>
	 * @return  Returns the degisiklikTarihi.
	 * @uml.property  name="degisiklikTarihi"
	 */
	public Date getDegisiklikTarihi() {
		return degisiklikTarihi;
	}

	/**
	 * Setter of the property <tt>degisiklikTarihi</tt>
	 * @param degisiklikTarihi  The degisiklikTarihi to set.
	 * @uml.property  name="degisiklikTarihi"
	 */
	public void setDegisiklikTarihi(Date degisiklikTarihi) {
		this.degisiklikTarihi = degisiklikTarihi;
	}
	
	/**
	 * @uml.property  name="degisiklikTipi"
	 */
	private String degisiklikTipi;

	/***
	 * Getter of the property <tt>degisiklikTipi</tt>
	 * @return  Returns the degisiklikTipi.
	 * @uml.property  name="degisiklikTipi"
	 */
	public String getDegisiklikTipi() {
		return degisiklikTipi;
	}

	/**
	 * Setter of the property <tt>degisiklikTipi</tt>
	 * @param degisiklikTipi  The degisiklikTipi to set.
	 * @uml.property  name="degisiklikTipi"
	 */
	public void setDegisiklikTipi(String degisiklikTipi) {
		this.degisiklikTipi = degisiklikTipi;
	}

}
