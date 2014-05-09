package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IGraphReaderWriter;

/**
 * Represents a GraphReaderWriter that reads/writes from/to files.
 *  
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/io/standard/FileReaderWriter.java $
 */
public interface SmpFileReaderWriter extends IGraphReaderWriter {
  
  /** filename key **/
  public static final String FILENAME = "FILENAME";
    
}
