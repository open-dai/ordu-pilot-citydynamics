package com.sampas.socbs.core.tiler.tool.impl;

import java.io.File;

import com.sampas.socbs.core.tile.toolbox.MimeType;

public class FilePathGenerator {
	
    public String[] tilePath(String prefix, String layerName, long[] tileIndex, int srsNumb, MimeType mimeType, long parameters_id) {
    	
        long x = tileIndex[0];
        long y = tileIndex[1];
        long z = tileIndex[2];
        
        String srsStr = "EPSG_"+ srsNumb;
        
        layerName = layerName.replace(':', '_').replace(' ', '_');
        
        String paramStr = "";
        if(parameters_id != -1L) {
            paramStr = "_" + Long.toHexString(parameters_id);
        }
        
        long shift = z / 2;
        long half = 2 << shift;
        int digits = 1;
        if (half > 10) {
            digits = (int) (Math.log10(half)) + 1;
        }
        long halfx = x / half;
        long halfy = y / half;

        String fileExtension = mimeType.getFileExtension();
        
        String[] ret = new String[2];
        
        ret[0] = prefix + File.separator + layerName + File.separator
                + srsStr + "_" + zeroPadder(z, 2) + paramStr + File.separator 
                + zeroPadder(halfx, digits) + "_" 
                + zeroPadder(halfy, digits);
        
        ret[1] = zeroPadder(x, 2 * digits) + "_" + zeroPadder(y, 2 * digits) + "." + fileExtension;
        
        return ret;
    }
    
    public static String zeroPadder(long number, int order) {
    	
        int numberOrder = 1;

        if (number > 9) {
            if(number > 11) {
                numberOrder = (int) Math.ceil(Math.log10(number) - 0.001);
            } else {
                numberOrder = 2;
            }
        }

        int diffOrder = order - numberOrder;
        
        StringBuilder padding = new StringBuilder(diffOrder);
        
        while (diffOrder > 0) {
            padding.append("0");
            diffOrder--;
        }

        return padding.toString() + Long.toString(number);
    }
    
}
