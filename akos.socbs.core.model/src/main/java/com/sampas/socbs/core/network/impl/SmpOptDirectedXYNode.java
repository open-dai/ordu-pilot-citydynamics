package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IXYNode;
import com.vividsolutions.jts.geom.Coordinate;
/**
 * Optimized implementation of XYNode extended from OptDirectedNode. Instead of 
 * storing an underlying coordinate object, only a set of (x,y) values are 
 * stored eliminating the storage of additional oordinate dimensions.
 * 
 * @see org.geotools.graph.structure.opt.OptDirectedNode
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/line/OptDirectedXYNode.java $
 */
public class SmpOptDirectedXYNode extends SmpOptDirectedNode implements IXYNode {
  
  /** x value of coordinate **/
  private double m_x;
  
  /** y value of coordinate **/
  private double m_y;
  
  /**
   * This method creates a new Coordinate object upon each call.
   * 
   * @see IXYNode#getCoordinate() 
   */
  public Coordinate getCoordinate() {
    return(new Coordinate(m_x, m_y));
  }

  /**
   * This method strips only the x and y ordinates from the Coordinate object
   * and stores them.
   * 
   * @see IXYNode#setCoordinate(Coordinate) 
   */
  public void setCoordinate(Coordinate c) {
    m_x = c.x;
    m_y = c.y; 
  }
}
