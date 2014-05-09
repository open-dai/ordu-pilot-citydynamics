package com.sampas.socbs.core.network.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.IGraphable;
import com.sampas.socbs.core.network.INode;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LineSegment;

/**
 * An implementation of GraphGenerator used to generate an optimized graph 
 * representing a line network. Graphs are generated by supplying the generator 
 * with objects of type LineSegment via the add(Object) method. <BR>
 * <BR>
 * For each line segment added, an edge in the graph is created. The builder
 * records the end coordinates of each line added, and maintains a map of 
 * coordinates to nodes, creating nodes when neccessary.<BR>
 * <BR>
 * Edges created by the generator are of type OptBasicEdge.
 * Nodes created by the generator are of type OptXYNode.
 *
 * @see org.geotools.graph.structure.opt.OptEdge
 * @see org.geotools.graph.structure.line.OptXYNode
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/build/line/OptDirectedLineGraphGenerator.java $
 */
public class SmpOptDirectedLineGraphGenerator extends SmpOptLineGraphGenerator {
 
  /** maps in coordinates to count / node **/
  HashMap m_in2count;
  
  /** maps out coordinates to count / node **/
  HashMap m_out2count;
  
  /**
   * Constructs a new OptLineGraphGenerator.
   */
  public SmpOptDirectedLineGraphGenerator() {
    super();
    
    m_in2count = new HashMap();
    m_out2count = new HashMap();
    
    setGraphBuilder(new SmpOptDirectedLineGraphBuilder());  
  }
   
  /**
   * Adds a line to the graph. Note that this method returns null since actual
   * building of the graph components is delayed until generate() is called.
   * 
   * @param obj A LineSegment object.
   * 
   * @return null because the actual building of the graph components is delayed
   *         until generate() is called.
   */
  public IGraphable add(Object obj) {
    LineSegment line = (LineSegment)obj;
    Integer count;
    
    //increment the count of the in coordinate
    if ((count = (Integer)m_in2count.get(line.p0)) == null) {
      m_in2count.put(line.p0, new Integer(1));
    } 
    else m_in2count.put(line.p0, new Integer(count.intValue()+1));
    
    //increment the count of the out coordinate
    if ((count = (Integer)m_out2count.get(line.p1)) == null) {
      m_out2count.put(line.p1, new Integer(1));
    } 
    else m_out2count.put(line.p1, new Integer(count.intValue()+1));
   
    getLines().add(line);
    
    return(null);
  }

  /**
   * Returns the coordinate to <B>in</B> node map. Note that before the call to
   * generate the map does not contain any nodes.
   * 
   * @return Coordinate to in node map.
   */
  public Map getInNodeMap() {
    return(m_in2count); 
  }
  
  /**
   * Returns the coordinate to <B>out</B> node map. Note that before the call to
   * generate the map does not contain any nodes.
   * 
   * @return Coordinate to out node map.
   */
  public Map getOutNodeMap() {
    return(m_out2count);
  }
  
  protected void generateNodes() {
    //create the nodes, starting with in nodes
    for (Iterator itr = m_in2count.entrySet().iterator(); itr.hasNext();) {
      Map.Entry entry = (Map.Entry)itr.next();
      Coordinate coord = (Coordinate)entry.getKey();
      Integer count = (Integer)entry.getValue();
      
      SmpOptDirectedXYNode node = (SmpOptDirectedXYNode)getGraphBuilder().buildNode();
      node.setCoordinate(coord);
      
      //set the out degree (in count means => out degree)
      node.setOutDegree(count.intValue());
      
      //get the in degree (out count) from the out map, if no entry, set to 0
      count = (Integer)m_out2count.get(coord);
      if (count != null) node.setInDegree(count.intValue());
      else node.setInDegree(0);
      
      getGraphBuilder().addNode(node);
      
      //set map value to be node instead of count
      entry.setValue(node);
    }
    
    //create only nodes that are not in the in set
    for (Iterator itr = m_out2count.entrySet().iterator(); itr.hasNext();) {
      Map.Entry entry = (Map.Entry)itr.next();
      Coordinate coord = (Coordinate)entry.getKey();
      Integer count = (Integer)entry.getValue();
      
      //look for the node in the in set, if not there, it means that the 
      // node is never an in node => out degree = 0
      SmpOptDirectedXYNode node = (SmpOptDirectedXYNode)m_in2count.get(coord);
      if (node == null) {
        node = (SmpOptDirectedXYNode)getGraphBuilder().buildNode();
        node.setCoordinate(coord);
        
        node.setOutDegree(0);
        node.setInDegree(count.intValue());
        
        getGraphBuilder().addNode(node);
      }
      //else do nothing, the node was already set when processing in set
      
      //set map value to be node instead of count
      entry.setValue(node);
    }
  }
  
  protected IEdge generateEdge(LineSegment line) {
    SmpOptDirectedNode n1 = (SmpOptDirectedNode)m_in2count.get(line.p0);
    SmpOptDirectedNode n2 = (SmpOptDirectedNode)m_out2count.get(line.p1);
    
    IEdge edge = getGraphBuilder().buildEdge(n1,n2);
    getGraphBuilder().addEdge(edge);
    
    return(edge);  
  }
  
  public INode getNode(Coordinate c) {
    INode n = (INode)m_in2count.get(c);
    
    if (n != null) return(n);
    return((INode)m_out2count.get(c));
  }

  public IEdge getEdge(Coordinate c1, Coordinate c2) {
    //TODO: IMPLEMENT
    
    return(null);
  }

}
