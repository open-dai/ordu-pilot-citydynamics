package com.sampas.socbs.core.tiler.tool.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class MetaDataFileHandler {

	private String filename;

	public MetaDataFileHandler(String filename) {

		this.setFilename(filename + ".properties");
	}

	public InputStream getResource() {

		return this.getClass().getResourceAsStream(this.filename);

	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}
	
	public InputStream getResourceAsStreamByFileName(String fileName){
		
		return this.getClass().getResourceAsStream(fileName);
	}
	public ImageInputStream getResourceAsImageInputStreamByFileName(String fileName){
		
		ImageInputStream iis =null;
		try {
			iis = ImageIO.createImageInputStream(this.getClass().getResourceAsStream(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iis;
	}
	
}
