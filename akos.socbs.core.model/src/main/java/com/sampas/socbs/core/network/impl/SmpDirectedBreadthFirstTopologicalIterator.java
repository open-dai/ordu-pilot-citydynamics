package com.sampas.socbs.core.network.impl;

import java.util.Iterator;

import com.sampas.socbs.core.network.IDirectedGraphable;
import com.sampas.socbs.core.network.IDirectedNode;
import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphTraversal;
import com.sampas.socbs.core.network.IGraphVisitor;
import com.sampas.socbs.core.network.IGraphable;
import com.sampas.socbs.core.network.IQueue;
/**
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/standard/DirectedBreadthFirstTopologicalIterator.java $
 */
public class SmpDirectedBreadthFirstTopologicalIterator 
  extends SmpAbstractGraphIterator {

  private IQueue m_queue;
  
  public void init(IGraph graph, IGraphTraversal traversal) {
    //create queue
    m_queue = buildQueue(graph);
    
    //initialize nodes
    graph.visitNodes(
      new IGraphVisitor() {
        public int visit(IGraphable component) {
          IDirectedNode node = (IDirectedNode)component;
          
          node.setVisited(false);
          node.setCount(0);
          
          if (node.getInDegree() == 0) m_queue.enq(node);
          
          return(0);  
        }
      } 
    );
  }

  public IGraphable next(IGraphTraversal traversal) {
    return(!m_queue.isEmpty() ? (IGraphable)m_queue.deq() : null); 
  }

  public void cont(IGraphable current, IGraphTraversal traversal) {
    //increment the count of all adjacent nodes by one
    // if the result count equal to the degree, place it into the queue
    IDirectedGraphable directed = (IDirectedGraphable)current;
    for (Iterator itr = directed.getOutRelated(); itr.hasNext();) {
      IDirectedNode related = (IDirectedNode)itr.next();
      if (!traversal.isVisited(related)) {
        related.setCount(related.getCount()+1);  
        if (related.getInDegree() == related.getCount()) m_queue.enq(related);
      }  
    }
  }

  public void killBranch(IGraphable current, IGraphTraversal traversal) {
    //do nothing
  }
  
  protected IQueue buildQueue(IGraph graph) {
    return(new SmpFIFOQueue(graph.getNodes().size()));  
  }
}
