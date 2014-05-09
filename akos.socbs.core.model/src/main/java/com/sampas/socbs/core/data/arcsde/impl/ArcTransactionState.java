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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geotools.data.DataSourceException;
import org.geotools.data.FeatureListenerManager;
import org.geotools.data.Transaction;
import com.esri.sde.sdk.client.SeConnection;
import com.esri.sde.sdk.client.SeException;
import com.esri.sde.sdk.client.SeObjectId;
import com.esri.sde.sdk.client.SeState;
import com.esri.sde.sdk.client.SeVersion;

/**
 * Externalizes transactional state for <code>ArcSDEFeatureWriter</code>
 * instances.
 * 
 * @author Jake Fear
 * @author Gabriel Roldan
 * @source $URL:
 *         http://svn.geotools.org/geotools/trunk/gt/modules/plugin/arcsde/datastore/src/main/java/org/geotools/arcsde/data/ArcTransactionState.java $
 * @version $Id: ArcTransactionState.java 29625 2008-03-14 13:55:02Z groldan $
 */
@SuppressWarnings("unchecked")
final class ArcTransactionState implements Transaction.State {
    private static final Logger LOGGER = org.geotools.util.logging.Logging
            .getLogger(ArcTransactionState.class.getPackage().getName());

    /**
     * Transactional connection this state works upon, held until commit(),
     * rollback() or close() is called.
     */
    private ArcSDEPooledConnection connection;

    private Transaction transaction;

    private final FeatureListenerManager listenerManager;

    /**
     * Set of typename changed to fire changed events for at commit and
     * rollback.
     */
    private final Set/* <String> */typesChanged = new HashSet/* <String> */();

    public SeState currentVersionState;

    public SeObjectId initialStateId;

    public SeVersion defaultVersion;

    private ArcSdeVersionHandler versionHandler = ArcSdeVersionHandler.NONVERSIONED_HANDLER;

    /**
     * Creates a new ArcTransactionState object.
     * 
     * @param listenerManager
     * @param pool
     *            connection pool where to grab a connection and hold it while
     *            there's a transaction open (signaled by any use of
     *            {@link #getConnection()}
     */
    private ArcTransactionState(ArcSDEPooledConnection connection,
            final FeatureListenerManager listenerManager) {
        this.connection = connection;
        this.listenerManager = listenerManager;
    }

    private void setupVersioningHandling() throws IOException {
        // create a versioned handler only if not already settled up, as this
        // method
        // may be called for each layer inside a transaction
        if (versionHandler == ArcSdeVersionHandler.NONVERSIONED_HANDLER) {
            versionHandler = new TransactionDefaultVersionHandler(connection);
        }
    }

    /**
     * @return
     */
    public ArcSdeVersionHandler getVersionHandler() {
        return versionHandler;
    }

    /**
     * Registers a feature change event over a feature type.
     * <p>
     * To be called by {@link TransactionFeatureWriter#write()} so this state
     * can fire a changed event at {@link #commit()} and {@link #rollback()}.
     * </p>
     * 
     * @param typeName
     *            the type name of the feature changed
     *            (inserted/removed/modified).
     */
    public void addChange(final String typeName) {
        typesChanged.add(typeName);
    }

    /**
     * Commits the transaction and returns the connection to the pool. A new one
     * will be grabbed when needed.
     * <p>
     * Preconditions:
     * <ul>
     * <li>{@link #setTransaction(Transaction)} already called with non
     * <code>null</code> argument.
     * <li>
     * </ul>
     * </p>
     */
    public void commit() throws IOException {
        failIfClosed();
        final ArcSDEPooledConnection connection = this.connection;
        try {
            connection.getLock().acquire();// .lock();
        } catch (InterruptedException e) {
            throw new DataSourceException(e);
        }
        try {
            if (this.currentVersionState != null) {
                SeObjectId parentStateId = initialStateId;
                // Change the version's state pointer to the last edit state.
                defaultVersion.changeState(currentVersionState.getId());

                // Trim the state tree.

                currentVersionState.trimTree(parentStateId, currentVersionState.getId());
            }

            connection.commitTransaction();
            versionHandler.commitEditState();
            // and keep editing
            connection.setTransactionAutoCommit(0);
            connection.startTransaction();

            fireChanges(true);
        } catch (SeException se) {
            try {
                connection.rollbackTransaction();
            } catch (SeException e) {
                LOGGER.log(Level.WARNING, new ArcSdeException(se).getMessage(), se);
            }
            // release resources
            close();
            LOGGER.log(Level.WARNING, se.getMessage(), se);
            throw new ArcSdeException(se);
        } finally {
            connection.getLock().release();// .unlock();
        }
    }

    /**
     * 
     */
    public void rollback() throws IOException {
        failIfClosed();
        final ArcSDEPooledConnection connection = this.connection;
        try {
            connection.getLock().acquire();// .lock();
        } catch (InterruptedException e) {
            throw new DataSourceException(e);
        }
        try {
            versionHandler.rollbackEditState();
            connection.rollbackTransaction();
            // and keep editing
            connection.setTransactionAutoCommit(0);
            connection.startTransaction();
            fireChanges(false);
        } catch (SeException se) {
            // release resources
            close();
            LOGGER.log(Level.WARNING, se.getMessage(), se);
            throw new ArcSdeException(se);
        } finally {
            connection.getLock().release();// .unlock();
        }
    }

