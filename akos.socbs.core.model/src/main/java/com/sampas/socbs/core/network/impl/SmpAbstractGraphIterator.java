package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphIterator;
import com.sampas.socbs.core.network.IGraphTraversal;
import com.sampas.socbs.core.network.IGraphWalker;

/**
 * An abstract implementation of GraphIterator. 
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/basic/AbstractGraphIterator.java $
 */
public abstract class SmpAbstractGraphIterator implements IGraphIterator {

  private IGraphTraversal m_traversal;
  
  /**
   * @see IGraphIterator#setTraversal(IGraphTraversal)
   */
  public void setTraversal(IGraphTraversal traversal) {
    m_traversal = traversal;  
  }
  
  /**
   * @see IGraphIterator#getTraversal()
   */
  public IGraphTraversal getTraversal() {
    return(m_traversal);  
  }
  
  /**
   * Returns the graph being traversed.
   * 
   * @return The graph being traversed.
   * 
   * @see IGraph
   */
  public IGraph getGraph() {
    return(m_traversal.getGraph());  
  }
  
  /**
   * Returns the walker being traversed over the graph.
   *
   * @return The walker being traversed over the graph.
   * 
   * @see IGraphWalker
   */
  
  public IGraphWalker getWalker() {
    return(m_traversal.getWalker());  
  }
}
