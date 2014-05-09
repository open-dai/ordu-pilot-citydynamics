package com.sampas.socbs.core.network;
/**
 * An interface in which to implement a visitor pattern with components of a
 * graph.
 *
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/GraphVisitor.java $
 */
public interface IGraphVisitor {
  
  /**
   * Presents the visitor with the component to visit. 
   * 
   * @param component The component being visited.
   * 
   * @return An integer signal value back to the caller.
   */
  public int visit(IGraphable component);
}
