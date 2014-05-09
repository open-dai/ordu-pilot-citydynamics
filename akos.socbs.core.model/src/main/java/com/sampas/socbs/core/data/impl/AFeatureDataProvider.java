package com.sampas.socbs.core.data.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geotools.data.AbstractDataStore;
import org.geotools.data.AbstractFeatureLocking;
import org.geotools.data.AbstractFeatureSource;
import org.geotools.data.AbstractFeatureStore;
import org.geotools.data.DataSourceException;
import org.geotools.data.DataStore;
import org.geotools.data.DataUtilities;
import org.geotools.data.Diff;
import org.geotools.data.DiffFeatureReader;
import org.geotools.data.EmptyFeatureReader;
import org.geotools.data.EmptyFeatureWriter;
import org.geotools.data.FeatureListener;
import org.geotools.data.FeatureListenerManager;
import org.geotools.data.FeatureReader;
import org.geotools.data.FeatureSource;
import org.geotools.data.FeatureWriter;
import org.geotools.data.FilteringFeatureReader;
import org.geotools.data.FilteringFeatureWriter;
import org.geotools.data.InProcessLockingManager;
import org.geotools.data.LockingManager;
import org.geotools.data.MaxFeatureReader;
import org.geotools.data.Query;
import org.geotools.data.ReTypeFeatureReader;
import org.geotools.data.SchemaNotFoundException;
import org.geotools.data.Transaction;
import org.geotools.data.TransactionStateDiff;
import org.geotools.data.view.DefaultView;
import org.geotools.feature.FeatureType;
import org.geotools.feature.SchemaException;
import org.opengis.filter.Filter;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.vividsolutions.jts.geom.Envelope;


public abstract class AFeatureDataProvider extends AbstractDataStore implements IFeatureDataStore, DataStore {

	   protected static final Logger LOGGER = org.geotools.util.logging.Logging.getLogger("org.geotools.data");

	    /** Manages listener lists for FeatureSource implementation */
	    public FeatureListenerManager listenerManager = new FeatureListenerManager();

	    /**
	     * Flags AbstractDataStore to allow Modification.
	     * <p>
	     * GetFeatureSource will return a FeatureStore is this is true.
	     * </p>
	     */
	    protected final boolean isWriteable;

	    /**
	     * Manages InProcess locks for FeatureLocking implementations.
	     *
	     * <p>
	     * May be null if subclass is providing real locking.
	     * </p>
	     */
	    private InProcessLockingManager lockingManager;

	    /** Default (Writeable) DataStore */
	    public AFeatureDataProvider() {
	        this(true);
	    }

	    /**
	     * AbstractDataStore creation.
	     * 
	     * @param isWriteable true for writeable DataStore. 
	     */
	    public AFeatureDataProvider(boolean isWriteable) {
	        this.isWriteable = isWriteable;
	        lockingManager = createLockingManager();
	    }

	    /**
	     * Currently returns an InProcessLockingManager.
	     *
	     * <p>
	     * Subclasses that implement real locking may override this method to
	     * return <code>null</code>.
	     * </p>
	     *
	     * @return InProcessLockingManager or null.
	     */
	    protected InProcessLockingManager createLockingManager() {
	        return new InProcessLockingManager();
	    }

//	    public void fireAdded( Feature newFeature ){
//	        String typeName = newFeature.getFeatureType().getTypeName();
//	        listenerManager.fireFeaturesAdded( typeName, Transaction.AUTO_COMMIT, newFeature.getBounds(), false );
//	    }
//	    public void fireRemoved( Feature removedFeature ){
//	        String typeName = removedFeature.getFeatureType().getTypeName();
//	        listenerManager.fireFeaturesRemoved( typeName, Transaction.AUTO_COMMIT, removedFeature.getBounds(), false );
//	    }
//	    public void fireChanged( Feature before, Feature after ){
//	        String typeName = after.getFeatureType().getTypeName();
//	        Envelope bounds = new Envelope();
//	        bounds.expandToInclude( before.getBounds() );
//	        bounds.expandToInclude( after.getBounds() );
//	        listenerManager.fireFeaturesChanged( typeName, Transaction.AUTO_COMMIT, bounds, false );
//	    }
	           
