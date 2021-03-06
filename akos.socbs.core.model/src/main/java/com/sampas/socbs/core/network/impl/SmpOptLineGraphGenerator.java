package com.sampas.socbs.core.network.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphBuilder;
import com.sampas.socbs.core.network.IGraphable;
import com.sampas.socbs.core.network.ILineGraphGenerator;
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
 * Edges created by the generator are of type OptBasicEdge.<BR>
 * Nodes created by the generator are of type OptXYNode. <BR>
 * <BR>
 * Since building optimized graphs requires knowing the degree of nodes before 
 * creating them, the physical construction of the graph is delayed until a call
 * to generate() is made. No component is created with a call to add(Object),
 * only information about the object is recorded.
 *
 * @see org.geotools.graph.structure.opt.OptEdge
 * @see org.geotools.graph.structure.line.OptXYNode
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/build/line/OptLineGraphGenerator.java $
 */
public class SmpOptLineGraphGenerator implements ILineGraphGenerator {

  /** coordinate to node / count **/
  private HashMap m_coord2count;
  
  /** lines added to the network **/
  private ArrayList m_lines;
  
  /** underlying builder **/
  private IGraphBuilder m_builder;
  
  /**
   * Constructs a new OptLineGraphGenerator.
   */
  public SmpOptLineGraphGenerator() {
    m_coord2count = new HashMap();  
    m_lines = new ArrayList();
    setGraphBuilder(new SmpOptLineGraphBuilder());
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
    
    //update count of first coordinate
    if ((count = (Integer)m_coord2count.get(line.p0)) == null) {
      m_coord2count.put(line.p0, new Integer(1));
    } 
    else m_coord2count.put(line.p0, new Integer(count.intValue()+1));
    
    //update count of second coordinate
    if ((count = (Integer)m_coord2count.get(line.p1)) == null) {
      m_coord2count.put(line.p1, new Integer(1));
    } 
    else m_coord2count.put(line.p1, new Integer(count.intValue()+1));
   
    //hold onto a reference to the line
    m_lines.add(line);
    
    //return null, no componenets created
    return(null);
  }

  /**
   * Returns the edge which represents a line. This method must be called
   * after the call to generate(). Note that if the exact same line 
   * has been added to the graph multiple times, then only one of the edges 
   * that represents it will be returned. It is undefined which edge will be 
   * returned.
   * 
   * @param obj An instance of LineSegment.
   * 
   * @return Edge that represents the line.
   * 
   * @see IGraphGenerator#get(Object)
   */
  public IGraphable get(Object obj) {
    LineSegment line = (LineSegment)obj;
    
    INode n1 = (INode)m_coord2count.get(line.p0);
    INode n2 = (INode)m_coord2count.get(line.p0);
    
    return(n1.getEdge(n2));
    
    //note: if there are identical lines in the graph then it is undefined
    //which of them will be returned
  }

  /**
   * Unsupported operation.
   * 
   * @throws UnsupportedOperationException
   */
  public IGraphable remove(Object obj) {
    throw new UnsupportedOperationException(
      getClass().getName() + "#remove(Object)"
    );
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
  
  /**
   * Performs the actual generation of the graph.
   */
  public void generate() {
    generateNodes();
    generateEdges();
  }
  
  /**
   * Returns the coordinate to node map. Note that before the call to generate
   * the map does not contain any nodes.
   * 
   * @return Coordinate to node map.
   */
  public Map getNodeMap() {
    return(m_coord2count);  
  }
  
  /**
   * Returns the lines added to the graph.
   *
   * @return A list of LineSegment objects.
   */
  protected List getLines() {
    return(m_lines);  
  }
  
  
  protected void generateNodes() {
    //create nodes from coordiante counts
    for (Iterator itr = m_coord2count.entrySet().iterator(); itr.hasNext();) {
      Map.Entry entry = (Map.Entry)itr.next();
      Coordinate coord = (Coordinate)entry.getKey();
      Integer count = (Integer)entry.getValue();
      
      SmpOptXYNode node = (SmpOptXYNode)m_builder.buildNode();
      node.setDegree(count.intValue());
      node.setCoordinate(coord);
      
      m_builder.addNode(node);
      
      entry.setValue(node);
    }
  }
  
  protected void generateEdges() {
    //relate nodes
    for (Iterator itr = m_lines.iterator(); itr.hasNext();) {
      LineSegment line = (LineSegment)itr.next();
      generateEdge(line);
    }
  }
  
  protected IEdge generateEdge(LineSegment line) {
    SmpOptNode n1 = (SmpOptNode)m_coord2count.get(line.p0);
    SmpOptNode n2 = (SmpOptNode)m_coord2count.get(line.p1);
    
    SmpOptEdge edge = (SmpOptEdge)m_builder.buildEdge(n1,n2);
    m_builder.addEdge(edge);
    
    return(edge);  
  }

  //TODO DOCUMENT ME!
  public INode getNode(Coordinate c) {
    return((INode)m_coord2count.get(c)); 
    
  }

  public IEdge getEdge(Coordinate c1, Coordinate c2) {
    INode n1 = (INode)m_coord2count.get(c1);
    INode n2 = (INode)m_coord2count.get(c2);
    
    return(n1.getEdge(n2));  
  }
}
