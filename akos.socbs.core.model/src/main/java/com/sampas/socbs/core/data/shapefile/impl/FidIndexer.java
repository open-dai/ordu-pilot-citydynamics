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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;

import org.geotools.data.DataUtilities;

/**
 * Creates a .fix file (fid index).
 *
 * @author Jesse
 */
public class FidIndexer {
	static Logger LOGGER=org.geotools.util.logging.Logging.getLogger("org.geotools.data.shapefile");
    public static synchronized URL generate(URL shpURL) throws IOException {
    	LOGGER.fine("Generating fids for "+shpURL);
        IndexedFidWriter writer = null;
        IndexFile indexFile=null;

        try {
            String filename;

            try {
                filename = java.net.URLDecoder.decode(shpURL.toString(),
                        "US-ASCII");
                filename = filename.substring(0, filename.lastIndexOf(".shp"));
            } catch (java.io.UnsupportedEncodingException use) {
                throw new java.net.MalformedURLException("Unable to decode "
                    + shpURL + " cause " + use.getMessage());
            }

            int indexslash = filename.lastIndexOf(File.pathSeparator);

            if (indexslash == -1) {
                indexslash = 0;
            }

            URL fixURL = new URL(filename + ".fix");
            URL indexURL = new URL(filename + ".shx");
            
            if( DataUtilities.urlToFile(fixURL).exists() )
            	return fixURL;
            
            if( !(DataUtilities.urlToFile(indexURL).exists()) )
            	return null;
            
			indexFile = new IndexFile(getReadChannel(indexURL));
            FileChannel writeChannel = getWriteChannel(fixURL);

            writer = new IndexedFidWriter(writeChannel,
                    new IndexedFidReader(filename.substring(indexslash),
                        writeChannel));

            for (int i = 0, j = indexFile.getRecordCount(); i < j; i++) {
                writer.next();
            }

            return fixURL;
        } finally {
        	try{
            if (indexFile != null) {
            	indexFile.close();
            }
        	}finally{

            if (writer != null) {
                writer.close();
            }
        	}
        }
    }

    /**
     * Obtain a ReadableByteChannel from the given URL. If the url
     * protocol is file, a FileChannel will be returned. Otherwise a generic
     * channel will be obtained from the urls input stream.
     *
     * @param url DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws IOException DOCUMENT ME!
     */
    protected static ReadableByteChannel getReadChannel(URL url)
        throws IOException {
        ReadableByteChannel channel = null;

        if (url.getProtocol().equals("file")) { // && useMemoryMappedBuffer) {

            File file = DataUtilities.urlToFile(url);

            if (!file.exists()) {
                throw new IOException("File doesn't exist: " + file);
            }

            if (!file.canRead()) {
                throw new IOException("File isn't readable: " + file);
            }

            FileInputStream in = new FileInputStream(file);
            channel = in.getChannel();
        } else {
            InputStream in = url.openConnection().getInputStream();
            channel = Channels.newChannel(in);
        }

        return channel;
    }

    /**
     * Obtain a WritableByteChannel from the given URL. If the url
     * protocol is file, a FileChannel will be returned. Currently, this
     * method will return a generic channel for remote urls, however both
     * shape and dbf writing can only occur with a local FileChannel channel.
     *
     * @param url DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws IOException DOCUMENT ME!
     */
    protected static FileChannel getWriteChannel(URL url)
        throws IOException {
        FileChannel channel;

        if (url.getProtocol().equals("file")) { // && useMemoryMappedBuffer) {

            File file = DataUtilities.urlToFile(url);

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            channel = raf.getChannel();

            ((FileChannel) channel).lock();

            return channel;
        } else {
            return null;
        }
    }
}
