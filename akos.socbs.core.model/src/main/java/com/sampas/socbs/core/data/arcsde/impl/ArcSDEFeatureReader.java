/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002-2008, Geotools Project Managment Committee (PMC)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package com.sampas.socbs.core.data.arcsde.impl;

import java.io.IOException;

import org.geotools.data.AttributeReader;
import org.geotools.data.DefaultFeatureReader;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureType;
import org.geotools.feature.IllegalAttributeException;
import org.geotools.feature.SchemaException;

/**
 * FeatureReader optimized for ArcSDE access.
 * 
 * @author Gabriel Roldan (TOPP)
 * @version $Id: ArcSDEFeatureReader.java 29625 2008-03-14 13:55:02Z groldan $
 * @since 2.5
 * @source $URL:
 *         http://svn.geotools.org/geotools/trunk/gt/modules/plugin/arcsde/datastore/src/main/java/org/geotools/arcsde/data/ArcSDEFeatureReader.java $
 */
public class ArcSDEFeatureReader extends DefaultFeatureReader {

    private FeatureType featureType;

    public ArcSDEFeatureReader(final ArcSDEAttributeReader attributeReader) throws SchemaException {
        super(attributeReader, attributeReader.getFeatureType());

        this.featureType = attributeReader.getFeatureType();
        // this.featureBuilder = new SimpleFeatureBuilder(featureType);
    }

    // @Override
    protected Feature readFeature(final AttributeReader atts) throws IllegalAttributeException,
            IOException {
        final ArcSDEAttributeReader sdeAttReader = (ArcSDEAttributeReader) atts;

        final int attCount = sdeAttReader.getAttributeCount();
        Object value;
        Object[] attributes = new Object[attCount];
        for (int index = 0; index < attCount; index++) {
            value = sdeAttReader.read(index);
            attributes[index] = value;
        }
        String fid = sdeAttReader.readFID();
        Feature feature = featureType.create(attributes, fid);
        // SimpleFeature feature = featureBuilder.buildFeature(fid);
        return feature;
    }

}
