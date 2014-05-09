package com.sampas.socbs.core.network.impl;

import java.util.Iterator;

import com.sampas.socbs.core.network.IDirectedGraphable;
import com.sampas.socbs.core.network.IGraphTraversal;
import com.sampas.socbs.core.network.IGraphable;
/**
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/standard/DirectedDepthFirstIterator.java $
 */
public class SmpDirectedDepthFirstIterator extends SmpDepthFirstIterator {

  public void cont(IGraphable current, IGraphTraversal traversal) {
    //only consider outing going related
    IDirectedGraphable dg = (IDirectedGraphable)current;
    for (Iterator itr = dg.getOutRelated(); itr.hasNext();) {
      IDirectedGraphable related = (IDirectedGraphable)itr.next();
      if (!traversal.isVisited(related)) getQueue().enq(related);  
    }
  }

}
