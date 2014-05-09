package com.sampas.gis.spatial.servis;

import java.net.URL;
import com.sampas.gis.ortak.basic.model.datastore.SmpWFSDataStore;
import com.sampas.gis.ortak.basic.model.geometry.SmpBoundingBox;
import com.sampas.gis.ortak.basic.model.returntype.SmpLayerReturnType;
import com.sampas.gis.ortak.basic.model.returntype.SmpWFSDataStoreReturnType;
import com.sampas.gis.ortak.basic.model.spatial.SmpLayerName;


public interface ISpatialService {

	public SmpWFSDataStoreReturnType getWFSDataStore(URL wfsDataStoreURL);
	
	public SmpLayerReturnType getLayerFromWFSDataStore(SmpWFSDataStore wfsDataStore, SmpLayerName layerName, SmpBoundingBox boundingBox);
	
}