package com.sampas.socbs.core.network;

/**
 * Represents an edge in a directed graph. A directed edge catagorizes its 
 * nodes as originating (in node), and terminating (out node). The edge is an 
 * <B>out edge</B> of its <B>in node</B> and an <B>in edge</B> to its <B>out 
 * node</B>.
 * 
 * @see IDirectedGraph
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 * 
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/DirectedEdge.java $
 */
public interface IDirectedEdge extends IEdge, IDirectedGraphable {
  
  /**
   * Returns the originating (in) node of the edge.
   * 
   * @return The directed node at the source of the  edge.
   */
  public IDirectedNode getInNode();
  
  /**
   * Returns the terminating (out) node of the edge.
   * 
   * @return The directed node at the destination of the edge. 
   */
  public IDirectedNode getOutNode();
  
}
