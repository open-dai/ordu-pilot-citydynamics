package com.sampas.socbs.core.network;

import java.util.Collection;

/**
 * Build the physical components of a Graph. The GraphBuilder manages the 
 * details of component creation, addition, and removal from the graph.<BR>
 * <BR>
 * Graph components are created through calls to buildNode() and buildEdge(Node,
 * Node), and then added to the graph through calls to addNode(Node), and 
 * addEdge(Edge).<BR>
 * <BR>
 * The GraphBuilder class is the lower level of the graph construction process.
 * The builder does not manage the entities being modelled by the graph 
 * components. This is done at a higher level by the GraphGenerator. 
 * class.
 * 
 * @see IGraph
 * @see IGraphGenerator
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/build/GraphBuilder.java $
 */
public interface IGraphBuilder {
    
    /**
     * Returns the graph being built.
     * 
     * @return Graph The graph being built.
     */
    public IGraph getGraph();

    /**
     * Builds a new node for the graph. This method does not add the new node to 
     * the graph, this must be done with the addNode(Node) method.
     * 
     * @return Node The newly built node.
     */
    public INode buildNode();
    
    /**
     * Builds a new edge for the graph. This method does not add the new node to 
     * the graph, this must be done with the addEdge(Edge) method.
     * 
     * @param nodeA Adjacent node to edge.
     * @param nodeB Adjacent node to edge.
     * 
     * @return Edge the newly built Edge.
     */
    public IEdge buildEdge(INode nodeA, INode nodeB);
    
    /**
     * Adds a node to the graph. 
     * 
     * @param node Node to be added to graph.
     */
    public void addNode(INode node);
    
    /**
     * Adds an edge to the graph. 
     * 
     * @param edge Edge to be added to graph.
     */
    public void addEdge(IEdge edge);
    
    /** 
     * Removes an node from the graph.
     * 
     * @param node Node to be removed from graph. 
     */
    public void removeNode(INode node);
    
    /**
     * Removes a collection of nodes from the graph.
     * 
     * @param nodes A collection of nodes to be removed from the graph.
     */
    public void removeNodes(Collection nodes);
    
    /**
     * Removes an edge from the graph.
     * 
     * @param edge Edge to be removed from graph.
     */
    public void removeEdge(IEdge edge);
    
    /**
     * Removes a collection of edges from the graph.
     * 
     * @param edges Collection of edges to be removed from the graph.
     */
    public void removeEdges(Collection edges);
    
    /**
     * Returns a clone of the builder. A deep clone copies the underlying
     * graph structure, a non deep clone results in an empty builder
     * 
     * @param deep Deep or non deep clone.
     * 
     * @return A graph builder. 
     */
    public Object clone(boolean deep) throws Exception;
    
    /** Constructs a graph builder from a pre built graph. The nodes and edges
     * of the existing graph are imported into the builder. Relationships between
     * nodes and edges are assummed to be preexistant. 
     * 
     * @param g A pre built graph.
     */
    public void importGraph(IGraph g);
    
}