	    /**
	     * Subclass override to provide access to metadata.
	     * <p>
	     * CreateTypeEntry uses this method to aquire metadata information,
	     * if available.
	     * </p>
	     */
	    @SuppressWarnings("unchecked")
		protected Map createMetadata( String typeName ) {
	        return Collections.EMPTY_MAP;
	    }
	       
	    /** Convience method for retriving all the names from the Catalog Entires */
	    public abstract String[] getTypeNames() throws IOException;

	    /** Retrive schema information for typeName */
	    public abstract FeatureType getSchema(String typeName) throws IOException;

	    /**
	     * Subclass must implement.
	     *
	     * @param typeName
	     *
	     * @return FeatureReader over contents of typeName
	     * 
	     */
	    protected abstract FeatureReader getFeatureReader(String typeName)
	        throws IOException;
	    /**
	     * Subclass can implement this to provide writing support.
	     *
	     * @param typeName
	     *
	     * @return FeatureWriter over contents of typeName
	     * @throws IOException 
	     *
	     * @throws IOException Subclass may throw IOException
	     * @throws UnsupportedOperationException Subclass may implement
	     * @deprecated
	     */
	    protected FeatureWriter getFeatureWriter(String typeName) throws IOException{
	        throw new UnsupportedOperationException("Schema creation not supported");    	
	    }

	    /**
	     * Subclass should implement this to provide writing support.
	     * <p>A feature writer writes to the resource so it should considered to always be committing.
	     * The transaction is passed in so that it can be known what FeatureListeners should be notified of the
	     * changes.  If the Transaction is AUTOCOMMIT then all listeners should be notified.  If not
	     * all listeners that are NOT registered with that transaction should be notified.<p>
	     * @param typeName
	     * @param transaction a feature writer
	     * @return FeatureWriter over contents of typeName
	     * @throws IOException 
	     *
	     * @throws IOException Subclass may throw IOException
	     * @throws UnsupportedOperationException Subclass may implement
	     */
	    protected FeatureWriter createFeatureWriter(String typeName, Transaction transaction)
	    throws IOException {
	        throw new UnsupportedOperationException("Schema creation not supported");    	
	    }
	    /**
	     * Subclass should implement to provide writing support.
	     *
	     * @param featureType Requested FeatureType
	     * @throws IOException 
	     *
	     * @throws IOException Subclass may throw IOException
	     * @throws UnsupportedOperationException Subclass may implement
	     */
	    public void createSchema(FeatureType featureType) throws IOException{
	        throw new UnsupportedOperationException("Schema creation not supported");
	    }
	    /* (non-Javadoc)
	     * @see org.geotools.data.DataStore#updateSchema(java.lang.String, org.geotools.feature.FeatureType)
	     */
	    public void updateSchema(String typeName, FeatureType featureType){
	        throw new UnsupportedOperationException("Schema modification not supported");
	    }

