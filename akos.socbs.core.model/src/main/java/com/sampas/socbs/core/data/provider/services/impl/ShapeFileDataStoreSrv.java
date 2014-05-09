package com.sampas.socbs.core.data.provider.services.impl;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.shapefile.impl.ShapefileDataStore;
import com.sampas.socbs.core.data.shapefile.impl.ShapefileDataStoreFactory;

public class ShapeFileDataStoreSrv {

	private Map<String,Serializable> connectParameters = new HashMap<String,Serializable>();
	
	private File file = null;
	
	private ShapefileDataStore shapeFileDataStore = null;
	
	private ShapefileDataStoreFactory shapefileDataStoreFactory = new ShapefileDataStoreFactory();
	
	boolean createSpatialIndex = false;
	
	public ShapeFileDataStoreSrv(File file, boolean createSpatialIndex) {
		
		this.file = file;
		this.createSpatialIndex = createSpatialIndex;
		
		try {
			if (file.exists()) { 
				if (SetConnectionParameters()) {

					this.shapeFileDataStore = (ShapefileDataStore)shapefileDataStoreFactory.createDataStore(connectParameters);
				} else {

				}
			} else {
				System.out.println("Error: on Opening File");
				
			}
		} catch (Exception e) {
			System.out.println("Error: " +e.getMessage());
		}
	}
	
	private boolean SetConnectionParameters() {
		try {

			connectParameters.put("url", this.file.toURI().toURL());
			connectParameters.put("create spatial index", createSpatialIndex );
			return true;

		} catch (Exception e) {
			return false;
		}
	}
	
	public IFeatureDataStore getShapeFileDataStore() {
		
		return (this.shapeFileDataStore);
	}
	
}
