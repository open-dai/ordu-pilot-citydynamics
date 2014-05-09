package com.sampas.socbs.core.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public interface IImageProcesses {

	public BufferedImage  mergeImages(BufferedImage imageBase, BufferedImage imageNew);
	
	public byte[] bufferedImageToByteArr(BufferedImage image);
	
	public abstract void showImageFromBufferedImage(BufferedImage imageBuffered, int showTimeSecond);
	
	public BufferedImage byteArrToBufferedImg(byte[] byteImage);
	
	public byte[] fileToByteArr(File file) throws IOException;
	
}
