package com.sampas.socbs.core.network.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sampas.socbs.core.network.IDirectedEdge;
import com.sampas.socbs.core.network.IDirectedNode;
import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.INode;
/**
 * Basic implementation of DirectedNode.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/basic/BasicDirectedNode.java $
 */
public class SmpBasicDirectedNode extends SmpBasicGraphable implements IDirectedNode {

  /** In adjacency list **/
  transient private ArrayList m_in;
  
  /** Out adjacecy list **/ 
  transient private ArrayList m_out;

  /**
   * Constructs a new BasicDirectedNode.
   */
  public SmpBasicDirectedNode() {
    super();
    m_in = new ArrayList();
    m_out = new ArrayList();
  }
  
  /**
   * Unsupported operation. Directed nodes classify edges as <B>in</B> and 
   * <B>out</B>. addIn(Edge) and addOut(Edge) should be used instead of this
   * method.
   *
   * @throws UnsupportedOperationException
   *  
   * @see IDirectedNode#addIn(IDirectedEdge)
   * @see IDirectedNode#addOut(IDirectedEdge)
   * 
   */
  public void add(IEdge e) {
    throw new UnsupportedOperationException(
      "add(Edge)"    
    );
  }
  
  /**
   * Adds an edge to the <B>in</B> adjacency list of the node which is an 
   * underlying List implementation. No checking is done on the edge 
   * (duplication, looping...), it is simply added to the list. It is also
   * assumed that the edge being added has the node as its out node.
   *
   * @see IDirectedNode#addIn(IDirectedEdge)  
   * @see IDirectedEdge#getOutNode()
   *
   */
  public void addIn(IDirectedEdge e) {
    m_in.add(e);
  }
  
  /**
   * Adds an edge to the <B>ou</B> adjacency list of the node which is an 
   * underlying List implementation. No checking is done on the edge 
   * (duplication, looping...), it is simply added to the list. It is also
   * assumed that the edge being added has the node as its in node.
   *
   * @see IDirectedNode#addOut(IDirectedEdge)  
   * @see IDirectedEdge#getInNode()
   *
   */
  public void addOut(IDirectedEdge e) {
    m_out.add(e);  
  }
  
  /**
   * Removes the edge from both the in and out adjacency lists.
   * 
   * @see INode#remove(IEdge)
   */
  public void remove(IEdge e) {
    m_in.remove(e);
    m_out.remove(e);
  }
  
  /**
   * @see IDirectedNode#removeIn(IDirectedEdge)
   */
  public void removeIn(IDirectedEdge e) {
    m_in.remove(e);
  }
  
  /**
   * @see IDirectedNode#removeOut(IDirectedEdge)
   */
  public void removeOut(IDirectedEdge e) {
    m_out.remove(e);  
  }
  
  /**
   * First searches for an in edge with an out node == this, and in 
   * node == other. If none is found an edge with out node == other, and in 
   * node == this is searched for.
   * 
   * @see INode#remove(IEdge)
   */
  public IEdge getEdge(INode other) {
    IEdge e = getInEdge((IDirectedNode)other);
    if (e != null) return(e);
    return(getOutEdge((IDirectedNode)other));
  }
  
  /**
   * @see IDirectedNode#getInEdge(IDirectedNode)
   */
  public IEdge getInEdge(IDirectedNode other) {
    //must explictley check that the edge has node other, and one node this, 
    // just checking other is not good enough because of loops
    for (int i = 0; i < m_in.size(); i++) {
      IDirectedEdge edge = (IDirectedEdge)m_in.get(i);
      if (edge.getInNode().equals(other) && edge.getOutNode().equals(this)) 
        return(edge);
    }
    return(null);
  }
  
  /**
   * @see IDirectedNode#getOutEdge(IDirectedNode)
   */
  public IEdge getOutEdge(IDirectedNode other) {
    //must explictley check that the edge has node other, and one node this, 
    // just checking other is not good enough because of loops
    for (int i = 0; i < m_out.size(); i++) {
      IDirectedEdge edge = (IDirectedEdge)m_out.get(i);
      if (edge.getOutNode().equals(other) && edge.getInNode().equals(this)) 
        return(edge);
    }
    return(null);
  }
  
