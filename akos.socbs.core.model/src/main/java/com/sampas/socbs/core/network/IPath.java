package com.sampas.socbs.core.network;

import java.util.Collection;
import java.util.HashSet;
/**
 * Represents a path in a graph. A <B>path</B> P is defined as a <B>walk</B> 
 * in which there are no node repetitions. 
 *
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/path/Path.java $
 */
public class IPath extends IWalk {

  public IPath() {
    super();	
  }
  
  //TODO: DOCUMENT ME!
  public IPath(Collection nodes) {
    super(nodes);
  }

  /**
   * Tests if the path is valid. A valid path satisfies two conditions: <BR>
   * <BR>
   * 1. Each pair of adjacent nodes share an edge.<BR>
   * 2. There are no node repetitions.
   */
  public boolean isValid() {
    if (super.isValid()) {
      //test repetitions
      HashSet s = new HashSet(this);
      return(size() == s.size());
    }
    
    return(false);
  }

}
