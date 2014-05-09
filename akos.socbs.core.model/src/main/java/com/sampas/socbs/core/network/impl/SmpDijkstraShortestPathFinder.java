package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphTraversal;
import com.sampas.socbs.core.network.IGraphWalker;
import com.sampas.socbs.core.network.IGraphable;
import com.sampas.socbs.core.network.impl.SmpDijkstraIterator.EdgeWeighter;

/**
 * Calculates node paths in a graph using Dijkstra's Shortest Path Algorithm. 
 * Dijsktras algorithm calculates a shortest path from a specefied node (the 
 * source node of the underlying dijkstra iteration) to every other node in the 
 * graph. 
 *  
 * @see DijsktraIterator 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/path/DijkstraShortestPathFinder.java $
 */
public class SmpDijkstraShortestPathFinder implements IGraphWalker {
  
  /** Graphs to calculate paths for **/
  private IGraph m_graph;
  
  /** Graph traversal used for the dijkstra iteration **/
  private IGraphTraversal m_traversal;
  
  /** Underling Dijkstra iterator **/
  private SmpDijkstraIterator m_iterator;
  
  /**
   * Constructs a new path finder.
   * 
   * @param graph The graph to calculate paths for.
   * @param iterator The dijsktra iterator to used to calculate shortest paths.
   */
  public SmpDijkstraShortestPathFinder(IGraph graph, SmpDijkstraIterator iterator) {
    m_graph = graph;
    m_iterator = iterator;
    m_traversal = new SmpBasicGraphTraversal(graph, this, iterator);
  }
        
  /**
   * Constructs a new path finder.
   * 
   * @param graph Graph to calculate paths for.
   * @param source Node to calculate paths from.
   * @param weighter Associates weights with edges in the graph.
   */
  public SmpDijkstraShortestPathFinder(
    IGraph graph, IGraphable source, EdgeWeighter weighter
  ) {
    m_graph = graph;
    m_iterator = new SmpDijkstraIterator(weighter);
    m_iterator.setSource(source);
    m_traversal = new SmpBasicGraphTraversal(graph, this, m_iterator); 
  }
  
  /**
   * Performs the graph traversal and calculates the shortest path from 
   * the source node to every other node in the graph.
   */
  public void calculate() {
    m_traversal.init();
    m_traversal.traverse(); 
  }
  
  /**
   * Returns a path <B>from</B> g <B>to</B> the source. If the desired path is
   * the opposite (from the source to g) can be used.
   * 
   * @param g The start node of the path to be calculated.
   * 
   * @see SmpPath#riterator()
   * 
   * @return A path from g to the source.
   */
  public SmpPath getPath(IGraphable g) {
    SmpPath p = new SmpPath();
    p.add(g);
    
    IGraphable parent = g;
    while((parent = m_iterator.getParent(parent)) != null) p.add(parent);
    
    if (!p.getLast().equals(m_iterator.getSource())) return(null);
    
    return(p);
  }

  /**
   * Returns the cost associated with a node calculated during the graph 
   * traversal.
   * 
   * @param g The node whose cost is desired.
   * 
   * @return The cost associated with the node.
   */
  public double getCost(IGraphable g) {
    return(m_iterator.getCost(g));
  }
  
  public SmpDijkstraIterator getIterator() {
    return(m_iterator);  
  }
  
  public IGraphTraversal getTraversal() {
    return(m_traversal);  
  }
  
  /**
   * Does nothing except signal the traversal to continue.
   * 
   * @see IGraphWalker#visit(IGraphable, IGraphTraversal)
   */
  public int visit(IGraphable element, IGraphTraversal traversal) {
    return(IGraphTraversal.CONTINUE);
  }
  
  /**
   * Does nothing.
   * 
   * @see IGraphWalker#finish()
   */
  public void finish() {}
}
