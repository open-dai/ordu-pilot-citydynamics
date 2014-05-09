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
import org.geotools.data.DefaultQuery;
import org.geotools.data.EmptyFeatureReader;
import org.geotools.data.FeatureReader;
import org.geotools.data.FilteringFeatureReader;
import org.geotools.data.Query;
import org.geotools.data.Transaction;
import org.geotools.data.crs.ForceCoordinateSystemFeatureReader;
import org.geotools.data.ows.FeatureSetDescription;
import org.geotools.data.ows.WFSCapabilities;
import org.geotools.feature.SchemaException;
import org.geotools.filter.Filters;
import org.geotools.filter.visitor.PostPreProcessFilterSplittingVisitor.WFSBBoxFilterVisitor;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.filter.Filter;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.xml.sax.SAXException;
import com.vividsolutions.jts.geom.Envelope;

/**
 * A version that is not strict about its filter compliance.  It can be used with geoserver but no other servers.
 * @author Jesse
 *
 */
class NonStrictWFSStrategy implements WFSStrategy {

    protected WFSDataStore store;

    public NonStrictWFSStrategy(WFSDataStore store) {
        this.store=store;
    }

    public FeatureReader getFeatureReader(Query query2, Transaction transaction) throws IOException {
        Query query = new DefaultQuery(query2);
        Filter processedFilter = store.processFilter(query.getFilter());
        // process the filter to update fidfilters using the transaction.
        ((DefaultQuery)query).setFilter(processedFilter);
        Filter serverFilter;
        Filter postFilter;
        {
            Filter[] filters1 = store.splitFilters(query,transaction); // [server][post]
            Filter[] filters = filters1;
            serverFilter = filters[0];
            postFilter = filters[1];
        }
        
        CoordinateReferenceSystem dataCRS = clipBBox(query, serverFilter);
        
        ((DefaultQuery)query).setFilter(serverFilter);
        FeatureReader reader = createFeatureReader(transaction, query);

        if (reader.hasNext()) { // opportunity to throw exception

            if (reader.getFeatureType() != null) {
                reader = wrapWithFilteringFeatureReader(postFilter, reader, processedFilter);
                reader = applyReprojectionDecorator(reader, query, dataCRS);
                return reader;
            }
            throw new IOException(
                "There are features but no feature type ... odd");
        }

        return new EmptyFeatureReader(store.getSchema(query.getTypeName()));
    }

    protected FeatureReader wrapWithFilteringFeatureReader(Filter postFilter, FeatureReader reader, Filter processedFilter) {
        if (!postFilter.equals( Filter.INCLUDE ) ) {
            return new FilteringFeatureReader(reader, postFilter);
        }
        return reader;
    }
    
    protected FeatureReader createFeatureReader(Transaction transaction, Query query) throws IOException {
        Data data;
        data=createFeatureReaderPOST(query, transaction);
        
        if( data.reader==null)
            data=createFeatureReaderGET(query, transaction);
        
        if(data.reader==null && data.saxException!=null)
            throw new IOException(data.saxException.toString());
        if(data.reader==null && data.ioException!=null)
            throw data.ioException;
        
        return data.reader;
    }



    protected Data createFeatureReaderPOST(Query query, Transaction transaction) {
        Data data=new Data();
        if (((store.protocol & WFSDataStore.POST_PROTOCOL) == WFSDataStore.POST_PROTOCOL) ) {
            try {
                data.reader = store.getFeatureReaderPost(query, transaction);
                if(data.reader!=null)
                    data.reader.hasNext(); // throws spot
            } catch (SAXException e) {
                data.reader = null;
//                WFSDataStoreFactory.logger.warning(e.toString());
                data.saxException = e;
            } catch (IOException e) {
                data.reader = null;
//                WFSDataStoreFactory.logger.warning(e.toString());
                data.ioException = e;
            }
        }
        return data;
    }

    protected Data createFeatureReaderGET(Query query, Transaction transaction) {
        Data data=new Data();
        if (((store.protocol & WFSDataStore.GET_PROTOCOL) == WFSDataStore.GET_PROTOCOL) ) {
            try {
                data.reader = store.getFeatureReaderGet(query, transaction);
                if(data.reader!=null)
                    data.reader.hasNext(); // throws spot
            } catch (SAXException e) {
                data.reader = null;
//                WFSDataStoreFactory.logger.warning(e.toString());
                data.saxException = e;
            } catch (IOException e) {
                data.reader = null;
//                WFSDataStoreFactory.logger.warning(e.toString());
                data.ioException = e;
            }
        }
        return data;
    }

    protected FeatureReader applyReprojectionDecorator(FeatureReader reader, Query query, CoordinateReferenceSystem dataCRS) {
        FeatureReader tmp = reader;
        if (query.getCoordinateSystem() !=null &&
            !query.getCoordinateSystem().equals( reader.getFeatureType().getDefaultGeometry().getCoordinateSystem())){
            try {
                reader = new ForceCoordinateSystemFeatureReader(reader,query.getCoordinateSystem());
            } catch (SchemaException e) {
//                WFSDataStoreFactory.logger.warning(e.toString());
                reader = tmp;
            }
        }else{
            if(reader.getFeatureType().getDefaultGeometry()!= null && dataCRS!=null &&
                    reader.getFeatureType().getDefaultGeometry().getCoordinateSystem()== null){
                // set up crs
                try {
                    reader = new ForceCoordinateSystemFeatureReader(reader,dataCRS);
                } catch (SchemaException e) {
//                    WFSDataStoreFactory.logger.warning(e.toString());
                    reader = tmp;
                }
            }
        }
        return reader;
    }
    
    protected CoordinateReferenceSystem clipBBox(Query query, Filter serverFilter) {
        // TODO modify bbox requests here
        FeatureSetDescription fsd = WFSCapabilities.getFeatureSetDescription(store.capabilities,query.getTypeName());
        
        Envelope maxbbox = null;
        CoordinateReferenceSystem dataCRS = null;
        if(fsd.getSRS()!=null){
            // reproject this
            try {
                dataCRS = CRS.decode(fsd.getSRS());
                MathTransform toDataCRS = CRS.findMathTransform( DefaultGeographicCRS.WGS84, dataCRS );
                maxbbox = JTS.transform( fsd.getLatLongBoundingBox(), null, toDataCRS, 10 );                
            } catch (FactoryException e) {
//                WFSDataStoreFactory.logger.warning(e.getMessage());maxbbox = null;
            } catch (MismatchedDimensionException e) {
//                WFSDataStoreFactory.logger.warning(e.getMessage());maxbbox = null;
            } catch (TransformException e) {
//                WFSDataStoreFactory.logger.warning(e.getMessage());maxbbox = null;
            }
        }
        else {
            maxbbox = fsd.getLatLongBoundingBox();
        }
        // Rewrite request if we have a mxxbox
        if(maxbbox!=null){
            WFSBBoxFilterVisitor clipVisitor = new WFSBBoxFilterVisitor(maxbbox);
            Filters.accept( serverFilter, clipVisitor );
        } else { // give up an request everything
//            WFSDataStoreFactory.logger.log( Level.FINE, "Unable to clip your query against the latlongboundingbox element" );
            // filters[0] = Filter.EXCLUDE; // uncoment this line to just give up
        }
        return dataCRS;
    }

    protected class Data{
        IOException ioException;
        SAXException saxException;
        FeatureReader reader;
    }


}
