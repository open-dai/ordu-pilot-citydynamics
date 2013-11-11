/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.sampas.GeoInfoMngmnt.CityDynamics.V1;


public class QueryClosestPatoiByAddressRequestType {

	private int QueryType;
	private Long recordID;

	private Object Address;

	private String PoiType;

	private Object SearchCriteria;

	public void setAddress(Object address) {
		Address = address;
	}

	public Object getAddress() {
		return Address;
	}

	public void setPoiType(String poiType) {
		PoiType = poiType;
	}

	public String getPoiType() {
		return PoiType;
	}

	public void setSearchCriteria(Object searchCriteria) {
		SearchCriteria = searchCriteria;
	}

	public Object getSearchCriteria() {
		return SearchCriteria;
	}

	public void setQueryType(int queryType) {
		QueryType = queryType;
	}

	public int getQueryType() {
		return QueryType;
	}

	public void setRecordID(Long recordID) {
		this.recordID = recordID;
	}

	public Long getRecordID() {
		return recordID;
	}

} // queryClosestPatoiByAddressRequestType
