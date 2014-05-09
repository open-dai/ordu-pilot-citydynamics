package com.sampas.socbs.core.network;

import java.util.Collection;
import java.util.List;

/**
 * 
 * Represents a graph. 
 * 
 * A graph is a collection of nodes (verticies) connected by links called 
 * edges (arcs). <BR>
 * <BR>
 * In most applications nodes of a graph represent the 
 * objects being modelled, and the edges represent the 
 * relationships between the objects. An example could be a polygon coverage in 
 * which one wishes to model a boundary sharing relationship. The following is 
 * an illustration.<BR>
 * <BR>
 * <IMG src="doc-files/poly_coverage.gif"><BR>
 * <BR>
 * In the above figure, the objects (nodes) are the polygons themselves, and the 
 * relationship (edges) between them is boundary sharing.<BR>
 * <BR>
 * However, there exists types of graphs in which the roles are reversed and the 
 * edges are the objects, and the nodes are the relationships. An example of 
 * such a graph is the stream network shown below.<BR>
 * <BR>
 * <IMG src="doc-files/stream_network.gif"><BR>
 * <BR>
 * In the above figure, the objects (edges) are the stream segments and the 
 * relationship (nodes) between them is endpoint sharing. However, if 
 * desirable one could model the second case similar to the first. The 
 * resulting graph is shown below.<BR>
 * <BR>
 * <IMG src="doc-files/stream_network2.gif"><BR>
 * <BR>
 * The Graph object is intended to serve as a container for a collection
 * of nodes and edges. It does dont define or manage the relationship among the 
 * components it contains.
 *
 * @see INode
 * @see IEdge
 *  
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/Graph.java $
 */
public interface IGraph {
  
  /** 
   * Signal to indicate that a graph component meets the requirements of a 
   *  query against a graph and that the query should continue.
   */
  public static final int PASS_AND_CONTINUE = 1;
  
  /**
   * Signal to indicate that a graph component meets the requirements of a 
   *  query against a graph and that the query should end.
   */
  public static final int PASS_AND_STOP = 2;
  
  /** 
   * Signal to indicate that a graph component does NOT meet the requirements 
   * of a query made against the graph. 
   */
  public static final int FAIL_QUERY = 0;
  
  /**
   * Returns the nodes of the graph.
   *
   * @return A collection of Node objects.
   *
   * @see INode
   */
  public Collection getNodes();

  /**
   * Returns the edges of the graph.
   *
   * @return A collection of Edge objects.
   *
   * @see IEdge
   */
  public Collection getEdges();

  /**
   * Performs a query against the nodes of the graph. Each Node object
   * contained in the graph is passed to a GraphVisitor to determine if it
   * meets the query criteria.
   *
   * @param visitor Determines if node meets query criteria. 
   * Returns MEET_AND_CONTINUE to signal that the node meets the query criteria 
   * and the query should continue.<BR>
   * Returns MEET_AND_STOP to signal that the node meest the query criteria and
   * the query should stop.<BR>
   * FAIL_QUERY to signal that the node does NOT meet the query criteria.
   *
   * @return A collection of nodes that meet the query criteria.
   *
   * @see INode
   * @see IGraphVisitor
   */
  public List queryNodes(IGraphVisitor visitor);

  /**
   * Performs a query against the edges of the graph. Each Edge object
   * contained in the graph is passed to a GraphVisitor to determine if it
   * meets the query criteria.
   *
   * @param visitor Determines if the meets the query criteria. <BR>
   * Returns MEET_AND_CONTINUE to signal that the edge meets the query criteria 
   * and the query should continue.<BR>
   * Returns MEET_AND_STOP to signal that the edge meest the query criteria and
   * the query should stop.<BR>
   * FAIL_QUERY to signal that the edge does NOT meet the query criteria.
   *
   * @return A collection of edges that meet the query criteria.
   *
   * @see IEdge
   * @see IGraphVisitor
   */
  public List queryEdges(IGraphVisitor visitor);
  

  /** 
   * Applies the visitor pattern to the nodes of the graph.
   * 
   * @param visitor
   */
	public void visitNodes(IGraphVisitor visitor);
  
  /**
   * Applies the visitor pattern to the edges of the graph.
   * 
   * @param visitor
   */
  public void visitEdges(IGraphVisitor visitor);

  /**
   * Returns all the nodes in the graph of a specified degree. The degree of 
   * a node is the number of edges that are adjacent to the node.
   *
   * @param n The desired degree of nodes to be returned.
   *
   * @return A collection of nodes of degree n.
   * 
   * @see INode#getDegree()
   */
  public List getNodesOfDegree(int n);

  /**
   * Returns all the nodes in the graph that have been marked as visited or
   * non-visited.
   *
   * @param visited True if node is visited, false if node is unvisited.
   *
   * @return List of nodes marked as visited / non-visited.
   * 
   * @see IGraphable#isVisited()
   */
  public List getVisitedNodes(boolean visited);

  /**
   * Returns all the edges in the graph that have been marked as visited or
   * non-visited.
   *
   * @param visited True if edge is visited, false if edge is unvisited.
   *
   * @return List of edges marked as visited / non-visited.
   * 
   * @see IGraphable#isVisited()
   */
  public List getVisitedEdges(boolean visited);
  
}
