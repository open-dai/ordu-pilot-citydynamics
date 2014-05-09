package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IQueue;

/**
 * Iterates over the nodes of a graph in <B>Depth First Topological Sort</B> 
 * pattern. The following is an illustration of the iteration.<BR>
 * <IMG src="doc-files/depth_topo.gif"><BR>
 * <BR>
 * Initially all nodes of degree less than two are <B>active</B> 
 * (ready to be visited). As nodes are visited, a node can become active 
 * when all but one of its related nodes have been visited (
 * degree = counter + 1). When a node becomes active it is placed into the
 * <B>active node queue</B> (queue of nodes to be visited). 
 * The Depth First Topological iterator places
 * nodes into the queue in <B>Last In First Out</B> order (a Stack).<BR>
 * <BR>
 * To determine when a node is to become active the iterator uses the counter
 * associated with each node. If these counters are modified by an entity 
 * other then the iterator, the iteration may be affected in undefined ways. 
 *  
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/standard/DepthFirstTopologicalIterator.java $
 */
public class SmpDepthFirstTopologicalIterator 
  extends SmpBreadthFirstTopologicalIterator {
  
  /**
   * Builds the active node queue. 
   * 
   * @param graph The Graph whose components are being iterated over.
   * 
   * @return A last in first out queue (a stack)
   */
  protected IQueue buildQueue(IGraph graph) {
    return(new SmpStack(graph.getNodes().size()));  
  }

}
