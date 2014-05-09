package com.sampas.socbs.core.tile.toolbox;


public class MimeType {
    protected String mimeType;
    
    protected String format;

    protected String fileExtension;

    protected String internalName;
    
    protected boolean supportsTiling;

    //private static Log log = LogFactory.getLog(org.geowebcache.mime.MimeType.class);

    public MimeType(String mimeType, String fileExtension, String internalName, String format, boolean supportsTiling) {
        this.mimeType = mimeType;
        this.fileExtension = fileExtension;
        this.internalName = internalName;
        this.format = format;
        this.supportsTiling = supportsTiling;
    }
    
    // The string representing the MIME type
    public String getMimeType() {
        return mimeType;
    }
    
    /**
     * Returns the format string, which can be different from 
     * 
     * @return format or mimetype
     */
    public String getFormat() {
        if(format != null) {
            return format;
        }
        return mimeType;
    }

    // Used for saving to files etc
    public String getFileExtension() {
        return fileExtension;
    }

    // Used for internal purposes, like picking image renderer
    public String getInternalName() {
        return internalName;
    }
    
    public boolean supportsTiling() {
        return supportsTiling;
    }
    
    /**
     * Get the MIME type object for a given MIME type string
     * 
     * @param mimeStr
     * @return
     */
    public static MimeType createFromFormat(String formatStr) throws MimeException {
        MimeType mimeType = null;
        if(formatStr == null) {
            throw new MimeException("formatStr was not set");
        }
        
        // TODO Making a special exception, generalize later
        if(! formatStr.equals("image/png; mode=24bit") && formatStr.contains(";")) {
        	
//            if(log.isDebugEnabled()) {
//                log.debug("Slicing off "+ formatStr.split(";")[1]);
//            }
        	
            formatStr = formatStr.split(";")[0];
        }
        
        if (formatStr.length() > 6 
                && formatStr.substring(0, 6).equalsIgnoreCase("image/")) {
            mimeType = ImageMime.checkForFormat(formatStr);
            
            if(mimeType != null) {
                return mimeType;
            }
        }
 
        mimeType = XMLMime.checkForFormat(formatStr);
        if(mimeType != null) {
            return mimeType;
        }
        
        mimeType = TextMime.checkForFormat(formatStr);
        if (mimeType != null) {
            return mimeType;
        }

        throw new MimeException("Unsupported format request: " + formatStr);
    }


    /**
     * Get the MIME type object for a given file extension
     * 
     * @param mimeStr
     * @return
     */
    public static MimeType createFromExtension(String fileExtension) throws MimeException {
        MimeType mimeType = null;

        mimeType = ImageMime.checkForExtension(fileExtension);
        if (mimeType != null) {
            return mimeType;
        }

        mimeType = XMLMime.checkForExtension(fileExtension);
        if (mimeType != null) {
            return mimeType;
        }
        
        mimeType = TextMime.checkForExtension(fileExtension);
        if (mimeType != null) {
            return mimeType;
        }

        //log.debug("Unsupported MIME type: " + fileExtension + ", returning null");
        return null;
    }

    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == this.getClass()) {
            MimeType mimeObj = (MimeType) obj;
            if (this.mimeType.equalsIgnoreCase(mimeObj.mimeType)) {
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        return format.hashCode();
    }
}