	    // Jody - This is my recomendation for DataStore
	    // in order to support CS reprojection and override
	    public FeatureSource getView(final Query query)
	        throws IOException, SchemaException {
	        return new DefaultView( this.getFeatureSource( query.getTypeName() ), query );
	    }        
	    /**
	     * Default implementation based on getFeatureReader and getFeatureWriter.
	     *
	     * <p>
	     * We should be able to optimize this to only get the RowSet once
	     * </p>
	     *
	     * @see org.geotools.data.DataStore#getFeatureSource(java.lang.String)
	     */
	    public FeatureSource getFeatureSource(final String typeName)
	        throws IOException {
	        final FeatureType featureType = getSchema(typeName);

	        if (isWriteable) {
	            if (lockingManager != null)
	                return new AbstractFeatureLocking(getSupportedHints()) {
	                    public AFeatureDataProvider getDataStore() {
	                        return AFeatureDataProvider.this;
	                    }

	                    public void addFeatureListener(FeatureListener listener) {
	                        listenerManager.addFeatureListener(this, listener);
	                    }

	                    public void removeFeatureListener(
	                        FeatureListener listener) {
	                        listenerManager.removeFeatureListener(this, listener);
	                    }

	                    public FeatureType getSchema() {
	                        return featureType;
	                    }
	                };
	                return new AbstractFeatureStore(getSupportedHints()) {
	                    public AFeatureDataProvider getDataStore() {
	                        return AFeatureDataProvider.this;
	                    }

	                    public void addFeatureListener(FeatureListener listener) {
	                        listenerManager.addFeatureListener(this, listener);
	                    }

	                    public void removeFeatureListener(
	                        FeatureListener listener) {
	                        listenerManager.removeFeatureListener(this, listener);
	                    }

	                    public FeatureType getSchema() {
	                        return featureType;
	                    }
	                };
	        }
	            return new AbstractFeatureSource(getSupportedHints()) {
	                public AFeatureDataProvider getDataStore() {
	                    return AFeatureDataProvider.this;
	                }

	                public void addFeatureListener(FeatureListener listener) {
	                    listenerManager.addFeatureListener(this, listener);
	                }

	                public void removeFeatureListener(FeatureListener listener) {
	                    listenerManager.removeFeatureListener(this, listener);
	                }

	                public FeatureType getSchema() {
	                    return featureType;
	                }
	            };
	    }


	    // Jody - Recomend moving to the following
	    // When we are ready for CoordinateSystem support
	    public FeatureReader getFeatureReader(Query query,Transaction transaction) throws IOException {
	        Filter filter = query.getFilter();
	        String typeName = query.getTypeName();
	        String propertyNames[] = query.getPropertyNames();

	        if (filter == null) {
	            throw new NullPointerException("getFeatureReader requires Filter: "
	                + "did you mean Filter.INCLUDE?");
	        }
	        if( typeName == null ){
	            throw new NullPointerException(
	                "getFeatureReader requires typeName: "
	                + "use getTypeNames() for a list of available types");
	        }
	        if (transaction == null) {
	            throw new NullPointerException(
	                "getFeatureReader requires Transaction: "
	                + "did you mean to use Transaction.AUTO_COMMIT?");
	        }
	        FeatureType featureType = getSchema( query.getTypeName() );

	        if( propertyNames != null || query.getCoordinateSystem()!=null ){
	            try {
	                featureType = DataUtilities.createSubType( featureType, propertyNames, query.getCoordinateSystem() );
	            } catch (SchemaException e) {
	                LOGGER.log( Level.FINEST, e.getMessage(), e);
	                throw new DataSourceException( "Could not create Feature Type for query", e );

	            }
	        }
	        if ( filter == Filter.EXCLUDE || filter.equals( Filter.EXCLUDE )) {
	            return new EmptyFeatureReader(featureType);
	        }
	        //GR: allow subclases to implement as much filtering as they can,
	        //by returning just it's unsupperted filter
	        filter = getUnsupportedFilter(typeName, filter);
	        if(filter == null){
	            throw new NullPointerException("getUnsupportedFilter shouldn't return null. Do you mean Filter.INCLUDE?");
	        }

	        // There are cases where the readers have to lock.  Take shapefile for example.  Getting a Reader causes
	        // the file to be locked.  However on a commit TransactionStateDiff locks before a writer is obtained.  In order to 
	        // prevent deadlocks either the diff has to obtained first or the reader has to be obtained first.
	        // Because shapefile writes to a buffer first the actual write lock is not flipped until the transaction has most of the work
	        // done.  As a result I suggest getting the diff first then getting the reader.
	        // JE
	        Diff diff=null;
	        if (transaction != Transaction.AUTO_COMMIT) {
	            diff = state(transaction).diff(typeName);
	        }
	        
	        // This calls our subclass "simple" implementation
	        // All other functionality will be built as a reader around
	        // this class
	        //
	        FeatureReader reader = getFeatureReader(typeName, query);

	        if( diff!=null )
	            reader = new DiffFeatureReader(reader, diff, query.getFilter());

	        if (!filter.equals( Filter.INCLUDE ) ) {
	            reader = new FilteringFeatureReader(reader, filter);
	        }

	        if (!featureType.equals(reader.getFeatureType())) {
	            LOGGER.fine("Recasting feature type to subtype by using a ReTypeFeatureReader");
	            reader = new ReTypeFeatureReader(reader, featureType, false);
	        }

	        if (query.getMaxFeatures() != Query.DEFAULT_MAX) {
				    reader = new MaxFeatureReader(reader, query.getMaxFeatures());
	        }

	        return reader;
	    }

