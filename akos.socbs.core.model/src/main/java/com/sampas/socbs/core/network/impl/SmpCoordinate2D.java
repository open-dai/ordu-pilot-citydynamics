package com.sampas.socbs.core.network.impl;

public class SmpCoordinate2D {
  public double x;
  public double y;
  
  public SmpCoordinate2D(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public boolean equals(Object obj) {
    if (obj instanceof SmpCoordinate2D) {
      SmpCoordinate2D other = (SmpCoordinate2D)obj;
      return(x == other.x && y == other.y);
    }
    return(false);
  }
  
  public int hashCode() {
    long v = Double.doubleToLongBits(x + y);
    return((int)(v^(v>>>32)));  
  }  
}