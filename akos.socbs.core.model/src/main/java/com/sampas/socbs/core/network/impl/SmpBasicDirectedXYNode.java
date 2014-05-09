package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IXYNode;
import com.vividsolutions.jts.geom.Coordinate;
/**
 * Basic implementation of a directed XYNode extended from BasicDirectedNode.
 * The coordinate is stored in the underlying object reference of the node.
 * 
 * @see org.geotools.graph.structure.basic.BasicDirectedNode
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 * 
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/line/BasicDirectedXYNode.java $
 */
public class SmpBasicDirectedXYNode extends SmpBasicDirectedNode implements IXYNode {

  /**
   * @see IXYNode#getCoordinate()
   */
  public Coordinate getCoordinate() {
    return((Coordinate)getObject());
  }

  /**
   * @see IXYNode#setCoordinate(Coordinate)
   */
  public void setCoordinate(Coordinate c) {
    setObject(c);    
  }
}