	    /**
	     * GR: this method is called from inside getFeatureReader(Query ,Transaction )
	     * to allow subclasses return an optimized FeatureReader wich supports the
	     * filter and attributes truncation specified in <code>query</code>
	     * <p>
	     * A subclass that supports the creation of such an optimized FeatureReader
	     * shold override this method. Otherwise, it just returns
	     * <code>getFeatureReader(typeName)</code>
	     * <p>
	     */
	    protected FeatureReader getFeatureReader(String typeName, Query query)
	    throws IOException
	    {
	      return getFeatureReader(typeName);
	    }
	    /**
	     * GR: if a subclass supports filtering, it should override this method
	     * to return the unsupported part of the passed filter, so a
	     * FilteringFeatureReader will be constructed upon it. Otherwise it will
	     * just return the same filter.
	     * <p>
	     * If the complete filter is supported, the subclass must return <code>Filter.INCLUDE</code>
	     * </p>
	     */
	    protected Filter getUnsupportedFilter(String typeName, Filter filter)
	    {
	      return filter;
	    }
	    /*
	     * @see org.geotools.data.DataStore#getFeatureReader(org.geotools.feature.FeatureType,
	     *      org.geotools.filter.Filter, org.geotools.data.Transaction)
	     *
	    public FeatureReader getFeatureReader(FeatureType featureType,
	        Filter filter, Transaction transaction) throws IOException {
	        if (filter == null) {
	            throw new NullPointerException("getFeatureReader requires Filter: "
	                + "did you mean Filter.INCLUDE?");
	        }

	        if (featureType == null) {
	            throw new NullPointerException(
	                "getFeatureReader requires FeatureType: "
	                + "use getSchema( typeName ) to aquire a FeatureType");
	        }

	        if (transaction == null) {
	            throw new NullPointerException(
	                "getFeatureReader requires Transaction: "
	                + "did you mean to use Transaction.AUTO_COMMIT?");
	        }

	        if (filter == Filter.EXCLUDE) {
	            return new EmptyFeatureReader(featureType);
	        }

	        String typeName = featureType.getTypeName();

	        FeatureReader reader = getFeatureReader(typeName);

	        if (filter != Filter.INCLUDE) {
	            reader = new FilteringFeatureReader(reader, filter);
	        }

	        if (transaction != Transaction.AUTO_COMMIT) {
	            Map diff = state(transaction).diff(typeName);
	            reader = new DiffFeatureReader(reader, diff);
	        }

	        if (!featureType.equals(reader.getFeatureType())) {
	            reader = new ReTypeFeatureReader(reader, featureType);
	        }

	        return reader;
	    }
	   */
	    TransactionStateDiff state(Transaction transaction) {
	        synchronized (transaction) {
	            TransactionStateDiff state = (TransactionStateDiff) transaction
	                .getState(this);

	            if (state == null) {
	                state = new TransactionStateDiff(this);
	                transaction.putState(this, state);
	            }

	            return state;
	        }
	    }

