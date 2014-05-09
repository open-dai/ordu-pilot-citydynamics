package com.sampas.socbs.core.dataset.table;

public interface IColumn {
	/*
	 * Indicates if the value is valid given the field definition
	 */
	public boolean isSuitableValue();
	/*
	 * The default value of the field.
	 */
	public Object get_DefaultValue();
	/*
	 * Indicates if the field is editable
	 */
	public boolean isEditable();
	/*
	 * The geometry type for the field
	 */
	public String getGeometryType(); 
	/*
	 * The name of the field
	 */
	public String getName();
	/*
	 * The type of the field.
	 */
	public String getType();

}
