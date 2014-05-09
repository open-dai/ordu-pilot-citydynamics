/*
 *    GeoTools - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2003-2006, GeoTools Project Managment Committee (PMC)
 *    (C) 2002, Centre for Computational Geography
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.channels.FileChannel;
import java.util.Hashtable;
import java.util.logging.Logger;

import com.sampas.socbs.core.data.shapefile.impl.ShapefileReader.Record;
import com.vividsolutions.jts.geom.Envelope;

/**
 * Utility class for Shapefile spatial indexing
 *
 * @author Tommaso Nolli
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/plugin/shapefile/src/main/java/org/geotools/data/shapefile/indexed/ShapeFileIndexer.java $
 */
@SuppressWarnings("unchecked")
public class ShapeFileIndexer {
    public static final String RTREE = "RTREE";
    public static final String QUADTREE = "QUADTREE";
    private static final Logger LOGGER = org.geotools.util.logging.Logging.getLogger(
            "org.geotools.data.shapefile");

    /**
     * Minimum time a thread will wait for index build by another thread by
     * default this is 2 seconds
     */
    private static final long MIN_WAIT_TIME = 2000;

    /**
     * Maximum time a thread will wait for index build by another thread by
     * default this is 5 minutes (is it too much?)
     */
    private static final long MAX_WAIT_TIME = 5 * 60 * 1000;
    private static Hashtable IDX_CREATION = new Hashtable();
    private String idxType;
    private int max = 50;
    private int min = 25;
    private short split = PageStore.SPLIT_QUADRATIC;
    private String fileName;
    private String byteOrder;

