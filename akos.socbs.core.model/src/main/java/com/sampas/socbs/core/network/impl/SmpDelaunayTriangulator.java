package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.INode;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Logger;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.math.Line;
/**
 *
 * @author jfc173
 */
public class SmpDelaunayTriangulator {
   
    public SmpDelaunayNode temp1, temp2, temp3;
    private SmpDelaunayNode[] nodes;
    private Vector triangleList;
    private static final Logger LOGGER = org.geotools.util.logging.Logging.getLogger("org.geotools.graph");
    
    /** Creates a new instance of delaunayTriangulator */
    public SmpDelaunayTriangulator() {
    }
    
    public void setNodeArray(SmpDelaunayNode[] nodeArray){
        nodes = nodeArray;
    }
    
    public SmpDelaunayNode[] getNodeArray(){
        return nodes;
    }
    
    public void setFeatureCollection(FeatureCollection data){
        nodes = featuresToNodes(data);
    }
    
    public Vector getTriangles(){
        return triangleList;
    }
    
    public SmpDelaunayNode[] featuresToNodes(FeatureCollection fc){
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
    
    private static boolean arrayContains(SmpDelaunayNode node, SmpDelaunayNode[] nodes, int index){
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
    
    public IGraph getTriangulation(){
        //this algorithm is from "Computational Geometry: Algorithms and Applications" by M. de Berg et al., 
        //written in 1997 and printed by Springer-Verlag (New York).  Pseudocode from section 9.3 (pp. 190-194).
        //A few additional checks for degenerate cases were needed.  They're commented below.        
               
        //find the initial bounding triangle and supplement the nodes with its corners        
        SmpDelaunayNode[] tempNodes = new SmpDelaunayNode[nodes.length+3];
        double max = 0;
        for (int i = 0; i < nodes.length; i++){
            tempNodes[i] = nodes[i];
            max = Math.max(max, Math.abs(nodes[i].getCoordinate().x));
            max = Math.max(max, Math.abs(nodes[i].getCoordinate().y));
        }
        tempNodes[nodes.length] = new SmpDelaunayNode();
        tempNodes[nodes.length].setCoordinate(new Coordinate(0, 3*max));
        tempNodes[nodes.length+1] = new SmpDelaunayNode();
        tempNodes[nodes.length+1].setCoordinate(new Coordinate(3*max, 0));       
        tempNodes[nodes.length+2] = new SmpDelaunayNode();
        tempNodes[nodes.length+2].setCoordinate(new Coordinate(-3*max, -3*max));        
        
        temp1 = tempNodes[nodes.length];
        temp2 = tempNodes[nodes.length+1];
        temp3 = tempNodes[nodes.length+2];
        
        //initialize triangulation to the bounding triangle
        triangleList = new Vector();
        SmpDelaunayEdge e1 = new SmpDelaunayEdge(tempNodes[nodes.length], tempNodes[nodes.length+1]);
        SmpDelaunayEdge e2 = new SmpDelaunayEdge(tempNodes[nodes.length], tempNodes[nodes.length+2]);
        SmpDelaunayEdge e3 = new SmpDelaunayEdge(tempNodes[nodes.length+1], tempNodes[nodes.length+2]); 
        SmpTriangle first = new SmpTriangle(e1, e2, e3);
        e1.setFaceA(first);
        e2.setFaceA(first);
        e3.setFaceA(first);
        
        SmpDelaunayNode U1 = new SmpDelaunayNode();
        U1.setCoordinate(new Coordinate(Double.POSITIVE_INFINITY, 0));
        SmpDelaunayNode U2 = new SmpDelaunayNode();
        U2.setCoordinate(new Coordinate(0, Double.POSITIVE_INFINITY));
        SmpDelaunayNode U3 = new SmpDelaunayNode();
        U3.setCoordinate(new Coordinate(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY));    
        SmpTriangle UNBOUNDED = new SmpTriangle(new SmpDelaunayEdge(U1, U2),  
                                          new SmpDelaunayEdge(U1, U3), 
                                          new SmpDelaunayEdge(U2, U3));               
        
        e1.setFaceB(UNBOUNDED);
        e2.setFaceB(UNBOUNDED);
        e3.setFaceB(UNBOUNDED);
        
        triangleList.add(first);                
        
        //add nodes one at a time.
        for (int i = 0; i < nodes.length; i++){
            System.out.println("triangulating node " + i);
            triangleList = insertNode(tempNodes[i], triangleList);
        }

        IGraph g = triangleListToGraph(triangleList);
                
        return g;  
    }
    
    public IGraph triangleListToGraph(Vector tList){
        //turn what I've got into a proper GeoTools2 Graph!        
        //But don't include the three temporary nodes and all incident edges.
        Vector edgeList = new Vector();
        Vector nodeList = new Vector();
        Iterator triangleIterator = tList.iterator();
        while(triangleIterator.hasNext()){
            SmpTriangle next = (SmpTriangle) triangleIterator.next();
            IEdge[] edges = next.getEdges();
            for (int i = 0; i < 3; i++){
                if (!(((SmpDelaunayEdge) edges[i]).hasEndPoint(temp1) ||      //this test ensures that we don't
                      ((SmpDelaunayEdge) edges[i]).hasEndPoint(temp2) ||      //add to the edge list any edges referring                        
                      ((SmpDelaunayEdge) edges[i]).hasEndPoint(temp3))){      //to the temporary nodes
                    if (!(edgeList.contains(edges[i]))){
                        edgeList.add(edges[i]);
                        edges[i].getNodeA().add(edges[i]);
                        edges[i].getNodeB().add(edges[i]);
                        if (!(nodeList.contains(edges[i].getNodeA()))){
                            nodeList.add(edges[i].getNodeA());
                        }
                        if (!(nodeList.contains(edges[i].getNodeB()))){
                            nodeList.add(edges[i].getNodeB());
                        }                        
                    }
                }
            }
        }
        
        return new SmpBasicGraph(nodeList, edgeList);        
    }
    
    public Vector insertNode(SmpDelaunayNode newNode, Vector tList){
        //find triangle containing node or if node is on an edge, the two triangles bordering that edge.
        //this finding-the-triangle section can be given better efficiency using the method on pp. 192-193 of book mentioned above.
        Iterator triangleIterator = tList.iterator();
        SmpTriangle contains = null;
        SmpTriangle borderA = null;
        SmpTriangle borderB = null;  //Note: assuming it's on the border of two triangles rather than at the intersection of 3 or more.
        boolean notDone = true;        
        while ((triangleIterator.hasNext()) && (notDone)){
            SmpTriangle next = (SmpTriangle) triangleIterator.next();
            int relation = next.relate(newNode);
            switch (relation){
                case SmpTriangle.INSIDE:
//                    System.out.println(newNode + " is inside " + next);
                    contains = next;
                    notDone = false;
                    break;
                
                case SmpTriangle.ON_EDGE:
                    borderA = next;
                    borderB = ((SmpDelaunayEdge) next.getBoundaryEdge(newNode)).getOtherFace(next);
//                    System.out.println(newNode + " is on the border between " + borderA + " and " + borderB);
                    break;
                
                case SmpTriangle.OUTSIDE:
                    notDone = true;
                    break;
                
                default:
                    throw new RuntimeException("So the point isn't inside, outside, or on the edge of this triangle?!");
            } //end switch            
        }
        
        //Found the triangle(s).  Now do something with them!
        if (contains != null){
            //create three new triangles by adding edges from node to the vertices of contains
            INode[] triangleNodes = contains.getNodes();
            IEdge[] triangleEdges = contains.getEdges();
            
            SmpDelaunayEdge newEdgeP_0 = new SmpDelaunayEdge(newNode, (SmpDelaunayNode) triangleNodes[0]);
            SmpDelaunayEdge newEdgeP_1 = new SmpDelaunayEdge(newNode, (SmpDelaunayNode) triangleNodes[1]);
            SmpDelaunayEdge newEdgeP_2 = new SmpDelaunayEdge(newNode, (SmpDelaunayNode) triangleNodes[2]);
            
            SmpDelaunayEdge oldEdge0_1 = null;
            SmpDelaunayEdge oldEdge0_2 = null;
            SmpDelaunayEdge oldEdge1_2 = null;
            for (int i = 0; i < 3; i++){
                if (((SmpDelaunayEdge) triangleEdges[i]).hasEndPoint((SmpDelaunayNode) triangleNodes[0])){
                    if (((SmpDelaunayEdge) triangleEdges[i]).hasEndPoint((SmpDelaunayNode) triangleNodes[1])){
                        oldEdge0_1 = (SmpDelaunayEdge) triangleEdges[i];
                    } else {
                        oldEdge0_2 = (SmpDelaunayEdge) triangleEdges[i];
                    }
                } else {
                    oldEdge1_2 = (SmpDelaunayEdge) triangleEdges[i];
                }
            }
            
            SmpTriangle newTriangleP_0_1 = new SmpTriangle(newEdgeP_0, newEdgeP_1, oldEdge0_1);
            SmpTriangle newTriangleP_0_2 = new SmpTriangle(newEdgeP_0, newEdgeP_2, oldEdge0_2);
            SmpTriangle newTriangleP_1_2 = new SmpTriangle(newEdgeP_1, newEdgeP_2, oldEdge1_2);
            
            SmpTriangle farSide0_1 = oldEdge0_1.getOtherFace(contains);
            SmpTriangle farSide0_2 = oldEdge0_2.getOtherFace(contains);
            SmpTriangle farSide1_2 = oldEdge1_2.getOtherFace(contains);
            
            oldEdge0_1.setOtherFace(newTriangleP_0_1, farSide0_1);
            oldEdge0_2.setOtherFace(newTriangleP_0_2, farSide0_2);
            oldEdge1_2.setOtherFace(newTriangleP_1_2, farSide1_2);
            
            newEdgeP_0.setFaceA(newTriangleP_0_1);
            newEdgeP_0.setFaceB(newTriangleP_0_2);
            newEdgeP_1.setFaceA(newTriangleP_0_1);
            newEdgeP_1.setFaceB(newTriangleP_1_2);
            newEdgeP_2.setFaceA(newTriangleP_0_2);
            newEdgeP_2.setFaceB(newTriangleP_1_2);
            
            tList.remove(contains);
            tList.add(newTriangleP_0_1);
            tList.add(newTriangleP_0_2);
            tList.add(newTriangleP_1_2);
            LOGGER.finer("was inside " + contains);
            LOGGER.finer("triangle List now is " + tList);
//            System.out.println("triangle List now is " + tList);
            //Make any necessary adjustments to other triangles.
            legalizeEdge(newTriangleP_0_1, oldEdge0_1, newNode, tList);
            legalizeEdge(newTriangleP_0_2, oldEdge0_2, newNode, tList);
            legalizeEdge(newTriangleP_1_2, oldEdge1_2, newNode, tList);
            LOGGER.finer("after legalization, triangle list now is: " + triangleList); 
//            System.out.println("after legalization, triangle list now is: " + triangleList);
            
        } else if ((borderA != null) && (borderB != null)){
            //check to see that borderA and borderB share an edge.  If not, whinge.
            SmpDelaunayEdge shared = (SmpDelaunayEdge) borderA.getSharedEdge(borderB);
            if (shared == null){
                throw new RuntimeException("The two bordering triangles for a border case apparently don't share an edge(!)");
            }
            
            //create four new triangles by adding edges from node to the vertices of borderA and borderB
            SmpDelaunayNode shared1 = (SmpDelaunayNode) shared.getNodeA();
            SmpDelaunayNode shared2 = (SmpDelaunayNode) shared.getNodeB();
            SmpDelaunayNode onlyInA = (SmpDelaunayNode) borderA.getThirdNode(shared);
            SmpDelaunayNode onlyInB = (SmpDelaunayNode) borderB.getThirdNode(shared);
            
            SmpDelaunayEdge newEdgeP_1 = new SmpDelaunayEdge(newNode, shared1);
            SmpDelaunayEdge newEdgeP_2 = new SmpDelaunayEdge(newNode, shared2);
            SmpDelaunayEdge newEdgeP_A = new SmpDelaunayEdge(newNode, onlyInA);
            SmpDelaunayEdge newEdgeP_B = new SmpDelaunayEdge(newNode, onlyInB);
            
            SmpDelaunayEdge oldEdgeA_1 = (SmpDelaunayEdge) borderA.getOppositeEdge(shared2);
            SmpDelaunayEdge oldEdgeA_2 = (SmpDelaunayEdge) borderA.getOppositeEdge(shared1);
            SmpDelaunayEdge oldEdgeB_1 = (SmpDelaunayEdge) borderB.getOppositeEdge(shared2);           
            SmpDelaunayEdge oldEdgeB_2 = (SmpDelaunayEdge) borderB.getOppositeEdge(shared1);
            
            SmpTriangle farSideA_1 = oldEdgeA_1.getOtherFace(borderA);
            SmpTriangle farSideA_2 = oldEdgeA_2.getOtherFace(borderA);
            SmpTriangle farSideB_1 = oldEdgeB_1.getOtherFace(borderB);
            SmpTriangle farSideB_2 = oldEdgeB_2.getOtherFace(borderB);
            
            SmpTriangle newTriangleP_A_1 = new SmpTriangle(newEdgeP_A, newEdgeP_1, oldEdgeA_1);
            SmpTriangle newTriangleP_A_2 = new SmpTriangle(newEdgeP_A, newEdgeP_2, oldEdgeA_2);
            SmpTriangle newTriangleP_B_1 = new SmpTriangle(newEdgeP_B, newEdgeP_1, oldEdgeB_1);
            SmpTriangle newTriangleP_B_2 = new SmpTriangle(newEdgeP_B, newEdgeP_2, oldEdgeB_2);
            
            newEdgeP_A.setFaceA(newTriangleP_A_1);
            newEdgeP_A.setFaceB(newTriangleP_A_2);
            newEdgeP_B.setFaceA(newTriangleP_B_1);
            newEdgeP_B.setFaceB(newTriangleP_B_2);
            newEdgeP_1.setFaceA(newTriangleP_A_1);
            newEdgeP_1.setFaceB(newTriangleP_B_1);
            newEdgeP_2.setFaceA(newTriangleP_A_2);
            newEdgeP_2.setFaceB(newTriangleP_B_2);
            
            oldEdgeA_1.setOtherFace(newTriangleP_A_1, farSideA_1);
            oldEdgeA_2.setOtherFace(newTriangleP_A_2, farSideA_2);
            oldEdgeB_1.setOtherFace(newTriangleP_B_1, farSideB_1);
            oldEdgeB_2.setOtherFace(newTriangleP_B_2, farSideB_2);
            
            shared.disconnect();
            
            tList.remove(borderA);
            tList.remove(borderB);
            tList.add(newTriangleP_A_1);
            tList.add(newTriangleP_A_2);
            tList.add(newTriangleP_B_1);
            tList.add(newTriangleP_B_2);
            LOGGER.finer("bordered " + borderA + " and " + borderB);
            LOGGER.finer("triangle list now is " + tList);
            
            legalizeEdge(newTriangleP_A_1, oldEdgeA_1, newNode, tList);
            legalizeEdge(newTriangleP_A_2, oldEdgeA_2, newNode, tList);
            legalizeEdge(newTriangleP_B_1, oldEdgeB_1, newNode, tList);
            legalizeEdge(newTriangleP_B_2, oldEdgeB_2, newNode, tList);
            LOGGER.finer("after legalization, triangle list now is: " + triangleList); 
        } else {
            throw new RuntimeException("What the?  It isn't in any triangle or on any borders?");
        }  
        return tList;      
    }
 
    private void legalizeEdge(SmpTriangle t, SmpDelaunayEdge e, SmpDelaunayNode p, Vector triangleList){
        LOGGER.fine("legalizing " + t + " and " + e.getOtherFace(t));
        if (isIllegal(t, e, p)){
            SmpTriangle otherFace = e.getOtherFace(t);  
            LOGGER.finer("switch internal edge");
//            System.out.println("switch internal edge");
            SmpDelaunayNode fourthCorner = (SmpDelaunayNode) otherFace.getThirdNode(e);
            SmpDelaunayNode eNodeA = (SmpDelaunayNode) e.getNodeA();
            SmpDelaunayNode eNodeB = (SmpDelaunayNode) e.getNodeB();
            //replace e with a new edge from p to fourthCorner
            SmpDelaunayEdge edgeP_4 = new SmpDelaunayEdge(p, fourthCorner);
            SmpDelaunayEdge edgeP_A = (SmpDelaunayEdge) t.getOppositeEdge(eNodeB);
            SmpDelaunayEdge edgeP_B = (SmpDelaunayEdge) t.getOppositeEdge(eNodeA);
            SmpDelaunayEdge edgeA_4 = (SmpDelaunayEdge) otherFace.getOppositeEdge(eNodeB);
            SmpDelaunayEdge edgeB_4 = (SmpDelaunayEdge) otherFace.getOppositeEdge(eNodeA);
            
            SmpTriangle farSideP_A = edgeP_A.getOtherFace(t);
            SmpTriangle farSideP_B = edgeP_B.getOtherFace(t);
            SmpTriangle farSideA_4 = edgeA_4.getOtherFace(otherFace);
            SmpTriangle farSideB_4 = edgeB_4.getOtherFace(otherFace);
            
            SmpTriangle newTriangleP_A_4 = new SmpTriangle(edgeP_A, edgeA_4, edgeP_4);
            SmpTriangle newTriangleP_B_4 = new SmpTriangle(edgeP_B, edgeB_4, edgeP_4);
            
            if (rejectSwap(t, otherFace, newTriangleP_A_4, newTriangleP_B_4)){ //Degenerate case.  Explained in the method rejectSwap
                LOGGER.finer("Rejected swap of " + t + " and " + otherFace);
//                System.out.println("Rejected swap of " + t + " and " + otherFace);
            } else {                         
                edgeP_A.setOtherFace(newTriangleP_A_4, farSideP_A);
                edgeP_B.setOtherFace(newTriangleP_B_4, farSideP_B);
                edgeA_4.setOtherFace(newTriangleP_A_4, farSideA_4);
                edgeB_4.setOtherFace(newTriangleP_B_4, farSideB_4);

                edgeP_4.setFaceA(newTriangleP_A_4);
                edgeP_4.setFaceB(newTriangleP_B_4);
                       
                e.disconnect();
                
                triangleList.remove(t);
                triangleList.remove(otherFace);
                triangleList.add(newTriangleP_A_4);
                triangleList.add(newTriangleP_B_4);
                LOGGER.finer("swapped " + t + " and " + otherFace);
                LOGGER.finer("new triangles are " + newTriangleP_A_4 + " and " + newTriangleP_B_4);
                LOGGER.finer("Triangle list now is: " + triangleList);     
//                System.out.println("swapped " + t + " and " + otherFace);
//                System.out.println("new triangles are " + newTriangleP_A_4 + " and " + newTriangleP_B_4);
//                System.out.println("Triangle list now is: " + triangleList);
                legalizeEdge(newTriangleP_A_4, edgeA_4, p, triangleList);
                legalizeEdge(newTriangleP_B_4, edgeB_4, p, triangleList);  
            }
        }
    }
    
    private boolean isTemporary(SmpDelaunayNode n){
        return ((n.equals(temp1)) || (n.equals(temp2)) || (n.equals(temp3)));
    }
    
    private boolean isIllegal(SmpTriangle t, SmpDelaunayEdge e, SmpDelaunayNode p){
        SmpDelaunayNode eNodeA = (SmpDelaunayNode) e.getNodeA();
        SmpDelaunayNode eNodeB = (SmpDelaunayNode) e.getNodeB();

        if (isTemporary(eNodeA) && isTemporary(eNodeB)){
            return false;
        }         
        
        SmpDelaunayNode farNode = (SmpDelaunayNode) e.getOtherFace(t).getThirdNode(e);

        SmpDelaunayEdge p_a = ((SmpDelaunayEdge) t.getOppositeEdge(e.getNodeB()));
        SmpDelaunayEdge p_b = ((SmpDelaunayEdge) t.getOppositeEdge(e.getNodeA()));
        SmpDelaunayNode farNodeP_A = (SmpDelaunayNode) p_a.getOtherFace(t).getThirdNode(p_a);
        SmpDelaunayNode farNodeP_B = (SmpDelaunayNode) p_b.getOtherFace(t).getThirdNode(p_b);
        
        if ((farNode.equals(farNodeP_A)) || (farNode.equals(farNodeP_B))){
            //Degenerate case.  There's already a line between p and farnode (p and k in the book) making either
            //p_farnode_A or p_farNode_B a triangle already in the triangulation.  This will eventually manifest
            //itself as trying to create a triangle with two points....  Not a good situation.
            return false;
        }
        
        int numTemporary = 0;
        if (isTemporary(eNodeA)){
            numTemporary++;
        }
        if (isTemporary(eNodeB)){
            numTemporary++;
        }
        if (isTemporary(p)){
            numTemporary++;
        }
        if (isTemporary(farNode)){
            numTemporary++;
        }        
        
        if (numTemporary == 0){
            Ellipse2D.Double circum = constructCircle(p, eNodeA, eNodeB);
            Point2D.Double point = new Point2D.Double(farNode.getCoordinate().x, farNode.getCoordinate().y);
            if (circum.contains(point)){
                LOGGER.finer("Illegal by case 2");
//                System.out.println("Illegal by case 2");
                return true;
            } else {
                return false;
            }            
        } else if (numTemporary == 1){
            if (isTemporary(eNodeA) || isTemporary(eNodeB)){
                LOGGER.finer("Illegal by case 3");
//                System.out.println("Illegal by case 3");
                return true;
            } else {
                return false;
            }
        } else if (numTemporary == 2){
            int i = whichSpecialNode(eNodeA, eNodeB);
            int j = whichSpecialNode(p, farNode);
            if (i<j){  //originally i<j.  i<j for messedUp3.doc, i>j for messedUp1.doc
                return false;
            } else {
                LOGGER.finer("Illegal by case 4");
//                System.out.println("Illegal by case 4");
                return true;
            }
        } else {
            throw new RuntimeException("Problem in DelaunayTriangulator.isIllegal--This shouldn't've happened!");
        }
    }
    
    private int whichSpecialNode(SmpDelaunayNode a, SmpDelaunayNode b){
        if ((a.equals(temp1)) || (b.equals(temp1))){
            return 1;
        } else if ((a.equals(temp2)) || (b.equals(temp2))){
            return 2;            
        } else if ((a.equals(temp3)) || (b.equals(temp3))){
            return 3;
        } else {
            throw new RuntimeException("I shouldn't be here.  Either node a or node b should be temporary.");
        }
    }
    
    private static Ellipse2D.Double constructCircle(SmpDelaunayNode a, SmpDelaunayNode b, SmpDelaunayNode c){
        //center of this circle is the intersection of the perpendicular bisectors of the triangle
        
        Point2D.Double midPointA_B = new Point2D.Double((a.getCoordinate().x + b.getCoordinate().x)/2,
                                                        (a.getCoordinate().y + b.getCoordinate().y)/2);
        double deltaXA_B = a.getCoordinate().x - midPointA_B.getX();
        double deltaYA_B = a.getCoordinate().y - midPointA_B.getY();

 
        Line bisectorA_B = new Line();
        bisectorA_B.setLine(new Point2D.Double((midPointA_B.getX() + 100*deltaYA_B), 
                                               (midPointA_B.getY() - 100*deltaXA_B)),
                            new Point2D.Double((midPointA_B.getX() - 100*deltaYA_B),
                                               (midPointA_B.getY() + 100*deltaXA_B)));
        
        Point2D.Double midPointA_C = new Point2D.Double((a.getCoordinate().x + c.getCoordinate().x)/2,
                                                        (a.getCoordinate().y + c.getCoordinate().y)/2);
        double deltaXA_C = a.getCoordinate().x - midPointA_C.getX();
        double deltaYA_C = a.getCoordinate().y - midPointA_C.getY();      
        
        Line bisectorA_C = new Line();
        
        Point2D intersection = null;
        int i = 1;
        do{
            bisectorA_C.setLine(new Point2D.Double((midPointA_C.getX() + Math.pow(100, i)*deltaYA_C), 
                                                   (midPointA_C.getY() - Math.pow(100, i)*deltaXA_C)),
                                new Point2D.Double((midPointA_C.getX() - Math.pow(100, i)*deltaYA_C),
                                                   (midPointA_C.getY() + Math.pow(100, i)*deltaXA_C)));        
            intersection = bisectorA_B.intersectionPoint(bisectorA_C);
            i++;
        } while (intersection == null);                
        
        //radius is the distance to the three points (which is hopefully the same!)        
        double radius = intersection.distance(a.getCoordinate().x, a.getCoordinate().y);
                
        //convert from center-radius to the java format
        Ellipse2D.Double circle = new Ellipse2D.Double(intersection.getX()-radius, 
                                                       intersection.getY()-radius, 
                                                       2*radius,
                                                       2*radius);
                
        return circle;  
    }
    
    private boolean rejectSwap(SmpTriangle oldFirst, SmpTriangle oldSecond, SmpTriangle newFirst, SmpTriangle newSecond){
        //I want to reject the swap if the new edge intersects any other edges in the graph, which can happen in
        //the case where A or B (i or j in the book) is one of the bounding triangle points.  This (I think)
        //is equivalent to ensuring that the areas of the new triangles is the same as the areas of the old triangles.
        double oldArea = oldFirst.getArea() + oldSecond.getArea();
        double newArea = newFirst.getArea() + newSecond.getArea();
        double diff = newArea - oldArea;
//        System.out.println("area difference is " + diff);
        double tolerance = 0.000001;
        return (diff > tolerance);   
    }
    
}
