/*
 *    GeoTools - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2003-2006, GeoTools Project Managment Committee (PMC)
 *    (C) 2001-2003, The Open Planning Project
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
package com.sampas.socbs.core.data.shapefile.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import org.geotools.data.DataSourceException;
import org.geotools.data.DataStore;

/**
 * This factory is maintained for GeoTools 2.3 code that made use of 
 * IndexedShapefileDataStoreFactory directly.
 * 
 * @deprecated Please use ShapefileDataStoreFactory, it can create an IndexedDataStore if appropriate
 * @author Chris Holmes, TOPP
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/plugin/shapefile/src/main/java/org/geotools/data/shapefile/indexed/IndexedShapefileDataStoreFactory.java $
 * @version $Id: IndexedShapefileDataStoreFactory.java 26202 2007-07-11 18:23:09Z jgarnett $
 */
@SuppressWarnings("unchecked")
public class IndexedShapefileDataStoreFactory extends ShapefileDataStoreFactory {
  
    /**
     * This implementation only tries to make an IndexedShapefileDataStore.
     * 
     * @param params
     *
     *
     * @throws IOException DOCUMENT ME!
     * @throws DataSourceException
     */
	public DataStore createNewShapefile(Map params) throws IOException {
        URL url = null;
        DataStore ds = null;

        try {
            url = (URL) URLP.lookUp(params);

            Boolean idx = (Boolean) CREATE_SPATIAL_INDEX.lookUp(params);

            if (idx == null) {
                idx = Boolean.TRUE;
            }

            URI namespace = (URI) NAMESPACEP.lookUp(params);
            
            Charset dbfCharset = (Charset) DBFCHARSET.lookUp(params);
            if (dbfCharset == null)
                dbfCharset = Charset.forName("ISO-8859-1");

            ds = new IndexedShapefileDataStore(url, namespace,
                    false, idx.booleanValue(), IndexedShapefileDataStore.TREE_QIX, dbfCharset);
        } catch (MalformedURLException mue) {
            throw new DataSourceException("Unable to attatch datastore to "
                + url, mue);
        }

        return ds;
    }

    public String getDisplayName() {
        return "Shapefile (Indexed)";
    }
    
    //    public DataSourceMetadataEnity createMetadata( Map params )
    //            throws IOException {
    //        
    //        URL url = (URL) URLP.lookUp(params);
    //        Boolean mm = (Boolean) MEMORY_MAPPED.lookUp(params);
    //        Boolean idx = (Boolean) CREATE_SPATIAL_INDEX.lookUp(params);
    //        
    //        String server;
    //        String name;
    //        if( url.getProtocol().equals("file")){
    //            server = "localhost";
    //            name = url.getPath();
    //        }
    //        else {
    //            server = url.getHost()+":"+url.getPort();
    //            name = url.getFile();
    //        }
    //        return new DataSourceMetadataEnity( server, name, "Shapefile access for "+url );
    //    }


}
