/**
 * RecordType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.Common.V1;


/**
 * Kayit tipi
 */
public class RecordType  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String createdBy;

    private java.lang.String createdDate;

    private java.lang.String updatedBy;

    private java.lang.String updatedDate;

    public RecordType() {
    }

    public RecordType(
           java.lang.String id,
           java.lang.String createdBy,
           java.lang.String createdDate,
           java.lang.String updatedBy,
           java.lang.String updatedDate) {
           this.id = id;
           this.createdBy = createdBy;
           this.createdDate = createdDate;
           this.updatedBy = updatedBy;
           this.updatedDate = updatedDate;
    }


    /**
     * Gets the id value for this RecordType.
     *
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this RecordType.
     *
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the createdBy value for this RecordType.
     *
     * @return createdBy
     */
    public java.lang.String getCreatedBy() {
        return createdBy;
    }


    /**
     * Sets the createdBy value for this RecordType.
     *
     * @param createdBy
     */
    public void setCreatedBy(java.lang.String createdBy) {
        this.createdBy = createdBy;
    }


    /**
     * Gets the createdDate value for this RecordType.
     *
     * @return createdDate
     */
    public java.lang.String getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate value for this RecordType.
     *
     * @param createdDate
     */
    public void setCreatedDate(java.lang.String createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets the updatedBy value for this RecordType.
     *
     * @return updatedBy
     */
    public java.lang.String getUpdatedBy() {
        return updatedBy;
    }


    /**
     * Sets the updatedBy value for this RecordType.
     *
     * @param updatedBy
     */
    public void setUpdatedBy(java.lang.String updatedBy) {
        this.updatedBy = updatedBy;
    }


    /**
     * Gets the updatedDate value for this RecordType.
     *
     * @return updatedDate
     */
    public java.lang.String getUpdatedDate() {
        return updatedDate;
    }


    /**
     * Sets the updatedDate value for this RecordType.
     *
     * @param updatedDate
     */
    public void setUpdatedDate(java.lang.String updatedDate) {
        this.updatedDate = updatedDate;
    }



}
