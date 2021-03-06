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
import java.nio.channels.ReadableByteChannel;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import org.geotools.data.FIDReader;
import org.geotools.resources.NIOUtilities;

/**
 * This object reads from a file the fid of a feature in a shapefile.
 *
 * @author Jesse
 */
public class IndexedFidReader implements FIDReader {
	
    private static final Logger LOGGER=org.geotools.util.logging.Logging.getLogger("org.geotools.data.shapefile");
    private ReadableByteChannel readChannel;
    private ByteBuffer buffer;
    private long count;
    private String typeName;
    private boolean done;
    private int removes;
    private int currentShxIndex = -1;
    private RecordNumberTracker reader;
    private long currentId;
    StreamLogging streamLogger=new StreamLogging("IndexedFidReader");

    public IndexedFidReader(String typeName, ReadableByteChannel readChannel)
        throws IOException {
        this.typeName = typeName + ".";
        this.readChannel = readChannel;
        streamLogger.open();
        getHeader();

        buffer = ByteBuffer.allocateDirect(12 * 1024);
        buffer.position(buffer.limit());
    }

    public IndexedFidReader(String typeName,
            RecordNumberTracker reader, ReadableByteChannel readChannel)
        throws IOException {
        this.reader = reader;
        this.typeName = typeName + ".";
        this.readChannel = readChannel;
        getHeader();

        buffer = ByteBuffer.allocateDirect(12 * 1024);
        buffer.position(buffer.limit());
    }

    private void getHeader() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(IndexedFidWriter.HEADER_SIZE);
        ShapefileReader.fill(buffer, readChannel);

        if (buffer.position() == 0) {
            done = true;
            count = 0;

            return;
        }

        buffer.position(0);

        byte version = buffer.get();

        if (version != 1) {
            throw new IOException(
                "File is not of a compatible version for this reader or file is corrupt.");
        }

        this.count = buffer.getLong();
        this.removes = buffer.getInt();
    }

    /**
     * Returns the number of Fid Entries in the file.
     *
     * @return Returns the number of Fid Entries in the file.
     */
    public long getCount() {
        return count;
    }

    /**
     * Returns the number of features that have been removed since the
     * fid index was regenerated.
     *
     * @return Returns the number of features that have been removed since the
     *         fid index was regenerated.
     */
    public int getRemoves() {
        return removes;
    }

    /**
     * Returns the offset to the location in the SHX file that the fid
     * identifies. This search take logN time.
     *
     * @param fid the fid to find.
     *
     * @return Returns the record number of the record in the SHX file that the
     *         fid identifies. Will return -1 if the fid was not found.
     *
     * @throws IOException
     * @throws IllegalArgumentException DOCUMENT ME!
     */
    public long findFid(String fid) throws IOException {
        try {
            long desired = Long.parseLong(fid.substring(fid.lastIndexOf(".")
                        + 1));

            if ((desired < 0)) {
                return -1;
            }

            if( desired<count ){
                return search(desired, -1, this.count, desired - 1);
            }else{
                return search( desired, -1, this.count, count - 1 );
            }
        } catch (NumberFormatException e) {
            LOGGER.warning( "Fid is not recognized as a fid for this shapefile: "
                + typeName);
            return -1;
        }
    }

    /**
     * Searches for the desired record.
     *
     * @param desired the id of the desired record.
     * @param minRec the last record that is known to be <em>before</em> the
     *        desired record.
     * @param maxRec the first record that is known to be <em>after</em> the
     *        desired record.
     * @param predictedRec the record that is predicted to be the desired
     *        record.
     *
     * @return returns the record number of the feature in the shx file.
     *
     * @throws IOException
     */
    long search(long desired, long minRec, long maxRec,
        long predictedRec) throws IOException {
        if (minRec == maxRec) {
            return -1;
        }

        goTo(predictedRec);
        buffer.limit(IndexedFidWriter.RECORD_SIZE);
        next();
        buffer.limit(buffer.capacity());
        if (currentId == desired) {
            return currentShxIndex;
        }

        if( maxRec-minRec < 10 ){
            return search( desired, minRec+1, maxRec, minRec+1 );
        }else{
            long newOffset = desired - currentId;
            long newPrediction = predictedRec + newOffset;

            if (newPrediction <= minRec) {
                newPrediction = minRec + ((predictedRec - minRec) / 2);
            }

            if (newPrediction >= maxRec) {
                newPrediction = predictedRec + ((maxRec - predictedRec) / 2);
            }

            if (newPrediction == predictedRec) {
                return -1;
            }

            if (newPrediction < predictedRec) {
                return search(desired, minRec, predictedRec, newPrediction);
            } else {
                return search(desired, predictedRec, maxRec, newPrediction);
            }
        }
    }

    /**
     * move the reader to the recno-th entry in the file.
     *
     * @param recno
     *
     * @throws IOException
     */
//    private long lastRecNo = Long.MIN_VALUE;
    private long bufferStart = Long.MIN_VALUE;
    public void goTo(long recno) throws IOException {
        if (readChannel instanceof FileChannel) {
            long newPosition = IndexedFidWriter.HEADER_SIZE + (recno * IndexedFidWriter.RECORD_SIZE);
            if(newPosition >= bufferStart + buffer.limit() || newPosition < bufferStart) {
                FileChannel fc = (FileChannel) readChannel;
                fc.position(newPosition);
                buffer.limit(buffer.capacity());
                buffer.position(buffer.limit());
            } else {
                buffer.position((int) (newPosition - bufferStart));
            }
        } else {
            throw new IOException(
                "Read Channel is not a File Channel so this is not possible.");
        }
    }

    public void close() throws IOException {
        try {
            if( buffer!=null ){
                if( buffer instanceof MappedByteBuffer ){
                    NIOUtilities.clean(buffer);
                }
            }
            if (reader != null)
                reader.close();
        } finally {
                readChannel.close();
                streamLogger.close();
        }
    }

    public boolean hasNext() throws IOException {
        if (done) {
            return false;
        }

        if (buffer.position() == buffer.limit()) {
            buffer.position(0);

            FileChannel fc = (FileChannel) readChannel;
            bufferStart = fc.position();
            int read = ShapefileReader.fill(buffer, readChannel);

            if (read != 0) {
                buffer.position(0);
            }
        }

        return buffer.remaining() != 0;
    }

    public String next() throws IOException {
        if (reader != null) {
            goTo(reader.getRecordNumber() - 1);
        }

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        currentId = buffer.getLong();
        currentShxIndex = buffer.getInt();

        return typeName + currentId;
    }

    /**
     * Returns the record number of the feature in the shx or shp that
     * is identified by the the last fid returned by next().
     *
     * @return Returns the record number of the feature in the shx or shp that
     *         is identified by the the last fid returned by next().
     *
     * @throws NoSuchElementException DOCUMENT ME!
     */
    public int currentIndex() {
        if (currentShxIndex == -1) {
            throw new NoSuchElementException(
                "Next must be called before there exists a current element.");
        }

        return currentShxIndex;
    }
}
