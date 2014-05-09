package com.sampas.socbs.core.network.impl;

import java.util.Iterator;
import java.util.List;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphBuilder;
import com.sampas.socbs.core.network.IGraphGenerator;
import com.sampas.socbs.core.network.IGraphable;
import com.sampas.socbs.core.network.INode;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.index.quadtree.Quadtree;
import com.vividsolutions.jts.index.strtree.STRtree;

/**
 * An implementation of GraphGenerator used to build graphs from a set 
 * of polygons.
 * <p>
 * This graph generator takes {@link com.vividsolutions.jts.geom.Polygon} 
 * objects as input when constructing a graph. The following code constructs
 * a graph from a set of polygons.
 * 
 * <pre>
 * 	<code>
 *  //get some polygons
 *  Polygon[] polygons = ...
 * 
 *  //determine what the relationship will be
 *  PolygonGraphGenerator rel = new PolygonGraphGenerator.PolygonRelationship() {
 *   
 *     public boolean related(Polygon p1, Polygon p2) {
 *	     return p1.intersects(p2);
 *     }
 *	
 *     public boolean equal(Polygon p1, Polygon p2) {
 *        return p1.equals(p2);
 *     }
 *  }
 *  //create the generator 
 *  PolygonGraphGenerator gg = new PolygonGraphGenerator(new BasicGraphBuilder(),rel);
 *  
 *  //start building
 *  for (int i = 0; i < polygons.length; i++) {
 *    gg.add(polygons[i]);
 *  }
 * 	</code>
 * </pre>
 * </p>
 * For each distinct polygon added to the graph, a node is created. If two 
 * polygons are considered equal, only a single node is created. If two 
 * polygons are considered related, the associated nodes share an edge. Equality
 * and relationship is determined by {@link org.geotools.graph.build.polygon.PolygonGraphGenerator.PolygonRelationship}
 * interface. An instance of this interface is passed to the generator at construction.

 * @author Justin Deoliveira, The Open Planning Project
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/build/polygon/PolygonGraphGenerator.java $
 */
public class SmpPolygonGraphGenerator implements IGraphGenerator {

	/**
	 * Determines the relationship among two polygons.
	 */
	public static interface PolygonRelationship {
		/**
		 * Determines if two polygons are related in any way. Rel
		 * @param p1
		 * @param p2
		 */
		boolean related(Polygon p1, Polygon p2);
		
		boolean equal(Polygon p1, Polygon p2);
	}
	
	/** store polygon to node mapping in spatial index **/
	Quadtree index;
	/** relationship between polygons in graph **/
	PolygonRelationship rel;
	/** the node/edge builder **/
	IGraphBuilder builder;
	
	public SmpPolygonGraphGenerator(IGraphBuilder builder,PolygonRelationship rel) {
		setGraphBuilder(builder);
		this.rel = rel;
		
		index = new Quadtree();
	}
	
	public IGraphable add(Object obj) {
		INode node = (INode) get(obj);
		if (node == null) {
			node = builder.buildNode();
			builder.addNode(node);
			
			node.setObject(obj);
			relate(node);
			
			//TODO: the envelope should be buffered by some tolerance
			index.insert(((Polygon)obj).getEnvelopeInternal(),node);
		}
		
		return node;
	}

	public IGraphable get(Object obj) {
		Polygon polygon = (Polygon)obj;
		return find(polygon);
	}

	public IGraphable remove(Object obj) {
		INode node = (INode) get(obj);
		if (node != null) {
			Polygon polygon = (Polygon) node.getObject();
			index.remove(polygon.getEnvelopeInternal(),node);
			
			builder.removeNode(node);
		}
		
		return node;
	}

	public void setGraphBuilder(IGraphBuilder builder) {
		this.builder = builder;

	}

	public IGraphBuilder getGraphBuilder() {
		return builder;
	}

	public IGraph getGraph() {
		return builder.getGraph();
	}
	
	protected INode find(Polygon polygon) {
		List close = index.query(polygon.getEnvelopeInternal());
		for (Iterator itr = close.iterator(); itr.hasNext();) {
			INode node = (INode)itr.next();
			Polygon p = (Polygon)node.getObject();
			
			if (rel.equal(polygon,p)) {
				return node;
			}
		}
		
		return null;
	}
	
	protected void relate(INode node) {
		Polygon polygon = (Polygon)node.getObject();
		List close = index.query(polygon.getEnvelopeInternal());
		
		for (Iterator itr = close.iterator(); itr.hasNext();) {
			INode n = (INode)itr.next();
			Polygon p = (Polygon)n.getObject();
			
			if (!rel.equal(polygon,p) && rel.related(polygon,p)) {
				IEdge edge = builder.buildEdge(node,n);
				builder.addEdge(edge);
				builder.addEdge(edge);
			}
		}
	}

}
