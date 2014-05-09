package com.sampas.socbs.core.network.impl;

import java.util.logging.Logger;

import com.sampas.socbs.core.network.IXYNode;
/**
 *
 * @author jfc173
 */
public class SmpDelaunayEdge extends SmpBasicEdge{
    
    private static final Logger LOGGER = org.geotools.util.logging.Logging.getLogger("org.geotools.graph");
    SmpTriangle faceA, faceB;
    
    /** Creates a new instance of DelaunayEdge */
    public SmpDelaunayEdge(IXYNode nodeA, IXYNode nodeB){
        super(nodeA, nodeB);    
    }
    
    public void disconnect(){
        this.getNodeA().remove(this);
        this.getNodeB().remove(this);        
    }
 
    public void setFaceA(SmpTriangle t){
        if (t.equals(faceB)){
            throw new RuntimeException("Face A must be different from Face B.");
        }
        faceA = t;
    }
    
    public void setFaceB(SmpTriangle t){
        if (t.equals(faceA)){
            throw new RuntimeException("Face A must be different from Face B.");
        }
        faceB = t;
    }
    
    public boolean hasEndPoint(IXYNode node){
        return ((node.equals(this.getNodeA())) || (node.equals(this.getNodeB())));
    }
    
    public SmpTriangle getOtherFace(SmpTriangle t){
        if (faceA.equals(t)){
            return faceB;
        } else if (faceB.equals(t)){
            return faceA;
        } else {
            LOGGER.warning("Oops.  Input face is " + t);
            LOGGER.warning("Face A is " + faceA);
            LOGGER.warning("Face B is " + faceB);
            throw new RuntimeException("DelaunayEdge.getOtherFace must receive as input one of the faces bordering that edge.");
        }
    }
    
    public void setOtherFace(SmpTriangle newT, SmpTriangle oldT){
        if (faceA.equals(oldT)){
            if (newT.equals(faceA)){
                LOGGER.warning("Oops.  Face A is " + faceA + " and new Triangle is " + newT);
                throw new RuntimeException("Face A must be different from Face B.");
            }            
            this.setFaceB(newT);
        } else if (faceB.equals(oldT)){
            if (newT.equals(faceB)){
                LOGGER.warning("Oops.  Face B is " + faceB + " and new Triangle is " + newT);
                throw new RuntimeException("Face A must be different from Face B.");
            }             
            this.setFaceA(newT);
        } else {
            throw new RuntimeException("DelaunayEdge.setOtherFace must have either faceA or faceB as the oldT parameter.");
        }
    }
    
    public double getEuclideanDistance(){
        return ((IXYNode) this.getNodeA()).getCoordinate().distance(((IXYNode) this.getNodeB()).getCoordinate());
    }
    
    public boolean equals(Object o){
        return ((o instanceof SmpDelaunayEdge) &&
                ((this.getNodeA().equals(((SmpDelaunayEdge)o).getNodeA()) && (this.getNodeB().equals(((SmpDelaunayEdge)o).getNodeB()))) ||                 
                 (this.getNodeA().equals(((SmpDelaunayEdge)o).getNodeB()) && (this.getNodeB().equals(((SmpDelaunayEdge)o).getNodeA())))));                 
    }
    
    public String toString(){
        return this.getNodeA().toString() + "--" + this.getNodeB().toString();
    }
    
}
