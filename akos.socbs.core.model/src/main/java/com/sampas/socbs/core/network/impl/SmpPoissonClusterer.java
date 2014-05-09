package com.sampas.socbs.core.network.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import org.geotools.feature.Feature;
import org.geotools.filter.Expression;

import com.sampas.socbs.core.network.IGraph;

/**
 *
 * @author jfc173
 */
public class SmpPoissonClusterer {
    
    private static double threshold = 1.0E-10;
    
    /** Creates a new instance of PoissonClusterer */
    public SmpPoissonClusterer() {
    }        
    
    public static IGraph findClusters(IGraph incoming, Expression base, Expression target, double meanRate, int distance){
        Collection nodes = incoming.getNodes();
        Iterator nodeIt = nodes.iterator();
        Vector clusterNodes = new Vector();
        Vector clusterEdges = new Vector();
        System.out.println("x, y, actual, expected, probability");
        while (nodeIt.hasNext()){
            SmpDelaunayNode next = (SmpDelaunayNode) nodeIt.next();
            Feature nextFeature = next.getFeature();
            
            Object baseObj = base.getValue(nextFeature);
            if (!(baseObj instanceof Number)){
                throw new RuntimeException("Expression " + base + " must evaluate to a number on feature " + nextFeature);
            }
            Object targetObj = target.getValue(nextFeature);
            if (!(targetObj instanceof Number)){
                throw new RuntimeException("Expression " + target + " must evaluate to a number on feature " + nextFeature);
            } 
            double totalBase = ((Number) baseObj).doubleValue();
            double totalTarget = ((Number) targetObj).doubleValue();                              
                   
            Collection newEdges = new Vector();
            Vector newNodes = new Vector();
            newNodes.add(next);
            
            if (distance == 1){
            
                newEdges = next.getEdges();
//                System.out.println("this node has " + newEdges.size() + " edges");
                Iterator edgeIt = newEdges.iterator();
                Vector removals = new Vector();
                while (edgeIt.hasNext()){
                    SmpDelaunayEdge nextEdge = (SmpDelaunayEdge) edgeIt.next();
                    if (nextEdge.getEuclideanDistance() > 30){
                        removals.add(nextEdge);
                    } else {
                        SmpDelaunayNode neighbor = (SmpDelaunayNode) nextEdge.getOtherNode(next);
                        if (neighbor == null){
                            throw new RuntimeException("We have a problem.  " + next + " and " + neighbor + " should be neighbors via " + nextEdge + ", but aren't.");
                        }
                        Feature neighborFeature = neighbor.getFeature();
                        newNodes.add(neighbor);

                        Object neighborsBaseObj = base.getValue(nextFeature);
                        if (!(baseObj instanceof Number)){
                            throw new RuntimeException("Expression " + base + " must evaluate to a number on feature " + neighborFeature);
                        }
                        Object neighborsTargetObj = target.getValue(nextFeature);
                        if (!(targetObj instanceof Number)){
                            throw new RuntimeException("Expression " + target + " must evaluate to a number on feature " + neighborFeature);
                        } 
                        totalBase = totalBase + ((Number) baseObj).doubleValue();
                        totalTarget = totalTarget + ((Number) targetObj).doubleValue(); 
                    }
                }
                newEdges.removeAll(removals);
            } else {
                for (int i = 0; i <= distance; i++){
                    Iterator nodeIt2 = newNodes.iterator();  
                    Vector nodesToAdd = new Vector();
                    Vector edgesToAdd = new Vector();
                    while (nodeIt2.hasNext()){
                        SmpDelaunayNode next2 = (SmpDelaunayNode) nodeIt2.next();
//                        System.out.println("expanding from " + next2);
                        Collection edges = next2.getEdges();
//                        System.out.println("its edges are " + edges);
                        newEdges.addAll(edges);
                        Iterator another = edges.iterator();
                        while (another.hasNext()){
                            SmpDelaunayEdge nextEdge = (SmpDelaunayEdge) another.next();
                            SmpDelaunayNode farNode = (SmpDelaunayNode) nextEdge.getOtherNode(next2);
//                            System.out.println("checking " + farNode + " in " + newNodes);
                            if (!(newNodes.contains(farNode))){
                                nodesToAdd.add(farNode);
                                edgesToAdd.add(nextEdge);
//                                System.out.println("adding node " + farNode + " and edge " + nextEdge);
                            }
                        }
                    }
                    newNodes.addAll(nodesToAdd);
                    newEdges.addAll(edgesToAdd);
                }
                
//                System.out.println("I've got " + newNodes + " and ");
//                System.out.println(newEdges);
                
                totalBase = totalTarget = 0;
                Iterator newNodeIt = newNodes.iterator();
                while (newNodeIt.hasNext()){
                    SmpDelaunayNode nextNode = (SmpDelaunayNode) newNodeIt.next();
                    Feature nextFeature2 = nextNode.getFeature();
                    Object neighborsBaseObj = base.getValue(nextFeature2);
                    if (!(baseObj instanceof Number)){
                        throw new RuntimeException("Expression " + base + " must evaluate to a number on feature " + nextFeature2);
                    }
                    Object neighborsTargetObj = target.getValue(nextFeature2);
                    if (!(targetObj instanceof Number)){
                        throw new RuntimeException("Expression " + target + " must evaluate to a number on feature " + nextFeature2);
                    } 
                    totalBase = totalBase + ((Number) baseObj).doubleValue();
                    totalTarget = totalTarget + ((Number) targetObj).doubleValue();                     
                }
            }
            double expectedTarget = meanRate * totalBase;

            double top = ((Math.pow(Math.E,(0 - expectedTarget)) * (Math.pow(expectedTarget, totalTarget))));
            double bottom = fact((int) Math.round(totalTarget));
            double poissonProb = top/bottom;
//            System.out.println("testing " + newNodes);
//            System.out.println("testing " + newEdges);
            System.out.println(next.getCoordinate().x + ", " + next.getCoordinate().y + ", " + totalTarget + ", " + expectedTarget + ", " + poissonProb);
            
            if (poissonProb < threshold){
                clusterNodes.addAll(newNodes);
                clusterEdges.addAll(newEdges);
            }            
        }  //end while (nodeIt.hasNext())
        
        return new SmpBasicGraph(clusterNodes, clusterEdges);
        
    }
    
    private static double iterFact(int i, int f){
        if ((i == 0) || (i == 1)){
            return f;
        } else {
            return iterFact(i-1, i*f);
        }
    }

    public static double fact(int i){
        return iterFact(i, 1);
    }     
    
}
