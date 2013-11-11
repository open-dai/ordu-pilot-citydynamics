/**
 * SearchAttributeType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sampas.Common.V1;

public class SearchAttributeType  implements java.io.Serializable {
    private java.lang.String searchByAttribute;

    private com.sampas.Common.V1.SearchConstraintType[] searchConstraints;

    public SearchAttributeType() {
    }

    public SearchAttributeType(
           java.lang.String searchByAttribute,
           com.sampas.Common.V1.SearchConstraintType[] searchConstraints) {
           this.searchByAttribute = searchByAttribute;
           this.searchConstraints = searchConstraints;
    }


    /**
     * Gets the searchByAttribute value for this SearchAttributeType.
     *
     * @return searchByAttribute
     */
    public java.lang.String getSearchByAttribute() {
        return searchByAttribute;
    }


    /**
     * Sets the searchByAttribute value for this SearchAttributeType.
     *
     * @param searchByAttribute
     */
    public void setSearchByAttribute(java.lang.String searchByAttribute) {
        this.searchByAttribute = searchByAttribute;
    }


    /**
     * Gets the searchConstraints value for this SearchAttributeType.
     *
     * @return searchConstraints
     */
    public com.sampas.Common.V1.SearchConstraintType[] getSearchConstraints() {
        return searchConstraints;
    }


    /**
     * Sets the searchConstraints value for this SearchAttributeType.
     *
     * @param searchConstraints
     */
    public void setSearchConstraints(com.sampas.Common.V1.SearchConstraintType[] searchConstraints) {
        this.searchConstraints = searchConstraints;
    }

    public com.sampas.Common.V1.SearchConstraintType getSearchConstraints(int i) {
        return this.searchConstraints[i];
    }

    public void setSearchConstraints(int i, com.sampas.Common.V1.SearchConstraintType _value) {
        this.searchConstraints[i] = _value;
    }

   


}
