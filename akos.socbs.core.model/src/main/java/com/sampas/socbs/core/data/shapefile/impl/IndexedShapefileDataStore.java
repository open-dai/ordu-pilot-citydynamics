/*
 *    GeoTools - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2003-2006, GeoTools Project Managment Committee (PMC)
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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import org.geotools.data.AbstractAttributeIO;
import org.geotools.data.AbstractFeatureLocking;
import org.geotools.data.AbstractFeatureSource;
import org.geotools.data.AbstractFeatureStore;
import org.geotools.data.AttributeReader;
import org.geotools.data.DataSourceException;
import org.geotools.data.DataStore;
import org.geotools.data.DataUtilities;
import org.geotools.data.EmptyFeatureReader;
import org.geotools.data.FeatureListener;
import org.geotools.data.FeatureReader;
import org.geotools.data.FeatureSource;
import org.geotools.data.FeatureWriter;
import org.geotools.data.InProcessLockingManager;
import org.geotools.data.Query;
import org.geotools.data.Transaction;
import org.geotools.data.TransactionStateDiff;
import org.geotools.feature.AttributeType;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureType;
import org.geotools.feature.FeatureTypes;
import org.geotools.feature.GeometryAttributeType;
import org.geotools.feature.IllegalAttributeException;
import org.geotools.feature.SchemaException;
import org.geotools.feature.type.BasicFeatureTypes;
import org.geotools.filter.FidFilter;
import org.geotools.filter.FilterAttributeExtractor;
import org.geotools.filter.Filters;
import org.opengis.filter.Filter;
import com.sampas.socbs.core.data.shapefile.impl.ShapefileReader.Record;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

/**
 * A DataStore implementation which allows reading and writing from Shapefiles.
 *
 * @author Ian Schneider
 * @author Tommaso Nolli
 *
 * @todo fix file creation bug
 * @source $URL:
 *         http://svn.geotools.org/geotools/branches/constantTimeFid/src/org/geotools/data/shapefile/indexed/IndexedShapefileDataStore.java $
 */
@SuppressWarnings({"unchecked", "deprecation"})
public class IndexedShapefileDataStore extends ShapefileDataStore {
	
	public static final byte TREE_NONE = 0;

	public static final byte TREE_GRX = 1;

	public static final byte TREE_QIX = 2;

    private static final Object FIX_LOCK = new Object();

    private static final Object GRX_LOCK = new Object();

    private static final Object QIX_LOCK = new Object();

	final URL treeURL;

	public URL fixURL;

	byte treeType;

	boolean createIndex;

	final boolean useIndex;

        
	private RTree rtree;

	int maxDepth;

	/**
	 * Creates a new instance of ShapefileDataStore.
	 *
	 * @param url
	 *            The URL of the shp file to use for this DataSource.
	 */
	public IndexedShapefileDataStore(URL url)
			throws java.net.MalformedURLException {
		this(url, null,  false, true, TREE_QIX);
	}

	/**
	 * Creates a new instance of ShapefileDataStore.
	 *
	 * @param url
	 *            The URL of the shp file to use for this DataSource.
	 * @param namespace
	 *            DOCUMENT ME!
	 */
	public IndexedShapefileDataStore(URL url, URI namespace)
			throws java.net.MalformedURLException {
		this(url, namespace, false, true, TREE_QIX );
	}

	/**
	 * Creates a new instance of ShapefileDataStore.
	 *
	 * @param url
	 *            The URL of the shp file to use for this DataSource.
	 * @param namespace
	 *            DOCUMENT ME!
	 * @param useMemoryMappedBuffer
	 *            enable/disable memory mapping of files
	 */
	public IndexedShapefileDataStore(URL url, URI namespace,
			boolean useMemoryMappedBuffer)
			throws java.net.MalformedURLException {
		this(url, namespace, useMemoryMappedBuffer, true, TREE_QIX);
	}

	/**
	 * Creates a new instance of ShapefileDataStore.
	 *
	 * @param url
	 *            The URL of the shp file to use for this DataSource.
	 * @param useMemoryMappedBuffer
	 *            enable/disable memory mapping of files
	 */
	public IndexedShapefileDataStore(URL url, boolean useMemoryMappedBuffer)
			throws java.net.MalformedURLException {
		this(url, (URI) null, useMemoryMappedBuffer, true, TREE_QIX);
	}

	/**
	 * Creates a new instance of ShapefileDataStore.
	 *
	 * @param url
	 *            The URL of the shp file to use for this DataSource.
	 * @param useMemoryMappedBuffer
	 *            enable/disable memory mapping of files
	 * @param createIndex
	 *            enable/disable automatic index creation if needed
	 */
	public IndexedShapefileDataStore(URL url, boolean useMemoryMappedBuffer,
			boolean createIndex) throws java.net.MalformedURLException {
		this(url, null, useMemoryMappedBuffer, createIndex, TREE_QIX);
	}
	
	/**
     * Creates a new instance of ShapefileDataStore.
     *
     * @param url
     *            The URL of the shp file to use for this DataSource.
     * @param namespace
     *            DOCUMENT ME!
     * @param useMemoryMappedBuffer
     *            enable/disable memory mapping of files
     * @param createIndex
     *            enable/disable automatic index creation if needed
     * @param treeType
     *            DOCUMENT ME!
     *
     * @throws NullPointerException
     *             DOCUMENT ME!
     * @throws .
     */
    public IndexedShapefileDataStore(URL url, URI namespace,
            boolean useMemoryMappedBuffer, boolean createIndex, byte treeType) throws MalformedURLException {
        this(url, namespace, useMemoryMappedBuffer, createIndex, treeType, DEFAULT_STRING_CHARSET);
    }

