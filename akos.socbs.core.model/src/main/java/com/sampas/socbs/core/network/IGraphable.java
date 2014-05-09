package com.sampas.socbs.core.network;

import java.util.Iterator;

/**
 * Represents a component of a graph. Graph components  
 * model "real life" entities. These entities are represented by an 
 * underyling object reference.
 * 
 * @see IGraph
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/Graphable.java $
 */
public interface IGraphable {
  
    /**
     * Returns an identifier for the component. This number is not 
     * necessarily persistent or unique.
     * 
     * @return Identifying integer.
     */
    public int getID();
    
    /**
     * Sets the identifying integer for the component.
     * 
     * @param id New identifying integer.
     */
    public void setID(int id);
    
    /**
     * Determines if the component has been marked as visited. The visited 
     * flag serves as a binary switch for the component which can be used
     * for graph queries, graph traversals, or user defined purposes.
     *
     * @return True if visited(on), false if unvisited(off).
     */
    public boolean isVisited();

    /**
     * Marks the component as being visited/unvisited. The visited 
     * flag serves as a binary switch for the component which can be used
     * for graph queries, graph traversals, or user defined purposes.
     *
     * @param visited True if visited(on), false if unvisited(off).
     */
    public void setVisited(boolean visited);

    /**
     * Returns the value of the counter for the component. Graph components 
     * have a counter associated with them to be used for graph queries, graph 
     * traversals, or user defined purposes. 
     *
     * @return int The value of the counter for the component.
     */
    public int getCount();

    /**
     * Sets the value of the counter for the component. Graph components 
     * have a counter associated with them to be used for graph queries, graph 
     * traversals, or user defined purposes. 
     *
     * @param count The new value of the counter for the component.
     */
    public void setCount(int count);
    
    /**
     * Returns the underlying object referenced by the graph component.
     *
     * @return Underlying object reference, the entity being modelled by 
     * graph component.
     */
    public Object getObject();
    
    /**
     * Sets the underlying object reference for the component.
     *
     * @param obj The entity being modelled by the graph component.
     */
    public void setObject(Object obj);
     
    /**
     * Returns an iterator over any related components. A graph component is 
     * related to other components of the graph of similary type through some 
     * relationship. 
     *
     * @return Iterator An iterator over other components of the graph that 
     * are related to the component.
     */
    public Iterator getRelated();
}

