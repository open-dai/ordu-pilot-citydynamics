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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.geotools.data.DataUtilities;
import org.geotools.data.FeatureReader;
import org.geotools.data.FeatureStore;
import org.geotools.data.Transaction;
import org.geotools.data.wfs.Action.DeleteAction;
import org.geotools.data.wfs.Action.InsertAction;
import org.geotools.data.wfs.Action.UpdateAction;
import org.geotools.feature.AttributeType;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.GeometryAttributeType;
import org.geotools.feature.IllegalAttributeException;
import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;


/**
 * DOCUMENT ME!
 *
 * @author dzwiers 
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/plugin/wfs/src/main/java/org/geotools/data/wfs/WFSFeatureStore.java $
 */
@SuppressWarnings({"unchecked", "deprecation"})
public class WFSFeatureStore extends WFSFeatureSource implements FeatureStore {
    
	protected Transaction trans = Transaction.AUTO_COMMIT;

    /**
     * 
     * @param ds
     * @param typeName
     */
    public WFSFeatureStore(WFSDataStore ds, String typeName) {
        super(ds, typeName);
    }

    /**
     * 
     * @see org.geotools.data.AbstractFeatureSource#getTransaction()
     */
    public Transaction getTransaction() {
        return trans;
    }

    public Set addFeatures(final FeatureReader reader) throws IOException {
        List features=new ArrayList();
        while(reader.hasNext()){
            try {
                Feature next = reader.next();
                features.add(next);
            } catch (Exception e) {
                throw (IOException) new IOException( ).initCause( e );
            }
        }
        return addFeatures(DataUtilities.collection((Feature[]) features.toArray(new Feature[0])));
    }

	public Set addFeatures(FeatureCollection collection) throws IOException {
        WFSTransactionState ts = null;

        if (trans == Transaction.AUTO_COMMIT) {
            ts = new WFSTransactionState(ds);
        } else {
            ts = (WFSTransactionState) trans.getState(ds);
        }

        HashSet r = new HashSet();
        
        AttributeType[] atrs = getSchema().getAttributeTypes();
        FeatureIterator iter=collection.features();
        try{
            Envelope bounds=null;
        while (iter.hasNext()){
            try {
                Feature newFeature;
                try {
                        Feature f = iter.next();
                        newFeature = getSchema().create( f.getAttributes(new Object[getSchema().getAttributeCount()]), ts.nextFid(getSchema().getTypeName()));
                        r.add(newFeature.getID());
                } catch (IllegalAttributeException e) {
                    throw (IOException) new IOException( e.getLocalizedMessage() );
                }

                for(int i=0;i<atrs.length;i++){
                    if(atrs[i] instanceof GeometryAttributeType){
                        Geometry g = (Geometry)newFeature.getAttribute(i);
                        CoordinateReferenceSystem cs = ((GeometryAttributeType)atrs[i]).getCoordinateSystem();
                        if( g==null )
                            continue;
                        if( cs!=null && !cs.getIdentifiers().isEmpty() )
                            g.setUserData(cs.getIdentifiers().iterator().next().toString());
                        if( bounds==null ){
                            bounds=new Envelope(g.getEnvelopeInternal());
                        }else{
                            bounds.expandToInclude(g.getEnvelopeInternal());
                        }
                    }
                }
                
                
                ts.addAction(getSchema().getTypeName(), new InsertAction(newFeature));

            } catch (NoSuchElementException e) {
                WFSDataStoreFactory.logger.warning(e.toString());
                throw new IOException(e.toString());
            }
        }

        // Fire a notification.
        // JE
        if( bounds==null){
            // if bounds are null then send an envelope to say that features were added but
            // at an unknown location.
            ((WFSDataStore)getDataStore()).listenerManager.fireFeaturesRemoved(getSchema().getTypeName(),
                    getTransaction(), new Envelope(), false);
        }else{
            ((WFSDataStore)getDataStore()).listenerManager.fireFeaturesRemoved(getSchema().getTypeName(),
                    getTransaction(), bounds, false);                   
        }

        }finally{ 
            iter.close();
        }
        if (trans == Transaction.AUTO_COMMIT) {
            ts.commit();

            String[] fids = ts.getFids(getSchema().getTypeName());
            r = new HashSet(Arrays.asList(fids));

            return r;
        }

        return r;
	}

