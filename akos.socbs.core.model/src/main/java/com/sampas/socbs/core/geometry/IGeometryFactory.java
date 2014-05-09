package com.sampas.socbs.core.geometry;

public interface IGeometryFactory {
	
	public int getSRID();

	public void createGeometry(IGeometry g);
	
	//public IGeometry buildGeometry(ICollection collection);

}
