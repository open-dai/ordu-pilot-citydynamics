package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphIterator;
import com.sampas.socbs.core.network.IGraphVisitor;
import com.sampas.socbs.core.network.IGraphWalker;
import com.sampas.socbs.core.network.IGraphable;

/**
 * 
 * TODO: DOCUMENT ME!
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/basic/StagedGraphTraversal.java $
 */
public class SmpStagedGraphTraversal extends SmpBasicGraphTraversal {

  private int m_stage;
  
  public SmpStagedGraphTraversal(
    IGraph graph, IGraphWalker walker, IGraphIterator iterator
  ) {
    super(graph, walker, iterator);
    m_stage = 0;
  }
  
  public void init() {
    //initialize the nodes of the graph by setting counts to 0
    getGraph().visitNodes(
      new IGraphVisitor() {
        public int visit(IGraphable component) {
          component.setCount(0);
          return(0);
        }
      }
    );
  }

  public boolean isVisited(IGraphable g) {
    return(g.getCount() == m_stage);
  }
  
  public void setVisited(IGraphable g, boolean visited) {
    g.setCount(visited ? m_stage : -1);
  }

}
