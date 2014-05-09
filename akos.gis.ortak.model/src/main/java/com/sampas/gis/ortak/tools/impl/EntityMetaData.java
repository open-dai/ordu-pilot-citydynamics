package com.sampas.gis.ortak.tools.impl;

import java.util.HashMap;
import java.util.Iterator;

public class EntityMetaData {
	
	HashMap<String, String> propertyColumnMappings;
	Class<?> entityClass;
	String dbTableName;
	/**
	 * @return the dbTableName
	 */
	public String getDbTableName() {
		return dbTableName;
	}
	/**
	 * @param dbTableName the dbTableName to set
	 */
	public void setDbTableName(String dbTableName) {
		this.dbTableName = dbTableName;
	}
	/**
	 * @return the propertyColumnMappings
	 */
	public HashMap<String, String> getPropertyColumnMappings() {
		return propertyColumnMappings;
	}
	/**
	 * @param propertyColumnMappings the propertyColumnMappings to set
	 */
	public void setPropertyColumnMappings(
			HashMap<String, String> propertyColumnMappings) {
		this.propertyColumnMappings = propertyColumnMappings;
	}
	/**
	 * @return the entityClass
	 */
	public Class<?> getEntityClass() {
		return entityClass;
	}
	/**
	 * @param entityClass the entityClass to set
	 */
	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}
	
	public String getEntityPropertyNameByColumnName(String columnName){
		String toReturn=null;
		
		if (propertyColumnMappings!=null){
			for (Iterator<?> itr=propertyColumnMappings.keySet().iterator();itr.hasNext();) {
				String propName=itr.next().toString();
				if (propName!=null){
					String colName=propertyColumnMappings.get(propName);
					if (colName.equals(columnName)){
						toReturn=propName;
						break;
					}
				}
			}
		}
		
		
		
		return toReturn;
	}
	
}
