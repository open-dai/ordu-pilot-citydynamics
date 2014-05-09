/*
 *    GeoTools - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002-2006, GeoTools Project Managment Committee (PMC)
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
package com.sampas.socbs.core.data.wfs.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import org.geotools.data.DefaultQuery;
import org.geotools.data.FeatureReader;
import org.geotools.data.FilteringFeatureReader;
import org.geotools.data.Query;
import org.geotools.data.Transaction;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureType;
import org.geotools.feature.IllegalAttributeException;
import org.geotools.filter.FidFilter;
import org.geotools.filter.FilterAttributeExtractor;
import org.geotools.filter.Filters;
import org.geotools.xml.XMLHandlerHints;
import org.geotools.xml.filter.FilterEncodingPreProcessor;
import org.opengis.filter.Filter;


/**
 * A version that is very strict about its filter compliance.
 * @author Jesse
 *
 */
@SuppressWarnings({"unchecked", "deprecation"})
class StrictWFSStrategy extends NonStrictWFSStrategy {

    /**
     * This just splits fid filters from non-fid filters.  The {@link StrictFeatureReader} is what does the rest of the 
     * compliance to high compliance.
     */
    protected static final Integer COMPLIANCE_LEVEL = XMLHandlerHints.VALUE_FILTER_COMPLIANCE_MEDIUM;

    public StrictWFSStrategy(WFSDataStore store) {
        super(store);
    }

    protected FeatureReader wrapWithFilteringFeatureReader(Filter postFilter, FeatureReader reader, Filter processedFilter) {
        FilterEncodingPreProcessor visitor = new FilterEncodingPreProcessor(COMPLIANCE_LEVEL);
        Filters.accept( processedFilter, visitor);
        
        if( visitor.requiresPostProcessing() )
            return new FilteringFeatureReader(reader, processedFilter);
        else
            return new FilteringFeatureReader(reader, postFilter);
            
    }

    protected FeatureReader createFeatureReader(Transaction transaction, Query query) throws IOException {
        return new StrictFeatureReader(transaction, query, 
                COMPLIANCE_LEVEL);
    }

    /**
     * Makes seperate requests between fetching the features using a normal filter and a seperate request for fetching features using
     * the FID filter.
     *  
     * @author Jesse
     */
    protected class StrictFeatureReader implements FeatureReader{

        private FeatureReader delegate;
        protected Filter filter;
        private Query query;
        private Transaction transaction;
        private Set foundFids=new HashSet();
        private Feature next;

        public StrictFeatureReader(Transaction transaction, Query query, Integer level) throws IOException {
            init(transaction, query, level);
        }

        protected void init( Transaction transaction, Query query, Integer level ) throws IOException {
            FilterEncodingPreProcessor visitor = new FilterEncodingPreProcessor(level);
            Filters.accept( query.getFilter(), visitor );
            
            this.transaction=transaction;
            if( visitor.requiresPostProcessing() && query.getPropertyNames()!=Query.ALL_NAMES){
                FilterAttributeExtractor attributeExtractor=new FilterAttributeExtractor();
                query.getFilter().accept( attributeExtractor, null );
                Set properties=new HashSet(attributeExtractor.getAttributeNameSet());
                properties.addAll(Arrays.asList(query.getPropertyNames()));
                this.query=new DefaultQuery(query.getTypeName(), query.getFilter(), query.getMaxFeatures(),
                        (String[]) properties.toArray(new String[0]), query.getHandle());
            }else
                this.query=query;
            
            this.filter=visitor.getFilter();

            DefaultQuery nonFidQuery=new DefaultQuery(query);
            FidFilter fidFilter = visitor.getFidFilter();
            nonFidQuery.setFilter(fidFilter);
            if( fidFilter.getFids().length>0 ){
                delegate = StrictWFSStrategy.super.createFeatureReader(transaction, nonFidQuery);
            }else{
                delegate=nextReader();
            }
        }

        public void close() throws IOException {
            if( delegate!=null )
                delegate.close();
        }

        public FeatureType getFeatureType() {
            return delegate.getFeatureType();
        }

        public boolean hasNext() throws IOException {
            if( next!=null )
                return true;
            
            if( delegate==null )
                return false;
            
            if ( !delegate.hasNext() ){
                delegate.close();
                delegate=null;
                delegate=nextReader();
                if( delegate==null )
                    return false;
            }
            
            try{
                while( next==null ){
                    if( !delegate.hasNext() )
                        return false;
                
                    next=delegate.next();
                    if( this.foundFids.contains(next.getID()) )
                        next=null;
                }
            }catch( IllegalAttributeException e){
                throw new IOException(e.getLocalizedMessage());
            } 
            
            return next!=null;
        }

        private FeatureReader nextReader() throws IOException {
            if( filter==null || filter==Filter.EXCLUDE )
                return null;

            DefaultQuery query2=new DefaultQuery(query);
            query2.setFilter(filter);
            
            FeatureReader nextReader = StrictWFSStrategy.super.createFeatureReader(transaction, query2);

            filter=null;
            return nextReader;
        }


        public Feature next() throws IOException, IllegalAttributeException, NoSuchElementException {
            if( !hasNext() )
                throw new NoSuchElementException();
            
            Feature tmp = next;
            foundFids.add(tmp.getID());
            next=null;
            return tmp;
        }
        
    }

}
