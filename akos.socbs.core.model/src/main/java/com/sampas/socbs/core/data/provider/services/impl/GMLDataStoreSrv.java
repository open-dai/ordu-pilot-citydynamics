package com.sampas.socbs.core.data.provider.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.gml.impl.FileGMLDataStore;
import com.sampas.socbs.core.data.gml.impl.GMLDataStoreFactory;
import com.sampas.socbs.core.data.provider.services.IGMLDataStoreSrv;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;

public class GMLDataStoreSrv implements IGMLDataStoreSrv {

	private Map<String,Serializable> connectParameters = new HashMap<String,Serializable>();
	
	private File file = null;
	
	private FileGMLDataStore fileGMLDataStore = null;
	
	private GMLDataStoreFactory fileGmlDataStoreFactory = new GMLDataStoreFactory();
	
	
	public GMLDataStoreSrv(File file) {
		
		this.file = file;
		
		try {
			if (file.exists()) { 
				if (SetConnectionParameters(this.file.toURI().toURL())) {

					this.fileGMLDataStore = (FileGMLDataStore)fileGmlDataStoreFactory.createDataStore(connectParameters);
				} else {

				}
			} else {
				System.out.println("Error on Opening File ! ERROR: File not exist !");
				
			}
		} catch (Exception e) {
			System.out.println("Error: " +e.getMessage());
		}
	}
	
	public GMLDataStoreSrv(InputStream stream, String fileLoc) {
		
		try {
			
			if (stream != null) { 
				   
			    File tempFile=new File(fileLoc);
			    OutputStream out=new FileOutputStream(tempFile);
			    byte buf[]=new byte[1024];
			    int len;
			    while((len=stream.read(buf))>0) {
				    out.write(buf,0,len);
			    }
				out.close();
				stream.close();
				
				if (SetConnectionParameters(tempFile.toURL())) {

					this.fileGMLDataStore = (FileGMLDataStore)fileGmlDataStoreFactory.createDataStore(connectParameters);
				} else {

				}
			} else {
				System.out.println("Error on Opening File ! ERROR: File not exist !");
				
			}
		} catch (Exception e) {
			System.out.println("Error: " +e.getMessage());
		}
	}
	
	private boolean SetConnectionParameters(URL filePath) {
		try {

			connectParameters.put(GMLDataStoreFactory.URLP.key, filePath);
			connectParameters.put(GMLDataStoreFactory.BUFFER_SIZEP.key, 10);
			connectParameters.put(GMLDataStoreFactory.TIMEOUT.key, 10000);
			return true;

		} catch (Exception e) {
			return false;
		}
	}
	
	public IFeatureDataStore getFileGMLDataStore() {
		
		return (this.fileGMLDataStore);
	}

	public IFeatureCollection getFeaturesFromGmlFile(File gmlFile) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFeatureCollection getFeaturesFromGmlStream(InputStream gmlStream) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