    public static void main(String[] args) {
        if ((args.length < 1) || (((args.length - 1) % 2) != 0)) {
            usage();
        }

        long start = System.currentTimeMillis();

        ShapeFileIndexer idx = new ShapeFileIndexer();

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-t")) {
                idx.setIdxType(args[++i]);
            } else if (args[i].equals("-M")) {
                idx.setMax(Integer.parseInt(args[++i]));
            } else if (args[i].equals("-m")) {
                idx.setMin(Integer.parseInt(args[++i]));
            } else if (args[i].equals("-s")) {
                idx.setSplit(Short.parseShort(args[++i]));
            } else if (args[i].equals("-b")) {
                idx.setByteOrder(args[++i]);
            } else {
                if (!args[i].toLowerCase().endsWith(".shp")) {
                    System.out.println("File extension must be '.shp'");
                    System.exit(1);
                }

                idx.setShapeFileName(args[i]);
            }
        }

        try {
            System.out.print("Indexing ");

            int cnt = idx.index(true, new Lock());
            System.out.println();
            System.out.print(cnt + " features indexed ");
            System.out.println("in " + (System.currentTimeMillis() - start)
                + "ms.");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
            usage();
            System.exit(1);
        }
    }

    private static void usage() {
        System.out.println("Usage: ShapeFileIndexer "
            + "-t <RTREE | QUADTREE> " + "[-M <max entries per node>] "
            + "[-m <min entries per node>] " + "[-s <split algorithm>] "
            + "[-b <byte order NL | NM>] " + "<shape file>");

        System.out.println();

        System.out.println("Options:");
        System.out.println("\t-t Index type: RTREE or QUADTREE");
        System.out.println();
        System.out.println("Following options apllies only to RTREE:");
        System.out.println("\t-M maximum number of entries per node");
        System.out.println("\t-m minimum number of entries per node");
        System.out.println("\t-s split algorithm to use");
        System.out.println();
        System.out.println("Following options apllies only to QUADTREE:");
        System.out.println("\t-b byte order to use: NL = LSB; "
            + "NM = MSB (default)");

        System.exit(1);
    }

    /**
     * Index the shapefile denoted by setShapeFileName(String fileName)  If
     * when a thread starts, another thread is indexing the same file, this
     * thread will wait that the first thread ends indexing; in this case
     * <b>zero</b> is reurned as result of the indexing process.
     *
     * @param verbose enable/disable printing of dots every 500 indexed records
     * @param lock DOCUMENT ME!
     *
     * @return The number of indexed records (or zero)
     *
     * @throws MalformedURLException
     * @throws IOException
     * @throws TreeException
     * @throws StoreException DOCUMENT ME!
     * @throws LockTimeoutException
     */
    public int index(boolean verbose, Lock lock)
        throws MalformedURLException, IOException, TreeException, 
            StoreException, LockTimeoutException {
        /* We don't want that 2 threads do a parallel
         * indexing of the same shape file...
         */
        boolean alreadyRunning = true;
        Object sync = null;

        if (this.fileName == null) {
            throw new IOException("You have to set a shape file name!");
        }

        File file = new File(this.fileName);
        
        if( !file.canWrite() )
        	return 0;
        
        synchronized (IDX_CREATION) {
            sync = IDX_CREATION.get(this.fileName);

            if (sync == null) {
                sync = Thread.currentThread().getName();
                IDX_CREATION.put(this.fileName, sync);
                alreadyRunning = false;
            }
        }

        /*
         * If another thread is running wait for index completition
         */
        if (alreadyRunning) {
            try {
                LOGGER.fine("Waiting for index build completition by thread "
                    + sync);

                long start = System.currentTimeMillis();

                while ((IDX_CREATION.get(this.fileName) != null)
                        && ((System.currentTimeMillis() - start) < MAX_WAIT_TIME)) {
                    synchronized (sync) {
                        sync.wait(MIN_WAIT_TIME);
                    }
                }

                if (IDX_CREATION.get(this.fileName) != null) {
                    throw new TreeException("Max wait time for index build "
                        + "reached!");
                }

                return 0;
            } catch (InterruptedException ie) {
                throw new TreeException("Interrupted during wait for index "
                    + "build");
            }
        }

        int cnt = 0;

        try {
            if (!RTREE.equals(this.idxType) && !QUADTREE.equals(this.idxType)) {
                throw new TreeException("Index type must be " + RTREE + " or "
                    + QUADTREE);
            }


            
            FileInputStream is = new FileInputStream(file);
            FileChannel channel = is.getChannel();
            ShapefileReader reader=null;
            String ext = this.fileName.substring(this.fileName.lastIndexOf('.'));
            String rtreeName = this.fileName.substring(0,
            		this.fileName.lastIndexOf('.'));

            // Temporary file for building...
            File treeFile = new File(rtreeName + ".bld");
            
            try{
            	reader = new ShapefileReader(channel, true, false,
                        lock);

	            if (!ext.equalsIgnoreCase(".shp")) {
	                throw new TreeException("The file to index must have "
	                    + "'.shp' extension");
	            }
	
	            // Build index name
	            if (this.idxType.equals(RTREE)) {
	                rtreeName += (ext.equals(".shp") ? ".grx" : ".GRX");
	            } else if (this.idxType.equals(QUADTREE)) {
	                rtreeName += (ext.equals(".shp") ? ".qix" : ".QIX");
	            }
	
	
	            // Delete temporary file if exists
	            if (treeFile.exists()) {
	                if (!treeFile.delete()) {
	                    throw new TreeException("Unable to delete " + treeFile
	                        + " cannot create a new index!");
	                }
	            }
	
	            if (this.idxType.equals(RTREE)) {
	                cnt = this.buildRTree(reader, treeFile, verbose);
	            } else if (this.idxType.equals(QUADTREE)) {
	                cnt = this.buildQuadTree(reader, treeFile, verbose);
	            }
            }finally{
            	if( reader!=null )
            		reader.close();
            	if( is!=null )
            		is.close();
            }

            // Final index file
            File finalFile = new File(rtreeName);
            boolean copied=false;
            
            // Delete file if exists
            if (finalFile.exists()) {
                if (!finalFile.delete()) {
                	try{
                		copyFile(treeFile, finalFile);
                		copied=true;
                	}catch (IOException ie){
                        throw new TreeException("Unable to delete " + treeFile
                                + " cannot commit the new index!");

                	}
                }
            }

            if (!copied && !treeFile.renameTo(finalFile)) {
                throw new TreeException("Unable to rename " + treeFile + " to "
                    + finalFile + " cannot commit the new index!");
            }
        } finally {
            if (!alreadyRunning) {
                synchronized (sync) {
                    IDX_CREATION.remove(this.fileName);
                    sync.notifyAll();
                }
            }
        }

        return cnt;
    }

    /** 
     * Copy data from source file to destination file.
     * 
     * @param source source file
     * @param dest destination file
     */
    private static void copyFile(File source, File dest) throws IOException {
    	 if(!dest.exists()) {
    	  dest.createNewFile();
    	 }
    	 InputStream in = null;
    	 OutputStream out = null;
    	 try {
    	  in = new FileInputStream(source);
    	  out = new FileOutputStream(dest);
    	    
    	  // Transfer bytes from in to out
    	  byte[] buf = new byte[1024];
    	  int len;
    	  while ((len = in.read(buf)) > 0) {
    	   out.write(buf, 0, len);
    	  }
    	 }
    	 finally {
    	  if(in != null) {
    	   in.close();
    	  }
    	  if(out != null) {
    	   out.close();
    	  }
    	 }
    	}

	/**
     * DOCUMENT ME!
     *
     * @param reader
     * @param rtreeFile
     * @param verbose
     *
     *
     * @throws TreeException
     * @throws LockTimeoutException
     * @throws IOException
     */
    private int buildRTree(ShapefileReader reader, File rtreeFile,
        boolean verbose)
        throws TreeException, LockTimeoutException, IOException {
        DataDefinition keyDef = new DataDefinition("US-ASCII");
        keyDef.addField(Integer.class);
        keyDef.addField(Long.class);

        FileSystemPageStoreRtree fps = new FileSystemPageStoreRtree(rtreeFile, keyDef,
                this.max, this.min, this.split);
        RTree rtree = new RTree(fps);

        Record record = null;
        Data data = null;

        int cnt = 0;

        while (reader.hasNext()) {
            record = reader.nextRecord();
            data = new Data(keyDef);
            data.addValue(new Integer(++cnt));
            data.addValue(new Long(record.offset()));

            rtree.insert(new Envelope(record.minX, record.maxX, record.minY,
                    record.maxY), data);

            if (verbose && ((cnt % 500) == 0)) {
                System.out.print('.');
            }
        }

        rtree.close();

        return cnt;
    }

    private int buildQuadTree(ShapefileReader reader, File file, boolean verbose)
        throws IOException, StoreException {
        byte order = 0;

        if ((this.byteOrder == null) || this.byteOrder.equalsIgnoreCase("NM")) {
            order = IndexHeader.NEW_MSB_ORDER;
        } else if (this.byteOrder.equalsIgnoreCase("NL")) {
            order = IndexHeader.NEW_LSB_ORDER;
        } else {
            throw new StoreException("Asked byte order '" + this.byteOrder
                + "' must be 'NL' or 'NM'!");
        }

        String ext = this.fileName.substring(this.fileName.lastIndexOf('.'));

        String idxFileName = this.fileName.substring(0,
                this.fileName.length() - 4)
            + (ext.equals(".shp") ? ".shx" : ".SHX");

        FileInputStream fisIdx = new FileInputStream(idxFileName);
        FileChannel channelIdx = fisIdx.getChannel();
        IndexFile shpIndex = new IndexFile(channelIdx);
        QuadTree tree=null;
        int cnt = 0;
        try{
        int numRecs = shpIndex.getRecordCount();
        ShapefileHeader header = reader.getHeader();
        Envelope bounds = new Envelope(header.minX(), header.maxX(),
                header.minY(), header.maxY());

        tree = new QuadTree(numRecs, max, bounds, shpIndex);

        Record rec = null;

        while (reader.hasNext()) {
            rec = reader.nextRecord();
            tree.insert(cnt++,
                new Envelope(rec.minX, rec.maxX, rec.minY, rec.maxY));

            if (verbose && ((cnt % 1000) == 0)) {
                System.out.print('.');
            }
            if( cnt%100000 == 0 )
            	System.out.print('\n');
        }
        if( verbose ) System.out.println("done");
        }finally{
	        channelIdx.close();
	        fisIdx.close();
	        shpIndex.close();
        }
        FileSystemIndexStore store = new FileSystemIndexStore(file, order);
        store.store(tree);

        return cnt;
    }

    /**
     * DOCUMENT ME!
     * 
     * For quad tree this is the max depth.  I don't know what it is for RTree
     *
     * @param i
     */
    public void setMax(int i) {
        max = i;
    }

    /**
     * DOCUMENT ME!
     *
     * @param i
     */
    public void setMin(int i) {
        min = i;
    }

    /**
     * DOCUMENT ME!
     *
     * @param s
     */
    public void setSplit(short s) {
        split = s;
    }

    /**
     * DOCUMENT ME!
     *
     * @param string
     */
    public void setShapeFileName(String string) {
        fileName = string;
    }

    /**
     * DOCUMENT ME!
     *
     * @param idxType The idxType to set.
     */
    public void setIdxType(String idxType) {
        this.idxType = idxType;
    }

    /**
     * DOCUMENT ME!
     *
     * @param byteOrder The byteOrder to set.
     */
    public void setByteOrder(String byteOrder) {
        this.byteOrder = byteOrder;
    }
}
