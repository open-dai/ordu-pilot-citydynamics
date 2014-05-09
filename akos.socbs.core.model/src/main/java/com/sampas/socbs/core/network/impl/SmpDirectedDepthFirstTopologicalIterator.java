package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IQueue;

/**
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/standard/DirectedDepthFirstTopologicalIterator.java $
 */
public class SmpDirectedDepthFirstTopologicalIterator 
  extends SmpDirectedBreadthFirstTopologicalIterator {
  
  protected IQueue buildQueue(IGraph graph) {
    return(new SmpStack(graph.getNodes().size()));
  }

}
