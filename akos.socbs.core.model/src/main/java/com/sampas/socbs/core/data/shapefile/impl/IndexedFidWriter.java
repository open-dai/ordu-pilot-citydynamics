/*
 *    GeoTools - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2005-2006, GeoTools Project Managment Committee (PMC)
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
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import org.geotools.resources.NIOUtilities;

/**
 * The Writer writes out the fid and record number of features to the fid
 * index file.
 *
 * @author Jesse
 */
public class IndexedFidWriter {
	
    public static final int HEADER_SIZE = 13;
    public static final int RECORD_SIZE = 12;
    private FileChannel channel;
    private ByteBuffer writeBuffer;
    private IndexedFidReader reader;
    long fidIndex;
    private int recordIndex;
    private boolean closed;

    private long current;

    private long position ;
    private int removes;
    StreamLogging streamLogger=new StreamLogging("IndexedFidReader");


    public IndexedFidWriter(FileChannel writeChannel,
        IndexedFidReader reader2) throws IOException {
        this.channel = writeChannel;
        reader = reader2;
        streamLogger.open();
        allocateBuffers();
        removes = reader.getRemoves();
        writeBuffer.position(HEADER_SIZE);
        closed = false;
        position=0;
        current=-1;
        recordIndex=0;
        fidIndex=0;
    }

    /**
     * Allocate some buffers for writing.
     */
    private void allocateBuffers() {
        writeBuffer = ByteBuffer.allocateDirect(HEADER_SIZE+RECORD_SIZE * 1024);
    }

    /**
     * Drain internal buffers into underlying channels.
     *
     * @throws IOException DOCUMENT ME!
     */
    private void drain() throws IOException {
        writeBuffer.flip();

        int written = 0;

        while (writeBuffer.remaining() > 0)
            written += channel.write(writeBuffer, position);

        position += written;

        writeBuffer.flip().limit(writeBuffer.capacity());
    }

    private void writeHeader() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(HEADER_SIZE);

        buffer.put((byte) 1);

        buffer.putLong(recordIndex);
        buffer.putInt(removes);
        buffer.flip();
        channel.write(buffer, 0);
    }

    public boolean hasNext() throws IOException {
        return reader.hasNext();
    }

    public long next() throws IOException {

        if( current != -1)
            write();

        if (reader.hasNext()) {
            String fid = reader.next();
            fidIndex = Integer.parseInt(fid.substring(fid.lastIndexOf(".") + 1));
        } else {
            fidIndex++;
        }

        current=fidIndex;

        return fidIndex;
    }

    public void close() throws IOException {
        if (closed) {
            return;
        }

        try {

            while(hasNext())
                next();

            if( current!=-1 )
                write();

            drain();
            writeHeader();
        } finally {
            try{

                if( writeBuffer!=null ){
                    if( writeBuffer instanceof MappedByteBuffer ){
                        NIOUtilities.clean(writeBuffer);
                    }
                }

                if( channel.isOpen() )
                    channel.close();
                streamLogger.close();
            }finally{
                reader.close();
            }
        }

        closed = true;
    }

    /**
     * Increments the fidIndex by 1.  Indicates that a feature was
     * removed from the location.  This is intended to ensure that FIDs stay
     * constant over time.  Consider the following case of 5 features. feature
     * 1 has fid typename.0 feature 2 has fid typename.1 feature 3 has fid
     * typename.2 feature 4 has fid typename.3 feature 5 has fid typename.4
     * when feature 3 is removed/deleted the following usage of the write
     * should take place:  next();  (move to feature 1) next(); (move to
     * feature 2) next();  (move to feature 3) remove();(delete feature 3)
     * next();  (move to feature 4)  // optional write(); (write feature 4)
     * next();  (move to feature 5) write(); (write(feature 5)
     *
     * @throws IOException
     */
    public void remove() throws IOException {
        if( current==-1 )
            throw new IOException("Current fid index is null, next must be called before remove");
        if (hasNext()) {
            removes++;
            current=-1;

            //            reader.next();
        }
    }

    /**
     * Writes the current fidIndex.  Writes to the same place in the
     * file each time.  Only {@link #next()} moves forward in the file.
     *
     * @throws IOException
     *
     * @see #next()
     * @see #remove()
     */
    public void write() throws IOException {
        if( current==-1 )
            throw new IOException("Current fid index is null, next must be called before remove");

        if (writeBuffer == null) {
            allocateBuffers();
        }

        if (writeBuffer.remaining()<RECORD_SIZE) {
            drain();
        }

        writeBuffer.putLong(current);
        writeBuffer.putInt(recordIndex);


        recordIndex++;
        current=-1;
    }

    public boolean isClosed() {
        return closed;
    }
}
