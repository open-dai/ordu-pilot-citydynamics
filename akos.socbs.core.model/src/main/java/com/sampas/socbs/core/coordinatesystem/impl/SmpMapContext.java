package com.sampas.socbs.core.coordinatesystem.impl;

import org.geotools.map.DefaultMapContext;
import org.geotools.map.MapContext;
import org.geotools.referencing.crs.DefaultGeographicCRS;

import com.sampas.socbs.core.coordinatesystem.IMapContext;

public class SmpMapContext implements IMapContext {
	
	private MapContext mapContext = new DefaultMapContext(DefaultGeographicCRS.WGS84);
	
	public SmpMapContext(){
		
		
	}
	
	public SmpMapContext(MapContext mapContext){
			
		this.mapContext = mapContext;
		
	}

	
	public IMapContext getMapContext() {
		
		IMapContext mapContext = new SmpMapContext(this.mapContext);
		
		return mapContext;
		
	}
	
}
