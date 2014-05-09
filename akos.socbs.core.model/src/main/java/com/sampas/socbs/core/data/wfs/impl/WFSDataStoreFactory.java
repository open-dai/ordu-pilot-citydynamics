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
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.geotools.data.AbstractDataStoreFactory;
import org.geotools.data.DataStore;
import org.xml.sax.SAXException;


/**
 * <p>
 * DOCUMENT ME!
 * </p>
 *
 * @author dzwiers
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/plugin/wfs/src/main/java/org/geotools/data/wfs/WFSDataStoreFactory.java $
 */
@SuppressWarnings("unchecked")
public class WFSDataStoreFactory extends AbstractDataStoreFactory {

    /**
     * url
     */
    public static final Param URL = new Param("WFSDataStoreFactory:GET_CAPABILITIES_URL",
            URL.class,
            "Represents a URL to the getCapabilities document or a server instance.",
            true);
    
    /**
     * boolean
     */
    public static final Param PROTOCOL = new Param("WFSDataStoreFactory:PROTOCOL",
            Boolean.class,
            "Sets a preference for the HTTP protocol to use when requesting WFS functionality. Set this value to Boolean.TRUE for POST, Boolean.FALSE for GET or NULL for AUTO",
            false);
    

    // password stuff -- see java.net.Authentication
    // either both or neither
    /**
     * String
     */
    public static final Param USERNAME = new Param("WFSDataStoreFactory:USERNAME",
            String.class,
            "This allows the user to specify a username. This param should not be used without the PASSWORD param.",
            false);
    /**
     * String
     */
    public static final Param PASSWORD = new Param("WFSDataStoreFactory:PASSWORD",
            String.class,
            "This allows the user to specify a username. This param should not be used without the USERNAME param.",
            false);
    
    /**
     * String
     */
    public static final Param ENCODING = new Param("WFSDataStoreFactory:ENCODING",
            String.class,
            "This allows the user to specify the Encoding of the XML of the XML-Requests sent to the Server.",
            false);

    // timeout -- optional
    /**
     * Integer
     */
    public static final Param TIMEOUT = new Param("WFSDataStoreFactory:TIMEOUT",
            Integer.class,
            "This allows the user to specify a timeout in milliseconds. This param has a default value of 3000ms.",
            false);

    // buffer size -- optional
    /**
     * Integer
     */
    public static final Param BUFFER_SIZE = new Param("WFSDataStoreFactory:BUFFER_SIZE",
            Integer.class,
            "This allows the user to specify a buffer size in features. This param has a default value of 10 features.",
            false);
    // use gzip -- optional
    /**
     * Boolean
     */
    public static final Param TRY_GZIP = new Param("WFSDataStoreFactory:TRY_GZIP",
            Boolean.class,
            "Indicates that datastore should use gzip to transfer data if the server supports it.  Default is true",
            false);
    // be lenient about parsing data -- optional
    /**
     * Boolean
     */
    public static final Param LENIENT = new Param("WFSDataStoreFactory:LENIENT",
            Boolean.class,
            "Indicates that datastore should do its best to create features from the provided data even if it does not accurately match the schema.  Errors will be logged but the parsing will continue if this is true.  Default is false",
            false);
    
    protected Map cache = new HashMap();
    protected static final Logger logger = logger();
    private static Logger logger(){
    	Logger r = org.geotools.util.logging.Logging.getLogger("org.geotools.data.wfs");
    	return r;
    }

    /**
     * @see org.geotools.data.DataStoreFactorySpi#createDataStore(java.util.Map)
     */
    public DataStore createDataStore(Map params) throws IOException {
        // TODO check that we can use hashcodes in this manner -- think it's ok, particularily for regular usage
        if (cache.containsKey(params)) {
            return (DataStore) cache.get(params);
        }

        return createNewDataStore(params);
    }

