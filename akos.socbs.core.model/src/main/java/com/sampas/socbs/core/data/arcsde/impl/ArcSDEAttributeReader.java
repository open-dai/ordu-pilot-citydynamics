/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002-2006, Geotools Project Managment Committee (PMC)
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
 *
 */
package com.sampas.socbs.core.data.arcsde.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geotools.data.AttributeReader;
import org.geotools.data.DataSourceException;
import org.geotools.feature.AttributeType;
import org.geotools.feature.FeatureType;
import org.geotools.feature.GeometryAttributeType;
import com.esri.sde.sdk.client.SeException;
import com.esri.sde.sdk.client.SeQuery;
import com.esri.sde.sdk.client.SeShape;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

/**
 * Implements an attribute reader that is aware of the particulars of ArcSDE.
 * This class sends its logging to the log named "org.geotools.data".
 * 
 * @author Gabriel Roldan, Axios Engineering
 * @source $URL:
 *         http://svn.geotools.org/geotools/trunk/gt/modules/plugin/arcsde/datastore/src/main/java/org/geotools/arcsde/data/ArcSDEAttributeReader.java $
 * @version $Id: ArcSDEAttributeReader.java 30166 2008-05-08 09:29:04Z groldan $
 */
@SuppressWarnings("unchecked")
class ArcSDEAttributeReader implements AttributeReader {
    /** Shared package's logger */
    private static final Logger LOGGER = org.geotools.util.logging.Logging
            .getLogger("org.geotools.data");

    /** query passed to the constructor */
    private ArcSDEQuery query;

    /** schema of the features this attribute reader iterates over */
    private FeatureType schema;

    /** current sde java api row being read */
    private SdeRow currentRow;

    /**
     * the unique id of the current feature. -1 means the feature id was not
     * retrieved
     */
    private long currentFid = -1;

    /**
     * holds the "&lt;DATABASE_NAME&gt;.&lt;USER_NAME&gt;." string and is used
     * to efficiently create String FIDs from the SeShape feature id, which is a
     * long number.
     */
    private StringBuffer fidPrefix;

    /**
     * lenght of the prefix string for creating string based feature ids, used
     * to truncate the <code>fidPrefix</code> and append it the SeShape's
     * feature id number
     */
    private int fidPrefixLen;

    /**
     * Strategy to read FIDs
     */
    private FIDReader fidReader;

    /**
     * flag to avoid the processing done in <code>hasNext()</code> if next()
     * was not called between calls to hasNext()
     */
    private boolean hasNextAlreadyCalled = false;

    /**
     * Declared binding for the schema's default geometry
     */
    private final Class/* <? extends Geometry> */schemaGeometryClass;

    private ArcSDEPooledConnection connection;

    /**
     * Flag indicating whether to close or not the connection.
     * <p>
     * If <code>true</code> the connection is automatically closed when the
     * reader is exhausted or then {@link #close()} is called. Otherwise it is
     * untouched. Rationale being an {@link ArcSDEFeatureReader} using this
     * attribute reader may be acting as the streamed content for a
     * FeatureWriter and sharing the connection, so closing the connection
     * becomes the responsibility of the feature writer, or it might be returned
     * to the pool while the writer is still using it.
     * </p>
     */
    private boolean handleConnectionClosing;

    /**
     * The query that defines this readers interaction with an ArcSDE instance.
     * 
     * @param query
     *            the {@link SeQuery} wrapper where to fetch rows from. Must NOT
     *            be already {@link ArcSDEQuery#execute() executed}.
     * @param connection
     *            the connection the <code>query</code> is being ran over.
     *            This attribute reader will close it only if it does not have a
     *            transaction in progress.
     * @param handleConnectionClosing
     *            whether to close or not the connection when done. Useful to
     *            allow a feature reader working over this attribute reader to
     *            stream out the filtered content of an
     *            {@link AutoCommitFeatureWriter}.
     * @throws IOException
     */
    public ArcSDEAttributeReader(final ArcSDEQuery query, final ArcSDEPooledConnection connection,
            final boolean handleConnectionClosing) throws IOException {
        this.query = query;
        this.connection = connection;
        this.handleConnectionClosing = handleConnectionClosing;
        this.fidReader = query.getFidReader();
        this.schema = query.getSchema();

        String typeName = schema.getTypeName();

        this.fidPrefix = new StringBuffer(typeName).append('.');
        this.fidPrefixLen = this.fidPrefix.length();

        final GeometryAttributeType geomType = schema.getDefaultGeometry();

        if (geomType != null) {
            this.schemaGeometryClass = (Class/* <? extends Geometry> */) geomType.getBinding();
        } else {
            this.schemaGeometryClass = null;
        }

        // get the lock before executing the query so streams don't get
        // confused,
        // and keep it until close() is called on this class
        try {
            connection.getLock().acquire();// .lock();
        } catch (InterruptedException e1) {
            throw new DataSourceException(e1);
        }
        try {
            query.execute();
        } catch (IOException e) {
            connection.getLock().release();// .unlock();
            throw e;
        } catch (RuntimeException e) {
            connection.getLock().release();// .unlock();
            LOGGER.log(Level.SEVERE, "Runtime exception creating SeQuery, returning connection", e);
            throw new DataSourceException(e);
        }
    }

