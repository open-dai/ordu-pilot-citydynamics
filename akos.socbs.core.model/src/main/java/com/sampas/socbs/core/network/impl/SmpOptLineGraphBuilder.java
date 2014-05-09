package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.INode;

/**
 * An inmplementation extended from OptGraphBuilder used to build optimized
 * components for line networks.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/build/line/OptLineGraphBuilder.java $
 */
public class SmpOptLineGraphBuilder extends SmpOptGraphBuilder {

  /**
   * Returns a node of type OptXYNode.
   * 
   * @see SmpOptXYNode
   * @see org.geotools.graph.build.GraphBuilder#buildNode()
   */
  public INode buildNode() {
    return(new SmpOptXYNode());  
  }
 
}
