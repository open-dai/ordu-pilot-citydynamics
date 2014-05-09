package com.sampas.socbs.opendai.servis.tools;

import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class SmpMetaDataHandler {

	private String filename;

	public SmpMetaDataHandler(String filename) {

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
