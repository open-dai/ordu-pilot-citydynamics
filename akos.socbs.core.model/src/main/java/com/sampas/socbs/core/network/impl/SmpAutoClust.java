package com.sampas.socbs.core.network.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.INode;
/**
 *
 * @author jfc173
 */
public class SmpAutoClust {        
    
    private static final Logger LOGGER = org.geotools.util.logging.Logging.getLogger("org.geotools.graph");
    
    /** Creates a new instance of AutoClust */
    public SmpAutoClust() {
    }
    
    public static IGraph runAutoClust(IGraph d){
        //this is assuming d is a delaunay triangulation.  If it isn't, results are unspecified.
        //Such a diagram can be obtained through org.geotools.graph.util.delaunay.DelaunayTriangulator.
            
        HashMap map = new HashMap();
        Collection nodes = d.getNodes();
        Collection edges = d.getEdges();
        showGraph(nodes, edges, 0);
        Iterator nodeIt = nodes.iterator();
        double[] localDevs = new double[nodes.size()];
        int index = 0;
        while (nodeIt.hasNext()){
            SmpDelaunayNode next = (SmpDelaunayNode) nodeIt.next();
            SmpAutoClustData acd = new SmpAutoClustData();
            List localEdges = SmpAutoClustUtils.findAdjacentEdges(next, edges);
            double totalLength = 0;
            Iterator edgeIt = localEdges.iterator();
            while (edgeIt.hasNext()){
                SmpDelaunayEdge nextEdge = (SmpDelaunayEdge) edgeIt.next();
                totalLength = totalLength + nextEdge.getEuclideanDistance();
            }
            double meanLength = totalLength/localEdges.size();
            double sumOfSquaredDiffs = 0;
            Iterator anotherEdgeIt = localEdges.iterator();            
            while (anotherEdgeIt.hasNext()){                
                SmpDelaunayEdge nextEdge = (SmpDelaunayEdge) anotherEdgeIt.next();
                sumOfSquaredDiffs = sumOfSquaredDiffs + Math.pow((nextEdge.getEuclideanDistance() - meanLength), 2);                
            }
            double variance = sumOfSquaredDiffs/(localEdges.size());
            double stDev = Math.sqrt(variance);
            localDevs[index] = stDev;
            index++;            
            acd.setLocalMean(meanLength);
            acd.setLocalStDev(stDev);
            map.put(next, acd);
        }
        double total = 0;
        for (int i = 0; i < localDevs.length; i++){
            total = total + localDevs[i];
        }
        double meanStDev = total/localDevs.length;
        
        //these three are for coloring the graph in the poster, not for algorithmic purposes
        Vector allShortEdges = new Vector();
        Vector allLongEdges = new Vector();
        Vector allOtherEdges = new Vector();
        
        Iterator anotherNodeIt = nodes.iterator();
        while (anotherNodeIt.hasNext()){
            SmpDelaunayNode next = (SmpDelaunayNode) anotherNodeIt.next();
            List localEdges = SmpAutoClustUtils.findAdjacentEdges(next, edges);
            SmpAutoClustData acd = (SmpAutoClustData) map.get(next);
            Iterator edgeIt = localEdges.iterator();
            Vector shortEdges = new Vector();
            Vector longEdges = new Vector();
            Vector otherEdges = new Vector();
            LOGGER.fine("local mean is " + acd.getLocalMean());
            LOGGER.fine("mean st dev is " + meanStDev);
            while (edgeIt.hasNext()){
                SmpDelaunayEdge nextEdge = (SmpDelaunayEdge) edgeIt.next();
                double length = nextEdge.getEuclideanDistance();
                if (length < acd.getLocalMean() - meanStDev){
                    shortEdges.add(nextEdge);
                    LOGGER.finer(nextEdge + ": length " + nextEdge.getEuclideanDistance() + " is short");
                } else if (length > acd.getLocalMean() + meanStDev){
                    longEdges.add(nextEdge);
                    LOGGER.finer(nextEdge + ": length " + nextEdge.getEuclideanDistance() + " is long");
                } else {
                    otherEdges.add(nextEdge);
                    LOGGER.finer(nextEdge + ": length " + nextEdge.getEuclideanDistance() + " is medium");
                }
            }
            acd.setShortEdges(shortEdges);
            acd.setLongEdges(longEdges);
            acd.setOtherEdges(otherEdges);            
                     
            allLongEdges.addAll(longEdges);
            allShortEdges.addAll(shortEdges);
            allOtherEdges.addAll(otherEdges);
        }
        
        //for the poster
        IGraph gp = new SmpBasicGraph(nodes, edges);
        javax.swing.JFrame frame = new javax.swing.JFrame();
        SmpGraphViewer viewer = new SmpGraphViewer();
        viewer.setLongEdges(allLongEdges);
        viewer.setShortEdges(allShortEdges);
        viewer.setOtherEdges(allOtherEdges);
        viewer.setColorEdges(true);
        viewer.setGraph(gp);
        frame.getContentPane().add(viewer);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(new java.awt.Dimension(800, 800));
        frame.setTitle("Assigned edge categories");
        frame.setVisible(true); 
        
        //Phase I
        Iterator nodeIt3 = nodes.iterator();
        while (nodeIt3.hasNext()){
            SmpDelaunayNode next = (SmpDelaunayNode) nodeIt3.next();
            SmpAutoClustData acd = (SmpAutoClustData) map.get(next);
            List shortEdges = acd.getShortEdges();
            List longEdges = acd.getLongEdges();
            edges.removeAll(shortEdges);
            LOGGER.finer("removed " + shortEdges);
            edges.removeAll(longEdges);
            LOGGER.finer("removed " + longEdges);
        }
        
        LOGGER.fine("End of phase one and ");
        LOGGER.fine("Nodes are " + nodes);
        LOGGER.fine("Edges are " + edges);                
        showGraph(nodes, edges, 1);        
        Vector connectedComponents = SmpAutoClustUtils.findConnectedComponents(nodes, edges);
        
        //Phase II
        Iterator nodeIt4 = nodes.iterator();
        while (nodeIt4.hasNext()){
            SmpDelaunayNode next = (SmpDelaunayNode) nodeIt4.next();
            SmpAutoClustData acd = (SmpAutoClustData) map.get(next);
            List shortEdges = acd.getShortEdges();
            if (!(shortEdges.isEmpty())){
                Vector shortlyConnectedComponents = new Vector();
                Iterator shortIt = shortEdges.iterator();
                while (shortIt.hasNext()){
                    IEdge nextEdge = (IEdge) shortIt.next();
                    INode other = nextEdge.getOtherNode(next);
                    IGraph g = getMyComponent(other, connectedComponents);
                    if (!(shortlyConnectedComponents.contains(g))){
                        shortlyConnectedComponents.add(g);
                    }
                }
                IGraph cv = null;
                if (shortlyConnectedComponents.size() > 1){
                    Iterator sccIt = shortlyConnectedComponents.iterator();
                    int maxSize = 0;
                    while (sccIt.hasNext()){
                        IGraph nextGraph = (IGraph) sccIt.next();
                        int size = nextGraph.getNodes().size();
                        if (size > maxSize){
                            maxSize = size;
                            cv = nextGraph;
                        }
                    }
                } else {
                    cv = (IGraph) shortlyConnectedComponents.get(0);
                }
                Iterator shortIt2 = shortEdges.iterator();
                while (shortIt2.hasNext()){
                    IEdge nextEdge = (IEdge) shortIt2.next();
                    INode other = nextEdge.getOtherNode(next);
                    if (cv.equals(getMyComponent(other, shortlyConnectedComponents))){
                        edges.add(nextEdge);
                    }
                }
            } //end if shortEdges isn't empty
            IGraph gr = getMyComponent(next, connectedComponents);
            if (gr.getNodes().size() == 1){
                Vector shortlyConnectedComponents = new Vector();
                Iterator shortIt = shortEdges.iterator();
                while (shortIt.hasNext()){
                    IEdge nextEdge = (IEdge) shortIt.next();
                    INode other = nextEdge.getOtherNode(next);
                    IGraph g = getMyComponent(other, connectedComponents);
                    if (!(shortlyConnectedComponents.contains(g))){
                        shortlyConnectedComponents.add(g);
                    }
                }
                if (shortlyConnectedComponents.size() == 1){
                    edges.addAll(shortEdges);
                }
            }
        } //end nodeIt4 while loop.
        
        LOGGER.fine("End of phase two and ");
        LOGGER.fine("Nodes are " + nodes);
        LOGGER.fine("Edges are " + edges);   
        showGraph(nodes, edges, 2);
        connectedComponents = SmpAutoClustUtils.findConnectedComponents(nodes, edges);
        
        //Phase III
        Iterator nodeIt5 = nodes.iterator();
        while (nodeIt5.hasNext()){
            SmpDelaunayNode next = (SmpDelaunayNode) nodeIt5.next();
            Vector edgesWithinTwo = new Vector();
            List adjacentEdges = SmpAutoClustUtils.findAdjacentEdges(next, edges); //yes, next.getEdges() could work, but there's no guarantee that next's edge list is current anymore
            edgesWithinTwo.addAll(adjacentEdges);
            Iterator adjacentIt = adjacentEdges.iterator();
            while (adjacentIt.hasNext()){
                IEdge nextEdge = (IEdge) adjacentIt.next();
                INode other = nextEdge.getOtherNode(next);
                List adjacentToOther = SmpAutoClustUtils.findAdjacentEdges(other, edges);  //yes, other.getEdges() could work, but there's no guarantee that other's edge list is current anymore
                Iterator atoIt = adjacentToOther.iterator();
                while (atoIt.hasNext()){
                    IEdge nextEdge2 = (IEdge) atoIt.next();
                    if (!(edgesWithinTwo.contains(nextEdge2))){
                        edgesWithinTwo.add(nextEdge2);
                    }
                }
            }
            double totalLength = 0;
            Iterator ewtIt = edgesWithinTwo.iterator();
            while (ewtIt.hasNext()){
                totalLength = totalLength + ((SmpDelaunayEdge) ewtIt.next()).getEuclideanDistance();
            }
            double local2Mean = totalLength/edgesWithinTwo.size();
            
            Iterator ewtIt2 = edgesWithinTwo.iterator();
            while (ewtIt2.hasNext()){
                SmpDelaunayEdge dEdge = (SmpDelaunayEdge) ewtIt2.next();
                if (dEdge.getEuclideanDistance() > (local2Mean + meanStDev)){
                    edges.remove(dEdge);
                }
            }                        
        } //end nodeIt5 loop
        
        LOGGER.fine("End of phase three and ");
        LOGGER.fine("Nodes are " + nodes);
        LOGGER.fine("Edges are " + edges);    
        showGraph(nodes, edges, 3);
        connectedComponents = SmpAutoClustUtils.findConnectedComponents(nodes, edges);
        
        return new SmpBasicGraph(nodes, edges);
    }
    
    private static IGraph getMyComponent(INode node, Vector components){
        Iterator it = components.iterator();
        IGraph ret = null;
        boolean found = false;
        while ((it.hasNext()) && (!(found))){
            IGraph next = (IGraph) it.next();
            if (next.getNodes().contains(node)){
                found = true;
                ret = next;
            }
        }
        if (ret == null){
            throw new RuntimeException("Couldn't find the graph component containing node: " + node);
        }
        return ret;
    }
    
    private static void showGraph(Collection nodes, Collection edges, int phase){
        IGraph g = new SmpBasicGraph(nodes, edges);
        javax.swing.JFrame frame = new javax.swing.JFrame();
        SmpGraphViewer viewer = new SmpGraphViewer();
        viewer.setGraph(g);
        frame.getContentPane().add(viewer);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(new java.awt.Dimension(800, 800));
        frame.setTitle("Phase " + phase);
        frame.setVisible(true); 
    }
    
}
