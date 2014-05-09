package com.sampas.socbs.core.tools.impl;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.sampas.socbs.core.tools.IImageProcesses;


public class ImageProcesses implements IImageProcesses {
	
	public BufferedImage  mergeImages(BufferedImage imageBase, BufferedImage imageNew) {
		
		try {
			BufferedImage resultImage = new BufferedImage(imageBase.getWidth(), imageBase.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
			Graphics2D resultGraph2 = resultImage.createGraphics();
			
			resultGraph2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            
			resultGraph2.drawImage(imageBase, 0, 0, null);
			
			resultGraph2.drawImage(imageNew, 0, 0, null);
			
			resultGraph2.dispose();
			
			return resultImage;
		} catch (Exception ex) {
			
			System.out.println("Error on merging images ! ERROR: " + ex);
			
			return null;
		}
	}
	
	public byte[] bufferedImageToByteArr(BufferedImage image) {

		if(image == null){
			
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] resultImageAsRawBytes = null;
		
		try {
			ImageIO.write(image, "png", baos);
			
			baos.flush();
			
			resultImageAsRawBytes = baos.toByteArray();
			
			baos.close();
			
			return resultImageAsRawBytes;
			
		} catch (Exception ex) {
			
			System.out.println("Error on converting buffered image to byte array images ! ERROR: " + ex);
			
			return null;
		}
	}
	
	public void showImageFromBufferedImage(BufferedImage imageBuffered, int showTimeSecond) {

		if (imageBuffered != null) {

			try {

				ImageIcon imgIconDoc = new ImageIcon(imageBuffered);
				
				JFrame frame = new JFrame("WMS EXAMPLE MAP");
				
				JLabel label = new JLabel(imgIconDoc);
				
				frame.getContentPane().setLayout(new BorderLayout());
				
				frame.getContentPane().add(label, BorderLayout.CENTER);
				
				frame.setSize(256, 256);
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				frame.setLocation(200, 200);
				
				frame.setVisible(true);
				
				Thread.currentThread();
				
				Thread.sleep(showTimeSecond * 1000);
			} catch (Exception ex) {

				System.out.println("Error on creating image from byte array");
			}

		} else {

			System.out.println("Error on image input");
		}
	}
	
	public BufferedImage byteArrToBufferedImg(byte[] byteImage) {
		
		if(byteImage == null || byteImage.length < 0){
			
			return null;
		}
		try {

			InputStream inputStream = new ByteArrayInputStream(byteImage);
			
			BufferedImage image = ImageIO.read(inputStream);
			
			return (image);
		} catch (Exception ex) {
			
			System.out.println("Error on converting byte array to buffered image ! ERROR: " + ex);
			
			return null;
		}
	}

	public byte[] fileToByteArr(File file) throws IOException {

        InputStream is = new FileInputStream(file);
        
        System.out.println("\nDEBUG: FileInputStream is " + file);
        // Get the size of the file
        long length = file.length();
        
        System.out.println("DEBUG: Length of " + file + " is " + length + "\n");
        /*
         * You cannot create an array using a long type. It needs to be an int
         * type. Before converting to an int type, check to ensure that file is
         * not loarger than Integer.MAX_VALUE;
         */
        if (length > Integer.MAX_VALUE) {
        	
            System.out.println("File is too large to process");
            
            return null;
        }
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
        // Read in the bytes
        int offset = 0;
        
        int numRead = 0;
        
        while ( (offset < bytes.length) && ( (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) ) {

            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
        	
            throw new IOException("Could not completely read file " + file.getName());
        }
        is.close();
        
        return bytes;
    }
	
}
