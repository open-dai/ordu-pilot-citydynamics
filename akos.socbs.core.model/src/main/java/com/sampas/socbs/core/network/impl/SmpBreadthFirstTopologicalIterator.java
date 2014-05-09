package com.sampas.socbs.core.network.impl;

import java.util.Iterator;

import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphTraversal;
import com.sampas.socbs.core.network.IGraphVisitor;
import com.sampas.socbs.core.network.IGraphable;
import com.sampas.socbs.core.network.INode;
import com.sampas.socbs.core.network.IQueue;
/**
 * Iterates over the nodes of a graph in <B>Breadth First Topological Sort</B> 
 * pattern. The following is an illustration of the iteration.<BR>
 * <IMG src="doc-files/breadth_topo.gif"><BR>
 * <BR>
 * Initially all nodes of degree less than two are <B>active</B> 
 * (ready to be visited). As nodes are visited, a node can become active 
 * when all but one of its related nodes have been visited (
 * degree = counter + 1). When a node becomes active it is placed into the
 * <B>active node queue</B> (queue of nodes to be visited). 
 * The Breadth First Topological iterator places
 * nodes into the queue in <B>First In First Out</B> order.<BR>
 * <BR>
 * To determine when a node is to become active the iterator uses the counter
 * associated with each node. If these counters are modified by an entity 
 * other then the iterator, the iteration may be affected in undefined ways. 
 *  
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/standard/BreadthFirstTopologicalIterator.java $
 */
public class SmpBreadthFirstTopologicalIterator extends SmpAbstractGraphIterator {

  /** Queue of active nodes **/
  private IQueue m_queue;
  
  /**
   * Creates the active queue, and populates it with all nodes of degree less
   * than 2. Counters of all nodes are also reset to 0.
   * 
   * @see org.geotools.graph.traverse.GraphIterator#init(IGraph)
   */
  public void init(IGraph graph, IGraphTraversal traversal) {
    //create queue
    m_queue = buildQueue(graph);
    
    //initialize nodes
    graph.visitNodes(
      new IGraphVisitor() {
        public int visit(IGraphable component) {
          INode node = (INode)component;
          
          //reset counter to zero
          node.setCount(0);
          
          if (node.getDegree() < 2) m_queue.enq(node);
          
          return(0);  
        }
      } 
    );    
  }

  /**
   * Returns the next node in the active node queue.
   * 
   * @see org.geotools.graph.traverse.GraphIterator#next()
   */
  public IGraphable next(IGraphTraversal traversal) {
    return(!m_queue.isEmpty() ? (IGraphable)m_queue.deq() : null); 
  }

  /**
   * Continues the iteration by incrementing the counters of any unvisited 
   * nodes related to the current node. If any related nodes have counters 
   * equal to degree of that node - 1, it is placed into the active queue.
   * 
   * @see org.geotools.graph.traverse.GraphIterator#cont(IGraphable)
   */
  public void cont(IGraphable current, IGraphTraversal traversal) {
    //increment the count of all adjacent nodes by one
    // if the result count is 1 less than the degree, place it into the queue
    for (Iterator itr = current.getRelated(); itr.hasNext();) {
      INode related = (INode)itr.next();
      if (!traversal.isVisited(related)) {
        related.setCount(related.getCount()+1);  
        if (related.getDegree()-1 == related.getCount()) m_queue.enq(related);
      }  
    }
  }

  /**
   * Kills the current branch of the traversal by not incremening the counters
   * of any related nodes.
   * 
   * @see org.geotools.graph.traverse.GraphIterator#killBranch(IGraphable) 
   */
  public void killBranch(IGraphable current, IGraphTraversal traversal) {
    //do nothing
  }
  
  /**
   * Builds the active node queue. 
   * 
   * @param graph The Graph whose components are being iterated over.
   * 
   * @return A first in first out queue
   */
  protected IQueue buildQueue(IGraph graph) {
    return(new SmpFIFOQueue(graph.getNodes().size())); 
  }
}
