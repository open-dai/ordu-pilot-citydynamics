package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IGraphTraversal;
import com.sampas.socbs.core.network.IGraphWalker;
import com.sampas.socbs.core.network.IGraphable;

/**
 * An implementation of GraphWalker that counts the number of components 
 * visited. As each component is visited, the walker sets the count of the 
 * component to the value of its counter.
 * 
 * @see IGraphable#setCount(int)
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/basic/CountingWalker.java $
 */
public class SmpCountingWalker implements IGraphWalker {

  /** counter of how many components have been visited **/
  private int m_counter;
  
  /**
   * Sets the count of the component and increments the counter.
   * 
   * @see IGraphable#setCount(int)
   * @see IGraphWalker#visit(IGraphable, IGraphTraversal)
   */
  public int visit(IGraphable element, IGraphTraversal traversal) {
    element.setCount(m_counter++);
    return IGraphTraversal.CONTINUE;
  }

  /**
   * Does nothing.
   * 
   * @see IGraphWalker#finish()
   */
  public void finish() {}
  
  /**
   * Returns the value of the visitation counter.
   * 
   * @return int Value of the counter.
   */
  public int getCount() {
    return(m_counter);
  }
}
