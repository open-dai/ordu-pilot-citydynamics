package com.sampas.socbs.core.network.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphBuilder;
import com.sampas.socbs.core.network.INode;
/**
 * Basic implementation of GraphBuilder. This implementation of builder 
 * creates the graph when the builder is created. The underlying graph 
 * implementation makes copies of the references to the node and edge 
 * collections, not copies of the underlying collections themselves. In this
 * way as nodes and edges are added to the builder, it is reflected in the 
 * built graph.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/build/basic/BasicGraphBuilder.java $
 */
public class SmpBasicGraphBuilder implements IGraphBuilder {

  /** graph being built **/
  private IGraph m_graph;
  
  /** nodes of graph being built **/
  private HashSet m_nodes;
  
  /** edges of graph being built **/
  private HashSet m_edges;
  
  /**
   * Constructs a new empty graph builder.
   */
  public SmpBasicGraphBuilder () {
    m_nodes = new HashSet();
    m_edges = new HashSet();
    m_graph = buildGraph();
  }
  
  /**
   * @see IGraphBuilder#buildNode()
   */
  public INode buildNode() {
    return(new SmpBasicNode());  
  }

  /**
   * @see IGraphBuilder#buildEdge(INode, INode)
   */
  public IEdge buildEdge(INode nodeA, INode nodeB) {
    return(new SmpBasicEdge(nodeA,nodeB));
  }

  /**
   * @see IGraphBuilder#addNode(INode)
   */
  public void addNode(INode node) {
    m_nodes.add(node);  
  }

  /**
   * Checks for loops in which case it only added the edge to the adjacency 
   * list of one of the nodes (both of its nodes are the same node).
   * 
   * @see IGraphBuilder#addEdge(IEdge)
   */
  public void addEdge(IEdge edge) {
    edge.getNodeA().add(edge);
    
    //if the edge is a loop edge, which is legal, only add to node once
    if (!edge.getNodeA().equals(edge.getNodeB())) edge.getNodeB().add(edge);
    m_edges.add(edge);
  }

  /**
   * @see IGraphBuilder#removeNode(INode)
   */
  public void removeNode(INode node) {
    //prevents concurrent modification
    ArrayList toRemove = new ArrayList(node.getEdges());
    removeEdges(toRemove);
    m_nodes.remove(node);
  }

  /**
   * @see IGraphBuilder#removeNodes(Collection)
   */
  public void removeNodes(Collection nodes) {
    for (Iterator itr = nodes.iterator(); itr.hasNext();) {
      INode n = (INode)itr.next();
      removeNode(n);
    }
  }
  
  /**
   * @see IGraphBuilder#removeEdge(IEdge)
   */
  public void removeEdge(IEdge edge) {
    edge.getNodeA().remove(edge);
    edge.getNodeB().remove(edge);
    m_edges.remove(edge);
  }
  
  /**
   * @see IGraphBuilder#removeEdges(Collection)
   */
  public void removeEdges(Collection edges) {
    for (Iterator itr = edges.iterator(); itr.hasNext();) {
      IEdge e = (IEdge)itr.next();
      removeEdge(e);
    }  
  }
  
  /**
   * @see IGraphBuilder#getGraph() 
   */  
  public IGraph getGraph() {
    return(m_graph);  
  }
  
  /**
   * @see IGraphBuilder#clone(boolean)
   */
  public Object clone(boolean deep) throws Exception {
    IGraphBuilder builder = (IGraphBuilder)getClass().newInstance();
    if (deep) builder.importGraph(getGraph());
    
    return(builder);
  }

  /**
   * @see IGraphBuilder#importGraph(IGraph)
   */
  public void importGraph(IGraph g) {
    m_nodes = new HashSet(g.getNodes());
    m_edges = new HashSet(g.getEdges());
    m_graph = buildGraph();
  }

  /**
   * Returns the nodes belonging to the graph being built.
   * 
   * @return A collection of nodes.
   */
  public Collection getNodes() {
    return(m_nodes);  
  }
  
  /**
   * Returns the edges belonging to the graph being built.
   * 
   * @return A collection of edges.
   */
  public Collection getEdges() {
    return(m_edges);  
  }
  
  /**
   * Creates the underlying graph object.
   * 
   * @return A Graph object.
   */
  protected IGraph buildGraph() {
    return(new SmpBasicGraph(m_nodes, m_edges));
  }
}
