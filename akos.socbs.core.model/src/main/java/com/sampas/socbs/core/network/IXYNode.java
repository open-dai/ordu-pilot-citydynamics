package com.sampas.socbs.core.network;

import com.vividsolutions.jts.geom.Coordinate;
/**
 * Represents a node in a line network. A node in a line graph has a coordinate
 * associated with it.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/line/XYNode.java $
 */
public interface IXYNode extends INode {
  
  /**
   * Returns the coordinate associated with the node.
   * 
   * @return A coordinate.
   */
  public Coordinate getCoordinate();
  
  /**
   * Sets the coordinate associated with the node.
   * 
   * @param c A coordinate.
   */
  public void setCoordinate(Coordinate c);
}
