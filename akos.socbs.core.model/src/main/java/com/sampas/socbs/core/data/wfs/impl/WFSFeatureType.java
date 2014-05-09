/*
 *    GeoTools - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2004-2006, Geotools Project Managment Committee (PMC)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package com.sampas.socbs.core.data.wfs.impl;

import java.net.URI;
import java.rmi.server.UID;
import org.geotools.feature.AttributeType;
import org.geotools.feature.DefaultFeature;
import org.geotools.feature.DefaultFeatureType;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureType;
import org.geotools.feature.GeometryAttributeType;
import org.geotools.feature.IllegalAttributeException;
import org.geotools.feature.SimpleFeature;
import org.geotools.geometry.jts.ReferencedEnvelope;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;

/**
 * A FeatureType that adds the information about the XMLSchema used to create the FeatureType.   
 * @author Jesse
 * @since 1.1.0
 */
@SuppressWarnings({"unchecked", "deprecation", "unused"})
class WFSFeatureType implements FeatureType {
	
    FeatureType delegate;
    private URI schemaURI;
    /**
     * If lenient then it will not throw exceptions on create() if the attributes aren't legal.
     */
    private boolean lenient;

    public WFSFeatureType( FeatureType delegate, URI schemaURI ) {
        this( delegate, schemaURI, false);
    }

    public WFSFeatureType( FeatureType delegate, URI schemaURI, boolean lenient2 ) {
        this.delegate = delegate;
        this.schemaURI=schemaURI;
        lenient=lenient2;
    }

    public Feature create( Object[] attributes, String featureID ) throws IllegalAttributeException {
        if( lenient && delegate instanceof DefaultFeatureType ){
            WFSFeatureType schema = new WFSFeatureType(delegate,schemaURI );
            return new LenientFeature(schema, attributes, featureID);
        }else{
            return delegate.create(attributes, featureID);
        }
    }
    public void setLenient( boolean lenient) {
        this.lenient=lenient;
    }

    boolean isLenient() {
        return lenient;
    }

    public Feature create( Object[] attributes ) throws IllegalAttributeException {
        return create(attributes, null);
    }

    public Feature duplicate( Feature original ) throws IllegalAttributeException {
        if( original == null ) return null;
        FeatureType featureType = original.getFeatureType();
        if (!featureType.equals(this)) { 
        throw new IllegalAttributeException("Feature type " + featureType
                        + " does not match " + this);
        }
        String id = original.getID();
        int numAtts = featureType.getAttributeCount();
        Object attributes[] = new Object[numAtts];
        for (int i = 0; i < numAtts; i++) {
        AttributeType curAttType = getAttributeType(i);
            attributes[i] = curAttType.duplicate(original.getAttribute(i));
        }
        return featureType.create(attributes, id );
    }

    public boolean equals( Object arg0 ) {
        if( !(arg0 instanceof WFSFeatureType) )
            return false;
        WFSFeatureType ft=(WFSFeatureType) arg0;
        return delegate.equals(ft.delegate);
    }

    public int find( AttributeType type ) {
        return delegate.find(type);
    }

    public int find( String attName ) {
        return delegate.find(attName);
    }

    public FeatureType[] getAncestors() {
        return delegate.getAncestors();
    }

    public int getAttributeCount() {
        return delegate.getAttributeCount();
    }

    public AttributeType getAttributeType( int position ) {
        return delegate.getAttributeType(position);
    }

    public AttributeType getAttributeType( String xPath ) {
        return delegate.getAttributeType(xPath);
    }

    public AttributeType[] getAttributeTypes() {
        return delegate.getAttributeTypes();
    }

    public GeometryAttributeType getDefaultGeometry() {
        return delegate.getDefaultGeometry();
    }
    
  
    public URI getNamespace() {
        return delegate.getNamespace();
    }

    public String getTypeName() {
        return delegate.getTypeName();
    }

    public boolean hasAttributeType( String xPath ) {
        return delegate.hasAttributeType(xPath);
    }

    public int hashCode() {
        return delegate.hashCode();
    }

    public boolean isAbstract() {
        return delegate.isAbstract();
    }

    public boolean isDescendedFrom( FeatureType type ) {
        return delegate.isDescendedFrom(type);
    }

    public boolean isDescendedFrom( URI nsURI, String typeName ) {
        return delegate.isDescendedFrom(nsURI, typeName);
    }

    public URI getSchemaURI() {
        return schemaURI;
    }
    
    public String toString() {
        return delegate.toString();
    }
    
    private static class LenientFeature implements SimpleFeature, Feature{

        /** The unique id of this feature */
        protected String featureId;

        /** Flat feature type schema for this feature. */
        private final WFSFeatureType schema;

        /** Attributes for the feature. */
        private Object[] attributes;

