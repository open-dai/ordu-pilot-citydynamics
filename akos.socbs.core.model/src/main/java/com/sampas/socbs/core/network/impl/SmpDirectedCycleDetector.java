package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphIterator;

/**
 * Detects cycles in a directed graph. A directed topological iteration 
 * of the nodes of the graph is performed. If the iteration includes all nodes 
 * in the graph then the graph is cycle free, otherwise a cycle exists. 
 * 
 * @see org.geotools.graph.traverse.standard.DirectedBreadthFirstTopologicalIterator
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/util/graph/DirectedCycleDetector.java $
 */

public class SmpDirectedCycleDetector extends SmpCycleDetector {

	public SmpDirectedCycleDetector(IGraph graph) {
		super(graph);
	}

	protected IGraphIterator createIterator() {
		return(new SmpDirectedBreadthFirstTopologicalIterator());
	}

	
}
