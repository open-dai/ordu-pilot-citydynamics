package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphIterator;
import com.sampas.socbs.core.network.IGraphTraversal;
import com.sampas.socbs.core.network.IGraphVisitor;
import com.sampas.socbs.core.network.IGraphWalker;
import com.sampas.socbs.core.network.IGraphable;

/**
 * A basic implementation of GraphTraversal.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/basic/BasicGraphTraversal.java $
 */
public class SmpBasicGraphTraversal implements IGraphTraversal {

  /** the graph being iterated over **/
  private IGraph m_graph;
  
  /** the walker being iterated over the graph **/
  private IGraphWalker m_walker;
  
  /** the iterator specifying the order in which to visit components of 
      the graph during the traversal **/
  private IGraphIterator m_iterator;
  
  /**
   * Constrcuts a new graph traversal. 
   * 
   * @param graph The graph being traversed.
   * @param walker The walker being traversed over the components of the graph.
   * @param iterator The iterator specifying the order in which to visit 
   *        components of the graph.
   */
  public SmpBasicGraphTraversal(
    IGraph graph, IGraphWalker walker, IGraphIterator iterator
  ) {
    m_graph = graph;
    m_walker = walker;
    setIterator(iterator);  
  }
  
  /**
   * @see IGraphTraversal#setGraph(IGraph)
   */
  public void setGraph(IGraph graph) {
    m_graph = graph;  
  }

  /**
   * @see IGraphTraversal#getGraph()
   */
  public IGraph getGraph() {
    return(m_graph);  
  }

  /**
   * Sets the iterator and intializes it.
   * 
   * @see IGraphIterator#init(IGraph)
   * @see IGraphTraversal#setIterator(IGraphIterator)
   */
  public void setIterator(IGraphIterator iterator) {
    m_iterator = iterator;
    m_iterator.setTraversal(this);
    m_iterator.init(m_graph, this);
  }

  /**
   * @see IGraphTraversal#getIterator()
   */
  public IGraphIterator getIterator() {
    return(m_iterator);
  }
  
  /**
   * @see IGraphTraversal#setWalker(IGraphWalker)
   */
  public void setWalker(IGraphWalker walker) {
    m_walker = walker;  
  }
  
  /**
   * @see IGraphTraversal#getWalker()
   */
  public IGraphWalker getWalker() {
    return(m_walker);
  }

  /**
   * Resets the visited flag and counts of all nodes of the graph.
   * 
   * @see IGraphTraversal#init()
   */
  public void init() {
    //initialize the nodes of the graph
    m_graph.visitNodes(
      new IGraphVisitor() {
        public int visit(IGraphable component) {
          component.setVisited(false);
          component.setCount(0);
          return(0);
        }
      }
    );
  }
  
  /**
   * Upon each iteration of the traversal, a component is returned from the 
   * iterator and passed to the visitor. The traversal interprets the return 
   * codes from the walker and performs the following actions. <BR>
   * <BR>
   * <TABLE border="1" width="60%" style="font-family:Arial;font-size=10pt">
   *   <TH width="20%">Code</TH>
   *   <TH width="40%">Action Performed</TH>
   *   <TR>
   *     <TD align="center">CONTINUE</TD>
   *     <TD>The traversal instructs the iterator to continue and starts the 
   *         next stage of iteration.</TD>
   *   </TR>
   *  <TR>
   *     <TD align="center">SUSPEND</TD>
   *     <TD>The traversal instructs the iterator to continue but does not start
   *         the next stage of iteration, returning from traverse().</TD> 
   *  </TR>
   *  <TR>
   *     <TD align="center">KILL_BRANCH</TD>
   *     <TD>The traversal instructs the iterator to kill the current branch
   *         and starts the next stage of iteration.</TD>
   *   </TR>
   *  <TR>
   *     <TD align="center">STOP</TD>
   *     <TD>The traversal does not instruct the iterator to continue and
   *         does not start the next of iteration, returning from traverse()
   *     </TD>
   *   </TR>
   * </TABLE>
   * 
   * @see IGraphTraversal#traverse()
   */
  public void traverse() {
    IGraphable current;
    
    //get next component from iterator, null means stop
O:  while((current = m_iterator.next(this)) != null) {
      setVisited(current, true);
      
      //pass the component to the visitor
      switch(m_walker.visit(current, null)) {
        case CONTINUE: 
          //signal iterator to continue from this point and start next 
          // iteration of traversal
          m_iterator.cont(current,this); 
          continue;
        
        case SUSPEND: 
          //signal iterator to continue from this point, but do not start
          // next iteration
          m_iterator.cont(current,this);
          return;
          
        case KILL_BRANCH:
          //signal iterator to kill branch at this point and start next
          //iteration
          m_iterator.killBranch(current,this);
          continue;
          
        case STOP:
          //stop traversal
          break O;
          
        default:
          //illegal return value from walker
          throw new IllegalStateException(
            "Unrecognized return value from GraphWalker"
          );
      }
    }
    
    //traversal complete, signal to walker
    m_walker.finish();
  }
  
  public void setVisited(IGraphable g, boolean visited) {
    g.setVisited(visited);  
  }
  
  public boolean isVisited(IGraphable g) {
    return(g.isVisited());
  }
}
