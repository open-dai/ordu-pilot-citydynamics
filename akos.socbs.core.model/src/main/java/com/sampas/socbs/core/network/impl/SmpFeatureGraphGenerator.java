package com.sampas.socbs.core.network.impl;

import org.geotools.feature.Feature;

import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphBuilder;
import com.sampas.socbs.core.network.IGraphGenerator;
import com.sampas.socbs.core.network.IGraphable;

/**
 * Builds a graph from {@link org.geotools.feature.Feature} objects.
 * <p>
 * This graph generator decorates another graph generator which 
 * builds a graph from geometries. 
 * </p>
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 */
public class SmpFeatureGraphGenerator extends SmpBasicGraphGenerator {

	/**
	 * The underling "geometry" building graph generator
	 */
	IGraphGenerator decorated;
	
	public SmpFeatureGraphGenerator( IGraphGenerator decorated ) {
		this.decorated = decorated;
	}
	
	public IGraph getGraph() {
		return decorated.getGraph();
	}
	
	public IGraphBuilder getGraphBuilder() {
		return decorated.getGraphBuilder();
	}
	
	public IGraphable add( Object obj ) {
		Feature feature = (Feature) obj;
		IGraphable g = decorated.add( feature.getDefaultGeometry() );
		g.setObject( feature );
	
		return g;
	}
	
	public IGraphable remove( Object obj ) {
		Feature feature = (Feature) obj;
		return decorated.remove( feature.getDefaultGeometry() );
	}
	
	public IGraphable get(Object obj) {
		Feature feature = (Feature) obj;
		return decorated.get( feature.getDefaultGeometry() );
	}
}