        /** The bounds of this feature. */
        private Envelope bounds;

        /** The collection that this Feature is a member of */
        private FeatureCollection parent;

        private int defaultGeomIndex=-1;

        private boolean constructing;

        /**
         * Creates a new instance of flat feature, which must take a flat feature
         * type schema and all attributes as arguments.
         *
         * @param schema Feature type schema for this flat feature.
         * @param attributes Initial attributes for this feature.
         * @param featureID The unique ID for this feature.
         *
         * @throws IllegalAttributeException Attribtues do not conform to feature
         *         type schema.
         * @throws NullPointerException if schema is null.
         */
        protected LenientFeature(WFSFeatureType schema, Object[] attributes,
            String featureID)
            throws IllegalAttributeException, NullPointerException {
            constructing=true;
            if (schema == null) {
                throw new NullPointerException("schema");
            }

            this.schema = schema;
            this.featureId = (featureID == null) ? defaultID() : featureID;
            this.attributes = new Object[schema.getAttributeCount()];

            setAttributes(attributes);
            
            defaultGeomIndex=schema.find(schema.getDefaultGeometry());
            constructing=false;
        }

        /**
         * Creates a new instance of flat feature, which must take a flat feature
         * type schema and all attributes as arguments.
         *
         * @param schema Feature type schema for this flat feature.
         * @param attributes Initial attributes for this feature.
         *
         * @throws IllegalAttributeException Attribtues do not conform to feature
         *         type schema.
         *
         * @task REVISIT: should we allow this?  Force users to explicitly set
         *       featureID to null?
         */
		protected LenientFeature(WFSFeatureType schema, Object[] attributes)
            throws IllegalAttributeException {
            this(schema, attributes, null);
        }

        /**
         * Creates an ID from a hashcode.
         *
         * @return an id for the feature.
         */
        String defaultID() {
            return "fid-" + new UID().toString().replace(':', '_');
        }

        /**
         * Gets a reference to the feature type schema for this feature.
         *
         * @return A copy of this feature's metadata in the form of a feature type
         *         schema.
         */
        public FeatureType getFeatureType() {
            return schema;
        }

        /**
         * Gets the unique indentification string of this Feature.
         *
         * @return The unique id.
         */
        public String getID() {
            return featureId;
        }

        /**
         * Copy all the attributes of this Feature into the given array. If the
         * argument array is null, a new one will be created. Gets all attributes
         * from this feature, returned as a complex object array.  This array
         * comes with no metadata, so to interpret this  collection the caller
         * class should ask for the schema as well.
         *
         * @param array The array to copy the attributes into.
         *
         * @return The array passed in, or a new one if null.
         */
        public Object[] getAttributes(Object[] array) {
            Object[] retArray;

            if (array == null) {
                retArray = new Object[attributes.length];
            } else {
                retArray = array;
            }

            System.arraycopy(attributes, 0, retArray, 0, attributes.length);

            return retArray;
        }

        /**
         * Gets an attribute for this feature at the location specified by xPath.
         *
         * @param xPath XPath representation of attribute location.
         *
         * @return Attribute.
         */
        public Object getAttribute(String xPath) {
            int idx = schema.find(xPath);

            if (idx == -1) {
                return null;
            }

            return attributes[idx];
        }

        /**
         * Gets an attribute by the given zero-based index.
         *
         * @param index the position of the attribute to retrieve.
         *
         * @return The attribute at the given index.
         */
        public Object getAttribute(int index) {
            return attributes[index];
        }

        /**
         * Sets the attribute at position to val.
         *
         * @param position the index of the attribute to set.
         * @param val the new value to give the attribute at position.
         *
         * @throws IllegalAttributeException if the passed in val does not validate
         *         against the AttributeType at that position.
         */
        public void setAttribute(int position, Object val)
            throws IllegalAttributeException {
            AttributeType type = schema.getAttributeType(position);

            try {
                if ((val == null) && !type.isNillable()) val = type.createDefaultValue(); 
                Object parsed = type.parse(val);
                try{
                    type.validate(parsed);
                }catch (Throwable e) {
                    if( constructing ){
//                        WFSDataStoreFactory.logger.logp(Level.WARNING, "LenientFeature", "setAttribute", "Illegal Argument but ignored since we are being lenient", e);
                    }else{
                        throw new IllegalAttributeException(type, val, e);
                    }
                }
                setAttributeValue(position, parsed);
            } catch (IllegalArgumentException iae) {
                throw new IllegalAttributeException(type, val, iae);
            }
        }

        /**
         * Sets the attribute value at a given position, performing no parsing or
         * validation. This is so subclasses can have access to setting the array,
         * without opening it up completely.
         *
         * @param position the index of the a ttribute to set.
         * @param val the new value to give the attribute at position.
         */
        protected void setAttributeValue(int position, Object val) {
            attributes[position] = val;
        }

