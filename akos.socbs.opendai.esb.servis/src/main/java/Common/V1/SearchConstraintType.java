/**
 * SearchConstraintType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package Common.V1;

public class SearchConstraintType  {
    private java.lang.String operator;

    private java.lang.String[] values;

    public SearchConstraintType() {
    }

    public SearchConstraintType(
           java.lang.String operator,
           java.lang.String[] values) {
           this.operator = operator;
           this.values = values;
    }


    /**
     * Gets the operator value for this SearchConstraintType.
     *
     * @return operator
     */
    public java.lang.String getOperator() {
        return operator;
    }


    /**
     * Sets the operator value for this SearchConstraintType.
     *
     * @param operator
     */
    public void setOperator(java.lang.String operator) {
        this.operator = operator;
    }


    /**
     * Gets the values value for this SearchConstraintType.
     *
     * @return values
     */
    public java.lang.String[] getValues() {
        return values;
    }


    /**
     * Sets the values value for this SearchConstraintType.
     *
     * @param values
     */
    public void setValues(java.lang.String[] values) {
        this.values = values;
    }

    public java.lang.String getValues(int i) {
        return this.values[i];
    }

    public void setValues(int i, java.lang.String _value) {
        this.values[i] = _value;
    }

    



}
