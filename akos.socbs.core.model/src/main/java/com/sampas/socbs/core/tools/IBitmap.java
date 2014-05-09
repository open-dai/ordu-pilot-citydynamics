package com.sampas.socbs.core.tools;

import java.awt.image.BufferedImage;

public interface IBitmap {
	
	public void setBufferedImage(BufferedImage bufferedImage);

	public BufferedImage getBufferedImage();
	
	public void setFileName(String fileName);
	
	public String getFileName();
	

}