	/**
	 * Creates a new instance of ShapefileDataStore.
	 *
	 * @param url
	 *            The URL of the shp file to use for this DataSource.
	 * @param namespace
	 *            DOCUMENT ME!
	 * @param useMemoryMappedBuffer
	 *            enable/disable memory mapping of files
	 * @param createIndex
	 *            enable/disable automatic index creation if needed
	 * @param treeType
	 *            DOCUMENT ME!
	 * @param dbfCharset {@link Charset} used to decode strings from the DBF
	 *
	 * @throws NullPointerException
	 *             DOCUMENT ME!
	 * @throws .
	 */
	public IndexedShapefileDataStore(URL url, URI namespace,
			boolean useMemoryMappedBuffer, boolean createIndex, byte treeType, Charset dbfCharset)
			throws java.net.MalformedURLException {
		super(url, namespace, true, dbfCharset);
		// test that the shx file can be accessed

		
		this.treeType = treeType;
		this.useMemoryMappedBuffer = new File(shpURL.getFile()).exists()
				&& useMemoryMappedBuffer;
		this.useIndex = treeType != TREE_NONE && isLocal();

		if (this.isLocal()) {
			fixURL = ShapefileDataStoreFactory.toFixURL(url);
			if (treeType == TREE_QIX) {
				treeURL = ShapefileDataStoreFactory.toQixURL(url);
				this.treeType = TREE_QIX;
				LOGGER.fine("Using qix tree");
			} else if (treeType == TREE_GRX) {
				treeURL = ShapefileDataStoreFactory.toGrxURL(url);
				LOGGER.fine("Using grx tree");
			} else {
				treeURL = ShapefileDataStoreFactory.toQixURL(url);
				this.treeType = TREE_NONE;
			}
			this.createIndex = new File(new File(treeURL.getFile()).getParent())
					.canWrite()
					&& createIndex && useIndex;
		} else {
			treeURL = ShapefileDataStoreFactory.toQixURL(url);
			this.treeType = TREE_NONE;
			this.createIndex = false;
			fixURL=null;
		}

	}

	protected void finalize() throws Throwable {
		if (rtree != null) {
			try {
				rtree.close();
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER
						.severe("org.geotools.data.shapefile.indexed.IndexedShapeFileDataStore#finalize(): Error closing rtree. "
								+ e.getLocalizedMessage());
			}
		}
	}

	/**
	 * Determine if the location of this shape is local or remote.
	 *
	 * @return true if local, false if remote
	 */
	public boolean isLocal() {
		return shpURL.getProtocol().equals("file");
	}

	protected Filter getUnsupportedFilter(String typeName, Filter filter) {

		if (filter instanceof FidFilter && fixURL!=null )
			return Filter.INCLUDE;

		return filter;
	}

	public FeatureWriter getFeatureWriterAppend(String typeName, Transaction transaction) throws IOException {
		if (transaction == null) {
            throw new NullPointerException(
                "getFeatureWriter requires Transaction: "
                + "did you mean to use Transaction.AUTO_COMMIT?");
        }

        FeatureWriter writer;

        

		if (transaction == Transaction.AUTO_COMMIT) {
			return super.getFeatureWriterAppend(typeName, transaction);
		} else {
			writer = state(transaction).writer(typeName, Filter.EXCLUDE);
		}
 

        if (getLockingManager() != null) {
            // subclass has not provided locking so we will
            // fake it with InProcess locks
            writer = ((InProcessLockingManager) getLockingManager()).checkedWriter(writer, transaction);
        }

        while(writer.hasNext() )
            writer.next();
        return writer;
	}