        /**
         * Sets all attributes for this feature, passed as an array.  All
         * attributes are checked for validity before adding.
         *
         * @param attributes All feature attributes.
         *
         * @throws IllegalAttributeException Passed attributes do not match feature
         *         type.
         */
        public void setAttributes(Object[] attributes)
            throws IllegalAttributeException {
            // the passed in attributes were null, lets make that a null array
            Object[] newAtts = attributes;

            if (attributes == null) {
                newAtts = new Object[this.attributes.length];
            }

            if( constructing ){

                // We're trying to make this work no matter what 
                // so try to figure out some mapping
                Object[] tmp = assumeCorrectOrder( newAtts );
                if( tmp==null )
                    newAtts = greedyMatch(newAtts);
                else
                    newAtts=tmp;
            }else{
                if (newAtts.length != this.attributes.length) {
                    throw new IllegalAttributeException(
                        "Wrong number of attributes expected "
                        + schema.getAttributeCount() + " got " + newAtts.length);
                }
            }
            
            for (int i = 0, ii = newAtts.length; i < ii; i++) {
                setAttribute(i, newAtts[i]);
            }
        }

        private Object[] assumeCorrectOrder( Object[] newAtts ) {
            Object[] tmp=new Object[schema.getAttributeCount()];
            for( int i = 0; i < newAtts.length && i<schema.getAttributeCount(); i++ ) {
                Object object = newAtts[i];
                AttributeType att = schema.getAttributeType(i);
                if( object==null )
                    continue;
                Class requiredClass = att.getType();
                Class realClass = object.getClass();
                if( !requiredClass.isAssignableFrom(realClass) )
                    return null;
                else
                    tmp[i]=object;
                
            }
            return tmp;
        }

        private Object[] greedyMatch( Object[] newAtts ) {
            Object[] relaxedAttrs=new Object[this.attributes.length];
            boolean inValid = false;
            for( int i = 0; i < newAtts.length; i++ ) {
                Object object = newAtts[i];
                boolean found = false;
                if( object==null )
                    continue;
                Class realClass = object.getClass();
                for( int j = 0; j < schema.getAttributeCount(); j++ ) {
                    AttributeType att = schema.getAttributeType(j);
                    Class requiredClass = att.getType();
                    if( relaxedAttrs[j]==null && requiredClass.isAssignableFrom(realClass) ){
                        relaxedAttrs[j]=object;
                        found=true;
                        break;
                    }
                }
                if( !found ) inValid=true;
            }
            newAtts=relaxedAttrs;
            if( inValid ){
                StringBuffer buf=new StringBuffer();
                buf.append("WFSFeatureType#setAttributes(Object[]):");
                buf.append("\nAttributes were not correct for the feature Type:");
                buf.append(schema.getTypeName());
                buf.append(".  Made best guess:\n Recieved: ");
                for( int i = 0; i < newAtts.length; i++ ) {
                    Object object = newAtts[i];
                    buf.append(object==null?"null":object.toString());
                    buf.append(",");
                }
                buf.append("\nBest Guess: \n");
                for( int i = 0; i < relaxedAttrs.length; i++ ) {
                    Object object = relaxedAttrs[i];
                    buf.append(object==null?"null":object.toString());
                    buf.append(",");
                }

//                WFSDataStoreFactory.logger.warning(buf.toString());
            }
            return newAtts;
        }

        /**
         * Sets a single attribute for this feature, passed as a complex object. If
         * the attribute does not exist or the object does not conform to the
         * internal feature type, an exception is thrown.
         *
         * @param xPath XPath representation of attribute location.
         * @param attribute Feature attribute to set.
         *
         * @throws IllegalAttributeException Passed attribute does not match
         *         feature type
         */
        public void setAttribute(String xPath, Object attribute)
            throws IllegalAttributeException {
            int idx = schema.find(xPath);

            if (idx < 0) {
                throw new IllegalAttributeException("No attribute named " + xPath);
            }

            setAttribute(idx, attribute);
        }

        /**
         * Gets the geometry for this feature.
         *
         * @return Geometry for this feature.
         */
        public final Geometry getDefaultGeometry() {
            if (defaultGeomIndex == -1) {
                return null;
            }

            return (Geometry) attributes[defaultGeomIndex];
        }
        
        /**
         * Modifies the geometry.
         *
         * @param geometry All feature attributes.
         *
         * @throws IllegalAttributeException if the feature does not have a
         *         geometry.
         */
        public void setDefaultGeometry(Geometry geometry)
            throws IllegalAttributeException {

            if (defaultGeomIndex < 0) {
                throw new IllegalAttributeException(
                    "Feature does not have geometry");
            }

            attributes[defaultGeomIndex] = geometry;
            bounds = null;
        }
        