	    /* (non-Javadoc)
	     * @see org.geotools.data.DataStore#getFeatureWriter(java.lang.String, org.geotools.filter.Filter, org.geotools.data.Transaction)
	     */
	    public FeatureWriter getFeatureWriter(String typeName, Filter filter,
	        Transaction transaction) throws IOException {
	        if (filter == null) {
	            throw new NullPointerException("getFeatureReader requires Filter: "
	                + "did you mean Filter.INCLUDE?");
	        }

	        if (filter == Filter.EXCLUDE) {
	            FeatureType featureType = getSchema(typeName);

	            return new EmptyFeatureWriter(featureType);
	        }

	        if (transaction == null) {
	            throw new NullPointerException(
	                "getFeatureWriter requires Transaction: "
	                + "did you mean to use Transaction.AUTO_COMMIT?");
	        }

	        FeatureWriter writer;

	        if (transaction == Transaction.AUTO_COMMIT) {
	        	try{
	        		writer = createFeatureWriter(typeName, transaction);
	        	}catch (UnsupportedOperationException e) {
					// This is for backward compatibility.
	        		writer = getFeatureWriter(typeName);
				}
	        } else {
	            writer = state(transaction).writer(typeName, filter);
	        }

	        if (lockingManager != null) {
	            // subclass has not provided locking so we will
	            // fake it with InProcess locks
	            writer = lockingManager.checkedWriter(writer, transaction);
	        }

	        if (filter != Filter.INCLUDE) {
	            writer = new FilteringFeatureWriter(writer, filter);
	        }

	        return writer;
	    }

	    /* (non-Javadoc)
	     * @see org.geotools.data.DataStore#getFeatureWriter(java.lang.String, org.geotools.data.Transaction)
	     */
	    public FeatureWriter getFeatureWriter(String typeName,
	        Transaction transaction) throws IOException {

	    	return getFeatureWriter(typeName, Filter.INCLUDE, transaction);
	    }

	    /* (non-Javadoc)
	     * @see org.geotools.data.DataStore#getFeatureWriterAppend(java.lang.String, org.geotools.data.Transaction)
	     *
	     */
	    public FeatureWriter getFeatureWriterAppend(String typeName,
	        Transaction transaction) throws IOException {
	        FeatureWriter writer = getFeatureWriter(typeName, transaction);

	        while (writer.hasNext()) {
	            writer.next(); // Hmmm this would be a use for skip() then?
	        }

	        return writer;
	    }

	    /**
	     * Locking manager used for this DataStore.
	     *
	     * <p>
	     * By default AbstractDataStore makes use of InProcessLockingManager.
	     * </p>
	     *
	     *
	     * @see org.geotools.data.DataStore#getLockingManager()
	     */
	    public LockingManager getLockingManager() {
	        return lockingManager;
	    }

	    /**
	     * Computes the bounds of the features for the specified feature type that
	     * satisfy the query provided that there is a fast way to get that result.
	     * <p>
	     * Will return null if there is not fast way to compute the bounds. Since
	     * it's based on some kind of header/cached information, it's not guaranteed
	     * to be real bound of the features
	     * </p>
	     * @param query
	     * @return the bounds, or null if too expensive
	     * @throws SchemaNotFoundException 
	     * @throws IOException
	     */
	    protected Envelope getBounds(Query query) throws IOException{
	        return null; // too expensive
	    }


	    /**
	     * Gets the number of the features that would be returned by this query for
	     * the specified feature type.
	     * <p>
	     * If getBounds(Query) returns <code>-1</code> due to expense consider
	     * using <code>getFeatures(Query).getCount()</code> as a an alternative.
	     * </p>
	     *
	     * @param query Contains the Filter and MaxFeatures to find the bounds for.
	     * @return The number of Features provided by the Query or <code>-1</code>
	     *         if count is too expensive to calculate or any errors or occur.
	     * @throws IOException 
	     *
	     * @throws IOException if there are errors getting the count
	     */
	    protected int getCount(Query query) throws IOException{
	        return -1; // too expensive
	    }
	    
	    /**
	     * If you are using the automated FeatureSource/Store/Locking creation, this method
	     * allows for the specification of the supported hints. 
	     * @return
	     */
	    @SuppressWarnings("unchecked")
		protected Set getSupportedHints() {
	        return Collections.EMPTY_SET;
	    }
	    
	    /**
	     * Dummy implementation, it's a no-op. Subclasses holding to system resources must
	     * override this method and release them.
	     */
	    public void dispose() {
	        // nothing to do
	    }
}
