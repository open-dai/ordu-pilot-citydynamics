package com.sampas.socbs.core.network;
/**
 * Iterated over the components of a graph using a standard visitor 
 * pattern.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/GraphWalker.java $
 */
public interface IGraphWalker {
    
    /**
     * Visits a graph component.
     *
     * @param element The component being visited.
     * @param traversal The traversal controlling the sequence of graph
     *        component visits.
     *
     * @return GraphTraversal#CONTINUE to signal that the traversal should 
     *         continue.<BR> 
     *         GraphTraversal#CONTINUE to signal that the traversal should 
     *         suspend.<BR>
     *         GraphTraversal#KILL_BRANCH to signal that the traversal should
     *         kill its current branch.<BR>
     *         GraphTraversal#STOP to signal that the traversal should stop.<BR>
     * 
     * @see IGraphTraversal
     * @see IGraphIterator
     */
    public int visit(IGraphable element, IGraphTraversal traversal);

    /**
     * Called when the graph traversal is completed. Wether this method is called
     * after a traversal has been stopped with a return signal is up to the 
     * implementation of GraphTraversal.
     * 
     * @see IGraphTraversal
     */
    public void finish();
}
