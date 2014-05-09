package com.sampas.socbs.core.tools.impl;

import java.awt.image.BufferedImage;

import com.sampas.socbs.core.tools.IBitmap;

public class SmpBitmap implements IBitmap {

	private BufferedImage bufferedImage;
	private String fileName;
	

	public String getFileName() {

		return this.fileName;
	}

	public void setFileName(String fileName) {
	
		this.fileName = fileName;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

}