    /**
     * DOCUMENT ME!
     *
     * @param params DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws IOException
     *
     * @see org.geotools.data.DataStoreFactorySpi#createNewDataStore(java.util.Map)
     */
    public DataStore createNewDataStore(Map params) throws IOException {
        URL host = null;

        if (params.containsKey(URL.key)) {
            host = (URL) URL.lookUp(params);
        }

        Boolean protocol = null;
        if (params.containsKey(PROTOCOL.key)) {
            protocol = (Boolean) PROTOCOL.lookUp(params);
        }
        
        String user, pass;
        user = pass = null;

        int timeout = 3000;
        int buffer = 10;
        boolean tryGZIP=true;
        boolean lenient=false;
        String encoding = null;

        if (params.containsKey(TIMEOUT.key)) {
            Integer i = (Integer) TIMEOUT.lookUp(params);
            if(i!=null)
                timeout = i.intValue();
        }

        if (params.containsKey(BUFFER_SIZE.key)) {
            Integer i = (Integer) BUFFER_SIZE.lookUp(params);
            if(i!=null)
                buffer = i.intValue();
        }

        if (params.containsKey(TRY_GZIP.key)) {
            Boolean b = (Boolean) TRY_GZIP.lookUp(params);
            if(b!=null)
                tryGZIP = b.booleanValue();
        }

        if (params.containsKey(LENIENT.key)) {
            Boolean b = (Boolean) LENIENT.lookUp(params);
            if(b!=null)
                lenient = b.booleanValue();
        }

        if (params.containsKey(USERNAME.key)) {
            user = (String) USERNAME.lookUp(params);
        }

        if (params.containsKey(PASSWORD.key)) {
            pass = (String) PASSWORD.lookUp(params);
        }
        
        if (params.containsKey(ENCODING.key)) {
            encoding = (String) ENCODING.lookUp(params);
        }

        if (((user == null) && (pass != null))
                || ((pass == null) && (user != null))) {
            throw new IOException(
                "Cannot define only one of USERNAME or PASSWORD, muct define both or neither");
        }

        
        
        DataStore ds = null;

        try {
            ds = new WFSDataStore(host, protocol, user, pass, timeout, buffer, tryGZIP, lenient,encoding);
            cache.put(params, ds);
        } catch (SAXException e) {
            logger.warning(e.toString());
            throw new IOException(e.toString());
        }

        return ds;
    }

    /**
     * @see org.geotools.data.DataStoreFactorySpi#getDescription()
     */
    public String getDescription() {
        return "The WFSDataStore represents a connection to a Web Feature Server. This connection provides access to the Features published by the server, and the ability to perform transactions on the server (when supported / allowed).";
    }

    /**
     * @see org.geotools.data.DataStoreFactorySpi#getParametersInfo()
     */
    public Param[] getParametersInfo() {
        return new Param[] {
            URL, PROTOCOL, USERNAME,
            PASSWORD, TIMEOUT, BUFFER_SIZE
        };
    }

    /**
     * @see org.geotools.data.DataStoreFactorySpi#canProcess(java.util.Map)
     */
    public boolean canProcess(Map params) {
        if (params == null) {
            return false;
        }

        // check url
        if (!params.containsKey(URL.key)) {
                return false; // cannot have both
        }

        // check password / username
        if (params.containsKey(USERNAME.key)) {
            if (!params.containsKey(PASSWORD.key)) {
                return false; // must have both
            }
        } else {
            if (params.containsKey(PASSWORD.key)) {
                return false; // must have both
            }
        }

        // check for type
        if (params.containsKey(PROTOCOL.key)) {
            try {
                PROTOCOL.lookUp(params);
            } catch (IOException e) {
                return false;
            }
        }
        
        // check for type
        if (params.containsKey(TIMEOUT.key)) {
            try {
                TIMEOUT.lookUp(params);
            } catch (IOException e) {
                return false;
            }
        }

        if (params.containsKey(BUFFER_SIZE.key)) {
            try {
                BUFFER_SIZE.lookUp(params);
            } catch (IOException e) {
                return false;
            }
        }

        return true;
    }

    /**
     * @see org.geotools.data.DataStoreFactorySpi#getDisplayName()
     */
    public String getDisplayName() {
        return "Web Feature Server";
    }

    /**
     * @see org.geotools.data.DataStoreFactorySpi#isAvailable()
     */
    public boolean isAvailable() {
        return true;
    }
}