        /**
         * Get the number of attributes this feature has. This is simply a
         * convenience method for calling
         * getFeatureType().getNumberOfAttributes();
         *
         * @return The total number of attributes this Feature contains.
         */
        public int getNumberOfAttributes() {
            return attributes.length;
        }

        /**
         * Get the total bounds of this feature which is calculated by doing a
         * union of the bounds of each geometry this feature is associated with.
         *
         * @return An Envelope containing the total bounds of this Feature.
         *
         * @task REVISIT: what to return if there are no geometries in the feature?
         *       For now we'll return a null envelope, make this part of
         *       interface? (IanS - by OGC standards, all Feature must have geom)
         */
        public ReferencedEnvelope getBounds() {
            if (bounds == null) {
                bounds = new Envelope();

                for (int i = 0, n = schema.getAttributeCount(); i < n; i++) {
                    if (schema.getAttributeType(i) instanceof GeometryAttributeType ) {
                        Geometry g = (Geometry) attributes[i];

                        // IanS - check for null geometry!
                        if (g == null) {
                            continue;
                        }

                        Envelope e = g.getEnvelopeInternal();

                        // IanS
                        // as of JTS 1.3, expandToInclude does not check to see if
                        // Envelope is "null", and simply adds the flagged values.
                        // This ensures that this behavior does not occur.
                        if (!e.isNull()) {
                            bounds.expandToInclude(e);
                        }
                    }
                }
            }

            // lets be defensive
            return ReferencedEnvelope.reference(new Envelope(bounds));
        }

        /**
         * Creates an exact copy of this feature.
         *
         * @return A default feature.
         *
         * @throws RuntimeException DOCUMENT ME!
         */
        public Object clone() {
            try {
                DefaultFeature clone = (DefaultFeature) super.clone();

                for (int i = 0; i < attributes.length; i++) {
                    try {
                        clone.setAttribute(i, attributes[i]);
                    } catch (IllegalAttributeException e1) {
                        throw new RuntimeException("The impossible has happened", e1);
                    }
                }

                return clone;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("The impossible has happened", e);
            }
        }

        /**
         * Returns a string representation of this feature.
         *
         * @return A representation of this feature as a string.
         */
        public String toString() {
            String retString = "Feature[ id=" + getID() + " , ";
            FeatureType featType = getFeatureType();

            for (int i = 0, n = attributes.length; i < n; i++) {
                retString += (featType.getAttributeType(i).getName() + "=");
                retString += attributes[i];

                if ((i + 1) < n) {
                    retString += " , ";
                }
            }

            return retString += " ]";
        }

        /**
         * returns a unique code for this feature
         *
         * @return A unique int
         */
        public int hashCode() {
            return featureId.hashCode() * schema.hashCode();
        }

        /**
         * override of equals.  Returns if the passed in object is equal to this.
         *
         * @param obj the Object to test for equality.
         *
         * @return <code>true</code> if the object is equal, <code>false</code>
         *         otherwise.
         */
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (obj == this) {
                return true;
            }

            if (!(obj instanceof Feature)) {
                return false;
            }

            Feature feat = (Feature) obj;

            if (!feat.getFeatureType().equals(schema)) {
                return false;
            }

            // this check shouldn't exist, by contract, 
            //all features should have an ID.
            if (featureId == null) {
                if (feat.getID() != null) {
                    return false;
                }
            }

            if (!featureId.equals(feat.getID())) {
                return false;
            }

            for (int i = 0, ii = attributes.length; i < ii; i++) {
                Object otherAtt = feat.getAttribute(i);

                if (attributes[i] == null) {
                    if (otherAtt != null) {
                        return false;
                    }
                } else {
                    if (!attributes[i].equals(otherAtt)) {
                        if (attributes[i] instanceof Geometry
                                && otherAtt instanceof Geometry) {
                            // we need to special case Geometry
                            // as JTS is broken
                            // Geometry.equals( Object ) and Geometry.equals( Geometry )
                            // are different 
                            // (We should fold this knowledge into AttributeType...)
                            // 
                            if (!((Geometry) attributes[i]).equals(
                                        (Geometry) otherAtt)) {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                }
            }

            return true;
        }

        /**
         * Gets the feature collection this feature is stored in.
         *
         * @return the collection that is the parent of this feature.
         */
        public FeatureCollection getParent() {
            return parent;
        }

        /**
         * Sets the parent collection this feature is stored in, if it is not
         * already set.  If it is set then this method does nothing.
         *
         * @param collection the collection to be set as parent.
         */
        public void setParent(FeatureCollection collection) {
            if (parent == null) {
                parent = collection;
            }
        }

        
    }
}
