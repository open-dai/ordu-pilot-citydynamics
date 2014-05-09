package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IQueue;

/**
 * Iterates over the nodes of a graph in a <B>Depth First Search</B> pattern 
 * starting from a specified node. The following illustrates the iteration order. 
 * <BR>
 * <BR>
 * <IMG src="doc-files/dfs.gif"/><BR>
 * <BR>
 * The iteration operates by maintaning a node queue of <B>active</B> nodes.  
 * An <B>active</B> node is a node that will returned at a later stage of the i
 * teration. The node queue for a Depth First iteration is implemented as a 
 * <B>Last In First Out</B> queue (a Stack).
 * A node is placed in the the node queue if it has not been visited, and 
 * it is adjacent to a a node that has been visited. The node queue intially 
 * contains only the source node of the traversal.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/standard/DepthFirstIterator.java $
 */
public class SmpDepthFirstIterator extends SmpBreadthFirstIterator {

  /**
   * Builds the node queue for the Iteration.
   * 
   * @param graph The graph of the iteration.
   * 
   * @return A Last In First Out queue (Stack)  
   */
  protected IQueue buildQueue(IGraph graph) {
    return(new SmpStack(graph.getNodes().size()));
  }

}