	/**
     * 
     * @see org.geotools.data.FeatureStore#removeFeatures(org.geotools.filter.Filter)
     */
    public void removeFeatures(Filter filter2) throws IOException {
    	Filter filter=ds.processFilter(filter2);
        WFSTransactionState ts = null;

        if (trans == Transaction.AUTO_COMMIT) {
            ts = new WFSTransactionState(ds);
        } else {
            ts = (WFSTransactionState) trans.getState(ds);
        }

        ts.addAction(getSchema().getTypeName(), new DeleteAction(getSchema().getTypeName(), filter));
        
        // Fire a notification.  I don't know a way of quickly getting the bounds of
        // an arbitrary filter so I'm sending a NULL envelope to say "some features were removed but I don't
        // know what."  Can't be null because the convention states that null is sent on commits only.
        // JE
        ((WFSDataStore)getDataStore()).listenerManager.fireFeaturesRemoved(getSchema().getTypeName(),
        		getTransaction(), new Envelope(), false);

        if (trans == Transaction.AUTO_COMMIT) {
            ts.commit();
        }
    }
    /**
     * 
     * @see org.geotools.data.FeatureStore#modifyFeatures(org.geotools.feature.AttributeType[], java.lang.Object[], org.geotools.filter.Filter)
     */
    public void modifyFeatures(AttributeType[] type, Object[] value,
        Filter filter2) throws IOException {
    	Filter filter=ds.processFilter(filter2);
        WFSTransactionState ts = null;

        if (trans == Transaction.AUTO_COMMIT) {
            ts = new WFSTransactionState(ds);
        } else {
            ts = (WFSTransactionState) trans.getState(ds);
        }

        Map props = new HashMap();
        
        Envelope bounds=null;
        for (int i = 0; i < type.length; i++) {
        	if(type[i] instanceof GeometryAttributeType){
        		Geometry g = (Geometry)value[i];
        		CoordinateReferenceSystem cs = ((GeometryAttributeType)type[i]).getCoordinateSystem();

                if( cs!=null && !cs.getIdentifiers().isEmpty() )
                    g.setUserData(cs.getIdentifiers().iterator().next().toString());
        		g.setUserData(cs.getIdentifiers().iterator().next().toString());
                if( cs!=null && !cs.getIdentifiers().isEmpty() )
                    g.setUserData(cs.getIdentifiers().iterator().next().toString());
                // set/expand the bounds that are being changed.
                if( g==null )
                	continue;
                if( bounds==null ){
                    bounds=new Envelope(g.getEnvelopeInternal());
                }else{
                    bounds.expandToInclude(g.getEnvelopeInternal());
                }
        	}
            props.put(type[i].getName(), value[i]);
        }

        ts.addAction(getSchema().getTypeName(), new UpdateAction(getSchema().getTypeName(), filter, props));

        // Fire a notification.
        // JE
        if( bounds==null ){
            // if bounds are null then send an envelope to say that features were modified but
            // at an unknown location.
            ((WFSDataStore)getDataStore()).listenerManager.fireFeaturesRemoved(getSchema().getTypeName(),
                    getTransaction(), new Envelope(), false);
        }else{
            ((WFSDataStore)getDataStore()).listenerManager.fireFeaturesRemoved(getSchema().getTypeName(),
                    getTransaction(), bounds, false);                   
        }    
        
        if (trans == Transaction.AUTO_COMMIT) {
            ts.commit();
        }
    }
    /**
     * 
     * @see org.geotools.data.FeatureStore#modifyFeatures(org.geotools.feature.AttributeType, java.lang.Object, org.geotools.filter.Filter)
     */
    public void modifyFeatures(AttributeType type, Object value, Filter filter)
        throws IOException {
        modifyFeatures(new AttributeType[] { type, }, new Object[] { value, },
            filter);
    }

    /**
     * 
     * @see org.geotools.data.FeatureStore#setFeatures(org.geotools.data.FeatureReader)
     */
    public void setFeatures(FeatureReader reader) throws IOException {
        WFSTransactionState ts = null;

        if (trans == Transaction.AUTO_COMMIT) {
            ts = new WFSTransactionState(ds);
        } else {
            ts = (WFSTransactionState) trans.getState(ds);
        }

        ts.addAction(getSchema().getTypeName(), new DeleteAction(getSchema().getTypeName(), Filter.INCLUDE));
        
        Envelope bounds=null;
        while (reader.hasNext()){

            try {
            	Feature f = reader.next();
            	AttributeType[] atrs = f.getFeatureType().getAttributeTypes();
            	for(int i=0;i<atrs.length;i++){
            		if(atrs[i] instanceof GeometryAttributeType){
            			Geometry g = (Geometry)f.getAttribute(i);
                		CoordinateReferenceSystem cs = ((GeometryAttributeType)atrs[i]).getCoordinateSystem();
                        if( cs!=null && !cs.getIdentifiers().isEmpty() )
                            g.setUserData(cs.getIdentifiers().iterator().next().toString());
                        if( g==null )
                        	continue;
                        if( bounds==null ){
                            bounds=new Envelope(g.getEnvelopeInternal());
                        }else{
                            bounds.expandToInclude(g.getEnvelopeInternal());
                        }
            		}
            	}
                ts.addAction(getSchema().getTypeName(), new InsertAction(f));
            } catch (NoSuchElementException e) {
                WFSDataStoreFactory.logger.warning(e.toString());
            } catch (IllegalAttributeException e) {
                WFSDataStoreFactory.logger.warning(e.toString());
            }
        }
            
        // Fire a notification.
        // JE
        if( bounds==null){
            // if bounds are null then send an envelope to say that features were added but
            // at an unknown location.
            ((WFSDataStore)getDataStore()).listenerManager.fireFeaturesRemoved(getSchema().getTypeName(),
                    getTransaction(), new Envelope(), false);
        }else{
            ((WFSDataStore)getDataStore()).listenerManager.fireFeaturesRemoved(getSchema().getTypeName(),
                    getTransaction(), bounds, false);                   
        }    
        if (trans == Transaction.AUTO_COMMIT) {
            ts.commit();
        }
    }

    /**
     * 
     * @see org.geotools.data.FeatureStore#setTransaction(org.geotools.data.Transaction)
     */
    public void setTransaction(Transaction transaction) {
        if(transaction == null)
            throw new NullPointerException("Should this not be Transaction.AutoCommit?");
        trans = transaction;
        if(trans != Transaction.AUTO_COMMIT){
        	WFSTransactionState ts = (WFSTransactionState) trans.getState(ds);
        	if(ts == null){
        		trans.putState(ds,new WFSTransactionState(ds));
        	}
        }
    }
}
