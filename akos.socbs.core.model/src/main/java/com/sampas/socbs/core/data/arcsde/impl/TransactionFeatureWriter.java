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
import java.util.NoSuchElementException;
import org.geotools.data.DataSourceException;
import org.geotools.data.FeatureListenerManager;
import org.geotools.data.FeatureReader;
import org.geotools.data.FeatureWriter;
import org.geotools.data.Transaction;
import org.geotools.feature.FeatureType;
import org.geotools.geometry.jts.ReferencedEnvelope;

/**
 * A FeatureWriter aware of transactions.
 * 
 * @author Gabriel Roldan (TOPP)
 * @version $Id: TransactionFeatureWriter.java 29625 2008-03-14 13:55:02Z groldan $
 * @since 2.5
 * @source $URL:
 *         http://svn.geotools.org/geotools/trunk/gt/modules/plugin/arcsde/datastore/src/main/java/org/geotools/arcsde/data/TransactionFeatureWriter.java $
 */
class TransactionFeatureWriter extends ArcSdeFeatureWriter {
    private ArcTransactionState state;

    /**
     * <p>
     * </p>
     * 
     * @param fidReader
     * @param featureType
     * @param filteredContent
     * @param listenerManager
     * @param transactionalConnection the {@link ArcSDEPooledConnection} to work over, with a
     *            {@link ArcSDEPooledConnection#isTransactionActive() transaction active}
     * @param transaction a transaction <b>already configured</b> with the
     *            {@link ArcTransactionState} needed for this writer to work.
     * @throws NoSuchElementException
     * @throws IOException
     */
    public TransactionFeatureWriter(final FIDReader fidReader,
                                    final FeatureType featureType,
                                    final FeatureReader/*<SimpleFeatureType, SimpleFeature>*/ filteredContent,
                                    final ArcTransactionState state,
                                    final FeatureListenerManager listenerManager) throws NoSuchElementException,
                                                                                 IOException {

        super(fidReader, featureType, filteredContent, state.getConnection(), listenerManager,
                state.getVersionHandler());
        this.state = state;
        assert state.getConnection().isTransactionActive();

    }

    /**
     * Overrides createStream so if the table is versioned instead of creating a new state the one
     * being used for the whole transaction is set to the stream object
     */
    // //@Override
    // protected SeStreamOp createStream(Class<? extends SeStreamOp> streamType) throws SeException,
    // DataSourceException {
    // final SeStreamOp streamOp = super.createStream(streamType);
    // final SeState transactionVersionState = state.currentVersionState;
    // if (transactionVersionState != null) {
    // // we're versioned and as inside a transaction we use this single
    // // state for the whole transaction. The state will be trimmed by the
    // // ArcTransactionState at commit time
    // SeObjectId differencesId = new SeObjectId(SeState.SE_NULL_STATE_ID);
    // SeObjectId currentStateId = transactionVersionState.getId();
    // streamOp.setState(currentStateId, differencesId, SeState.SE_STATE_DIFF_NOCHECK);
    // }
    // return streamOp;
    // }
    /**
     * Overrides to not close the connection as it's the transaction responsibility.
     * 
     * @see FeatureWriter#close()
     */
    //@Override
    public void close() throws IOException {
        // we're inside a transaction, so we don't
        // close the connection. Neither filteredContent should do.
        if (filteredContent != null) {
            filteredContent.close();
            filteredContent = null;
        }
    }

    //@Override
    public void write() throws IOException {
        try {
            connection.getLock().acquire();// .lock();
        } catch (InterruptedException e) {
            throw new DataSourceException(e);
        }
        try {
            super.write();
            String typeName = feature.getFeatureType().getTypeName();
            state.addChange(typeName);
        } catch (IOException e) {
            state.getTransaction().rollback();
            throw e;
        } finally {
            connection.getLock().release();//.unlock();
        }
    }

    //@Override
    public void remove() throws IOException {
        try {
            connection.getLock().acquire();// .lock();
        } catch (InterruptedException e) {
            throw new DataSourceException(e);
        }
        try {
            super.remove();
        } catch (IOException e) {
            state.getTransaction().rollback();
            throw e;
        } finally {
            connection.getLock().release();//.unlock();
        }
    }

    //@Override
    protected void doFireFeaturesAdded(String typeName, ReferencedEnvelope bounds) {
        Transaction transaction = state.getTransaction();
        listenerManager.fireFeaturesAdded(typeName, transaction, bounds, false);
    }

    //@Override
    protected void doFireFeaturesChanged(String typeName, ReferencedEnvelope bounds) {
        Transaction transaction = state.getTransaction();
        listenerManager.fireFeaturesChanged(typeName, transaction, bounds, false);
    }

    //@Override
    protected void doFireFeaturesRemoved(String typeName, ReferencedEnvelope bounds) {
        Transaction transaction = state.getTransaction();
        listenerManager.fireFeaturesRemoved(typeName, transaction, bounds, false);
    }

}
