package com.sampas.socbs.core.network.impl;

import java.io.Serializable;

import com.sampas.socbs.core.network.IGraphable;
/**
 * Root of class hierarchy for optimized implementation of graph components. 
 * The optimizations reduce the space taken up by graph components: <BR>
 * <UL>
 * <LI>Counter implemented as byte
 * <LI>No underlying object reference.
 * </UL>
 * Objects in the optimized hierarchy implement the Serializable interface.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 * @see IGraphable
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/opt/OptGraphable.java $
 */
public abstract class SmpOptGraphable implements IGraphable, Serializable {

  /** visited flag **/
  private boolean m_visited;
  
  /** counter **/
  private byte m_count;
   
  /**
   * Constructs a new optimized graphable object. Visited flag it set to false
   * and counter set to -1.
   */
  public SmpOptGraphable() {
    m_visited = false;
    m_count = -1;
  }
  
  /**
   * Does nothing.
   * 
   * @see IGraphable#setID(int) 
   */
  public void setID(int id) {}
  
  /**
   * Returns 0.
   * 
   * @see IGraphable#getID()
   */
  public int getID() {
    return 0;
  }
  
  /**
   * @see IGraphable#setVisited(boolean)
   */
  public void setVisited(boolean visited) {
    m_visited = visited;
  }
  
  /**
   * @see IGraphable#isVisited()
   */
  public boolean isVisited() {
    return(m_visited);
  }

  /**
   * To minimize space, the counter is stored as a byte. Therefore the counter
   * can take on values from -128 to 127.
   * 
   * @see IGraphable#setCount(int)
   */
  public void setCount(int count) {
    m_count = (byte)count;    
  }

  /**
   * @see IGraphable#getCount()
   */
  public int getCount() {
    return(m_count);
  }
  
  /**
   * Does nothing.
   * 
   * @see IGraphable#setObject(Object)
   */
  public void setObject(Object obj) {}

  /**
   * Returns null.
   * 
   * @see IGraphable#getObject()
   */
  public Object getObject() {
    return null;
  }
} 
