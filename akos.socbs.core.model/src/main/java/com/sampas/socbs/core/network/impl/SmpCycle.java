package com.sampas.socbs.core.network.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.INode;
/**
 * 
 * Represents a cycle in a graph. A <B>cycle</B> C is defined as a closed walk 
 * of size n in which nodes 1 through n-1 form a path. 
 *   
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/path/Cycle.java $
 */
public class SmpCycle extends SmpWalk {
  
   //TODO: DOCUMENT ME!
  public SmpCycle(Collection nodes) {
    super(nodes);  
  }
  
  /**
   * Tests if the cycle is valid. A valid cycle satisfies two conditions: <BR>
   * <BR>
   * 1. Each pair of adjacent nodes share an edge.<BR>
   * 2. The first and last nodes share an edge.
   * 3. The only node repetition is the first and last nodes.
   */
  public boolean isValid() {
    if (super.isValid()) {
      
      //ensure first and last nodes are same
      if (isClosed()) {
        //ensure no node repetitions except for first and last
        return(new HashSet(this).size() == size()-1);
      }  
    }
    return(false);
  }
  
  protected List buildEdges() {
    List edges = super.buildEdges();
    
    //get the edge between the first and last nodes
    INode first = (INode)get(0);
    INode last = (INode)get(size()-1);
    
    IEdge e = first.getEdge(last);
    if (e != null) {
      edges.add(e);
      return(edges);
    }
    
    return(null);
  }
}
