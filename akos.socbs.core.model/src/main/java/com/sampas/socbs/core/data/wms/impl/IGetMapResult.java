package com.sampas.socbs.core.data.wms.impl;



public interface IGetMapResult {

	public void setImage(byte[] image);

	public byte[] getImage();
	
	public void setException(String exception);

	public String getException();
	
}
