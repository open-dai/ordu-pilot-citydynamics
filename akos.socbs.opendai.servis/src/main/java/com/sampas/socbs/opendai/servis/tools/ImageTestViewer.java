package com.sampas.socbs.opendai.servis.tools;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageTestViewer {

	public void showImageFromBufferedImage(BufferedImage imageBuffered, int imgWith, int imgHeight, int showSecond) {
		
		if (imageBuffered != null) {

			try {
				
				ImageIcon imgIconDoc = new ImageIcon(imageBuffered);
				JFrame frame = new JFrame("WMS EXAMPLE MAP");
				JLabel label = new JLabel(imgIconDoc);
				frame.getContentPane().setLayout(new BorderLayout());
				frame.getContentPane().add(label, BorderLayout.CENTER);
				frame.setSize(imgWith, imgHeight);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocation(300, 300);
				frame.setVisible(true);
				Thread.currentThread();
				Thread.sleep(showSecond);
			} catch (Exception ex) {
				
				System.out.println("Error on creating image from byte array");
			}

		} else {
			
			System.out.println("Error on image input");
		}
	}
	
	public void showImageFromByteArray(byte[] imageByte , int imgWith, int imgHeight, int showSecond) {
		
		if (imageByte.length != 0) {

			try {
				
				ImageIcon imgIconDoc = new ImageIcon(imageByte);
				JFrame frame = new JFrame("WMS EXAMPLE MAP");
				JLabel label = new JLabel(imgIconDoc);
				frame.getContentPane().setLayout(new BorderLayout());
				frame.getContentPane().add(label, BorderLayout.CENTER);
				frame.setSize(imgWith, imgHeight);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocation(300, 300);
				frame.setVisible(true);
				Thread.currentThread();
				Thread.sleep(showSecond);
			} catch (Exception ex) {
				
				System.out.println("Error on creating image from byte array");
			}

		} else {
			
			System.out.println("Error on image input");
		}
	}
}
