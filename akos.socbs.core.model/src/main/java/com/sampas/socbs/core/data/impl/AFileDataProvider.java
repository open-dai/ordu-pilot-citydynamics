package com.sampas.socbs.core.data.impl;

import java.io.IOException;

import org.geotools.data.AbstractDataStore;
import org.geotools.data.FeatureReader;
import org.geotools.data.FeatureSource;
import org.geotools.data.FeatureWriter;
import org.geotools.data.Transaction;
import org.geotools.feature.FeatureType;
import org.opengis.filter.Filter;

import com.sampas.socbs.core.data.IFeatureDataStore;

public abstract class AFileDataProvider extends AbstractDataStore implements IFeatureDataStore {
	   /**
     * Singular version, returns the FeatureType for the url being read.
     *
     * @see org.geotools.data.DataStore#getSchema(java.lang.String)
     */
    public abstract FeatureType getSchema() throws IOException;

    /**
     * Singular version, which must be implemented to represent a Reader  for
     * the url being read.
     *
     * @see org.geotools.data.DataStore#getFeatureReader(java.lang.String)
     */
    protected abstract FeatureReader getFeatureReader()
        throws IOException;

    /**
     * Singular version, calls parent with getSchema().getTypeName()
     *
     * @see org.geotools.data.DataStore#updateSchema(java.lang.String,
     *      org.geotools.feature.FeatureType)
     */
    public void updateSchema(FeatureType featureType) throws IOException {
        updateSchema(getSchema().getTypeName(), featureType);
    }

    /**
     * Singular version, calls parent with getSchema().getTypeName()
     *
     * @see org.geotools.data.DataStore#getFeatureSource(java.lang.String)
     */
    public FeatureSource getFeatureSource() throws IOException {
        return getFeatureSource(getSchema().getTypeName());
    }

    /**
     * Singular version, calls parent with getSchema().getTypeName()
     */
    public FeatureWriter getFeatureWriter(Filter filter, Transaction transaction)
        throws IOException {
        return getFeatureWriter(getSchema().getTypeName(), filter, transaction);
    }

    /**
     * @see org.geotools.data.DataStore#getFeatureWriter(java.lang.String,
     *      org.geotools.data.Transaction)
     */
    public FeatureWriter getFeatureWriter(Transaction transaction)
        throws IOException {
        return getFeatureWriter(getSchema().getTypeName(), transaction);
    }

    /**
     * @see org.geotools.data.DataStore#getFeatureWriterAppend(java.lang.String,
     *      org.geotools.data.Transaction)
     */
    public FeatureWriter getFeatureWriterAppend(Transaction transaction)
        throws IOException {
        return getFeatureWriterAppend(getSchema().getTypeName(), transaction);
    }
}
