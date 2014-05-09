package com.sampas.socbs.core.network.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphVisitor;
import com.sampas.socbs.core.network.IGraphable;
import com.sampas.socbs.core.network.INode;
/**
 * Basic implemenation of Graph.
 *
 * @see IGraph
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/basic/BasicGraph.java $
 */
public class SmpBasicGraph implements IGraph, Serializable {
    
    /** Nodes belonging to the graph */
    transient private Collection m_nodes;

    /** Edges belonging to the graph */
    transient private Collection m_edges;

    /**
     * Constructs an empty graph with edge and node collections uninitialized.
     * Mainly for serializability purposes.
     */
    public SmpBasicGraph() {
      
    }
    
    /**
     * Constructs a graph from a collection of nodes and a collection of edges.
     * The relationships between the nodes (edges) are already assumed to be 
     * formed. Only the references to the node and edge collections are copied,
     * not the underlying collections themselves.
     * 
     * @param nodes Collection of nodes to be contained by the graph.
     * @param edges Collection of edges to be contained by the graph.
     */
    public SmpBasicGraph(Collection nodes, Collection edges) {
    	m_nodes = nodes;
    	m_edges = edges;
    }
    
    /**
     * Sets the node collection of the graph.
     * 
     * @param nodes Collection of Node objects.
     */
    public void setNodes(Collection nodes) {
      m_nodes = nodes;
    }
    
    /**
     * @see IGraph#getNodes()
     */ 
    public Collection getNodes() {
        return (m_nodes);
    }

    /**
     * Sets the edge collection for the graph.
     * 
     * @param edges Collection of Edge objects.
     */
    public void setEdges(Collection edges) {
      m_edges = edges;  
    }
    
    /**
     * @see IGraph#getEdges()
     */
    public Collection getEdges() {
        return (m_edges);
    }

    /**
     * @see IGraph#queryNodes(IGraphVisitor)
     */
    public List queryNodes(IGraphVisitor visitor) {
      return(query(getNodes(), visitor));
    }

    /**
     * @see IGraph#queryEdges(IGraphVisitor)
     */
    public List queryEdges(IGraphVisitor visitor) {
      return(query(getEdges(), visitor));
    }
    
    /**
     * @see IGraph#visitNodes(IGraphVisitor)
     */
    public void visitNodes(IGraphVisitor visitor) {
      visit(m_nodes, visitor);
    }
    
    /**
     * @see IGraph#visitEdges(IGraphVisitor)
     */
    public void visitEdges(IGraphVisitor visitor) {
      visit(m_edges, visitor);  
    }

    /**
     * @see IGraph#getNodesOfDegree(int)
     * @see INode#getDegree()
     */
    public List getNodesOfDegree(int n) {
      final int degree = n;

      return (
        queryNodes(
          new IGraphVisitor() {
            public int visit(IGraphable component) {
              if (((INode) component).getDegree() == degree) 
                return (PASS_AND_CONTINUE);
              return (FAIL_QUERY);
            }
          }
        )
		  );
    }

    /**
     * @see IGraph#getVisitedNodes(boolean)
     */
    public List getVisitedNodes(boolean visited) {
      return(getVisited(getNodes(), visited));
    }

    /**
     * @see IGraph#getVisitedEdges(boolean)
     */
    public List getVisitedEdges(boolean visited) {
    	return(getVisited(getEdges(), visited));
    }
    
     /**
     * Initializes the nodes in the graph by setting all visited flags to false
     * and all visited counts to zero.
     * 
     * @see SmpBasicGraphable#isVisited()
     * @see SmpBasicGraphable#getCount()
     */
    public void initNodes() {
      for (Iterator itr = m_nodes.iterator(); itr.hasNext();) {
        INode node = (INode) itr.next();
        node.setVisited(false);
        node.setCount(0);
      }
    }

    /**
     * Initializes the edges in the graph by setting all visited flags to false
     * and all visited counts to zero.
     * 
     * @see SmpBasicGraphable#isVisited()
     * @see SmpBasicGraphable#getCount()
     */
    public void initEdges() {
      for (Iterator itr = m_edges.iterator(); itr.hasNext();) {
        IEdge edge = (IEdge) itr.next();
        edge.setVisited(false);
        edge.setCount(0);
      }
    }

    /**
     * Returns the string representation of the graph which is
     * just the string representation of the edge and node collections.
     * 
     * @return String represtentaton of graph.
     */
    public String toString() {
      return("V=" + m_nodes.toString() + "\n" + "E=" + m_edges.toString());  
    }
    /*
     * Internal query method.
     */
    private List query(Collection components, IGraphVisitor visitor) {
    	ArrayList result = new ArrayList();

    	for (Iterator itr = components.iterator(); itr.hasNext();) {
    		IGraphable component = (IGraphable) itr.next();

    		switch(visitor.visit(component)) {
          case PASS_AND_CONTINUE:   
            result.add(component);
            continue;
          
          case PASS_AND_STOP:
            result.add(component);
            return(result);
            
          case FAIL_QUERY:
            continue;
    		}
    	}

    	return (result);
    }
    
    /*
     * Internal visit method
     */
    private void visit(Collection components, IGraphVisitor visitor) {
      for (Iterator itr = components.iterator(); itr.hasNext();) {
        visitor.visit((IGraphable)itr.next());  
      }
    }
    
    /*
     * Internal getVisited method.
     */
    private List getVisited(Collection components, boolean visited) {
      final boolean isVisited = visited;
      return (
        query(
          components, 
          new IGraphVisitor() {
            public int visit(IGraphable component) {
              if (component.isVisited() == isVisited)
                return (PASS_AND_CONTINUE);
              return (FAIL_QUERY);
            }
          }
        )
		  );
    }
}
