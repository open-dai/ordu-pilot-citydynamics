package com.sampas.socbs.core.network.impl;

import java.util.Collection;

import com.sampas.socbs.core.network.IDirectedGraph;
/**
 * Basic implementation of DirectedGraph. 
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/basic/BasicDirectedGraph.java $
 */
public class SmpBasicDirectedGraph extends SmpBasicGraph implements IDirectedGraph {

  /**
   * Creates a directed graph from a collection of directed nodes and a 
   * collection of directed edges.
   * The relationships between the nodes (edges) are already assumed to be 
   * formed. Only the references to the node and edge collections are copied,
   * not the underlying collections themselves.
   * 
   * @param nodes Collection of DirectedNode objects contained by the graph.
   * @param edges Collection of DirectedEdge objects contained by the graph.
   */
  public SmpBasicDirectedGraph(Collection nodes, Collection edges) {
    super(nodes, edges);
  }
  
}
