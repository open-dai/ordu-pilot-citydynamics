package com.sampas.socbs.opendai.servis.tools;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ImageTypeConverter {

	public BufferedImage ByteArrToBufferedImg(byte[] byteArr) {
		
		try {
			InputStream legendStream = new ByteArrayInputStream(byteArr);

			BufferedImage bufferedImage = null;

			bufferedImage = javax.imageio.ImageIO.read(legendStream);

			return bufferedImage;
			
		} catch (Exception ex) {
			
			System.out.println("Error on converting operation ! ERROR : " + ex);
			
			return null;
		}
 	}
	
}
