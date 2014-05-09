package com.sampas.socbs.core.network;

import java.util.Iterator;

/**
 * Reperesents a component in a directed graph. 
 * 
 * @see IDirectedGraph
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/DirectedGraphable.java $
 */
public interface IDirectedGraphable extends IGraphable {
  
  /**
   * Returns other components related through an <B>in</B> relationship. 
   *   
   * @return An iterator over the other directed components related through an 
   * in relationship.
   * 
   * @see IGraphable#getRelated()
   */
  public Iterator getInRelated();
  
  /**
   * Returns other components related through an <B>out</B> relationship.
   * 
   * @return An iterator over the other directed components related through an 
   * out relationship.
   * 
   * @see IGraphable#getRelated()
   */
  public Iterator getOutRelated();	
}
