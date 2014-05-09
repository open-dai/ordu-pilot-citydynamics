/**
 * ComponentType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package Common.V1;


/**
 * Bilesen tipi
 */
public class ComponentType {
    private java.lang.String id;

    private java.lang.String description;

    public ComponentType() {
    }

    public ComponentType(
           java.lang.String id,
           java.lang.String description) {
           this.id = id;
           this.description = description;
    }


    /**
     * Gets the id value for this ComponentType.
     *
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this ComponentType.
     *
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the description value for this ComponentType.
     *
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ComponentType.
     *
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


}
