package com.sampas.socbs.core.tile.toolbox;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@SuppressWarnings("unused")
public class ErrorMime extends MimeType {
    
	private static Log log = LogFactory.getLog(com.sampas.socbs.core.tile.toolbox.ErrorMime.class);
    
	public static final ErrorMime vnd_ogc_se_inimage  = new ErrorMime("application/vnd.ogc.se_inimage");
    
    private ErrorMime(String mimeType) {
        super(mimeType,null,null,mimeType,false);
    }
    
    public ErrorMime(String mimeType, String fileExtension, String internalName, String format)
    throws MimeException {
        super(mimeType, fileExtension, internalName, format, false);
        
        // Check for trouble
        if(mimeType.length() < 12 || ! mimeType.substring(0,12).equalsIgnoreCase("application/")) {
            throw new MimeException("MIME type " + mimeType + " does not start with application/");
        }
    }
    
    public static ErrorMime createFromMimeType(String mimeType) throws MimeException {
        if (mimeType.equalsIgnoreCase("application/vnd.ogc.se_inimage")) {
            return vnd_ogc_se_inimage;
        } else {
            
        	//log.error("Unsupported MIME type: " + mimeType + ", falling back to application/vnd.ogc.se_inimage.");
            return vnd_ogc_se_inimage;
        }
    }
}
