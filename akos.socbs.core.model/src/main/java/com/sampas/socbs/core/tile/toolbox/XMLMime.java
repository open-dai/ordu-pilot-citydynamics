package com.sampas.socbs.core.tile.toolbox;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@SuppressWarnings("unused")
public class XMLMime extends MimeType {
    
	private static Log log = LogFactory.getLog(com.sampas.socbs.core.tile.toolbox.XMLMime.class);

    public static final XMLMime ogcxml = new XMLMime(
       "application/vnd.ogc.se_xml", "ogc-xml", "ogc-xml",
       "application/vnd.ogc.se_xml", true);

    public static final XMLMime kml = new XMLMime(
            "application/vnd.google-earth.kml+xml", "kml", "kml",
            "application/vnd.google-earth.kml+xml", true);
    
    public static final XMLMime kmz = new XMLMime(
            "application/vnd.google-earth.kmz", "kmz", "kmz",
            "application/vnd.google-earth.kmz", true);
    
    public static final XMLMime gml = new XMLMime(
            "application/vnd.ogc.gml", "gml", "gml",
            "application/vnd.ogc.gml", true);
    
    private XMLMime(String mimeType, String fileExtension, 
                String internalName, String format, boolean noop) {
        super(mimeType, fileExtension, internalName, format, false);
    }
        
    public XMLMime(String mimeType, String fileExtension, 
            String internalName, String format) throws MimeException {        
        super(mimeType, fileExtension, internalName, format, false);
        
        // Check for trouble
        //if(mimeType.length() < 12 || ! mimeType.substring(0,12).equalsIgnoreCase("application/")) {
        //    throw new MimeException("MIME type " + mimeType + " does not start with application/");
        //}
    }

    protected static XMLMime checkForFormat(String formatStr) throws MimeException {
        if(formatStr.equalsIgnoreCase("application/vnd.google-earth.kml+xml")) {
            return kml;
        } else if (formatStr.equalsIgnoreCase("application/vnd.google-earth.kmz")) {
            return kmz;
        } else if(formatStr.equalsIgnoreCase("application/vnd.ogc.se_xml")) {
            return ogcxml;
        } else if (formatStr.equalsIgnoreCase("application/vnd.ogc.gml")) {
            return gml;
        }
        
        return null;
    }
    
    //public static XMLMime createFromMimeType(String mimeType) throws MimeException {
    //    XMLMime xmlMime = checkForMimeType(mimeType);
    //    if(xmlMime == null) {
    //        log.error("Unsupported MIME type: " + mimeType + ", returning null.");
    //    }
    //    
    //    return xmlMime;
    //}
    
    protected static XMLMime checkForExtension(String fileExtension) throws MimeException {
        if (fileExtension.equalsIgnoreCase("kml")) {
            return kml;
        } else if (fileExtension.equalsIgnoreCase("kmz")) {
            return kmz;
        } else if (fileExtension.equalsIgnoreCase("gml")) {
            return gml;
        }
        
        return null;
    }
    
//    public static XMLMime createFromExtension(String fileExtension) throws MimeException {
//        XMLMime xmlMime = checkForExtension(fileExtension);
//        if(xmlMime == null) {
//            log.error("Unsupported MIME type: " + fileExtension + ", returning null");
//        }
//        
//        return xmlMime;
//    }
}
