package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.INode;

/**
 * An inmplementation extended from OptDirectedGraphBuilder used to build 
 * optimized directed components for line networks.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/build/line/OptDirectedLineGraphBuilder.java $
 */
public class SmpOptDirectedLineGraphBuilder extends SmpOptDirectedGraphBuilder {

  /**
   * Returns a node of type OptDirectedXYNode.
   * 
   * @see SmpOptDirectedXYNode
   * @see org.geotools.graph.build.GraphBuilder#buildNode()
   */
  public INode buildNode() {
    return(new SmpOptDirectedXYNode());  
  }
 
}
