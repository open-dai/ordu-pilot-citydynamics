package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.INode;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Logger;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
/**
 *
 * @author jfc173
 */
public class SmpAutoClustUtils {
    
    private static final Logger LOGGER = org.geotools.util.logging.Logging.getLogger("org.geotools.graph");
    
    /** Creates a new instance of AutoClustUtils */
    public SmpAutoClustUtils() {
    }
    
    public static Vector findConnectedComponents(final Collection nodes, final Collection edges){
        Vector components = new Vector();
        Vector nodesVisited = new Vector();
        
        Iterator nodesIt = nodes.iterator();
        while (nodesIt.hasNext()){            
            INode next = (INode) nodesIt.next();
            if (!(nodesVisited.contains(next))){
                Vector componentNodes = new Vector();
                Vector componentEdges = new Vector();
                expandComponent(next, edges, componentNodes, componentEdges);
                nodesVisited.addAll(componentNodes);
                IGraph component = new SmpBasicGraph(componentNodes, componentEdges);
                components.add(component);
            }
        }
        
        return components;
    }
    
    private static void expandComponent(final INode node, final Collection edges, final Collection componentNodes, final Collection componentEdges){
        if (componentNodes.contains(node)){
            //base case.  I've already expanded on node, so no need to repeat the process.
//            LOGGER.fine("I've already expanded from " + node);
        } else {
            componentNodes.add(node);
//            LOGGER.finer("Adding " + node + " to component");
            Vector adjacentEdges = findAdjacentEdges(node, edges);  //yes, I know node.getEdges() should do this, but this method could be out of data by the time I use this in AutoClust
            adjacentEdges.trimToSize();
            componentEdges.addAll(adjacentEdges);  
//            LOGGER.finer("Adding " + adjacentEdges + " to component");
            
            Iterator aeIt = adjacentEdges.iterator();
            while (aeIt.hasNext()){
                IEdge next = (IEdge) aeIt.next();
//                LOGGER.finer("looking at edge " + next);
                INode additionalNode = next.getOtherNode(node);
//                LOGGER.finer("its other node is " + additionalNode);
                if (additionalNode == null){
                    throw new RuntimeException("I tried to get the other node of this edge " + next + " but it doesn't have " + node);
                }
                expandComponent(additionalNode, edges, componentNodes, componentEdges);
            }
            adjacentEdges.clear();
        }
    }
    
    public static Vector findAdjacentEdges(final INode node, final Collection edges){
        Vector ret = new Vector();
        Iterator it = edges.iterator();
        while (it.hasNext()){
            IEdge next = (IEdge) it.next();
            if ((next.getNodeA().equals(node)) || (next.getNodeB().equals(node))){
                ret.add(next);
            }
        }
        return ret;
    }
    
    public static SmpDelaunayNode[] featureCollectionToNodeArray(FeatureCollection fc){
        FeatureIterator iter = fc.features();
        int size = fc.size();
        SmpDelaunayNode[] nodes = new SmpDelaunayNode[size];
        int index = 0;
        while (iter.hasNext()){
            Feature next = iter.next();
            Geometry geom = next.getDefaultGeometry();
            Point centroid;
            if (geom instanceof Point){
                centroid = (Point) geom;
            } else {
                centroid = geom.getCentroid();
            }
            SmpDelaunayNode node = new SmpDelaunayNode();   
            node.setCoordinate(centroid.getCoordinate()); 
            node.setFeature(next);
            if (!(arrayContains(node, nodes, index))){
                nodes[index] = node;
                index++;                
            }                  
        }
        
        SmpDelaunayNode[] trimmed = new SmpDelaunayNode[index];
        for (int i = 0; i < index; i++){
            trimmed[i] = nodes[i];
        }
        return trimmed;
    }
    
    public static boolean arrayContains(SmpDelaunayNode node, SmpDelaunayNode[] nodes, int index){
        boolean ret = false;
        boolean done = false;
        int i = 0;
        while (!(done)){
            if (i < index){
                done = ret = (nodes[i].equals(node));
                i++;
            } else {
                done = true;
            }
        }
        return ret;
    }    
    
}
