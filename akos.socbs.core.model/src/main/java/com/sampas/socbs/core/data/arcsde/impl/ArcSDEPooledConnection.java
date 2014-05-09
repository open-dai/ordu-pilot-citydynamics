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

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Vector; // import java.util.concurrent.locks.Lock;
import java.util.WeakHashMap;
import EDU.oswego.cs.dl.util.concurrent.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.pool.ObjectPool;
import org.geotools.data.DataSourceException;
import com.esri.sde.sdk.client.SeConnection;
import com.esri.sde.sdk.client.SeException;
import com.esri.sde.sdk.client.SeLayer;
import com.esri.sde.sdk.client.SeTable;

/**
 * An SeConnection that returns itself to the connection pool instead of closing
 * on each call to close().
 * 
 * @author Gabriel Roldan (TOPP)
 * @version $Id: ArcSDEPooledConnection.java 30166 2008-05-08 09:29:04Z groldan $
 * @since 2.3.x
 * 
 */
@SuppressWarnings("unchecked")
public class ArcSDEPooledConnection extends SeConnection {

    private static final Logger LOGGER = org.geotools.util.logging.Logging
            .getLogger("org.geotools.arcsde.pool");

    private ReentrantLock lock;

    private ObjectPool pool;

    private ArcSDEConnectionConfig config;

    private static int connectionCounter;

    private int connectionId;

    private boolean transactionInProgress;

    private boolean isPassivated;

    private Map/*<String, SeTables>*/ cachedTables = new WeakHashMap();
    private Map/*<String, SeLayer>*/ cachedLayers = new WeakHashMap();

    public ArcSDEPooledConnection(ObjectPool pool, ArcSDEConnectionConfig config)
            throws SeException {
        super(config.getServerName(), config.getPortNumber().intValue(), config.getDatabaseName(),
                config.getUserName(), config.getUserPassword());
        this.config = config;
        this.pool = pool;
        this.lock = new ReentrantLock();// (false);
        this.setConcurrency(SeConnection.SE_UNPROTECTED_POLICY);
        synchronized (ArcSDEPooledConnection.class) {
            connectionCounter++;
            connectionId = connectionCounter;
        }
    }

    public final ReentrantLock getLock() {
        return lock;
    }

    // @Override
    public final boolean isClosed() {
        return super.isClosed();
    }

    /**
     * Marks the connection as being active (i.e. its out of the pool and ready
     * to be used).
     * <p>
     * Shall be called just before being returned from the connection pool
     * </p>
     * 
     * @see #markInactive()
     * @see #isPassivated
     * @see #checkActive()
     */
    void markActive() {
        this.isPassivated = false;
    }

    /**
     * Marks the connection as being inactive (i.e. laying on the connection
     * pool)
     * <p>
     * Shall be callled just before sending it back to the pool
     * </p>
     * 
     * @see #markActive()
     * @see #isPassivated
     * @see #checkActive()
     */
    void markInactive() {
        this.isPassivated = true;
    }

    /**
     * Returns whether this connection is on the connection pool domain or not.
     * 
     * @return <code>true</code> if this connection has beed returned to the
     *         pool and thus cannot be used, <code>false</code> if its safe to
     *         keep using it.
     */
    public boolean isPassivated() {
        return isPassivated;
    }

    /**
     * Sanity check method called before every public operation delegates to the
     * superclass.
     * 
     * @throws IllegalStateException
     *             if {@link #isPassivated() isPassivated() == true} as this is
     *             a serious workflow breackage.
     */
    private void checkActive() {
        if (isPassivated()) {
            throw new IllegalStateException("Unrecoverable error: " + toString()
                    + " is passivated, shall not be used!");
        }
    }

    public synchronized SeLayer getLayer(final String layerName) throws DataSourceException {
        checkActive();
        if (!cachedLayers.containsKey(layerName)) {
            try {
                cacheLayers();
            } catch (SeException e) {
                throw new DataSourceException("Can't obtain layer " + layerName, e);
            }
        }
        SeLayer seLayer = (SeLayer) cachedLayers.get(layerName);
        if (seLayer == null) {
            throw new NoSuchElementException("Layer '" + layerName + "' not found");
        }
        return seLayer;
    }

    public synchronized SeTable getTable(final String tableName) throws DataSourceException {
        checkActive();
        if (!cachedTables.containsKey(tableName)) {
            try {
                cacheLayers();
            } catch (SeException e) {
                throw new DataSourceException("Can't obtain table " + tableName, e);
            }
        }
        SeTable seLayer = (SeTable) cachedTables.get(tableName);
        if (seLayer == null) {
            throw new NoSuchElementException("Table '" + tableName + "' not found");
        }
        return seLayer;
    }

    /**
     * Caches both tables and layers
     * @throws SeException
     */
    // @SuppressWarnings("unchecked")
    private void cacheLayers() throws SeException {
        Vector/* <SeLayer> */layers = this.getLayers();
        String qualifiedName;
        SeLayer layer;
        SeTable table;
        cachedTables.clear();
        cachedLayers.clear();
        for (Iterator it = layers.iterator(); it.hasNext();) {
            layer = (SeLayer) it.next();
            qualifiedName = layer.getQualifiedName();
            table = new SeTable((SeConnection)this, qualifiedName);
            cachedLayers.put(qualifiedName, layer);
            cachedTables.put(qualifiedName, table);
        }
    }

    // @Override
    public void startTransaction() throws SeException {
        checkActive();
        super.startTransaction();
        transactionInProgress = true;
    }

    // @Override
    public void commitTransaction() throws SeException {
        checkActive();
        super.commitTransaction();
        transactionInProgress = false;
    }

    /**
     * Returns whether a transaction is in progress over this connection
     * <p>
     * As for any other public method, this one can't be called if
     * {@link #isPassivated()} is true.
     * </p>
     * 
     * @return
     */
    public boolean isTransactionActive() {
        checkActive();
        return transactionInProgress;
    }

    // @Override
    public void rollbackTransaction() throws SeException {
        checkActive();
        super.rollbackTransaction();
        transactionInProgress = false;
    }

    /**
     * Doesn't close the connection, but returns itself to the connection pool.
     * 
     * @throws IllegalStateException
     *             if close() is called while a transaction is in progress
     * @see #destroy()
     */
    // @Override
    public void close() throws IllegalStateException {
        checkActive();
        if (transactionInProgress) {
            throw new IllegalStateException(
                    "Transaction is in progress, should commit or rollback before closing");
        }

        try {
            if (LOGGER.isLoggable(Level.FINER)) {
                // StackTraceElement[] stackTrace =
                // Thread.currentThread().getStackTrace();
                // String caller = stackTrace[3].getClassName() + "." +
                // stackTrace[3].getMethodName();
                // System.err.println("<- " + caller + " returning " +
                // toString() + " to pool");

                LOGGER.finer("<- returning " + toString() + " to pool");
            }
            this.pool.returnObject(this);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    // @Override
    public String toString() {
        return "ArcSDEPooledConnection[" + connectionId + "]";
    }

    /**
     * Actually closes the connection
     */
    void destroy() {
        try {
            super.close();
        } catch (SeException e) {
            LOGGER.info("closing connection: " + e.getMessage());
        }
    }

    /**
     * Compares for reference equality
     */
    // @Override
    public boolean equals(Object other) {
        return other == this;
    }

    // @Override
    public int hashCode() {
        return 17 ^ this.config.hashCode();
    }

}
