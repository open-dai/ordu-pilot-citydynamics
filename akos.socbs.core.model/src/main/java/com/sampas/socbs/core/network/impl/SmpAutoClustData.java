package com.sampas.socbs.core.network.impl;

import java.util.List;

/**
 *
 * @author jfc173
 */
public class SmpAutoClustData {
    
    double localMean;
    double localStDev; 
    List shortEdges;
    List longEdges;
    List otherEdges;
    
    /** Creates a new instance of AutoClustData */
    public SmpAutoClustData() {
    }
    
    public void setLocalMean(double d){
        localMean = d;
    }
    
    public double getLocalMean(){
        return localMean;
    }
    
    public void setLocalStDev(double d){
        localStDev = d;
    }
    
    public double getLocalStDev(){
        return localStDev;
    }
    
    public void setShortEdges(List l){
        shortEdges = l;
    }
    
    public List getShortEdges(){
        return shortEdges;
    }
       
    public void setLongEdges(List l){
        longEdges = l;
    }
    
    public List getLongEdges(){
        return longEdges;
    }
    
    public void setOtherEdges(List l){
        otherEdges = l;
    }
    
    public List getOtherEdges(){
        return otherEdges;
    }    
}
