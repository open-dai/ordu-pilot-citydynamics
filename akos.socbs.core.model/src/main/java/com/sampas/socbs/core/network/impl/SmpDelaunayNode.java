package com.sampas.socbs.core.network.impl;

import org.geotools.feature.Feature;
/**
 *
 * @author jfc173
 */
public class SmpDelaunayNode extends SmpBasicXYNode{    
    
    private Feature feature;
    
    /** Creates a new instance of delaunayNode */
    public SmpDelaunayNode() {    
    }

    public void setFeature(Feature f){
        feature = f;
    }
    
    public Feature getFeature(){
        return feature;
    }
    
    public boolean equals(Object o){
        return ((o instanceof SmpDelaunayNode) &&
                (this.getCoordinate().x == ((SmpDelaunayNode)o).getCoordinate().x) &&
                (this.getCoordinate().y == ((SmpDelaunayNode)o).getCoordinate().y));
    }

/*    waiting until we use 1.5 and Math.log10 becomes available!
    private double roundToSigDigs(double d, int digits){
        if (d == 0){
            return 0;
        } else {
            double log = Math.log10(d);
            int digitsLeftOfDecimal = (int) Math.ceil(log);
            int digitsToMoveLeft = digits - digitsLeftOfDecimal;
            double movedD = d*Math.pow(10, digitsToMoveLeft);
            double rounded = Math.rint(movedD);
            double ret = rounded / Math.pow(10, digitsToMoveLeft);
            return ret;
        }
    }    
*/
    
    public String toString(){
//        return "(" + roundToSigDigs(this.getCoordinate().x, 5) + "," + roundToSigDigs(this.getCoordinate().y, 5) + ")";
	return "(" + this.getCoordinate().x + "," + this.getCoordinate().y + ")";  
    }
    
}