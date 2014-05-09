package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.INode;

/**
 * An implementation of GraphBuilder that builds optimized graph components.
 * 
 * @see org.geotools.graph.structure.opt.OptNode
 * @see org.geotools.graph.structure.opt.OptEdge
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/build/opt/OptGraphBuilder.java $
 */
public class SmpOptGraphBuilder extends SmpBasicGraphBuilder {
 
  /**
   * Creates an optimized node.
   * 
   * @see IGraphBuilder#buildNode()
   * @see SmpOptNode
   */
  public INode buildNode() {
    return(new SmpOptNode());
  }

  /**
   * Creates an optimized edge.
   * 
   * @see IGraphBuilder#buildEdge()
   * @see SmpOptEdge
   */
  public IEdge buildEdge(INode nodeA, INode nodeB) {
    return(new SmpOptEdge((SmpOptNode)nodeA, (SmpOptNode)nodeB));
  }
 
}
