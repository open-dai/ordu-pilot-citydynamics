package com.sampas.socbs.core.network.impl;

import java.util.ArrayList;
import java.util.Iterator;

import com.sampas.socbs.core.network.IDirectedEdge;
import com.sampas.socbs.core.network.IDirectedNode;
import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.INode;
/**
 * Optimized implementation of DirectedEdge.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @see IDirectedEdge
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/opt/OptDirectedEdge.java $
 */
public class SmpOptDirectedEdge extends SmpOptGraphable implements IDirectedEdge {

  /** in node **/
  private SmpOptDirectedNode m_in;
  
  /** out node **/
  private SmpOptDirectedNode m_out;
  
  /** 
   * Constructs a new OptDirectedEdge.
   * 
   * @param in Optimized in node.
   * @param out Optimized out node.
   */
  public SmpOptDirectedEdge(SmpOptDirectedNode in, SmpOptDirectedNode out) {
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
   * @see IEdge#getNodeA()
   */
  public INode getNodeA() {
    return(m_in);
  }

  /**
   * @see IEdge#getNodeB()
   */
  public INode getNodeB() {
    return(m_out);
  }

  /**
   * @see IEdge#getOtherNode(INode)
   */
  public INode getOtherNode(INode node) {
    return(node == m_in ? m_out : node == m_out ? m_in : null); 
  }

  /**
   * Unsupported Operation.
   * 
   * @throws UnsupportedOperationException
   */
  public void reverse() {
    throw new UnsupportedOperationException(
      getClass().getName() + "#reverse()"   
    );
  }

  /**
   * @see IEdge#compareNodes(IEdge)
   */
  public int compareNodes(IEdge other) {
    if (m_in.equals(other.getNodeA()) && m_out.equals(other.getNodeB()))
      return(IEdge.EQUAL_NODE_ORIENTATION);
      
    if (m_in.equals(other.getNodeB()) && m_out.equals(other.getNodeA())) 
      return(IEdge.OPPOSITE_NODE_ORIENTATION);
      
    return(IEdge.UNEQUAL_NODE_ORIENTATION);
  }

  /**
   * @see IGraphable#getRelated()
   */
  public Iterator getRelated() {
    ArrayList related = new ArrayList(
      m_in.getDegree() + m_out.getDegree() - 2 
    );
    
    IEdge[] edges = m_in.getInEdgeArray();
    for (int i = 0; i < edges.length; i++) {
      related.add(edges[i]);  
    }
    
    edges = m_in.getOutEdgeArray();
    for (int i = 0; i < edges.length; i++) {
      IEdge e = edges[i]; 
      if (!e.equals(this) && !(e.getNodeA().equals(e.getNodeB()))) 
        related.add(edges[i]);  
    }
    
    edges = m_out.getInEdgeArray();
    for (int i = 0; i < edges.length; i++) {
      IEdge e = edges[i];
      
      switch(compareNodes(e)) {
        case IEdge.EQUAL_NODE_ORIENTATION:
        case IEdge.OPPOSITE_NODE_ORIENTATION:
          continue; //already added
        
        case IEdge.UNEQUAL_NODE_ORIENTATION:
          related.add(e);
      }  
    }
    
    edges = m_out.getOutEdgeArray();
    for (int i = 0; i < edges.length; i++) {
      IEdge e = edges[i];
      
      switch(compareNodes(edges[i])) {
        case IEdge.EQUAL_NODE_ORIENTATION:
        case IEdge.OPPOSITE_NODE_ORIENTATION:
          continue; //already added
        
        case IEdge.UNEQUAL_NODE_ORIENTATION:
          if (!e.getNodeA().equals(e.getNodeB())) related.add(e);
      }  
    }
    
    return(related.iterator());
  }
  
  /**
   * @see IDirectedGraphable#getInRelated()
   */
  public Iterator getInRelated() {
    return(new RelatedIterator(RelatedIterator.IN));  
  }

  /**
   * @see IDirectedGraphable#getOutRelated()
   */
  public Iterator getOutRelated() {
    return(new RelatedIterator(RelatedIterator.OUT));
  }
  
  /**
   * Iterator used to iterate over adjacent edges.
   * 
   * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
   *
   */
  public class RelatedIterator implements Iterator {
    /** in mode **/
    public static final int IN = 0;
    
    /** out mode **/
    public static final int OUT = 1;
    
    /** both mode **/
    public static final int BOTH = 2;
    
    /** iteration mode **/
    private int m_mode;
    
    /** iteration index **/
    private int m_index;
    
    /** number of edges to iterate over **/
    private int m_n;
    
    /**
     * Constructs a new iterator.
     * 
     * @param mode Iteration mode.
     */
    public RelatedIterator(int mode) {
      m_mode = mode;
      m_index = 0;
      
      switch(m_mode) {
        case IN: 
          m_n = m_in.getInDegree();
          break;
          
        case OUT: 
          m_n = m_out.getOutDegree();
          break;
        
        default:
          m_n = 0;       
      }
    }
    
    /**
     * Unsupported Operation.
     * 
     * @throws UnsupportedOperationException
     */
    public void remove() {
      throw new UnsupportedOperationException(
        getClass().getName() + "#remove()"
      );
    }

    /**
     * Determines if there are any more related edges to return.
     * 
     * @see Iterator#hasNext()
     */
    public boolean hasNext() {
      return(m_index < m_n);  
    }

    /**
     * Returns the next related edge.
     * 
     * @see Iterator#next()
     */
    public Object next() {
      switch(m_mode) {
        case IN: return(m_in.getInEdgeArray()[m_index++]);
        case OUT: return(m_out.getOutEdgeArray()[m_index++]);
      }
      
      return(null);
    }
  }
}
