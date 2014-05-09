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

import java.io.IOException;
import org.geotools.data.AbstractFeatureSource;
import org.geotools.data.DataStore;
import org.geotools.data.DefaultFeatureResults;
import org.geotools.data.DefaultQuery;
import org.geotools.data.FeatureListener;
import org.geotools.data.FeatureReader;
import org.geotools.data.Query;
import org.geotools.data.Transaction;
import org.geotools.data.store.EmptyFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureType;
import org.geotools.filter.Filter;
import org.geotools.geometry.jts.ReferencedEnvelope;
import com.vividsolutions.jts.geom.Envelope;


/**
 * DOCUMENT ME!
 *
 * @author dzwiers 
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/plugin/wfs/src/main/java/org/geotools/data/wfs/WFSFeatureSource.java $
 */
@SuppressWarnings("deprecation")
public class WFSFeatureSource extends AbstractFeatureSource {
    protected WFSDataStore ds;
    protected String fname;

    protected WFSFeatureSource(WFSDataStore ds, String fname) {
        this.ds = ds;
        this.fname = fname;
    }

    /**
     * 
     * @see org.geotools.data.FeatureSource#getDataStore()
     */
    public DataStore getDataStore() {
        return ds;
    }

    /**
     * 
     * @see org.geotools.data.FeatureSource#addFeatureListener(org.geotools.data.FeatureListener)
     */
    public void addFeatureListener(FeatureListener listener) {
        ds.listenerManager.addFeatureListener(this, listener);
    }

    /**
     * 
     * @see org.geotools.data.FeatureSource#removeFeatureListener(org.geotools.data.FeatureListener)
     */
    public void removeFeatureListener(FeatureListener listener) {
        ds.listenerManager.removeFeatureListener(this, listener);
    }

    /**
     * 
     * @see org.geotools.data.FeatureSource#getSchema()
     */
    public FeatureType getSchema() {
    	try {
			return ds.getSchema(fname);
		} catch (IOException e) {
			return null;
		}
    }

    /**
     * 
     * @see org.geotools.data.FeatureSource#getBounds()
     */
    public Envelope getBounds() throws IOException {
        return getBounds((fname == null) ? Query.ALL
                                      : new DefaultQuery(fname));
    }

    /**
     * 
     * @see org.geotools.data.FeatureSource#getBounds(org.geotools.data.Query)
     */
    public Envelope getBounds(Query query) throws IOException {
        return ds.getBounds(namedQuery(query));
    }

    /**
     * 
     * @see org.geotools.data.FeatureSource#getFeatures()
     */
    public FeatureCollection getFeatures() throws IOException {
        return getFeatures(new DefaultQuery(getSchema().getTypeName(), Filter.INCLUDE));
    }

    /**
     * 
     * @see org.geotools.data.FeatureSource#getFeatures(org.geotools.filter.Filter)
     */
    public FeatureCollection getFeatures(Filter filter) throws IOException {
        return getFeatures(new DefaultQuery(getSchema().getTypeName(), filter));
    }

    /**
     * 
     * @see org.geotools.data.FeatureSource#getFeatures(org.geotools.data.Query)
     */
    public FeatureCollection getFeatures(Query query) throws IOException  {
        FeatureType schema = getSchema();        
        String typeName = schema.getTypeName();
        
        if( query.getTypeName() == null ){ // typeName unspecified we will "any" use a default
            DefaultQuery defaultQuery = new DefaultQuery(query);
            defaultQuery.setTypeName( typeName );
        }
        
        if( !typeName.equals( query.getTypeName() ) ){
            return new EmptyFeatureCollection( schema );
        }
        else {
            return new DefaultFeatureResults(this, query);    
        }
    }

    /**
     * 
     * @see org.geotools.data.AbstractFeatureSource#getTransaction()
     */
    public Transaction getTransaction() {
        return Transaction.AUTO_COMMIT;
    }

    /**
     * 
     * @author dzwiers
     */
    public static class WFSFeatureResults extends DefaultFeatureResults {
        private WFSFeatureSource fs;
        private Query query;

        /**
         * 
         * @param fs
         * @param query
         */
        public WFSFeatureResults(WFSFeatureSource fs, Query query) throws IOException {
        	super(fs, query);
            this.query = query;
            this.fs = fs;
        }

        /**
         * 
         * @see org.geotools.data.FeatureResults#getSchema()
         */
        public FeatureType getSchema(){
            return fs.getSchema();
        }

        /**
         * 
         * @see org.geotools.data.FeatureResults#reader()
         */
        public FeatureReader reader() throws IOException {
            return fs.ds.getFeatureReader(query, fs.getTransaction());
        }

        /**
         * 
         * @see org.geotools.data.FeatureResults#getBounds()
         */
        public ReferencedEnvelope getBounds(){
            try {
				return ReferencedEnvelope.reference(fs.getBounds(query));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
        }

        /**
         * 
         * @see org.geotools.data.FeatureResults#getCount()
         */
        public int getCount() throws IOException {
            return fs.getCount(query);
        }
    }
}
