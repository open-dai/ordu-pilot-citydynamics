package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IGraphTraversal;
import com.sampas.socbs.core.network.IGraphWalker;
import com.sampas.socbs.core.network.IGraphable;

/**
 * A simple implementation of GraphWalker that does nothing but signal 
 * a graph traversal to continue.
 * 
 * @see IGraphTraversal
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/basic/DummyGraphWalker.java $
 */
public class SmpDummyGraphWalker implements IGraphWalker {

  /**
   * Returns the continue signal.
   * 
   * @see IGraphWalker#visit(IGraphable, IGraphTraversal)
   * @see IGraphTraversal#CONTINUE
   */
  public int visit(IGraphable element, IGraphTraversal traversal) {
    return(IGraphTraversal.CONTINUE);
  }

  /**
   * Does nothing.
   * 
   * @see IGraphWalker#finish()
   */
  public void finish() {}
  
}
