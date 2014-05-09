package com.sampas.socbs.core.network.impl;

import java.util.ArrayList;
import java.util.Iterator;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.INode;
/**
 * Optimized implementation of Edge. 
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 * @see IEdge
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/opt/OptEdge.java $
 */
public class SmpOptEdge extends SmpOptGraphable implements IEdge {

  /** a node **/
  private SmpOptNode m_nodeA;
  
  /** b node **/
  private SmpOptNode m_nodeB;
  
  /**
   * Constructs a new optimized edge.
   * 
   * @param nodeA A node of edge.
   * @param nodeB B node of edge.
   */
  public SmpOptEdge(SmpOptNode nodeA, SmpOptNode nodeB) {
    m_nodeA = nodeA;
    m_nodeB = nodeB;
  }
  
  /**
   * @see IEdge#getNodeA()
   */
  public INode getNodeA() {
    return(m_nodeA);
  }

  /**
   * @see IEdge#getNodeB()
   */
  public INode getNodeB() {
    return(m_nodeB);
  }

  /**
   * @see IEdge#getOtherNode(INode)
   */
  public INode getOtherNode(INode node) {
    return(
      m_nodeA.equals(node) ? m_nodeB : m_nodeB.equals(node) ? m_nodeA : null
    );
  }

  /**
   * @see IEdge#reverse()
   */
  public void reverse() {
    SmpOptNode tmp = m_nodeA;
    m_nodeA = m_nodeB;
    m_nodeB = tmp;
  }

  /**
   * @see IEdge#compareNodes(IEdge)
   */
  public int compareNodes(IEdge other) {
    if (m_nodeA.equals(other.getNodeA()) && m_nodeB.equals(other.getNodeB()))
      return(IEdge.EQUAL_NODE_ORIENTATION);
      
    if (m_nodeB.equals(other.getNodeA()) && m_nodeA.equals(other.getNodeB()))
      return(IEdge.OPPOSITE_NODE_ORIENTATION);
      
    return(IEdge.UNEQUAL_NODE_ORIENTATION);
  }

  /**
   * @see org.geotools.graph.structure.Graphable#getRelated()
   */
  public Iterator getRelated() {
   return(new RelatedIterator(this));  
  }
  
  public class RelatedIterator implements Iterator {

    private Iterator m_itr;
    
    public RelatedIterator(SmpOptEdge edge) {
      ArrayList edges = new ArrayList(
        m_nodeA.getDegree() + m_nodeB.getDegree() - 2 - 
        m_nodeA.getEdges(m_nodeB).size()
      );  
      
      //add all edges of node A except this edge
      for (int i = 0; i < m_nodeA.getEdgeArray().length; i++) {
        IEdge e = (IEdge)m_nodeA.getEdgeArray()[i];
        if (!e.equals(edge)) edges.add(m_nodeA.getEdgeArray()[i]);  
      }
      
      //add only edges from node b that are node shared with node a
      for (int i = 0; i < m_nodeB.getEdgeArray().length; i++) {
        IEdge e = (IEdge)m_nodeB.getEdgeArray()[i];
        if (!e.getOtherNode(m_nodeB).equals(m_nodeA)) edges.add(e);
      }
      
      m_itr = edges.iterator(); 
    }
    
    public void remove() {
      throw new UnsupportedOperationException(
        getClass().getName() + "#remove() not supported."
      ); 
    }

    public boolean hasNext() {
      return(m_itr.hasNext()); 
    }

    public Object next() {
      return(m_itr.next());
    }
    
  }
}
