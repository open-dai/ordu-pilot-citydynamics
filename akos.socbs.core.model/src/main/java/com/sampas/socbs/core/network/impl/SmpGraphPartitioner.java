package com.sampas.socbs.core.network.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphTraversal;
import com.sampas.socbs.core.network.IGraphWalker;
import com.sampas.socbs.core.network.IGraphable;
import com.sampas.socbs.core.network.INode;
/**
 * Creates a collection of connected graphs from a single graph. A 
 * connected graph in which for every two pair of nodes, there is a path between
 * them.  
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/util/graph/GraphPartitioner.java $
 */
public class SmpGraphPartitioner implements IGraphWalker {

  /** graph to be partitioned into connected components **/
  private IGraph m_graph;
  
  /** paritions of graph **/
  private ArrayList m_partitions;
  
  /** current partition **/
  private ArrayList m_partition;
  
  /** visited counter **/
  private int m_nvisited;
  
  /**
   * Constructs a new partitioner for a graph.
   *  
   * @param graph Graph to be partitioned.
   */
  public SmpGraphPartitioner(IGraph graph) {
    m_graph = graph;
    m_partitions = new ArrayList();
  }
  
  /**
   * Performs the partition.
   * 
   * @return True if the partition was successful, otherwise false.
   */
  public boolean partition() {
    //strategy is to perform a depth first search from a node, every node is 
    // reaches is connected therefore in the same partition
    // when traversal ends, start from a new source, repeat until no more 
    // sources
    
    try {
      m_nvisited = m_graph.getNodes().size();
      
      SmpDepthFirstIterator iterator = new SmpDepthFirstIterator();
      SmpBasicGraphTraversal traversal = new SmpBasicGraphTraversal(
        m_graph, this, iterator
      );
      
      Iterator sources = m_graph.getNodes().iterator();
      
      traversal.init();
      m_partition = new ArrayList();
      
      while(m_nvisited > 0) {
        
        //find a node that hasn't been visited and set as source of traversal
        INode source = null;
        while(sources.hasNext() && (source = (INode)sources.next()).isVisited());
       
        //if we could not find a source, return false
        if (source == null || source.isVisited()) return(false);
        
        iterator.setSource(source);
        traversal.traverse();
      }
      
      //create the individual graphs
      HashSet nodes = null;
      HashSet edges = null;
      ArrayList graphs = new ArrayList();
      
      for (Iterator itr = m_partitions.iterator(); itr.hasNext();) {
        m_partition = (ArrayList)itr.next();
        if (m_partition.size() == 0) continue;
        
        nodes = new HashSet();
        edges = new HashSet();
        for (Iterator nitr = m_partition.iterator(); nitr.hasNext();) {
          INode node = (INode)nitr.next();
          nodes.add(node);
          edges.addAll(node.getEdges());
        } 
        
        graphs.add(new SmpBasicGraph(nodes, edges));
      }
      
      m_partitions = graphs;
      
      return(true);
    }
    catch(Exception e) {
      e.printStackTrace();  
    }
    return(false);
  }
  
  /**
   * Returns the partitions of the graph.
   * 
   * @return A collection of Graph objects.
   * 
   * @see IGraph
   */
  public List getPartitions() {
    return(m_partitions);  
  }
  
  /**
   * Adds the element to the current partition.
   * 
   * @see IGraphWalker#visit(IGraphable, IGraphTraversal)
   */
  public int visit(IGraphable element, IGraphTraversal traversal) {
    //add element to current set
    m_nvisited--;
    m_partition.add(element);
    return(IGraphTraversal.CONTINUE);
  }

  /**
   * Adds the current partition to the completed set of partitions and 
   * creates a new partition.
   * 
   * @see IGraphWalker#finish()
   */
  public void finish() {
    //create a new set
    m_partitions.add(m_partition);
    m_partition = new ArrayList();
  }
}
