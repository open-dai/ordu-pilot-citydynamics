package com.sampas.socbs.core.network.impl;

import java.util.Iterator;

import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphTraversal;
import com.sampas.socbs.core.network.IGraphable;
import com.sampas.socbs.core.network.IQueue;
/**
 * Iterates over the nodes of a graph in a <B>Breadth First Search</B> pattern 
 * starting from a specified node. The following illustrates the iteration 
 * order. <BR>
 * <BR>
 * <IMG src="doc-files/bfs.gif"/><BR>
 * <BR>
 * The iteration operates by maintaning a node queue of <B>active</B> nodes.  
 * An <B>active</B> node is a node that will returned at a later stage of the i
 * teration. The node queue for a Breadth First iteration is implemented as a 
 * <B>First In First Out</B> queue.
 * A node is placed in the the node queue if it has not been visited, and 
 * it is adjacent to a a node that has been visited. The node queue intially 
 * contains only the source node of the traversal.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/standard/BreadthFirstIterator.java $
 */
public class SmpBreadthFirstIterator extends SmpSourceGraphIterator {

  /** Contains all nodes to be returned **/
  private IQueue m_active;
  
  /**
   * Sets the source of the traversal and places it in the node queue. The first 
   * call to this method will result in the internal node queue being built. 
   * Subsequent calls to the method clear the node queue and reset the iteration. 
   * 
   * @see SmpSourceGraphIterator#setSource(IGraphable)
   */
  public void setSource(IGraphable source) {
    super.setSource(source);
    
    //set source of traversal, creating queue if necessary
    if (m_active == null) m_active = buildQueue(getGraph());
    else if (m_active.isEmpty()) m_active.clear();
     
    m_active.enq(getSource());
  }

  /**
   * Does nothing. 
   * 
   * @see org.geotools.graph.traverse.GraphIterator#init(IGraph)
   */
  public void init(IGraph graph, IGraphTraversal traversal) { 
    //do nothing
  }
  
  /**
   * Returns the next node from the node queue that has not yet been visited. It
   * is possible for the node queue to contain duplicate entries. To prevent
   * the iteration returning the same node multiple times, the visited flag is
   * checked on nodes coming out of the queue. If the flag is set, the node 
   * is ignored, not returned, and the next node in the queue is returned. This
   * is however tranparent to the caller.
   * 
   * @see org.geotools.graph.traverse.GraphIterator#next()
   */
  public IGraphable next(IGraphTraversal traversal) {
    while(!m_active.isEmpty()) {
      IGraphable next = (IGraphable)m_active.deq();
      if (!traversal.isVisited(next)) return(next);  
    }
    return(null);
  }

  /**
   * Looks for nodes adjacent to the current node to place into the node queue. 
   * An adjacent node is only placed into the node queue if its visited flag
   * is unset.
   * 
   * @see org.geotools.graph.traverse.GraphIterator#cont(IGraphable)
   */
  public void cont(IGraphable current, IGraphTraversal traversal) {
    for (Iterator itr = current.getRelated(); itr.hasNext();) {
      IGraphable related = (IGraphable)itr.next();
      if (!traversal.isVisited(related)) {
        m_active.enq(related);  
      }      
    }
  }

  /**
   * Kills the current branch by not looking for any adjacent nodes to place
   * into the node queue.
   * 
   * @see org.geotools.graph.traverse.GraphIterator#killBranch(IGraphable)
   */
  public void killBranch(IGraphable current, IGraphTraversal traversal) {
    //do not look for any adjacent nodes to place into the active queue
  }
  
  /**
   * Builds the node queue for the iteration.
   * 
   * @param graph The graph being iterated over.
   *
   * @return A First In First Out queue.
   */
  protected IQueue buildQueue(IGraph graph) {
    return(new SmpFIFOQueue(graph.getNodes().size()));
  }
  
  /**
   * Returns the node queue.
   * 
   * @return The node queue.
   */
  protected IQueue getQueue() {
    return(m_active);  
  }
  
}
