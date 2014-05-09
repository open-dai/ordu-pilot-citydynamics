package com.sampas.socbs.core.network.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import com.sampas.socbs.core.network.IDirectedEdge;
import com.sampas.socbs.core.network.IDirectedNode;
import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.INode;

/**
 * Basic implementation of DirectedEdge.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/basic/BasicDirectedEdge.java $
 */
public class SmpBasicDirectedEdge extends SmpBasicGraphable implements IDirectedEdge {

  /** in node **/
  private IDirectedNode m_in;
  
  /** out node **/
  private IDirectedNode m_out;
  
  /** Contstructs a new DirectedEdge.
   * 
   * @param in The in node of the edge.
   * @param out The out node of the edge.
   */
  public SmpBasicDirectedEdge(IDirectedNode in, IDirectedNode out) {
    super();
    m_in = in;
    m_out = out;
  }
  
  /**
   * @see IDirectedEdge#getInNode()
   */
  public IDirectedNode getInNode() {
    return(m_in);
  }


  /**
   * @see IDirectedEdge#getOutNode()
   */
  public IDirectedNode getOutNode() {
    return(m_out);
  }

  /**
   * Returns the in node.
   * 
   * @see IEdge#getNodeA()
   */
  public INode getNodeA() {
    return(m_in);
  }

  /**
   * Returns the out node.
   * 
   * @see IEdge#getNodeB()
   */
  public INode getNodeB() {
    return(m_out);
  }

  /**
   * @see IEdge#getOtherNode(INode)
   */
  public INode getOtherNode(INode node) {
    return(m_in.equals(node) ? m_out : m_out.equals(node) ? m_in : null);
  }

  /**
   * Removes the edge from the out list of the in node and from the in list of
   * the out node. Nodes are switched and then the edge is added to the in list
   * of the new out node, and to the out list of the new in node.
   * 
   * @see IEdge#reverse()
   * 
   */
  public void reverse() {
    //remove edge from adjacent nodes
    m_in.removeOut(this);
    m_out.removeIn(this);
    
    //swap nodes
    IDirectedNode tmp = m_in;
    m_in = m_out;
    m_out = tmp;
   
    //re add nodes
    m_in.addOut(this);
    m_out.addIn(this);
  }

  /**
   * Returns an iterator over all edges incident to both the in and out nodes.
   * 
   * @see org.geotools.graph.structure.Graphable#getRelated()
   */
  public Iterator getRelated() {
    HashSet related = new HashSet();
    
    //add all edges incident to both nodes
    related.addAll(m_in.getEdges());
    related.addAll(m_out.getEdges());
    
    //remove this edge
    related.remove(this);
    
    return(related.iterator());
    
//    
//    ArrayList related = new ArrayList();
//    
//    related.addAll(m_in.getInEdges());
//    
//    //add out edges, look for an opposite edge, it will have already 
//    // been added so dont add it
//    for (Iterator itr = m_out.getOutEdges().iterator(); itr.hasNext();) {
//      DirectedEdge de = (DirectedEdge)itr.next();
//      switch(de.compareNodes(this)) {
//        case OPPOSITE_NODE_ORIENTATION: continue;
//      }
//      related.add(de);	
//    }
//    
//    //look for duplicate edges (same direction) if not equal add
//    // dont add opposite edges
//    // dont add loops
//    for (Iterator itr = m_in.getOutEdges().iterator(); itr.hasNext();) {
//      DirectedEdge de = (DirectedEdge)itr.next();
//      switch(de.compareNodes(this)) {
//      	case EQUAL_NODE_ORIENTATION:
//      	  if (!de.equals(this)) 
//      	    related.add(de);
//      	  continue;
//        case OPPOSITE_NODE_ORIENTATION: 
//          continue;
//        case UNEQUAL_NODE_ORIENTATION: 
//          if (de.getNodeA().equals(de.getNodeB())) continue;
//          related.add(de);
//      }
//    }
//    
//    return(related.iterator());
  }
  
  /**
   * Returns an iterator over the in edges of the in node.
   * 
   * @see org.geotools.graph.structure.DirectedGraphable#getInRelated()
   */
  public Iterator getInRelated() {
    ArrayList in = new ArrayList();
    for (Iterator itr = m_in.getInEdges().iterator(); itr.hasNext();) {
      IDirectedEdge de = (IDirectedEdge)itr.next();
      //this check has to be because the edge could be a loop in which case
      // it is in related to itself
      if (!de.equals(this)) in.add(de);  
    }
    return(in.iterator());  
  }

  /**
   * Returns an iterator over the out edges of the out node.
   * 
   * @see org.geotools.graph.structure.DirectedGraphable#getOutRelated() 
   */
  public Iterator getOutRelated() {
     ArrayList out = new ArrayList();
    for (Iterator itr = m_out.getOutEdges().iterator(); itr.hasNext();) {
      IDirectedEdge de = (IDirectedEdge)itr.next();
      //this check has to be because the edge could be a loop in which case
      // it is in related to itself
      if (!de.equals(this)) out.add(de);  
    }
    return(out.iterator());    
  }

  /**
   * @see IEdge#compareNodes(IEdge)
   */
  public int compareNodes(IEdge other) {
    if (other instanceof IDirectedEdge) {
      IDirectedEdge de = (IDirectedEdge)other;
      if (de.getInNode().equals(m_in) && de.getOutNode().equals(m_out)) 
        return(EQUAL_NODE_ORIENTATION);
      if (de.getInNode().equals(m_out) && de.getOutNode().equals(m_in)) 
        return(OPPOSITE_NODE_ORIENTATION);	
    }
    return(UNEQUAL_NODE_ORIENTATION);
  }
}
