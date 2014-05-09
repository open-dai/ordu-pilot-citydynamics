/*
 *    GeoTools - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2003-2006, GeoTools Project Managment Committee (PMC)
 *    (C) 2003, Refractions Reserach Inc.
 *        
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphIterator;
import com.sampas.socbs.core.network.IGraphTraversal;
import com.sampas.socbs.core.network.IGraphWalker;
import com.sampas.socbs.core.network.IGraphable;

/**
 * Detects cycles in a graph. A topological iteration 
 * of the nodes of the graph is performed. If the iteration includes all nodes 
 * in the graph then the graph is cycle free, otherwise a cycle exists. 
 * 
 * @see org.geotools.graph.traverse.standard.BreadthFirstTopologicalIterator
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/util/graph/CycleDetector.java $
 */
public class SmpCycleDetector implements IGraphWalker {
  
  /** the graph to be tested for cycle exisitance **/
  private IGraph m_graph;
  
  /** counter to keep track of the number of nodes visited in the iteration **/ 
  private int m_nvisited;
  
  /** iteration to perform on nodes of graph **/
  private IGraphIterator m_iterator;
  
  /** 
   * Constructs a new CycleDetector.
   * 
   * @param graph The graph to be tested for cycle existance. 
   */
  public SmpCycleDetector(IGraph graph) {
    m_graph = graph;
    m_nvisited = 0;
    m_iterator = createIterator();
  }
  
  /**
   * Performs the iteration to determine if a cycle exits in the graph.
   *
   * @return True if a cycle exists, false if not.
   */
  public boolean containsCycle() {
    //initialize visited counter
    m_nvisited = 0; 
    
    //create the traversal that uses the topological iterator
    IGraphTraversal traversal = new SmpBasicGraphTraversal(
      m_graph, this, m_iterator
    );
    traversal.init();
    traversal.traverse();
        
    //if all nodes visited then no cycle
    if (m_graph.getNodes().size() == m_nvisited) return(false);
    return(true);
  }
  
  /**
   * Increments the count of nodes visited.
   * 
   * @see IGraphWalker#visit(IGraphable, IGraphTraversal)
   */
  public int visit(IGraphable element, IGraphTraversal traversal) {
    m_nvisited++;
    return(IGraphTraversal.CONTINUE);
  }
  
  /**
   * Does nothing.
   * 
   * @see IGraphWalker#finish()
   */
  public void finish() {}
  
  /**
   * Creates the iterator to be used in the cycle detection.
   * 
   * @return a BreathFirstToplogicalIterator.
   */
  protected IGraphIterator createIterator() {
    return(new SmpBreadthFirstTopologicalIterator());
  }

}
