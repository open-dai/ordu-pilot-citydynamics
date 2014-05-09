package com.sampas.socbs.core.dataset.table;

public interface IColumnCollection {
	
	/*
	 * The field at the specified index in the fields collection.
	 */	
	public IColumn getColumnAt(int index);	
	/*
	 * The number of fields in the fields collection.
	 */	
	public int getColumnCollectionCount();	
	/*
	 * Finds the index of the named field in the fields collection.
	 */	
	public int findColumn(String columnName);

}
