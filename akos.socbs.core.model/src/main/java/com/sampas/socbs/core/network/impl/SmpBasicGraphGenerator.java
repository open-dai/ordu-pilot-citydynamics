package com.sampas.socbs.core.network.impl;

import java.util.HashMap;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphBuilder;
import com.sampas.socbs.core.network.IGraphGenerator;
import com.sampas.socbs.core.network.IGraphable;
import com.sampas.socbs.core.network.INode;
/**
 * An implementation of GraphGenerator. Graphs are generated as follows:<BR>
 * <UL>
 * <LI>Objects added to the generator are 2 element object arrays.</LI>
 * <LI>The elements of the array represent the objects modelled by the nodes.
 * <LI>The object array itself is the object modelled by the edges.
 * <LI>As each object array is added to the generator:
 *   <UL>
 *   <LI>A node lookup table is queried using the elements of the object array.
 *   <LI>If a node lookup returns null, a new node is created for its respective
 *       object.
 *   <LI>A new edge is created incident to the two looked up nodes.
 *   <LI>The underlying object of the edge is set to the be object array. 
 *   </UL>
 * </UL> 
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/build/basic/BasicGraphGenerator.java $
 */
public class SmpBasicGraphGenerator implements IGraphGenerator {

  /** The underlying builder **/
  private IGraphBuilder m_builder;
  
  /** object to node lookup table **/
  private HashMap m_obj2graphable;
  
  /** 
   * Constructs a new generator.
   */
  public SmpBasicGraphGenerator() {
    m_obj2graphable = new HashMap();
    setGraphBuilder(new SmpBasicGraphBuilder());  
  }
  
  /**
   * @see IGraphGenerator#add(Object)
   */
  public IGraphable add(Object obj) {
    Object[] objs = (Object[])obj;
    
    INode n1, n2;
    
    //look up first node and create if necessary
    if ((n1 = (INode)m_obj2graphable.get(objs[0])) == null) {
      n1 = getGraphBuilder().buildNode();
      n1.setObject(objs[0]);
      
      getGraphBuilder().addNode(n1);
      m_obj2graphable.put(objs[0], n1);
    }
    
    //look up second node and create if necessary
    if ((n2 = (INode)m_obj2graphable.get(objs[1])) == null) {
      n2 = getGraphBuilder().buildNode();
      n2.setObject(objs[1]);
      
      getGraphBuilder().addNode(n2);
      m_obj2graphable.put(objs[1], n2);
    }  
    
    //create edge and set underlying object
    IEdge e = getGraphBuilder().buildEdge(n1,n2);
    e.setObject(obj);
    
    getGraphBuilder().addEdge(e);
    
    return(e);  
  }

  /**
   * @see IGraphGenerator#get(Object)
   */
  public IGraphable get(Object obj) {
    Object[] objs = (Object[])obj;
    INode n1 = (INode)m_obj2graphable.get(objs[0]);
    INode n2 = (INode)m_obj2graphable.get(objs[1]);
    
    return(n1.getEdge(n2));  
  }
  
  /**
   * @see IGraphGenerator#remove(Object)
   */
  public IGraphable remove(Object obj) {
    Object[] objs = (Object[])obj;
    
    INode n1 = (INode)m_obj2graphable.get(objs[0]);
    INode n2 = (INode)m_obj2graphable.get(objs[1]);
    
    IEdge e = n1.getEdge(n2);
    getGraphBuilder().removeEdge(e);
    
    return(e);
  }

  /**
   * @see IGraphGenerator#setGraphBuilder(IGraphBuilder)
   */
  public void setGraphBuilder(IGraphBuilder builder) {
    m_builder = builder;
  }

  /**
   * @see IGraphGenerator#getGraphBuilder()
   */
  public IGraphBuilder getGraphBuilder() {
    return(m_builder);
  }
  
  /**
   * @see IGraphGenerator#getGraph()
   */
  public IGraph getGraph() {
    return(m_builder.getGraph());
  }
  
}
