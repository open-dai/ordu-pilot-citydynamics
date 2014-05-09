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
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.geotools.data.DataSourceException;
import org.geotools.data.DefaultQuery;
import org.geotools.data.FeatureReader;
import org.geotools.data.Query;
import org.geotools.data.store.DataFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureReaderIterator;
import org.geotools.feature.FeatureType;
import org.geotools.feature.GeometryAttributeType;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * FeatureCollection implementation that works over an
 * {@link ArcSDEFeatureReader} or one of the decorators over it returned by
 * {@link ArcSDEDataStore#getFeatureReader(Query, ArcSDEPooledConnection, boolean)}.
 * 
 * @author Gabriel Roldan (TOPP)
 * @version $Id: ArcSdeFeatureCollection.java 30166 2008-05-08 09:29:04Z groldan $
 * @since 2.5
 * @source $URL:
 *         http://svn.geotools.org/geotools/trunk/gt/modules/plugin/arcsde/datastore/src/main/java/org/geotools/arcsde/data/ArcSdeFeatureCollection.java $
 * @see FeatureCollection
 */
@SuppressWarnings("unchecked")
public class ArcSdeFeatureCollection extends DataFeatureCollection {

    private final ArcSdeFeatureSource featureSource;

    private final Query query;

    private final Set/* <FeatureReaderIterator<SimpleFeature>> */openIterators;

    private ArcSDEPooledConnection _connection;

    private FeatureType childrenSchema;

    public ArcSdeFeatureCollection(final ArcSdeFeatureSource featureSource, final Query namedQuery) {
        this.featureSource = featureSource;
        this.query = namedQuery;

        final Set/* <FeatureReaderIterator<SimpleFeature>> */iterators;
        iterators = new HashSet/* <FeatureReaderIterator<SimpleFeature>> */();
        this.openIterators = Collections.synchronizedSet(iterators);
    }

    /**
     * @see FeatureCollection#getSchema()
     */
    // @Override
    public final synchronized FeatureType getSchema() {
        if (childrenSchema == null) {
            try {
                final ArcSDEPooledConnection conn = getConnection();
                final ArcSDEDataStore dataStore = (ArcSDEDataStore) featureSource.getDataStore();
                DefaultQuery excludeFilterQuery = new DefaultQuery(this.query);
                excludeFilterQuery.setFilter(Filter.EXCLUDE);

                final FeatureReader/* <SimpleFeatureType, SimpleFeature> */reader;
                reader = dataStore.getFeatureReader(excludeFilterQuery, conn, false,
                        ArcSdeVersionHandler.NONVERSIONED_HANDLER);

                this.childrenSchema = reader.getFeatureType();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't fetch schema for query " + query, e);
            } finally {
                closeConnection();
            }
        }
        return childrenSchema;
    }

    /**
     * @see FeatureCollection#getBounds()
     */
    // @Override
    public final ReferencedEnvelope getBounds() {
        ReferencedEnvelope bounds;
        try {
            final ArcSDEPooledConnection connection = getConnection();
            bounds = featureSource.getBounds(query, connection);
            if (bounds == null) {
                bounds = new ReferencedEnvelope(getCRS());
            }
        } catch (IOException e) {
            bounds = new ReferencedEnvelope(getCRS());
        } finally {
            closeConnection();
        }
        return bounds;
    }

    private CoordinateReferenceSystem getCRS() {
        GeometryAttributeType defaultGeometry = this.featureSource.getSchema().getDefaultGeometry();
        return defaultGeometry == null ? null : defaultGeometry.getCoordinateSystem();
    }

    // @Override
    public final int getCount() throws IOException {
        final ArcSDEPooledConnection connection = getConnection();
        try {
            return featureSource.getCount(query, connection);
        } finally {
            closeConnection();
        }
    }

    // @Override
    protected final void closeIterator(Iterator close) throws IOException {
        FeatureReaderIterator/* <SimpleFeature> */iterator = (FeatureReaderIterator/* <SimpleFeature> */) close;
        iterator.close(); // only needs package visability
    }

    /**
     * Returns
     */
    // @Override
    protected final Iterator openIterator() throws IOException {
        final ArcSDEDataStore dataStore = (ArcSDEDataStore) featureSource.getDataStore();
        final ArcSDEPooledConnection connection = getConnection();

        ArcSdeVersionHandler versionHandler = featureSource.getVersionHandler();
        final FeatureReader/* <SimpleFeatureType, SimpleFeature> */reader;
        try {
            reader = dataStore.getFeatureReader(query, connection, false, versionHandler);
        } catch (IOException e) {
            if (!_connection.isPassivated()) {
                _connection.getLock().release();// .unlock();
                if (!_connection.isTransactionActive()) {
                    _connection.close();
                }
            }
            _connection = null;
            throw e;
        }
        // slight optimization here: store the child features schema if not yet
        // done
        if (this.childrenSchema == null) {
            this.childrenSchema = reader.getFeatureType();
        }

        final FeatureReaderIterator/* <SimpleFeature> */iterator;
        iterator = new FeatureReaderIterator/* <SimpleFeature> */(reader) {
            // @Override
            public void close() {
                super.close();
                openIterators.remove(this);
                closeConnection();
            }
        };
        openIterators.add(iterator);
        return iterator;
    }

    private synchronized ArcSDEPooledConnection getConnection() throws IOException {
        if (_connection == null || _connection.isPassivated()) {
            _connection = featureSource.getConnection();
            try {
                _connection.getLock().acquire();// .lock();
            } catch (InterruptedException e) {
                throw new DataSourceException(e);
            }
        }
        return _connection;
    }

    private synchronized void closeConnection() {
        if (openIterators.size() == 0) {
            if (!_connection.isPassivated()) {
                _connection.getLock().release();// .unlock();
                if (!_connection.isTransactionActive()) {
                    _connection.close();
                }
            }
            _connection = null;
        }
    }
}