    /**
     * 
     */
    public int getAttributeCount() {
        return this.schema.getAttributeCount();
    }

    /**
     * 
     */
    public AttributeType getAttributeType(int index) throws ArrayIndexOutOfBoundsException {
        return this.schema.getAttributeType(index);
    }

    /**
     * Closes the associated query object and, if this attribute reader is not
     * being run over a connection with a transaction in progress, closes the
     * connection too.
     */
    public void close() throws IOException {
        if (query != null) {
            this.query.close();
            if (handleConnectionClosing) {
                if (!connection.isTransactionActive()) {
                    connection.close();
                }
            }
            if (connection != null) {
                connection.getLock().release();// .unlock();
            }
            query = null;
            connection = null;
            schema = null;
            fidReader = null;
            currentRow = null;
        }
    }

    /**
     * 
     */
    public boolean hasNext() throws IOException {
        if (!this.hasNextAlreadyCalled) {
            try {
                currentRow = query.fetch();
                if (currentRow == null) {
                    this.query.close();
                } else {
                    this.currentFid = fidReader.readFid(currentRow);
                }
                hasNextAlreadyCalled = true;
            } catch (IOException dse) {
                this.hasNextAlreadyCalled = true;
                close();
                LOGGER.log(Level.SEVERE, dse.getLocalizedMessage(), dse);
                throw dse;
            } catch (RuntimeException ex) {
                this.hasNextAlreadyCalled = true;
                close();
                throw new DataSourceException("Fetching row:" + ex.getMessage(), ex);
            }
        }

        boolean hasNext = this.currentRow != null;
        if (!hasNext) {
            // be cautious of clients not calling close and letting stale
            // connections
            // TODO: it might be better to do a sanity check on finalize()
            // and require client code to respect contract.
            close();
        }
        return hasNext;
    }

    /**
     * Retrieves the next row, or throws a DataSourceException if not more rows
     * are available.
     * 
     * @throws IOException
     *             DOCUMENT ME!
     * @throws DataSourceException
     *             DOCUMENT ME!
     */
    public void next() throws IOException {
        if (this.currentRow == null) {
            throw new DataSourceException("There are no more rows");
        }

        this.hasNextAlreadyCalled = false;
    }

    /**
     * DOCUMENT ME!
     * 
     * @param index
     *            DOCUMENT ME!
     * @return DOCUMENT ME!
     * @throws IOException
     *             never, since the feature retrieve was done in
     *             <code>hasNext()</code>
     * @throws ArrayIndexOutOfBoundsException
     *             if <code>index</code> is outside the bounds of the schema
     *             attribute's count
     */
    public Object read(int index) throws IOException, ArrayIndexOutOfBoundsException {
        Object value = currentRow.getObject(index);
        if (value instanceof SeShape) {
            try {
                final SeShape shape = (SeShape) value;
                final Class/* <? extends Geometry> */actualGeomtryClass;
                if (shape.isNil()) {
                    // actualGeomtryClass = this.schemaGeometryClass;
                    value = null;
                } else {
                    actualGeomtryClass = ArcSDEAdapter.getGeometryTypeFromSeShape(shape);
                    final ArcSDEGeometryBuilder geometryBuilder;
                    geometryBuilder = ArcSDEGeometryBuilder.builderFor(actualGeomtryClass);
                    value = geometryBuilder.construct(shape);
                    if (!this.schemaGeometryClass.isAssignableFrom(actualGeomtryClass)) {
                        value = adaptGeometry((Geometry) value, schemaGeometryClass);
                    }
                }
            } catch (SeException e) {
                throw new ArcSdeException(e);
            }
        }

        return value;
    }

    private Geometry adaptGeometry(final Geometry value, Class/*
                                                                 * <? extends
                                                                 * Geometry>
                                                                 */targetType) {
        final Class/* <? extends Geometry> */currentClass = value.getClass();
        final GeometryFactory factory = value.getFactory();

        Geometry adapted;
        if (MultiPoint.class == targetType && Point.class == currentClass) {
            adapted = factory.createMultiPoint(value.getCoordinates());
        } else if (MultiLineString.class == targetType && LineString.class == currentClass) {
            adapted = factory.createMultiLineString(new LineString[] { (LineString) value });
        } else if (MultiPolygon.class == targetType && Polygon.class == currentClass) {
            adapted = factory.createMultiPolygon(new Polygon[] { (Polygon) value });
        } else {
            throw new IllegalArgumentException("Don't know how to adapt " + currentClass.getName()
                    + " to " + targetType.getName());
        }
        return adapted;
    }

    public Object[] readAll() throws ArrayIndexOutOfBoundsException, IOException {
        int size = schema.getAttributeCount();
        Object[] all = new Object[size];
        for (int i = 0; i < size; i++) {
            all[i] = read(i);
        }
        return all;
    }

    /**
     * 
     */
    public String readFID() throws IOException {
        if (this.currentFid == -1) {
            throw new DataSourceException("The feature id was not fetched");
        }
        this.fidPrefix.setLength(this.fidPrefixLen);
        this.fidPrefix.append(this.currentFid);

        return this.fidPrefix.toString();
    }

    FeatureType getFeatureType() {
        return schema;
    }
}
