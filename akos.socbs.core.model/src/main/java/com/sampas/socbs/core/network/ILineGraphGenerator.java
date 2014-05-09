package com.sampas.socbs.core.network;

import com.vividsolutions.jts.geom.Coordinate;

//TODO: COMMENT ME!

public interface ILineGraphGenerator extends IGraphGenerator {
 
  public INode getNode(Coordinate c);
  
  public IEdge getEdge(Coordinate c1, Coordinate c2); 
  
}