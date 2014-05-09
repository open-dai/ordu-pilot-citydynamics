package com.sampas.socbs.core.tiler.tool.tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

import com.sampas.socbs.core.tools.impl.ImageProcesses;

public class AddByteToFileJTests extends TestCase {
	
	public void testRemoveHeaderToFile() {
		
		try {
			
			File file = new File("C://Documents and Settings//Itosunoglu//Desktop//test.png");
			
			byte[] fileAsByte = getBytesFromFile(file);
			
			byte[] newFileByte = new byte[fileAsByte.length - 16];
			
			int cntr = 0;
			for (int i = 16; i < fileAsByte.length; i++) {
				newFileByte[cntr] = fileAsByte[i];
				cntr++;
			}
			
			ImageProcesses imgTools = new ImageProcesses();
			BufferedImage newBufImage = imgTools.byteArrToBufferedImg(newFileByte);
			
			imgTools.showImageFromBufferedImage(newBufImage, 5);
			
			System.out.print("Debug Stoper");
			
			//tileImage = ImageIO.read(file);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
//		ImageProcesses imgTools = new ImageProcesses();
//		imgTools.showImageFromBufferedImage(tileImage, 5);
	}

	public static byte[] getBytesFromFile(File file) throws IOException {
        
		InputStream is = new FileInputStream(file);
    
        // Get the size of the file
        long length = file.length();
    
        if (length > Integer.MAX_VALUE) {
        	
            System.out.println("File is too large !");
            return null;
        }
    
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        
        while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
        	
            throw new IOException("Could not completely read file "+file.getName());
        }
    
        // Close the input stream and return bytes
        is.close();
        return bytes;
    } 
	
}
