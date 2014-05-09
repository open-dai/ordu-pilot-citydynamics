package com.sampas.socbs.core.network.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.INode;
/**
 * Optimized implementation of Node. The following optimizations reduce space
 * and improve performance.<BR>
 * <UL>
 *   <LI>Edge adjacency list stored as array of predetermined size.</LI>
 *   <LI>Removing support for removing edges from the nodes ajdacency list.</LI>
 *   <LI>The related component iterator iterates over the underlying edge 
 *       array of the node instread of a newly created collection.
 * </UL>
 * Using an optimized node requires that the degree of the node be known before
 * the node is built. 
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 * @see INode
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/opt/OptNode.java $
 */
public class SmpOptNode extends SmpOptGraphable implements INode {

  /** edge adjacency list **/
  private IEdge[] m_edges;
  
  /**
   * Constructs a new OptimizedNode. This constructor does not build the 
   * adjacency array for the node.
   */
  public SmpOptNode() {
    this(0);
  }
  
  /**
   * Constructs a new Optimized Node with a known degree.
   * 
   * @param degree The degree of the node.
   */
  public SmpOptNode(int degree) {
    super();
    m_edges = new IEdge[degree];
  }
  
  /**
   * @see INode#add(IEdge)
   */
  public void add(IEdge e) {
    for (int i = 0; i < m_edges.length; i++) {
      if (m_edges[i] == null) {
        m_edges[i] = e;
        return;
      }  
    }    
  }

  /**
   * Not supported.
   * 
   * @throws UnsupportedOperationException
   * 
   * @see INode#remove(IEdge)
   */
  public void remove(IEdge e) {
    throw new UnsupportedOperationException(
      getClass().getName() + "#remove(Edge)" 
    );  
  }

  /**
   * @see INode#getEdge(INode)
   */
  public IEdge getEdge(INode other) {
    for (int i = 0; i < m_edges.length; i++) {
      if (
       m_edges[i].getNodeA().equals(this) && m_edges[i].getNodeB().equals(other)
    || m_edges[i].getNodeB().equals(this) && m_edges[i].getNodeA().equals(other)
      ) return(m_edges[i]);    
    }
    return(null);
  }

  /**
   * @see INode#getEdges(INode)
   */
  public List getEdges(INode other) {
    ArrayList edges = new ArrayList();
    for (int i = 0; i < m_edges.length; i++) {
      if (
       m_edges[i].getNodeA().equals(this) && m_edges[i].getNodeB().equals(other)
    || m_edges[i].getNodeB().equals(this) && m_edges[i].getNodeA().equals(other)
      ) edges.add(m_edges[i]);    
    }
    return(edges);
  }

  /**
   * Returns the edge adjacency list of the node as an array.
   * 
   * @return An array containing edges adjacent to the node.
   */
  public IEdge[] getEdgeArray() {
    return(m_edges);  
  }
   
  /**
   * @see INode#getEdges()
   */
  public List getEdges() {
    ArrayList edges = new ArrayList();
    
    for (int i = 0; i < m_edges.length; i++) {
      edges.add(m_edges[i]);  
    }
   
    return(edges); 
  }

  /**
   * Sets the degree of the node. This method build the edge adjacency array 
   * for the node.  
   * 
   * @param degree The degree of the node / size of edge adjacency array.
   */
  public void setDegree(int degree) {
    m_edges = new IEdge[degree];
  }
   
  /**
   * @see INode#getDegree()
   */
  public int getDegree() {
    return(m_edges.length);
  }

  /**
   * This iterator iterates over the underlying edge array of the node.
   * 
   * @see org.geotools.graph.structure.Graphable#getRelated()
   */
  public Iterator getRelated() {
    return(new RelatedIterator(this));
  }
  
  /**
   * Overrides the default deserialization operation. Since edge adjacency 
   * lists of Nodes are not written out upon serialization, they must be 
   * recreated upon deserialization. 
   * 
   * @param in Object input stream containing serialized objects.
   * 
   * @throws IOException
   * @throws ClassNotFoundException
   */
  private void readObject(ObjectInputStream in)
   throws IOException, ClassNotFoundException {
     
    in.defaultReadObject();
    
    //read degree from object stream and recreate edge list
    setDegree(in.readInt());
  }
  
  /**
   * Overrides the default serialization operation. Since edge adjacency 
   * lists of Nodes are not written out upon serialization, all the information 
   * needed to recreate them must be written to the object stream as well. Since
   * the edge list is not written out, and the node does not store its degree
   * explicitly, it must be written to the output stream.
   * 
   * @param in Object output stream containing serialized objects.
   * 
   * @throws IOException
   * @throws ClassNotFoundException
   */
  private void writeObject(ObjectOutputStream out)
   throws IOException {
     
    out.defaultWriteObject();
    
    //store degree in object stream
    out.writeInt(getDegree());
  }
  
  /**
   * An iterator used to iterate over related nodes.
   * 
   * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
   *
   */
  public class RelatedIterator implements Iterator {
    
    /** index of iterator **/
    private byte m_index = 0;
    private SmpOptNode m_node;
    
    public RelatedIterator(SmpOptNode node) {
      m_node = node;    
    }
    
    /**
     * Not supported.
     * 
     * @throws UnsupportedOperationException
     */
    public void remove() {
      throw new UnsupportedOperationException(
        getClass().getName() + "#remove()"
      );
    }

    /**
     * Determines if there are any more related nodes to return.
     * 
     * @see Iterator#hasNext()
     */
    public boolean hasNext() {
      return(m_index < m_edges.length);
    }

    /**
     * Returns the next related node.
     * 
     * @see Iterator#next() 
     */
    public Object next() {
      IEdge e = m_edges[m_index++];
      return(e.getNodeA().equals(m_node) ? e.getNodeB() : e.getNodeA());
    } 
  } 
}
