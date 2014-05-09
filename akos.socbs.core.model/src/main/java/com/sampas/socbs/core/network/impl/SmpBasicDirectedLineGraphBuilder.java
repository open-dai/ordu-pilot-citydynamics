package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.INode;

/**
 * An implementation of GraphBuilder extended from BasicDirectedGraphBuilder 
 * used to build graphs representing directed line networks.  
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/build/line/BasicDirectedLineGraphBuilder.java $
 */
public class SmpBasicDirectedLineGraphBuilder extends SmpBasicDirectedGraphBuilder {
 
  /**
   * Returns a node of type BasicDirectedXYNode.
   * 
   * @see SmpBasicDirectedXYNode
   * @see org.geotools.graph.build.GraphBuilder#buildNode()
   */
  public INode buildNode() {
    return(new SmpBasicDirectedXYNode());
  }
 
}
