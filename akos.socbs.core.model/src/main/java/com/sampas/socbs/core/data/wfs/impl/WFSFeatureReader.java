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
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.geotools.data.FeatureReader;
import org.geotools.data.wfs.Action;
import org.geotools.data.wfs.Action.InsertAction;
import org.geotools.data.wfs.Action.UpdateAction;
import org.geotools.feature.Feature;
import org.geotools.xml.DocumentFactory;
import org.geotools.xml.XMLHandlerHints;
import org.geotools.xml.gml.FCBuffer;
import org.xml.sax.SAXException;


/**
 * <p>
 * DOCUMENT ME!
 * </p>
 *
 * @author dzwiers
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/plugin/wfs/src/main/java/org/geotools/data/wfs/WFSFeatureReader.java $
 */
@SuppressWarnings("unchecked")
public class WFSFeatureReader extends FCBuffer {
    private InputStream is = null;
    private WFSTransactionState ts = null;
    private Feature next = null;
    private int insertSearchIndex = -1;

    private WFSFeatureReader(InputStream is, int capacity, int timeout,
        WFSTransactionState trans, WFSFeatureType ft) {
        //document may be null
        super(null, capacity, timeout,ft);
        this.is = is;
        ts = trans;
    }

    /**
     * 
     * @param document
     * @param capacity
     * @param timeout
     * @param transaction
     * @param ft
     * @return WFSFeatureReader
     * @throws SAXException
     */
    public static FeatureReader getFeatureReader(URI document, int capacity,
        int timeout, WFSTransactionState transaction, WFSFeatureType ft) throws SAXException {
        HttpURLConnection hc;

        try {
            hc = (HttpURLConnection) document.toURL().openConnection();

            return getFeatureReader(hc.getInputStream(), capacity, timeout,
                transaction, ft);
        } catch (MalformedURLException e) {
            logger.warning(e.toString());
            throw new SAXException(e);
        } catch (IOException e) {
            logger.warning(e.toString());
            throw new SAXException(e);
        }
    }

    /**
     * 
     * @param is
     * @param capacity
     * @param timeout
     * @param transaction
     * @param ft
     * @return WFSFeatureReader
     * @throws SAXException
     */
    public static WFSFeatureReader getFeatureReader(InputStream is,
        int capacity, int timeout, WFSTransactionState transaction, WFSFeatureType ft)
        throws SAXException {
        WFSFeatureReader fc = new WFSFeatureReader(is, capacity, timeout,
                transaction, ft);
        fc.start(); // calls run

        if (fc.exception != null) {
            throw fc.exception;
        }

        return fc;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    public void run() {
        XMLHandlerHints hints = new XMLHandlerHints();
        initHints(hints);

        try {
            try {
                DocumentFactory.getInstance(is, hints, logger.getLevel());
                is.close();

                // start parsing until buffer part full, then yield();
            } catch (StopException e) {
                exception = e;
                state = STOP;
                is.close();
                yield();
            } catch (SAXException e) {
                exception = e;
                state = STOP;
                is.close();
                yield();
            }  catch (RuntimeException e) {
            	exception = new SAXException(e.getMessage());
            	exception.initCause(e);
                state = STOP;
                is.close();
                yield();
            }
        } catch (IOException e) {
            logger.warning(e.toString());
        }
    }

    protected void initHints( XMLHandlerHints hints ) {
        super.initHints(hints);

        if( ft instanceof WFSFeatureType){
            Map schemas=new HashMap(1);
            WFSFeatureType wfsFT=(WFSFeatureType) ft;
            schemas.put(wfsFT.getNamespace().toString(), wfsFT.getSchemaURI());
            hints.put(XMLHandlerHints.NAMESPACE_MAPPING, schemas);
        }
    }
    
    /**
     * 
     * @see org.geotools.data.FeatureReader#hasNext()
     */
    public boolean hasNext() throws IOException {
        if (next != null) {
            return true;
        }

        try {
            loadElement();
        } catch (NoSuchElementException e) {
            return false;
        }

        return next != null;
    }

    private boolean loadElement()
        throws NoSuchElementException, IOException {
        if (ts == null) {
            while ((next == null) && super.hasNext())
                next = super.next();
        } else {
            List l = ts.getActions(ft.getTypeName());

            if ((insertSearchIndex < l.size()) && (next == null) ) {
                // look for an insert then
                // advance one spot

                while ((insertSearchIndex+1 < l.size()) && (next == null)) {
                    insertSearchIndex++;
                    Action a = (Action) l.get(insertSearchIndex);
                    if (a.getType() == Action.INSERT) {
                        InsertAction ia = (InsertAction) a;
                        next = ia.getFeature();

                        //run thorough the rest to look for deletes / mods
                        int i = insertSearchIndex + 1;

                        while ((i < l.size()) && (next != null)) {
                            a = (Action) l.get(i);
                            next=updateFeature(a, next);
                            i++;
                        }
                    }
                }
            }
            
            while ((next == null) && super.hasNext()) {
                next = super.next();

                if ((ts != null) && (next != null)) {
                    // 	check to make sure it wasn't deleted
                    // 	check for updates
                    Iterator i = l.iterator();

                    while (i.hasNext() && (next != null)) {
                        Action a = (Action) i.next();

                        next=updateFeature(a, next);
                    }
                }
            }

        }

        return next != null;
    }

    private Feature updateFeature( Action a, Feature feature ) {
        if ((a.getType() == Action.DELETE)
                && a.getFilter().evaluate(feature)) {
            return null;
        } else {
            if ((a.getType() == Action.UPDATE)
                    && a.getFilter().evaluate(feature)) {
                // update the feature
                UpdateAction ua = (UpdateAction) a;
                ua.update(feature);
            }
        }
        return feature;
    }

    /**
     * @see org.geotools.data.FeatureReader#next()
     */
    public Feature next()
        throws IOException, NoSuchElementException {
        if (next == null) {
            loadElement(); // load it

            if (next == null) {
                throw new NoSuchElementException();
            }
        }

        Feature r = next;
        next = null;

        return r;
    }

}
