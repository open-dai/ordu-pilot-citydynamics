package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IDirectedEdge;
import com.sampas.socbs.core.network.IDirectedNode;
import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.INode;

/**
 * An implementation of GraphBuilder used to build directed graphs.
 * 
 * @see org.geotools.graph.structure.DirectedGraph
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/build/basic/BasicDirectedGraphBuilder.java $
 */
public class SmpBasicDirectedGraphBuilder extends SmpBasicGraphBuilder {
 
  /**
   * Builds a directed node.
   * 
   * @see IDirectedNode
   * @see IGraphBuilder#buildNode()
   */
  public INode buildNode() {
    return(new SmpBasicDirectedNode());
  }
  
  /**
   * Builds a directed edge.
   * 
   * @see IDirectedEdge
   * @see IGraphBuilder#buildEdge()
   */
  public IEdge buildEdge(INode nodeA, INode nodeB) {
    return(new SmpBasicDirectedEdge((IDirectedNode)nodeA, (IDirectedNode)nodeB));
  }
  
  /**
   * Adds a directed edge to the graph.
   * 
   * @see IDirectedEdge
   * @see IGraphBuilder#addEdge(IEdge)
   */
  public void addEdge(IEdge edge) {
    IDirectedEdge de = (IDirectedEdge)edge;
    de.getInNode().addOut(de);
    de.getOutNode().addIn(de);
    getEdges().add(de);
  }
  
  /**
   * Creates a directed graph object.
   */
  protected IGraph buildGraph() {
    return(new SmpBasicDirectedGraph(getNodes(), getEdges()));      
  }
  
}
