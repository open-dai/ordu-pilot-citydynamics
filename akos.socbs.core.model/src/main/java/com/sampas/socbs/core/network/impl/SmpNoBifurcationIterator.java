package com.sampas.socbs.core.network.impl;

import java.util.Iterator;

import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphTraversal;
import com.sampas.socbs.core.network.IGraphable;
import com.sampas.socbs.core.network.INode;
/**
 * Iterates over the nodes of a graph starting from a specified node, stopping 
 * at a bifurcation. A <B>bifurcation</B> is defined as a node of degree > 2. 
 * The following figures illustrate examples of the iterator.<BR>
 * <BR>
 * <IMG src="doc-files/nobif_0.gif"/><BR>
 * <BR>
 * <BR>
 * <BR>
 * <IMG src="doc-files/nobif_1.gif"/><BR>
 * <BR>
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/standard/NoBifurcationIterator.java $
 */
public class SmpNoBifurcationIterator extends SmpSourceGraphIterator {
  
  /** the next node to be returned by the iterator **/
  private INode m_next;
  
  /**
   * Does nothing. 
   * 
   * @see IGraphIterator#init(IGraph)
   */
  public void init(IGraph graph, IGraphTraversal traversal) {
    //do nothing
  }

  /**
   * Sets the source of the traversal. This must be a node of degree less than
   * or equal to 2. 
   * 
   * @param source node of degree less than or equal 2
   * 
   * @see SmpSourceGraphIterator#setSource(IGraphable)
   * @throws IllegalStateException
   */
  public void setSource(IGraphable source) {
    //check that source node if of degree <= 2
    if (((INode)source).getDegree() > 2) {
      throw new IllegalStateException(
        "Cannot start a no bifurcation traversal  on a node that " +
        "bifurcates."
      );   
    }
    super.setSource(source);
    m_next = (INode)getSource();
  }

  /**
   * The next node in the iteration is the first node found adjacent to the 
   * current node that is non visited and of degree less than 2.
   * 
   * @see org.geotools.graph.traverse.GraphIterator#next()
   */
  public IGraphable next(IGraphTraversal traversal) {
    return(m_next);  
  }
  
  /**
   * Searches for the next node to be returned in the iteration. The next node 
   * is the first node (of two) related to the current node that is non visited
   * and of degree <= 2.
   * 
   * @see org.geotools.graph.traverse.GraphIterator#cont(IGraphable)
   */
  public void cont(IGraphable current, IGraphTraversal traversal) {
    //find a related node that is non visited and degree <= 2
    Iterator itr = current.getRelated();
    m_next = (INode)itr.next();
    if (itr.hasNext() && (traversal.isVisited(m_next) || m_next.getDegree() > 2)) 
      m_next = (INode)itr.next();
    if (traversal.isVisited(m_next) || m_next.getDegree() > 2) m_next = null;
  }

  /**
   * Kills the current branch of the iteration by explicitly setting the next
   * node to be returned to null. This call always ends the traversal.
   * 
   * @see org.geotools.graph.traverse.GraphIterator#killBranch(IGraphable)
   */
  public void killBranch(IGraphable current, IGraphTraversal traversal) {
    m_next = null;
  }
}
