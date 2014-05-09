package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.INode;

/**
 * An implementation of GraphBuilder that builds optimized directed graph 
 * components.
 * 
 * @see org.geotools.graph.structure.opt.OptDirectedNode
 * @see org.geotools.graph.structure.opt.OptDirectedEdge
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/build/opt/OptDirectedGraphBuilder.java $
 */
public class SmpOptDirectedGraphBuilder extends SmpBasicDirectedGraphBuilder {
  
  /**
   * Creates an optimized directed node.
   * 
   * @see IGraphBuilder#buildNode()
   * @see SmpOptDirectedNode
   */
  public INode buildNode() {
    return(new SmpOptDirectedNode());
  }

  /**
   * Creates an optimized directed edge.
   * 
   * @see IGraphBuilder#buildEdge()
   * @see SmpOptDirectedEdge
   */
  public IEdge buildEdge(INode nodeA, INode nodeB) {
    return(new SmpOptDirectedEdge((SmpOptDirectedNode)nodeA, (SmpOptDirectedNode)nodeB));
  }
  
}
