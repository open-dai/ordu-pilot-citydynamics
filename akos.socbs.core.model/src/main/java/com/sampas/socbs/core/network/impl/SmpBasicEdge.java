package com.sampas.socbs.core.network.impl;

import java.util.ArrayList;
import java.util.Iterator;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.INode;

/**
 * Basic implementation of Edge.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/basic/BasicEdge.java $
 */
public class SmpBasicEdge extends SmpBasicGraphable implements IEdge {

  /** A node */
  private INode m_nodeA;
  
  /** B node */
  private INode m_nodeB;
  
  /**
   * Constructs a new edge.
   * 
   * @param nodeA A node of edge.
   * @param nodeB B node of edge.
   */
  public SmpBasicEdge(INode nodeA, INode nodeB) {
    super();
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
   * Returns null if the specified node is neither the A node or the B node.
   * 
   * @see IEdge#getOtherNode(INode)
   */
  public INode getOtherNode(INode node) {
    if (node.equals(m_nodeA)) return(m_nodeB);
    if (node.equals(m_nodeB)) return(m_nodeA);
    return(null);
  }

   /** 
   * Returns all edges that are adjacent to both the A and B nodes.  This
   * iterator is generated by calculating an underlying collection upon each 
   * method call. 
   *
   * @see org.geotools.graph.structure.Graphable#getRelated()
   */
  public Iterator getRelated() {
  	ArrayList adj = new ArrayList();
    
    for (Iterator itr = m_nodeA.getEdges().iterator(); itr.hasNext();) {
      IEdge e = (IEdge)itr.next();
      switch(e.compareNodes(this)) {
        case EQUAL_NODE_ORIENTATION: //same node orientation
          if (e.equals(this)) continue;
        case OPPOSITE_NODE_ORIENTATION: //opposite node orientation
        case UNEQUAL_NODE_ORIENTATION: //different
          adj.add(e);  	
      }
    }
    
    for (Iterator itr = m_nodeB.getEdges().iterator(); itr.hasNext();) {
      IEdge e = (IEdge)itr.next();
      switch(e.compareNodes(this)) {
        case EQUAL_NODE_ORIENTATION: 
        case OPPOSITE_NODE_ORIENTATION: 
          continue; //edges already added from other node
        case UNEQUAL_NODE_ORIENTATION:
          adj.add(e);  	
      }
    }
    
    return(adj.iterator());
  }

  /**
   * @see IEdge#reverse()
   */
  public void reverse() {
    INode n = m_nodeA;
    m_nodeA = m_nodeB;
    m_nodeB = n;
  }
  
  /**
   * @see IEdge#compareNodes(IEdge)
   */
  public int compareNodes(IEdge other) {
    if(m_nodeA.equals(other.getNodeA()) && m_nodeB.equals(other.getNodeB())) 
      return(EQUAL_NODE_ORIENTATION);
    
    if(
      (m_nodeA.equals(other.getNodeA()) && m_nodeB.equals(other.getNodeB()))||
      (m_nodeA.equals(other.getNodeB()) && m_nodeB.equals(other.getNodeA()))
    ) return(OPPOSITE_NODE_ORIENTATION);
  
    return(UNEQUAL_NODE_ORIENTATION);
  }
  
  /**
   * Returns ([A node.toString()],[B node.toString()]). 
   */
  public String toString() {
    return(super.toString()+" ("+m_nodeA.getID()+","+m_nodeB.getID()+")");  
  }
}