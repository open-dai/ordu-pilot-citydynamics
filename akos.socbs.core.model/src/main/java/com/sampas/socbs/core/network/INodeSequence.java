package com.sampas.socbs.core.network;

import java.util.Iterator;
/**
 * Represents a sequence of nodes in a graph.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/path/NodeSequence.java $
 */
public interface INodeSequence {
  
  /**
   * Returns the first node in the sequence.
   * 
   * @return Object of tupe Node.
   */
  public INode getFirst();
  
  /**
   * Returns the last node in the sequence.
   * 
   * @return Object of type node.
   */
  public INode getLast();
  
  /**
   * Returns the number of nodes in the sequence.
   * 
   * @return an integer representing the number of nodes in the sequence.
   */
  public int size();
  
  /**
   * Returns an iterator over the nodes.
   * 
   * @return An iterator.
   */
  public Iterator iterator();
  
  /**
   * Determines if the node sequence id valid based on the rules of the 
   * implementation.
   * 
   * @return True if valid, otherwise false.
   */
  public boolean isValid();    
}