  /**
   * Returns the combination of both the in and out adjacecy lists.
   */
  public List getEdges() {
    ArrayList edges = new ArrayList();
    edges.addAll(m_in);
    edges.addAll(m_out);
    return(edges);
  }
  
  /**
   * @see IDirectedNode#getInEdges()
   */
  public List getInEdges() {
    return(m_in);  
  }
  
  /**
   * @see IDirectedNode#getOutEdges()
   */
  public List getOutEdges() {
    return(m_out);  
  }
  
  /**
   * A combination of the results of getInEdges(Node) and getOutEdges(Node).
   * 
   * @see INode#getEdges(INode)
   * @see IDirectedNode#getInEdges(IDirectedNode)
   * @see IDirectedNode#getOutEdges(IDirectedNode)
   */
  public List getEdges(INode other) {
    List edges = getInEdges((IDirectedNode)other);
    edges.addAll(getOutEdges((IDirectedNode)other));
    return(edges);
  }
  
  /**
   * @see IDirectedNode#getInEdges(IDirectedNode)
   */
  public List getInEdges(IDirectedNode other) {
    ArrayList edges = new ArrayList();
    for (int i = 0; i < m_in.size(); i++) {
      IDirectedEdge edge = (IDirectedEdge)m_in.get(i);
      if (edge.getInNode().equals(other)) edges.add(edge);
    }
    return(edges);
  }
  
  /**
   * @see IDirectedNode#getOutEdges(IDirectedNode)
   */
  public List getOutEdges(IDirectedNode other) {
    ArrayList edges = new ArrayList();
    for (int i = 0; i < m_out.size(); i++) {
      IDirectedEdge edge = (IDirectedEdge)m_out.get(i);
      if (edge.getOutNode().equals(other)) edges.add(edge);
    }
    return(edges);
  }
  
  /**
   * Returns sum of sizes of in and out adjacency lists.
   * 
   * @see INode#getDegree()
   */
  public int getDegree() {
    return(m_in.size() + m_out.size());
  }
  
  /**
   * @see IDirectedNode#getInDegree()
   */
  public int getInDegree() {
    return(m_in.size());  
  }

  /**
   * @see IDirectedNode#getOutDegree()
   */
  public int getOutDegree() {
    return(m_out.size());
  }

  /**
   * Returns an iterator over all out nodes of out edges and in nodes of in 
   * edges.
   * 
   * @see org.geotools.graph.structure.Graphable#getRelated() 
   */
  public Iterator getRelated() {
    ArrayList related = new ArrayList(m_out.size()+m_in.size());
    for (int i = 0; i < m_in.size(); i++) {
      IDirectedEdge e = (IDirectedEdge)m_in.get(i);
      related.add(e.getInNode());
    }
    
    for (int i = 0; i < m_out.size(); i++) {
      IDirectedEdge e = (IDirectedEdge)m_out.get(i);
      related.add(e.getOutNode());
    }
    return(related.iterator());
  }
  
  /**
   * Returns all in nodes of in edges.
   * 
   * @see org.geotools.graph.structure.DirectedGraphable#getInRelated()
   */
  public Iterator getInRelated() {
    ArrayList related = new ArrayList(m_in.size());
    for (int i = 0; i < m_in.size(); i++) {
      IDirectedEdge e = (IDirectedEdge)m_in.get(i);
      related.add(e.getInNode());
    }
  
    return(related.iterator());
  }

  /**
   * Returns all out nodes of out edges.
   * 
   * @see org.geotools.graph.structure.DirectedGraphable#getOutRelated()
   */
  public Iterator getOutRelated() {
  	ArrayList related = new ArrayList(m_out.size());
    for (int i = 0; i < m_out.size(); i++) {
      IDirectedEdge e = (IDirectedEdge)m_out.get(i);
      related.add(e.getOutNode());
    }
    return(related.iterator());
  }
  
  /**
   * Overides the default deserialization operation. The edge adjacency lists
   * of a BasicDirectedNode is not written out when the node is serialized so 
   * they must be recreated upon deserialization.
   * 
   * @param in Object input stream containing serialized object.
   *
   * @throws IOException
   * @throws ClassNotFoundException
   */
  private void readObject(ObjectInputStream in)
   throws IOException, ClassNotFoundException {
     
    in.defaultReadObject();
    
    //recreate edge adjacency lists
    m_in = new ArrayList();
    m_out = new ArrayList();
  }
}
