package com.sampas.socbs.core.renderer;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:45
 */
public interface IRendererFields {

	/**
	 * 
	 * @param fieldName
	 * @param fieldAlias
	 */
	public void addField(String fieldName, String fieldAlias);

	/**
	 * 
	 * @param index
	 */
	public String getField(int index);

	/**
	 * 
	 * @param index
	 */
	public String getFieldAlias(int index);

	public int getFieldCount();

	/**
	 * 
	 * @param fieldName
	 */
	public void removeField(String fieldName);

	/**
	 * 
	 * @param index
	 * @param fieldName
	 */
	public void setField(int index, String fieldName);

	/**
	 * 
	 * @param index
	 * @param fieldAlias
	 */
	public void setFieldAlias(int index, String fieldAlias);

}