    private TransactionStateDiff state(Transaction transaction) {
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

	/**
	 * Use the spatial index if available and adds a small optimization: if no
	 * attributes are going to be read, don't uselessly open and read the dbf
	 * file.
	 *
	 * @see org.geotools.data.AbstractDataStore#getFeatureReader(java.lang.String,
	 *      org.geotools.data.Query)
	 */
	protected FeatureReader getFeatureReader(String typeName, Query query)
			throws IOException {
		if( query.getFilter()==Filter.EXCLUDE )
			return new EmptyFeatureReader(getSchema());

		String[] propertyNames = query.getPropertyNames()==null?new String[0]:query.getPropertyNames();
		String defaultGeomName = schema.getDefaultGeometry().getName();

        FilterAttributeExtractor fae= new FilterAttributeExtractor();
        query.getFilter().accept(fae, null);
        
        Set attributes=new HashSet(Arrays.asList(propertyNames));
        attributes.addAll(fae.getAttributeNameSet());
        
		FeatureType newSchema = schema;
		boolean readDbf = true;
		boolean readGeometry = true;

        propertyNames=(String[]) attributes.toArray(new String[attributes.size()]);
        
		try {
			if (((query.getPropertyNames() != null) && (propertyNames.length == 1) && propertyNames[0]
					.equals(defaultGeomName))) {
				readDbf = false;
				newSchema = DataUtilities.createSubType(schema, propertyNames);
			} else if ((query.getPropertyNames() != null) && (propertyNames.length == 0)) {
				readDbf = false;
				readGeometry = false;
				newSchema = DataUtilities.createSubType(schema, propertyNames);
			}

			return createFeatureReader(typeName, getAttributesReader(readDbf,
					readGeometry, query.getFilter()), newSchema);
		} catch (SchemaException se) {
			throw new DataSourceException("Error creating schema", se);
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param typeName
	 * @param r
	 * @param readerSchema
	 *
	 *
	 * @throws SchemaException
	 * @throws IOException
	 */
	protected FeatureReader createFeatureReader(String typeName, Reader r,
			FeatureType readerSchema) throws SchemaException, IOException {

		if (isLocal() && fixURL!=null ) {
			if (!(new File(fixURL.getFile()).exists()))
				fixURL=FidIndexer.generate(shpURL);

			if( fixURL==null )
				return new org.geotools.data.FIDFeatureReader(r,
						new ShapeFIDReader(getCurrentTypeName(), r), readerSchema);

			return new org.geotools.data.FIDFeatureReader(r,
					new IndexedFidReader(getCurrentTypeName(), r,
							getReadChannel(fixURL)), readerSchema);
		} else {
			return new org.geotools.data.FIDFeatureReader(r,
					new ShapeFIDReader(getCurrentTypeName(), r), readerSchema);
		}
	}

	/**
	 * Returns the attribute reader, allowing for a pure shape reader, or a
	 * combined dbf/shp reader.
	 *
	 * @param readDbf -
	 *            if true, the dbf fill will be opened and read
	 * @param readGeometry
	 *            DOCUMENT ME!
	 * @param filter -
	 *            a Filter to use
	 *
	 *
	 * @throws IOException
	 */
	protected Reader getAttributesReader(boolean readDbf, boolean readGeometry,
			Filter filter) throws IOException {
		Envelope bbox = null;

		Collection goodRecs = null;
		if (filter instanceof FidFilter && fixURL!=null ) {
			FidFilter fidFilter = (FidFilter) filter;
			goodRecs = queryFidIndex(fidFilter.getFids());
		} else {
			if (filter != null) {
				FilterConsumer fc = new FilterConsumer();
				Filters.accept(filter,fc);
				bbox = fc.getBounds();
			}

			if ((bbox != null) && this.useIndex) {
				try {
					goodRecs = this.queryTree(bbox);
				} catch (TreeException e) {
					throw new IOException("Error querying index: "
							+ e.getMessage());
				}
			}
		}

		AttributeType[] atts = (schema == null) ? readAttributes() : schema
				.getAttributeTypes();

		IndexedDbaseFileReader dbfR = null;

		if (!readDbf) {
			LOGGER.fine("The DBF file won't be opened since no attributes "
					+ "will be read from it");
			atts = new AttributeType[] { schema.getDefaultGeometry() };

			if (!readGeometry) {
				atts = new AttributeType[0];
			}
		} else {
			dbfR = (IndexedDbaseFileReader) openDbfReader();
		}

		return new Reader(atts, openShapeReader(), dbfR, goodRecs);
	}

	/**
	 * Uses the Fid index to quickly lookup the shp offset and the record number
	 * for the list of fids
	 *
	 * @param fids
	 *            the fids of the features to find.
	 * @return a list of Data objects
	 * @throws IOException
	 * @throws TreeException
	 */
	private List queryFidIndex(String[] fids) throws IOException {
		Arrays.sort(fids);
		IndexedFidReader reader = null;
		try {
			File indexFile = new File(fixURL.getFile());
            if (isLocal()) {
                synchronized (FIX_LOCK) {
    
                    // remove index file if it is out of date.
                    if( indexFile.exists() && !isIndexed(fixURL) ){
                        if( !indexFile.delete() ){
                            indexFile.deleteOnExit();
                            fixURL=null;
                            return null;
                        }
                    }
    
                    
    				if (!(indexFile.exists())  )
    					FidIndexer.generate(shpURL);
                }
			} else {
				return null;
			}
            if (!(indexFile.exists()) ){
                fixURL=null;
                return null; 
            }
            
			reader = new IndexedFidReader(getCurrentTypeName(),
					getReadChannel(fixURL));
			if (reader.getRemoves() >= reader.getCount() / 2) {
				indexFile.deleteOnExit();
			}

		} catch (Exception e) {
            fixURL=null;
			return null;
		}

		List records = new ArrayList(fids.length);
		try {
			IndexFile shx = openIndexFile(shxURL);
			try {

				DataDefinition def = new DataDefinition("US-ASCII");
				def.addField(Integer.class);
				def.addField(Long.class);
				for (int i = 0; i < fids.length; i++) {
					long recno = reader.findFid(fids[i]);
					if (recno == -1)
						continue;
					try {
						Data data = new Data(def);
						data.addValue(new Integer((int) recno+1));
						data.addValue(new Long(shx
								.getOffsetInBytes((int) recno)));
						records.add(data);
					} catch (Exception e) {
						IOException exception = new IOException();
						exception.initCause(e);
						throw exception;
					}
				}
			} finally {
				shx.close();
			}
		} finally {
			reader.close();
		}

		return records;
	}

    private boolean isIndexed( URL indexURL ) {
        if( !isLocal())
            return false;
        File indexFile = new File(indexURL.getFile());
        File shpFile = new File( shpURL.getPath() );
        return indexFile.exists() && indexFile.lastModified()>=shpFile.lastModified();
    }
    
    /**
     * Returns true if the indices already exist and do not need to be regenerated.
     *
     * @return true if the indices already exist and do not need to be regenerated.
     */
    public boolean isIndexed( ){
        return isIndexed(fixURL) && isIndexed(treeURL);
    }

	/**
	 * Queries the spatial index
	 *
	 * @param bbox
	 *
	 * @return a List of <code>Data</code> objects
	 */
	private Collection queryTree(Envelope bbox) throws DataSourceException,
			IOException, TreeException {
		if (this.treeType == TREE_GRX) {
			return this.queryRTree(bbox);
		} else if (this.treeType == TREE_QIX) {
			return this.queryQuadTree(bbox);
		} else {
			// Should not happen
			return null;
		}
	}

	/**
	 * RTree query
	 *
	 * @param bbox
	 *
	 *
	 * @throws DataSourceException
	 * @throws IOException
	 */
	private List queryRTree(Envelope bbox) throws DataSourceException,
			IOException {
		List goodRecs = null;
		RTree rtree = this.openRTree();

		try {
			if ((rtree != null) && (rtree.getBounds() != null)
					&& !bbox.contains(rtree.getBounds())) {
				goodRecs = rtree.search(bbox);
			}
		} catch (LockTimeoutException le) {
			throw new DataSourceException("Error querying RTree", le);
		} catch (TreeException re) {
			throw new DataSourceException("Error querying RTree", re);
		}

		return goodRecs;
	}

	/**
	 * QuadTree Query
	 *
	 * @param bbox
	 *
	 *
	 * @throws DataSourceException
	 * @throws IOException
	 * @throws TreeException
	 *             DOCUMENT ME!
	 */
	private Collection queryQuadTree(Envelope bbox) throws DataSourceException,
			IOException, TreeException {
        Collection tmp = null;

        try {
            QuadTree quadTree=openQuadTree();
			if ((quadTree != null) && !bbox.contains(quadTree.getRoot().getBounds())) {
                tmp = quadTree.search(bbox);
                
                if( tmp==null || !tmp.isEmpty())
                	return tmp;
			}
			if( quadTree!=null )
				quadTree.close();
        }catch (Exception e) {
			throw new DataSourceException("Error querying QuadTree", e);
		}

    	return null;
	}

	/**
	 * Convenience method for opening a DbaseFileReader.
	 *
	 * @return A new DbaseFileReader
	 *
	 * @throws IOException
	 *             If an error occurs during creation.
	 */
	protected DbaseFileReader openDbfReader() throws IOException {
		ReadableByteChannel rbc = getReadChannel(dbfURL);

		if (rbc == null) {
			return null;
		}

		return new IndexedDbaseFileReader(rbc, false, dbfCharset);
	}

	/**
	 * Convenience method for opening an RTree index.
	 *
	 * @return A new RTree.
	 *
	 * @throws IOException
	 *             If an error occurs during creation.
	 * @throws DataSourceException
	 *             DOCUMENT ME!
	 */
	protected RTree openRTree() throws IOException {
		if (rtree == null) {
			if (!this.isLocal()) {
				return null;
			}

			File treeFile = new File(treeURL.getPath());
            synchronized (GRX_LOCK) {
    
                // remove index file if it is out of date.
                if( treeFile.exists() && !isIndexed(treeURL) ){
                    if( !treeFile.delete() ){
                        treeFile.deleteOnExit();
                        createIndex=false;
                        treeType=TREE_NONE;
                        return null;
                    }
                }
    
    			if (!treeFile.exists() || (treeFile.length() == 0)) {
    				if (this.createIndex) {
    					try {
    						this.buildRTree();
    					} catch (TreeException e) {
    						createIndex=false;
    						return null;
    					}
    				} else {
    					return null;
    				}
    			}
    
    
                if (!treeFile.exists() || (treeFile.length() == 0)) {
                    createIndex=false;
                    treeType=TREE_NONE;
                    return null;
                }
            }
            try {
				FileSystemPageStoreRtree fps = new FileSystemPageStoreRtree(treeFile);
				rtree = new RTree(fps);
			} catch (TreeException re) {
				throw new DataSourceException("Error opening RTree", re);
			}
		}

		return rtree;
	}

	/**
	 * Convenience method for opening a QuadTree index.
	 *
	 * @return A new QuadTree
	 *
	 * @throws StoreException
	 */
	protected QuadTree openQuadTree() throws StoreException {
		QuadTree quadTree=null;
		if (quadTree == null) {
            File treeFile = new File(treeURL.getPath());
            synchronized (QIX_LOCK) {

                // remove index file if it is out of date.
                if( treeFile.exists() && !isIndexed(treeURL) ){
                    if( !treeFile.delete() ){
                        createIndex=false;
                        treeType=TREE_NONE;
                        return null;
                    }
                }
                
    			if (!treeFile.exists() || (treeFile.length() == 0)) {
    				if (this.createIndex) {
    					try {
    						this.buildQuadTree(maxDepth);
    					} catch (Throwable e) {
    						createIndex=false;
                            treeType=TREE_NONE;
    						return null;
    					}
    				} else {
    					return null;
    				}
    			}
    
    
                if (!treeFile.exists() || (treeFile.length() == 0)) {
                    createIndex=false;
                    treeType=TREE_NONE;
                    return null;
                }
            }
            
			FileSystemIndexStore store = new FileSystemIndexStore(treeFile);
			try {
				quadTree = store.load(openIndexFile(shxURL));
			} catch (IOException e) {
				throw new StoreException(e);
			}
		}

		return quadTree;
	}

	/**
	 * Get an array of type names this DataStore holds.<BR/>ShapefileDataStore
	 * will always return a single name.
	 *
	 * @return An array of length one containing the single type held.
	 */
	public String[] getTypeNames() {
		return new String[] { getCurrentTypeName(), };
	}

	/**
	 * Create the type name of the single FeatureType this DataStore represents.<BR/>
	 * For example, if the urls path is file:///home/billy/mytheme.shp, the type
	 * name will be mytheme.
	 *
	 * @return A name based upon the last path component of the url minus the
	 *         extension.
	 */
	protected String createFeatureTypeName() {
		String path = shpURL.getPath();
		int slash = Math.max(0, path.lastIndexOf('/') + 1);
		int dot = path.indexOf('.', slash);

		if (dot < 0) {
			dot = path.length();
		}

		return path.substring(slash, dot);
	}

	protected String getCurrentTypeName() {
		return (schema == null) ? createFeatureTypeName() : schema
				.getTypeName();
	}

	/**
	 * A convenience method to check if a type name is correct.
	 *
	 * @param requested
	 *            The type name requested.
	 *
	 * @throws IOException
	 *             If the type name is not available
	 */
	protected void typeCheck(String requested) throws IOException {
		if (!getCurrentTypeName().equals(requested)) {
			throw new IOException("No such type : " + requested);
		}
	}

	/**
	 * Create a FeatureWriter for the given type name.
	 *
	 * @param typeName
	 *            The typeName of the FeatureType to write
	 * @param transaction
	 *            DOCUMENT ME!
	 *
	 * @return A new FeatureWriter.
	 *
	 * @throws IOException
	 *             If the typeName is not available or some other error occurs.
	 */
	protected FeatureWriter createFeatureWriter(String typeName,
			Transaction transaction) throws IOException {
		typeCheck(typeName);

		return new Writer(typeName);
	}

	/**
	 * Obtain the FeatureType of the given name. ShapefileDataStore contains
	 * only one FeatureType.
	 *
	 * @param typeName
	 *            The name of the FeatureType.
	 *
	 * @return The FeatureType that this DataStore contains.
	 *
	 * @throws IOException
	 *             If a type by the requested name is not present.
	 */
	public FeatureType getSchema(String typeName) throws IOException {
		typeCheck(typeName);

		return getSchema();
	}

	public FeatureType getSchema() throws IOException {
		if (schema == null) {
			try {
				AttributeType[] types = readAttributes();
				FeatureType parent = null;
				Class geomType = types[0].getType();

				if ((geomType == Point.class) || (geomType == MultiPoint.class)) {
					parent = BasicFeatureTypes.POINT;
				} else if ((geomType == Polygon.class)
						|| (geomType == MultiPolygon.class)) {
					parent = BasicFeatureTypes.POLYGON;
				} else if ((geomType == LineString.class)
						|| (geomType == MultiLineString.class)) {
					parent = BasicFeatureTypes.LINE;
				}

				if (parent != null) {
					schema = FeatureTypes.newFeatureType(readAttributes(),
							createFeatureTypeName(), namespace, false,
							new FeatureType[] { parent });
				} else {
					if (namespace != null) {
						schema = FeatureTypes.newFeatureType(readAttributes(),
								createFeatureTypeName(), namespace, false);
					} else {
						schema = FeatureTypes.newFeatureType(readAttributes(),
								createFeatureTypeName(), FeatureTypes.DEFAULT_NAMESPACE,
								false);
					}
				}
			} catch (SchemaException se) {
				throw new DataSourceException("Error creating FeatureType", se);
			}
		}

		return schema;
	}

	/**
     * @see org.geotools.data.AbstractDataStore#getBounds(org.geotools.data.Query)
     */
    protected Envelope getBounds(Query query) throws IOException {
        Envelope ret = null;

        Set records=new HashSet();
        if (query.getFilter() == Filter.INCLUDE || query==Query.ALL) {
            return getBounds();
        } else if (this.useIndex) {
            if (treeType == TREE_GRX) {
                return getBoundsRTree(query);
            }
        }
        
        FidFilterParserVisitor visitor=new FidFilterParserVisitor();
        Filters.accept(query.getFilter(), visitor);
        if( !visitor.fids.isEmpty() ) {
            List recordsFound = queryFidIndex((String[]) visitor.fids.toArray(new String[0]));
            if( recordsFound!=null )
                records.addAll(recordsFound);
        }
        
        if( records.isEmpty() )
            return null;
        
        ShapefileReader reader=new ShapefileReader(getReadChannel(shpURL), this.readWriteLock);
        try{
            ret=new Envelope();
            for (Iterator iter = records.iterator(); iter.hasNext();) {
                Data data = (Data) iter.next();
                reader.goTo(((Long)data.getValue(1)).intValue());
                Record record = reader.nextRecord();
                ret.expandToInclude(new Envelope(record.minX, record.maxX, record.minY, record.maxY));
            }
            return ret;
        }finally{
            reader.close();
        }
    }

    private Envelope getBoundsRTree( Query query ) throws IOException {
        Envelope ret=null;
        
        RTree rtree = this.openRTree();

        if (rtree != null) {
            try {
                ret = rtree.getBounds(query.getFilter());
            } catch (TreeException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            } catch (UnsupportedFilterException e) {
                // Ignoring...
            } finally {
                try {
                    rtree.close();
                } catch (Exception ee) {
                }
            }
        }
        return ret;
    }

        /**
	 * @see org.geotools.data.DataStore#getFeatureSource(java.lang.String)
	 */
	public FeatureSource getFeatureSource(final String typeName)
			throws IOException {
		final FeatureType featureType = getSchema(typeName);

		if (isWriteable) {
			if (getLockingManager() != null) {
				return new AbstractFeatureLocking() {
					public DataStore getDataStore() {
						return IndexedShapefileDataStore.this;
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

					public Envelope getBounds(Query query) throws IOException {
						return IndexedShapefileDataStore.this.getBounds(query);
					}
				};
			} else {
				return new AbstractFeatureStore() {
					public DataStore getDataStore() {
						return IndexedShapefileDataStore.this;
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

					public Envelope getBounds(Query query) throws IOException {
						return IndexedShapefileDataStore.this.getBounds(query);
					}
				};
			}
		} else {
			return new AbstractFeatureSource() {
				public DataStore getDataStore() {
					return IndexedShapefileDataStore.this;
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

				public Envelope getBounds(Query query) throws IOException {
					return IndexedShapefileDataStore.this.getBounds(query);
				}
			};
		}
	}
        
        /**
	 * Builds the RTree index
	 *
	 * @throws TreeException
	 *             DOCUMENT ME!
	 */
	private void buildRTree() throws TreeException {
		if (isLocal()) {
			LOGGER.fine("Creating spatial index for " + shpURL.getPath());

			synchronized (this) {
				if (rtree != null) {
					rtree.close();
				}

				rtree = null;
			}

			ShapeFileIndexer indexer = new ShapeFileIndexer();
			indexer.setIdxType(ShapeFileIndexer.RTREE);
			indexer.setShapeFileName(shpURL.getPath());

			try {
				indexer.index(false, readWriteLock);
			} catch (MalformedURLException e) {
				throw new TreeException(e);
			} catch (LockTimeoutException e) {
				throw new TreeException(e);
			} catch (Exception e) {
				File f = new File(treeURL.getPath());

				if (f.exists()) {
					f.delete();
				}

				if (e instanceof TreeException) {
					throw (TreeException) e;
				} else {
					throw new TreeException(e);
				}
			}
		}
	}

	/**
	 * Builds the QuadTree index.  Usually not necessary since reading features will index when required
	 *
	 * @param maxDepth depth of the tree.  if < 0 then a best guess is made.
	 * @throws TreeException
	 */
	public void buildQuadTree(int maxDepth) throws TreeException {
		if (isLocal()) {
			LOGGER.fine("Creating spatial index for " + shpURL.getPath());

			ShapeFileIndexer indexer = new ShapeFileIndexer();
			indexer.setIdxType(ShapeFileIndexer.QUADTREE);
			indexer.setShapeFileName(shpURL.getPath());
			indexer.setMax(maxDepth);

			try {
				indexer.index(false, readWriteLock);
			} catch (MalformedURLException e) {
				throw new TreeException(e);
			} catch (LockTimeoutException e) {
				throw new TreeException(e);
			} catch (Exception e) {
				File f = new File(treeURL.getPath());

				if (f.exists()) {
					f.delete();
				}

				if (e instanceof TreeException) {
					throw (TreeException) e;
				} else {
					throw new TreeException(e);
				}
			}
		}
	}

	public boolean isMemoryMapped() {
		return useMemoryMappedBuffer;
	}

	/**
	 * An AttributeReader implementation for shape. Pretty straightforward.
	 * <BR/>The default geometry is at position 0, and all dbf columns follow.
	 * <BR/>The dbf file may not be necessary, if not, just pass null as the
	 * DbaseFileReader
	 */
	protected static class Reader extends AbstractAttributeIO implements
			AttributeReader, RecordNumberTracker {

		protected ShapefileReader shp;

		protected IndexedDbaseFileReader dbf;

		protected IndexedDbaseFileReader.Row row;

		protected ShapefileReader.Record record;

		protected Iterator goodRecs;

		private int recno;

		private Data next;

		/**
		 * Create the shape reader
		 *
		 * @param atts -
		 *            the attributes that we are going to read.
		 * @param shp -
		 *            the shape reader, required
		 * @param dbf -
		 *            the dbf file reader. May be null, in this case no
		 *            attributes will be read from the dbf file
		 * @param goodRecs
		 *            DOCUMENT ME!
		 */
		public Reader(AttributeType[] atts, ShapefileReader shp,
				IndexedDbaseFileReader dbf, Collection goodRecs) {
			super(atts);
			this.shp = shp;
			this.dbf = dbf;
			if( goodRecs!=null )
				this.goodRecs = goodRecs.iterator();

			this.recno = 0;
		}

		public void close() throws IOException {
			try {
				if( shp!=null )
					shp.close();

				if (dbf != null) {
					dbf.close();
				}
			} finally {
				row = null;
				record = null;
				shp = null;
				dbf = null;
				goodRecs = null;
			}
		}

		public boolean hasNext() throws IOException {
            if (this.goodRecs != null) {
            	if( next!=null )
            		return true;
                if (this.goodRecs.hasNext()) {
                	
                    next=(Data)goodRecs.next();
                    this.recno = ((Integer) next.getValue(0)).intValue();
                    return true;
                }
                return false;
            }

			int n = shp.hasNext() ? 1 : 0;

			if (dbf != null) {
				n += (dbf.hasNext() ? 2 : 0);
			}

			if ((n == 3) || ((n == 1) && (dbf == null))) {
				return true;
			}

			if (n == 0) {
				return false;
			}

			throw new IOException(((n == 1) ? "Shp" : "Dbf")
					+ " has extra record");
		}

		public void next() throws IOException {
        	if( !hasNext() )
        		throw new IndexOutOfBoundsException("No more features in reader");
			if (this.goodRecs != null) {
				this.recno = ((Integer) next.getValue(0)).intValue();

				if (dbf != null) {
					dbf.goTo(this.recno);
				}

				Long l = (Long) next.getValue(1);
				shp.goTo((int) l.longValue());
				next=null;
			} else {
				this.recno++;
			}

			record = shp.nextRecord();

			if (dbf != null) {
				row = dbf.readRow();
			}
		}

		public int getRecordNumber() {
			return this.recno;
		}

		public Object read(int param) throws IOException,
				java.lang.ArrayIndexOutOfBoundsException {
			switch (param) {
			case 0:
				return record.shape();

			default:

				if (row != null) {
					return row.read(param - 1);
				} else {
					return null;
				}
			}
		}
	}

	/**
	 * A FeatureWriter for ShapefileDataStore. Uses a write and annotate
	 * technique to avoid buffering attributes and geometries. Because the shape
	 * and dbf require header information which can only be obtained by reading
	 * the entire series of Features, the headers are updated after the initial
	 * write completes.
	 */
	protected class Writer implements FeatureWriter {
		// store current time here as flag for temporary write
		private long temp;

		// the FeatureReader to obtain the current Feature from
		protected FeatureReader featureReader;

		// the AttributeReader
		protected Reader attReader;

		// the current Feature
		private Feature currentFeature;

		// the FeatureType we are representing
		private FeatureType featureType;

		// an array for reuse in Feature creation
		private Object[] emptyAtts;

		// an array for reuse in writing to dbf.
		private Object[] transferCache;

		private ShapeType shapeType;

		private ShapeHandler handler;

		// keep track of shape length during write, starts at 100 bytes for
		// required header
		private int shapefileLength = 100;

		// keep track of the number of records written
		private int records = 0;

		// hold 1 if dbf should write the attribute at the index, 0 if not
		private byte[] writeFlags;

		private ShapefileWriter shpWriter;

		private DbaseFileWriter dbfWriter;

		private DbaseFileHeader dbfHeader;

		private FileChannel dbfChannel;

		// keep track of bounds during write
		private Envelope bounds = new Envelope();

		private IndexedFidWriter indexedFidWriter;

		public Writer(String typeName) throws IOException {
			// set up reader
			try {
				temp = System.currentTimeMillis();
				attReader = getAttributesReader(true, true, null);
				featureReader = createFeatureReader(typeName, attReader, schema);
			} catch (Exception e) {
				FeatureType schema = getSchema(typeName);

				if (schema == null) {
					throw new IOException(
							"To create a shape, you must first call createSchema()");
				}

				featureReader = new EmptyFeatureReader(schema);
				temp = 0;
			}

			this.featureType = featureReader.getFeatureType();

			// set up buffers and write flags
			emptyAtts = new Object[featureType.getAttributeCount()];
			writeFlags = new byte[featureType.getAttributeCount()];

			int cnt = 0;

			for (int i = 0, ii = featureType.getAttributeCount(); i < ii; i++) {
				// if its a geometry, we don't want to write it to the dbf...
				if (!(featureType.getAttributeType(i) instanceof GeometryAttributeType)) {
					cnt++;
					writeFlags[i] = (byte) 1;
				}
			}

			// dbf transfer buffer
			transferCache = new Object[cnt];

			// open underlying writers
			shpWriter = new ShapefileWriter(
					(FileChannel) getWriteChannel(getStorageURL(shpURL, temp)),
					(FileChannel) getWriteChannel(getStorageURL(shxURL, temp)),
					readWriteLock);

			dbfChannel = (FileChannel) getWriteChannel(getStorageURL(dbfURL,
					temp));
			dbfHeader = createDbaseHeader();
			dbfWriter = new DbaseFileWriter(dbfHeader, dbfChannel);

			FileChannel fidIndexChannel=(FileChannel) getWriteChannel(getStorageURL(fixURL, temp));

			indexedFidWriter = new IndexedFidWriter(fidIndexChannel,
						new IndexedFidReader(getCurrentTypeName(), temp!=0?getReadChannel(fixURL):fidIndexChannel));

            if(attReader!=null && attReader.hasNext()){
                shapeType=attReader.shp.getHeader().getShapeType();
                handler = shapeType.getShapeHandler();
                shpWriter.writeHeaders(bounds, shapeType, records, shapefileLength);
            }
		}

		/**
		 * Go back and update the headers with the required info.
		 *
		 * @throws IOException
		 *             DOCUMENT ME!
		 */
		protected void flush() throws IOException {
			if ((records <= 0) && (shapeType == null)) {
				GeometryAttributeType geometryAttributeType = featureType
						.getDefaultGeometry();

				Class gat = geometryAttributeType.getType();
				shapeType = JTSUtilities.getShapeType(gat);
			}

			shpWriter.writeHeaders(bounds, shapeType, records, shapefileLength);

			dbfHeader.setNumRecords(records);
			dbfChannel.position(0);
			dbfHeader.writeHeader(dbfChannel);
		}

		/**
		 * Attempt to create a DbaseFileHeader for the FeatureType. Note, we
		 * cannot set the number of records until the write has completed.
		 *
		 * @return DOCUMENT ME!
		 *
		 * @throws IOException
		 *             DOCUMENT ME!
		 * @throws DbaseFileException
		 *             DOCUMENT ME!
		 */
		protected DbaseFileHeader createDbaseHeader() throws IOException,
				DbaseFileException {
			DbaseFileHeader header = new DbaseFileHeader();

			for (int i = 0, ii = featureType.getAttributeCount(); i < ii; i++) {
				AttributeType type = featureType.getAttributeType(i);

				Class colType = type.getType();
				String colName = type.getName();
				int fieldLen = FeatureTypes.getFieldLength(type);

				if (fieldLen <= 0) {
					fieldLen = 255;
				}

				// @todo respect field length
				if ((colType == Integer.class) || (colType == Short.class)
						|| (colType == Byte.class)) {
					header.addColumn(colName, 'N', Math.min(fieldLen, 9), 0);
				} else if (colType == Long.class) {
					header.addColumn(colName, 'N', Math.min(fieldLen, 19), 0);
				} else if ((colType == Double.class)
						|| (colType == Float.class)
						|| (colType == Number.class)) {
					int l = Math.min(fieldLen, 33);
					int d = Math.max(l - 2, 0);
					header.addColumn(colName, 'N', l, d);
				} else if (java.util.Date.class.isAssignableFrom(colType)) {
					header.addColumn(colName, 'D', fieldLen, 0);
				} else if (colType == Boolean.class) {
					header.addColumn(colName, 'L', 1, 0);
				} else if (CharSequence.class.isAssignableFrom(colType)) {
					// Possible fix for GEOT-42 : ArcExplorer doesn't like 0
					// length
					// ensure that maxLength is at least 1
					header.addColumn(colName, 'C', Math.min(254, fieldLen), 0);
				} else if (Geometry.class.isAssignableFrom(colType)) {
					continue;
				} else {
					throw new IOException("Unable to write : "
							+ colType.getName());
				}
			}

			return header;
		}

		/**
		 * In case someone doesn't close me.
		 *
		 * @throws Throwable
		 *             DOCUMENT ME!
		 */
		protected void finalize() throws Throwable {
			if (featureReader != null) {
				try {
					close();
				} catch (Exception e) {
					// oh well, we tried
				}
			}
		}

		/**
		 * Clean up our temporary write if there was one
		 *
		 * @throws IOException
		 *             DOCUMENT ME!
		 */
		protected void clean() throws IOException {
			if (temp == 0) {
				return;
			}

			copyAndDelete(shpURL, temp);
			copyAndDelete(shxURL, temp);
			copyAndDelete(dbfURL, temp);
			if( fixURL!=null )
				copyAndDelete(fixURL, temp);
		}

		/**
		 * Release resources and flush the header information.
		 *
		 * @throws IOException
		 *             DOCUMENT ME!
		 */
		public void close() throws IOException {
			if (featureReader == null) {
				throw new IOException("Writer closed");
			}

			// make sure to write the last feature...
			if (currentFeature != null) {
				write();
			}

			// if the attribute reader is here, that means we may have some
			// additional tail-end file flushing to do if the Writer was closed
			// before the end of the file
			if (attReader != null && attReader.hasNext()) {
				shapeType = attReader.shp.getHeader().getShapeType();
				handler = shapeType.getShapeHandler();

				// handle the case where zero records have been written, but the
				// stream is closed and the headers
				if (records == 0) {
					shpWriter.writeHeaders(bounds, shapeType, 0, 0);
				}

				// copy array for bounds
				double[] env = new double[4];

				while (attReader.hasNext()) {
					// transfer bytes from shape
					shapefileLength += attReader.shp.transferTo(shpWriter,
							++records, env);

					// bounds update
					bounds.expandToInclude(env[0], env[1]);
					bounds.expandToInclude(env[2], env[3]);

					// transfer dbf bytes
					attReader.dbf.transferTo(dbfWriter);
				}
			}

			// close reader, flush headers, and copy temp files, if any
			try {
				featureReader.close();
			} finally {
				try {
					flush();
				} finally {
					shpWriter.close();
					dbfWriter.close();
					dbfChannel.close();
					if( indexedFidWriter!=null )
					indexedFidWriter.close();
				}

				featureReader = null;
				shpWriter = null;
				dbfWriter = null;
				dbfChannel = null;
				indexedFidWriter = null;
				clean();

				/*
				 * TODO This is added here for simplicity... index geometry
				 * during shp record writes
				 */
				try {
					String filename = shpURL.getFile().substring(0,
							shpURL.getFile().length() - 4);
					File file = new File(filename + ".qix");

					if (file.exists()) {
						file.delete();
					}

					file = new File(filename + ".grx");

					if (file.exists()) {
						file.delete();
					}

					if (createIndex) {
						if (treeType == TREE_GRX) {
							buildRTree();
							filename = shpURL.getFile().substring(0,
									shpURL.getFile().length() - 4);

							File toDelete = new File(filename + ".qix");

							if (toDelete.exists()) {
								toDelete.delete();
							}
						} else if (treeType == TREE_QIX) {
							buildQuadTree(maxDepth);
							filename = shpURL.getFile().substring(0,
									shpURL.getFile().length() - 4);

							File otherIndex = new File(filename + ".grx");

							if (otherIndex.exists()) {
								otherIndex.delete();
							}
						}
					}
                } catch (Throwable e) {
                    createIndex=false;
                    treeType=TREE_NONE;
                    LOGGER.log(Level.WARNING, "Error creating RTree", e);
                }					
			}
		}

		public org.geotools.feature.FeatureType getFeatureType() {
			return featureType;
		}

		public boolean hasNext() throws IOException {
			if (featureReader == null) {
				throw new IOException("Writer closed");
			}

			return featureReader.hasNext();
		}

		public org.geotools.feature.Feature next() throws IOException {
			// closed already, error!
			if (featureReader == null) {
				throw new IOException("Writer closed");
			}


			// we have to write the current feature back into the stream
			if (currentFeature != null) {
				write();
			}

			// is there another? If so, return it
			if (featureReader.hasNext()) {
				try {
					if( indexedFidWriter!=null )
						indexedFidWriter.next();
					return currentFeature = featureReader.next();
				} catch (IllegalAttributeException iae) {
					throw new DataSourceException("Error in reading", iae);
				}
			}

			long id;
			if( indexedFidWriter!=null )
				id=indexedFidWriter.next();
			else
				id=this.records+1;
			// reader has no more (no were are adding to the file)
			// so return an empty feature
			try {
				return currentFeature = DataUtilities.template(
						getFeatureType(), getCurrentTypeName()+"."+id, emptyAtts);
			} catch (IllegalAttributeException iae) {
				throw new DataSourceException("Error creating empty Feature",
						iae);
			}
		}

		public void remove() throws IOException {
			if (featureReader == null) {
				throw new IOException("Writer closed");
			}

			if (currentFeature == null) {
				throw new IOException("Current feature is null");
			}
			if( indexedFidWriter!=null ){
				if (indexedFidWriter.isClosed()) {
					throw new IOException("Writer closed");
				}
				indexedFidWriter.remove();
			}

			// mark the current feature as null, this will result in it not
			// being rewritten to the stream
			currentFeature = null;
		}

		public void write() throws IOException {
			if (currentFeature == null) {
				throw new IOException("Current feature is null");
			}

			if (featureReader == null) {
				throw new IOException("Writer closed");
			}

			if( indexedFidWriter!=null ){
				if (indexedFidWriter.isClosed()) {
					throw new IOException("FID Writer closed");
				}
				indexedFidWriter.write();
			}
			// writing of Geometry
			Geometry g = currentFeature.getDefaultGeometry();

			// if this is the first Geometry, find the shapeType and handler
			if (shapeType == null) {
				int dims = JTSUtilities.guessCoorinateDims(g.getCoordinates());

				try {
					shapeType = JTSUtilities.getShapeType(g, dims);

					// we must go back and annotate this after writing
					shpWriter.writeHeaders(new Envelope(), shapeType, 0, 0);
					handler = shapeType.getShapeHandler();
				} catch (ShapefileException se) {
					throw new RuntimeException("Unexpected Error", se);
				}
			}

			// convert geometry
			g = JTSUtilities.convertToCollection(g, shapeType);

			// bounds calculations
			Envelope b = g.getEnvelopeInternal();

			if (!b.isNull()) {
				bounds.expandToInclude(b);
			}

			// file length update
			shapefileLength += (handler.getLength(g) + 8);

			// write it
			shpWriter.writeGeometry(g);

			// writing of attributes
			int idx = 0;

			for (int i = 0, ii = featureType.getAttributeCount(); i < ii; i++) {
				// skip geometries
				if (writeFlags[i] > 0) {
					transferCache[idx++] = currentFeature.getAttribute(i);
				}
			}

			dbfWriter.write(transferCache);

			// one more down...
			records++;

			// clear the currentFeature
			currentFeature = null;
		}
	}
}
