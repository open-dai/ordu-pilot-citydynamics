package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.INode;

/**
 * An implementation of GraphBuilder extended from BasicGraphBuilder used
 * to build graphs representing line networks. 
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/build/line/BasicLineGraphBuilder.java $
 */
public class SmpBasicLineGraphBuilder extends SmpBasicGraphBuilder {
  
  /**
   * Returns a node of type BasicXYNode.
   * 
   * @see SmpBasicXYNode
   * @see org.geotools.graph.build.GraphBuilder#buildNode()
   */
  public INode buildNode() {
    return(new SmpBasicXYNode());
  }
 
}