    /**
     * Fires the per typename changes registered through
     * {@link #addChange(String)} and clears the changes cache.
     */
    private void fireChanges(final boolean commit) {
        for (Iterator it = typesChanged.iterator(); it.hasNext();) {
            String typeName = (String) it.next();
            listenerManager.fireChanged(typeName, transaction, commit);
        }
        typesChanged.clear();
    }

    /**
     * 
     */
    public void addAuthorization(String authId) {
        // intentionally blank
    }

    /**
     * @see Transaction.State#setTransaction(Transaction)
     * @param transaction
     *            transaction information, <code>null</code> signals this
     *            state lifecycle end.
     * @throws IllegalStateException
     *             if close() is called while a transaction is in progress
     */
    public void setTransaction(final Transaction transaction) {
        if (Transaction.AUTO_COMMIT.equals(transaction)) {
            throw new IllegalArgumentException("Cannot use Transaction.AUTO_COMMIT here");
        }
        if (transaction == null) {
            // this is a call to free resources (ugly, but that's what the API
            // says)
            close();
        } else if (this.transaction != null) {
            // assert this assumption
            throw new IllegalStateException(
                    "Once a transaction is set, it is "
                            + "illegal to call Transaction.State.setTransaction with anything other than null: "
                            + transaction);
        }

        this.transaction = transaction;
    }

    /**
     * If this state has been closed throws an unchecked exception as its
     * clearly a broken workflow.
     * 
     * @throws IllegalStateException
     *             if the transaction state has been closed.
     */
    private void failIfClosed() throws IllegalStateException {
        if (connection == null) {
            throw new IllegalStateException("This transaction state has already been closed");
        }
    }

    /**
     * Releases resources and invalidates this state (signaled by setting the
     * connection to null)
     */
    private void close() {
        if (connection == null) {
            return;
        }
        try {
            connection.getLock().acquire();// .lock();
        } catch (InterruptedException e) {
            throw (RuntimeException) new RuntimeException().initCause(e);
        }
        // can't even try to use this state in any way from now on
        // may throw ISE if transaction is still in progress
        try {
            // release current transaction before returning the
            // connection to the pool
            try {
                connection.rollbackTransaction();
                // connection.setConcurrency(SeConnection.SE_UNPROTECTED_POLICY);
            } catch (SeException e) {
                // TODO: this shouldn't happen, but if it does
                // we should somehow invalidate the connection?
                LOGGER.log(Level.SEVERE, "Unexpected exception at close(): "
                        + e.getSeError().getSdeErrMsg(), e);
            }
            try {
                connection.setConcurrency(SeConnection.SE_UNPROTECTED_POLICY);
            } catch (SeException e) {
                LOGGER.log(Level.SEVERE,
                        "Unexpected exception restoring connection to thread unprotected state "
                                + e.getSeError().getSdeErrMsg(), e);
            }
            // now its safe to return it to the pool
            connection.close();
        } catch (IllegalStateException workflowError) {
            // fail fast but put the connection in a healthy state first
            try {
                connection.rollbackTransaction();
            } catch (SeException e) {
                // well, it's totally messed up, just log though
                LOGGER.log(Level.SEVERE, "rolling back connection " + connection, e);
                connection.close();
            }
            throw workflowError;
        } finally {
            connection.getLock().release();// .unlock();
            connection = null;
        }
    }

    /**
     * Used only within the package to provide access to a single connection on
     * which this transaction is being conducted.
     * 
     * @return connection
     * @throws UnavailableArcSDEConnectionException
     * @throws DataSourceException
     * @throws SeException
     */
    ArcSDEPooledConnection getConnection() throws DataSourceException,
            UnavailableArcSDEConnectionException {
        failIfClosed();
        return connection;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * Grab the ArcTransactionState (when not using AUTO_COMMIT).
     * <p>
     * As of GeoTools 2.5 we store the TransactionState using the connection
     * pool as a key.
     * </p>
     * 
     * @param transaction
     *            non autocommit transaction
     * @param listenerManager
     * @param versioned
     * @return the ArcTransactionState stored in the transaction with
     *         <code>connectionPool</code> as key.
     */
    public static ArcTransactionState getState(final Transaction transaction,
            final ArcSDEConnectionPool connectionPool,
            final FeatureListenerManager listenerManager, final boolean versioned)
            throws IOException {
        ArcTransactionState state;

        synchronized (ArcTransactionState.class) {
            state = (ArcTransactionState) transaction.getState(connectionPool);

            if (state == null) {
                // start a transaction
                final ArcSDEPooledConnection connection = connectionPool.getConnection();
                try {
                    // TRY_LOCK: one thread at a time can use the connection
                    connection.setConcurrency(SeConnection.SE_TRYLOCK_POLICY);
                    // do not auto commit
                    connection.setTransactionAutoCommit(0);
                    // and start a transaction
                    connection.startTransaction();
                } catch (SeException e) {
                    try {
                        connection.rollbackTransaction();
                    } catch (SeException ignorableException) {
                        // bah, we're already failing
                    }
                    connection.close();
                    throw new ArcSdeException("Exception initiating transaction on " + connection,
                            e);
                }

                state = new ArcTransactionState(connection, listenerManager);
                transaction.putState(connectionPool, state);
            }
        }

        // if only one of the tables being handled by this transaction state is
        // versioned setHandleVersioned has to be set
        if (versioned) {
            state.setupVersioningHandling();
        }
        return state;
    }
}
