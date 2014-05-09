package com.sampas.socbs.core.network.impl;
/**
 * An implementation of GraphGenerator used to generate directed graphs.
 * Graphs are generated as follows:<BR>
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
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/build/basic/BasicDirectedGraphGenerator.java $
 */
public class SmpBasicDirectedGraphGenerator extends SmpBasicGraphGenerator {
 
  /**
   * Constructs a new generator.
   */
  public SmpBasicDirectedGraphGenerator() {
    super();
    setGraphBuilder(new SmpBasicDirectedGraphBuilder());  
  } 
}
