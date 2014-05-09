/*
 *    Geotools - OpenSource mapping toolkit
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
 *
 *    This file is based on an origional contained in the GISToolkit project:
 *    http://gistoolkit.sourceforge.net/
 */
package com.sampas.socbs.core.data.shapefile.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

/** A DbaseFileReader is used to read a dbase III format file.
 * <br>
 * The general use of this class is:
 * <CODE><PRE>
 * FileChannel in = new FileInputStream("thefile.dbf").getChannel();
 * DbaseFileReader r = new DbaseFileReader( in )
 * Object[] fields = new Object[r.getHeader().getNumFields()];
 * while (r.hasNext()) {
 *    r.readEntry(fields);
 *    // do stuff
 * }
 * r.close();
 * </PRE></CODE>
 * For consumers who wish to be a bit more selective with their reading of rows,
 * the Row object has been added. The semantics are the same as using the
 * readEntry method, but remember that the Row object is always the same. The
 * values are parsed as they are read, so it pays to copy them out (as each call
 * to Row.read() will result in an expensive String parse).
 * <br><b>EACH CALL TO readEntry OR readRow ADVANCES THE FILE!</b><br>
 * An example of using the Row method of reading:
 * <CODE><PRE>
 * FileChannel in = new FileInputStream("thefile.dbf").getChannel();
 * DbaseFileReader r = new DbaseFileReader( in )
 * int fields = r.getHeader().getNumFields();
 * while (r.hasNext()) {
 *   DbaseFileReader.Row row = r.readRow();
 *   for (int i = 0; i < fields; i++) {
 *     // do stuff
 *     Foo.bar( row.read(i) );
 *   }
 * }
 * r.close();
 * </PRE></CODE>
 *
 * @author Ian Schneider
 * @author Tommaso Nolli
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/plugin/shapefile/src/main/java/org/geotools/data/shapefile/dbf/IndexedDbaseFileReader.java $
 */
public class IndexedDbaseFileReader extends DbaseFileReader{


/**
   * 
   * @param recno
   * @throws IOException
   * @throws UnsupportedOperationException
   */
  public void goTo(int recno)
  throws IOException, UnsupportedOperationException
  {
    
    if (this.randomAccessEnabled) {
      int newPosition = this.header.getHeaderLength() +
                        this.header.getRecordLength() * (recno - 1);
    
      if (this.useMemoryMappedBuffer) {
        buffer.position(newPosition);
      } else {
        FileChannel fc = (FileChannel)this.channel;
        fc.position(newPosition);
        buffer.limit(buffer.capacity());
        buffer.position(0);
        fill(buffer, channel);
        buffer.position(0);
        
        this.currentOffset = newPosition;
      }
    } else {
        throw new UnsupportedOperationException("Random access not enabled!");
    }
      
  }
  
    /**
   * Like calling DbaseFileReader(ReadableByteChannel, true);
   * @param channel
   * @throws IOException
   */
  public IndexedDbaseFileReader(ReadableByteChannel channel) throws IOException {
      this(channel, true);
  }

  /** Creates a new instance of DBaseFileReader
   * @param channel The readable channel to use.
   * @param useMemoryMappedBuffer Wether or not map the file in memory
   * @throws IOException If an error occurs while initializing.
   */
  public IndexedDbaseFileReader(ReadableByteChannel channel,
                         boolean useMemoryMappedBuffer)
  throws IOException
  {
	  super( channel, useMemoryMappedBuffer);
  }
  
  public IndexedDbaseFileReader(ReadableByteChannel channel,
          boolean useMemoryMappedBuffer, Charset stringCharset)
  throws IOException
  {
    super( channel, useMemoryMappedBuffer, stringCharset);
  }

  public boolean IsRandomAccessEnabled(){
      return this.randomAccessEnabled;
  }
  
public static void main(String[] args) throws Exception {
    FileChannel channel = new FileInputStream(args[0]).getChannel();
    IndexedDbaseFileReader reader = new IndexedDbaseFileReader(channel, true);
    System.out.println(reader.getHeader());
    int r = 0;
    while (reader.hasNext()) {
      System.out.println(++r + "," + java.util.Arrays.asList(reader.readEntry()));
    }
    reader.close();
  }
  
}
