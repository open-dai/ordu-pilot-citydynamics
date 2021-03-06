/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002, Geotools Project Managment Committee (PMC)
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
package com.sampas.socbs.core.data.gml.impl;

import java.io.IOException;
import java.net.URI;

import org.geotools.data.FeatureReader;
import org.geotools.data.Query;
import org.geotools.feature.AttributeType;
import org.geotools.feature.AttributeTypeFactory;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureType;
import org.geotools.feature.FeatureTypeBuilder;
import org.geotools.feature.SchemaException;
import org.geotools.filter.Filter;
import org.geotools.referencing.CRS;
import org.geotools.xml.gml.FCBuffer;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.xml.sax.SAXException;

import com.sampas.socbs.core.data.impl.AFeatureDataProvider;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;

/**
 * 
 * DataStore class for handling a single GML file/stream.
 * 
 * The datastore is "Read Only" as it should only be used
 * to open GML files. 
 * 
 * @author jeichar
 * 
 * TODO Check for a cleaner way to do
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/unsupported/gml/src/main/java/org/geotools/data/gml/FileGMLDataStore.java $
 */


@SuppressWarnings("deprecation")
public class FileGMLDataStore extends AFeatureDataProvider {

	/** URI to a location (doesn't have to be a file) containing GML data*/
	private URI uri;
	private volatile FCBuffer fcbuffer = null;
	/** the number of features that are cached */
	private int bufferSize;
	/** length of time before the datastore gives up. */
	private int timeout;
	private FeatureType schema;

	/**
	 * New instance
	 * 
	 * @param uri URI to a file containing GML data
	 * @param featureBufferSize the number of features that are cached
	 * @param timeout length of time before the datastore gives up.
	 */
	public FileGMLDataStore(URI uri, int featureBufferSize, int timeout){
		super(false); //does not allow writing
		this.uri=uri;
		this.bufferSize=featureBufferSize;
		this.timeout=timeout;
	}
	
    /**
     * Create the type name of the single FeatureType this DataStore
     * represents.<BR/> For example, if the urls path is
     * file:///home/billy/mytheme.shp, the type name will be mytheme.
     *
     * @return A name based upon the last path component of the url minus the
     *         extension.
     */
    protected String createFeatureTypeName() {
        String path = uri.getPath();
        int slash = Math.max(0, path.lastIndexOf('/') + 1);
        int dot = path.indexOf('.', slash);

        if (dot < 0) {
            dot = path.length();
        }

        return path.substring(slash, dot);
    }

	/* Gets the name of all the layers from the filenames
	 * in the directory.
	 * @see org.geotools.data.DataStore#getTypeNames()
	 */
	public String[] getTypeNames() throws IOException {
        return new String[]{(fcbuffer == null) ? createFeatureTypeName() : fcbuffer.getFeatureType().getTypeName()};
	}

	/* Provides access to a FeatureType referenced by a type name.
	 * 
	 * Basically it parses the file using SAX, gets the featureReader
	 * and returns the result of the getFeatureType() method of
	 * the featureReader.
	 * 
	 * @see org.geotools.data.DataStore#getSchema(java.lang.String)
	 */
	public FeatureType getSchema(String typeName) throws IOException {
		if( !getTypeNames()[0].equals(typeName) )
			throw new IOException("Feature type "+typeName+" does not exist.  This datastore only has "+getTypeNames()[0]);
		
		return getSchema();
	}
	
	/* Returns a reference on the featureReader of the current FCBuffer.
	 * 
	 * @see org.geotools.data.AbstractDataStore#getFeatureReader(java.lang.String)
	 */
	protected FeatureReader getFeatureReader(String typeName)
			throws IOException {
		//Q: FCBuffer never closed : memory leak?
		//A: this is not a concern, as the http connection will clean up after itself, 
		//and the buffer is not cached, so it will clean up too (just make sure as a 
		//user you don't cache the reader ...
		
		//XMLSAXHandler.setLogLevel(Level.FINEST);
		//XSISAXHandler.setLogLevel(Level.FINEST);
		//XMLElementHandler.setLogLevel(Level.FINEST);
		//XSIElementHandler.setLogLevel(Level.FINEST);
		
		if( !getTypeNames()[0].equals(typeName) )
			throw new IOException("Feature type "+typeName+" does not exist.  This datastore only has "+getTypeNames()[0]);
		
		try{
			return (FeatureReader)FCBuffer.getFeatureReader(uri,bufferSize,timeout);
		} catch (SAXException sxe){
			sxe.printStackTrace();
			return null;
		}
	}

	public FeatureType getSchema() throws IOException {
		if( fcbuffer==null ){
			synchronized (this) {
				if( fcbuffer==null ){
					try {
						fcbuffer = (FCBuffer)FCBuffer.getFeatureReader(uri, bufferSize, timeout);
						try{
						if( fcbuffer.hasNext() ){
							Feature f=fcbuffer.next();
							try {
								schema=createFinalSchema(f);
							} catch (SchemaException e) {
								LOGGER.warning(e.getLocalizedMessage());
								schema=fcbuffer.getFeatureType();
							}
						}
						}finally{
							fcbuffer.close();
						}
				    } catch (SAXException e) {
				    	throw new IOException(e.toString());
				    }
				}
			}
		}
		
		return schema;
	}
	
	private FeatureType createFinalSchema(Feature f) throws SchemaException {
		FeatureTypeBuilder builder=FeatureTypeBuilder.newInstance(f.getFeatureType().getTypeName());
		for (int i = 0; i < f.getNumberOfAttributes(); i++) {
			AttributeType att = f.getFeatureType().getAttributeType(i);
			if( !Geometry.class.isAssignableFrom(att.getType()) ){
				builder.addType(att);
			}else{
				Geometry geom = (Geometry) f.getAttribute(i);
				Object data = geom.getUserData();
				if( data instanceof CoordinateReferenceSystem ){
					builder.addType(AttributeTypeFactory.newAttributeType(att.getName(), 
							att.getType(), att.isNillable(), att.getRestriction(), 
							att.createDefaultValue(), data));
				}else if( data instanceof String){
					String string=(String) data;
					String[] parts=string.split("#");
					Object crs;
					if( parts[0].equals("http://www.opengis.net/gml/srs/epsg.xml") ){
						try {
							crs=CRS.decode("EPSG:"+parts[1]);
						} catch (NoSuchAuthorityCodeException e) {
							LOGGER.warning(e.getLocalizedMessage());
							crs=null;
						} catch (org.opengis.referencing.FactoryException fe){
							LOGGER.warning(fe.getLocalizedMessage());
							crs=null;
						}
					}else{
						crs=null;
					}
					builder.addType(AttributeTypeFactory.newAttributeType(att.getName(), 
							att.getType(), att.isNillable(), att.getRestriction(), 
							att.createDefaultValue(), crs));
				}
			}
		}
		return builder.getFeatureType();
	}

	protected Envelope getBounds(Query query) throws IOException {
		if( query==Query.ALL || query.getFilter().equals(Filter.INCLUDE) ){
			//TODO parse out bounds
			return null;
		}else{
			// to expensive!
			return null;
		}
	}

}
