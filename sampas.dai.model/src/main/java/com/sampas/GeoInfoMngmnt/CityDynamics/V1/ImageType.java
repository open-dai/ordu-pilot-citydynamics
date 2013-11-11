/**
 * ImageType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.GeoInfoMngmnt.CityDynamics.V1;


/**
 * Image type definition
 */
public class ImageType  implements java.io.Serializable {
    private java.lang.String imageURL;

    private byte[] imageContents;

    public ImageType() {
    }

    public ImageType(
           java.lang.String imageURL,
           byte[] imageContents) {
           this.imageURL = imageURL;
           this.imageContents = imageContents;
    }


    /**
     * Gets the imageURL value for this ImageType.
     * 
     * @return imageURL
     */
    public java.lang.String getImageURL() {
        return imageURL;
    }


    /**
     * Sets the imageURL value for this ImageType.
     * 
     * @param imageURL
     */
    public void setImageURL(java.lang.String imageURL) {
        this.imageURL = imageURL;
    }


    /**
     * Gets the imageContents value for this ImageType.
     * 
     * @return imageContents
     */
    public byte[] getImageContents() {
        return imageContents;
    }


    

}
